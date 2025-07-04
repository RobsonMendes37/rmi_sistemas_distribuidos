package org.example.hospital.modelo;

public class MedicoPlantonista extends Medico {
    private int horasTrabalhadas;
    private double valorHoraPlatao;

    public MedicoPlantonista(String matricula, String nome, String crm, int horasTrabalhadas, double valorHoraPlatao) {
        super(matricula, nome, crm);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHoraPlatao = valorHoraPlatao;
    }

    @Override
    public double calcularSalario() {
        return this.horasTrabalhadas * this.valorHoraPlatao;
    }

    @Override
    public String obterDetalhes() {
        return String.format("Medico Plantonista | CRM: %s | Horas: %d | Salario: R$ %.2f", crm, horasTrabalhadas, calcularSalario());
    }
}