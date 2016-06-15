package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Joueur;
import model.carreaux.propriete.ProprieteConstructible;

public class IhmConstruire extends JPanel implements ActionListener
{
    private javax.swing.JButton buttonConstruire;
    private javax.swing.JLabel imageHotel;
    private javax.swing.JLabel imageMaison;
    private javax.swing.JList<String> listRue;
    private javax.swing.JComboBox<String> nbMaison;
    private javax.swing.JPanel panelConstruction;
    private javax.swing.JPanel panelOptionConstruction;
    private javax.swing.JScrollPane scroll_ruePossibleAconstuire;
    private javax.swing.JLabel titreConstruction;
    private javax.swing.JLabel wallpaperConstruction;
    private Joueur joueur;
    private ProprieteConstructible proprieteAConstruire = null;
    private Ihm2 ihm;
    private ArrayList<ProprieteConstructible> proprietes;

    private int nbMaisons;
    private int nbHotels;
    public IhmConstruire(Joueur joueur, Ihm2 ihm) 
    {
        this.ihm = ihm;
        this.joueur = joueur;
        initComponents();
    }                       
    private void initComponents() {

        wallpaperConstruction = new javax.swing.JLabel();
        panelConstruction = new javax.swing.JPanel();
        titreConstruction = new javax.swing.JLabel();
        scroll_ruePossibleAconstuire = new javax.swing.JScrollPane();
        listRue = new javax.swing.JList<>();
        panelOptionConstruction = new javax.swing.JPanel();
        nbMaison = new javax.swing.JComboBox<>();
        buttonConstruire = new javax.swing.JButton();
        imageMaison = new javax.swing.JLabel();
        imageHotel = new javax.swing.JLabel();
        proprietes = new ArrayList<>();

        setBackground(new java.awt.Color(255, 255, 255));

        wallpaperConstruction.setIcon(new javax.swing.ImageIcon(getClass().getResource("wallpaperConstruction.png")));
        wallpaperConstruction.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        panelConstruction.setBackground(new java.awt.Color(255, 255, 255));
        panelConstruction.setOpaque(false);
        panelConstruction.setLayout(null);

        titreConstruction.setFont(new java.awt.Font("TeXGyreAdventor", 1, 36));
        titreConstruction.setText("");
        panelConstruction.add(titreConstruction);
        titreConstruction.setBounds(20, 0, 530, 70);

        listRue.setFont(new java.awt.Font("Lato Semibold", 1, 18));
        listRue.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<ProprieteConstructible> jListProprietes = proprietes;
            @Override
            public int getSize() { return jListProprietes.size(); }
            @Override
            public String getElementAt(int i) { return jListProprietes.get(i).getNom(); }
        });
        scroll_ruePossibleAconstuire.setViewportView(listRue);

        panelConstruction.add(scroll_ruePossibleAconstuire);
        scroll_ruePossibleAconstuire.setBounds(140, 90, 290, 150);

        panelOptionConstruction.setOpaque(false);
        panelOptionConstruction.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        buttonConstruire.setFont(new java.awt.Font("Te X Gyre Adventor", 1, 24));
        buttonConstruire.setText("Construire");
        buttonConstruire.addActionListener(this);
        panelOptionConstruction.add(buttonConstruire);

        panelConstruction.add(panelOptionConstruction);
        panelOptionConstruction.setBounds(120, 260, 320, 60);

        imageMaison.setIcon(new javax.swing.ImageIcon(getClass().getResource("maison.gif")));
        panelConstruction.add(imageMaison);
        imageMaison.setBounds(440, 120, 100, 100);

        imageHotel.setIcon(new javax.swing.ImageIcon(getClass().getResource("hotel.gif")));
        panelConstruction.add(imageHotel);
        imageHotel.setBounds(20, 170, 100, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(wallpaperConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 582, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(wallpaperConstruction, javax.swing.GroupLayout.PREFERRED_SIZE, 359, Short.MAX_VALUE))
        );
    }    

    public void afficher(final Joueur joueur) 
    {
        this.joueur = joueur;
        
        this.setVisible(true);
        
        titreConstruction.setText("Construction de " + joueur.getNom());
        
        listRue.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<ProprieteConstructible> jListProprietes = joueur.getProprietesConstructibles();
            @Override
            public int getSize() { return jListProprietes.size(); }
            @Override
            public String getElementAt(int i) { return jListProprietes.get(i).getNom(); }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (listRue.getSelectedIndex() != -1)
        {
            proprieteAConstruire = joueur.getProprietes().get(listRue.getSelectedIndex());
        
            int prix;
            if (proprieteAConstruire.maisonConstructible())
            {
                prix = proprieteAConstruire.getPrixMaison();
            }
            else
            {
                prix = proprieteAConstruire.getPrixHotel();
            }
            ihm.getControleur().construirePropriete(proprieteAConstruire, prix);
        }
        this.setVisible(false);
        
    }
                                  
}