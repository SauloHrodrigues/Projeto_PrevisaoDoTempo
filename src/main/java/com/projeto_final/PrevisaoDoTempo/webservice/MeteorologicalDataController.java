package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataResponseDto;
import com.projeto_final.PrevisaoDoTempo.service.MeteorologicalDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/previsao/clima/dados")
public class MeteorologicalDataController {

   private final MeteorologicalDataService service;

    @PostMapping("/")
    public ResponseEntity<CityResponseDto> registerNewMeteorologicalData(@RequestBody MeteorologicalDataRequestDto newData){
        CityResponseDto city = service.registerNewMeteorologicalData(newData);
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarMeteorologicalDataByID(@PathVariable("id") Long id){
        service.deletarMeteorologicalDataById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeteorologicalDataResponseDto> chageMeteorologicalDataById(@PathVariable("id") Long id, @RequestBody MeteorologicalDataResponseDto dto){
        service.chageMeteorologicalData(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}