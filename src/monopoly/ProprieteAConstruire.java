package monopoly;

import java.util.ArrayList;

public class ProprieteAConstruire extends Propriete
{
    /*
    * Représente le groupe de couleur auquel appartient la propriété
    */
    private Groupe groupe;
    
    /*
    * Loyer de base de la propriété, sans ajouts supplémentaires(Maison etc..)
    */
    private int loyerNu;

    /*
    * Proprieté construisible (ajouts de maisons etc..)
    */
    public ProprieteAConstruire(int numero, String nom, Groupe groupe, int prix, int loyerNu) 
    {
        super(numero, nom, prix);
        this.groupe = groupe;
        this.loyerNu = loyerNu;
        this.groupe.addPropriete(this);
    }
    
    /*
    * Loyer = Loyer nu si le joueur n'a pas toutes les propriétés du groupe
    * Sinon le loyer = 2*loyer nu
    */
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