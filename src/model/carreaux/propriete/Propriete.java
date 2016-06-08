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
    public Message action(Joueur j) 
    { 
        Message message = new Message();
        message.setPropriete(this);
        
        // Aucun proprietaire
        if (proprietaire == null)
        {
           // Le joueur a assez pour acheter 
           if (j.getCash() >= prix) 
           {
                message.setType(TypeAction.ACHAT);
           }
           else
           {
               message.setType(TypeAction.RIEN);
           }
        }
        // La propriete a un proprietaire, le joueur doit payer
        else if (proprietaire != j)
        {  
            message.setLoyer(calculLoyer());
            message.setType(TypeAction.PAYER_LOYER);
        }
        else
        {
            message.setType(TypeAction.RIEN);
        }
        
        return message;
    }
    
    
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