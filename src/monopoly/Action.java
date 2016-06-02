package monopoly;

public abstract class Action 
{
    /*
    * Le joueur qui subit l'action
    */
    protected Joueur joueur;
    
    /*
    * Represente une action d'un joueur.
    * Est envoyé par les différentes cases et executée par le controleur.
    */
    public Action(Joueur j) 
    {
        this.joueur = j;
    }
    
    /*
    * Action qui sera executée par le controleur
    * après sa réception
    */
    public abstract ResultatAction faireAction(boolean reponseJ);
    
    
    /*
    * Message informatif pour que le joueur puisse savoir
    * où il est tombé
    */
    public abstract String getMessage();
    
    /*
    * Est-ce que l'action requiert un choix du joueur
    */
    public abstract boolean entraineDemande();
}