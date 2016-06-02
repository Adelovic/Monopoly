package monopoly;

public class Gare extends Propriete
{
    
    private static final int LOYER_GARE = 25;
    
    public Gare(int numero, String nom, int prix) 
    {
        super(numero, nom, prix);
    }
    public int calculLoyer()
    {
        int nbGares = getProprietaire().getNbGares();
        return LOYER_GARE * nbGares;
    }
    
    public void acheter(Joueur j)
    {
        super.acheter(j);
        j.addGare(this);
    }
}