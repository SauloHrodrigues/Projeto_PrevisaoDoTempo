package com.projeto_final.PrevisaoDoTempo.core.entities;

import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "dados_meteorologico")
public class DadoMeteorologico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dados")
    private Long id;
    private LocalDate data;
    private Integer temperaturaMinima;
    private Integer temperaturaMaxima;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @Enumerated(EnumType.STRING)
    private Clima clima;
    private Integer precipitacao;
}
