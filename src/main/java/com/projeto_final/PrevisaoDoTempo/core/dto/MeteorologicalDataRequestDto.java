package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeteorologicalDataRequestDto {

    @NotBlank
    private String nomeDaCidade;

    @NotBlank
    private LocalDate data;

    @NotBlank
    private Integer temperaturaMinima;

    @NotBlank
    private Integer temperaturaMaxima;

    @NotBlank
    private Turno turno;

    @NotBlank
    private Clima clima;
    @NotBlank
    private Integer precipitacao;

}