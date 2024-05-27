package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.service.DadosMeteorologicosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/previsao/clima/dados")
public class DadoMeteorologicoController {

   private final DadosMeteorologicosService service;

//    cadastrar dados
    @PostMapping("/")
    void cadastrarNovoDadoMeteorologico(@RequestBody DadoMeteorologicoRequestDto novosDados){
        service.cadastrarDado(novosDados);
    }
//    Apagar dados
    @DeleteMapping("/{id}")
    void deletarDadoMeteorologico(@PathVariable("id") Long id){
        service.deletarDadoPorId(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<DadoMeteorologicoResponseDto> alterarDado(@PathVariable("id") Long id, @RequestBody DadoMeteorologicoResponseDto dto){
        service.alterarDado(id,dto);

        return null;
    }
}
