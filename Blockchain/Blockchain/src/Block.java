import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    //Liste des transactions VALIDEES au préalable
    Transaction[] listeTransaction;
    String hashPrecedent;
    String hashPresent;
    static int id; //(Attribut partagé entre tout les blocks pour la numérotation)


    public Block(String hashPrecedent) {
        //3 transactions/block
        this.listeTransaction = new Transaction[3];

        //recupere via le dernier id de la liste de block dans l'usine
        this.hashPrecedent = hashPrecedent;
        id++;
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
    public boolean ajouterTransaction(Transaction t) throws NoSuchAlgorithmException {
        // On verifie que le hash est null, sinon cela veut dire que le bloc est censé être fermé
        if (this.hashPresent == null) {
            if (t.etat.equals("Valide")) {
                for (int i = 0; i < listeTransaction.length; i++) {
                    if (listeTransaction[i] == null) {
                        listeTransaction[i] = t;
                        if(i==9){
                            this.genererHash();
                        }
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
        while (listeTransaction[i] != null && i < listeTransaction.length) {
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
        Block b = new Block("none");
        Machine m1 = new Machine(1,"mdp",true,true,true);
        Machine m2 = new Machine(2,"mdp2",true,true,true);
        Machine[] listeMachine=new Machine[2];
        listeMachine[0]=m1;
        listeMachine[1]=m2;
        Data d = new Data(m1,m2,"a detecte une PANNE",listeMachine);
        d.verifierTransaction();
        System.out.println("ajout d :"+b.ajouterTransaction(d));
        //System.out.println(b.toString());
        b.genererHash();
        System.out.println(b.hashPresent);
    }


}