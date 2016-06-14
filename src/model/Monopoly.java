package model;

import model.carreaux.propriete.ProprieteConstructible;
import model.carreaux.Carreau;
import model.carreaux.propriete.Compagnie;
import model.carreaux.propriete.Gare;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import model.cartes.Carte;

public class Monopoly
{
    
    private int joueurCourant = 0;
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
    
    // Nombre de maisons et hotels maximums
    private int maisonsDisponibles = 32;
    private int hotelsDisponibles = 12;
        
    /*
    * Calcule le nouveau carreau à partir des deux dés
    * et y déplace le joueur
    */    
    public boolean deplacerJoueur(Joueur j, int montant)
    {
        Carreau carr = j.getPositionCourante();
        
        int carrPos = carr.getNumero();
        int prochainCarreau = carrPos+montant;
        
        boolean passeCaseDepart = prochainCarreau > carreaux.size() || prochainCarreau < 1;
        Carreau nouveauCarr = getCarreau(prochainCarreau > carreaux.size() ? prochainCarreau%carreaux.size() : prochainCarreau < 1 ? carreaux.size()+(prochainCarreau-1) : prochainCarreau);
        
        nouveauCarr.setDernierJoueur(j);
        j.setPositionCourante(nouveauCarr);
        
        return passeCaseDepart;
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

    /** Lance deux dés et les renvoie sous forme de liste **/
    public int[] lancerDes()
    { 
        Random random = new Random();
        return new int[] { random.nextInt(6)+1, random.nextInt(6)+1 };
    }
    
    
    public Joueur getJoueurCourant()
    {
        return getJoueurs().get(joueurCourant);
    }
    
    public Joueur prochainJoueur()
    {
        joueurCourant++;
        if (joueurCourant >= getJoueurs().size())
        {
            joueurCourant = 0;
        }
        
        return getJoueurCourant();
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
        for (ProprieteConstructible prop : joueur.getProprietes())
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
       
    public void actualiserProprietesConstructibles(Joueur joueur)
    {
        final ArrayList<ProprieteConstructible> proprietesConstructibles = new ArrayList<ProprieteConstructible>();
        
        for (ProprieteConstructible prop : joueur.getProprietes())
        {
            if (prop.maisonConstructible() && maisonsDisponibles > 0 || prop.hotelConstructible() && hotelsDisponibles > 0)
            {
                proprietesConstructibles.add(prop);
            }
        }
        joueur.setProprietesConstructibles(proprietesConstructibles);
    }
    
    public ArrayList<Joueur> getJoueurs()
    {
        return joueurs;
    }

    public ArrayList<Joueur> getJoueursElimines() 
    {
        return joueursElimines;
    }
    
    public void addCarte(TypeCarte type, Carte carte)
    {
        switch(type)
        {
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
        Carte carte;
        ArrayList<Carte> cartesTmp = new ArrayList();
        
        if (type == TypeCarte.CHANCE)
        {
            carte = cartesChance.get(cartesChance.size()-1);
            cartesTmp.add(carte);
            for( Carte c : cartesChance)
            {
                if (c != carte)
                {
                    cartesTmp.add(c);
                }
                cartesChance = cartesTmp;  
            }
            
        }
        else 
        {
            carte = cartesCommunaute.get(cartesCommunaute.size()-1);
            cartesTmp.add(carte);
            for( Carte c : cartesCommunaute)
            {
                if (c != carte)
                {
                    cartesTmp.add(c);
                }
                cartesCommunaute = cartesTmp;  
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
    
    public void liberer(Joueur j)            
    {
        j.setEnPrison(false);    
        j.setTourPrison(0);
    }
    
    public int getMaisonsDisponibles()
    {
        return maisonsDisponibles;
    }
    
    public int getHotelsDisponibles()
    {
        return hotelsDisponibles;
    }
        
    public void enleverMaison()
    {
        maisonsDisponibles++;
    }
    
    public void ajouterMaison()
    {
        maisonsDisponibles--;
    }
    
            
    public void enleverHotel()
    {
        hotelsDisponibles++;
    }
    
    public void ajouterHotel()
    {
        hotelsDisponibles--;
    }
  
    
}