package model.cartes.transaction;

import model.Joueur;
import model.Message;
import model.TypeCarte;
import model.cartes.Carte;

public abstract class CarteTransaction extends Carte 
{
    private int montant;
    
    public CarteTransaction(TypeCarte type, String description, int montant) 
    {
        super(type, description);
        this.montant = montant;
    }
    
    @Override
    public abstract Message actionCarte();
    
    public int getMontant()
    {
        return montant;
    }
    
}
