package model.cartes.deplacement;

import model.Joueur;
import model.actions.Action;
import model.TypeCarte;


public class CarteDeplacementFixe extends CarteDeplacement
{

    public CarteDeplacementFixe(TypeCarte type, String description, int deplacement) 
    {
        super(type, description, deplacement);
    }


    @Override
    public Action getAction(Joueur j) 
    {
        return new ActionDeplacementFixe()
    }
    
}
