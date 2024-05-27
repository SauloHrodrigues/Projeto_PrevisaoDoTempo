package com.projeto_final.PrevisaoDoTempo.repositories;

import com.projeto_final.PrevisaoDoTempo.core.entities.Cidade;
import com.projeto_final.PrevisaoDoTempo.core.entities.DadoMeteorologico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadoMeteorologicoRepository extends JpaRepository<DadoMeteorologico, Long> {
}
