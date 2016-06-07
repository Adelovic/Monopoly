package model.cartes.transaction;

import model.TypeCarte;
import model.cartes.Carte;

public abstract class CarteTransaction extends Carte 
{
    
    public CarteTransaction(TypeCarte type, String description) 
    {
        super(type, description);
    }
    
}
