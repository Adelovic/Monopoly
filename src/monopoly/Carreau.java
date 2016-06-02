package monopoly;


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
    
    public abstract Action action(Joueur j);
}