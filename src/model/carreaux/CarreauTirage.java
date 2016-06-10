/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.carreaux;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.TypeCarte;

public class CarreauTirage extends Carreau 
{
    private TypeCarte type;
    
    public CarreauTirage(int numero, String nom, TypeCarte type) {
        super(numero, nom);
        this.type = type;
    }

    @Override
    public Message action(Joueur j) {
        Message message = new Message();
        message.setType(TypeAction.TIRER_CARTE);
        message.setTypeCarte(type);
        return message;
    }
    
}
