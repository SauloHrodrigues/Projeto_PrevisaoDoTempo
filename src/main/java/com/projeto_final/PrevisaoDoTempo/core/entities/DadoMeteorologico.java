package com.projeto_final.PrevisaoDoTempo.core.entities;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "dados_meteorologico")
public class DadoMeteorologico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Cidade cidade;
    private LocalDate data;
    private Integer temperaturaMinima;
    private Integer temperaturaMaxima;
    @Enumerated(EnumType.STRING)
    private Turno turnos;
    @Enumerated(EnumType.STRING)
    private Clima clima;
    private Integer precipitacao;
}
