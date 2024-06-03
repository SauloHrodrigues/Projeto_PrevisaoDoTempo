package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperDadosMetearologicos;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class DadosMeteorologicosService {

    @Autowired
    private final CidadeRepository cidadeRepository;
    @Autowired
    private final DadoMeteorologicoRepository dadoRepository;


    //    cadastrar dados
    public void cadastrarDado(DadoMeteorologicoRequestDto dado) {
        Cidade cidade = cidadeRepository.findByNome(dado.getNomeDaCidade())
                .orElseThrow(() -> new NoSuchElementException("Cidade não encontrada após a verificação de existência."));

        DadoMeteorologico novoDado = MapperDadosMetearologicos.dtoToEntity(dado, cidade);
        dadoRepository.save(novoDado);
    }

    //    Apagar dados
    public void deletarDadoPorId(Long id) {
        DadoMeteorologico dadoMeteorologico = dadoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dado não encontrado"));
        dadoRepository.delete(dadoMeteorologico);
    }

    public void alterarDado(Long id, DadoMeteorologicoRequestDto dto) {
        DadoMeteorologico dadoMeteorologico = dadoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dado não encontrado"));
        if (dto.getData() != null) {
            dadoMeteorologico.setData(dto.getData());
        }
        if (dto.getTemperaturaMinima() != null) {
            dadoMeteorologico.setTemperaturaMinima(dto.getTemperaturaMinima());
        }
        if (dto.getTemperaturaMaxima() != null) {
            dadoMeteorologico.setTemperaturaMaxima(dto.getTemperaturaMaxima());
        }
        if (dto.getTurno() != null) {
            dadoMeteorologico.setTurno(dto.getTurno());
        }
        if (dto.getClima() != null) {
            dadoMeteorologico.setClima(dto.getClima());
        }
        if (dto.getPrecipitacao() != null) {
            dadoMeteorologico.setPrecipitacao(dto.getPrecipitacao());
        }
        dadoRepository.save(dadoMeteorologico);
    }
}