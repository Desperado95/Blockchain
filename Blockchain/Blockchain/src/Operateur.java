import java.util.Scanner;

public class Operateur extends Noeud {

    int id;
    String mdp;
    String nom, prenom;
    int nbBlocTraite;

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
    }

    @Override
    public String toString() {
        return "\nOperateur: " + id +
                "\nmdp: " + mdp +
                "\nnom: " + nom +
                "\nprenom: " + prenom +
                "\nnbBlocTraite: " + nbBlocTraite;

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
            System.out.println("Validez-vous la transaction t ? : "+toString());
            System.out.println("Yes : Y / No : N  :");
            Scanner sc = new Scanner(System.in);
            reponse = sc.nextLine();
            if (reponse.equals("y") || reponse.equals("Y")) {
                return "valide";
            } else if (reponse.equals("n") || reponse.equals("N")) {
                return "non valide";
            } else {
                System.out.println("Reponse non conforme. Essayez encore : "+reponse);
            }
        }
        return "pas de reponse";
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