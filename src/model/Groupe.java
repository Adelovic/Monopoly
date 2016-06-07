package model;

import model.carreaux.propriete.ProprieteAConstruire;
import model.CouleurPropriete;
import java.util.ArrayList;

public class Groupe 
{
    private ArrayList<ProprieteAConstruire> proprietes;
    private CouleurPropriete couleurPropriete;
    
    /*
    * Représente un groupe de propriété. Il ne peut y en avoir qu'un seul par CouleurPropriete
    */
    public Groupe(CouleurPropriete couleurPropriete)
    {
        this.couleurPropriete = couleurPropriete;
        this.proprietes = new ArrayList<ProprieteAConstruire>();
    }
    
    public void addPropriete(ProprieteAConstruire prop)
    {
        proprietes.add(prop);
    }
    
    public ArrayList<ProprieteAConstruire> getProprietes()
    {
        return proprietes;
    }
    
    public CouleurPropriete getCouleur()
    {
        return couleurPropriete;
    }
}