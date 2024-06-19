package com.projeto_final.PrevisaoDoTempo.core.dto;

import lombok.Getter;
import lombok.Setter;

public class MeteorologicalDataResponseDto extends MeteorologicalDataRequestDto {
    @Getter
    @Setter
    private Long id;
}
