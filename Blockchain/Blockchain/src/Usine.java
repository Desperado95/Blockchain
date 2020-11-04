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

    /** Main de test*/
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Machine m1=new Machine(1,"mdpm1",true,true,true);
        Machine m2=new Machine(2,"mdpm2",true,true,true);

        Usine usine = new Usine();
        usine.liste_Machine.put(usine.convertStringToSHA256(m1.mdp),m1);
        usine.liste_Machine.put(usine.convertStringToSHA256(m2.mdp),m2);

       System.out.println( usine.liste_Machine.toString());
       System.out.println(usine.convertStringToSHA256(m1.toString()));
        System.out.print("test pull");
    }
// TEST THEO
}
