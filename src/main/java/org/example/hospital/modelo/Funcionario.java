package org.example.hospital.modelo;

import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    protected String matricula;
    protected String nome;

    public Funcionario(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract String obterDetalhes();
    public abstract double calcularSalario();
}