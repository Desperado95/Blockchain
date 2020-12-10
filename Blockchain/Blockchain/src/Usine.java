import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Usine {

    ArrayList<Block> blockchain;
    private Map<String, Operateur> liste_OP;
    private Map<String, Machine> liste_Machine;

    /***
     *Constructeur usine
     */
    public Usine() {
        this.blockchain = new ArrayList<Block>();
        this.liste_OP = new HashMap<String, Operateur>();
        this.liste_Machine = new HashMap<String, Machine>();
    }

    /**Fonction qui renvoie le SHA-256 d'un string s
     * Utilisable aussi sur les objets avec objet.toString() */
    public String convertStringToSHA256(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(s.getBytes(StandardCharsets.UTF_8));
        byte[] encodedhash = digest.digest();
        String hex = String.format("%064x", new BigInteger( 1, encodedhash ) );
        return hex;
    }

    /**
     * Fonction d'affichage de la liste des Machines et de leurs clefs
     */
    public void afficherListeMachine(){
        for(Map.Entry m : liste_Machine.entrySet())
        {
            System.out.print("Key : "+m.getKey() + m.getValue()+"\n\n");
        }
    }

    /**
     * Fonction d'affichage de la liste des Operateurs et de leurs clefs
     */
    public void afficherListeOP(){
        for(Map.Entry m : liste_OP.entrySet())
        {
            System.out.print("Key : "+m.getKey() + m.getValue()+"\n\n");
        }
    }

public int calculNbOperateurConnecte(){
        int x=0;
        //A FAIRE
        return x;
}

public int calculNbOperateurVerificateur(){
        int x=this.calculNbOperateurConnecte();
        x=x/3;
        if(x%2==0){
            return x+1;
        }
        else{
            return x;
        }
}

    public int calculNbMachineConnecte(){
        //FIABLE ET CONNECTE
        int x=0;
        //A FAIRE
        return x;
    }

    public int calculNbMachineVerificateur(){
        int x=this.calculNbMachineConnecte();
        x=x/3;
        if(x%2==0){
            return x+1;
        }
        else{
            return x;
        }
    }


    /**
     *Construction du tableau des verificateurs
     * @return tableau [Operateur]
     */
    public Operateur[] selectionnerOP(){
        Operateur[] t = new Operateur[calculNbOperateurVerificateur()];
        return t;
    }

    public Machine[] selectionnerMachine(){
        Machine[] t = new Machine[10];
        //Selectionner machines fiables
        return t;
    }

    /**
     *  Verifier si le mdp hashé de l'op/m est bien celui qui est bien sa clef dans la liste
     * @param op
     * @return
     */
    public boolean authentifier(Operateur op ){
        return true;
    }

    public boolean authentifier(Machine m ){
        return true;
    }

    public void ajouterBlock(){
        // Trouver le dernier block, prendre son Hash, et le mettre en tant que "HashPrecedent" dans le constructeur du nouveau block

        //ajouter le block àa la blockchain

    }


    /** Main de test
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Machine m1=new Machine(1,"mdpm1",true,true,true);
        Machine m2=new Machine(2,"mdpm2",true,true,true);

        Usine usine = new Usine();
        usine.liste_Machine.put(usine.convertStringToSHA256(m1.mdp),m1);
        usine.liste_Machine.put(usine.convertStringToSHA256(m2.mdp),m2);

        usine.afficherListeMachine();
        /*
       System.out.println( usine.liste_Machine.toString());
       System.out.println(usine.convertStringToSHA256(m1.toString()));
     }
       */


    }


