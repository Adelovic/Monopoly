package model.carreaux;

import model.Joueur;
import model.Message;
import model.TypeAction;

public class AutreCarreau extends Carreau 
{

    /*
    * Pas supporté pour le moment.
    * Représente les autres cases vides
    */
    public AutreCarreau(int numero, String nomCarreau) 
    {
        super(numero, nomCarreau);
    }

    /*
    * Renvoie null, aucune action n'est renvoyée
    * car il n'y a aucune action sur les cases vides
    */
    @Override
    public Message action(Joueur j) 
    {
        Message message = new Message();
        message.setType(TypeAction.RIEN);
        return message;
    }
}