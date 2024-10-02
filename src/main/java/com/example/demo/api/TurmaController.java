package com.example.demo.api;

import com.example.demo.domain.Professor;
import com.example.demo.domain.Turma;
import com.example.demo.infrastructure.ProfessorRepository;
import com.example.demo.infrastructure.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarTurma(@RequestBody Turma turma) {
        // Verificar se o professor já existe pelo email
        Professor professor = professorRepository.findByEmail(turma.getProfessor().getEmail())
            .orElse(turma.getProfessor()); // Se não encontrar, usa o professor do corpo da requisição

        // Atualiza o nome caso o professor exista, mas o nome tenha mudado
        professor.setNome(turma.getProfessor().getNome());

        // Persistir o professor antes de associá-lo à turma
        professor = professorRepository.save(professor);

        // Associa o professor salvo à turma
        turma.setProfessor(professor);

        // Salva a turma
        Turma novaTurma = turmaRepository.save(turma);
        return ResponseEntity.ok(novaTurma);
    }

    @GetMapping
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
}
