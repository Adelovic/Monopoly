package model.carreaux.propriete;

import java.util.ArrayList;
import model.Groupe;
import model.Joueur;
import model.Message;
import model.TypeAction;

public class ProprieteConstructible extends Propriete
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
    
    private int nbMaisons;
    private int nbHotels;
    private int prixMaison;
    private int prixHotel;
    
    private ArrayList <Integer> loyers;
    
    public ProprieteConstructible(int numero, String nom, Groupe groupe, int prix, int loyerNu, ArrayList<Integer> loyers, int prixM, int prixH) 
    {
        super(numero, nom, prix);
        this.groupe = groupe;
        this.loyerNu = loyerNu;
        this.groupe.addPropriete(this);
        this.loyers = loyers;
        this.prixMaison = prixM;
        this.prixHotel = prixH;
        this.nbHotels = 0;
        this.nbMaisons = 0;
        
    }
    
    @Override
    public Message action(Joueur j)
    {
        Message message = new Message();
        message.setPropriete(this);
        message.setProprieteConstructible(this);
        message.setEstConstructible(true);
        
        // Aucun proprietaire
        if (getProprietaire() == null)
        {
           // Le joueur a assez pour acheter 
           if (j.getCash() >= getPrix()) 
           {
                message.setType(TypeAction.ACHAT);
           }
           else
           {
               message.setType(TypeAction.RIEN);
           }
        }
        // La propriete a un proprietaire, le joueur doit payer
        else if (getProprietaire() != j)
        {  
            message.setLoyer(calculLoyer());
            message.setType(TypeAction.PAYER_LOYER);
        }
        else            
        {
            // Le controleur vérifiera s'il peut construire
            message.setType(TypeAction.CONSTRUIRE);
        }
        
        return message;
    }
    
    /*
    * Loyer = Loyer nu si le joueur n'a pas toutes les propriétés du groupe
    * Sinon le loyer = 2*loyer nu
    */
    @Override
    public int calculLoyer() 
    {
        Joueur proprio = getProprietaire();
        ArrayList<ProprieteConstructible> props = groupe.getProprietes();
        
        
        for (Propriete prop : props)
        {
            if (prop.getProprietaire() != proprio)
            {
                return getLoyerNu();
            }
        }
        
        if (nbHotels == 1)
        {
            return loyers.get(4);
        }
        else if (nbMaisons > 0)
        {
            return loyers.get(nbMaisons);
        }
        else
        {
            return 2*getLoyerNu();
        }
    }
    
    public boolean maisonConstructible()    
    {
        boolean constructionPossible = true;
        Joueur proprio = getProprietaire();
        int cashJ = proprio.getCash();
        
        if (cashJ >= prixMaison) 
        {
            ArrayList<ProprieteConstructible> proprietes = groupe.getProprietes();
            for (ProprieteConstructible p : proprietes) 
            {
                if (p.getProprietaire() != proprio || nbMaisons > p.getNbMaisons() || nbMaisons == 4 || nbHotels == 1) 
                {
                    constructionPossible = false;
                }
            }
        } 
        else 
        {
            constructionPossible = false;
        }
        return constructionPossible;
    }
    
    public boolean hotelConstructible()    
    {
        boolean constructionPossible = true;
        Joueur proprio = getProprietaire();
        int cashJ = proprio.getCash();
        
        if (cashJ >= prixHotel) 
        {
            ArrayList<ProprieteConstructible> proprietes = groupe.getProprietes();
            for (ProprieteConstructible p : proprietes) 
            {
                if (p.getProprietaire() != proprio || nbMaisons != 4 || nbHotels == 1) 
                {
                    constructionPossible = false;
                }
            }
        } 
        else 
        {
            constructionPossible = false;
        }
        return constructionPossible;
    }
    
    public void construire()
    {
        if (nbMaisons < 4)
        {
            nbMaisons++;
        }
        else
        {
            nbHotels = 1;
            nbMaisons = 0;
        }
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
    
    public int getNbMaisons()
    {
        return nbMaisons;
    }
    
    public int getNbHotels()
    {
        return nbHotels;
    }
    
    public int getLoyerNu()
    {
        return loyerNu;
    }
    
    public int getPrixMaison()
    {
        return prixMaison;
    }
    
    public int getPrixHotel()
    {
        return prixHotel;
    }
    
    public ArrayList<Integer> getLoyers()
    {
        return loyers;
    }
}