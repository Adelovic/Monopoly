package model.cartes.transaction;

import model.Joueur;
import model.Message;
import model.TypeCarte;

public class CarteAnniversaire extends CarteTransaction
{
    
    public CarteAnniversaire(TypeCarte type, String description, int montant ) 
    {
        super(type, description, montant);
    }

    @Override
    public Message actionCarte(Joueur j) {
        Message message = new Message();
    }
    
}
