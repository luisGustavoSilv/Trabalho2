package web.trab2.model;

public class AlunoDto {

    private String id;
    private String turma;
    private String nome;
    private String matricula;
    private String nota;

    // Construtor padrão
    public AlunoDto() {}

    // Construtor que inicializa a partir de um objeto Aluno
    public AlunoDto(Aluno a) {
        this.id = String.valueOf(a.getId());
        this.turma = String.valueOf(a.getTurma());
        this.nome = a.getNome();
        this.matricula = a.getMatricula();
        this.nota = String.valueOf(a.getNota());
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTurma() {
        return turma;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNota() {
        return nota;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    // toString method
    @Override
    public String toString() {
        return "AlunoDto{" +
                "id='" + id + '\'' +
                ", turma='" + turma + '\'' +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }
}