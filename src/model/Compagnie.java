package model;

public class Compagnie extends Propriete 
{
    
    public Compagnie(int numero, String nom, int prix) 
    {
        super(numero, nom, prix);
    }
    
    /*
    * Loyer = 4 fois le nombre de dés si le propriétaire possède une compagnie
    * Sinon Loyer = 10 fois le nombre de dés
    */
    @Override
    public int calculLoyer() 
    {
        Joueur proprio = getProprietaire();
        int nbCompagnies = proprio.getNbCompagnies();
        int[] dernierDes = dernierJoueur.getDernierDes();
        int totalDes = dernierDes[0]+dernierDes[1];
        
        return nbCompagnies == 1 ? 4*totalDes : 10*totalDes;
    }
    
    @Override
    public void acheter(Joueur j) 
    {
        super.acheter(j);
        j.addCompagnie(this);
    }
}