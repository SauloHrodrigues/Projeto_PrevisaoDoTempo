package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.entities.MeteorologicalData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CityResponseDto {
    private Long id;
    private String nome;
    private List<MeteorologicalData> dadosMeteorologicos = new ArrayList<>();

}