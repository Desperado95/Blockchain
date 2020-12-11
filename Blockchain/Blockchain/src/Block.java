import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Théo
 */

public class Block {

    //Liste des transactions VALIDEES au préalable
    private Transaction[] listeTransaction;
    private String hashPrecedent;
    private String hashPresent;
    private static int num = 0; //(Attribut partagé entre tout les blocks pour la numérotation)
    private int id;

    public Block(String hashPrecedent, int taille) {
        //3 transactions/block
        this.listeTransaction = new Transaction[taille];

        //recupere via le dernier id de la liste de block dans l'usine
        this.hashPrecedent = hashPrecedent;
        id = num++;
    }

    public String convertStringToSHA256(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(s.getBytes(StandardCharsets.UTF_8));
        byte[] encodedhash = digest.digest();
        String hex = String.format("%064x", new BigInteger(1, encodedhash));
        return hex;
    }

    /**
     * @param t : transaction à ajouter
     * @return true si la transaction à pu être ajoutée, sinon false (déjà pleine ou t non valide ou Bloc cloturé)
     */
    public boolean ajouterTransaction(Transaction t) {
        // On verifie que le hash est null, sinon cela veut dire que le bloc est censé être fermé
        if (this.hashPresent == null) {
            if (t.getEtat().equals("Valide")) {
                for (int i = 0; i < listeTransaction.length; i++) {
                    if (listeTransaction[i] == null) {
                        listeTransaction[i] = t;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return un String correspondant au bloc sans son hash present
     */
    public String toString() {
        String res = "Id Block: " + id +
                "\nTransactions : \n";
        int i = 0;
        while (listeTransaction[i] != null && i < listeTransaction.length - 1) {
            res += listeTransaction[i].toString();
            i++;
        }
        res += "\n HashPrecedent: " + hashPrecedent;
        return res;
    }

    /**
     * Fontion qui genère le hash de ce bloc via son toString()
     */
    public void genererHash() throws NoSuchAlgorithmException {
        this.hashPresent = this.convertStringToSHA256(this.toString());
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        Block b = new Block("none", 3);
        Machine m1 = new Machine(1, "mdp", true, true, true);
        Machine m2 = new Machine(2, "mdp2", true, true, true);
        Machine[] listeMachine = new Machine[2];
        listeMachine[0] = m1;
        listeMachine[1] = m2;
        Data d = new Data(m1, m2, "a detecte une PANNE", listeMachine);
        d.verifierTransaction();
        System.out.println("ajout d :" + b.ajouterTransaction(d));
        //System.out.println(b.toString());
        b.genererHash();
        System.out.println(b.hashPresent);
    }

    /**
     * Getter
     * Setter
     */
    public Transaction[] getListeTransaction() {
        return listeTransaction;
    }

    public void setListeTransaction(Transaction[] listeTransaction) {
        this.listeTransaction = listeTransaction;
    }

    public String getHashPrecedent() {
        return hashPrecedent;
    }

    public void setHashPrecedent(String hashPrecedent) {
        this.hashPrecedent = hashPrecedent;
    }

    public String getHashPresent() {
        return hashPresent;
    }

    public void setHashPresent(String hashPresent) {
        this.hashPresent = hashPresent;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Block.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}