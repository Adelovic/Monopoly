/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cartes.deplacement;

import model.Message;
import model.TypeAction;
import model.TypeCarte;

/**
 *
 * @author raffya
 */
public class CarteDeplacementAbsolu extends CarteDeplacement
{
    
    private final boolean passerCaseDepart;
    
    public CarteDeplacementAbsolu(TypeCarte type, String description, int deplacement, boolean passerCaseDepart) 
    {
        super(type, description, deplacement);
        this.passerCaseDepart = passerCaseDepart;
    }

    @Override
    public Message actionCarte() 
    {
        Message message = new Message();
        message.setType(TypeAction.C_DEPLACEMENT_ABSOLU);
        message.setDeplacement(getDeplacement());
        message.setPasserCaseDepart(passerCaseDepart);
        return message;
    }
    
    
    
}
