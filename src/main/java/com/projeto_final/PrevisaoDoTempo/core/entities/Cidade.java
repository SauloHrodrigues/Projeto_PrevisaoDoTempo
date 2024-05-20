package com.projeto_final.PrevisaoDoTempo.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

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
    private Map<LocalDate,DadoMeteorologico> dadosMeteorologicos= new HashMap<>();

}
