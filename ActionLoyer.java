package backup;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.carreaux.propriete.Propriete;

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
    public Message faireAction(boolean reponseJ) 
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
        
        Message message = new Message();
        message.setType(TypeAction.PAYER_LOYER);
        message.setJoueur(joueur);
        message.setProprietaire(proprio);
        message.setLoyer(loyer);
        return message;
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
    
    @Override
    public boolean entraineNouveauCoup()
    {
        return false;
    }

    @Override
    public boolean entraineTirage() 
    {
        return false;
    }
}