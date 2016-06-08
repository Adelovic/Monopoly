package model.cartes.deplacement;

import model.Joueur;
import model.Message;
import model.TypeCarte;
import model.cartes.Carte;

public class CarteDeplacement extends Carte
{
    private int deplacement;
    private boolean relatif;
    private boolean passerCaseDepart;
    
    public CarteDeplacement(TypeCarte type, String description, int deplacement, boolean relatif, boolean passerCasedepart) 
    {
        super(type, description);
        this.deplacement = deplacement;
        this.relatif = relatif;
        this.passerCaseDepart = passerCasedepart;
    }

    @Override
    public Message actionCarte(Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
