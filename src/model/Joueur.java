package model;

import model.Carreau;
import model.Compagnie;
import model.Gare;
import java.util.ArrayList;
public class Joueur 
{
    private String nomJoueur;
    private int cash = 1500;
    private int[] dernierDes;
    private Carreau positionCourante;
    private ArrayList<Gare> gares = new ArrayList<Gare>();
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();
    private ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
    
    public Joueur(String nomJoueur)
    {
        this.nomJoueur = nomJoueur;
    }
    
    
    //Getters
    public ArrayList<Gare> getGares(){ return gares; }

    public ArrayList<ProprieteAConstruire> getProprietes(){ return proprietes;}

    public ArrayList<Compagnie> getCompagnies() {return compagnies;}
         
    public String getNom() { return nomJoueur; }
    
    public int getNbGares() { return gares.size(); }
    
    public int getNbCompagnies()  { return compagnies.size(); }
    
    public int getNbProprieteAConstruire() { return proprietes.size(); }
    
    public int getCash() { return this.cash; }
    
    public int[] getDernierDes() { return this.dernierDes; }
    
    
    public void setPositionCourante(Carreau carreau) 
    {
        this.positionCourante = carreau;
    }
    
    public void setDernierDes(int[] dernierDes) 
    {
        this.dernierDes = dernierDes;
    }
    
    public Carreau getPositionCourante() 
    {
        return this.positionCourante;
    }
    
    public void addCash(int cash) 
    {
        this.cash += cash;
    }
    
    public void removeCash(int cash) 
    {
        this.cash -= cash;
    }
    
    public void addGare(Gare g) 
    {
        gares.add(g);
    }
    
    public void addProprieteAConstruire(ProprieteAConstruire p) 
    {
        proprietes.add(p);
    }
    
    public void addCompagnie(Compagnie c) 
    {
        compagnies.add(c);
    }

}