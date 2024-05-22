package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.service.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @GetMapping("/listar")
    public List<Cidade> lista(){
        return cidadeService.listar();
    }

    @PostMapping("/cadastrar")
    public void cadastraCidade(@RequestBody CidadeRequestDdo requisicao ){
        cidadeService.cadastrarCidade(requisicao);

    }

    @GetMapping("/{cidade}")
    public CidadeResponseDto pesquisarCidade(@PathVariable("cidade") String nomeCidade){
        System.out.println("Nome Cidade = "+ nomeCidade);
        return cidadeService.retornaDadosMeteorologicoPorCidade(nomeCidade);
    }
}
