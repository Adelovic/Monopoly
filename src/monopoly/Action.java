package monopoly;

public abstract class Action 
{
    protected Joueur joueur;
    
    public Action(Joueur j) 
    {
        this.joueur = j;
    }
    
    public abstract ResultatAction faireAction(boolean reponseJ);
    
    public abstract String getMessage();
    
    public abstract boolean entraineDemande();
}