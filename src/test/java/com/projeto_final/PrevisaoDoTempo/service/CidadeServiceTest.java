package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class CidadeServiceTest {

    @Autowired
    CidadeService serviceTest;



    @Test
    public void deveLancarExcecaoAoCadastrarCidadeJaExistente(){
        CidadeRequestDdo cidade01 = new CidadeRequestDdo();
        CidadeRequestDdo cidade02 = new CidadeRequestDdo();
        cidade01.setNome("Campinas");
        cidade02.setNome("Campinas");
        serviceTest.cadastrarCidade(cidade01);
        serviceTest.cadastrarCidade(cidade02);
        Assertions.assertEquals("Cidade já cadastrada.", ******************** );
    }

}
