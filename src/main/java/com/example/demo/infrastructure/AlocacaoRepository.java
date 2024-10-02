package com.example.demo.infrastructure;

import com.example.demo.domain.Alocacao;
import com.example.demo.domain.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    List<Alocacao> findByTurmaCodigo(Long codigo);

    boolean existsByDataAndHorarioAndRecursos(LocalDate data, String horario, Recurso recurso);

    List<Alocacao> findByData(LocalDate data);
}
