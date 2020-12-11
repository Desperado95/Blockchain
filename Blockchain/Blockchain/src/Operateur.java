
//import com.sun.org.apache.xpath.internal.operations.Bool;
/**
 * @author Mathilde
 */

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

public class Operateur extends Noeud {

    int id;
    String mdp;
    String nom, prenom;
    int nbBlocTraite;
    Boolean connecte; // True = co / False = deco

    public int getNbBlocTraite() {
        return nbBlocTraite;
    }

    /**
     * Constructeur par defaut
     * int id : identifiant ou numéro
     * String mdp : mot de passe en brut
     * String nom : nom de l'operateur
     * String prenom : prenom de l'operateur
     * int nbBlocTraité initialisé à 0 par defaut
     */
    public Operateur(int id, String mdp, String nom, String prenom) {
        this.id = id;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.nbBlocTraite = 0;
        this.connecte=false;
    }

    /**
     * Constructeur
     * int id : identifiant ou numéro
     * String mdp : mot de passe en brut
     * String nom : nom de l'operateur
     * String prenom : prenom de l'operateur
     * int nbBlocTraité initialisé à une valeur nbBlocTraite donnée
     */
    public Operateur(int id, String mdp, String nom, String prenom, int nbBlocTraite) {
        this.id = id;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.nbBlocTraite = nbBlocTraite;
        this.connecte=false;
    }

    @Override
    public String toString() {
        return "\nOperateur: " + id +
                "\nmdp: " + mdp +
                "\nnom: " + nom +
                "\nprenom: " + prenom +
                "\nnbBlocTraite: " + nbBlocTraite
                +"\nConnecte : "+connecte;

    }

    /**
     * Fonction qui affiche un operateur
     */
    public void afficher() {
        System.out.println(this.toString());
    }

    /** Fonction qui permet à un operateur de valider ou non une transaction
     * t: Transaction à valider
     */

    public String demanderValidation(Transaction t) {
        String reponse = "pas de reponse";
        while (reponse != "Y" || reponse != "y" || reponse != "n" || reponse != "N") {
            System.out.println("Validez-vous la transaction t ? : "+ "Operateur : " + prenom + nom );
            System.out.println("Yes : Y / No : N  :");
            Scanner sc = new Scanner(System.in);
            reponse = sc.nextLine();
            if (reponse.equals("y") || reponse.equals("Y")) {

                return "Valide";
            } else if (reponse.equals("n") || reponse.equals("N")) {

                return "Non Valide";
            } else {
                System.out.println("Reponse non conforme. Essayez encore : "+reponse);
            }
        }
        nbBlocTraite--;
        return "pas de reponse";
    }

    //Instruction DonnerInstruction(Machine n, String Instruction)

    /**
     * Creer une Instruction afin de  traiter le cas Op -> machine
     * @param m
     * @param instruction
     * @return
     */
    public Instruction donnerInstruction(Machine m, String instruction, Noeud[] listeVerificateur){

        Instruction instruct = new Instruction(this,m,instruction, listeVerificateur);

        return instruct;
    }


    public String creerInstruction()
    {
        Random random = new Random();
        int rand = random.nextInt(6);
        String[] listeInstruction = {"ACCELERE","RALENTI","AUGMENTE LA FORCE","DIMINUE LA FORCE","AUGMENTE LA CONSOMATION","DIMINUE LA CONSOMMATION"};
        return listeInstruction[rand];
    }

/** Main de test (definir toString pour Data et Instruction et ça marchera) */
/*
    public static void main(String[] args) {
    Operateur op = new Operateur(1,"dsfsd","Handerson","David");
    Data t = new Data();
    System.out.println(op.demanderValidation(t));
    }
*/
}