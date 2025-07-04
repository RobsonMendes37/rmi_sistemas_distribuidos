package org.example.hospital.servico;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.example.hospital.modelo.Funcionario;

public interface RHServiceRemote extends Remote {

    void contratarFuncionario(Funcionario funcionario) throws RemoteException;

    List<Funcionario> listarFuncionarios() throws RemoteException;

    Funcionario consultarFuncionario(String matricula) throws RemoteException;

    boolean demitirFuncionario(String matricula) throws RemoteException;

    String getNomeHospital() throws RemoteException;
}