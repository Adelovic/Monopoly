package view;


import controller.Controleur;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import model.Joueur;
import model.Message;
import model.TypeCarte;
import model.carreaux.propriete.Propriete;
import model.carreaux.propriete.ProprieteConstructible;
import model.carreaux.propriete.ProprieteNonConstructible;
import model.cartes.Carte;

public class Ihm2 extends JFrame implements ActionListener, Observateur
{
    private String[] icones = { "chaussure.png", "chapeau.png", "chaussure.png", "voiture.png", "bateau.png", "deACoudre.png" };
       
    private HashMap<Joueur, ArrayList<ProprieteConstructible>> proprietesConstructibles;
    
    private Color[] listeCouleurs = {Color.red, Color.yellow, Color.green, Color.blue, Color.PINK, Color.GRAY};
    
    private boolean recuDes = false;
    
    private JPanel panelDes;
    private JButton buttonLancerDes;
    private De de1;
    private De de2;
    private JPanel fenetreJeu;
    private JLabel labelPositionActuelle;
    private JLabel labelNomProprio;
    private JLabel labelNomTour;
    private JLabel labelSolde;
    private JList<String> listProprietes;
    private JScrollPane scrollProprietes;
    private JPanel panCaseActuelle;
    private JPanel panInfoJoueurCourant;
    private JPanel panInfoJoueurs;
    private Plateau plateau;
    private JLabel wallpaper;  
    private JPanel panInfoPrison;
    private JButton buttonCautionPrison;
    private JButton buttonCarteLiberte;
    private JLabel imagePrison;
    private JLabel textPrison;
    private JLabel nbToursPrison;
    private JLabel wallpaperPrison;
    private JPanel panInfoCdC;
    private JLabel txtCarteChance1;
    private JLabel txtCartChance2;
    private JLabel imageCdC;
    private JPanel panelInfoCdC;
    private JLabel txtCdC;
    private JLabel logoMrmonopolyCdC;
    private JPanel descriptionCdC;
    private JLabel panWallpaperCarteChance;
    private JPanel panDescCarteChance;
    private JPanel panInfoCarteChance;
    private JLabel imageChance;
    private JPanel panelInfoChance;
    private JLabel txtChance;
    private JLabel logoMrmonopolyChance;
    private JLabel panWallpaperChance;
    private JLabel labelTourSuivant;
    private JButton buttonTourSuivant;
    private JToggleButton buttonAcheter;
    private JToggleButton buttonConstruire;
    private JLabel txtPosition;
    private JLabel loyerVide;
    private JLabel loyer1Maison;
    private JLabel loyer2Maisons;
    private JLabel loyer3Maisons;
    private JLabel loyer4Maisons;
    private JLabel loyerHotel;
    private JLabel nomPropriete;
    private JPanel panelCartePosition;
    private JPanel panelCouleur;
    private JPanel panelLocation;
    private JPanel panInfoCarreau;;
    private JLabel labelCdC1;
    private JLabel labelCdC2;
    private JTextArea areaInfoCarteChance;
    private JTextArea areaInfoCdC;
    
    //infos des joueurs 
    private int[] des;
    
    // Propriete courante
    private Joueur joueurCourant;
    private Propriete proprieteCourante;
    
    //classement final
    private JPanel fenetreClassement; 
    private JPanel panelClassement;
    private JPanel panelLogoPodium;
    private JLabel labelLogoGagnant;
    private JLabel labelLogoSecond;
    private JLabel labelLogoTroisieme;
    private JPanel sousPanelLogoPodium;
    private JLabel labelGagnant;
    private JLabel labelSecond;
    private JLabel labelTroisieme;
    private JPanel panelLogoPlace456;
    private JLabel JoueurQuatrieme;
    private JLabel JoueurCinquieme;
    private JLabel JoueurSixieme;
    private JLabel labelClassement;
    private JLabel labelImageFondClassement;
    private JLabel labelNom1;
    private JLabel labelNom2;
    private JLabel labelNom3;
    private JLabel labelNom4;
    private JLabel labelNom5;
    private JLabel labelNom6;
    
    //Fonts
    Font font = new Font("TeXGyreAdventor", 1, 30);
    Font font2 = new Font("TeXGyreAdventor", 1, 21);
    Font font3 = new Font("FreeSans", 0, 18);
    Font font4 = new Font("TeXGyreAdventor", 1, 33);
    Font font5 = new Font("TeXGyreAdventor", 1, 24);
    Font font6 = new Font("Lato Light", 0, 18);
    Font font7 = new Font("Lato Semibold", 1, 18);
    Font font8 = new Font("FreeSans", 0, 12);
    Font font9 = new Font("FreeSans", 1, 14);
    
    //déplacement pions
    float positionHorizontale = 0;
    float positionVerticale = 0;
    
    //controleur
    private final Controleur controleur;
    
    public Ihm2(Controleur controleur) 
    {
        this.controleur = controleur;
        this.controleur.setObservateur(this);
        
        this.setVisible(false);
        
        PlayerFrame playerFrame = new PlayerFrame(this);
        playerFrame.setSize(1020, 680);
        playerFrame.setLocationRelativeTo(null);
        playerFrame.setVisible(true);
        
    }
        
    public void initialiserJoueurs(ArrayList<String> noms)
    {
        this.controleur.initialiserJoueurs(noms);
        
        for (String nom : noms)
        {
            plateau.addPion(nom, Pion.values()[new Random().nextInt(Pion.values().length)]);
        }
    }
    
    private void initComponents() 
    {
        this.setUndecorated(true);
        
        fenetreJeu = new JPanel();
        panInfoCarteChance = new JPanel();
        txtCarteChance1 = new JLabel();
        txtCartChance2 = new JLabel();
        imageChance = new JLabel();
        panelInfoChance = new JPanel();
        txtChance = new JLabel();
        logoMrmonopolyChance = new JLabel();
        panDescCarteChance = new JPanel();
        panWallpaperChance = new JLabel();
        panInfoJoueurCourant = new JPanel();
        labelNomTour = new JLabel();
        labelNomProprio = new JLabel();
        labelSolde = new JLabel();
        scrollProprietes = new JScrollPane();
        listProprietes = new JList<String>();
        panelDes = new JPanel();
        de1 = new  De();
        de2 = new  De();
        buttonLancerDes = new JButton();
        panCaseActuelle = new JPanel();
        labelPositionActuelle = new JLabel();
        panInfoJoueurs = new JPanel();
        wallpaper = new JLabel();
        panInfoPrison = new JPanel();
        buttonCautionPrison = new JButton();
        buttonCarteLiberte = new JButton();
        imagePrison = new JLabel();
        textPrison = new JLabel();
        nbToursPrison = new JLabel();
        wallpaperPrison = new JLabel();
        panInfoCdC = new JPanel();
        txtCarteChance1 = new JLabel();
        txtCartChance2 = new JLabel();
        imageCdC = new JLabel();
        panelInfoCdC = new JPanel();
        txtCdC = new JLabel();
        logoMrmonopolyCdC = new JLabel();
        descriptionCdC = new JPanel();
        panWallpaperCarteChance = new JLabel();
        labelTourSuivant = new JLabel();
        buttonTourSuivant = new JButton();
        buttonAcheter = new JToggleButton();
        buttonConstruire = new JToggleButton();
        txtPosition = new JLabel();
        panelCartePosition = new JPanel();
        panelCouleur = new JPanel();
        panelLocation = new JPanel();
        loyerVide = new JLabel();
        loyer1Maison = new JLabel();
        loyer2Maisons = new JLabel();
        loyer3Maisons = new JLabel();
        loyer4Maisons = new JLabel();
        loyerHotel = new JLabel();
        nomPropriete = new JLabel();
        panInfoCarreau = new JPanel();
        labelCdC2 = new JLabel();
        labelCdC1 = new JLabel();
        areaInfoCarteChance = new JTextArea();
        areaInfoCdC = new JTextArea();
        fenetreClassement = new JPanel();
        panelClassement = new JPanel();
        panelLogoPodium = new JPanel();
        labelLogoGagnant = new JLabel();
        labelLogoSecond = new JLabel();
        labelLogoTroisieme = new JLabel();
        sousPanelLogoPodium = new JPanel();
        labelGagnant = new JLabel();
        labelSecond = new JLabel();
        labelTroisieme = new JLabel();
        panelLogoPlace456 = new JPanel();
        JoueurQuatrieme = new JLabel();
        JoueurCinquieme = new JLabel();
        JoueurSixieme = new JLabel();
        labelClassement = new JLabel();
        labelImageFondClassement = new JLabel();
        labelNom1 = new JLabel();
        labelNom2 = new JLabel();
        labelNom3 = new JLabel();
        labelNom4 = new JLabel();
        labelNom5 = new JLabel();
        labelNom6 = new JLabel();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fenetreJeu.setBackground(new Color(204, 204, 204));
        fenetreJeu.setLayout(null);
        
        //Classement général
        fenetreClassement.setBackground(new java.awt.Color(205, 230, 208));
        fenetreClassement.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 8, true));
        fenetreClassement.setLayout(null);

        panelClassement.setOpaque(false);
        panelClassement.setLayout(new java.awt.GridLayout(1, 4, 10, 1));

        panelLogoPodium.setOpaque(false);
        panelLogoPodium.setLayout(new java.awt.GridLayout(3, 1, 10, 20));

        sousPanelLogoPodium.setOpaque(false);
        sousPanelLogoPodium.setLayout(new java.awt.GridLayout(3, 1, 10, 30));

        labelGagnant.setIcon(new javax.swing.ImageIcon(getClass().getResource("n1.png")));
        sousPanelLogoPodium.add(labelGagnant);
        labelNom1.setFont(font9);
        labelNom1.setText("Gagnant");
        sousPanelLogoPodium.add(labelNom1);

        labelSecond.setIcon(new javax.swing.ImageIcon(getClass().getResource("n2.png")));
        sousPanelLogoPodium.add(labelSecond);
        labelNom2.setFont(font9);
        labelNom2.setText("deuxieme");
        sousPanelLogoPodium.add(labelNom2);

        labelTroisieme.setIcon(new javax.swing.ImageIcon(getClass().getResource("n3.png")));
        sousPanelLogoPodium.add(labelTroisieme);
        labelNom3.setFont(font9);
        labelNom3.setText("troisième");
        sousPanelLogoPodium.add(labelNom3);

        panelClassement.add(sousPanelLogoPodium);

        panelLogoPlace456.setOpaque(false);
        panelLogoPlace456.setLayout(new java.awt.GridLayout(3, 1, 10, 30));

        JoueurQuatrieme.setIcon(new javax.swing.ImageIcon(getClass().getResource("n4.png")));
        panelLogoPlace456.add(JoueurQuatrieme);
        labelNom4.setFont(font9);
        labelNom4.setText("quatrième");
        panelLogoPlace456.add(labelNom4);

        JoueurCinquieme.setIcon(new javax.swing.ImageIcon(getClass().getResource("n5.png")));
        panelLogoPlace456.add(JoueurCinquieme);
        labelNom5.setFont(font9);
        labelNom5.setText("cinquième");
        panelLogoPlace456.add(labelNom5);

        JoueurSixieme.setIcon(new javax.swing.ImageIcon(getClass().getResource("n6.png")));
        panelLogoPlace456.add(JoueurSixieme);
        labelNom6.setFont(font9);
        labelNom6.setText("sixième");
        panelLogoPlace456.add(labelNom6);

        panelClassement.add(panelLogoPlace456);

        fenetreClassement.add(panelClassement);
        panelClassement.setBounds(20, 60, 360, 300);
        panelClassement.setVisible(true);

        labelClassement.setFont(new java.awt.Font("TeXGyreAdventor", 1, 30));
        labelClassement.setForeground(new java.awt.Color(0, 0, 0));
        labelClassement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelClassement.setText("Classement");
        fenetreClassement.add(labelClassement);
        labelClassement.setBounds(110, 20, 190, 30);

        labelImageFondClassement.setIcon(new javax.swing.ImageIcon(getClass().getResource("wallpaperQuiCommence.png")));
        fenetreClassement.add(labelImageFondClassement);
        labelImageFondClassement.setBounds(7, 5, 390, 370);

        fenetreJeu.add(fenetreClassement);
        fenetreClassement.setBounds(670, 310, 400, 380);
        fenetreClassement.setVisible(false);
        
        //fenetre carte chance
        panInfoCarteChance.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        panInfoCarteChance.setLayout(null);

        txtCarteChance1.setFont(font); 
        txtCarteChance1.setForeground(new Color(255, 255, 255));
        txtCarteChance1.setHorizontalAlignment(SwingConstants.CENTER);
        txtCarteChance1.setText("Vous avez pioché ");
        panInfoCarteChance.add(txtCarteChance1);
        txtCarteChance1.setBounds(20, 10, 460, 30);

        txtCartChance2.setFont(font); 
        txtCartChance2.setForeground(new Color(255, 255, 255));
        txtCartChance2.setHorizontalAlignment(SwingConstants.CENTER);
        txtCartChance2.setText("une carte Chance");
        panInfoCarteChance.add(txtCartChance2);
        txtCartChance2.setBounds(10, 40, 480, 40);

        imageChance.setIcon(new ImageIcon(getClass().getResource("chance.png"))); 
        imageChance.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panInfoCarteChance.add(imageChance);
        imageChance.setBounds(80, 90, 344, 210);

        panelInfoChance.setBackground(new Color(255, 255, 255));
        panelInfoChance.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 4));
        panelInfoChance.setLayout(null);

        txtChance.setFont(new Font("TeXGyreAdventor", 1, 36)); 
        txtChance.setForeground(new Color(241, 37, 51));
        txtChance.setHorizontalAlignment(SwingConstants.CENTER);
        txtChance.setText("Chance");
        panelInfoChance.add(txtChance);
        txtChance.setBounds(170, 10, 200, 40);

        logoMrmonopolyChance.setIcon(new ImageIcon(getClass().getResource("Monsieur_chance.png"))); 
        panelInfoChance.add(logoMrmonopolyChance);
        logoMrmonopolyChance.setBounds(10, 10, 180, 250);

        panDescCarteChance.setBackground(new Color(255, 255, 255));

        GroupLayout descriptionChanceLayout = new GroupLayout(panDescCarteChance);
        panDescCarteChance.setLayout(descriptionChanceLayout);
        descriptionChanceLayout.setHorizontalGroup(
            descriptionChanceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(areaInfoCarteChance)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        descriptionChanceLayout.setVerticalGroup(
            descriptionChanceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(areaInfoCarteChance)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        panelInfoChance.add(panDescCarteChance);
        panDescCarteChance.setBounds(190, 80, 190, 170);

        panInfoCarteChance.add(panelInfoChance);
        panelInfoChance.setBounds(50, 330, 400, 270);

        panWallpaperChance.setIcon(new ImageIcon(getClass().getResource("wallpaperRED.png"))); 
        panInfoCarteChance.add(panWallpaperChance);
        panWallpaperChance.setBounds(8, 5, 484, 620);

        fenetreJeu.add(panInfoCarteChance);
        panInfoCarteChance.setBounds(600, 190, 500, 630);
        panInfoCarteChance.setVisible(false);
        
        
        //fenetre carte caisse de communauté
        panInfoCdC.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        panInfoCdC.setLayout(null);

        labelCdC1.setFont(font); 
        labelCdC1.setForeground(new Color(255, 255, 255));
        labelCdC1.setHorizontalAlignment(SwingConstants.CENTER);
        labelCdC1.setText("Vous avez pioché une carte");
        panInfoCdC.add(labelCdC1);
        labelCdC1.setBounds(20, 20, 460, 30);

        labelCdC2.setFont(font); 
        labelCdC2.setForeground(new Color(255, 255, 255));
        labelCdC2.setHorizontalAlignment(SwingConstants.CENTER);
        labelCdC2.setText("Caisse de communauté");
        panInfoCdC.add(labelCdC2);
        labelCdC2.setBounds(10, 50, 480, 40);

        imageCdC.setIcon(new ImageIcon(getClass().getResource("caisse.png"))); 
        imageCdC.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panInfoCdC.add(imageCdC);
        imageCdC.setBounds(80, 100, 320, 200);

        panelInfoCdC.setBackground(new Color(255, 255, 255));
        panelInfoCdC.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 4));
        panelInfoCdC.setLayout(null);

        txtCdC.setFont(font5); 
        txtCdC.setForeground(new Color(53, 124, 251));
        txtCdC.setHorizontalAlignment(SwingConstants.CENTER);
        txtCdC.setText("Caisse de communauté");
        panelInfoCdC.add(txtCdC);
        txtCdC.setBounds(-40, 10, 400, 40);

        logoMrmonopolyCdC.setIcon(new ImageIcon(getClass().getResource("vieux.jpg"))); 
        panelInfoCdC.add(logoMrmonopolyCdC);
        logoMrmonopolyCdC.setBounds(250, 10, 140, 250);

        descriptionCdC.setBackground(new Color(255, 255, 255));

        GroupLayout descriptionCdCLayout = new GroupLayout(descriptionCdC);
        descriptionCdC.setLayout(descriptionCdCLayout);
        descriptionCdCLayout.setHorizontalGroup(
            descriptionCdCLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(areaInfoCdC)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        descriptionCdCLayout.setVerticalGroup(
            descriptionCdCLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(areaInfoCdC)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        panelInfoCdC.add(descriptionCdC);
        descriptionCdC.setBounds(30, 60, 220, 180);

        panInfoCdC.add(panelInfoCdC);
        panelInfoCdC.setBounds(50, 330, 400, 270);

        panWallpaperCarteChance.setIcon(new ImageIcon(getClass().getResource("wallpaperCaisse.png"))); 
        panInfoCdC.add(panWallpaperCarteChance);
        panWallpaperCarteChance.setBounds(8, 5, 484, 620);

        fenetreJeu.add(panInfoCdC);
        panInfoCdC.setBounds(600, 190, 500, 630);
        panInfoCdC.setVisible(false);
        
        //fenetre Info prison
        panInfoPrison.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        panInfoPrison.setLayout(null);
        buttonCautionPrison.setBackground(new Color(153, 153, 153));
        buttonCautionPrison.setFont(font2); 
        buttonCautionPrison.addActionListener(this);
        buttonCautionPrison.setForeground(new Color(255, 255, 255));
        buttonCautionPrison.setText("Payer la caution");
        panInfoPrison.add(buttonCautionPrison);
        buttonCautionPrison.setBounds(50, 370, 190, 60);

        buttonCarteLiberte.setBackground(new Color(153, 153, 153));
        buttonCarteLiberte.setFont(font2); 
        buttonCarteLiberte.setForeground(new Color(255, 255, 255));
        buttonCarteLiberte.setSelected(true);
        buttonCarteLiberte.setText("Carte de liberté");
        buttonCarteLiberte.addActionListener(this);
        panInfoPrison.add(buttonCarteLiberte);
        buttonCarteLiberte.setBounds(250, 370, 200, 60);

        imagePrison.setIcon(new ImageIcon(getClass().getResource("prison.png"))); 
        imagePrison.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
        panInfoPrison.add(imagePrison);
        imagePrison.setBounds(30, 80, 440, 280);

        textPrison.setFont(font); 
        textPrison.setText("Vous etes en Prison !");
        panInfoPrison.add(textPrison);
        textPrison.setBounds(110, 20, 310, 30);

        nbToursPrison.setFont(font3); 
        //nbToursPrison.setText("il vous reste plus que " + joueurCourant.getTourPrison() + " tour(s)");
        panInfoPrison.add(nbToursPrison);
        nbToursPrison.setBounds(140, 50, 260, 20);

        wallpaperPrison.setIcon(new ImageIcon(getClass().getResource("wallpaperPrison.jpeg"))); 
        panInfoPrison.add(wallpaperPrison);
        wallpaperPrison.setBounds(8, 8, 484, 434);
        
        fenetreJeu.add(panInfoPrison);
        panInfoPrison.setBounds(600, 270, 500, 450);
        panInfoPrison.setVisible(false);
        
        plateau = new Plateau();
        
        fenetreJeu.add(plateau);
        plateau.setBounds(390, 44, 899, 899);
        
        //Bouton "fin de tour
        labelTourSuivant.setFont(font4); 
        labelTourSuivant.setForeground(new Color(255, 255, 255));
        labelTourSuivant.setText("TOUR SUIVANT");
        labelTourSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fenetreJeu.add(labelTourSuivant);
        labelTourSuivant.setBounds(1380, 893, 260, 40);

        buttonTourSuivant.setIcon(new ImageIcon(getClass().getResource("bouton_rouge.png"))); 
        buttonTourSuivant.setBorder(null);
        buttonTourSuivant.setContentAreaFilled(false);
        buttonTourSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonTourSuivant.addActionListener(this);
        fenetreJeu.add(buttonTourSuivant);
        buttonTourSuivant.setBounds(1350, 860, 290, 100);
        
        panInfoJoueurCourant.setBackground(new Color(205, 230, 208));
        panInfoJoueurCourant.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        panInfoJoueurCourant.setForeground(new Color(255, 0, 102));
        panInfoJoueurCourant.setToolTipText("");
        panInfoJoueurCourant.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        labelNomTour.setFont(font5); 
        labelNomProprio.setFont(font6); 
        labelSolde.setFont(font6); 

        listProprietes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        listProprietes.setFont(font7); 
        scrollProprietes.setViewportView(listProprietes);

        GroupLayout panel_InfoLayout = new GroupLayout(panInfoJoueurCourant);
        panInfoJoueurCourant.setLayout(panel_InfoLayout);
        panel_InfoLayout.setHorizontalGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(scrollProprietes)
                            .addGroup(panel_InfoLayout.createSequentialGroup()
                                .addComponent(labelNomProprio)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_InfoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(labelNomTour, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSolde)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_InfoLayout.setVerticalGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomTour)
                .addGap(18, 18, 18)
                .addComponent(labelNomProprio)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollProprietes, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSolde)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fenetreJeu.add(panInfoJoueurCourant);
        panInfoJoueurCourant.setBounds(1330, 43, 320, 280);

        panelDes.setBackground(new Color(208, 230, 205));
        panelDes.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        
        buttonLancerDes.setBackground(new Color(153, 153, 153));
        buttonLancerDes.setFont(font2); 
        buttonLancerDes.setForeground(new Color(255, 255, 255));
        buttonLancerDes.setText("Lancer les Dés");
        buttonLancerDes.addActionListener(this);

        GroupLayout PanelDeLayout = new GroupLayout(panelDes);
        panelDes.setLayout(PanelDeLayout);
        PanelDeLayout.setHorizontalGroup(PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(PanelDeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(de1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(de2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(GroupLayout.Alignment.TRAILING, PanelDeLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLancerDes, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        PanelDeLayout.setVerticalGroup(PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(PanelDeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(de2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(de1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLancerDes, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        fenetreJeu.add(panelDes);
        panelDes.setBounds(1330, 342, 320, 210);

        panCaseActuelle.setBackground(new Color(208, 230, 205));
        panCaseActuelle.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));

        
        labelPositionActuelle.setFont(font5); 
        labelPositionActuelle.setText("Position actuelle");

        GroupLayout panel_Image_CaseLayout = new GroupLayout(panCaseActuelle);
        panCaseActuelle.setLayout(panel_Image_CaseLayout);
        panel_Image_CaseLayout.setHorizontalGroup(panel_Image_CaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panel_Image_CaseLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(labelPositionActuelle, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        panel_Image_CaseLayout.setVerticalGroup(panel_Image_CaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_Image_CaseLayout.createSequentialGroup()
                .addComponent(labelPositionActuelle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 327, Short.MAX_VALUE))
        );

        fenetreJeu.add(panCaseActuelle);
        panCaseActuelle.setBounds(1330, 570, 320, 380);
        
        
        //Infos carreau courant
        panCaseActuelle.setBackground(new Color(208, 230, 205));
        panCaseActuelle.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        panCaseActuelle.setLayout(null);

        txtPosition.setFont(font5); 
        txtPosition.setText("Position Actuelle");
        panCaseActuelle.add(txtPosition);
        txtPosition.setBounds(58, 4, 215, 30);

        buttonAcheter.setBackground(new Color(153, 153, 153));
        buttonAcheter.setFont(new Font("TeXGyreAdventor", 1, 19)); 
        buttonAcheter.setForeground(new Color(255, 255, 255));
        buttonAcheter.setText("Acheter");
        buttonAcheter.addActionListener(this);
        panCaseActuelle.add(buttonAcheter);
        buttonAcheter.setBounds(190, 60, 120, 70);

        buttonConstruire.setBackground(new Color(153, 153, 153));
        buttonConstruire.setFont(new Font("TeXGyreAdventor", 1, 18)); 
        buttonConstruire.setForeground(new Color(255, 255, 255));
        buttonConstruire.setText("Constuire");
        buttonConstruire.addActionListener(this);
        buttonConstruire.setEnabled(false);
        panCaseActuelle.add(buttonConstruire);
        buttonConstruire.setBounds(190, 170, 120, 70);

        panelCartePosition.setBackground(new Color(203, 230, 208));
        panelCartePosition.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        panelCartePosition.setLayout(null);

        panelCouleur.setBackground(new Color(51, 153, 0));
        panelCouleur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

        GroupLayout panelCouleurLayout = new GroupLayout(panelCouleur);
        panelCouleur.setLayout(panelCouleurLayout);
        panelCouleurLayout.setHorizontalGroup(
            panelCouleurLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );
        panelCouleurLayout.setVerticalGroup(
            panelCouleurLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        panelCartePosition.add(panelCouleur);
        panelCouleur.setBounds(2, 2, 166, 66);

        panelLocation.setBackground(new Color(205, 230, 208));
        panelLocation.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, new Color(0, 0, 0)));
        panelLocation.setLayout(new GridLayout(6, 0));

        loyerVide.setText("");
        panelLocation.add(loyerVide);

        loyer1Maison.setText("");
        panelLocation.add(loyer1Maison);

        loyer2Maisons.setText("");
        panelLocation.add(loyer2Maisons);

        loyer3Maisons.setText("");
        panelLocation.add(loyer3Maisons);

        loyer4Maisons.setText("");
        panelLocation.add(loyer4Maisons);

        loyerHotel.setText("");
        panelLocation.add(loyerHotel);

        panelCartePosition.add(panelLocation);
        panelLocation.setBounds(0, 100, 170, 100);

        nomPropriete.setText("");
        panelCartePosition.add(nomPropriete);
        nomPropriete.setBounds(0, 70, 170, 30);

        panInfoCarreau.setBackground(new Color(205, 230, 208));
        panInfoCarreau.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 2, new Color(0, 0, 0)));
        panInfoCarreau.setLayout(new GridLayout(2, 0));

        panelCartePosition.add(panInfoCarreau);
        panInfoCarreau.setBounds(0, 200, 170, 30);

        panCaseActuelle.add(panelCartePosition);
        panelCartePosition.setBounds(10, 40, 170, 230);

        fenetreJeu.add(panCaseActuelle);
        panCaseActuelle.setBounds(1330, 570, 320, 280);

        //infos joueurs
        panInfoJoueurs.setOpaque(false);
        panInfoJoueurs.setLayout(new GridLayout(6, 1, 0, 15));
        //boucle de display des joueurs
        fenetreJeu.add(panInfoJoueurs);
        panInfoJoueurs.setBounds(30, 45, 340, 905);

        wallpaper.setBackground(new Color(204, 204, 0));
        wallpaper.setIcon(new ImageIcon(getClass().getResource("wallpapers_hd_4fc5.png"))); 
        fenetreJeu.add(wallpaper);
        wallpaper.setBounds(0, 0, 1740, 990);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fenetreJeu, GroupLayout.PREFERRED_SIZE, 1671, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fenetreJeu, GroupLayout.PREFERRED_SIZE, 1030, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }  
    
    private void displayJoueurs(ArrayList<Joueur> joueurs)
    {
        panInfoJoueurs.removeAll();
        
        for (int i = 0; i < joueurs.size(); i++)
        {
            JPanel pan = new JPanel();

            pan.setBackground(new Color(208, 230, 205));
            pan.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
            pan.setPreferredSize(new Dimension(340, 120));
            pan.setLayout(null);

            System.out.println(icones[i]);
            JLabel icone = new JLabel();
            icone.setIcon(new ImageIcon(getClass().getResource(icones[i])));
            pan.add(icone);
            icone.setBounds(7, 7, 124, 124);

            JPanel sousPan = new JPanel();
            sousPan.setBackground(new Color(205, 230, 208));
            sousPan.setLayout(new GridLayout(7, 1, 0, 4));

            JLabel nom = new JLabel();
            nom.setFont(new Font("Lato Light", 1, 18)); 
            nom.setHorizontalAlignment(SwingConstants.CENTER);
            nom.setText(joueurs.get(i).getNom());
            sousPan.add(nom);

            JLabel cash = new JLabel();
            cash.setFont(new Font("FreeSans", 1, 14)); 
            cash.setText("Cash : " + joueurs.get(i).getCash() + " €");
            sousPan.add(cash);

            JLabel nbProprietes = new JLabel();
            nbProprietes.setFont(font8); 
            nbProprietes.setText("Nombre de Propriétés : " + joueurs.get(i).getProprietes().size());
            sousPan.add(nbProprietes);

            JLabel nbMaisons = new JLabel();
            nbMaisons.setFont(font8); 
            nbMaisons.setText("Nombre de Maison : " + joueurs.get(i).getNbMaisons());
            sousPan.add(nbMaisons);

            JLabel nbHotels = new JLabel();
            nbHotels.setFont(font8); 
            nbHotels.setText("Nombre d'Hotels : " + joueurs.get(i).getNbHotels());
            sousPan.add(nbHotels);

            JLabel nbCompagnies = new JLabel();
            nbCompagnies.setFont(font8); 
            nbCompagnies.setText("Nombre de Compagnies : " + joueurs.get(i).getNbCompagnies());
            sousPan.add(nbCompagnies);

            JLabel nbCartesLiberte = new JLabel();
            nbCartesLiberte.setFont(font8); 
            nbCartesLiberte.setText(joueurs.get(i).getNom() + " a " + joueurs.get(i).getNbCartesLiberation() + " carte de liberté");
            sousPan.add(nbCartesLiberte);

            pan.add(sousPan);
            sousPan.setBounds(140, 10, 190, 120);

            panInfoJoueurs.add(pan);
        }
    }
    
    public void afficherProprieteConstructible(ProprieteConstructible prop, boolean estConstructible, boolean estAchetable)
    {
        //panelLocation.setBackground(prop.getGroupe().getCouleur());
        loyerVide.setText("Prix vide : " + prop.getPrix());
        loyer1Maison.setText("1 maison " + prop.getLoyers().get(0));
        loyer2Maisons.setText("2 maisons " + prop.getLoyers().get(1));
        loyer3Maisons.setText("3 maisons " + prop.getLoyers().get(2));
        loyer4Maisons.setText("4 maisons " + prop.getLoyers().get(3));
        loyerHotel.setText("Hôtel " + prop.getLoyers().get(4));
        panCaseActuelle.setVisible(true);
        
        if (estConstructible)
        {
            buttonConstruire.setVisible(true);
            buttonAcheter.setVisible(false);
        }
        else if (estAchetable)
        {
            buttonConstruire.setVisible(false);
            buttonAcheter.setVisible(true);
        }
        else
        {
            buttonConstruire.setVisible(false);
            buttonAcheter.setVisible(false);
        }
    }
    
    public void displayProprieteNonConstructible(ProprieteNonConstructible prop)
    {
        panCaseActuelle.setBackground(new Color(208, 230, 205));
        panCaseActuelle.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        panCaseActuelle.setLayout(null);

        txtPosition.setFont(font5); 
        txtPosition.setText("Position Actuelle");
        panCaseActuelle.add(txtPosition);
        txtPosition.setBounds(58, 4, 215, 30);

        buttonAcheter.setBackground(new Color(153, 153, 153));
        buttonAcheter.setFont(new Font("TeXGyreAdventor", 1, 19)); 
        buttonAcheter.setForeground(new Color(255, 255, 255));
        buttonAcheter.setText("Acheter");
        buttonAcheter.addActionListener(this);
        panCaseActuelle.add(buttonAcheter);
        buttonAcheter.setBounds(190, 60, 120, 70);

        buttonConstruire.setBackground(new Color(153, 153, 153));
        buttonConstruire.setFont(new Font("TeXGyreAdventor", 1, 18)); 
        buttonConstruire.setForeground(new Color(255, 255, 255));
        buttonConstruire.setText("Constuire");
        buttonConstruire.addActionListener(this);
        
        panCaseActuelle.add(buttonConstruire);
        buttonConstruire.setBounds(190, 170, 120, 70);

        panelCartePosition.setBackground(new Color(203, 230, 208));
        panelCartePosition.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        panelCartePosition.setLayout(null);

        panelCouleur.setBackground(new Color(51, 153, 0));
        panelCouleur.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));

        GroupLayout panelCouleurLayout = new GroupLayout(panelCouleur);
        panelCouleur.setLayout(panelCouleurLayout);
        panelCouleurLayout.setHorizontalGroup(
            panelCouleurLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );
        panelCouleurLayout.setVerticalGroup(
            panelCouleurLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        panelCartePosition.add(panelCouleur);
        panelCouleur.setBounds(2, 2, 166, 66);

        panelLocation.setBackground(new Color(205, 230, 208));
        panelLocation.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, new Color(0, 0, 0)));
        panelLocation.setLayout(new GridLayout(6, 0));

        loyerVide.setText("Prix vide : " + prop.getPrix());
        panelLocation.add(loyerVide);

        panelCartePosition.add(panelLocation);
        panelLocation.setBounds(0, 100, 170, 100);

        nomPropriete.setText(prop.getNom());
        panelCartePosition.add(nomPropriete);
        nomPropriete.setBounds(0, 70, 170, 30);

        panInfoCarreau.setBackground(new Color(205, 230, 208));
        panInfoCarreau.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 2, new Color(0, 0, 0)));
        panInfoCarreau.setLayout(new GridLayout(2, 0));

        panelCartePosition.add(panInfoCarreau);
        panInfoCarreau.setBounds(0, 200, 170, 30);

        panCaseActuelle.add(panelCartePosition);
        panelCartePosition.setBounds(10, 40, 170, 230);

        fenetreJeu.add(panCaseActuelle);
        panCaseActuelle.setBounds(1330, 570, 320, 280);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLancerDes)
        {
            buttonLancerDes.setVisible(false);
            clearPlateau();
            controleur.jouerCoup();
            
            Random r = new Random();
            
            while (!recuDes)
            {
                try {
                    Thread.sleep(500);
                    de1.setValeurDe(r.nextInt(6) + 1);
                    de2.setValeurDe(r.nextInt(6) + 1);
                } 
                catch (IOException | InterruptedException ex) 
                {
                    Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
                }
                de1.paint(de1.getGraphics());
                de2.paint(de2.getGraphics());
            }
            
            try 
            {
                de1.setValeurDe(des[0]);
                de2.setValeurDe(des[1]);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
            }
            de1.paint(de1.getGraphics());
            de2.paint(de2.getGraphics());
            
            recuDes = false;
        }
        else if (e.getSource() == buttonTourSuivant)
        {
            clearPlateau();
            controleur.finCoup();
        }
        else if (e.getSource() == buttonCautionPrison)
        {
            
        }
        else if (e.getSource() == buttonAcheter)
        {
            controleur.acheterPropriete(joueurCourant, proprieteCourante);
            System.out.println("Le joueur " + joueurCourant.getNom() + " a acheté " + proprieteCourante.getNom() + " pour " + proprieteCourante.getPrix());
            buttonAcheter.setVisible(false);     
        }
        else if (e.getSource() == buttonConstruire)
        {
            System.out.println("Construire");
            //controleur.construirePropriete(joueurCourant.getProprieteConstructible(), message.getPrix());
        }
    }
    
    public void debutCoup(final Joueur joueur)
    {
        joueurCourant = joueur;
        
        nbToursPrison.setText("il vous reste plus que " + joueur.getTourPrison() + " tour(s)");
        labelNomTour.setText("Tour de : " + joueur.getNom());
        labelNomProprio.setText("Propriétés de " + joueur.getNom() + " :");
        labelSolde.setText("Votre solde s'élève à : " + joueur.getCash() + " €");

        listProprietes.setModel(new AbstractListModel() {
            ArrayList<ProprieteConstructible> strings = joueur.getProprietes();
            public int getSize() { return strings.size(); }
            public Object getElementAt(int i) { return strings.get(i).getNom(); }
        });
        
        buttonLancerDes.setVisible(true);
        buttonTourSuivant.setVisible(false);
        labelTourSuivant.setVisible(false);
        panCaseActuelle.setVisible(false);
    }
    
    public void deplacerPion(int num, String nom)
    {
        plateau.updatePion(nom, num);
    }  
    public void afficherCarteChance(String text)
    {
        clearPlateau();
        panInfoCarteChance.setVisible(true);
        areaInfoCarteChance.setText(text);
        areaInfoCarteChance.setLineWrap(true);
        areaInfoCarteChance.setFont(font2);
        panInfoCarteChance.paint(panInfoCarteChance.getGraphics());
    }
    
    public void afficherCarteCommunaute(String text)
    {
        clearPlateau();
        panInfoCdC.setVisible(true); 
        areaInfoCdC.setText(text);
        areaInfoCdC.setLineWrap(true);
        areaInfoCdC.setFont(font2);
        panInfoCdC.paint(panInfoCdC.getGraphics());
    }
    
    public void afficherPrison(int tourRestant, boolean aCarteLiberation)
    {
        nbToursPrison.setText("il vous reste plus que " + tourRestant + " tour(s)");
        
        buttonCautionPrison.setVisible(true);
        buttonCarteLiberte.setVisible(true);
        if (tourRestant == 3)
        {
           buttonCautionPrison.setVisible(false);
           buttonCarteLiberte.setVisible(false);
        }
        else if (tourRestant == 0)
        {
           buttonCautionPrison.setEnabled(true);
           buttonCarteLiberte.setEnabled(false);
        }
        else if (aCarteLiberation)
        {
           buttonCautionPrison.setEnabled(false);
           buttonCarteLiberte.setEnabled(true);            
        }
        else
        {
            buttonCautionPrison.setVisible(false);
            buttonCarteLiberte.setVisible(false); 
        }
        
        panCaseActuelle.setVisible(false);
        panInfoPrison.setVisible(true);
        panInfoPrison.paint(panInfoPrison.getGraphics());
    }
    
    public void clearPlateau()
    {
        panInfoPrison.setVisible(false);
        panInfoCdC.setVisible(false);
        panInfoCarteChance.setVisible(false);
    }

    public void setDes(int[] des)
    {
        this.des = des;
    }
    public void displayClassementGeneral()
    {
        fenetreClassement.setVisible(true);
    }
    @Override
    public void notifier(Message message) 
    {
        switch (message.getType())
        {
            case TIRER_CARTE:
                Carte carte = message.getCarte();
                String description = carte.getDescription();
                System.out.println(carte.getDescription());
                System.out.println(carte.getType());
                if (carte.getType() == TypeCarte.CHANCE)
                {
                    afficherCarteChance(description);
                }
                else
                {
                    afficherCarteCommunaute(description);
                }
                break;
            case LANCER_DES:
                System.out.println(message.getJoueur().getNom() + " a obtenu les dés " + message.getDerniersDes()[0] + " " + message.getDerniersDes()[1]);
                setDes(message.getDerniersDes());
                recuDes = true;
                break;
            case DEPLACER_JOUEUR:
                deplacerPion(message.getJoueur().getPositionCourante().getNumero(), message.getJoueur().getNom());
                break;
            case PRISON:
                afficherPrison(3-message.getJoueur().getTourPrison(), message.getJoueur().getNbCartesLiberation() > 0);
                break;
            case ALLER_PRISON:
                afficherPrison(3, message.getJoueur().getNbCartesLiberation() > 0);
                break;
            case PAYER_LOYER:
                System.out.println("Le joueur " + message.getJoueur().getNom() + " a payé " + message.getLoyer() + "$ de loyer à " + message.getPropriete().getProprietaire().getNom());
                if (message.getEstConstructible())
                {
                    afficherProprieteConstructible(message.getProprieteConstructible(), false, false);
                }
                break;
            case ACHAT:
                if (message.getEstConstructible())
                {
                    afficherProprieteConstructible(message.getProprieteConstructible(), false, true);
                }
                proprieteCourante = message.getPropriete();
                break;
            case CONSTRUIRE:
                afficherProprieteConstructible(message.getProprieteConstructible(), true, false);
                buttonConstruire.setEnabled(true);
                //if (demanderJoueur("Voulez-vous construire pour " + message.getPrix() + " ?"))
                //{
                //    controleur.construirePropriete(message.getProprieteConstructible(), message.getPrix());
                //}
                break;
            case RIEN:
                System.out.println("RIEN!");
                break;
            case ELIMINER_JOUEUR:
                System.out.println("Elimnation du joueur " + message.getJoueur().getNom());
                break;
            case DEBUT_PARTIE:
                initComponents();
                this.setSize(1680,1000);
                this.controleur.initialiserJeu();
                displayJoueurs(message.getJoueurs());
                this.setVisible(true);
                break;
            case FIN_PARTIE:
                System.out.println("Fin du jeu ! Gagnant :  " + message.getJoueur().getNom());
                displayClassementGeneral();
                break;
            case DEBUT_COUP:
                System.out.println("Debut du coup de " + message.getJoueur().getNom());
                displayJoueurs(message.getJoueurs());
                debutCoup(message.getJoueur());
                break;
            case FIN_COUP:
                buttonTourSuivant.setVisible(true);
                labelTourSuivant.setVisible(true);
                break;
            case REJOUER_DOUBLE_DES:
                System.out.println("Double dé de " + message.getJoueur().getNom());
                debutCoup(message.getJoueur());
                break;
            case REJOUER:
                System.out.println("Le joueur" + message.getJoueur().getNom() + " rejoue");
                controleur.jouerCarreau(message.getJoueur());
                break;
        }
    }
}
