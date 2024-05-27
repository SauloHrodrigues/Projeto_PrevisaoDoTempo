package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class DadoMeteorologicoRequestDto {

    @Getter
    @Setter
    private String nomeDaCidade;
    @Getter
    @Setter
    private LocalDate data;
    @Getter
    @Setter
    private Integer temperaturaMinima;
    @Getter
    @Setter
    private Integer temperaturaMaxima;
    @Getter
    @Setter
    private Turno turno;
    @Getter
    @Setter
    private Clima clima;
    @Getter
    @Setter
    private Integer precipitacao;

}