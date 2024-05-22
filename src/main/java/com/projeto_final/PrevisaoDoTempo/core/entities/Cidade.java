package com.projeto_final.PrevisaoDoTempo.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cidades")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade")
    private List<DadoMeteorologico> dadosMeteorologicos= new ArrayList<>();

}
