package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CidadeService {


    private final CidadeRepository cidadeRepository;



    public Cidade cadastrarCidade(CidadeRequestDdo cidadeRequestDdo){
        Cidade novaCidade = new Cidade();
        DadoMeteorologico dadoMeteorologico = new DadoMeteorologico();
        BeanUtils.copyProperties(cidadeRequestDdo, novaCidade);
        BeanUtils.copyProperties(cidadeRequestDdo.getDadosMeteorologicos(), dadoMeteorologico);
        novaCidade.getDadosMeteorologicos().put(cidadeRequestDdo.getDadosMeteorologicos().getData(), cidadeRequestDdo.getDadosMeteorologicos());
        return cidadeRepository.save(novaCidade);

    }

    public List<Cidade> listar(){
        List<Cidade> response = cidadeRepository.findAll();
        return response;
    }

    public CidadeResponseDto retornaDadosMeteorologicoPorCidade(String nomeDaCidade){
        Cidade  cidadePesquisada = cidadeRepository.findByNome(nomeDaCidade).orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
        CidadeResponseDto cidadeResponseDto = new CidadeResponseDto();
        BeanUtils.copyProperties(cidadePesquisada,cidadeResponseDto);
        return cidadeResponseDto;

    }



}
