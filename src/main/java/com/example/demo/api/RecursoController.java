package com.example.demo.api;

import com.example.demo.domain.Recurso;
import com.example.demo.infrastructure.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    private RecursoRepository recursoRepository;

    @PostMapping
    public Recurso cadastrarRecurso(@RequestBody Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    @GetMapping
    public List<Recurso> listarRecursos() {
        return recursoRepository.findAll();
    }
}
