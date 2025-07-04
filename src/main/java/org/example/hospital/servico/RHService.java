package org.example.hospital.servico;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.example.hospital.modelo.Funcionario;

public class RHService extends UnicastRemoteObject implements RHServiceRemote {
    private Hospital hospital;

    public RHService(String nomeHospital) throws RemoteException {
        super();
        this.hospital = new Hospital(nomeHospital);
    }

    @Override
    public void contratarFuncionario(Funcionario funcionario) throws RemoteException {
        hospital.contratarFuncionario(funcionario);
    }

    @Override
    public List<Funcionario> listarFuncionarios() throws RemoteException {
        return hospital.getFuncionarios();
    }

    @Override
    public Funcionario consultarFuncionario(String matricula) throws RemoteException {
        for (Funcionario f : hospital.getFuncionarios()) {
            if (f.getMatricula().equals(matricula))
                return f;
        }
        return null;
    }

    @Override
    public boolean demitirFuncionario(String matricula) throws RemoteException {
        return hospital.getFuncionarios().removeIf(f -> f.getMatricula().equals(matricula));
    }

    @Override
    public String getNomeHospital() throws RemoteException {
        return hospital.getNome();
    }
}