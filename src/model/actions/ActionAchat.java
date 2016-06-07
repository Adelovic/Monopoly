package model.actions;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.carreaux.propriete.Propriete;

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
    public Message faireAction(boolean reponseJ) 
    {
        int prix = propriete.getPrix();
        
        Message message = new Message();
        message.setJoueur(joueur);
        message.setProprietaire(propriete.getProprietaire());
        message.setPrix(prix);
        
        if (reponseJ)
        {
            joueur.removeCash(prix);
            propriete.acheter(joueur);
            
            message.setType(TypeAction.ACHAT);
            return message;
        }
        else
        {
            message.setType(TypeAction.REFUS_ACHAT);
            return message;
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
    
    @Override
    public boolean entraineTirage() 
    {
        return false;
    }
    
    @Override
    public boolean entraineNouveauCoup()
    {
        return false;
    }
}