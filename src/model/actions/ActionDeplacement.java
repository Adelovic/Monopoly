/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions;

import model.Joueur;
import model.Message;

/**
 *
 * @author raffya
 */
public class ActionDeplacement extends Action 
{

    public ActionDeplacement(Joueur j) 
    {
        super(j);
    }

    @Override
    public Message faireAction(boolean reponseJ) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean entraineDemande() 
    {
        return false;
    }

    @Override
    public boolean entraineNouveauCoup() 
    {
        return true;
    }

    @Override
    public boolean entraineTirage() 
    {
        return false;
    }
    
}
