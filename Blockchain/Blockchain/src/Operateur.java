
//import com.sun.org.apache.xpath.internal.operations.Bool;
/**
 * @author Mathilde
 */

import java.util.Random;
import java.util.Scanner;

public class Operateur extends Noeud {

    private int id;
    private String mdp;
    private String nom, prenom;
    private int nbTransactionTraite;
    private Boolean connecte; // True = co / False = deco


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
        this.nbTransactionTraite = 0;
        this.connecte = false;
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
        this.nbTransactionTraite = nbBlocTraite;
        this.connecte = false;
    }

    @Override
    public String toString() {
        return "\nOperateur: " + id +
                "\nmdp: " + mdp +
                "\nnom: " + nom +
                "\nprenom: " + prenom +
                "\nnbBlocTraite: " + nbTransactionTraite
                + "\nConnecte : " + connecte;

    }

    /**
     * Fonction qui affiche un operateur
     */
    public void afficher() {
        System.out.println(this.toString());
    }

    /**
     * Fonction qui permet à un operateur de valider ou non une transaction
     * t: Transaction à valider
     */

    public String demanderValidation(Transaction t) {
        String reponse = "pas de reponse";
        while (reponse != "Y" || reponse != "y" || reponse != "n" || reponse != "N") {
            System.out.println("Validez-vous la transaction t ? : " + "Operateur : " + prenom + nom);
            System.out.println("Yes : Y / No : N  :");
            Scanner sc = new Scanner(System.in);
            reponse = sc.nextLine();
            if (reponse.equals("y") || reponse.equals("Y")) {

                return "Valide";
            } else if (reponse.equals("n") || reponse.equals("N")) {

                return "Non Valide";
            } else {
                System.out.println("Reponse non conforme. Essayez encore : " + reponse);
            }
        }
        nbTransactionTraite--;
        return "pas de reponse";
    }

    //Instruction DonnerInstruction(Machine n, String Instruction)

    /**
     * Creer une Instruction afin de  traiter le cas Op -> machine
     *
     * @param m
     * @param instruction
     * @return
     */
    public Instruction donnerInstruction(Machine m, String instruction, Noeud[] listeVerificateur) {

        Instruction instruct = new Instruction(this, m, instruction, listeVerificateur);

        return instruct;
    }


    public String creerInstruction() {
        Random random = new Random();
        int rand = random.nextInt(6);
        String[] listeInstruction = {"ACCELERE", "RALENTI", "AUGMENTE LA FORCE", "DIMINUE LA FORCE", "AUGMENTE LA CONSOMATION", "DIMINUE LA CONSOMMATION"};
        return listeInstruction[rand];
    }

    /**
     * Getter
     * Setter
     */

    public int getNbTransactionTraite() {
        return nbTransactionTraite;
    }

    public int getId() {
        return id;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean getConnecte() {
        return connecte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNbTransactionTraite(int nbTransactionTraite) {
        this.nbTransactionTraite = nbTransactionTraite;
    }

    public void setConnecte(Boolean connecte) {
        this.connecte = connecte;
    }
}