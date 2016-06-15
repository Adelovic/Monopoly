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
public class CarreauPrison extends Carreau{

    public CarreauPrison(int numero, String nom) {
        super(numero, nom);
    }

    @Override
    public Message action(Joueur j) {
        Message message = new Message();
        message.setJoueur(j);
        message.setType(TypeAction.ALLER_PRISON);
        return message;
    }
    
}
