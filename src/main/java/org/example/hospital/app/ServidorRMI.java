package org.example.hospital.app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import org.example.hospital.servico.RHService;

public class ServidorRMI {

    public static void main(String[] args) {
        try {
            System.out.println("=================================================");
            System.out.println("INICIANDO SERVIDOR RMI DE RH DE HOSPITAL");
            System.out.println("=================================================");

            LocateRegistry.createRegistry(2999);
            System.out.println("Registro RMI criado na porta 2999");

            RHService service = new RHService("Hospital Central de Capistrano");

            String serviceName = "RHService";
            Naming.rebind("rmi://localhost:2999/" + serviceName, service);

            System.out.println("Servico '" + serviceName + "' registrado com sucesso!");
            System.out.println("Servidor RMI rodando em: rmi://localhost:2999/" + serviceName);
            System.out.println("Aguardando clientes...");
            System.out.println("\nPara parar o servidor, pressione Ctrl+C");

            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}