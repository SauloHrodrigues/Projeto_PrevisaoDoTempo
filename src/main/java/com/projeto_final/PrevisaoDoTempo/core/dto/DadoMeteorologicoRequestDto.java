package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;


import java.time.LocalDate;

public class DadoMeteorologicoRequestDto {
    private LocalDate data;
    private Integer temperaturaMinima;
    private Integer temperaturaMaxima;
    private Turno turno;
    private Clima clima;
    private Integer precipitacao;
}
