package model;

import model.carreaux.propriete.ProprieteConstructible;
import model.CouleurPropriete;
import java.util.ArrayList;

public class Groupe 
{
    private ArrayList<ProprieteConstructible> proprietes;
    private CouleurPropriete couleurPropriete;
    
    /*
    * Représente un groupe de propriété. Il ne peut y en avoir qu'un seul par CouleurPropriete
    */
    public Groupe(CouleurPropriete couleurPropriete)
    {
        this.couleurPropriete = couleurPropriete;
        this.proprietes = new ArrayList<ProprieteConstructible>();
    }
    
    public void addPropriete(ProprieteConstructible prop)
    {
        proprietes.add(prop);
    }
    
    public ArrayList<ProprieteConstructible> getProprietes()
    {
        return proprietes;
    }
    
    public CouleurPropriete getCouleur()
    {
        return couleurPropriete;
    }
}