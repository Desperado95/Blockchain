import sun.awt.windows.WPrinterJob;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwann Forgez
 */
public class Instruction extends Transaction {

    private final String instruction;

    /**
     * Constructeur Instruction
     */
    public Instruction(Noeud emet, Noeud dest, String instruction, Noeud[] listeVerificateur) {
        super(emet, dest, listeVerificateur);
        this.instruction = instruction;
        this.setEmetteur(emet);
        this.setDestinataire(dest);
        this.setDegreImportance(4);//true
    }


    /**
     * verifierTransaction valide la transaction si consensus de la liste des verificateurs
     * Operateur -> machine operateur verificateur
     * Machine -> Operateur X
     * Machine -> Machine Machine verificateur
     */
    void verifierTransaction() {
        int nbV = getListeVerificateur().length;
        int n = 0;
        int cpt = 0;
        //On crée un tableau de même taille que la liste de verificateur
        String[] val = new String[nbV];
        for (Noeud op : getListeVerificateur()) {
            if (op instanceof Operateur) {
                //Demande à chaque operateur de vérifier
                val[n] = ((Operateur) op).demanderValidation(this);
                //Si l'operateur a validé on incremente le compteur de validé
                if (val[n].equals("Valide")) cpt++;
                n++;
            }
        }
        //L'instruction est valide si plus de la moitié on validé
        if (cpt > (getListeVerificateur().length / 2)) {
            this.setEtat("Valide");
        } else {
            this.setEtat("Non Valide");
        }


    }

    @Override
    public String toString() {
        return "Instruction{" +
                "instruction='" + instruction + '\'' +
                '}';
    }

    public void afficher() {
        System.out.println();
        System.out.println("Instruction : " + instruction);
        System.out.println(getEmetteur().toString() + "\n" + " -> " + getDestinataire().toString());
        System.out.println();

    }

    /**
     * Getter
     * Setter
     */

    public String getInstruction() {
        return instruction;
    }
}
