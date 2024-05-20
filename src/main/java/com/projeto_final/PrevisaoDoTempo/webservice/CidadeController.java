package com.projeto_final.PrevisaoDoTempo.webservice;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/listar")
    public List<Cidade> lista(){
        return cidadeService.listar();
    }

    @PostMapping("/cadastrar")
    public void cadastraCidade(@RequestBody CidadeRequestDdo requisicao ){
        cidadeService.cadastrarCidade(requisicao);

    }
}
