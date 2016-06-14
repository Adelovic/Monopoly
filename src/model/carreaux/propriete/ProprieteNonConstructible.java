/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.carreaux.propriete;

import model.Joueur;
import model.Message;
import model.TypeAction;

/**
 *
 * @author raffya
 */
public abstract class ProprieteNonConstructible extends Propriete 
{

    public ProprieteNonConstructible(int numero, String nom, int prix) 
    {
        super(numero, nom, prix);
    }

    @Override
    public abstract int calculLoyer();
    
    @Override
    public Message action(Joueur j)
    {
        Message message = new Message();
        message.setPropriete(this);
        
        // Aucun proprietaire
        if (getProprietaire() == null)
        {
           // Le joueur a assez pour acheter 
           if (j.getCash() >= getPrix()) 
           {
                message.setType(TypeAction.ACHAT);
           }
           else
           {
               message.setType(TypeAction.PROPRIETE);
           }
        }
        // La propriete a un proprietaire, le joueur doit payer
        else if (getProprietaire() != j)
        {  
            message.setLoyer(calculLoyer());
            message.setType(TypeAction.PAYER_LOYER);
        }
        else
        {
            message.setType(TypeAction.PROPRIETE);
        }
        
        return message;
    }
    
}
