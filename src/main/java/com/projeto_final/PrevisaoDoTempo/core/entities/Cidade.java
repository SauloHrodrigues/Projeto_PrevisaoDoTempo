package com.projeto_final.PrevisaoDoTempo.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cidades")
public class Cidade {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DadoMeteorologico> dadosMeteorologicos= new ArrayList<>();

}