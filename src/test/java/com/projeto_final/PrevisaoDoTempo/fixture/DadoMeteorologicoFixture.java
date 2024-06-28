package com.projeto_final.PrevisaoDoTempo.fixture;

import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataRequestDto;
import com.projeto_final.PrevisaoDoTempo.core.dto.MeteorologicalDataResponseDto;
import com.projeto_final.PrevisaoDoTempo.core.entities.City;
import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DadoMeteorologicoFixture {
    static LocalDate hoje = LocalDate.now();
    public static List<MeteorologicalData> gerarListaDadoMeteorologico(int quantidade){
        LocalDate data = hoje.minusDays(quantidade);
        List<MeteorologicalData> dados = new ArrayList<>();
        for(int i=0;i<=quantidade*2;i++){
            dados.add(gerarDadoMeteorologico(data));
            data = data.plusDays(1);
        }
        return dados;
    }

    public static MeteorologicalData gerarDadoMeteorologico(LocalDate data) {
        MeteorologicalDataResponseDto response = gerarDadoMeteorologicoResponseDto();
        MeteorologicalData dados = new MeteorologicalData();
        dados.setData(data);
        dados.setTemperaturaMinima(response.getTemperaturaMinima());
        dados.setTemperaturaMaxima(response.getTemperaturaMaxima());
        dados.setTurno(response.getTurno());
        dados.setClima(response.getClima());
        dados.setPrecipitacao(response.getPrecipitacao());
        return dados;
    }
    public static MeteorologicalData gerarDadoMeteorologico() {
        MeteorologicalDataResponseDto response = gerarDadoMeteorologicoResponseDto();
        MeteorologicalData dados = new MeteorologicalData();
        dados.setData(response.getData());
        dados.setTemperaturaMinima(response.getTemperaturaMinima());
        dados.setTemperaturaMaxima(response.getTemperaturaMaxima());
        dados.setTurno(response.getTurno());
        dados.setClima(response.getClima());
        dados.setPrecipitacao(response.getPrecipitacao());
        return dados;
    }
    public static MeteorologicalData gerarDadoMeteorologico(MeteorologicalDataRequestDto requestDto, City city) {
        MeteorologicalData dados = new MeteorologicalData();
        dados.setData(requestDto.getData());
        dados.setTemperaturaMinima(requestDto.getTemperaturaMinima());
        dados.setTemperaturaMaxima(requestDto.getTemperaturaMaxima());
        dados.setTurno(requestDto.getTurno());
        dados.setClima(requestDto.getClima());
        dados.setPrecipitacao(requestDto.getPrecipitacao());
        dados.setCidade(city);
        return dados;
    }


    public static MeteorologicalDataResponseDto gerarDadoMeteorologicoResponseDto() {
        MeteorologicalDataRequestDto requestDto = DadoMeteorologicoFixture.gerarDadoMeteorologicoRequestDto();
        MeteorologicalDataResponseDto dados = new MeteorologicalDataResponseDto();
        dados.setId(1L);
        return dados;
    }

    public static MeteorologicalDataRequestDto gerarDadoMeteorologicoRequestDto(String cidade){
        MeteorologicalDataRequestDto dados = gerarDadoMeteorologicoRequestDto();
        dados.setNomeDaCidade(cidade);
        return dados;
    }
    public static MeteorologicalDataRequestDto gerarDadoMeteorologicoRequestDto() {
        MeteorologicalDataRequestDto dados = new MeteorologicalDataRequestDto();
        dados.setNomeDaCidade("São Paulo");
        dados.setData(hoje);
        dados.setTemperaturaMinima(20);
        dados.setTemperaturaMaxima(35);
        dados.setTurno(Turno.NOITE);
        dados.setClima(Clima.ENSOLARADO);
        dados.setPrecipitacao(49);
        return dados;
    }
}