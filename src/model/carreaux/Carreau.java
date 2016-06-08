package model.carreaux;

import model.Joueur;
import model.Message;


public abstract class Carreau
{
    private int numero;
    private String nom;
    
    public Carreau(int numero, String nom)
    {
        this.numero = numero;
        this.nom = nom;
    }
    
    public int getNumero() 
    {
        return this.numero;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    /*
    * Renvoie le message que le controleur
    * pourra traiter en fonction
    * des différents paramètres
    */
    public abstract Message action(Joueur j);
}