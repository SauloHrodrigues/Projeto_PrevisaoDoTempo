package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class CidadeResponseDto {
    private Long id;
    private String nome;
    private Map<LocalDate,DadoMeteorologico> dadosMeteorologicos= new HashMap<>();
}
