package model;

import model.Joueur;
import model.Propriete;

public class ActionLoyer extends Action 
{
    /*
    * Propriété
    */
    private Propriete propriete;
    
    public ActionLoyer(Joueur j, Propriete propriete) 
    {
        super(j);
        this.propriete = propriete;
    }
    
    /*
    * Le joueur tombe sur une case propriété
    * qui est déjà achetée, il doit payer le loyer au propriétaire
    */
    @Override
    public ResultatAction faireAction(boolean reponseJ) 
    {
        int loyer = propriete.calculLoyer();
        Joueur proprio = propriete.getProprietaire();
        
        int cashJ = joueur.getCash();
        boolean success = cashJ > loyer;
        if (success)
        {
           joueur.removeCash(loyer);
           proprio.addCash(loyer);
           
        }
        else
        {
            joueur.removeCash(cashJ);
            proprio.addCash(cashJ);
        }
        
        return new ResultatAction(success,joueur.getNom() + " a payé " + (success ? loyer : cashJ) + "$ de loyer à " + proprio.getNom());
    }
    
    @Override
    public String getMessage() 
    {
        return joueur.getNom() + " est tombé sur la case " + propriete.getNom() + " et doit payer le loyer à " + propriete.getProprietaire().getNom();
    }
    
    @Override
    public boolean entraineDemande() 
    {
        return false;
    }
    
    
}