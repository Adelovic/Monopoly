package model;

import model.carreaux.propriete.ProprieteAConstruire;
import model.carreaux.Carreau;
import model.carreaux.propriete.Compagnie;
import model.carreaux.propriete.Gare;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import model.cartes.Carte;

public class Monopoly
{
    
    /*
    * Groupe de propriété, un groupe correspond à une CouleurPropriete
    */
    private HashMap<CouleurPropriete, Groupe> groupes = new HashMap<CouleurPropriete, Groupe>();
    
    /*
    * Carreaux du jeu
    */
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    
    /*
    * Représente les joueurs encore en jeu
    * Ceux qui sont eliminés sont déplacés dans joueursElimines
    */
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    
    /*
    * Représente les joueurs éliminés
    */
    private ArrayList<Joueur> joueursElimines = new ArrayList<Joueur>();
    
    // Piles de cartes
    private ArrayList <Carte> cartesChance = new ArrayList<Carte>();
    private ArrayList <Carte> cartesCommunaute = new ArrayList<Carte>();
    
    
    /*
    * Calcule le nouveau carreau à partir des deux dés
    * et y déplace le joueur
    */
    public void avancerJoueur(Joueur j, int[] totalDes)
    {
        Carreau carr = j.getPositionCourante();
        int carrPos = carr.getNumero();
        int prochainCarreau = carrPos+totalDes[0]+totalDes[1];
        Carreau nouveauCarr = getCarreau(prochainCarreau > carreaux.size() ? prochainCarreau%carreaux.size() : prochainCarreau);
        
        j.setPositionCourante(nouveauCarr);
    }
    
    public void addGroupe(Groupe groupe)
    {
        groupes.put(groupe.getCouleur(), groupe);
    }
    
    public void addCarreau(Carreau carreau)
    {
        carreaux.put(carreau.getNumero(), carreau);
    }
    
    public Carreau getCarreau(int numero)
    {
        return carreaux.get(numero);
    }
    
    public Groupe getGroupe(CouleurPropriete couleur)
    {
        return groupes.get(couleur);
    }
    
    public void addJoueur(Joueur joueur)
    {
        joueurs.add(joueur);
        joueur.setPositionCourante(carreaux.get(1));
    }
    
    /*
    * Supprime le joueur des joueurs encore en course
    * et l'ajoute aux joueurs éliminés
    * Supprime également ces titres de propriétés
    */
    public void eliminerJoueur(Joueur joueur)
    {
        joueurs.remove(joueur);
        // Supprimer les propriétés à construire
        for (ProprieteAConstruire prop : joueur.getProprietes())
        {
            prop.setProprietaire(null);
        }
        for (Gare gare : joueur.getGares())
        {
            gare.setProprietaire(null);
        }
        for (Compagnie compagnie : joueur.getCompagnies())
        {
            compagnie.setProprietaire(null);
        }
        
        joueur.getProprietes().clear();
        joueur.getGares().clear();
        joueur.getCompagnies().clear();
        joueursElimines.add(joueur);
    }
        
    public ArrayList<Joueur> getJoueurs()
    {
        return joueurs;
    }

    public ArrayList<Joueur> getJoueursElimines() 
    {
        return joueursElimines;
    }
    
    public void addCarte (TypeCarte type, Carte carte)
    {
        switch(type){
            case CHANCE:
                cartesChance.add(carte);
                break;
            case COMMUNAUTE:
                cartesCommunaute.add(carte);
                break;
        }
    }
    
    public Carte tirerCarte( TypeCarte type )
    {
        Carte carte = null;
        if(type == TypeCarte.CHANCE)
        {
            ArrayList<Carte> cartesChanceTmp = new ArrayList();
            carte = cartesChance.get(cartesChance.size()-1);
            cartesChanceTmp.add(carte);
            for( Carte c : cartesChance)
            {
                if (c != carte)
                {
                    cartesChanceTmp.add(c);
                }
                cartesChance = cartesChanceTmp;  
            }
            
        }
        else 
        {
            ArrayList<Carte> cartesCommunauteTmp = new ArrayList();
            carte = cartesCommunaute.get(cartesCommunaute.size()-1);
            cartesCommunauteTmp.add(carte);
            for( Carte c : cartesCommunaute)
            {
                if (c != carte)
                {
                    cartesCommunauteTmp.add(c);
                }
                cartesCommunaute = cartesCommunauteTmp;  
            }
        }
        return carte;
    }
    
    public void  melangerCartes() 
    {
        Collections.shuffle(cartesChance);
        Collections.shuffle(cartesCommunaute);
    }
    
    public void emprisonner(Joueur j)
    {
        j.setPositionCourante(carreaux.get(11));
        j.setEnPrison(true);    
    }
  
    
}