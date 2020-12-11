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
    //Taille maximum des tableau de transaction des blocks créés
    final int tailleMAX=3;

    /***
     *Constructeur usine
     */
    public Usine() throws NoSuchAlgorithmException {

        this.blockchain = new ArrayList<Block>();
        Block genese= new Block(" 000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f",tailleMAX);
        genese.genererHash();
        blockchain.add(genese);
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

    /**
     *
     * @return un entier definissant combien d'operateur sont connectés
     */
    public int calculNbOperateurConnecte(){
        int x=0;
        //A FAIRE
        return x;
}
    /**
     *
     * @return un entier impair definissant combien il y aura d'operateur verificateur (toujours impair pour avoir une majorité)
     */
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
    /**
     *
     * @return un entier definissant combien de machine sont fiables et connectés
     */
    public int calculNbMachineConnecte(){
        //FIABLE ET CONNECTE
        int x=0;
        //A FAIRE
        return x;
    }
    /**
     *
     * @return un entier impair definissant combien il y aura de machine verificatrice (toujours impair pour avoir une majorité)
     */
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
     * @return  le tableau des operateurs verificateurs
     */
    public Operateur[] selectionnerOP(){
        Operateur[] t = new Operateur[calculNbOperateurVerificateur()];

        ArrayList<Operateur> list_opTrieeParScore = new ArrayList<Operateur>();

    //Construction de la liste op des OP connectés
        for(Map.Entry m: liste_OP.entrySet()){
            if(m.getValue() instanceof Operateur){
                if(((Operateur) m.getValue()).connecte)
                    list_opTrieeParScore.add((Operateur) m.getValue());
                }
        }
        //On trie cette liste par ordre croissant du nombre de bloc traité des OP connectés
        Collections.sort(list_opTrieeParScore, new Comparator<Operateur>() {
            @Override
            public int compare(Operateur o1, Operateur o2) {
                return Integer.compare(o1.getNbBlocTraite(),o2.getNbBlocTraite());
            }
        });
        // On ajoute autant d'OP que necessaire dans la liste des verifiacteur
        for(int i=0;i<calculNbOperateurVerificateur();i++){
            t[i]=list_opTrieeParScore.get(i);
            t[i].nbBlocTraite++;
        }

        return t;
    }


    /**
     *Construction du tableau des verificateurs
     * @return  le tableau des machine verificatrice
     */
    public Machine[] selectionnerMachine(){
        Machine[] t = new Machine[calculNbMachineVerificateur()];
        int cpt=0;

        //Construction du tableau des machines connectées et fiables

        List clef= new ArrayList(liste_Machine.keySet());
        Collections.shuffle(clef);
        for(Object o: clef){
            if(liste_Machine.get(o).connecte && liste_Machine.get(o).degreFiabilite>=2){
                t[cpt]=liste_Machine.get(o);
                cpt++;
                if(cpt==calculNbMachineVerificateur()) break;
            }
        }

        return t;
    }

    /**
     *  Verifier si le mdp hashé de l'op/m est bien celui qui est bien sa clef dans la liste
     * @param op
     * @return
     */
    public boolean authentifier(Operateur op ) throws NoSuchAlgorithmException {
        for(Map.Entry m : liste_OP.entrySet())
        {
           if(m.getKey().equals(this.convertStringToSHA256(op.mdp)))
               return true;
        }
        return false;
    }

    public boolean authentifier(Machine machine ) throws NoSuchAlgorithmException {
        for(Map.Entry m : liste_Machine.entrySet())
        {
            if(m.getKey().equals(this.convertStringToSHA256(machine.mdp)))
                return true;
        }
        return false;
    }


    /**
     * On  ajoute un block à la block chain avec toutes les transactions d'un tableau de transaction passé en paramètre
     * @param transactions : tableau de transaction
     * @throws NoSuchAlgorithmException
     */
    public void ajouterBlock(Transaction[] transactions) throws NoSuchAlgorithmException {
        Block b = new Block(blockchain.get(blockchain.size()-1).hashPresent,tailleMAX);
        assert(transactions.length<=b.listeTransaction.length);
        for(Transaction t: transactions){
            b.ajouterTransaction(t);
        }
        if(b.listeTransaction[0]!=null) {
            b.genererHash();
            blockchain.add(b);
        }
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

/**
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Operateur o1=new Operateur(1,"password","Nom1","Prenom1",2);
        Operateur o2=new Operateur(2,"password2","Nom2","Prenom2",0);
        Operateur o3=new Operateur(3,"password3","Nom3","Prenom3",5);

        Usine usine = new Usine();
        usine.liste_OP.put(usine.convertStringToSHA256(o1.mdp),o1);
        usine.liste_OP.put(usine.convertStringToSHA256(o2.mdp),o2);
        usine.liste_OP.put(usine.convertStringToSHA256(o3.mdp),o3);
        o1.connecte=true; o2.connecte=true; o3.connecte=true;

        ArrayList<Operateur> list_opTrieeParScore = new ArrayList<Operateur>();
        for(Map.Entry m: usine.liste_OP.entrySet()){
            if(m.getValue() instanceof Operateur){
                if(((Operateur) m.getValue()).connecte)
                    list_opTrieeParScore.add((Operateur) m.getValue());
            }
        }

        System.out.print("AVANT TRI");
        for(Operateur o : list_opTrieeParScore){
            System.out.print(o.toString()+"\n");
        }

        Collections.sort(list_opTrieeParScore, new Comparator<Operateur>() {
            @Override
            public int compare(Operateur o1, Operateur o2) {
                return Integer.compare(o1.getNbBlocTraite(),o2.getNbBlocTraite());
            }
        });

        System.out.print("\n Après TRI");
        for(Operateur o : list_opTrieeParScore){
            System.out.print(o.toString()+"\n");
        }
    }
*/

public static void main(String[] args) throws NoSuchAlgorithmException {
    Machine m1=new Machine(1,"mdpm1",true,true,true);
    Machine m2=new Machine(2,"mdpm2",true,true,true);
    Machine m3=new Machine(3,"mdpm3",true,true,true);

    Machine m4=new Machine(4,"mdpm4",true,true,true);
    Machine m5=new Machine(5,"mdpm5",true,true,true);
    Machine m6=new Machine(6,"mdpm6",true,false,false);

    Operateur op1 = new Operateur(1,"mdpop1","nom1","prenom1");
    Operateur op2 = new Operateur(2,"mdpop2","nom2","prenom2");
    Operateur op3 = new Operateur(3,"mdpop3","nom3","prenom3");

    Operateur op4 = new Operateur(4,"mdpop5","nom4","prenom4");
    Operateur op5 = new Operateur(5,"mdpop5","nom5","prenom5");
    Operateur op6 = new Operateur(6,"mdpop6","nom6","prenom6");

    op1.connecte = true;
    op2.connecte = true;
    op3.connecte = true;

    m1.connecte = true;
    m2.connecte = true;
    m3.connecte = true;

    Usine usine = new Usine();

    usine.liste_Machine.put(usine.convertStringToSHA256(m1.mdp),m1);
    usine.liste_Machine.put(usine.convertStringToSHA256(m2.mdp),m2);
    usine.liste_Machine.put(usine.convertStringToSHA256(m3.mdp),m3);
    usine.liste_Machine.put(usine.convertStringToSHA256(m4.mdp),m4);
    usine.liste_Machine.put(usine.convertStringToSHA256(m5.mdp),m5);
    usine.liste_Machine.put(usine.convertStringToSHA256(m6.mdp),m6);

    usine.liste_OP.put(usine.convertStringToSHA256(op1.mdp),op1);
    usine.liste_OP.put(usine.convertStringToSHA256(op2.mdp),op2);
    usine.liste_OP.put(usine.convertStringToSHA256(op3.mdp),op3);
    usine.liste_OP.put(usine.convertStringToSHA256(op4.mdp),op4);
    usine.liste_OP.put(usine.convertStringToSHA256(op5.mdp),op5);
    usine.liste_OP.put(usine.convertStringToSHA256(op6.mdp),op6);
/**
    usine.afficherListeMachine();
    usine.afficherListeOP();

    System.out.println( usine.liste_Machine.toString());
    System.out.println(usine.convertStringToSHA256(m1.toString()));
*/
    Transaction[] tableuTransaction = new Transaction[3];
    Operateur[] listeV = usine.selectionnerOP();
    tableuTransaction[0] = op1.donnerInstruction(m1,op1.creerInstruction(),usine.selectionnerOP());
    tableuTransaction[1] = op2.donnerInstruction(m2,op2.creerInstruction(),usine.selectionnerOP());
    tableuTransaction[2] = m1.EnvoyerData(op1,"temparature = 10", usine.selectionnerMachine());


    for (Transaction t:tableuTransaction) {
        if (t instanceof Instruction){
            t.verifierTransaction();
        }
        else{
            t.verifierTransaction();
        }
        System.out.println(t.etat);
    }
    usine.ajouterBlock(tableuTransaction);

    Transaction[] tableauTransaction2 = new Transaction[3];

    tableauTransaction2[0]= op1.donnerInstruction(m6,op1.creerInstruction(),usine.selectionnerOP());
    tableauTransaction2[1] = op2.donnerInstruction(m2,op2.creerInstruction(),usine.selectionnerOP());
    tableauTransaction2[2] = m6.EnvoyerData(op1,"temparature = 10", usine.selectionnerMachine());

    for (Transaction t:tableauTransaction2) {
        if (t instanceof Instruction){
            t.verifierTransaction();
        }
        else{
            t.verifierTransaction();
        }
        System.out.println(t.etat);
    }
    usine.ajouterBlock(tableuTransaction);


    for (Block b : usine.blockchain)
     {
         System.out.println(b.toString()+"\n");

    }
    //System.out.println(usine.blockchain.get(1).toString());


}

    }


