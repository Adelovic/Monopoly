package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.Joueur;

public class PlayerFrame extends JFrame implements ActionListener, KeyListener 
{
    private int joueursActifs = 2;
    private ArrayList<JTextField> listTextField;
    private boolean fenetreVisible = true;
    
    // Variables declaration - do not modify                     
    private JButton jButtonValider;
    private JButton jButtonSupprimer;
    private JButton jButtonAjouter;
    private JLabel jLabel1;
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
    
    private Ihm2 ihm;
    
    public PlayerFrame(Ihm2 ihm) 
    {
        this.ihm = ihm;
        initComponents();
    }
                          
    private void initComponents() {
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
        jLabel1 = new JLabel();
        panelErreur1 = new JPanel();
        panelErreur2 = new JPanel();
        labelErreur1 = new JLabel();
        labelErreur2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(0, 0, 0));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        //Bouton Valider
        jButtonValider.setBackground(new Color(153, 153, 153));
        jButtonValider.setFont(new Font("TeXGyreAdventor", 0, 18));
        jButtonValider.setForeground(new Color(255, 255, 255));
        jButtonValider.setText("Valider");
        jButtonValider.setToolTipText("");
        jButtonValider.setActionCommand("");
        jButtonValider.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
        jButtonValider.addActionListener(this);
        getContentPane().add(jButtonValider);
        jButtonValider.setBounds(230, 270, 130, 60);
        jButtonValider.getAccessibleContext().setAccessibleName("Valider");

        jButtonSupprimer.setBackground(new Color(153, 153, 153));
        jButtonSupprimer.setFont(new Font("TeXGyreAdventor", 0, 16));
        jButtonSupprimer.setForeground(new Color(255, 255, 255));
        jButtonSupprimer.setText("Supprimer un joueur");
        jButtonSupprimer.setToolTipText("");
        jButtonSupprimer.setActionCommand("");
        jButtonSupprimer.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
        jButtonSupprimer.addActionListener(this);
        getContentPane().add(jButtonSupprimer);
        jButtonSupprimer.setBounds(390, 270, 190, 60);

        jButtonAjouter.setBackground(new Color(153, 153, 153));
        jButtonAjouter.setFont(new Font("TeXGyreAdventor", 0, 18));
        jButtonAjouter.setForeground(new Color(255, 255, 255));
        jButtonAjouter.setText("Ajouter un joueur");
        jButtonAjouter.setToolTipText("");
        jButtonAjouter.setActionCommand("");
        jButtonAjouter.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
        jButtonAjouter.addActionListener(this);
        getContentPane().add(jButtonAjouter);
        jButtonAjouter.setBounds(20, 270, 182, 60);
        
        //Label d'erreur 1
        labelErreur1.setText(" Vous devez entrer le nom d'au moins deux joueurs!");
        labelErreur1.setForeground(Color.red);
        labelErreur1.setFont(new Font("TeXGyreAdventor", 0, 18));
        
        panelErreur1.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panelErreur1.setLayout(new BorderLayout());
        panelErreur1.add(labelErreur1, BorderLayout.CENTER);
        panelErreur1.setBackground(Color.black);
        getContentPane().add(panelErreur1);
        panelErreur1.setBounds(100, 180, 420, 25);
        panelErreur1.setVisible(false);
        
        Font font = new Font("Lucida Bright", 1, 36);
        //Label d'erreur 2 
        labelErreur2.setText(" Deux joueurs ont le même nom! Veuillez en renommer un!");
        labelErreur2.setForeground(Color.red);
        labelErreur2.setFont(new Font("TeXGyreAdventor", 0, 18));
        
        panelErreur2.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panelErreur2.setLayout(new BorderLayout());
        panelErreur2.add(labelErreur2, BorderLayout.CENTER);
        panelErreur2.setBackground(Color.black);
        getContentPane().add(panelErreur2);
        panelErreur2.setBounds(50, 180, 470, 25);
        panelErreur2.setVisible(false);
        
        jTextJoueur1.setFont(font);
        jTextJoueur1.setText("");
        jTextJoueur1.addKeyListener(this);
        listTextField.add(jTextJoueur1);
        getContentPane().add(jTextJoueur1);
        jTextJoueur1.setBounds(20, 40, 170, 50);

        jTextJoueur2.setFont(font);
        jTextJoueur2.setText("");
        jTextJoueur2.addKeyListener(this);
        listTextField.add(jTextJoueur2);
        getContentPane().add(jTextJoueur2);
        jTextJoueur2.setBounds(210, 40, 170, 50);
        
        jTextJoueur3.setFont(font);
        jTextJoueur3.setText("");
        jTextJoueur3.addKeyListener(this);
        listTextField.add(jTextJoueur3);
        getContentPane().add(jTextJoueur3);
        jTextJoueur3.setBounds(410, 40, 170, 50);

        jTextJoueur4.setFont(font);
        jTextJoueur4.setText("");
        jTextJoueur4.addKeyListener(this);
        listTextField.add(jTextJoueur4);
        getContentPane().add(jTextJoueur4);
        jTextJoueur4.setBounds(20, 150, 170, 50);

        jTextJoueur5.setFont(font);
        jTextJoueur5.setText("");
        jTextJoueur5.addKeyListener(this);
        listTextField.add(jTextJoueur5);
        getContentPane().add(jTextJoueur5);
        jTextJoueur5.setBounds(210, 150, 170, 50);

        jTextJoueur6.setFont(font);
        jTextJoueur6.setText("");
        jTextJoueur6.addKeyListener(this);
        listTextField.add(jTextJoueur6);
        getContentPane().add(jTextJoueur6);
        jTextJoueur6.setBounds(410, 150, 170, 50);

        jLabel1.setIcon(new ImageIcon(getClass().getResource("family-fun-pack-1.jpg")));
        jLabel1.setText("Joueur 1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 610, 350);
        
        for (int i = joueursActifs; i < listTextField.size(); i++)
        {
            listTextField.get(i).setVisible(false);
        }

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == jButtonAjouter)
        {
            if (joueursActifs <= 5 )
            {
                if (joueursActifs == 1)
                {
                    listTextField.get(2).setVisible(true);
                    joueursActifs += 2;
                }
                else
                {
                    listTextField.get(joueursActifs).setVisible(true);
                    joueursActifs++;
                }
            }
        }
        else if (e.getSource() == jButtonSupprimer)
        {
            if (joueursActifs > 2)
            {   
                if (joueursActifs == 6)
                {
                    listTextField.get(joueursActifs - 1).setVisible(false);
                    joueursActifs --;
                }
                else
                {
                   listTextField.get(joueursActifs - 1).setVisible(false);
                   joueursActifs --; 
                }
            }      
        }
        else if (e.getSource() == jButtonValider)
        {
            boolean erreur1 = false;
            ArrayList<String> listNom = new ArrayList<>();
            for (JTextField j : listTextField)
            {
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
            if (listNom.size() <= 1 || erreur1)
            {
                fenetreVisible = true;
                panelErreur2.setVisible(erreur1);
                panelErreur1.setVisible(!erreur1);
            }
            else
            {
                fenetreVisible = false; 
                System.out.println("ihm initJoueurs");
                ihm.initialiserJoueurs(listNom);
                this.dispose();
            } 
        }
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