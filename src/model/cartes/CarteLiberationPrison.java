/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cartes;

import model.Message;
import model.TypeAction;
import model.TypeCarte;

/**
 *
 * @author raffya
 */
public class CarteLiberationPrison extends Carte 
{
    
    public CarteLiberationPrison(TypeCarte type, String description) 
    {
        super(type, description);
    }

    @Override
    public Message actionCarte() 
    {
        Message message = new Message();
        message.setType(TypeAction.C_LIBERATION);
        message.setCarteLiberation(this);
        return message;
    }
}
