package model.carreaux.propriete;

import model.Joueur;


public class Compagnie extends ProprieteNonConstructible 
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
        int[] dernierDes = getDernierJoueur().getDernierDes();
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