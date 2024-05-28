package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/previsao/clima/cidade")
public class CidadeController {

    private final CidadeService cidadeService;

    @PostMapping("/")
    public ResponseEntity<CidadeResponseDto> cadastraCidade(@RequestBody CidadeRequestDdo requisicao ){
        CidadeResponseDto responseDto = cidadeService.cadastrarCidade(requisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/")
    public List<Cidade> lista(){
        return cidadeService.listar();
    }

    @GetMapping("/{cidade}/proximos_sete_dias")
    CidadeResponseDto  ultimos07Dias(@PathVariable("cidade") String cidade){
        return cidadeService.retornarDadosProximosSeteDias(cidade);
    }

    @GetMapping("/{cidade}/hoje")
    CidadeResponseDto  dadosDeHoje(@PathVariable("cidade") String cidade){
        return cidadeService.retornarDadosDeHoje(cidade);
    }


    @GetMapping("/{cidade}")
    public CidadeResponseDto pesquisarCidade(@PathVariable("cidade") String nomeCidade){
        return cidadeService.retornaDadosMeteorologicoPorCidade(nomeCidade);
    }

    @DeleteMapping("/{cidade}")
    public ResponseEntity deletarCidade(@PathVariable("cidade") String nomeCidade){
        return cidadeService.deletarCidade(nomeCidade);
    }
}