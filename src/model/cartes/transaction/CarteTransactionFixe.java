package model.cartes.transaction;

import model.actions.Action;
import model.TypeCarte;

public class CarteTransactionFixe extends CarteTransaction
{

    public CarteTransactionFixe(TypeCarte type, String description) 
    {
        super(type, description);
    }

    
    @Override
    public Action getAction() 
    {
        return 
    }
    
}
