package rmiClient;

import rmiService.IConversion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

// Classe responsable de la communication avec le serveur RMI pour la conversion
public class ConversionClient {

    public static void main(String[] args) {
        IConversion stub;

        try {
            // Recherche du service de conversion dans le registre RMI
            stub = (IConversion) Naming.lookup("rmi://localhost:1099/ConversionObject");

            // Demande à l'utilisateur de saisir un montant
            System.out.println("Donner le Montant :");
            Scanner scanner = new Scanner(System.in);
            double montant = scanner.nextDouble();

            // Appel de la méthode distante pour la conversion
            double montantConv = stub.convertirMontant(montant);

            // Affichage des résultats
            System.out.println("Montant avant conv:" + montant);
            System.out.println("\nMontant apres conv:" + montantConv);
        } catch (NotBoundException e) {
            // En cas d'erreur de liaison avec le service distant
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            // En cas d'URL mal formée
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            // En cas d'erreur de communication avec le serveur RMI
            throw new RuntimeException(e);
        }
    }
}
