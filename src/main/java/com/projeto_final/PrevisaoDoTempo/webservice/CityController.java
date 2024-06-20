package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/previsao/clima/cidade")
public class CityController {

    private final CityService cityService;

    @PostMapping("/")
    public ResponseEntity<CityResponseDto> registerNewCity(@RequestBody CityRequestDdo cityRequestDdo ){
        CityResponseDto responseDto = cityService.registerNewCity(cityRequestDdo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> listRegisteredCity(){
        List<City> cityList = cityService.registeredCityList();
        return ResponseEntity.status(HttpStatus.OK).body(cityList);
    }

    @GetMapping("/{cidade}/proximos_sete_dias")
    public ResponseEntity<CityResponseDto> listWeatherDataForTheNextSevenDays(@PathVariable("cidade") String city){
        CityResponseDto cityResponseDto= cityService.returnsWeathersDataForTheNextSevenDays(city);
        return ResponseEntity.status(HttpStatus.OK).body(cityResponseDto);
    }


    @GetMapping("/{cidade}/hoje")
    public ResponseEntity<CityResponseDto> ListarDadosMeteorologicosDeHojePorCidade(@PathVariable("cidade") String city){
        CityResponseDto cityResponseDto= cityService.returnsTodayWeathersData(city);
        return ResponseEntity.status(HttpStatus.OK).body(cityResponseDto);
    }


    @GetMapping("/{cidade}")
    public ResponseEntity<CityResponseDto> searchCity(@PathVariable("cidade") String nameOfTheCity){
        CityResponseDto cityResponseDto= cityService.returnsWeathersDataByCity(nameOfTheCity);
        return ResponseEntity.status(HttpStatus.OK).body(cityResponseDto);
    }

    @DeleteMapping("/{cidade}")
    public ResponseEntity deletarCity(@PathVariable("cidade") String nameOfTheCity){
        cityService.deletarCity(nameOfTheCity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}