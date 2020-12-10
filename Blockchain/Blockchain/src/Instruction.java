/**
 * @Author Erwann Forgez
 */
public class Instruction extends Transaction {

    String instruction;
    // Dans le constructeur degreImportance = MAX
    //Afficher

/*
    public Instruction() {
        this.instruction = "";
        this.etat ="A verifier";
        this.degreImportance = 4;//true
    }
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


    /**
     * Valide la transaction si les verificateurs sont majoritaires
     * **/
    void verifierTransaction(Noeud[] listeVerificateur, String[] validation) {
        assert listeVerificateur.length == validation.length;//verififie que les tableaux font la même taille
        int lgt = validation.length;
        int val = 0;
        int degreeMax=0;
        for(int i=0;i<lgt;i++)
        {
            if(validation[i]=="val")//val a modifier
            {
                Machine verificateur = (Machine)listeVerificateur[i];
                val += verificateur.degreFiabilite;
                degreeMax += verificateur.degreFiabilite;
            }
        }

            if(val > lgt/2)
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
        System.out.println("");
        System.out.println("Instruction : " + instruction);
        System.out.println(emetteur.toString() + "\n"+" -> " + destinataire.toString());
        System.out.println("");

    }

    public static void main(String[] args) {

        Noeud emet = new Operateur(1,"mdp","Nom","Prenom");
        Noeud dest = new Machine(2,"mdp",false,false,false);
        Instruction ist = new Instruction("Accelere ",emet,dest);
        String[] valid = {"","","","",""};
        Noeud[] verif = new Noeud[5];

       ist.afficher();
       ist.verifierTransaction(verif,valid);
     }
}
