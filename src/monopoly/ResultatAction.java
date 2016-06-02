package monopoly;

public class ResultatAction
{
    private boolean succes;
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