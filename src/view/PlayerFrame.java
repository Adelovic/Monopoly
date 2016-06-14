package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import view.DesIcon.*;

public class PlayerFrame extends JFrame implements ActionListener, KeyListener 
{
    private int joueursActifs = 2;
    private ArrayList<JTextField> listTextField;
    private boolean fenetreVisible = true;
                       
    private JButton jButtonValider;
    private JButton jButtonSupprimer;
    private JButton jButtonAjouter;
    private JLabel wallpaperFond;
    private JTextField jTextJoueur1;
    private JTextField jTextJoueur2;
    private JTextField jTextJoueur3;
    private JTextField jTextJoueur4;
    private JTextField jTextJoueur5;
    private JTextField jTextJoueur6;  
    private JLabel labelErreur1;
    private JPanel panelErreur1;
    private JLabel labelErreur2;
    private JPanel panelErreur2;  
    private JLabel logoMonopoly;
    private final Ihm2 ihm;
    
    private DeIcon de1;
    private DeIcon de2;
    private DeIcon de3;
    private DeIcon de4;
    private DeIcon de5;
    private DeIcon de6;
    private DeIcon de7;
    private DeIcon de8;
    private DeIcon de9;
    private DeIcon de10;
    private DeIcon de11;
    private DeIcon de12; 
    private DeIcon[] listeDes;
    
    private int resultatDesJoueur1;
    private int resultatDesJoueur2;
    private int resultatDesJoueur3;
    private int resultatDesJoueur4;
    private int resultatDesJoueur5;
    private int resultatDesJoueur6;
    
    private HashMap<JTextField, Integer> listeResultatsDes;
    
    public PlayerFrame(Ihm2 ihm) 
    {
        this.ihm = ihm;
        initComponents();
    }
                          
    private void initComponents() 
    {
        
        //Dès
        de1 = new DeIcon();
        de2 = new DeIcon();
        de3 = new DeIcon();
        de4 = new DeIcon();
        de5 = new DeIcon();
        de6 = new DeIcon();
        de7 = new DeIcon();
        de8 = new DeIcon();
        de9 = new DeIcon();
        de10 = new DeIcon();
        de11 = new DeIcon();
        de12 = new DeIcon();
        listeDes = new DeIcon[] {de1, de2, de3, de4, de5, de6, de7, de8, de9, de10, de11, de12};
        listeResultatsDes = new HashMap<>();
        
        listTextField = new ArrayList<>();
        jButtonValider = new JButton();
        jButtonSupprimer = new JButton();
        jButtonAjouter = new JButton();
        jTextJoueur1 = new JTextField();
        jTextJoueur2 = new JTextField();    
        jTextJoueur3 = new JTextField();
        jTextJoueur4 = new JTextField();
        jTextJoueur5 = new JTextField();
        jTextJoueur6 = new JTextField();
        panelErreur1 = new JPanel();
        panelErreur2 = new JPanel();
        labelErreur1 = new JLabel();
        labelErreur2 = new JLabel();
        
        //images
        wallpaperFond = new JLabel();
        logoMonopoly = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(0, 0, 0));
        getContentPane().setLayout(null);

        //Bouton Ajouter Joueur
        jButtonAjouter.setBackground(new Color(153, 153, 153));
        jButtonAjouter.setFont(new Font("TeXGyreAdventor", 0, 18));
        jButtonAjouter.setForeground(new Color(255, 255, 255));
        jButtonAjouter.setText("Ajouter un joueur");
        jButtonAjouter.setToolTipText("");
        jButtonAjouter.setActionCommand("");
        jButtonAjouter.addActionListener(this);
        getContentPane().add(jButtonAjouter);
        jButtonAjouter.setBounds(120, 550, 182, 60);
        
        //Bouton Valider
        jButtonValider.setBackground(new Color(153, 153, 153));
        jButtonValider.setFont(new Font("TeXGyreAdventor", 0, 18));
        jButtonValider.setForeground(new Color(255, 255, 255));
        jButtonValider.setText("Valider");
        jButtonValider.setToolTipText("");
        jButtonValider.setActionCommand("");
        jButtonValider.addActionListener(this);
        getContentPane().add(jButtonValider);
        jButtonValider.setBounds(410, 550, 170, 60);
        jButtonValider.getAccessibleContext().setAccessibleName("Valider");
        
        //Bouton Suuprimer Joueur
        jButtonSupprimer.setBackground(new Color(153, 153, 153));
        jButtonSupprimer.setFont(new Font("TeXGyreAdventor", 0, 16));
        jButtonSupprimer.setForeground(new Color(255, 255, 255));
        jButtonSupprimer.setText("Supprimer un joueur");
        jButtonSupprimer.setToolTipText("");
        jButtonSupprimer.setActionCommand("");
        jButtonSupprimer.addActionListener(this);
        getContentPane().add(jButtonSupprimer);
        jButtonSupprimer.setBounds(700, 550, 190, 60);

        
        //Label d'erreur 1
        labelErreur1.setText(" Vous devez entrer le nom d'au moins deux joueurs!");
        labelErreur1.setForeground(Color.red);
        labelErreur1.setFont(new Font("TeXGyreAdventor", 0, 18));
        
        panelErreur1.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panelErreur1.setLayout(new BorderLayout());
        panelErreur1.add(labelErreur1, BorderLayout.CENTER);
        panelErreur1.setBackground(Color.black);
        getContentPane().add(panelErreur1);
        panelErreur1.setBounds(300, 220, 455, 20);
        panelErreur1.setVisible(false);
        
        Font font = new Font("Lucida Bright", 1, 18);
        //Label d'erreur 2 
        labelErreur2.setText(" Deux joueurs ont le même nom! Veuillez en renommer un!");
        labelErreur2.setForeground(Color.red);
        labelErreur2.setFont(new Font("TeXGyreAdventor", 0, 18));
        
        panelErreur2.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panelErreur2.setLayout(new BorderLayout());
        panelErreur2.add(labelErreur2, BorderLayout.CENTER);
        panelErreur2.setBackground(Color.black);
        getContentPane().add(panelErreur2);
        panelErreur2.setBounds(270, 220, 520, 20);
        panelErreur2.setVisible(false);
        
        jTextJoueur1.setFont(font);
        jTextJoueur1.setText("Joueur 1");
        jTextJoueur1.addKeyListener(this);
        listTextField.add(jTextJoueur1);
        getContentPane().add(jTextJoueur1);
        jTextJoueur1.setBounds(410, 250, 170, 20);
        //Des du joueur 1
        getContentPane().add(listeDes[0]);
        listeDes[0].setBounds(600, 245, 30, 30);
        getContentPane().add(listeDes[1]);
        listeDes[1].setBounds(640, 245, 30, 30);

        jTextJoueur2.setFont(font);
        jTextJoueur2.setText("Joueur 2");
        jTextJoueur2.addKeyListener(this);
        listTextField.add(jTextJoueur2);
        getContentPane().add(jTextJoueur2);
        jTextJoueur2.setBounds(410, 300, 170, 20);
        //Des du joueur 2
        getContentPane().add(listeDes[2]);
        listeDes[2].setBounds(600, 295, 30, 30);
        getContentPane().add(listeDes[3]);
        listeDes[3].setBounds(640, 295, 30, 30);
        
        jTextJoueur3.setFont(font);
        jTextJoueur3.setText("Joueur 3");
        jTextJoueur3.addKeyListener(this);
        listTextField.add(jTextJoueur3);
        getContentPane().add(jTextJoueur3);
        jTextJoueur3.setBounds(410, 350, 170, 20);
        //Des du joueur 3
        getContentPane().add(listeDes[4]);
        listeDes[4].setBounds(600, 345, 30, 30);
        getContentPane().add(listeDes[5]);
        listeDes[5].setBounds(640, 345, 30, 30);
        
        jTextJoueur4.setFont(font);
        jTextJoueur4.setText("Joueur 4");
        jTextJoueur4.addKeyListener(this);
        listTextField.add(jTextJoueur4);
        getContentPane().add(jTextJoueur4);
        jTextJoueur4.setBounds(410, 400, 170, 20);
        //Des du joueur 4
        getContentPane().add(listeDes[6]);
        listeDes[6].setBounds(600, 395, 30, 30);
        getContentPane().add(listeDes[7]);
        listeDes[7].setBounds(640, 395, 30, 30);

        jTextJoueur5.setFont(font);
        jTextJoueur5.setText("Joueur 5");
        jTextJoueur5.addKeyListener(this);
        listTextField.add(jTextJoueur5);
        getContentPane().add(jTextJoueur5);
        jTextJoueur5.setBounds(410, 450, 170, 20);
        //Des du joueur 5
        getContentPane().add(listeDes[8]);
        listeDes[8].setBounds(600, 445, 30, 30);
        getContentPane().add(listeDes[9]);
        listeDes[9].setBounds(640, 445, 30, 30);

        jTextJoueur6.setFont(font);
        jTextJoueur6.setText("Joueur 6");
        jTextJoueur6.addKeyListener(this);
        listTextField.add(jTextJoueur6);
        getContentPane().add(jTextJoueur6);
        jTextJoueur6.setBounds(410, 500, 170, 20);
        //Des du joueur 6
        getContentPane().add(listeDes[10]);
        listeDes[10].setBounds(600, 495, 30, 30);
        getContentPane().add(listeDes[11]);
        listeDes[11].setBounds(640, 495, 30, 30);
        
        logoMonopoly.setIcon(new ImageIcon(getClass().getResource("logo_monopoly4.png")));
        getContentPane().add(logoMonopoly);
        logoMonopoly.setBounds(140, 0, 700, 225);

        wallpaperFond.setIcon(new ImageIcon(getClass().getResource("inscriptionJoueurs.jpeg")));
        getContentPane().add(wallpaperFond);
        wallpaperFond.setBounds(0, 0, 1020, 680);
        
        for (int i = joueursActifs; i < listTextField.size(); i++)
        {
            listTextField.get(i).setVisible(false);
        }
        for (int i = joueursActifs + 2; i < listeDes.length; i++)
        {
            listeDes[i].setVisible(false);
        }
        pack();
    }
    
        public void tournerDes()
        {
            Random r = new Random();
                for (int i = 0; i < 12; i ++)
                {
                    try {
                        Thread.sleep(100);
                        de1.setValeurDe(r.nextInt(6) + 1);
                        de2.setValeurDe(r.nextInt(6) + 1);
                        de3.setValeurDe(r.nextInt(6) + 1);
                        de4.setValeurDe(r.nextInt(6) + 1);
                        de5.setValeurDe(r.nextInt(6) + 1);
                        de6.setValeurDe(r.nextInt(6) + 1);
                        de7.setValeurDe(r.nextInt(6) + 1);
                        de8.setValeurDe(r.nextInt(6) + 1);
                        de9.setValeurDe(r.nextInt(6) + 1);
                        de10.setValeurDe(r.nextInt(6) + 1);
                        de11.setValeurDe(r.nextInt(6) + 1);
                        de12.setValeurDe(r.nextInt(6) + 1);
                    } 
                    catch (IOException | InterruptedException ex) 
                    {
                        Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    de1.paint(de1.getGraphics());
                    de2.paint(de2.getGraphics());
                    resultatDesJoueur1 = de1.getValeurDe() + de2.getValeurDe();

                    de3.paint(de3.getGraphics());
                    de4.paint(de4.getGraphics());
                    resultatDesJoueur2 = de3.getValeurDe() + de4.getValeurDe();
 
                    de5.paint(de5.getGraphics());
                    de6.paint(de6.getGraphics());
                    resultatDesJoueur3 = de5.getValeurDe() + de6.getValeurDe();
                    
                    de7.paint(de7.getGraphics());
                    de8.paint(de8.getGraphics());
                    resultatDesJoueur4 = de7.getValeurDe() + de8.getValeurDe();

                    de9.paint(de9.getGraphics());
                    de10.paint(de10.getGraphics());
                    resultatDesJoueur5 = de9.getValeurDe() + de10.getValeurDe();

                    de11.paint(de11.getGraphics());
                    de12.paint(de12.getGraphics());
                    resultatDesJoueur6 = de11.getValeurDe() + de12.getValeurDe();
                    
                    i++;
                }
                
                listeResultatsDes.put(jTextJoueur1, resultatDesJoueur1);
                listeResultatsDes.put(jTextJoueur2, resultatDesJoueur2);
                listeResultatsDes.put(jTextJoueur3, resultatDesJoueur3);
                listeResultatsDes.put(jTextJoueur4, resultatDesJoueur4);
                listeResultatsDes.put(jTextJoueur5, resultatDesJoueur5);
                listeResultatsDes.put(jTextJoueur6, resultatDesJoueur6);
        }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == jButtonAjouter)
        {
            if (joueursActifs <= 5 )
            {
                listTextField.get(joueursActifs).setVisible(true);                    
                listeDes[joueursActifs * 2].setVisible(true);
                listeDes[joueursActifs * 2 + 1].setVisible(true);
                joueursActifs++;
            }
        }
        else if (e.getSource() == jButtonSupprimer)
        {
            if (joueursActifs > 2)
            {   
                System.out.println(joueursActifs);
                listTextField.get(joueursActifs - 1).setVisible(false);
                joueursActifs --; 
                listeDes[joueursActifs * 2].setVisible(false);
                listeDes[joueursActifs * 2 + 1].setVisible(false);
            }      
        }
        else if (e.getSource() == jButtonValider)
        {
            boolean erreur1 = false;
            ArrayList<String> listNom = new ArrayList<>();
            
            tournerDes();
            for (JTextField j : listTextField)
            {
                
                if (j.isVisible())
                {
                    erreur1 = false;
                    if (!j.getText().equals(""))
                    {

                        if (listNom.contains(j.getText()))
                        {
                            fenetreVisible = true;
                            erreur1 = true;
                        }
                        else
                        {
                            erreur1 = false;
                        }
                        listNom.add(j.getText());
                    }
                }
            }
            
            if (listNom.size() <= 1 || erreur1)
            {
                fenetreVisible = true;
                panelErreur2.setVisible(erreur1);
                panelErreur1.setVisible(!erreur1);
            }
            else
            { 
                HashMap<JTextField, Integer> listeJoueurs = new HashMap<JTextField, Integer>();
            
                for (Entry<JTextField, Integer> entry : listeResultatsDes.entrySet())
                {
                    if (entry.getKey().isVisible())
                    {
                        listeJoueurs.put(entry.getKey(), entry.getValue());
                    }
                }

                listNom.clear();
                while (!listeJoueurs.isEmpty())
                {
                    listNom.add(getMax(listeJoueurs));
                }
                fenetreVisible = false; 
                ihm.initialiserJoueurs(listNom);
                this.dispose();
            }
            
            
            
        }
    }

    public String getMax(HashMap<JTextField, Integer> liste)
    {
        JTextField field = null;
        int max = 0;
        for (Entry<JTextField, Integer> entry : liste.entrySet())
        {
            if (entry.getValue() > max)
            {
                field = entry.getKey();
                max = entry.getValue();
            }
        }
        
        liste.remove(field);
        return field.getText();
    }

    public boolean isFrameVisible() 
    {
        return fenetreVisible;
    }  

    @Override
    public void keyTyped(KeyEvent e) 
    {
            
    }
    @Override
    public void keyPressed(KeyEvent e) 
    {
        for (JTextField j : listTextField)
        {
            if (e.getSource() == j)
            {
                panelErreur1.setVisible(false);
                panelErreur2.setVisible(false);
            }
        }    
    }
    @Override
    public void keyReleased(KeyEvent e) 
    {
    }
    
}