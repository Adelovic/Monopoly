package model;

import model.Joueur;
import model.Propriete;

public class ActionAchat extends Action 
{
    private Propriete propriete;
    
    public ActionAchat(Joueur j, Propriete propriete) 
    {
        super(j);
        this.propriete = propriete;
    }
    
    /*
    * Le joueur tombe sur une case propriété
    * qu'il a la possibilité d'acheter
    */
    @Override
    public ResultatAction faireAction(boolean reponseJ) 
    {
        if (reponseJ)
        {
            int prix = propriete.getPrix();
            joueur.removeCash(prix);
            propriete.acheter(joueur);
            return new ResultatAction(true, joueur.getNom() + " a acheté " + propriete.getNom() + " pour " + prix + "$");
        }
        else
        {
            return new ResultatAction(true, joueur.getNom() + " n'a pas souhaité acheter la case " + propriete.getNom());
        }
    }
    
    @Override
    public String getMessage() 
    {
        return joueur.getNom() + " est tombé sur la case " + propriete.getNom() + " et a la possibilité de l'acheter pour " + propriete.getPrix() + "$";
    }
    
    @Override
    public boolean entraineDemande() 
    {
        return true;
    }
}