package model.cartes.transaction;

import model.Joueur;
import model.Message;
import model.TypeCarte;

public class CarteReparation extends CarteTransaction 
{

    public CarteReparation(TypeCarte type, String description, int montant ) 
    {
        super(type, description, montant);
    }

    @Override
    public Message actionCarte(Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
