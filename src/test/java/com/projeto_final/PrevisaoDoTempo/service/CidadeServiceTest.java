package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
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
        DadoMeteorologicoRequestDto dados = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto();
        CidadeRequestDdo dto = CidadeFixture.gerarCidadeRequestDto("Campinas",dados);
        Cidade novaCidade = CidadeFixture.gerarCidade(dto);
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
        CidadeRequestDdo dto = CidadeFixture.gerarCidadeRequestDto("Campinas");
        Cidade novaCidade = CidadeFixture.gerarCidade(dto);
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(novaCidade);
//      act
        CidadeResponseDto responseDto = cidadeService.cadastrarCidade(dto);
//        assert
        verify(cidadeRepository, times(1)).save(any(Cidade.class));
        assertTrue(responseDto.getDadosMeteorologicos().isEmpty());
    }
    @Test
    @DisplayName("Deve lançar excessão de cidade já Existente")
    public void deveLancarExceptionDeCidadeJaExistente() {
        // arrange
        String cidade = "Valinhos";
        CidadeRequestDdo dto = CidadeFixture.gerarCidadeRequestDto(cidade);
        Cidade novaCidade = CidadeFixture.gerarCidade(dto);
        when(cidadeRepository.save(any(Cidade.class))).thenThrow(new DataIntegrityViolationException("erro"));
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            cidadeService.cadastrarCidade(dto);
        });
        assertEquals("Cidade já cadastrada.",illegalArgumentException.getMessage());
    }
    @Test
    @DisplayName("Deve retornar uma lista de cidades")
    public void deveRetornarUmaListaDeCidades() {
        // arrange
        CidadeRequestDdo dto = CidadeFixture.gerarCidadeRequestDto ("Campinas");
        Cidade novaCidade = CidadeFixture.gerarCidade(dto);
        List<Cidade> cidadeList = new ArrayList<>();
        cidadeList.add(novaCidade);
        cidadeList.add(novaCidade);
        when(cidadeRepository.findAll()).thenReturn(cidadeList);
       List<Cidade> resposta = cidadeService.listar();
        assertEquals(resposta.get(0),novaCidade);
        assertTrue(resposta.size() == 2);
    }

    @Test
    @DisplayName("Deve retornar uma lista de dados de determinada cidades dos próximos sete dias")
    public void deveRetornarDadosProximosSeteDias() {
        // arrange
        String cidade = "Campinas";
        List<DadoMeteorologico> dados = DadoMeteorologicoFixture.gerarListaDadoMeteorologico(7);
        Cidade cidadeComListaDeDados = CidadeFixture.gerarCidade(cidade,dados);
        when(cidadeRepository.findByNome(cidade)).thenReturn(Optional.of(cidadeComListaDeDados)); // mocando o retorno de findByNome
        CidadeResponseDto retorno= cidadeService.retornarDadosProximosSeteDias(cidade);
        assertEquals(7,retorno.getDadosMeteorologicos().size());
    }

    @Test
    @DisplayName("Deve retornar os dados meteorologicos de determinada cidade")
    public void deveRetornaDadosMeteorologicoPorCidade(){
        String cidade = "Campinas";
        DadoMeteorologico dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        Cidade cidadeEntytie = CidadeFixture.gerarCidade(cidade,dado);
        when(cidadeRepository.findByNome(cidade)).thenReturn(Optional.of(cidadeEntytie)); // mocando o retorno de findByNom
        CidadeResponseDto retorno= cidadeService.retornaDadosMeteorologicoPorCidade(cidade);
        verify(cidadeRepository, times(1)).findByNome(cidade);
        assertEquals(retorno.getNome(),cidade);
    }

    @Test
    @DisplayName("Deve deletar cidade Existente")
    public void deletarCidade(){
        String nomeCidade = "Campinas";
        Cidade cidade = CidadeFixture.gerarCidade(nomeCidade);
        when(cidadeRepository.findByNome(nomeCidade)).thenReturn(Optional.of(cidade));
        ResponseEntity resposta = cidadeService.deletarCidade(nomeCidade);
        verify(cidadeRepository, times(1)).deleteById(cidade.getId());
        assertTrue(resposta.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Deve retornar dados meteorologicos de hoje")
    public void deveRretornarDadosDeHoje(){
        LocalDate hoje = LocalDate.now();
        String nomeDaCidade= "São Paulo";
        List<DadoMeteorologico> dados = DadoMeteorologicoFixture.gerarListaDadoMeteorologico(1);
        Cidade cidade = CidadeFixture.gerarCidade(nomeDaCidade,dados);
        when(cidadeRepository.findByNome(nomeDaCidade)).thenReturn(Optional.of(cidade));
        CidadeResponseDto resposta = cidadeService.retornarDadosDeHoje(nomeDaCidade);
        assertEquals(nomeDaCidade,resposta.getNome());
        assertEquals(LocalDate.now(),resposta.getDadosMeteorologicos().get(0).getData());
    }




}