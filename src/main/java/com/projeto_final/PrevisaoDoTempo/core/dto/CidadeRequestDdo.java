package com.projeto_final.PrevisaoDoTempo.core.dto;

import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class CidadeRequestDdo {
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private DadoMeteorologicoRequestDto dadosMeteorologicos;
}
