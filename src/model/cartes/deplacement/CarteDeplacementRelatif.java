/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cartes.deplacement;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.TypeCarte;

/**
 *
 * @author raffya
 */
public class CarteDeplacementRelatif extends CarteDeplacement {
    
    public CarteDeplacementRelatif(TypeCarte type, String description, int deplacement) {
        super(type, description, deplacement);
        
    }

    @Override
    public Message actionCarte() 
    {
        Message message = new Message();
        message.setType(TypeAction.C_DEPLACEMENT_RELATIF);
        message.setDeplacement(getDeplacement());
        return message;
    }
    
}
