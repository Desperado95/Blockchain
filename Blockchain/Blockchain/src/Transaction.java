/**
 * @author Erwann
 */

public abstract class Transaction {

    String etat; // Valide / Non Valide / A verifier
    Noeud emetteur;
    Noeud destinataire;
    Noeud[] listeVerificateur;
    int degreImportance; // A Determiner dans le constructeur

    void verifierTransaction() {

    }

    public Transaction( Noeud emetteur, Noeud destinataire,Noeud[] listeVerificateur) {
        this.etat = "A verifier";
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.listeVerificateur = listeVerificateur;
    }

    void verifierTransaction(Noeud[] listeVerificateur,String[] validation) {

    }

}