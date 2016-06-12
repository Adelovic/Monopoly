package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.carreaux.AutreCarreau;
import model.carreaux.Carreau;
import model.carreaux.propriete.Compagnie;
import model.CouleurPropriete;
import model.carreaux.propriete.Gare;
import model.Groupe;
import model.Joueur;
import model.Monopoly;
import model.carreaux.propriete.ProprieteConstructible;
import model.Message;
import model.TypeAction;
import model.TypeCarte;
import model.carreaux.CarreauSansAction;
import model.carreaux.CarreauTirage;
import model.carreaux.Prison;
import model.carreaux.Taxe;
import model.carreaux.propriete.Propriete;
import model.cartes.Carte;
import model.cartes.CarteLiberationPrison;
import model.cartes.deplacement.CarteDeplacementAbsolu;
import model.cartes.deplacement.CarteDeplacementRelatif;
import model.cartes.transaction.CarteAnniversaire;
import model.cartes.transaction.CarteReparation;
import model.cartes.transaction.CarteTransactionFixe;
import view.Ihm2;
import view.Observateur;

public class Controleur
{
    /** Plateau de jeu **/
    private Monopoly monopoly;
    
    /** Observateur qui va recevoir les messages depuis jouerCoup **/
    private Observateur observateur;
    
    /** Initialise le plateau **/
    public Controleur()
    {
        monopoly = new Monopoly(); 
    }
    
    public static void main(String[] args)
    {
        new Ihm2(new Controleur());
    }
    
        
    /** Initialise les joueurs en demandant les noms à l'IHM **/
    public void initialiserJoueurs(ArrayList<String> noms)
    {
        for (String nom : noms)
        {
           monopoly.addJoueur(new Joueur(nom));
        }
        
        
        Message message = new Message();
        message.setType(TypeAction.DEBUT_PARTIE);
        message.setJoueurs(monopoly.getJoueurs());
        observateur.notifier(message);
        
        message.setType(TypeAction.DEBUT_COUP);
        message.setJoueur(monopoly.getJoueurCourant());
        observateur.notifier(message);
    }
    
    /**  Lance le jeu en créant propriétés, groupes et joueurs, puis gère la boucle de jeu principale **/
    public void initialiserJeu()
    {
        creerGroupes();
        creerPlateau("src/data/data.txt");
        creerCartes("src/data/data_cartes.txt");
        
        // Placer les joueurs sur la case départ
        for (Joueur joueur : monopoly.getJoueurs())
        {
            joueur.setPositionCourante(monopoly.getCarreau(1));
        }
        //ihm.afficherClassement(gagnant, monopoly.getJoueursElimines());
    }
    
    
    /** Fais avancer le joueur d'un montant de des et traite l'action, et les actions engendrées à la suite **/
    public void jouerCarreau(Joueur joueur)
    {
        
        // Met à jour les propriétés constructible **/
        Carreau carreau = joueur.getPositionCourante();
        Message messageCarreau = carreau.action(joueur);
        messageCarreau.setJoueur(joueur);

        boolean peutRejouer = false;
        boolean passeCaseDepart = false;
        switch (messageCarreau.getType()) 
        {
            case TIRER_CARTE:
                System.out.println(messageCarreau.getTypeCarte());
                Carte carte = monopoly.tirerCarte(messageCarreau.getTypeCarte());
                messageCarreau.setCarte(carte);
                Message messageCarte = carte.actionCarte();
                switch (messageCarte.getType()) 
                {
                    case C_LIBERATION:
                        break;
                    case C_TRANSACTION_FIXE:
                        int montantTransaction = messageCarte.getMontantTransaction();
                        joueur.modifierCash(montantTransaction);
                        break;
                    case C_ANNIVERSAIRE:
                        int montantAnniversaire = messageCarte.getMontantAnniversaire();
                        for (Joueur j : monopoly.getJoueurs()) 
                        {
                            if (j != joueur) 
                            {
                                j.payerA(joueur, montantAnniversaire);
                            }
                        }
                        break;
                    case C_REPARATION:
                        int nbHotels = 0;
                        int nbMaisons = 0;
                        for (ProprieteConstructible p : joueur.getProprietes()) 
                        {
                            nbMaisons += p.getNbMaisons();
                            nbHotels += p.getNbHotels();
                        }
                        int montant = (nbMaisons * messageCarreau.getCoutParMaison()) + (nbHotels * messageCarreau.getCoutParHotel());
                        joueur.removeCash(montant);
                        break;
                    case C_DEPLACEMENT_RELATIF:
                        peutRejouer = true;
                        int deplacement = messageCarte.getDeplacement();
                        passeCaseDepart = monopoly.deplacerJoueur(joueur, deplacement);
                        Message msgDeplacement = new Message();
                        msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                        msgDeplacement.setJoueur(joueur);
                        msgDeplacement.setPasserCaseDepart(passeCaseDepart);
                        observateur.notifier(msgDeplacement);
                        break;
                    case C_DEPLACEMENT_ABSOLU:
                        peutRejouer = true;
                        deplacement = messageCarte.getDeplacement();
                        boolean doitPasserCaseDepart = messageCarte.getPasserCaseDepart();
                        passeCaseDepart = (deplacement < joueur.getPositionCourante().getNumero() && doitPasserCaseDepart) || doitPasserCaseDepart;
                        joueur.setPositionCourante(monopoly.getCarreau(deplacement));
                        msgDeplacement = new Message();
                        msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                        msgDeplacement.setJoueur(joueur);
                        msgDeplacement.setPasserCaseDepart(passeCaseDepart && doitPasserCaseDepart);
                        observateur.notifier(msgDeplacement);
                        break;
                    case PRISON:
                        monopoly.emprisonner(joueur);
                        msgDeplacement = new Message();
                        msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                        msgDeplacement.setJoueur(joueur);
                        observateur.notifier(msgDeplacement);
                        break;

                }
                break;
            case PRISON:
                monopoly.emprisonner(joueur);
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
                break;
            case PAYER_LOYER:
                Joueur proprio = messageCarreau.getPropriete().getProprietaire();
                joueur.payerA(proprio, messageCarreau.getLoyer());
                break;
            case ACHAT:
                break;
            case CONSTRUIRE:
                ProprieteConstructible prop = messageCarreau.getProprieteConstructible();
                
                if (prop.maisonConstructible() && joueur.getCash() > prop.getNbMaisons() && monopoly.getMaisonsDisponibles() > 0)
                {
                    messageCarreau.setPrix(prop.getPrixMaison());
                }
                else if (prop.hotelConstructible() && joueur.getCash() > prop.getNbHotels() && monopoly.getHotelsDisponibles() > 0)
                {
                    messageCarreau.setPrix(prop.getPrixHotel());
                }
                else
                {
                    messageCarreau.setType(TypeAction.RIEN);
                }
                break;
            case RIEN:
                break;
        }
        Message message = new Message();
        message.setJoueur(joueur); 
         
        observateur.notifier(messageCarreau);
        
        if (passeCaseDepart)
        {
            joueur.modifierCash(200);
            message.setType(TypeAction.TOUCHE_CASE_DEPART);
            observateur.notifier(message);
        }
        
        if (joueur.getCash() <= 0) 
        {
            message.setType(TypeAction.ELIMINER_JOUEUR);
            monopoly.eliminerJoueur(joueur); 
            observateur.notifier(message);
        }
        else if (peutRejouer)
        {
            message.setType(TypeAction.REJOUER);            
            observateur.notifier(message);
        }
        else
        {
            message.setType(TypeAction.FIN_COUP);
            observateur.notifier(message);
        }
        
        if (monopoly.getJoueurs().size() == 1)
        {
            Message messageFinPartie = new Message();
            messageFinPartie.setType(TypeAction.FIN_PARTIE);
            messageFinPartie.setJoueur(monopoly.getJoueurs().get(0));
            observateur.notifier(messageFinPartie);
        }
    }
    
    /* La fin d'un coup change le joueur courant et envoie le signal d'un nouveau courant à l'ihm */
    public void finCoup()
    {
        Message message = new Message();
        message.setType(TypeAction.DEBUT_COUP);
        message.setJoueur(monopoly.prochainJoueur());
        observateur.notifier(message);
    
    }
    
    /** Jouer un coup du joueur **/
    public void jouerCoup()
    {
        Joueur joueur = monopoly.getJoueurCourant();
        // Lancer les dés
        int[] des = monopoly.lancerDes();
        joueur.setDernierDes(des);
                        
        // Notification à l'ihm du lancé de dés
        Message msg = new Message();
        msg.setType(TypeAction.LANCER_DES);
        msg.setJoueur(joueur);
        msg.setDerniersDes(joueur.getDernierDes());
        observateur.notifier(msg);
            
        if (joueur.isEnPrison())
        {
            joueur.addTourPrison();
            if (joueur.doubleDes())
            {
                monopoly.liberer(joueur);
                monopoly.deplacerJoueur(joueur, des[0]+des[1]);
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setPasserCaseDepart(false);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
                jouerCarreau(joueur);
            }
            else if (joueur.getTourPrison() == 3)
            {
                joueur.removeCash(50);
                monopoly.liberer(joueur);
                monopoly.deplacerJoueur(joueur, des[0]+des[1]);
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setPasserCaseDepart(false);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
                jouerCarreau(joueur);
            }  
        }
        else
        {
            boolean passeCaseDepart = monopoly.deplacerJoueur(joueur, des[0]+des[1]);
            Message msgDeplacement = new Message();
            msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
            msgDeplacement.setJoueur(joueur);
            msgDeplacement.setPasserCaseDepart(passeCaseDepart);
            observateur.notifier(msgDeplacement);
            jouerCarreau(joueur);
        }
        
        if (joueur.doubleDes() && !joueur.isEnPrison())
        {
            Message message = new Message();
            message.setType(TypeAction.REJOUER_DOUBLE_DES);
            message.setJoueur(joueur);
            observateur.notifier(message);
        }
    }
    
    /** Acheter une propriété, est appelé par l'observateur **/
    public void acheterPropriete(Joueur joueur, Propriete propriete)
    {
        propriete.acheter(joueur);
    }

    /** Construire une propriété, ajoute automatiquement une maison ou un hotel **/
    public void construirePropriete(ProprieteConstructible propriete, int prix)
    {
        propriete.construire();
        propriete.getProprietaire().removeCash(prix);
    }
    /*
    * Crée les groupes de propriété à partir de CouleurPropriete
    * Une couleur représente un groupe
    */
    private void creerGroupes()
    {
        for (CouleurPropriete couleur : CouleurPropriete.values())
        {
            monopoly.addGroupe(new Groupe(couleur));
        }     
    }
    
    /** Crée le plateau en initialisant les propriétés et en les ajoutant au monopoly **/
    private void creerPlateau(String dataFilename)
    {
        try
        {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            
            for(int i=0; i<data.size(); ++i)
            {
                String[] caseInfos = data.get(i);
                String caseType = caseInfos[0];
                if(caseType.compareTo("P") == 0)
                {
                    System.out.println("Propriété :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    ArrayList<Integer> loyers = new ArrayList();
                    for (int j = 6; j <= 10; j++)
                    {
                        loyers.add(Integer.valueOf(caseInfos[j]));
                    }
                    
                    monopoly.addCarreau(new ProprieteConstructible(Integer.valueOf(caseInfos[1]), caseInfos[2], monopoly.getGroupe(CouleurPropriete.valueOf(caseInfos[3])), Integer.valueOf(caseInfos[4]), Integer.valueOf(caseInfos[5]), loyers, Integer.valueOf(caseInfos[11]), Integer.valueOf(caseInfos[12]) ));
                }
                else if(caseType.compareTo("G") == 0)
                {
                    System.out.println("Gare :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new Gare(Integer.valueOf(caseInfos[1]), caseInfos[2], Integer.valueOf(caseInfos[3])));
                }
                else if(caseType.compareTo("C") == 0)
                {
                    System.out.println("Compagnie :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new Compagnie(Integer.valueOf(caseInfos[1]), caseInfos[2], Integer.valueOf(caseInfos[3])));
                }
                else if(caseType.compareTo("CSA") == 0)
                {
                    System.out.println("Case Départ :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new CarreauSansAction(Integer.valueOf(caseInfos[1]), caseInfos[2]));
                }
                else if(caseType.compareTo("PR") == 0)
                {
                    System.out.println("Prison :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new Prison(Integer.valueOf(caseInfos[1]), caseInfos[2]));
                }
                else if(caseType.compareTo("TX") == 0)
                {
                    System.out.println("Carreau taxe :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new Taxe(Integer.valueOf(caseInfos[1]), caseInfos[2], Integer.valueOf(caseInfos[3])));
                }
                else if(caseType.compareTo("CT") == 0)
                {
                    System.out.println("Carreau Tirage :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new CarreauTirage(Integer.valueOf(caseInfos[1]), caseInfos[2], TypeCarte.valueOf(caseInfos[3])));
                }
                else if(caseType.compareTo("AU") == 0)
                {
                    System.out.println("Case Autre :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new AutreCarreau(Integer.valueOf(caseInfos[1]), caseInfos[2]));
                }
                else
                {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }
            
        }
        catch(FileNotFoundException e)
        {
            System.err.println("[buildGamePlateau()] : File not found!");
        }
        catch(IOException e)
        {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
  
    /** Crée les cartes et les ajoute au monopoly **/
    private void creerCartes(String dataFilename)
    {
        try
        {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            
            for(int i=0; i<data.size(); ++i)
            {
                String[] carteInfos = data.get(i);
                
                TypeCarte type = carteInfos[0].equals("ch") ? TypeCarte.CHANCE : TypeCarte.COMMUNAUTE;
                
                String carteType = carteInfos[1];
                
                // Libération prison
                if(carteType.compareTo("LP") == 0)
                {
                    monopoly.addCarte(type, new CarteLiberationPrison(type, carteInfos[2]));
                }
                // Déplacement relatif
                else if(carteType.compareTo("DA") == 0)
                {
                    
                    monopoly.addCarte(type, new CarteDeplacementAbsolu(type, carteInfos[2], Integer.parseInt(carteInfos[3]), Boolean.valueOf(carteInfos[4])));
                }
                // Déplacement fixe
                else if(carteType.compareTo("DR") == 0)
                {
                    monopoly.addCarte(type, new CarteDeplacementRelatif(type, carteInfos[2], Integer.parseInt(carteInfos[3])));
                }
                // Transaction propriété
                else if(carteType.compareTo("TP") == 0)
                {
                   monopoly.addCarte(type, new CarteReparation(type, carteInfos[2], Integer.parseInt(carteInfos[3]), Integer.parseInt(carteInfos[4]))); 
                }
                // Transaction fixe 
                else if(carteType.compareTo("TF") == 0)
                {
                    monopoly.addCarte(type, new CarteTransactionFixe(type, carteInfos[2], Integer.parseInt(carteInfos[3])));
                }
                // Transaction anniversaire 
                else if(carteType.compareTo("TA") == 0)
                {
                    monopoly.addCarte(type, new CarteAnniversaire(type, carteInfos[2]));
                }
                else
                {
                    System.err.println("[creerCartes()] : Invalid Data type");
                }
            }
            
        }
        catch(FileNotFoundException e)
        {
            System.err.println("[buildGamePlateau()] : File not found!");
        }
        catch(IOException e)
        {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
    
    /** Lis le fichier des carreaux et le renvoie sous forme de matrice **/
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
    {
        ArrayList<String[]> data = new ArrayList<String[]>();
        
        BufferedReader reader  = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = reader.readLine()) != null)
        {
            data.add(line.split(token));
        }
        
        reader.close();
        
        return data;
    }

    /** Définit l'observateur à qui on enverra les messages **/
    public void setObservateur(Observateur observateur) 
    {
        this.observateur = observateur;
    }
}

