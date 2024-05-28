package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;

import java.time.LocalDate;

public class DadoMeteorologicoFixture {
    public static DadoMeteorologico gerarDadoMeteorologico() {
        DadoMeteorologico dados = new DadoMeteorologico();
        dados.setData(LocalDate.now());
        dados.setTemperaturaMinima(20);
        dados.setTemperaturaMaxima(35);
        dados.setTurno(Turno.NOITE);
        dados.setClima(Clima.ENSOLARADO);
        dados.setPrecipitacao(49);
        return dados;
    }
}