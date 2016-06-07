/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cartes;

import model.Joueur;
import model.TypeCarte;
import model.actions.Action;

/**
 *
 * @author raffya
 */
public class CartePrison extends Carte
{

    public CartePrison(TypeCarte type, String description) 
    {
        super(type, description);
    }

    @Override
    public Action getAction(Joueur j) 
    {
        return new ActionPrison(j); //To change body of generated methods, choose Tools | Templates.
    }
    
}
