import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    //Liste des transaction VALIDEES au préalable
    Transaction[] listeTransaction;
    String hashPrecedent;
    String hashPresent;
    static int id; //(Attribut partagé entre tout les blocks pour la numérotation)


    public Block( String hashPrecedent) {
        //10 transactions/block
        this.listeTransaction = new Transaction[10];
        //recupere via le dernier id de la liste de block dans l'usine
        this.hashPrecedent = hashPrecedent;
        id++;
    }

    public String convertStringToSHA256(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(s.getBytes(StandardCharsets.UTF_8));
        byte[] encodedhash = digest.digest();
        String hex = String.format("%064x", new BigInteger( 1, encodedhash ) );
        return hex;
    }

// Redefinir block.tostring pour qu'il ne comprenne pas le hashPresent
//ajouterTransaction(Transaction t) -> transaction doit être validée (état = valide) sinon on l'ajoute pas
//genererHash() -> On verifie que toutes les transactions sont validées; -> Créer un autre block dans Usine
}
