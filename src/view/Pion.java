package view;

public enum Pion 
{
    BATEAU("bateau.gif"),
    CHAPEAU("chapeau.gif"),
    CHAUSSURE("chaussure.gif"),
    CHIEN("chien.gif"),
    DE("de.gif"),
    VOITURE("voiture.gif");
    
    
    
    private String fileName;
    
    Pion(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getFileName()
    {
        return fileName;
    }
}
