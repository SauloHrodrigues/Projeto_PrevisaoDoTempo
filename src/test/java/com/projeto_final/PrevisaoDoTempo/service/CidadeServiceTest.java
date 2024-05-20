package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeRequestDtoFixture;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CidadeServiceTest {

    @InjectMocks
    private CidadeService cidadeService;
    @Mock
    private CidadeRepository cidadeRepository;

    @Test
    void deveCadastrarNovaCidade(){
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDto();
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDto(dto);
        when(cidadeRepository.save(novaCidade)).thenReturn(novaCidade);
        Cidade resposta = cidadeService.cadastrarCidade(dto);
        verify(cidadeRepository).save(novaCidade);
        assertEquals(novaCidade, resposta);
    }

}