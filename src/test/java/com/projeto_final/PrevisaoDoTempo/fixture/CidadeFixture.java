package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;

import java.time.LocalDate;

public class CidadeFixture {
    public static Cidade gerarCidadePorCidadeRequestDto(CidadeRequestDdo dto){
        DadoMeteorologico dadoMeteorologico = DadoMeteorologicoFixture.gerarDadoMeteorologico();
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        cidade.getDadosMeteorologicos().add(dadoMeteorologico);
        return cidade;
    }
    public static Cidade gerarCidadePorCidadeRequestDtoSemDadosMeteorologicos(CidadeRequestDdo dto){
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        return cidade;
    }
}
