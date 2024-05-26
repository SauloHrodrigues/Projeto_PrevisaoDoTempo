package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.mapper.Conversor;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CidadeService {
    @Autowired
    private final CidadeRepository cidadeRepository;

    @Autowired
    private final DadoMeteorologicoRepository dadoRepository;

    public void cadastrarCidade(CidadeRequestDdo cidadeRequestDdo) {
        Cidade novaCidade = Conversor.dtoToEntity(cidadeRequestDdo);
        novaCidade.setNome(cidadeRequestDdo.getNome());

        if(cidadeRequestDdo.getDadosMeteorologicos() != null){
            DadoMeteorologico novoDado = criarNovoDado(cidadeRequestDdo.getDadosMeteorologicos());
            dadoRepository.save(novoDado);
            novaCidade.getDadosMeteorologicos().add(novoDado);
        }
        cidadeRepository.save(novaCidade);
    }

    public List<Cidade> listar() {
        List<Cidade> response = cidadeRepository.findAll();
        return response;
    }

    public CidadeResponseDto retornaDadosMeteorologicoPorCidade(String nomeDaCidade) {
        Cidade cidadePesquisada = cidadeRepository.findByNome(nomeDaCidade).orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
        return Conversor.entityToResponseDto(cidadePesquisada);
    }

    public ResponseEntity deletarCidade(String nomeDaCidade){
        Cidade cidadePesquisada = cidadeRepository.findByNome(nomeDaCidade).get();//.orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
        if(cidadePesquisada != null){
            cidadeRepository.deleteById(cidadePesquisada.getId());
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

    private DadoMeteorologico criarNovoDado (DadoMeteorologicoRequestDto dados){
        DadoMeteorologico novoDado = new DadoMeteorologico();
        novoDado.setData(dados.getData());
        novoDado.setTemperaturaMinima(dados.getTemperaturaMinima());
        novoDado.setTemperaturaMaxima(dados.getTemperaturaMaxima());
        novoDado.setTurno(dados.getTurno());
        novoDado.setClima(dados.getClima());
        novoDado.setPrecipitacao(dados.getPrecipitacao());
        dadoRepository.save(novoDado);
        return novoDado;
    }

}