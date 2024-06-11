package serveurBanque;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaceServeur extends Remote {
    void creerCompte(String var1, double var2) throws RemoteException;

    void ajouter(String var1, double var2) throws RemoteException;

    void retirer(String var1, double var2) throws RemoteException;

    double consulterSolde(String var1) throws RemoteException;

    double transfererSolde(String var1, String var2, double var3) throws RemoteException;
}
