package com.example.demo.api;

import com.example.demo.domain.Turma;
import com.example.demo.infrastructure.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public Turma cadastrarTurma(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    @GetMapping
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
}
