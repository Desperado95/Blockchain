/**
 * @author  Erwann Forgez
 */
public class Instruction extends Transaction {

    private final String instruction;
    // Dans le constructeur degreeImportance = MAX
    //Afficher
/*

*/
    public Instruction(String instruction,Noeud emet,Noeud dest) {
        super(emet,dest);
        this.instruction = instruction;
        this.emetteur = emet;
        this.destinataire = dest;
        this.degreImportance = 4;//true
    }

    public Instruction(Noeud emet,Noeud dest,String instruction) {
        super(emet,dest);
        this.instruction = instruction;
        this.emetteur = emet;
        this.destinataire = dest;
        this.degreImportance = 4;//true
    }

    public String getInstruction() {
        return instruction;
    }

//Operateur -> machine operateur verificateur
//Machine -> Operateur X
//Machine -> Machine Machine verificateur

    String[] valider(Noeud[] listeVerificateur){
        int nbV = listeVerificateur.length;
        int n=0;
        String[] val =new String[nbV];
        for( Noeud m : listeVerificateur){
            if(m instanceof Machine){
                val[n]=((Machine) m).verifierTransaction(this);
                n++;
            }
        }
        return val;
    }

    /**
     * Valide la transaction si les verificateurs sont majoritaires
     * @param listeVerificateur liste des objets verifiants la transaction
     * **/
    void verifierTransaction(Noeud[] listeVerificateur, String[] validation) {
        assert listeVerificateur.length == validation.length;//verififie que les tableaux font la même taille
        int lgt = validation.length;
        int val = 0;
        int degreeMax=0;
        for(int i=0;i<lgt;i++)
        {
            if(validation[i].equals("val"))//val a modifier
            {
                Machine verificateur = (Machine)listeVerificateur[i];
                val += verificateur.degreFiabilite;
                degreeMax += verificateur.degreFiabilite;
            }
        }

            if(val > degreeMax/2)
                this.etat="Valide";
            else
                this.etat="Non Valide";

        //this.setEtat("valide"/"nonvalide")
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "instruction='" + instruction + '\'' +
                '}';
    }

    public void afficher(){
        System.out.println();
        System.out.println("Instruction : " + instruction);
        System.out.println(emetteur.toString() + "\n"+" -> " + destinataire.toString());
        System.out.println();

    }

    public static void main(String[] args) {

        Noeud emet = new Operateur(1,"mdp","Nom","Prenom");
        Noeud dest = new Machine(2,"mdp",true,true,true);

        Instruction ist = new Instruction("Accelere ",emet,dest);

        Noeud verif1 = new Machine(3,"mdp",true,true,true);
        Noeud verif2 = new Machine(4,"mdp",true,true,true);
        Noeud verif3 = new Machine(5,"mdp",false,false,false);
        Noeud[] listeVerif={verif1,verif2, verif3};

       ist.afficher();
       String[] valid = ist.valider(listeVerif);
       ist.verifierTransaction(listeVerif,valid);
       System.out.println(ist.etat);

     }

}
