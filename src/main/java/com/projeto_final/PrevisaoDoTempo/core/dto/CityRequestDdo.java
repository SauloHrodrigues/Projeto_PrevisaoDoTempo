package com.projeto_final.PrevisaoDoTempo.core.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDdo {
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private MeteorologicalDataRequestDto dadosMeteorologicos;
}
