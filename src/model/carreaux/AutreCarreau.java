package model.carreaux;

import model.Joueur;
import model.actions.Action;
import model.carreaux.Carreau;

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
    public Action action(Joueur j) 
    {
        return null;
    }
}