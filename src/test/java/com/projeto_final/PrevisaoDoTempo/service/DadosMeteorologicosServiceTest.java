package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeRequestDtoFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.DadoMeteorologicoFixture;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperCidade;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DadosMeteorologicosServiceTest {

    @InjectMocks
    DadosMeteorologicosService dadoService;
    @Mock
    CidadeRepository cidadeRepository;
    @Mock
    DadoMeteorologicoRepository dadoRepository;


    @Test
    @DisplayName("Deve cadastrar dados meteorologicos em determinada cidade com sucesso")
    void testCadastrarDadoComSucesso() {
        String cidade = "Campinas";
        DadoMeteorologicoRequestDto dadoRequest = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto(cidade);
        Cidade cidadeEntyt = CidadeFixture.gerarCidade(cidade);
        DadoMeteorologico novoDado = DadoMeteorologicoFixture.gerarDadoMeteorologico(dadoRequest, cidadeEntyt);
        when(cidadeRepository.findByNome(cidade)).thenReturn(Optional.of(cidadeEntyt));
        when(dadoRepository.save(any(DadoMeteorologico.class))).thenReturn(novoDado);
        dadoService.cadastrarDado(dadoRequest);

        verify(cidadeRepository).findByNome(cidade);
        verify(dadoRepository).save(any(DadoMeteorologico.class));
    }


    @Test
    @DisplayName("Deve testar lançamendo de excessão quando a cidade não for encontrada")
    void testCadastrarDadoSemSucesso_CidadeNaoEncontrada() {
        String cidade = "Campinas";
        DadoMeteorologicoRequestDto dadoRequest = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto(cidade);
        when(cidadeRepository.findByNome(cidade)).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            dadoService.cadastrarDado(dadoRequest);
        });
        assertEquals("Cidade não encontrada após a verificação de existência.", exception.getMessage());
        verify(cidadeRepository).findByNome(cidade);
        verify(dadoRepository, never()).save(any(DadoMeteorologico.class));
    }

    @Test
    @DisplayName("Deve deletar dado por id")
    void deveDeletarDadoPorId() {
        Long id = 1L;
        DadoMeteorologico dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        when(dadoRepository.findById(id)).thenReturn(Optional.of(dado));
        dadoService.deletarDadoPorId(id);
        verify(dadoRepository).delete(dado);
    }

    @Test
    @DisplayName("Deve alterar dados meteorologicos encontrados por id")
    void deveAlterarDados() {
        Long id = 1L;
        DadoMeteorologico dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        DadoMeteorologicoRequestDto alteracoes = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto();
        alteracoes.setClima(Clima.NUBLADO);
        alteracoes.setTemperaturaMinima(13);
        alteracoes.setPrecipitacao(126);
        when(dadoRepository.findById(id)).thenReturn(Optional.of(dado));
        dadoService.alterarDado(id,alteracoes);
        verify(dadoRepository, times(1)).save(dado);
        assertEquals(alteracoes.getClima(), dado.getClima());
        assertEquals(alteracoes.getTemperaturaMinima(), dado.getTemperaturaMinima());
        assertEquals(alteracoes.getPrecipitacao(), dado.getPrecipitacao());
    }
}