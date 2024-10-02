package com.example.demo.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Turma {

    @Id
    private Long codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Professor professor;

    private String horario;

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
