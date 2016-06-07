package model.actions;

import model.Joueur;
import model.Message;
import model.TypeAction;


public class ActionPrison extends Action
{

    public ActionPrison(Joueur j) 
    {
        super(j);
    }

    @Override
    public Message faireAction(boolean reponseJ) 
    {
        Message message = new Message();
        message.setType(TypeAction.PRISON);
        message.setJoueur(joueur);
        return message;
    }

    @Override
    public String getMessage() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
