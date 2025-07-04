package org.example.hospital.modelo;
import org.example.hospital.modelo.Funcionario;

public abstract class Medico extends Funcionario {
    protected String crm;

    public Medico(String matricula, String nome, String crm) {
        super(matricula, nome);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}