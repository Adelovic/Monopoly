package view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class De extends Canvas
{
    private int valeurDe = 1;
    private Image  de;
    
    private final String[] facesDes = {"de1.png", "de2.png", "de3.png", "de4.png", "de5.png", "de6.png" };
    
    public De()
    {
        super();
        try {
            setValeurDe(new Random().nextInt(6) + 1);
        } catch (IOException ex) {
            Logger.getLogger(De.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(de, 0, 0, this);
    }

    public void setValeurDe(int valeurDe) throws IOException 
    {
        this.valeurDe = valeurDe;
        de = ImageIO.read(new File(facesDes[valeurDe - 1]));
    }

    public int getValeurDe() 
    {
        return valeurDe;
    }

    
}
    
    
    
