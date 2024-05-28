package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeRequestDtoFixture;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CidadeServiceTest {

    @InjectMocks
    private CidadeService cidadeService;
    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private DadoMeteorologicoRepository dadoRepository;

    @Test
    void deveCadastrarNovaCidadeSemDadosMeteorologicos(){
        // arrange
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDtoComDadosMeteorologico("Campinas");
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDtoSemDadosMeteorologicos(dto);
        when(cidadeRepository.save(novaCidade)).thenReturn(novaCidade);
//         Act

        CidadeResponseDto resposta = cidadeService.cadastrarCidade(dto);

//        Asserts
        verify(cidadeRepository).save(novaCidade);
        assertEquals(novaCidade, resposta);


    }

    @Test
    void deveBuscarCidadePorNome(){
        String cidade = "Valinhos";
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDto(cidade);
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDto(dto);
        when(cidadeRepository.findByNome(cidade)).thenReturn(Optional.of(novaCidade));
        CidadeResponseDto resposta = cidadeService.retornaDadosMeteorologicoPorCidade(cidade);
        verify(cidadeRepository).findByNome(cidade);
        assertEquals(resposta.getNome(), cidade);
    }

    @Test
    void deveCadastrarNovoDadoMeteorologico(){
        String cidade = "Campinas";

    }
}