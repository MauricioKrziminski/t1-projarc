package com.example.demo.api;

import com.example.demo.domain.Alocacao;
import com.example.demo.infrastructure.AlocacaoRepository;
import com.example.demo.infrastructure.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoController {

    @Autowired
    private AlocacaoRepository alocacaoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Alocacao> listarAlocacoes() {
        return alocacaoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> criarAlocacao(@RequestBody Alocacao alocacao) {
        // Validação de dados
        if (!turmaRepository.existsById(alocacao.getTurma().getCodigo())) {
            return ResponseEntity.badRequest().body("Turma não encontrada");
        }
        for (var recurso : alocacao.getRecursos()) {
            if (alocacaoRepository.existsByDataAndHorarioAndRecursos(alocacao.getData(), alocacao.getHorario(), recurso)) {
                return ResponseEntity.badRequest().body("Recurso já reservado para este horário.");
            }
        }
        Alocacao novaAlocacao = alocacaoRepository.save(alocacao);
        return ResponseEntity.ok(novaAlocacao);
    }

    // Listar compromissos de uma turma
    @GetMapping("/turmas/{codigo}/compromissos")
    public ResponseEntity<?> listarCompromissosDaTurma(@PathVariable Long codigo) {
        if (!turmaRepository.existsById(codigo)) {
            return ResponseEntity.badRequest().body("Turma não encontrada");
        }
        List<Alocacao> alocacoes = alocacaoRepository.findByTurmaCodigo(codigo);
        return ResponseEntity.ok(alocacoes);
    }

    // Listar reservas por data específica
    @GetMapping("/reservas")
    public ResponseEntity<?> listarReservasPorData(
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Alocacao> reservas = alocacaoRepository.findByData(data);
        return ResponseEntity.ok(reservas);
    }
}
