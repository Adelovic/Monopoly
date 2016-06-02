package model;


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
    * Renvoie l'action que le joueur subira
    * en fonction de différents paramètres
    */
    public abstract Action action(Joueur j);
}