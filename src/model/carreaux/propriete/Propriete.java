package model.carreaux.propriete;

import model.Joueur;
import model.Message;
import model.TypeAction;
import model.carreaux.Carreau;


public abstract class Propriete extends Carreau 
{
    
    /*
    * Dernier joueur a être passé sur la propriété
    * Utilisé pour calculLoyer de certaines propriétés pour récupérer les dés
    */
    
    private int prix;
    private Joueur proprietaire;
    
    public Propriete(int numero, String nom, int prix)
    {
        super(numero, nom);
        this.prix = prix;
    }
    
    /*
    * Calcule le loyer en fonction des différents types de propriété
    * Chaque type de proprieté calcule le loyer d'une façon différente
    */
    public abstract int calculLoyer();
    
    public void acheter(Joueur j)
    {
        this.setProprietaire(j);
    }
    
    /*
    * Renvoie les informations nécessaires au controleur
    * Achat si la propriété n'a pas de propriétaire et que le joueur a suffisamment d'argent
    * Sinon si proprietaire, payer le loyer
    */
    @Override
    public abstract Message action(Joueur j);
    
    
    public int getPrix() 
    {
        return this.prix;
    }
    
    public Joueur getProprietaire() 
    {
        return proprietaire;
    }
    
    public void setProprietaire(Joueur joueur)
    {
        this.proprietaire = joueur;
    }
}