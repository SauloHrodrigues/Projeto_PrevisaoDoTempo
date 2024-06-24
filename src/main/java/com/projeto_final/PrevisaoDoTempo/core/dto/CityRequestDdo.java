package com.projeto_final.PrevisaoDoTempo.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityRequestDdo {
    @NotBlank
    private String nome;
    private MeteorologicalDataRequestDto dadosMeteorologicos;
}
