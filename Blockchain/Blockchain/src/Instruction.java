import sun.awt.windows.WPrinterJob;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Erwann Forgez
 */
public class Instruction extends Transaction {

    private final String instruction;
    // Dans le constructeur degreeImportance = MAX
    //Afficher
    /*

     */
    /*public Instruction(String instruction,Noeud emet,Noeud dest) {
        super(emet,dest);
        this.instruction = instruction;
        this.emetteur = emet;
        this.destinataire = dest;
        this.degreImportance = 4;//true
    }*/

    public Instruction(Noeud emet,Noeud dest,String instruction, Noeud[] listeVerificateur) {
        super(emet,dest, listeVerificateur);
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

    void verifierTransaction(){
        int nbV = listeVerificateur.length;
        int n=0; int cpt=0;
        String[] val =new String[nbV];
        for( Noeud op : listeVerificateur){
            if(op instanceof Operateur){
                val[n]=((Operateur) op).demanderValidation(this);
                if(val[n].equals("Valide")) cpt++;
                n++;
            }
        }
        if(cpt> (listeVerificateur.length/2)){
            this.etat="Valide";
        }
        else{
            this.etat="Non Valide";
        }


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

    /**
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("1");
        test.add("2");

        System.out.println(test.get(0));
        System.out.println(test.get(1));

        Noeud emet = new Operateur(1,"mdp","Nom","Prenom");
        Noeud dest = new Machine(2,"mdp",true,true,true);

        Noeud op1 = new Operateur(2,"mdp","Nom","Prenom");
        Noeud op2 = new Operateur(3,"mdp","Nom","Prenom");

        Noeud[] lV = new Noeud[2];
        lV[0]=op1;
        lV[1]=op2;
        Instruction ist = new Instruction(emet,dest,"Accelere ",lV);

        Noeud verif1 = new Machine(3,"mdp",true,true,true);
        Noeud verif2 = new Machine(4,"mdp",true,true,true);
        Noeud verif3 = new Machine(5,"mdp",false,false,false);
        Noeud[] listeVerif={verif1,verif2, verif3};

        ist.afficher();
        String[] valid = ist.valider(listeVerif);
        ist.verifierTransaction(listeVerif,valid);
        System.out.println(ist.etat);

    }
*/
}
