package model.cartes.deplacement;

import model.actions.Action;
import model.TypeCarte;

public class CarteDeplacementRelatif extends CarteDeplacement
{

    public CarteDeplacementRelatif(TypeCarte type, String description, int deplacement) 
    {
        super(type, description, deplacement);
    }

    @Override
    public Action getAction() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
