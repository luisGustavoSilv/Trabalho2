package web.trab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.trab2.config.Utils;
import web.trab2.model.Aluno;
import web.trab2.model.AlunoDto;
import web.trab2.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private AlunoRepository repository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @PostMapping("/updateAluno")
    public ResponseEntity<Object> updateAluno(@RequestBody AlunoDto dto) {
        try {
            // Validação básica do DTO
            if (dto.getId() == null || dto.getId().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
            }
            if (dto.getTurma() == null || dto.getTurma().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Turma inválida");
            }

            // Conversão de tipos
            Long id = Long.parseLong(dto.getId());
            int turma = Integer.parseInt(dto.getTurma());

            Optional<Aluno> alunoOptional = repository.findById(id);
            if (alunoOptional.isPresent()) {
                Aluno aluno = alunoOptional.get();
                aluno.setNome(dto.getNome());
                aluno.setId(id); // O ID normalmente não é alterado
                aluno.setTurma(turma);
                repository.save(aluno);
                return ResponseEntity.status(HttpStatus.OK).body(aluno);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há aluno com este id");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de ID ou Turma inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @PostMapping("/novoAluno")
    public ResponseEntity<Object> novoAluno(@RequestBody AlunoDto dto) {
        try {
            // Validação básica do DTO
            if (dto.getNome() == null || dto.getNome().isEmpty() ||
                dto.getTurma() == null || dto.getTurma().isEmpty() ||
                dto.getMatricula() == null || dto.getMatricula().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados incompletos ou inválidos");
            }

            // Conversão da turma de String para int
            int turma;
            try {
                turma = Integer.parseInt(dto.getTurma());
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Turma inválida");
            }

            // Contagem de alunos na turma
            int totalAlunosNaTurma = repository.countAlunoByTurma(turma);
            if (totalAlunosNaTurma >= 10) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Dados em excesso");
            }

            // Criação do novo aluno
            Aluno novoAluno = new Aluno(dto);
            repository.save(novoAluno);
            return ResponseEntity.status(HttpStatus.OK).body(novoAluno);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteAluno(@RequestBody AlunoDto dto) {
        try {
            Long alunoId = Long.parseLong(dto.getId());
            repository.deleteById(alunoId);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @GetMapping("/reset")
    public ResponseEntity<Object> reset() {
        try {
            repository.deleteAll();
            Utils.startDb(repository);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @GetMapping("/zerar")
    public ResponseEntity<Object> zerar() {
        try {
            repository.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}