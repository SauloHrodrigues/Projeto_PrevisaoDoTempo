package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.CidadeRequestDdo;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;

import java.time.LocalDate;

public class CidadeRequestDtoFixture {

    public static CidadeRequestDdo gerarCidadeRequestDto(String nomeDaCidade){
        DadoMeteorologico dadoMeteorologico = new DadoMeteorologico(
                null,
                LocalDate.now(),
                20,
                10,
                Turno.NOITE,
                Clima.NUBLADO,
                50
                );


        return new CidadeRequestDdo(nomeDaCidade, dadoMeteorologico);

    }
}
