package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.fixture.CidadeFixture;
import com.projeto_final.PrevisaoDoTempo.fixture.DadoMeteorologicoFixture;
import com.projeto_final.PrevisaoDoTempo.repositories.CityRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.MeteorologicalDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeteorologicalDataServiceTest {

    @InjectMocks
    MeteorologicalDataService dadoService;
    @Mock
    CityRepository cityRepository;
    @Mock
    MeteorologicalDataRepository dadoRepository;


    @Test
    @DisplayName("Deve cadastrar dados meteorologicos em determinada cidade com sucesso")
    void testCadastrarDadoComSucesso() {
        String cidade = "Campinas";
        MeteorologicalDataRequestDto dadoRequest = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto(cidade);
        City cityEntyt = CidadeFixture.gerarCidade(cidade);
        MeteorologicalData novoDado = DadoMeteorologicoFixture.gerarDadoMeteorologico(dadoRequest, cityEntyt);
        when(cityRepository.findByNome(cidade)).thenReturn(Optional.of(cityEntyt));
        when(dadoRepository.save(any(MeteorologicalData.class))).thenReturn(novoDado);
        dadoService.registerNewMeteorologicalData(dadoRequest);

        verify(cityRepository).findByNome(cidade);
        verify(dadoRepository).save(any(MeteorologicalData.class));
    }


    @Test
    @DisplayName("Deve testar lançamendo de excessão quando a cidade não for encontrada")
    void testCadastrarDadoSemSucesso_CidadeNaoEncontrada() {
        String cidade = "Campinas";
        MeteorologicalDataRequestDto dadoRequest = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto(cidade);
        when(cityRepository.findByNome(cidade)).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            dadoService.registerNewMeteorologicalData(dadoRequest);
        });
        assertEquals("City não encontrada após a verificação de existência.", exception.getMessage());
        verify(cityRepository).findByNome(cidade);
        verify(dadoRepository, never()).save(any(MeteorologicalData.class));
    }

    @Test
    @DisplayName("Deve deletar dado por id")
    void deveDeletarDadoPorId() {
        Long id = 1L;
        MeteorologicalData dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        when(dadoRepository.findById(id)).thenReturn(Optional.of(dado));
        dadoService.deletarMeteorologicalDataById(id);
        verify(dadoRepository).delete(dado);
    }

    @Test
    @DisplayName("Deve alterar dados meteorologicos encontrados por id")
    void deveAlterarDados() {
        Long id = 1L;
        MeteorologicalData dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        MeteorologicalDataRequestDto alteracoes = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto();
        alteracoes.setClima(Clima.NUBLADO);
        alteracoes.setTemperaturaMinima(13);
        alteracoes.setPrecipitacao(126);
        when(dadoRepository.findById(id)).thenReturn(Optional.of(dado));
        dadoService.chageMeteorologicalData(id,alteracoes);
        verify(dadoRepository, times(1)).save(dado);
        assertEquals(alteracoes.getClima(), dado.getClima());
        assertEquals(alteracoes.getTemperaturaMinima(), dado.getTemperaturaMinima());
        assertEquals(alteracoes.getPrecipitacao(), dado.getPrecipitacao());
    }
}