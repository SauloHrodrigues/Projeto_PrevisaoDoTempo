package com.projeto_final.PrevisaoDoTempo.mapper;

import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;

public class MapperDadosMetearologicos {

    public static DadoMeteorologicoResponseDto entityToResponseDto(DadoMeteorologico dado) {
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

    public static DadoMeteorologico dtoToEntity (DadoMeteorologicoRequestDto dado, Cidade cidade){
        DadoMeteorologico novoDado = new DadoMeteorologico();
        novoDado.setData(dado.getData());
        novoDado.setTemperaturaMinima(dado.getTemperaturaMinima());
        novoDado.setTemperaturaMaxima(dado.getTemperaturaMaxima());
        novoDado.setTurno(dado.getTurno());
        novoDado.setClima(dado.getClima());
        novoDado.setPrecipitacao(dado.getPrecipitacao());
        novoDado.setCidade(cidade);
        return novoDado;
    }

    //    public static DadoMeteorologico dtoToEntity (DadoMeteorologicoRequestDto dados){
//        DadoMeteorologico novoDado = new DadoMeteorologico();
//        novoDado.setData(dados.getData());
//        novoDado.setTemperaturaMinima(dados.getTemperaturaMinima());
//        novoDado.setTemperaturaMaxima(dados.getTemperaturaMaxima());
//        novoDado.setTurno(dados.getTurno());
//        novoDado.setClima(dados.getClima());
//        novoDado.setPrecipitacao(dados.getPrecipitacao());
//
//        return novoDado;
//
//    }
}
