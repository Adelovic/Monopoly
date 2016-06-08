package model.carreaux.propriete;

import model.carreaux.propriete.Propriete;
import java.util.ArrayList;
import model.Groupe;
import model.Joueur;

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
    
    private int nbMaisons, nbHotels;
    private int prixMaison;
    private int prixHotel = 5 * prixMaison; 
    private ArrayList <Integer> loyers;
    
    public ProprieteAConstruire(int numero, String nom, Groupe groupe, int prix, int loyerNu, ArrayList<Integer> loyers, int prixM, int prixH) 
    {
        super(numero, nom, prix);
        this.groupe = groupe;
        this.loyerNu = loyerNu;
        this.groupe.addPropriete(this);
        this.loyers = loyers;
        this.prixMaison = prixM;
        this.prixHotel = prixH;
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
    
    public boolean etudeConstructionMaison()    
    {
        boolean constructionPossible = true;
        Joueur proprio = getProprietaire();
        int cashJ = proprio.getCash();
        if(cashJ >= prixMaison)
        {
            ArrayList<ProprieteAConstruire> proprietes = groupe.getProprietes();
            for(ProprieteAConstruire p : proprietes)
            {
                if(p.getProprietaire() != proprio || nbMaisons > p.getNbMaisons())
                {
                    constructionPossible =  false;
                }
            }
        }
        else 
        {
            constructionPossible = false;
        }
        return constructionPossible;
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

    
    // Getters et setters maisons et hotels
    public int getNbMaisons() {
        return nbMaisons;
    }

    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    
    
}