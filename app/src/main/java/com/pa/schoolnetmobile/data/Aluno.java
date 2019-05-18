package com.pa.schoolnetmobile.data;

public class Aluno {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Integer nivelConclusao;
    private Curso curso;
    //notasFaltas = array com dados de faltas e notas

    //GETTERS E SETTERS

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNivelConclusao() {
        return nivelConclusao;
    }

    public void setNivelConclusao(Integer nivelConclusao) {
        this.nivelConclusao = nivelConclusao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


}
