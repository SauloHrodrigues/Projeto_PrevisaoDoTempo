package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperDadosMetearologicos;
import com.projeto_final.PrevisaoDoTempo.repositories.CityRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.MeteorologicalDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class MeteorologicalDataService {
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final MeteorologicalDataRepository meteorologicalDataRepository;
    
    public CityResponseDto registerNewMeteorologicalData(MeteorologicalDataRequestDto dataRequestDto) {
        City city = cityRepository.findByNome(dataRequestDto.getNomeDaCidade())
                .orElseThrow(() -> new NoSuchElementException("City não encontrada após a verificação de existência."));

        MeteorologicalData newMeteorologicalData = MapperDadosMetearologicos.dtoToEntity(dataRequestDto, city);
        meteorologicalDataRepository.save(newMeteorologicalData);
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setNome(city.getNome());
        cityResponseDto.getDadosMeteorologicos().add(newMeteorologicalData);
        return cityResponseDto;
    }

    public void deletarMeteorologicalDataById(Long id) {
        MeteorologicalData meteorologicalData = meteorologicalDataRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dado não encontrado"));
        meteorologicalDataRepository.delete(meteorologicalData);
    }

    public void chageMeteorologicalData(Long id, MeteorologicalDataRequestDto dataRequestDto) {
        MeteorologicalData meteorologicalData = meteorologicalDataRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dado não encontrado"));
        if (dataRequestDto.getData() != null) {
            meteorologicalData.setData(dataRequestDto.getData());
        }
        if (dataRequestDto.getTemperaturaMinima() != null) {
            meteorologicalData.setTemperaturaMinima(dataRequestDto.getTemperaturaMinima());
        }
        if (dataRequestDto.getTemperaturaMaxima() != null) {
            meteorologicalData.setTemperaturaMaxima(dataRequestDto.getTemperaturaMaxima());
        }
        if (dataRequestDto.getTurno() != null) {
            meteorologicalData.setTurno(dataRequestDto.getTurno());
        }
        if (dataRequestDto.getClima() != null) {
            meteorologicalData.setClima(dataRequestDto.getClima());
        }
        if (dataRequestDto.getPrecipitacao() != null) {
            meteorologicalData.setPrecipitacao(dataRequestDto.getPrecipitacao());
        }
        meteorologicalDataRepository.save(meteorologicalData);
    }
}