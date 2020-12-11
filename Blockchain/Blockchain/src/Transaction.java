/**
 * @author Erwann
 */

public abstract class Transaction {

    private String etat; // Valide / Non Valide / A verifier
    private Noeud emetteur;
    private Noeud destinataire;
    private Noeud[] listeVerificateur;
    private int degreImportance; // A Determiner dans le constructeur

    void verifierTransaction() {

    }

    public Transaction(Noeud emetteur, Noeud destinataire, Noeud[] listeVerificateur) {
        this.etat = "A verifier";
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.listeVerificateur = listeVerificateur;
    }

    void verifierTransaction(Noeud[] listeVerificateur, String[] validation) {

    }

    /**
     * Getter
     * Setter
     */
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Noeud getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Noeud emetteur) {
        this.emetteur = emetteur;
    }

    public Noeud getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Noeud destinataire) {
        this.destinataire = destinataire;
    }

    public Noeud[] getListeVerificateur() {
        return listeVerificateur;
    }

    public void setListeVerificateur(Noeud[] listeVerificateur) {
        this.listeVerificateur = listeVerificateur;
    }

    public int getDegreImportance() {
        return degreImportance;
    }

    public void setDegreImportance(int degreImportance) {
        this.degreImportance = degreImportance;
    }
}