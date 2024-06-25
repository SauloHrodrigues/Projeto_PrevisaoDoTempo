package com.projeto_final.PrevisaoDoTempo.core.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityRequestDdo {
    @NotBlank(message = "O Campo nome é obrigatório.")
    private String nome;
    @Valid
    private MeteorologicalDataRequestDto dadosMeteorologicos;

}
