package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;

import java.util.List;

public class CidadeFixture {

    public static City gerarCidade(CityRequestDdo dto) {
        City city = new City();
        city.setNome(dto.getNome());
        city.setId(1L);
        if (dto.getDadosMeteorologicos() != null) {
            MeteorologicalData dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
            city.getDadosMeteorologicos().add(dado);
        }
        return city;
    }

    public static City gerarCidade(String nome) {
        CityRequestDdo dto = CidadeFixture.gerarCidadeRequestDto(nome);
        return CidadeFixture.gerarCidade(dto);
    }
    public static City gerarCidade(String nome, MeteorologicalData meteorologicalData) {
        City city = new City();
        city.setNome(nome);
        city.getDadosMeteorologicos().add(meteorologicalData);
        return city;
    }
    public static City gerarCidade(String nome, List<MeteorologicalData> dados) {
        City city = new City();
        city.setNome(nome);
        city.setDadosMeteorologicos(dados);
        return city;
    }

    public static CityResponseDto cidadeResponseDto(City city) {
        CityResponseDto responseDto = new CityResponseDto();
        responseDto.setId(1l);
        responseDto.setNome(city.getNome());
        if (!city.getDadosMeteorologicos().isEmpty()) {
            responseDto.setDadosMeteorologicos(city.getDadosMeteorologicos());
        }
        return responseDto;
    }
    public static CityRequestDdo gerarCidadeRequestDto(String nomeDaCidade) {
        CityRequestDdo cidadeDto = new CityRequestDdo();
        cidadeDto.setNome(nomeDaCidade);
        return cidadeDto;
    }
    public static CityRequestDdo gerarCidadeRequestDto(String nomeDaCidade, MeteorologicalDataRequestDto dados) {
        CityRequestDdo cidadeDto = new CityRequestDdo();
        cidadeDto.setNome(nomeDaCidade);
       cidadeDto.setDadosMeteorologicos(dados);
        return cidadeDto;
    }
}
