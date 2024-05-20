package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade cadastrarCidade(CidadeRequestDdo cidadeRequestDdo){
        Cidade novaCidade = new Cidade();
        DadoMeteorologico dadoMeteorologico = new DadoMeteorologico();
        BeanUtils.copyProperties(cidadeRequestDdo, novaCidade);
        BeanUtils.copyProperties(cidadeRequestDdo.getDadosMeteorologicos(), dadoMeteorologico);
        novaCidade.getDadosMeteorologicos().put(cidadeRequestDdo.getDadosMeteorologicos().getData(), cidadeRequestDdo.getDadosMeteorologicos());
        return cidadeRepository.save(novaCidade);
//        gravarNoBanco(null, cidadeRequestDdo);
    }

    public List<Cidade> listar(){
        List<Cidade> response = cidadeRepository.findAll();

        return response;
    }



}
