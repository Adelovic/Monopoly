package backup;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.TypeCarte;

public class ActionTirage extends Action
{
    private final TypeCarte typeCarte;
    
    public ActionTirage(Joueur j, TypeCarte typeCarte) 
    {
        super(j);
        this.typeCarte = typeCarte;
    }

    @Override
    public Message faireAction(boolean reponseJ)
    {
        Message message = new Message();
        message.setType(TypeAction.LANCER_DES);
        message.setTypeCarte(typeCarte);
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
        return true;
    }
    
}
