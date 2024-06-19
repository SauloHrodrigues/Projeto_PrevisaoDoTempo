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
    public List<City> listRegisteredCity(){
        return cityService.registeredCityList();
    }

    @GetMapping("/{cidade}/proximos_sete_dias")
    CityResponseDto listWeatherDataForTheNextSevenDays(@PathVariable("cidade") String city){
        return cityService.returnsWeathersDataForTheNextSevenDays(city);
    }


    @GetMapping("/{cidade}/hoje")
    CityResponseDto ListarDadosMeteorologicosDeHojePorCidade(@PathVariable("cidade") String city){
        return cityService.returnsTodayWeathersData(city);
    }


    @GetMapping("/{cidade}")
    public CityResponseDto pesquisarCidade(@PathVariable("cidade") String nameOfTheCity){
        return cityService.returnsWeathersDataByCity(nameOfTheCity);
    }

    @DeleteMapping("/{cidade}")
    public ResponseEntity deletarCidade(@PathVariable("cidade") String nameOfTheCity){
        return cityService.deletarCity(nameOfTheCity);
    }
}