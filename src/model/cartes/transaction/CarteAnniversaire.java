package model.cartes.transaction;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.TypeCarte;
import model.cartes.Carte;

public class CarteAnniversaire extends Carte
{
    
    public CarteAnniversaire(TypeCarte type, String description) 
    {
        super(type, description);
    }

    @Override
    public Message actionCarte() 
    {
        Message message = new Message();
        message.setType(TypeAction.C_ANNIVERSAIRE);
        return message;
    }
    
}
