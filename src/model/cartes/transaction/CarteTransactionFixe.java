package model.cartes.transaction;

import model.Message;
import model.TypeAction;
import model.TypeCarte;

public class CarteTransactionFixe extends CarteTransaction
{

    public CarteTransactionFixe(TypeCarte type, String description, int montant) 
    {
        super(type, description, montant);
    }

    @Override
    public Message actionCarte() {
        Message message = new Message();
        message.setType(TypeAction.C_TRANSACTION_FIXE);
        message.setMontantTransaction(getMontant());
        return message;
    }
}
