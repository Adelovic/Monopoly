package view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import model.Joueur;

public class Ihm2 extends javax.swing.JFrame implements ActionListener 
{

    public Ihm2(ArrayList<Joueur> list, Joueur joueurCourant, int[] Des) 
    {
        this.Des = Des;
        this.joueurCourant = joueurCourant;
        this.list = list;
        initComponents();
    }
                         
    private void initComponents() {

        fenetre_Jeu = new JPanel();
        fenetreInfoChance = new JPanel();
        txtCarteChance1 = new JLabel();
        txtCartChance2 = new JLabel();
        imageChance = new JLabel();
        panelInfoChance = new JPanel();
        txtChance = new JLabel();
        logoMrmonopolyChance = new JLabel();
        descriptionChance = new JPanel();
        wallpaper_Chance = new JLabel();
        plateau = new JLabel();
        panel_Info = new JPanel();
        jLabelNomTour = new JLabel();
        jLabelNomProprio = new JLabel();
        jLabelSolde = new JLabel();
        jScrolPropriétés = new JScrollPane();
        jList1 = new JList<String>();
        PanelDe = new JPanel();
        canvas_DeN1 = new  De();
        canvas_DeN2 = new  De();
        button_LancerLesDes = new JButton();
        panel_Image_Case = new JPanel();
        jLabel1 = new JLabel();
        panel_Info_Joueurs = new JPanel();
        info_Joueur1 = new JPanel();
        logo_Chien_J1 = new JLabel();
        info_JoueurA = new JPanel();
        nom_JoueurA = new JLabel();
        Cash_A = new JLabel();
        nb_ProprioA = new JLabel();
        nb_MaisonA = new JLabel();
        nb_HotelA = new JLabel();
        nb_CompaA = new JLabel();
        nb_LiberteA = new JLabel();
        Info_Joueur2 = new JPanel();
        logo_Chapeau_J2 = new JLabel();
        info_JoueurB = new JPanel();
        nom_JoueurB = new JLabel();
        Cash_B = new JLabel();
        nb_ProprioB = new JLabel();
        nb_MaisonB = new JLabel();
        nb_HotelB = new JLabel();
        nb_CompaB = new JLabel();
        nb_LiberteB = new JLabel();
        info_Joueur3 = new JPanel();
        logo_Voiture_J3 = new JLabel();
        info_JoueurC = new JPanel();
        nom_JoueurC = new JLabel();
        cash_JoueurC = new JLabel();
        nb_ProprioC = new JLabel();
        nb_MaisonC = new JLabel();
        nb_HotelsC = new JLabel();
        nb_CompagniesC = new JLabel();
        liberte_C = new JLabel();
        info_Joueur4 = new JPanel();
        logo_De_J4 = new JLabel();
        info_JoueurD = new JPanel();
        nom_JoueurD = new JLabel();
        cash_JoueurD = new JLabel();
        nb_ProprietesD = new JLabel();
        nb_MaisonsD = new JLabel();
        nb_HotelsD = new JLabel();
        nb_CompagniesD = new JLabel();
        liberte_D = new JLabel();
        info_Joueur5 = new JPanel();
        logo_Bateau_J5 = new JLabel();
        info_JoueurE = new JPanel();
        nom_JoueurE = new JLabel();
        cash_JoueurE = new JLabel();
        nb_ProprieteE = new JLabel();
        nb_MaisonE = new JLabel();
        nb_HotelsE = new JLabel();
        nb_CompagniesE = new JLabel();
        liberte_E = new JLabel();
        info_Joueur6 = new JPanel();
        logo_Chaussure_J6 = new JLabel();
        info_JoueurF = new JPanel();
        nom_JoueurF = new JLabel();
        cash_JoueurF = new JLabel();
        nb_ProprietesF = new JLabel();
        nb_MaisonsF = new JLabel();
        nb_HotelsF = new JLabel();
        nb_CompagniesF = new JLabel();
        liberte_F = new JLabel();
        wallpaper = new JLabel();
        fenetreInfoPrison = new JPanel();
        boutonLancerDePrison = new JButton();
        boutonCarteLiberte = new JButton();
        imagePrison = new JLabel();
        text_prison1 = new JLabel();
        nbTourPrison = new JLabel();
        wallpaper_prison = new JLabel();
        //panelPions = new JPanel();
        fenetreInfoCdC = new JPanel();
        txtCarteChance1 = new JLabel();
        txtCartChance2 = new JLabel();
        imageCdC = new JLabel();
        panelInfoCdC = new JPanel();
        txtCdC = new JLabel();
        logoMrmonopolyCDC = new JLabel();
        descriptionCdC = new JPanel();
        wallpaper_CdC = new JLabel();
        textBoutonRouge = new JLabel();
        boutonTourSuivant = new JButton();
        boutonAcheter = new JToggleButton();
        boutonConstruire = new JToggleButton();
        txtPosition = new JLabel();
        panelCartePosition = new JPanel();
        panelCouleur = new JPanel();
        panelLocation = new JPanel();
        loyerVide = new JLabel();
        loyer1Maison = new JLabel();
        loyer2Maison = new JLabel();
        loyer3Maison = new JLabel();
        loyer4Maison = new JLabel();
        loyerHotel = new JLabel();
        nomPropriete = new JLabel();
        jPanel9 = new JPanel();
        jLabel36 = new JLabel();
        jLabel37 = new JLabel();
        txtCdC2 = new JLabel();
        txtCdC1 = new JLabel();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fenetre_Jeu.setBackground(new Color(204, 204, 204));
        fenetre_Jeu.setLayout(null);
        
        
        Font font = new Font("TeXGyreAdventor", 1, 30);
        Font font2 = new Font("TeXGyreAdventor", 1, 21);
        Font font3 = new Font("FreeSans", 0, 18);
        Font font4 = new Font("TeXGyreAdventor", 1, 33);
        Font font5 = new Font("TeXGyreAdventor", 1, 24);
        Font font6 = new Font("Lato Light", 0, 18);
        Font font7 = new Font("Lato Semibold", 1, 18);
        Font font8 = new Font("FreeSans", 0, 12);
        //fenetre carte chance
        fenetreInfoChance.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        fenetreInfoChance.setLayout(null);

        txtCarteChance1.setFont(font); 
        txtCarteChance1.setForeground(new Color(255, 255, 255));
        txtCarteChance1.setHorizontalAlignment(SwingConstants.CENTER);
        txtCarteChance1.setText("Vous avez pioché ");
        fenetreInfoChance.add(txtCarteChance1);
        txtCarteChance1.setBounds(20, 10, 460, 30);

        txtCartChance2.setFont(font); 
        txtCartChance2.setForeground(new Color(255, 255, 255));
        txtCartChance2.setHorizontalAlignment(SwingConstants.CENTER);
        txtCartChance2.setText("une carte Chance");
        fenetreInfoChance.add(txtCartChance2);
        txtCartChance2.setBounds(10, 40, 480, 40);

        imageChance.setIcon(new ImageIcon(getClass().getResource("/chance.png"))); 
        imageChance.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        fenetreInfoChance.add(imageChance);
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

        logoMrmonopolyChance.setIcon(new ImageIcon(getClass().getResource("/Monsieur_chance.png"))); 
        panelInfoChance.add(logoMrmonopolyChance);
        logoMrmonopolyChance.setBounds(10, 10, 180, 250);

        descriptionChance.setBackground(new Color(255, 255, 255));

        GroupLayout descriptionChanceLayout = new GroupLayout(descriptionChance);
        descriptionChance.setLayout(descriptionChanceLayout);
        descriptionChanceLayout.setHorizontalGroup(
            descriptionChanceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        descriptionChanceLayout.setVerticalGroup(
            descriptionChanceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        panelInfoChance.add(descriptionChance);
        descriptionChance.setBounds(190, 80, 190, 170);

        fenetreInfoChance.add(panelInfoChance);
        panelInfoChance.setBounds(50, 330, 400, 270);

        wallpaper_Chance.setIcon(new ImageIcon(getClass().getResource("/wallpaperRED.png"))); 
        fenetreInfoChance.add(wallpaper_Chance);
        wallpaper_Chance.setBounds(8, 5, 484, 620);

        fenetre_Jeu.add(fenetreInfoChance);
        fenetreInfoChance.setBounds(600, 190, 500, 630);
        fenetreInfoChance.setVisible(false);
        
        
        //fenetre carte caisse de communauté
        fenetreInfoCdC.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        fenetreInfoCdC.setLayout(null);

        txtCdC1.setFont(font); 
        txtCdC1.setForeground(new Color(255, 255, 255));
        txtCdC1.setHorizontalAlignment(SwingConstants.CENTER);
        txtCdC1.setText("Vous avez pioché une carte");
        fenetreInfoCdC.add(txtCdC1);
        txtCdC1.setBounds(20, 20, 460, 30);

        txtCdC2.setFont(font); 
        txtCdC2.setForeground(new Color(255, 255, 255));
        txtCdC2.setHorizontalAlignment(SwingConstants.CENTER);
        txtCdC2.setText("Caisse de communauté");
        fenetreInfoCdC.add(txtCdC2);
        txtCdC2.setBounds(10, 50, 480, 40);

        imageCdC.setIcon(new ImageIcon(getClass().getResource("caisse.png"))); 
        imageCdC.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        fenetreInfoCdC.add(imageCdC);
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

        logoMrmonopolyCDC.setIcon(new ImageIcon(getClass().getResource("vieux.jpg"))); 
        panelInfoCdC.add(logoMrmonopolyCDC);
        logoMrmonopolyCDC.setBounds(250, 10, 140, 250);

        descriptionCdC.setBackground(new Color(255, 255, 255));

        GroupLayout descriptionCdCLayout = new GroupLayout(descriptionCdC);
        descriptionCdC.setLayout(descriptionCdCLayout);
        descriptionCdCLayout.setHorizontalGroup(
            descriptionCdCLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        descriptionCdCLayout.setVerticalGroup(
            descriptionCdCLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        panelInfoCdC.add(descriptionCdC);
        descriptionCdC.setBounds(30, 60, 220, 180);

        fenetreInfoCdC.add(panelInfoCdC);
        panelInfoCdC.setBounds(50, 330, 400, 270);

        wallpaper_CdC.setIcon(new ImageIcon(getClass().getResource("wallpaperCaisse.png"))); 
        fenetreInfoCdC.add(wallpaper_CdC);
        wallpaper_CdC.setBounds(8, 5, 484, 620);

        fenetre_Jeu.add(fenetreInfoCdC);
        fenetreInfoCdC.setBounds(600, 190, 500, 630);
        fenetreInfoCdC.setVisible(false);
        
        //fenetre Info prison
        fenetreInfoPrison.setBorder(new LineBorder(new Color(0, 0, 0), 8, true));
        fenetreInfoPrison.setLayout(null);
        boutonLancerDePrison.setBackground(new Color(153, 153, 153));
        boutonLancerDePrison.setFont(font2); 
        boutonLancerDePrison.addActionListener(this);
        boutonLancerDePrison.setForeground(new Color(255, 255, 255));
        boutonLancerDePrison.setText("Lancer les Dés");
        fenetreInfoPrison.add(boutonLancerDePrison);
        boutonLancerDePrison.setBounds(50, 370, 190, 60);

        boutonCarteLiberte.setBackground(new Color(153, 153, 153));
        boutonCarteLiberte.setFont(font2); 
        boutonCarteLiberte.setForeground(new Color(255, 255, 255));
        boutonCarteLiberte.setSelected(true);
        boutonCarteLiberte.setText("Carte de liberté");
        boutonCarteLiberte.addActionListener(this);
        fenetreInfoPrison.add(boutonCarteLiberte);
        boutonCarteLiberte.setBounds(250, 370, 200, 60);

        imagePrison.setIcon(new ImageIcon(getClass().getResource("prison.png"))); 
        imagePrison.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
        fenetreInfoPrison.add(imagePrison);
        imagePrison.setBounds(30, 80, 440, 280);

        text_prison1.setFont(font); 
        text_prison1.setText("Vous etes en Prison !");
        fenetreInfoPrison.add(text_prison1);
        text_prison1.setBounds(110, 20, 310, 30);

        nbTourPrison.setFont(font3); 
        nbTourPrison.setText("il vous reste plus que " + joueurCourant.getTourPrison() + " tour(s)");
        fenetreInfoPrison.add(nbTourPrison);
        nbTourPrison.setBounds(140, 50, 260, 20);

        wallpaper_prison.setIcon(new ImageIcon(getClass().getResource("wallpaperPrison.jpeg"))); 
        fenetreInfoPrison.add(wallpaper_prison);
        wallpaper_prison.setBounds(8, 8, 484, 434);
        
        fenetre_Jeu.add(fenetreInfoPrison);
        fenetreInfoPrison.setBounds(600, 270, 500, 450);
        fenetreInfoPrison.setVisible(false);
        
        
        plateau.setBorder(new LineBorder(new Color(0, 0, 0), 10, true));
        Plateau p1 = new Plateau();
        plateau.setIcon(new ImageIcon(p1.getPlateau()));
        jPion = new JLabel();
        jPion.setBackground(Color.red);
        jPion.setOpaque(true);
        jPion.setBorder(new LineBorder(Color.BLACK, 2, true));
        plateau.add(jPion);
        System.out.println("case 0");
        jPion.setBounds((int)(810 - ((positionCourante % 10) / 2 * 150)), (int) (810 - ((positionCourante % 10) / 2 * 150)), 50, 50);
        jPion.setVisible(true);
        
        fenetre_Jeu.add(plateau);
        plateau.setBounds(390, 44, 920, 910);
        
        //Bouton "fin de tour
        textBoutonRouge.setFont(font4); 
        textBoutonRouge.setForeground(new Color(255, 255, 255));
        textBoutonRouge.setText("TOUR SUIVANT");
        textBoutonRouge.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fenetre_Jeu.add(textBoutonRouge);
        textBoutonRouge.setBounds(1370, 893, 260, 40);

        boutonTourSuivant.setIcon(new ImageIcon(getClass().getResource("bouton_rouge.png"))); 
        boutonTourSuivant.setBorder(null);
        boutonTourSuivant.setContentAreaFilled(false);
        boutonTourSuivant.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boutonTourSuivant.addActionListener(this);
        fenetre_Jeu.add(boutonTourSuivant);
        boutonTourSuivant.setBounds(1350, 860, 290, 100);
        
        panel_Info.setBackground(new Color(205, 230, 208));
        panel_Info.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        panel_Info.setForeground(new Color(255, 0, 102));
        panel_Info.setToolTipText("");
        panel_Info.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        jLabelNomTour.setFont(font5); 
        jLabelNomTour.setText("C'est votre tour " + joueurCourant.getNom());

        jLabelNomProprio.setFont(font6); 
        jLabelNomProprio.setText("Propriétés de : " + joueurCourant.getNom());

        jLabelSolde.setFont(font6); 
        jLabelSolde.setText("Votre solde s'élève à : " + joueurCourant.getCash() + " €");

        jList1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jList1.setFont(font7); 
        jList1.setModel(new AbstractListModel() {
            String[] strings = joueurCourant.getListProprietes();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrolPropriétés.setViewportView(jList1);

        GroupLayout panel_InfoLayout = new GroupLayout(panel_Info);
        panel_Info.setLayout(panel_InfoLayout);
        panel_InfoLayout.setHorizontalGroup(
            panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panel_InfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jScrolPropriétés)
                            .addGroup(panel_InfoLayout.createSequentialGroup()
                                .addComponent(jLabelNomProprio)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_InfoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelNomTour, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSolde)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_InfoLayout.setVerticalGroup(
            panel_InfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_InfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomTour)
                .addGap(18, 18, 18)
                .addComponent(jLabelNomProprio)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrolPropriétés, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSolde)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fenetre_Jeu.add(panel_Info);
        panel_Info.setBounds(1330, 43, 320, 280);

        PanelDe.setBackground(new Color(208, 230, 205));
        PanelDe.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        
        button_LancerLesDes.setBackground(new Color(153, 153, 153));
        button_LancerLesDes.setFont(font2); 
        button_LancerLesDes.setForeground(new Color(255, 255, 255));
        button_LancerLesDes.setText("Lancer les Dés");
        button_LancerLesDes.addActionListener(this);

        GroupLayout PanelDeLayout = new GroupLayout(PanelDe);
        PanelDe.setLayout(PanelDeLayout);
        PanelDeLayout.setHorizontalGroup(
            PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(PanelDeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(canvas_DeN1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(canvas_DeN2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(GroupLayout.Alignment.TRAILING, PanelDeLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_LancerLesDes, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        PanelDeLayout.setVerticalGroup(
            PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(PanelDeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(canvas_DeN2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(canvas_DeN1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_LancerLesDes, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        fenetre_Jeu.add(PanelDe);
        PanelDe.setBounds(1330, 342, 320, 210);

        panel_Image_Case.setBackground(new Color(208, 230, 205));
        panel_Image_Case.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));

        jLabel1.setFont(font5); 
        jLabel1.setText("Position Actuelle");

        GroupLayout panel_Image_CaseLayout = new GroupLayout(panel_Image_Case);
        panel_Image_Case.setLayout(panel_Image_CaseLayout);
        panel_Image_CaseLayout.setHorizontalGroup(
            panel_Image_CaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panel_Image_CaseLayout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        panel_Image_CaseLayout.setVerticalGroup(
            panel_Image_CaseLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel_Image_CaseLayout.createSequentialGroup()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 327, Short.MAX_VALUE))
        );

        fenetre_Jeu.add(panel_Image_Case);
        panel_Image_Case.setBounds(1330, 570, 320, 380);

        //Infos carreau courant
        panel_Image_Case.setBackground(new Color(208, 230, 205));
        panel_Image_Case.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        panel_Image_Case.setLayout(null);

        txtPosition.setFont(font5); 
        txtPosition.setText("Position Actuelle");
        panel_Image_Case.add(txtPosition);
        txtPosition.setBounds(58, 4, 215, 30);

        boutonAcheter.setBackground(new Color(153, 153, 153));
        boutonAcheter.setFont(new Font("TeXGyreAdventor", 1, 19)); 
        boutonAcheter.setForeground(new Color(255, 255, 255));
        boutonAcheter.setText("Acheter");
        boutonAcheter.addActionListener(this);
        panel_Image_Case.add(boutonAcheter);
        boutonAcheter.setBounds(190, 60, 120, 70);

        boutonConstruire.setBackground(new Color(153, 153, 153));
        boutonConstruire.setFont(new Font("TeXGyreAdventor", 1, 18)); 
        boutonConstruire.setForeground(new Color(255, 255, 255));
        boutonConstruire.setText("Constuire");
        boutonConstruire.addActionListener(this);
        panel_Image_Case.add(boutonConstruire);
        boutonConstruire.setBounds(190, 170, 120, 70);

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

        loyerVide.setText("   A Vide                   10");
        panelLocation.add(loyerVide);

        loyer1Maison.setText("Avec 1 Maison       100");
        panelLocation.add(loyer1Maison);

        loyer2Maison.setText("Avec 2 Maison       200");
        panelLocation.add(loyer2Maison);

        loyer3Maison.setText("Avec 3 Maison       300");
        panelLocation.add(loyer3Maison);

        loyer4Maison.setText("Avec 4 Maison       400");
        panelLocation.add(loyer4Maison);

        loyerHotel.setText("Avec Hôtel              500");
        panelLocation.add(loyerHotel);

        panelCartePosition.add(panelLocation);
        panelLocation.setBounds(0, 100, 170, 100);

        nomPropriete.setText("listCarreau.get(positionCourante).getNom()");
        panelCartePosition.add(nomPropriete);
        nomPropriete.setBounds(0, 70, 170, 30);

        jPanel9.setBackground(new Color(205, 230, 208));
        jPanel9.setBorder(BorderFactory.createMatteBorder(0, 1, 2, 2, new Color(0, 0, 0)));
        jPanel9.setLayout(new GridLayout(2, 0));

        jLabel36.setText("Prix 1 Maison          400");
        jPanel9.add(jLabel36);

        jLabel37.setText("Prix d'un Hôtel      2000 ");
        jPanel9.add(jLabel37);

        panelCartePosition.add(jPanel9);
        jPanel9.setBounds(0, 200, 170, 30);

        panel_Image_Case.add(panelCartePosition);
        panelCartePosition.setBounds(10, 40, 170, 230);

        fenetre_Jeu.add(panel_Image_Case);
        panel_Image_Case.setBounds(1330, 570, 320, 280);

        //infos joueurs
        panel_Info_Joueurs.setOpaque(false);
        panel_Info_Joueurs.setLayout(new GridLayout(6, 1, 0, 15));

        info_Joueur1.setBackground(new Color(208, 230, 205));
        info_Joueur1.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        info_Joueur1.setPreferredSize(new Dimension(340, 120));
        info_Joueur1.setLayout(null);

        logo_Chien_J1.setIcon(new ImageIcon(getClass().getResource("/chien.png"))); 
        info_Joueur1.add(logo_Chien_J1);
        logo_Chien_J1.setBounds(7, 7, 124, 124);

        info_JoueurA.setBackground(new Color(205, 230, 208));
        info_JoueurA.setLayout(new GridLayout(7, 1, 0, 4));

        nom_JoueurA.setFont(new Font("Lato Light", 1, 18)); 
        nom_JoueurA.setHorizontalAlignment(SwingConstants.CENTER);
        nom_JoueurA.setText(list.get(0).getNom());
        info_JoueurA.add(nom_JoueurA);

        Cash_A.setFont(new Font("FreeSans", 1, 14)); 
        Cash_A.setText("Cash : " + list.get(0).getCash() + " €");
        info_JoueurA.add(Cash_A);

        nb_ProprioA.setFont(font8); 
        nb_ProprioA.setText("Nombre de Propriétés : " + list.get(0).getNbProprietes());
        info_JoueurA.add(nb_ProprioA);

        nb_MaisonA.setFont(font8); 
        nb_MaisonA.setText("Nombre de Maison : " + list.get(0).getNbMaisons());
        info_JoueurA.add(nb_MaisonA);

        nb_HotelA.setFont(font8); 
        nb_HotelA.setText("Nombre d'Hotels : " + list.get(0).getNbHotels());
        info_JoueurA.add(nb_HotelA);

        nb_CompaA.setFont(font8); 
        nb_CompaA.setText("Nombre de Compagnies : " + list.get(0).getNbCompagnies());
        info_JoueurA.add(nb_CompaA);

        nb_LiberteA.setFont(font8); 
        nb_LiberteA.setText(list.get(0).getNom() + " a " + list.get(0).getNbLibre() + " carte de liberté");
        info_JoueurA.add(nb_LiberteA);

        info_Joueur1.add(info_JoueurA);
        info_JoueurA.setBounds(140, 10, 190, 120);

        panel_Info_Joueurs.add(info_Joueur1);

        Info_Joueur2.setBackground(new Color(208, 230, 205));
        Info_Joueur2.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
        Info_Joueur2.setPreferredSize(new Dimension(340, 120));
        Info_Joueur2.setLayout(null);

        logo_Chapeau_J2.setIcon(new ImageIcon(getClass().getResource("/chapeau.png"))); 
        logo_Chapeau_J2.setText("jLabel4");
        Info_Joueur2.add(logo_Chapeau_J2);
        logo_Chapeau_J2.setBounds(7, 4, 130, 130);

        info_JoueurB.setBackground(new Color(208, 230, 205));
        info_JoueurB.setLayout(new GridLayout(7, 1, 0, 3));

        nom_JoueurB.setFont(new Font("Lato Light", 1, 18)); 
        nom_JoueurB.setHorizontalAlignment(SwingConstants.CENTER);
        nom_JoueurB.setText(list.get(1).getNom());
        nom_JoueurB.setHorizontalTextPosition(SwingConstants.CENTER);
        info_JoueurB.add(nom_JoueurB);

        Cash_B.setFont(new Font("Dialog", 1, 14)); 
        Cash_B.setText("Cash : " + list.get(1).getCash() + " €");
        info_JoueurB.add(Cash_B);

        nb_ProprioB.setFont(font8); 
        nb_ProprioB.setText("Nombre de Propriétés : " + list.get(1).getNbProprietes());
        info_JoueurB.add(nb_ProprioB);

        nb_MaisonB.setFont(font8); 
        nb_MaisonB.setText("Nombre de Maison : " + list.get(1).getNbMaisons());
        info_JoueurB.add(nb_MaisonB);

        nb_HotelB.setFont(font8); 
        nb_HotelB.setText("Nombre d'Hotels : " + list.get(1).getNbHotels());
        info_JoueurB.add(nb_HotelB);

        nb_CompaB.setFont(font8); 
        nb_CompaB.setText("Nombre de Compagnies : " + list.get(1).getNbCompagnies());
        info_JoueurB.add(nb_CompaB);

        nb_LiberteB.setFont(font8); 
        nb_LiberteB.setText(list.get(1).getNom() +" a " + list.get(1).getNbLibre() + " carte de liberté");
        info_JoueurB.add(nb_LiberteB);

        Info_Joueur2.add(info_JoueurB);
        info_JoueurB.setBounds(140, 10, 190, 120);

        panel_Info_Joueurs.add(Info_Joueur2);

           if (list.size() >= 3)
           {
            info_Joueur3.setBackground(new Color(208, 230, 205));
            info_Joueur3.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
            info_Joueur3.setPreferredSize(new Dimension(340, 120));
            info_Joueur3.setLayout(null);

            logo_Voiture_J3.setIcon(new ImageIcon(getClass().getResource("/voiture.png"))); 
            info_Joueur3.add(logo_Voiture_J3);
            logo_Voiture_J3.setBounds(7, 4, 130, 130);

            info_JoueurC.setBackground(new Color(208, 230, 205));
            info_JoueurC.setLayout(new GridLayout(7, 1, 0, 3));

            nom_JoueurC.setFont(new Font("Lato Light", 1, 18)); 
            nom_JoueurC.setHorizontalAlignment(SwingConstants.CENTER);
            nom_JoueurC.setText(list.get(2).getNom());
            info_JoueurC.add(nom_JoueurC);

            cash_JoueurC.setFont(new Font("Lato Light", 1, 14)); 
            cash_JoueurC.setText("Cash : " + list.get(2).getCash() + "€");
            info_JoueurC.add(cash_JoueurC);

            nb_ProprioC.setFont(font8); 
            nb_ProprioC.setText("Nombre de Propriétés : " + list.get(2).getNbProprietes());
            info_JoueurC.add(nb_ProprioC);

            nb_MaisonC.setFont(font8); 
            nb_MaisonC.setText("Nombre de Maison : " + list.get(2).getNbMaisons());
            info_JoueurC.add(nb_MaisonC);

            nb_HotelsC.setFont(font8); 
            nb_HotelsC.setText("Nombre d'Hotels : " + list.get(2).getNbHotels());
            info_JoueurC.add(nb_HotelsC);

            nb_CompagniesC.setFont(font8); 
            nb_CompagniesC.setText("Nombre de Compagnies : " + list.get(2).getNbCompagnies());
            info_JoueurC.add(nb_CompagniesC);

            liberte_C.setFont(font8); 
            liberte_C.setText(list.get(1).getNom() +" a " + list.get(1).getNbLibre() + " carte de liberté");
            info_JoueurC.add(liberte_C);

            info_Joueur3.add(info_JoueurC);
            info_JoueurC.setBounds(140, 10, 190, 120);

            panel_Info_Joueurs.add(info_Joueur3);
        }

        if (list.size() >= 4)
        {
           info_Joueur4.setBackground(new Color(208, 230, 205));
            info_Joueur4.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
            info_Joueur4.setPreferredSize(new Dimension(340, 120));
            info_Joueur4.setLayout(null);

            logo_De_J4.setIcon(new ImageIcon(getClass().getResource("/De.png"))); 
            info_Joueur4.add(logo_De_J4);
            logo_De_J4.setBounds(7, -11, 130, 160);

            info_JoueurD.setBackground(new Color(203, 230, 208));
            info_JoueurD.setLayout(new GridLayout(7, 1, 0, 3));

            nom_JoueurD.setFont(new Font("Lato Light", 1, 18)); 
            nom_JoueurD.setHorizontalAlignment(SwingConstants.CENTER);
            nom_JoueurD.setText(list.get(3).getNom());
            info_JoueurD.add(nom_JoueurD);

            cash_JoueurD.setFont(new Font("Lato Light", 1, 14)); 
            cash_JoueurD.setText("Cash : " + list.get(3).getCash() + "€");
            info_JoueurD.add(cash_JoueurD);

            nb_ProprietesD.setFont(font8); 
            nb_ProprietesD.setText("Nombre de Propriétés : " + list.get(3).getNbProprietes());
            info_JoueurD.add(nb_ProprietesD);

            nb_MaisonsD.setFont(font8); 
            nb_MaisonsD.setText("Nombre de Maison : " + list.get(3).getNbMaisons());
            info_JoueurD.add(nb_MaisonsD);

            nb_HotelsD.setFont(font8); 
            nb_HotelsD.setText("Nombre d'Hotels : " + list.get(3).getNbHotels());
            info_JoueurD.add(nb_HotelsD);

            nb_CompagniesD.setFont(font8); 
            nb_CompagniesD.setText("Nombre de Compagnies : " + list.get(3).getNbCompagnies());
            info_JoueurD.add(nb_CompagniesD);

            liberte_D.setFont(font8); 
            liberte_D.setText(list.get(3).getNom() +" a" + list.get(3).getNbLibre() + "carte de liberté");
            info_JoueurD.add(liberte_D);

            info_Joueur4.add(info_JoueurD);
            info_JoueurD.setBounds(140, 10, 190, 120);

            panel_Info_Joueurs.add(info_Joueur4);
        }
        if (list.size() >= 5)
        {
            info_Joueur5.setBackground(new Color(208, 230, 205));
            info_Joueur5.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
            info_Joueur5.setPreferredSize(new Dimension(340, 120));
            info_Joueur5.setLayout(null);

            logo_Bateau_J5.setIcon(new ImageIcon(getClass().getResource("/bateau.png"))); 
            info_Joueur5.add(logo_Bateau_J5);
            logo_Bateau_J5.setBounds(7, 4, 130, 130);

            info_JoueurE.setBackground(new Color(203, 230, 208));
            info_JoueurE.setLayout(new GridLayout(7, 1, 0, 3));

            nom_JoueurE.setFont(new Font("Lato Light", 1, 18)); 
            nom_JoueurE.setHorizontalAlignment(SwingConstants.CENTER);
            nom_JoueurE.setText(list.get(4).getNom());
            info_JoueurE.add(nom_JoueurE);

            cash_JoueurE.setFont(new Font("Lato Light", 1, 14)); 
            cash_JoueurE.setText("Cash : " + list.get(4).getCash() + "€");
            info_JoueurE.add(cash_JoueurE);

            nb_ProprieteE.setFont(font8); 
            nb_ProprieteE.setText("Nombre de Propriétés : " + list.get(4).getNbProprietes());
            info_JoueurE.add(nb_ProprieteE);

            nb_MaisonE.setFont(font8); 
            nb_MaisonE.setText("Nombre de Maison : " + list.get(4).getNbMaisons());
            info_JoueurE.add(nb_MaisonE);

            nb_HotelsE.setFont(font8); 
            nb_HotelsE.setText("Nombre d'Hotels : " + list.get(4).getNbHotels());
            info_JoueurE.add(nb_HotelsE);

            nb_CompagniesE.setFont(font8); 
            nb_CompagniesE.setText("Nombre de Compagnies : " + list.get(4).getNbCompagnies());
            info_JoueurE.add(nb_CompagniesE);

            liberte_E.setFont(font8); 
            liberte_E.setText(list.get(3).getNom() +" a" + list.get(4).getNbLibre() + "carte de liberté");
            info_JoueurE.add(liberte_E);

            info_Joueur5.add(info_JoueurE);
            info_JoueurE.setBounds(140, 10, 190, 120);

            panel_Info_Joueurs.add(info_Joueur5);
        }
        if (list.size() >= 5)
        {
            info_Joueur6.setBackground(new Color(208, 230, 205));
            info_Joueur6.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 255, 255)));
            info_Joueur6.setPreferredSize(new Dimension(340, 120));
            info_Joueur6.setLayout(null);

            logo_Chaussure_J6.setIcon(new ImageIcon(getClass().getResource("/chaussure.png"))); 
            info_Joueur6.add(logo_Chaussure_J6);
            logo_Chaussure_J6.setBounds(7, 4, 130, 130);

            info_JoueurF.setBackground(new Color(203, 230, 208));
            info_JoueurF.setLayout(new GridLayout(7, 1, 0, 3));

            nom_JoueurF.setFont(new Font("Lato Light", 1, 18)); 
            nom_JoueurF.setHorizontalAlignment(SwingConstants.CENTER);
            nom_JoueurF.setText(list.get(5).getNom());
            info_JoueurF.add(nom_JoueurF);

            cash_JoueurF.setFont(new Font("Lato Light", 1, 14)); 
            cash_JoueurF.setText("Cash : " + list.get(5).getCash() + "€");
            info_JoueurF.add(cash_JoueurF);

            nb_ProprietesF.setFont(font8); 
            nb_ProprietesF.setText("Nombre de Propriétés : " + list.get(4).getNbProprietes());
            info_JoueurF.add(nb_ProprietesF);

            nb_MaisonsF.setFont(font8); 
            nb_MaisonsF.setText("Nombre de Maison : " + list.get(4).getNbMaisons());
            info_JoueurF.add(nb_MaisonsF);

            nb_HotelsF.setFont(font8); 
            nb_HotelsF.setText("Nombre d'Hotels : " + list.get(4).getNbHotels());
            info_JoueurF.add(nb_HotelsF);

            nb_CompagniesF.setFont(font8); 
            nb_CompagniesF.setText("Nombre de Compagnies : " + list.get(4).getNbCompagnies());
            info_JoueurF.add(nb_CompagniesF);

            liberte_F.setFont(font8); 
            liberte_F.setText(list.get(3).getNom() +" a" + list.get(4).getNbLibre() + "carte de liberté");
            info_JoueurF.add(liberte_F);

            info_Joueur6.add(info_JoueurF);
            info_JoueurF.setBounds(140, 10, 190, 120);

            panel_Info_Joueurs.add(info_Joueur6);
        }

        fenetre_Jeu.add(panel_Info_Joueurs);
        panel_Info_Joueurs.setBounds(30, 45, 340, 905);

        wallpaper.setBackground(new Color(204, 204, 0));
        wallpaper.setIcon(new ImageIcon(getClass().getResource("/wallpapers_hd_4fc5.png"))); 
        fenetre_Jeu.add(wallpaper);
        wallpaper.setBounds(0, 0, 1740, 990);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fenetre_Jeu, GroupLayout.PREFERRED_SIZE, 1671, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fenetre_Jeu, GroupLayout.PREFERRED_SIZE, 1030, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }                     
    private JLabel Cash_A;
    private JLabel Cash_B;
    private JPanel Info_Joueur2;
    private JPanel PanelDe;
    private JButton button_LancerLesDes;
    private De canvas_DeN1;
    private De canvas_DeN2;
    private JLabel cash_JoueurC;
    private JLabel cash_JoueurD;
    private JLabel cash_JoueurE;
    private JLabel cash_JoueurF;
    private JPanel fenetre_Jeu;
    private JPanel info_Joueur1;
    private JPanel info_Joueur3;
    private JPanel info_Joueur4;
    private JPanel info_Joueur5;
    private JPanel info_Joueur6;
    private JPanel info_JoueurA;
    private JPanel info_JoueurB;
    private JPanel info_JoueurC;
    private JPanel info_JoueurD;
    private JPanel info_JoueurE;
    private JPanel info_JoueurF;
    private JLabel jLabel1;
    private JLabel jLabelNomProprio;
    private JLabel jLabelNomTour;
    private JLabel jLabelSolde;
    private JList<String> jList1;
    private JScrollPane jScrolPropriétés;
    private JLabel liberte_C;
    private JLabel liberte_D;
    private JLabel liberte_E;
    private JLabel liberte_F;
    private JLabel logo_Bateau_J5;
    private JLabel logo_Chapeau_J2;
    private JLabel logo_Chaussure_J6;
    private JLabel logo_Chien_J1;
    private JLabel logo_De_J4;
    private JLabel logo_Voiture_J3;
    private JLabel nb_CompaA;
    private JLabel nb_CompaB;
    private JLabel nb_CompagniesC;
    private JLabel nb_CompagniesD;
    private JLabel nb_CompagniesE;
    private JLabel nb_CompagniesF;
    private JLabel nb_HotelA;
    private JLabel nb_HotelB;
    private JLabel nb_HotelsC;
    private JLabel nb_HotelsD;
    private JLabel nb_HotelsE;
    private JLabel nb_HotelsF;
    private JLabel nb_LiberteA;
    private JLabel nb_LiberteB;
    private JLabel nb_MaisonA;
    private JLabel nb_MaisonB;
    private JLabel nb_MaisonC;
    private JLabel nb_MaisonE;
    private JLabel nb_MaisonsD;
    private JLabel nb_MaisonsF;
    private JLabel nb_ProprieteE;
    private JLabel nb_ProprietesD;
    private JLabel nb_ProprietesF;
    private JLabel nb_ProprioA;
    private JLabel nb_ProprioB;
    private JLabel nb_ProprioC;
    private JLabel nom_JoueurA;
    private JLabel nom_JoueurB;
    private JLabel nom_JoueurC;
    private JLabel nom_JoueurD;
    private JLabel nom_JoueurE;
    private JLabel nom_JoueurF;
    private JPanel panel_Image_Case;
    private JPanel panel_Info;
    private JPanel panel_Info_Joueurs;
    private JLabel plateau;
    private JLabel wallpaper;  
    private JPanel fenetreInfoPrison;
    private JButton boutonLancerDePrison;
    private JButton boutonCarteLiberte;
    private JLabel imagePrison;
    private JLabel text_prison1;
    private JLabel nbTourPrison;
    private JLabel wallpaper_prison;
    private JPanel fenetreInfoCdC;
    private JLabel txtCarteChance1;
    private JLabel txtCartChance2;
    private JLabel imageCdC;
    private JPanel panelInfoCdC;
    private JLabel txtCdC;
    private JLabel logoMrmonopolyCDC;
    private JPanel descriptionCdC;
    private JLabel wallpaper_CdC;
    private JPanel descriptionChance;
    private JPanel fenetreInfoChance;
    private JLabel imageChance;
    private JPanel panelInfoChance;
    private JLabel txtChance;
    private JLabel logoMrmonopolyChance;
    private JLabel wallpaper_Chance;
    private JLabel textBoutonRouge;
    private JButton boutonTourSuivant;
    private JToggleButton boutonAcheter;
    private JToggleButton boutonConstruire;
    private JLabel txtPosition;
    private JLabel loyerVide;
    private JLabel loyer1Maison;
    private JLabel loyer2Maison;
    private JLabel loyer3Maison;
    private JLabel loyer4Maison;
    private JLabel loyerHotel;
    private JLabel nomPropriete;
    private JPanel panelCartePosition;
    private JPanel panelCouleur;
    private JPanel panelLocation;
    private JPanel jPanel9;
    private JLabel jLabel36;
    private JLabel jLabel37;
    private JLabel jPion;
    private JLabel txtCdC2;
    private JLabel txtCdC1;
    
    private float positionCourante = 0;
    //private JPanel panelPions;
    
    private Boolean sensDeplacementHorizontal;
    
    //infos des joueurs 
    private int[] Des;
    private ArrayList<Joueur> list = new ArrayList<>();
    private Joueur joueurCourant;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_LancerLesDes || e.getSource() == boutonLancerDePrison)
        {
            Scanner c = new Scanner(System.in);
            clearPlateau();
            
            System.out.println("Entrez le premier dé");
            Des[0] = c.nextInt();
            System.out.println("Entrez le deuxieme dé");
            Des[1] = c.nextInt();

            if (e.getSource() == boutonLancerDePrison)
            {
                fenetreInfoPrison.setVisible(false);
            }
            
            Random r = new Random();
            
            for (int i = 0; i < 15; i++)
            {
                try {
                    Thread.sleep(80);
                    canvas_DeN1.setValeurDe(r.nextInt(6) + 1);
                    canvas_DeN2.setValeurDe(r.nextInt(6) + 1);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
                }
                canvas_DeN1.paint(canvas_DeN1.getGraphics());
                canvas_DeN2.paint(canvas_DeN2.getGraphics());
            }
            
            try 
            {
                canvas_DeN1.setValeurDe(Des[0]);
                canvas_DeN2.setValeurDe(Des[1]);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Ihm2.class.getName()).log(Level.SEVERE, null, ex);
            }
            canvas_DeN1.paint(canvas_DeN1.getGraphics());
            canvas_DeN2.paint(canvas_DeN2.getGraphics());
            
            this.positionCourante = DeplacerPion(this.positionCourante,Des[0] + Des[1]);
            //DeplacerPion(1, Des[0] + Des[1]);
        }
        else if (e.getSource() == boutonTourSuivant)
        {
            System.out.println("Fin du tour");
            clearPlateau();
        }
        else if (e.getSource() == boutonAcheter)
        {
            System.out.println("Achat de propriete");
        }
        else if (e.getSource() == boutonConstruire)
        {
            System.out.println("Construction de propriete");
        }
    }
    
    public float DeplacerPion(float positionCourante,int nbCase)
    {
        sensDeplacementHorizontal = false;
        
        jPion.setVisible(true);
        int i = 1;
        float positionHorizontale = positionCourante;
        float positionVerticale = positionCourante;
        
        boolean ligne = false;
        
        //déplacement linéaire
        float x = positionCourante;
        float y = positionCourante;
        
        
        //déplacement horizontal entre les cases 0 et 10
        while ( i <= nbCase && positionCourante < 10 && positionCourante >= 0)
        {
            positionCourante++;
            positionVerticale = 0;
            i++;
            sensDeplacementHorizontal = true;
        }
        if (sensDeplacementHorizontal)
        {  
            positionHorizontale = positionCourante;
        }
        
        //dernière case de la ligne
        if (positionHorizontale == 10.0)
        {
            System.out.println("case 10");
        }
        else
        {
            jPion.setBounds((int)(810 - ((positionHorizontale % 10) / 2 * 150)), (int) (810 - ((positionVerticale % 10) / 2 * 150)), 50, 50);
        }
        
        //déplacement vertical entre les case 10 et 20
        while (i <= nbCase && positionCourante < 20 && positionCourante >= 10)
        {
            positionCourante++;
            positionHorizontale = 10;
            i++;
            sensDeplacementHorizontal = false;
        }
        if (!sensDeplacementHorizontal)
        {  
            positionVerticale = positionCourante;
        }

        if (positionVerticale == 20.0)
        {
            jPion.setBounds((int) (810 - ((positionHorizontale / 2) * 150)), 810 - 760, 50, 50);
        }
        else
        {
            jPion.setBounds((int)(810 - ((positionHorizontale % 10) / 2 * 150)), (int) (810 - ((positionVerticale % 10) / 2 * 150)), 50, 50);
        }

        //déplacement horizontale entre les cases 20 et 30
        while (i <= nbCase && positionCourante < 30 && positionCourante >= 20)
        {
            positionCourante++;
            positionVerticale = 0;
            i++;
            sensDeplacementHorizontal = true;
            ligne = true;
        }
        if (sensDeplacementHorizontal)
        {  
            positionHorizontale = positionCourante;
        }
        
        //déplacement vertical entre les case 20 et 30
        while (i <= nbCase && positionCourante < 40 && positionCourante >= 30)
        {
            positionCourante++;
            positionHorizontale = 30;
            i++;
            sensDeplacementHorizontal = false;
            ligne = true;
        }
        if (!sensDeplacementHorizontal)
        {  
            positionVerticale = positionCourante;
        }
        
        if (ligne)
        {
            if (positionHorizontale == 30.0)
            {
                jPion.setBounds(810, (int) (60 + (positionVerticale % 10 / 2) * 150), 50, 50);
            }
            else
            {
                jPion.setBounds((int) (60 + (positionHorizontale % 10 / 2) * 150), (int)(60 + (positionVerticale % 10 / 2) * 150), 50, 50);
            }
        }
       
        //Cases particulières
        if (positionCourante == 40)
        {
            positionCourante = 0; 
            jPion.setBounds((int)(810 - ((positionHorizontale % 10) / 2 * 150)), (int) (810 - ((positionVerticale % 10) / 2 * 150)), 50, 50);
            sensDeplacementHorizontal = true;
        }
        
        if (positionCourante == 10 || positionCourante == 30)
        {
            fenetreInfoPrison.setVisible(true);
            positionCourante = 10; 
            jPion.setBounds(60, (int) (810 - ((positionVerticale % 10) / 2 * 150)), 50, 50);
        }
        else if (positionCourante == 2 || positionCourante == 17)
        {
            fenetreInfoCdC.setVisible(true);
        }
        else if (positionCourante == 7 || positionCourante == 21 || positionCourante == 35)
        {
            fenetreInfoChance.setVisible(true);
        }
        System.out.println("Position Courante à la fin du jet de dé : " + positionCourante);
        return positionCourante;
    }
        
    
    public void clearPlateau()
    {
        fenetreInfoPrison.setVisible(false);
        fenetreInfoCdC.setVisible(false);
        fenetreInfoChance.setVisible(false);
    }
}
