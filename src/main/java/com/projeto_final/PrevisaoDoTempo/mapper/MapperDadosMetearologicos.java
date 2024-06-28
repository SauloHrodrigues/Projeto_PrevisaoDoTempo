package com.projeto_final.PrevisaoDoTempo.mapper;

import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;

public class MapperDadosMetearologicos {

    public static MeteorologicalDataResponseDto entityToResponseDto(MeteorologicalData meteorologicalData) {
        MeteorologicalDataResponseDto responseDto = new MeteorologicalDataResponseDto();
        responseDto.setId(meteorologicalData.getId());
        responseDto.setData(meteorologicalData.getData());
        responseDto.setTemperaturaMinima(meteorologicalData.getTemperaturaMinima());
        responseDto.setTemperaturaMaxima(meteorologicalData.getTemperaturaMaxima());
        responseDto.setTurno(meteorologicalData.getTurno());
        responseDto.setClima(meteorologicalData.getClima());
        responseDto.setPrecipitacao(meteorologicalData.getPrecipitacao());
        return responseDto;
    }

    public static MeteorologicalData dtoToEntity (MeteorologicalDataRequestDto meteorologicalDataRequestDto, City city){
        MeteorologicalData newMeteorologicalData = new MeteorologicalData();
        newMeteorologicalData.setData(meteorologicalDataRequestDto.getData());
        newMeteorologicalData.setTemperaturaMinima(meteorologicalDataRequestDto.getTemperaturaMinima());
        newMeteorologicalData.setTemperaturaMaxima(meteorologicalDataRequestDto.getTemperaturaMaxima());
        newMeteorologicalData.setTurno(meteorologicalDataRequestDto.getTurno());
        newMeteorologicalData.setClima(meteorologicalDataRequestDto.getClima());
        newMeteorologicalData.setPrecipitacao(meteorologicalDataRequestDto.getPrecipitacao());
        newMeteorologicalData.setCidade(city);
        return newMeteorologicalData;
    }

}
