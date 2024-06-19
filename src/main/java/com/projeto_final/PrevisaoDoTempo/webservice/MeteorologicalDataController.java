package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataResponseDto;
import com.projeto_final.PrevisaoDoTempo.service.MeteorologicalDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/previsao/clima/dados")
public class MeteorologicalDataController {

   private final MeteorologicalDataService service;

//    cadastrar dados
    @PostMapping("/")
    void registerNewMeteorologicalData(@RequestBody MeteorologicalDataRequestDto newData){
        service.registerNewMeteorologicalData(newData);
    }
//    Apagar dados
    @DeleteMapping("/{id}")
    void deletarMeteorologicalDataByID(@PathVariable("id") Long id){
        service.deletarMeteorologicalDataById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<MeteorologicalDataResponseDto> chageMeteorologicalDataById(@PathVariable("id") Long id, @RequestBody MeteorologicalDataResponseDto dto){
        service.chageMeteorologicalData(id,dto);

        return null;
    }
}
