package model.cartes.deplacement;

import model.Joueur;
import model.Message;
import model.TypeCarte;
import model.cartes.Carte;

public abstract class CarteDeplacement extends Carte
{
    private int deplacement;

    
    public CarteDeplacement(TypeCarte type, String description, int deplacement) 
    {
        super(type, description);
        this.deplacement = deplacement;
    }

    @Override
    public abstract Message actionCarte(Joueur j);
    
    public int getDeplacement()
    {
        return deplacement;
    }
    
    
    
    
    
}
