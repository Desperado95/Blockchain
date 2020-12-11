/**
 * @author Mathilde
 */

public class Machine extends Noeud {

    private int ID;
    private String mdp;

    private int degreFiabilite;
    /**
     * Variables de verifications
     */
    private boolean checkVitesse;
    private boolean checkConso;
    private boolean checkForce;
    private boolean connecte;

    /**
     * Constructeur d'une Machine
     * int ID : identifiant de la machine ou numéro
     * String mdp : mot de passe de la machine en brut
     * boolean checkVitesse, checkConso, checkForce : Variables de contrôle
     * degrefiabilite prends la somme des booleen convertis en int
     */
    public Machine(int ID, String mdp, boolean checkVitesse, boolean checkConso, boolean checkForce) {
        super();
        this.ID = ID;
        this.mdp = mdp;

        this.checkVitesse = checkVitesse;
        this.checkConso = checkConso;
        this.checkForce = checkForce;
        this.degreFiabilite = (boolToInt(checkConso) + boolToInt(checkForce) + boolToInt(checkVitesse));

        this.connecte = true;
    }


    /**
     * Converti un boolean a en int (true = 1 / False = 0)
     */
    int boolToInt(boolean a) {
        return Boolean.compare(a, false);
    }


    /**
     * Redefinition de la fonction .toString pour renvoyer la chaîne d'une machine
     */
    @Override
    public String toString() {
        String toReturn = "\nMachine: " + ID + "\nmdp: " + mdp + "\ncheckVitesse: " + checkVitesse + "\ncheckConso: " + checkConso + "\ncheckForce: " + checkForce + "\netat: " + connecte + "\nFiabilite: " + degreFiabilite;
        if (degreFiabilite == 3) {
            toReturn += " (FIABLE)";
        } else {
            toReturn += " (NON FIABLE)";
        }
        return toReturn;
    }

    /**
     * Fonction qui affiche une machine
     */
    public void afficher() {
        System.out.println(this.toString());
    }

    /**
     * Verifie un transaction machine -> machine contenant une data
     *
     * @param t : transaction à verifier
     * @return
     */
    public String verifierTransaction(Transaction t) {
        if (((Machine) t.getEmetteur()).degreFiabilite >= 2) {
            return "Valide";
        } else {
            return "Non Valide";
        }
    }


    /**
     * A rajouter
     * Fonction Data EnvoyerData(Noeud destinataire, String donnee)
     */

    Data EnvoyerData(Noeud destinataire, String donnee, Machine[] listeVerificateur) {
        Data data = new Data(this, destinataire, donnee, listeVerificateur);
        return data;
    }

    /**
     * Getter
     * Setter
     */

    public int getID() {
        return ID;
    }

    public String getMdp() {
        return mdp;
    }

    public int getDegreFiabilite() {
        return degreFiabilite;
    }

    public boolean isCheckVitesse() {
        return checkVitesse;
    }

    public boolean isCheckConso() {
        return checkConso;
    }

    public boolean isCheckForce() {
        return checkForce;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setDegreFiabilite(int degreFiabilite) {
        this.degreFiabilite = degreFiabilite;
    }

    public void setCheckVitesse(boolean checkVitesse) {
        this.checkVitesse = checkVitesse;
    }

    public void setCheckConso(boolean checkConso) {
        this.checkConso = checkConso;
    }

    public void setCheckForce(boolean checkForce) {
        this.checkForce = checkForce;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }
}