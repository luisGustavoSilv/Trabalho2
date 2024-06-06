package web.trab2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int turma;
    private String nome;
    private String matricula;
    private double nota;

    public Aluno() {}

    public Aluno(AlunoDto dto) {
        this.id = (dto.getId() == null || dto.getId().isEmpty()) ? null : Long.parseLong(dto.getId());
        this.turma = (dto.getTurma() == null || dto.getTurma().isEmpty()) ? 0 : Integer.parseInt(dto.getTurma());
        this.nome = dto.getNome();
        this.matricula = dto.getMatricula();
        this.nota = (dto.getNota() == null || dto.getNota().isEmpty()) ? 0 : Double.parseDouble(dto.getNota());
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", turma=" + turma +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nota=" + nota +
                '}';
    }
}