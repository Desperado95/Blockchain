public class Data extends Transaction {

    String donnee;
    int degreImportance; //à determiner via les enumérations MotsImportants
    int nbVerificateur;
    Machine[] listeVerificateur;
    String[] reponseVerificateur;


    public Data(Machine emetteur, Noeud destinataire, String donnee, Machine[] listeVerificateur) {
        super(emetteur, destinataire,listeVerificateur);
        this.donnee = donnee;
        this.listeVerificateur=listeVerificateur;
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
            etat = "Valide";
        } else {
            etat = "Non Valide";
        }
    }


    /**
     * Fonction qui demande la validation ou non de la data et met à jour son état selon la majorité
     */
    void verifierTransaction(Machine[] listeVerificateur,String[] reponseVerificateur) {
        int cpt = 0;
        for (int i = 0; i < listeVerificateur.length; i++) {
            if(listeVerificateur[i] instanceof Machine)
            reponseVerificateur[i] = listeVerificateur[i].verifierTransaction(this);
            if (reponseVerificateur[i].equals("Valide")) {
                cpt++;
            }
        }
        if (cpt > (listeVerificateur.length / 2)) {
            etat = "Valide";
        } else {
            etat = "Non Valide";
        }
    }
    /**
     * @return string contenant le verificateur et leurs réponses
     */
    public String getVerificateur() {
        String result = " [ ";
        for (int i = 0; i < listeVerificateur.length; i++) {
            result += listeVerificateur[i].ID + " : " + reponseVerificateur[i] + " ; ";
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
                ", etat='" + etat + '\'' +
                ",\n\n emetteur= " + emetteur +
                ",\n\n destinataire=" + destinataire +
                '}' + "\n Liste verificateurs :" + this.getVerificateur() + "\n\n ------------";
    }


    public static void main(String[] args){
     Machine m1 = new Machine(1,"mdp",true,true,true);
     Machine m2 = new Machine(2,"mdp2",false,false,true);
     Machine[] listeMachine=new Machine[2];
     listeMachine[0]=m1;
         listeMachine[1]=m2;
     Data d = new Data(m1,m2,"a detecte une PANNE",listeMachine);// contient "PANNE" donc degreImportance = 3
         d.verifierTransaction();
         System.out.println(d.etat);
        System.out.println(d.toString());
     }

}