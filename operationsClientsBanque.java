package serveurBanque;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class operationsClientsBanque extends UnicastRemoteObject implements interfaceServeur {
    private HashMap<String, Double> comptes = new HashMap();

    protected operationsClientsBanque() throws RemoteException {
    }

    public void creerCompte(String id, double somme) throws RemoteException {
        this.comptes.put(id, somme);
    }

    public void ajouter(String id, double somme) throws RemoteException {
        if (this.comptes.containsKey(id)) {
            this.comptes.put(id, (Double)this.comptes.get(id) + somme);
        }

    }

    public void retirer(String id, double somme) throws RemoteException {
        if (this.comptes.containsKey(id)) {
            this.comptes.put(id, (Double)this.comptes.get(id) - somme);
        }

    }

    public double consulterSolde(String id) throws RemoteException {
        return (Double)this.comptes.getOrDefault(id, 0.0);
    }

    public double transfererSolde(String id_C, String id_D, double somme) throws RemoteException {
        if (this.comptes.containsKey(id_C) && this.comptes.containsKey(id_D)) {
            this.retirer(id_C, somme);
            this.ajouter(id_D, somme);
            return somme;
        } else {
            return 0.0;
        }
    }
}
