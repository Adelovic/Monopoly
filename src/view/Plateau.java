package view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public final class Plateau extends Canvas
{
    private final String[] facesDes = {"de1.png", "de2.png", "de3.png", "de4.png", "de5.png", "de6.png" };
    private Image plateau; 
    private Image pion;

    public Plateau()
    {
        super();
        try
        {
            this.setDe();
            this.setPion();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(De.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setDe() throws IOException
    {
        this.plateau = ImageIO.read(new File("src/view/PlateauFinal.png"));        
    }
    public void setPion() throws IOException
    {
        this.pion = ImageIO.read(new File("src/view/" + facesDes[0]));        
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(plateau, 0, 0, this);
        //g.drawImage(pion,0,0,this);
    }

    public Image getPlateau() {
        return plateau;
    }

    public Image getPion() {
        return pion;
    }
    
}
