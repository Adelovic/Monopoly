package model.cartes.transaction;

import model.TypeCarte;
import model.cartes.Carte;

public abstract class CarteTransaction extends Carte 
{
    int montant;
    public CarteTransaction(TypeCarte type, String description, int montant) 
    {
        super(type, description);
        this.montant = montant;
    }
    
}
