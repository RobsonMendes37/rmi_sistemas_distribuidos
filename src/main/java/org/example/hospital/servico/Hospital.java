//eh o servico que representa a agragação
package org.example.hospital.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.example.hospital.modelo.Funcionario;

public class Hospital implements Serializable {
    private String nome;
    private List<Funcionario> funcionarios;

    public Hospital(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public void contratarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public String getNome() {
        return nome;
    }
}