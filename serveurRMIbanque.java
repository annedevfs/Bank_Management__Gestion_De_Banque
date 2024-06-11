package serveurBanque;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class serveurRMIbanque {
    public serveurRMIbanque() {
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            operationsClientsBanque banque = new operationsClientsBanque();
            Naming.rebind("rmi://localhost/bank_Management__GestionnaireDeCompteBancaire", banque);
            System.out.println("serveur de la banque actif.");
        } catch (Exception var2) {
            Exception e = var2;
            e.printStackTrace();
        }

    }
}
