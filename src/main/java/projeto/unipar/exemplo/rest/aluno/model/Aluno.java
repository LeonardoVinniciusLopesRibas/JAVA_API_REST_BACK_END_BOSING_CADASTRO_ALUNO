package projeto.unipar.exemplo.rest.aluno.model;

import projeto.unipar.exemplo.rest.aluno.dto.AlunoRequest;

public class Aluno {
    
    private int id;
    private String nome;
    private String cpf;
    private String email;

    public Aluno() {
    }

    public Aluno(int id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static Aluno requestToAluno(AlunoRequest alunoRequest){
        return new Aluno(0, alunoRequest.getNome(), alunoRequest.getCpf(), alunoRequest.getEmail());
    }
    
    
}
