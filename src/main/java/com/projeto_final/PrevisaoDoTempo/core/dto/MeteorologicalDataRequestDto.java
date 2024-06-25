package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    private String nomeDaCidade;

    @NotNull
    private LocalDate data;

    @NotNull
    private Integer temperaturaMinima;

    @NotNull(message = "Temperatura máxima é campo obrigatório")
    private Integer temperaturaMaxima;

    @NotNull
    private Turno turno;

    @NotNull(message = "O clima não pode ser nulo")
    private Clima clima;
    @NotNull
    private Integer precipitacao;

}