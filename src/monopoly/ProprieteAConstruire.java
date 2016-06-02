package monopoly;

import java.util.ArrayList;

public class ProprieteAConstruire extends Propriete
{
    private Groupe groupe;
    private int loyerNu;

    public ProprieteAConstruire(int numero, String nom, Groupe groupe, int prix, int loyerNu) 
    {
        super(numero, nom, prix);
        this.groupe = groupe;
        this.loyerNu = loyerNu;
        this.groupe.addPropriete(this);
    }
    
    public int calculLoyer() 
    {
        Joueur proprio = getProprietaire();
        ArrayList<ProprieteAConstruire> props = groupe.getProprietes();
        
        for (Propriete prop : props)
        {
            if (prop.getProprietaire() != proprio)
            {
                return getLoyerNu();
            }
        }
        return 2*getLoyerNu();
    }
    
    public Groupe getGroupe() 
    {
        return groupe;
    }

    @Override
    public void acheter(Joueur j) 
    {
        super.acheter(j);
        j.addProprieteAConstruire(this);
    }
    
    public int getLoyerNu()
    {
        return loyerNu;
    }
}