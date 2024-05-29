package com.projeto_final.PrevisaoDoTempo.service;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperCidade;
import com.projeto_final.PrevisaoDoTempo.repositories.CidadeRepository;
import com.projeto_final.PrevisaoDoTempo.repositories.DadoMeteorologicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public CidadeResponseDto cadastrarCidade(CidadeRequestDdo cidadeRequestDdo) {
        if(cidadeRequestDdo.getNome()==null){
            throw  new IllegalArgumentException("Falta o nome da cidade");
        }

        cidadeRepository.findByNome(cidadeRequestDdo.getNome()).ifPresent((cidade) -> {
            throw new IllegalArgumentException("Cidade já cadastrada.");
        });

            Cidade novaCidade = MapperCidade.dtoToEntity(cidadeRequestDdo);
            novaCidade.setNome(cidadeRequestDdo.getNome());

            if (cidadeRequestDdo.getDadosMeteorologicos() != null) {
                DadoMeteorologico novoDado = criarNovoDado(cidadeRequestDdo.getDadosMeteorologicos());
                novoDado.setCidade(novaCidade);
//            dadoRepository.save(novoDado);
                novaCidade.getDadosMeteorologicos().add(novoDado);
            }
            cidadeRepository.save(novaCidade);
            return MapperCidade.entityToResponseDto(novaCidade);
    }


    public List<Cidade> listar() {
        List<Cidade> response = cidadeRepository.findAll();
        return response;
    }

    public CidadeResponseDto retornarDadosProximosSeteDias(String nomeDaCidade) {
        CidadeResponseDto cidadeBuscada = retornaDadosMeteorologicoPorCidade(nomeDaCidade);
        List<DadoMeteorologico> todosDados = cidadeBuscada.getDadosMeteorologicos();
        List<DadoMeteorologico> dadosSelecionados = new ArrayList<>();
        LocalDate data = LocalDate.now();
        LocalDate dataFutura = LocalDate.now().plusDays(7);

        for (DadoMeteorologico dado : todosDados) {
            if (dado.getData().isAfter(data) && dado.getData().isBefore(dataFutura)) {
                dadosSelecionados.add(dado);
            }
        }
        CidadeResponseDto response = new CidadeResponseDto();
        response.setId(cidadeBuscada.getId());
        response.setNome(cidadeBuscada.getNome());
        response.setDadosMeteorologicos(dadosSelecionados);
        return response;

    }

    public CidadeResponseDto retornaDadosMeteorologicoPorCidade(String nomeDaCidade) {
        Cidade cidadePesquisada = cidadeRepository.findByNome(nomeDaCidade).orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
        return MapperCidade.entityToResponseDto(cidadePesquisada);
    }

    public ResponseEntity deletarCidade(String nomeDaCidade) {
        Cidade cidadePesquisada = cidadeRepository.findByNome(nomeDaCidade).orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada"));
        cidadeRepository.deleteById(cidadePesquisada.getId());
        return ResponseEntity.ok().build();
    }

    public CidadeResponseDto retornarDadosDeHoje(String nomeDaCidade) {
        CidadeResponseDto cidadeBuscada = retornaDadosMeteorologicoPorCidade(nomeDaCidade);
        List<DadoMeteorologico> dadoMeteorologicos = cidadeBuscada.getDadosMeteorologicos();
        List<DadoMeteorologico> dadosDeHoje = new ArrayList<>();

        for (DadoMeteorologico dado : dadoMeteorologicos) {
            if (dado.getData().equals(LocalDate.now())) {
                dadosDeHoje.add(dado);
            }
        }
        CidadeResponseDto response = new CidadeResponseDto();
        response.setId(cidadeBuscada.getId());
        response.setNome(cidadeBuscada.getNome());
        response.setDadosMeteorologicos(dadosDeHoje);
        return response;
    }

    // remover da lista preimeiro

    private DadoMeteorologico criarNovoDado(DadoMeteorologicoRequestDto dados) {
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