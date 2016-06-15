package model;

public enum CouleurPropriete 
{
    bleuFonce(0x006BB3), 
    orange(0xF68F02), 
    mauve(0x944828), 
    violet(0xDB2E8A), 
    bleuCiel(0xB8E5FE), 
    jaune(0xFFEC00), 
    vert(0x1DAB4C), 
    rouge(0XE3010F);
    
    public int couleur;
    
    CouleurPropriete(int couleur)
    {
        this.couleur = couleur;
    }
    
    public int getCouleur()
    {
        return couleur;
    }
}