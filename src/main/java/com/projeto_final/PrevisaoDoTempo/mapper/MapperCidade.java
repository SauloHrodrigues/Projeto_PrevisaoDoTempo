package com.projeto_final.PrevisaoDoTempo.mapper;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;

public class MapperCidade {

    public static City dtoToEntity(CityRequestDdo cityRequestDdo){
        City novaCity = new City();
        novaCity.setNome(cityRequestDdo.getNome());
        return novaCity;
    }

    public static CityResponseDto entityToResponseDto(City city) {
        CityResponseDto responseDto = new CityResponseDto();
        responseDto.setId(city.getId());
        responseDto.setNome(city.getNome());
        responseDto.setDadosMeteorologicos(city.getDadosMeteorologicos());
        return responseDto;
    }
}
