/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.unipar.exemplo.rest.aluno.dto;

/**
 *
 * @author leona
 */
public class AlunoFindAllResponse {
    
    private int id;
    private String nome;

    public AlunoFindAllResponse() {
    }

    public AlunoFindAllResponse(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
    
    
    
    
}
