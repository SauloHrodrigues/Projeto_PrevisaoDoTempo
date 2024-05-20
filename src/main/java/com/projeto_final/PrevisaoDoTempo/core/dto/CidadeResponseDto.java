package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CidadeResponseDto {
    private Long id;
    private String nome;
    private Map<LocalDate, DadoMeteorologico> dadosMeteorologicos;
}
