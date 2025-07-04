package org.example.hospital.app;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;
import org.example.hospital.modelo.*;
import org.example.hospital.servico.RHServiceRemote;

public class ClienteRMI {
    private static RHServiceRemote service;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("=================================================");
            System.out.println("CLIENTE INTERATIVO DE RH DE HOSPITAL - RMI");
            System.out.println("=================================================");

            String serviceURL = "rmi://localhost:2999/RHService";
            service = (RHServiceRemote) Naming.lookup(serviceURL);
            System.out.println("Conectado ao servidor: " + serviceURL);
            System.out.println("Hospi   tal: " + service.getNomeHospital());
            System.out.println();

            while (true) {
                try {
                    exibirMenu();
                    int opcao = lerOpcao();

                    switch (opcao) {
                        case 1:
                            contratarFuncionario();
                            break;
                        case 2:
                            listarFuncionarios();
                            break;
                        case 3:
                            consultarFuncionario();
                            break;
                        case 4:
                            demitirFuncionario();
                            break;
                        case 0:
                            System.out.println("Encerrando o cliente...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opcao invalida! Tente novamente.");
                    }
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();

                } catch (Exception e) {
                    System.err.println("Erro: " + e.getMessage());
                    System.out.println("\n(((Pressione ENTER para continuar)))");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o servidor RMI: " + e.getMessage());
            System.err.println("Certifique-se que o ServidorRMI esta rodando!");
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMENU PRINCIPAL:");
        System.out.println("1  Contratar Funcionario");
        System.out.println("2  Listar Funcionarios");
        System.out.println("3  Consultar Funcionario");
        System.out.println("4  Demitir Funcionario");
        System.out.println("0  Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor inválido para forçar o erro no switch
        }
    }

    private static void contratarFuncionario() throws Exception {
        System.out.println("\n--- CONTRATAR NOVO FUNCIONARIO ---");
        System.out.println("Tipos disponiveis:");
        System.out.println("1 - Medico Efetivo");
        System.out.println("2 - Medico Plantonista");
        System.out.println("3 - Enfermeiro");
        System.out.print("Escolha o tipo de funcionario: ");

        int tipo = Integer.parseInt(scanner.nextLine());
        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();

        Funcionario funcionario = null;
        switch (tipo) {
            case 1:
                System.out.print("CRM: ");
                String crmEfetivo = scanner.nextLine();
                System.out.print("Salario Base (ex: 15000.00): ");
                double salario = Double.parseDouble(scanner.nextLine());
                funcionario = new MedicoEfetivo(matricula, nome, crmEfetivo, salario);
                break;

            case 2:
                System.out.print("CRM: ");
                String crmPlantonista = scanner.nextLine();
                System.out.print("Horas Trabalhadas no Mes: ");
                int horas = Integer.parseInt(scanner.nextLine());
                System.out.print("Valor por Hora de Plantao (ex: 250.00): ");
                double valorHora = Double.parseDouble(scanner.nextLine());
                funcionario = new MedicoPlantonista(matricula, nome, crmPlantonista, horas, valorHora);
                break;

            case 3:
                System.out.print("COREN (Registro de Enfermagem): ");
                String coren = scanner.nextLine();
                System.out.print("Salario Base (ex: 4500.00): ");
                double salarioEnfermeiro = Double.parseDouble(scanner.nextLine());
                funcionario = new Enfermeiro(matricula, nome, coren, salarioEnfermeiro);
                break;

            default:
                System.out.println("Tipo invalido!");
                return;
        }
        service.contratarFuncionario(funcionario);
        System.out.println("\nFuncionario contratado com sucesso!");
        System.out.println("Salario calculado: R$ " + String.format("%.2f", funcionario.calcularSalario()));
    }

    private static void listarFuncionarios() throws Exception {
        System.out.println("\n--- QUADRO DE FUNCIONARIOS ---");

        List<Funcionario> funcionarios = service.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario encontrado no sistema.");
            return;
        }

        System.out.println("-".repeat(80));
        for (Funcionario f : funcionarios) {
            System.out.printf("Matricula: %s | Nome: %s%n", f.getMatricula(), f.getNome());
            System.out.printf("Cargo/Detalhes: %s%n", f.obterDetalhes());
            System.out.println("-".repeat(80));
        }

        System.out.printf("Total de funcionarios: %d%n", funcionarios.size());
    }

    private static void consultarFuncionario() throws Exception {
        System.out.println("\n--- CONSULTAR FUNCIONARIO ---");
        System.out.print("Digite a matricula do funcionario: ");
        String matricula = scanner.nextLine();

        Funcionario funcionario = service.consultarFuncionario(matricula);

        if (funcionario != null) {
            System.out.println("\nDados do Funcionario:");
            System.out.println("-".repeat(40));
            System.out.printf("Matricula: %s%nNome: %s%n", funcionario.getMatricula(), funcionario.getNome());
            System.out.printf("Detalhes: %s%n", funcionario.obterDetalhes());
            System.out.println("-".repeat(40));
        } else {
            System.out.printf("Funcionario com matricula '%s' nao encontrado.%n", matricula);
        }
    }

    private static void demitirFuncionario() throws Exception {
        System.out.println("\n--- DEMITIR FUNCIONARIO ---");
        System.out.print("Digite a matricula do funcionario a ser demitido: ");
        String matricula = scanner.nextLine();

        boolean demitido = service.demitirFuncionario(matricula);

        if (demitido) {
            System.out.printf("Funcionario com matricula '%s' foi demitido com sucesso!%n", matricula);
        } else {
            System.out.printf("Funcionario com matricula '%s' nao foi encontrado no sistema.%n", matricula);
        }
    }
}