package view.DesIcon;

import view.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class DeIcon extends Canvas
{
    private int valeurDe = 1;
    private Image  de;
    
    private final String[] facesDes = {"de1Petit.png", "de2Petit.png", "de3Petit.png", "de4Petit.png", "de5Petit.png", "de6Petit.png" };
    
    public DeIcon()
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
        de = ImageIO.read(new File("src/view/DesIcon/" + facesDes[valeurDe - 1]));
    }

    public int getValeurDe() 
    {
        return valeurDe;
    }

    
}
    
    
    
