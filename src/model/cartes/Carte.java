package model.cartes;

import model.Joueur;
import model.Message;
import model.TypeCarte;

public abstract class Carte 
{
    
    /** Type de carte : communaute ou chance **/
    private TypeCarte type;    
    
    /** Texte descriptif de la carte **/
    private String description;
    
    public Carte(TypeCarte type, String description)
    {
        this.type = type;
        this.description = description;
    }
    
    public TypeCarte getType()
    {
        return type;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public abstract Message actionCarte();
}
