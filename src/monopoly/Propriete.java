package monopoly;

public abstract class Propriete extends Carreau 
{
    private int prix;
    private Joueur proprietaire;
    
    public Propriete(int numero, String nom, int prix)
    {
        super(numero, nom);
        this.prix = prix;
    }
    
    public abstract int calculLoyer();
    
    public void acheter(Joueur j)
    {
        this.setProprietaire(j);
    }
    
    public Action action(Joueur j) 
    { 
        // Aucun proprietaire
        if (proprietaire == null)
        {
           // Le joueur a assez pour acheter 
           if (j.getCash() >= prix) 
           {
               return new ActionAchat(j, this);
           }
           else
           {
               return null;
           }
        }
        // La propriete a un proprietaire, on fait payer!
        else if (proprietaire != j)
        {
            return new ActionLoyer(j, this);
        }
        else
        {
            return null;
        }
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