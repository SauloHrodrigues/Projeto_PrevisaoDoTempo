package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import com.projeto_final.PrevisaoDoTempo.exception.CityNotFind;
import com.projeto_final.PrevisaoDoTempo.exception.MeteorologicalDataNotFind;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperDadosMetearologicos;
import com.projeto_final.PrevisaoDoTempo.repositories.CityRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.MeteorologicalDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MeteorologicalDataService {

    private final CityRepository cityRepository;

    private final MeteorologicalDataRepository meteorologicalDataRepository;
    
    public CityResponseDto registerNewMeteorologicalData(MeteorologicalDataRequestDto dataRequestDto) {
        City city = cityRepository.findByNome(dataRequestDto.getNomeDaCidade())
                .orElseThrow(() -> new CityNotFind("City não encontrada."));

        MeteorologicalData newMeteorologicalData = MapperDadosMetearologicos.dtoToEntity(dataRequestDto, city);
        meteorologicalDataRepository.save(newMeteorologicalData);
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setNome(city.getNome());
        cityResponseDto.getDadosMeteorologicos().add(newMeteorologicalData);
        return cityResponseDto;
    }

    public void deletarMeteorologicalDataById(Long id) {
        MeteorologicalData meteorologicalData = searchMeteorologicalData(id);
        meteorologicalDataRepository.delete(meteorologicalData);
    }

    public void chageMeteorologicalData(Long id, MeteorologicalDataRequestDto dataRequestDto) {
        MeteorologicalData meteorologicalData = searchMeteorologicalData(id);
            meteorologicalData.setData(dataRequestDto.getData());
            meteorologicalData.setTemperaturaMinima(dataRequestDto.getTemperaturaMinima());
            meteorologicalData.setTemperaturaMaxima(dataRequestDto.getTemperaturaMaxima());
            meteorologicalData.setTurno(dataRequestDto.getTurno());
            meteorologicalData.setClima(dataRequestDto.getClima());

            meteorologicalData.setPrecipitacao(dataRequestDto.getPrecipitacao());

        meteorologicalDataRepository.save(meteorologicalData);
    }

    private MeteorologicalData searchMeteorologicalData(Long id) {
        Optional<MeteorologicalData> meteorologicalData = meteorologicalDataRepository.findById(id);
        if (meteorologicalData.isPresent()) {
            return meteorologicalData.get();
        }else {
            throw new CityNotFind("Dado meteorologico não encontrado");
        }
    }
}