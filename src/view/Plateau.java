package view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public final class Plateau extends Canvas
{
    
    private final int bigCarreauSize = 116;
    private final int carreauSize = 73;
    
    private Image plateau; 

    private HashMap<String, PlayerPion> pions = new HashMap<String, PlayerPion>();

    public Plateau()
    {
        super();
        try
        {
            this.setPlateau();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(De.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setPlateau() throws IOException
    {
        this.plateau = ImageIO.read(new File("src/view/PlateauFinal.png"));        
    }
    
    public void addPion(String nomJoueur, Pion pion)
    {
        try 
        {
            PlayerPion playerPion = new PlayerPion(pion);
            
            // On definit l'image
            BufferedImage image = ImageIO.read(new File("src/view/pions/" + pion.getFileName())); 
            playerPion.setImage(image);
            
            // On definit les positions
            int x = this.getWidth() - bigCarreauSize + playerPion.getImage().getWidth()/2;
            playerPion.updatePos(x, x);
            
            pions.put(nomJoueur, playerPion);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Plateau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePion(String nomJoueur, int numCarreau)
    {
        PlayerPion pion = pions.get(nomJoueur);
        int x;
        int y;
        
        int left = (bigCarreauSize/2)-pion.getImage().getWidth()/2;
        int right = this.getWidth() - bigCarreauSize + pion.getImage().getWidth()/2;
        

        if (numCarreau >= 31)
        {
            int pos = numCarreau-31;
            x = right;
            
            y = left+pos*carreauSize + 20;
        }
        else if (numCarreau >= 21)
        {
            int pos = numCarreau-21;
            x = left+pos*carreauSize + 20;
            y = left;
        }
        else if (numCarreau >= 11)
        {
            int pos = numCarreau-11;
            x = left;
            y = right-pos*carreauSize - 20;
        }
        else
        {
            x = right-carreauSize*(numCarreau-1) - 20;
            y = right;
        }
        
        pions.get(nomJoueur).updatePos(x, y);
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.drawImage(plateau, 0, 0, this);
        
        for (PlayerPion pion : pions.values())
        {
            g.drawImage(pion.getImage(), pion.getX(), pion.getY(), this);          
        }
    }

    public Image getPlateau() 
    {
        return plateau;
    }
    
    class PlayerPion
    {
        private Pion pion;
        private int x;
        private int y;

        private BufferedImage image;
        
        public PlayerPion(Pion pion)
        {
            this.pion = pion;
        }
        
        public Pion getPion() 
        {
            return pion;
        }

        public void setImage(BufferedImage image)
        {
            this.image = image;
        }
        
        public int getX() 
        {
            return x;
        }

        public int getY() 
        {
            return y;
        }
        
        public void updatePos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public BufferedImage getImage() 
        {
            return image;
        }
    }
    
}
