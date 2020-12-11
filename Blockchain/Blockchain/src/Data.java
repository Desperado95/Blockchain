/**
 * @author Théo
 */

public class Data extends Transaction {

    private String donnee;
    private int degreImportance; //à determiner via les enumérations MotsImportants
    private int nbVerificateur;
    private Machine[] listeVerificateur;
    private String[] reponseVerificateur;


    /**
     * Constructeur Data
     */
    public Data(Machine emetteur, Noeud destinataire, String donnee, Machine[] listeVerificateur) {
        super(emetteur, destinataire, listeVerificateur);
        this.donnee = donnee;
        this.listeVerificateur = listeVerificateur;
        reponseVerificateur = new String[listeVerificateur.length];
        initialiserReponsesVerificateur(); // met les reponses à "A valider"

        if (donnee.contains(MotsImportants.valueOf("CRITIQUE").toString())) {
            degreImportance = 4;
        } else if (donnee.contains(MotsImportants.valueOf("PANNE").toString())) {
            degreImportance = 3;
        } else if (donnee.contains(MotsImportants.valueOf("URGENT").toString())) {
            degreImportance = 2;
        } else if (donnee.contains(MotsImportants.valueOf("MANQUE").toString())) {
            degreImportance = 1;
        } else {
            degreImportance = 0;
        }

    }

    /**
     * Fonction qui initilise les réponses à "A valider"
     */
    public void initialiserReponsesVerificateur() {
        for (String s : reponseVerificateur) {
            s = "A valider";
        }
    }

    /**
     * Fonction qui demande la validation ou non de la data et met à jour son état selon la majorité
     */
    void verifierTransaction() {
        int cpt = 0;
        for (int i = 0; i < listeVerificateur.length; i++) {
            reponseVerificateur[i] = listeVerificateur[i].verifierTransaction(this);
            if (reponseVerificateur[i].equals("Valide")) {
                cpt++;
            }
        }
        if (cpt > (listeVerificateur.length / 2)) {
            setEtat("Valide");
        } else {
            setEtat("Non Valide");
        }
    }

    /**
     * Fonction qui demande la validation ou non de la data et met à jour son état selon la majorité
     */
    void verifierTransaction(Machine[] listeVerificateur, String[] reponseVerificateur) {
        int cpt = 0;
        for (int i = 0; i < listeVerificateur.length; i++) {
            if (listeVerificateur[i] instanceof Machine)
                reponseVerificateur[i] = listeVerificateur[i].verifierTransaction(this);
            if (reponseVerificateur[i].equals("Valide")) {
                cpt++;
            }
        }
        if (cpt > (listeVerificateur.length / 2)) {
            setEtat("Valide");
        } else {
            setEtat("Non Valide");
        }
    }

    /**
     * @return string contenant le verificateur et leurs réponses
     */
    public String getVerificateur() {
        String result = " [ ";
        for (int i = 0; i < listeVerificateur.length; i++) {
            result += listeVerificateur[i].getID() + " : " + reponseVerificateur[i] + " ; ";
        }
        result += " ] ";
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "donnee='" + donnee + '\'' +
                ", degreImportance=" + degreImportance +
                ", nbVerificateur=" + nbVerificateur +
                ", etat='" + getEtat() + '\'' +
                ",\n\n emetteur= " + getEmetteur() +
                ",\n\n destinataire=" + getDestinataire() +
                '}' + "\n Liste verificateurs :" + this.getVerificateur() + "\n\n ------------";
    }


    /**
     * Getter
     * Setter
     */

    public String getDonnee() {
        return donnee;
    }

    public void setDonnee(String donnee) {
        this.donnee = donnee;
    }

    public int getDegreImportance() {
        return degreImportance;
    }

    public void setDegreImportance(int degreImportance) {
        this.degreImportance = degreImportance;
    }

    public int getNbVerificateur() {
        return nbVerificateur;
    }

    public Machine[] getListeVerificateur() {
        return listeVerificateur;
    }

    public String[] getReponseVerificateur() {
        return reponseVerificateur;
    }

    public void setReponseVerificateur(String[] reponseVerificateur) {
        this.reponseVerificateur = reponseVerificateur;
    }

    public void setListeVerificateur(Machine[] listeVerificateur) {
        this.listeVerificateur = listeVerificateur;
    }

    public void setNbVerificateur(int nbVerificateur) {
        this.nbVerificateur = nbVerificateur;
    }
}