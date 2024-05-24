package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CidadeService  extends AuxiliarService {

    private final CidadeRepository cidadeRepository;

    public void cadastrarCidade(CidadeRequestDdo cidadeRequestDdo) {
        Cidade novaCidade = criarNovaCidade(cidadeRequestDdo);
        cidadeRepository.save(novaCidade);
    }

    public List<Cidade> listar() {
        List<Cidade> response = cidadeRepository.findAll();
        return response;
    }

    public CidadeResponseDto retornaDadosMeteorologicoPorCidade(String nomeDaCidade) {
        Cidade cidadePesquisada = pesquisarCidadePorNome(nomeDaCidade);
        CidadeResponseDto cidadeResponseDto = convertCidadeToResponseDto(cidadePesquisada);
        return cidadeResponseDto;
    }

    public CidadeResponseDto cadastrarDadosMeteorologicos(DadoMeteorologicoRequestDto dadoMeteorologico, String cidade) {
        Cidade cidadeEntity = pesquisarCidadePorNome(cidade);
        DadoMeteorologico novoDadoMeteorologico = criarNovoDadoMeteirologico(dadoMeteorologico);
        cidadeEntity.getDadosMeteorologicos().add(novoDadoMeteorologico);
        cidadeRepository.save(cidadeEntity);
        CidadeResponseDto cidadeAtualizada = convertCidadeToResponseDto(cidadeEntity);
        return cidadeAtualizada;
    }

    private Cidade pesquisarCidadePorNome(String cidade) {
        return cidadeRepository.findByNome(cidade).orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
    }

}