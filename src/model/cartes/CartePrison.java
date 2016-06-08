/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cartes;

import model.Joueur;
import model.Message;
import model.TypeCarte;

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
    public Message actionCarte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}