package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

public class AuxiliarService {

    protected DadoMeteorologico criarNovoDadoMeteirologico(DadoMeteorologicoRequestDto dados) {
        DadoMeteorologico novoDadoMeteorologico = new DadoMeteorologico();
        novoDadoMeteorologico.setData(dados.getData());
        novoDadoMeteorologico.setTemperaturaMinima(dados.getTemperaturaMinima());
        novoDadoMeteorologico.setTemperaturaMaxima(dados.getTemperaturaMaxima());
        novoDadoMeteorologico.setTurno(dados.getTurno());
        novoDadoMeteorologico.setClima(dados.getClima());
        novoDadoMeteorologico.setPrecipitacao(dados.getPrecipitacao());
        convertDadoMeteorologicoToResponseDto(novoDadoMeteorologico);
        return novoDadoMeteorologico;
    }

    protected DadoMeteorologicoResponseDto convertDadoMeteorologicoToResponseDto(DadoMeteorologico dado) {
        DadoMeteorologicoResponseDto responseDto = new DadoMeteorologicoResponseDto();
        responseDto.setId(dado.getId());
        responseDto.setData(dado.getData());
        responseDto.setTemperaturaMinima(dado.getTemperaturaMinima());
        responseDto.setTemperaturaMaxima(dado.getTemperaturaMaxima());
        responseDto.setTurno(dado.getTurno());
        responseDto.setClima(dado.getClima());
        responseDto.setPrecipitacao(dado.getPrecipitacao());
        return responseDto;
    }

    protected Cidade criarNovaCidade(CidadeRequestDdo cidadeRequestDdo) {
        DadoMeteorologico novoDadoMeteorologico;
        Cidade novaCidade = new Cidade();
        novaCidade.setNome(cidadeRequestDdo.getNome());
        if(cidadeRequestDdo.getDadosMeteorologicos()!=null){
            novoDadoMeteorologico = criarNovoDadoMeteirologico(cidadeRequestDdo.getDadosMeteorologicos());
            novaCidade.getDadosMeteorologicos().add(novoDadoMeteorologico);
        }
        return novaCidade;
    }

    protected CidadeResponseDto convertCidadeToResponseDto(Cidade cidade) {
        CidadeResponseDto responseDto = new CidadeResponseDto();
        responseDto.setId(cidade.getId());
        responseDto.setNome(cidade.getNome());
        responseDto.setDadosMeteorologicos(cidade.getDadosMeteorologicos());
        return responseDto;
    }

}