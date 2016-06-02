package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Monopoly
{
    
    /*
    * Groupe de propriété, un groupe correspond à une CouleurPropriete
    */
    private HashMap<String, Groupe> groupes = new HashMap<String, Groupe>();
    
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
        groupes.put(groupe.getCouleur().toString(), groupe);
    }
    
    public void addCarreau(Carreau carreau)
    {
        carreaux.put(carreau.getNumero(), carreau);
    }
    
    public Carreau getCarreau(int numero)
    {
        return carreaux.get(numero);
    }
    
    public Groupe getGroupe(String couleur)
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

    ArrayList<Joueur> getJoueursElimines() 
    {
        return joueursElimines;
    }
}