package model;

public class ResultatAction
{
    
    /*
    * Représente le succès d'exécution de l'action
    * Si il n'y a pas succès, il y a élimination du joueur
    */
    private boolean succes;
    
    /*
    * Le message sert à notifier le joueur de l'action et de son déroulement
    */
    private String message;
    
    
    public ResultatAction(boolean succes, String message)
    {
        this.succes = succes;
        this.message = message;
    }
    
    public boolean getSucces() 
    {
        return this.succes;
    }
    
    
    public String getMessage() 
    {
        return this.message;
    }
}