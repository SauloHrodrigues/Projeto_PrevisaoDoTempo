package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeRequestDtoFixture;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperCidade;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CidadeServiceTest {

    @InjectMocks
    private CidadeService cidadeService;
    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private DadoMeteorologicoRepository dadoRepository;


    @Test
    @DisplayName("Deve cadastrar uma cidade com dados meteorológicos")
    public void deveCadastrarCidadeComDadosMeteorologicos() {
        // arrange
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDtoComDadosMeteorologico("Campinas");
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDto(dto);
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(novaCidade);
        when(dadoRepository.save(any(DadoMeteorologico.class))).thenReturn(any(DadoMeteorologico.class));
//      act
        CidadeResponseDto responseDto = cidadeService.cadastrarCidade(dto);
//        assert
        verify(cidadeRepository, times(1)).save(any(Cidade.class));
        verify(dadoRepository, times(1)).save(any(DadoMeteorologico.class));
        assertTrue(!responseDto.getDadosMeteorologicos().isEmpty());
    }
    @Test
    @DisplayName("Deve cadastrar uma cidade sem meteorológicos")
    public void deveCadastrarCidadeSemDadosMeteorologicos() {
        // arrange
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDto("Campinas");
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDto(dto);
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(novaCidade);
//      act
        CidadeResponseDto responseDto = cidadeService.cadastrarCidade(dto);
//        assert
        verify(cidadeRepository, times(1)).save(any(Cidade.class));
        assertTrue(responseDto.getDadosMeteorologicos().isEmpty());
    }
    @Test
    @DisplayName("Deve lançar excessão de cidade já Existente")
    public void deveLncarExceptionDeCidadeJaExistente() {
        // arrange
        String cidade = "Valinhos";
        CidadeRequestDdo dto = CidadeRequestDtoFixture.gerarCidadeRequestDto(cidade);
        Cidade novaCidade = CidadeFixture.gerarCidadePorCidadeRequestDto(dto);
        when(cidadeRepository.save(any(Cidade.class))).thenThrow(new DataIntegrityViolationException("erro"));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            cidadeService.cadastrarCidade(dto);
        });
        assertEquals("Cidade já cadastrada.",illegalArgumentException.getMessage());
    }

}