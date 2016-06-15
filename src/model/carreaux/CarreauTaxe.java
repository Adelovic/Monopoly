/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.carreaux;

import model.Joueur;
import model.Message;
import model.TypeAction;

/**
 *
 * @author raffya
 */
public class CarreauTaxe extends Carreau {
    int montant;
    
    public CarreauTaxe(int numero, String nom, int montant) {
        super(numero, nom);
        this.montant = montant;
        
    }

    @Override
    public Message action(Joueur j) {
        Message message = new Message();
        message.setCarreau(this);
        message.setType(TypeAction.C_TRANSACTION_FIXE);
        message.setMontantTransaction(montant);
        return message;
    }

   
    
}
