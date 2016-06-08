package model.cartes.transaction;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.TypeCarte;

public class CarteReparation extends Carte 
{

    private int coutParMaison;
    private int coutParHotel;
    
    public CarteReparation(TypeCarte type, String description, int coutParMaison, int coutParHotel) 
    {
        super(type, description);
        this.coutParMaison = coutParMaison;
        this.coutParHotel = coutParHotel;
    }

    @Override
    public Message actionCarte(Joueur j) 
    {
        Message message = new Message();
        message.setType(TypeAction.C_REPARATION);
        message.setCoutMaison(coutParMaison);
        message.setCoutHotel(coutParHotel);
        return message;
    }
    
}
