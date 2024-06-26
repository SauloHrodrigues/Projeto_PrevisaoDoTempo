package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import com.projeto_final.PrevisaoDoTempo.exception.CityNotFind;
import com.projeto_final.PrevisaoDoTempo.exception.FindCityException;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperCidade;
import com.projeto_final.PrevisaoDoTempo.repositories.CityRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.MeteorologicalDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final MeteorologicalDataRepository dataRepository;

    public CityResponseDto registerNewCity(CityRequestDdo cityRequestDdo) {
        Optional<City> city = cityRepository.findByNome(cityRequestDdo.getNome());
        if (city.isPresent()) {
            throw new FindCityException("Cidade já existente!");
        }
        City newCity = MapperCidade.dtoToEntity(cityRequestDdo);
        newCity.setNome(cityRequestDdo.getNome());

        if (cityRequestDdo.getDadosMeteorologicos() != null) {
            MeteorologicalData newWeatherData = createNewWeatherData(cityRequestDdo.getDadosMeteorologicos());
            newWeatherData.setCidade(newCity);
            newCity.getDadosMeteorologicos().add(newWeatherData);
        }
        cityRepository.save(newCity);

        return MapperCidade.entityToResponseDto(newCity);
    }

    public List<City> registeredCityList() {
        List<City> response = cityRepository.findAll();
        return response;
    }

    public CityResponseDto returnsWeathersDataForTheNextSevenDays(String nomeDaCidade) {
        City cityBuscada = searchCity(nomeDaCidade);
        List<MeteorologicalData> allCityWeatherData = cityBuscada.getDadosMeteorologicos();
        List<MeteorologicalData> selectCityWeatherData = new ArrayList<>();
        LocalDate data = LocalDate.now();
        LocalDate dataFutura = LocalDate.now().plusDays(7);

        for (MeteorologicalData dado : allCityWeatherData) {
            if (dado.getData().isAfter(data) && dado.getData().isBefore(dataFutura.plusDays(1))) {
                selectCityWeatherData.add(dado);
            }
        }

        CityResponseDto response = new CityResponseDto();
        response.setId(cityBuscada.getId());
        response.setNome(cityBuscada.getNome());
        response.setDadosMeteorologicos(selectCityWeatherData);
        return response;
    }

    public CityResponseDto returnsWeathersDataByCity(String nameOfTheCity) {
        City cityPesquisada = searchCity(nameOfTheCity);
        return MapperCidade.entityToResponseDto(cityPesquisada);
    }

    public void deletarCity(String nameOfTheCity) {
        City citySearched = searchCity(nameOfTheCity);
        cityRepository.deleteById(citySearched.getId());
    }

    public CityResponseDto returnsTodayWeathersData(String nameOfTheCity) {
        CityResponseDto searchCity = returnsWeathersDataByCity(nameOfTheCity);
        List<MeteorologicalData> meteorologicalData = searchCity.getDadosMeteorologicos();
        List<MeteorologicalData> todaysData = new ArrayList<>();

        for (MeteorologicalData dado : meteorologicalData) {
            if (dado.getData().equals(LocalDate.now())) {
                todaysData.add(dado);
            }
        }

        CityResponseDto response = new CityResponseDto();
        response.setId(searchCity.getId());
        response.setNome(searchCity.getNome());
        response.setDadosMeteorologicos(todaysData);
        return response;
    }

    private City searchCity(String nameOfTheCity) {
        return cityRepository.findByNome(nameOfTheCity).orElseThrow(()->
                new CityNotFind("Cidade não encontrada!"));
    }

    private MeteorologicalData createNewWeatherData(MeteorologicalDataRequestDto data) {
        MeteorologicalData newData = new MeteorologicalData();
        newData.setData(data.getData());
        newData.setTemperaturaMinima(data.getTemperaturaMinima());
        newData.setTemperaturaMaxima(data.getTemperaturaMaxima());
        newData.setTurno(data.getTurno());
        newData.setClima(data.getClima());
        newData.setPrecipitacao(data.getPrecipitacao());
        dataRepository.save(newData);
        return newData;
    }
}