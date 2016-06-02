package monopoly;

import java.util.ArrayList;

public class Groupe 
{
    private ArrayList<ProprieteAConstruire> proprietes;
    private CouleurPropriete couleurPropriete;
    
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