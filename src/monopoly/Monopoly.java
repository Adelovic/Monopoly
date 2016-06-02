package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Monopoly
{
    private HashMap<String, Groupe> groupes = new HashMap<String, Groupe>();
    private HashMap<Integer, Carreau> carreaux = new HashMap<Integer, Carreau>();
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Joueur> joueursElimines = new ArrayList<Joueur>();
    
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
    
    public void removeJoueur(Joueur joueur)
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
    }
        
    public ArrayList<Joueur> getJoueurs()
    {
        return joueurs;
    }
    
    public void addJoueurElimine(Joueur j)
    {
        joueursElimines.add(j);
    }

    ArrayList<Joueur> getJoueursElimines() 
    {
        return joueursElimines;
    }
}