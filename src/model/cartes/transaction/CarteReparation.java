package model.cartes.transaction;

import model.actions.Action;
import model.TypeCarte;

public class CarteReparation extends CarteTransaction 
{

    public CarteReparation(TypeCarte type, String description) 
    {
        super(type, description);
    }

    @Override
    public Action getAction() 
    {
        return new ActionReparation()
    }
    
}
