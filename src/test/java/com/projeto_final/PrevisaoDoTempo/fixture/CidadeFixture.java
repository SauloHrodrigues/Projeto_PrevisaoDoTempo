package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperCidade;
import com.projeto_final.PrevisaoDoTempo.mapper.MapperDadosMetearologicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CidadeFixture {

    public static Cidade gerarCidade(CidadeRequestDdo dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        if (dto.getDadosMeteorologicos() != null) {
            DadoMeteorologico dado = DadoMeteorologicoFixture.gerarDadoMeteorologico();
            cidade.getDadosMeteorologicos().add(dado);
        }
        return cidade;
    }

    public static Cidade gerarCidade(String nome) {
        CidadeRequestDdo dto = CidadeFixture.gerarCidadeRequestDto(nome);
        return CidadeFixture.gerarCidade(dto);
    }
    public static Cidade gerarCidade(String nome, DadoMeteorologico dadoMeteorologico) {
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.getDadosMeteorologicos().add(dadoMeteorologico);
        return cidade;
    }
    public static Cidade gerarCidade(String nome, List<DadoMeteorologico> dados) {
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setDadosMeteorologicos(dados);
        return cidade;
    }

    public static CidadeResponseDto cidadeResponseDto(Cidade cidade) {
        CidadeResponseDto responseDto = new CidadeResponseDto();
        responseDto.setId(1l);
        responseDto.setNome(cidade.getNome());
        if (!cidade.getDadosMeteorologicos().isEmpty()) {
            responseDto.setDadosMeteorologicos(cidade.getDadosMeteorologicos());
        }
        return responseDto;
    }
    public static CidadeRequestDdo gerarCidadeRequestDto(String nomeDaCidade) {
        CidadeRequestDdo cidadeDto = new CidadeRequestDdo();
        cidadeDto.setNome(nomeDaCidade);
        return cidadeDto;
    }
    public static CidadeRequestDdo gerarCidadeRequestDto(String nomeDaCidade, DadoMeteorologicoRequestDto dados) {
        CidadeRequestDdo cidadeDto = new CidadeRequestDdo();
        cidadeDto.setNome(nomeDaCidade);
       cidadeDto.setDadosMeteorologicos(dados);
        return cidadeDto;
    }
}
