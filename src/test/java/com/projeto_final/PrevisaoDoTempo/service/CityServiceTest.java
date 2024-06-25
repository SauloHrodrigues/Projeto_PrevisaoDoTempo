package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CityRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CityResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @InjectMocks
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;

    @Mock
    private MeteorologicalDataRepository dadoRepository;


    @Test
    @DisplayName("Deve cadastrar uma cidade com dados meteorológicos")
    public void deveCadastrarCidadeComDadosMeteorologicos() {
        // arrange
        MeteorologicalDataRequestDto dados = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto();
        CityRequestDdo dto = CidadeFixture.gerarCidadeRequestDto("Campinas",dados);
        City novaCity = CidadeFixture.gerarCidade(dto);
        when(cityRepository.save(any(City.class))).thenReturn(novaCity);
        when(dadoRepository.save(any(MeteorologicalData.class))).thenReturn(any(MeteorologicalData.class));
//      act
        CityResponseDto responseDto = cityService.registerNewCity(dto);
//        assert
        verify(cityRepository, times(1)).save(any(City.class));
        verify(dadoRepository, times(1)).save(any(MeteorologicalData.class));
        assertTrue(!responseDto.getDadosMeteorologicos().isEmpty());
    }
    @Test
    @DisplayName("Deve cadastrar uma cidade sem meteorológicos")
    public void deveCadastrarCidadeSemDadosMeteorologicos() {
        // arrange
        CityRequestDdo dto = CidadeFixture.gerarCidadeRequestDto("Campinas");
        City novaCity = CidadeFixture.gerarCidade(dto);
        when(cityRepository.save(any(City.class))).thenReturn(novaCity);
//      act
        CityResponseDto responseDto = cityService.registerNewCity(dto);
//        assert
        verify(cityRepository, times(1)).save(any(City.class));
        assertTrue(responseDto.getDadosMeteorologicos().isEmpty());
    }
    @Test
    @DisplayName("Deve lançar excessão de cidade já Existente")
    public void deveLancarExceptionDeCidadeJaExistente() {
        // arrange
        String cidade = "Valinhos";
        CityRequestDdo dto = CidadeFixture.gerarCidadeRequestDto(cidade);
        City novaCity = CidadeFixture.gerarCidade(dto);
        when(cityRepository.save(any(City.class))).thenThrow(new DataIntegrityViolationException("erro"));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            cityService.registerNewCity(dto);
        });
        assertEquals("City já cadastrada.",illegalArgumentException.getMessage());
    }
    @Test
    @DisplayName("Deve retornar uma lista de cidades")
    public void deveRetornarUmaListaDeCidades() {
        // arrange
        CityRequestDdo dto = CidadeFixture.gerarCidadeRequestDto ("Campinas");
        City novaCity = CidadeFixture.gerarCidade(dto);
        List<City> cityList = new ArrayList<>();
        cityList.add(novaCity);
        cityList.add(novaCity);
        when(cityRepository.findAll()).thenReturn(cityList);
       List<City> resposta = cityService.registeredCityList();
        assertEquals(resposta.get(0), novaCity);
        assertTrue(resposta.size() == 2);
    }

    @Test
    @DisplayName("Deve retornar uma lista de dados de determinada cidades dos próximos sete dias")
    public void deveRetornarDadosProximosSeteDias() {
        // arrange
        String cidade = "Campinas";
        List<MeteorologicalData> dados = DadoMeteorologicoFixture.gerarListaDadoMeteorologico(7);
        City cityComListaDeDados = CidadeFixture.gerarCidade(cidade,dados);
        when(cityRepository.findByNome(cidade)).thenReturn(Optional.of(cityComListaDeDados)); // mocando o retorno de findByNome
        CityResponseDto retorno= cityService.returnsWeathersDataForTheNextSevenDays(cidade);
        assertEquals(7,retorno.getDadosMeteorologicos().size());
    }

    @Test
    @DisplayName("Deve retornar os dados meteorologicos de determinada cidade")
    public void deveRetornaDadosMeteorologicoPorCidade(){
        String cidade = "Campinas";
        MeteorologicalData dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        City cityEntytie = CidadeFixture.gerarCidade(cidade,dado);
        when(cityRepository.findByNome(cidade)).thenReturn(Optional.of(cityEntytie)); // mocando o retorno de findByNom
        CityResponseDto retorno= cityService.returnsWeathersDataByCity(cidade);
        verify(cityRepository, times(1)).findByNome(cidade);
        assertEquals(retorno.getNome(),cidade);
    }

    @Test
    @DisplayName("Deve deletar cidade Existente")
    public void deletarCidade(){
        String nomeCidade = "Campinas";
        City city = CidadeFixture.gerarCidade(nomeCidade);
        when(cityRepository.findByNome(nomeCidade)).thenReturn(Optional.of(city));
        verify(cityRepository, times(1)).deleteById(city.getId());
    }

    @Test
    @DisplayName("Deve retornar dados meteorologicos de hoje")
    public void deveRretornarDadosDeHoje(){
        LocalDate hoje = LocalDate.now();
        String nomeDaCidade= "São Paulo";
        List<MeteorologicalData> dados = DadoMeteorologicoFixture.gerarListaDadoMeteorologico(1);
        City city = CidadeFixture.gerarCidade(nomeDaCidade,dados);
        when(cityRepository.findByNome(nomeDaCidade)).thenReturn(Optional.of(city));
        CityResponseDto resposta = cityService.returnsTodayWeathersData(nomeDaCidade);
        assertEquals(nomeDaCidade,resposta.getNome());
        assertEquals(LocalDate.now(),resposta.getDadosMeteorologicos().get(0).getData());
    }




}