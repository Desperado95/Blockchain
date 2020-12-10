import com.sun.org.apache.xpath.internal.operations.Bool;

public abstract class Transaction {

    String etat; // Valide / Non Valide / A verifier
    Noeud emetteur;
    Noeud destinataire;
    int degreImportance; // A Determiner dans le constructeur


    public Transaction( Noeud emetteur, Noeud destinataire) {
        this.etat = "A verifier";
        this.emetteur = emetteur;
        this.destinataire = destinataire;

    }

    void verifierTransaction(Noeud[] listeVerificateur, String[] validation) {

    }

}
