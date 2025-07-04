package org.example.hospital.modelo;

public class MedicoEfetivo extends Medico {
    private double salarioBase;

    public MedicoEfetivo(String matricula, String nome, String crm, double salarioBase) {
        super(matricula, nome, crm);
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return this.salarioBase;
    }

    @Override
    public String obterDetalhes() {
        return String.format("Medico Efetivo | CRM: %s | Salario: R$ %.2f", crm, calcularSalario());
    }
}
