package org.example.hospital.modelo;

public class Enfermeiro extends Funcionario {
    private String coren;
    private double salarioBase;

    public Enfermeiro(String matricula, String nome, String coren, double salarioBase) {
        super(matricula, nome);
        this.coren = coren;
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularSalario() {
        return salarioBase;
    }

    @Override
    public String obterDetalhes() {
        return String.format("Enfermeiro | COREN: %s | Salario: R$ %.2f", coren, calcularSalario());
    }
}
