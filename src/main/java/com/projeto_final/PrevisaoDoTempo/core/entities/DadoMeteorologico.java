package com.projeto_final.PrevisaoDoTempo.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Clima;
import com.projeto_final.PrevisaoDoTempo.core.enuns.Turno;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dados_meteorologico")
public class DadoMeteorologico {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    private LocalDate data;
    @Getter @Setter
    private Integer temperaturaMinima;
    @Getter @Setter
    private Integer temperaturaMaxima;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Clima clima;
    @Getter @Setter
    private Integer precipitacao;
    @JsonIgnore
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
