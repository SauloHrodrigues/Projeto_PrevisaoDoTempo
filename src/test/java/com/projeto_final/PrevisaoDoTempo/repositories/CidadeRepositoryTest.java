package com.projeto_final.PrevisaoDoTempo.repositories;

import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class CidadeRepositoryTest {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Test
    public void saveCidadeTeste(){
        Cidade cidade = new Cidade();
        cidade.setNome("Campina");
        cidadeRepository.save(cidade);
        Assertions.assertTrue(cidade.getId() > 0);
    }
}
