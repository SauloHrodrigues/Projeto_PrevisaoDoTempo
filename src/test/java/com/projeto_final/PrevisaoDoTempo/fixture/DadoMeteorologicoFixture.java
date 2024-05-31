package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.DadoMeteorologicoResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DadoMeteorologicoFixture {
    static LocalDate hoje = LocalDate.now();
    public static List<DadoMeteorologico> gerarListaDadoMeteorologico(int quantidade){
        LocalDate data = hoje.minusDays(quantidade);
        List<DadoMeteorologico> dados = new ArrayList<>();
        for(int i=0;i<=quantidade*2;i++){
            dados.add(gerarDadoMeteorologico(data));
            data = data.plusDays(1);
        }
        return dados;
    }

    public static DadoMeteorologico gerarDadoMeteorologico(LocalDate data) {
        DadoMeteorologicoResponseDto response = gerarDadoMeteorologicoResponseDto();
        DadoMeteorologico dados = new DadoMeteorologico();
        dados.setData(data);
        dados.setTemperaturaMinima(response.getTemperaturaMinima());
        dados.setTemperaturaMaxima(response.getTemperaturaMaxima());
        dados.setTurno(response.getTurno());
        dados.setClima(response.getClima());
        dados.setPrecipitacao(response.getPrecipitacao());
        return dados;
    }
    public static DadoMeteorologico gerarDadoMeteorologico() {
        DadoMeteorologicoResponseDto response = gerarDadoMeteorologicoResponseDto();
        DadoMeteorologico dados = new DadoMeteorologico();
        dados.setData(response.getData());
        dados.setTemperaturaMinima(response.getTemperaturaMinima());
        dados.setTemperaturaMaxima(response.getTemperaturaMaxima());
        dados.setTurno(response.getTurno());
        dados.setClima(response.getClima());
        dados.setPrecipitacao(response.getPrecipitacao());
        return dados;
    }

    public static DadoMeteorologicoRequestDto gerarDadoMeteorologicoRequestDto(){
        DadoMeteorologicoResponseDto response = gerarDadoMeteorologicoResponseDto();
        DadoMeteorologicoRequestDto dados = new DadoMeteorologicoRequestDto();
        dados.setData(response.getData());
        dados.setTemperaturaMinima(response.getTemperaturaMinima());
        dados.setTemperaturaMaxima(response.getTemperaturaMaxima());
        dados.setTurno(response.getTurno());
        dados.setClima(response.getClima());
        dados.setPrecipitacao(response.getPrecipitacao());
        return dados;
    }

    public static DadoMeteorologicoResponseDto gerarDadoMeteorologicoResponseDto() {
        DadoMeteorologicoResponseDto dados = new DadoMeteorologicoResponseDto();
        dados.setId(1L);
        dados.setData(hoje);
        dados.setTemperaturaMinima(20);
        dados.setTemperaturaMaxima(35);
        dados.setTurno(Turno.NOITE);
        dados.setClima(Clima.ENSOLARADO);
        dados.setPrecipitacao(49);
        return dados;
    }

}