package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
import model.carreaux.CarreauPrison;
import model.carreaux.CarreauTaxe;
import model.carreaux.propriete.Propriete;
import model.cartes.Carte;
import model.cartes.CarteLiberationPrison;
import model.cartes.deplacement.CarteDeplacementAbsolu;
import model.cartes.deplacement.CarteDeplacementRelatif;
import model.cartes.transaction.CarteAnniversaire;
import model.cartes.CarteReparation;
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
        
        monopoly.melangerCartes();
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

        /* Utilisé pour savoir si l'utilisateur peut rejouer après une action */
        boolean peutRejouer = false;
        
        /* Utilisé pour savoir si le joueur passe par la case départ */
        boolean passeCaseDepart = false;
        
        /* On traite chaque action reçue par le carreau */
        switch (messageCarreau.getType()) 
        {
            case TIRER_CARTE:
                Carte carte = monopoly.tirerCarte(messageCarreau.getTypeCarte());
                messageCarreau.setCarte(carte);
                Message messageCarte = carte.actionCarte();
                switch (messageCarte.getType()) 
                {
                    /* Stocker la carte libération */
                    case C_LIBERATION:
                        break;
                        
                    /* Effectuer une transaction (Retirer ou ajouter) */
                    case C_TRANSACTION_FIXE:
                        int montantTransaction = messageCarte.getMontantTransaction();
                        joueur.modifierCash(montantTransaction);
                        break;
                        
                    /* Chaque joueur paye un montant fixe au joueur en question */
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
                        
                    /* Le joueur repare ses proprietes, paye en fonction du nombre de maison et d'hotels */
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
                        
                    /* Déplace le joueur de X cases suivant sa position */
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
                        
                    /* Déplace le joueur à une case donnée */
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
                        
                    /* Emprisonne le joueur */
                    case ALLER_PRISON:
                        monopoly.emprisonner(joueur);
                        msgDeplacement = new Message();
                        msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                        msgDeplacement.setJoueur(joueur);
                        observateur.notifier(msgDeplacement);
                        break;
                }
                break;
                
            /* Emprisonne le joueur */
            case ALLER_PRISON:
                monopoly.emprisonner(joueur);
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
                break;
                
            /* Paye le loyer du carreau sur lequel il est tombé */
            case PAYER_LOYER:
                Joueur proprio = messageCarreau.getPropriete().getProprietaire();
                joueur.payerA(proprio, messageCarreau.getLoyer());
                break;
            case C_TRANSACTION_FIXE:
                System.out.println("WHY ? " + messageCarreau.getMontantTransaction());
                joueur.modifierCash(messageCarreau.getMontantTransaction());
                System.out.println(joueur.getCash());
                break;
            /* Aucune action n'est effectuée */
            case RIEN:
                break;
        }
        
        /* Envoyer le message au carreau */      
        observateur.notifier(messageCarreau);
        
        /* Construire le nouveau packet à envoyer en fonction de l'état du joueur */
        Message message = new Message();
        message.setJoueur(joueur); 
        
        /* Chaque fin d'action d'un carreau, renvoyer la liste des proprietes modifiables (maisons, hotels) du joueur */
        monopoly.actualiserProprietesConstructibles(joueur);
        
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
            messageFinPartie.setJoueurs(monopoly.getJoueursElimines());
            observateur.notifier(messageFinPartie);
        }
    }
    
    /* La fin d'un coup change le joueur courant et envoie le signal d'un nouveau courant à l'ihm */
    public void finCoup()
    {
        Joueur joueur = monopoly.prochainJoueur();
        Message message = new Message();
        message.setType(TypeAction.DEBUT_COUP);
        message.setJoueurs(monopoly.getJoueurs());
        message.setJoueur(joueur);
        observateur.notifier(message);
       
        /* On notifie dés le début si le joueur est en prison */
        if (joueur.isEnPrison())
        {
            joueur.addTourPrison();
            
            message.setType(TypeAction.PRISON);
            observateur.notifier(message);
        }
    
    }
    
        /** Jouer un coup du joueur **/
    public void jouerCoup(int de1, int de2)
    {
        Joueur joueur = monopoly.getJoueurCourant();
        // Lancer les dés
        int[] des = new int[] { de1, de2 };
        joueur.setDernierDes(des);

        // Notification à l'ihm du lancé de dés
        Message msg = new Message();
        msg.setType(TypeAction.LANCER_DES);
        msg.setJoueur(joueur);
        msg.setDerniersDes(joueur.getDernierDes());
        observateur.notifier(msg);
            
        /* On ne joue pas le coup si le joueur est en prison, on verifie simplement le résultat des dés et le nombre de tour en prison */
        if (joueur.isEnPrison())
        {
            Message messagePrison = new Message();
            messagePrison.setJoueur(joueur);
            if (joueur.doubleDes())
            {
                monopoly.liberer(joueur);
                monopoly.deplacerJoueur(joueur, des[0]+des[1]);
                messagePrison.setType(TypeAction.DEPLACER_JOUEUR);
                messagePrison.setPasserCaseDepart(false);
                jouerCarreau(joueur);
            }
            else if (joueur.getTourPrison() < 3)
            {
                messagePrison.setType(TypeAction.FIN_COUP);
                messagePrison.setPasserCaseDepart(false);
            }
            observateur.notifier(messagePrison);
        }
        /* Sinon, on joue */
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
        
        /* Si le joueur a fait un double dé et qu'il n'est pas/plus en prison, on rejoue */
        if (joueur.doubleDes() && !joueur.isEnPrison())
        {
            joueur.addDoubleDes();
            Message message = new Message();
            message.setJoueur(joueur);
            
            /* On envoie en prison le joueur et on envoie le message de déplacement à l'ihm */
            if (joueur.getNbDoubleDes() == 3)
            {
                monopoly.emprisonner(joueur);
                message.setType(TypeAction.ALLER_PRISON);
                joueur.resetNbDoubleDe();
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
            }
            else
            {
                message.setType(TypeAction.REJOUER_DOUBLE_DES);
            }
            observateur.notifier(message);
        }
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
            
        /* On ne joue pas le coup si le joueur est en prison, on verifie simplement le résultat des dés et le nombre de tour en prison */
        if (joueur.isEnPrison())
        {
            Message messagePrison = new Message();
            messagePrison.setJoueur(joueur);
            if (joueur.doubleDes())
            {
                monopoly.liberer(joueur);
                monopoly.deplacerJoueur(joueur, des[0]+des[1]);
                messagePrison.setType(TypeAction.DEPLACER_JOUEUR);
                messagePrison.setPasserCaseDepart(false);
                jouerCarreau(joueur);
            }
            else if (joueur.getTourPrison() < 3)
            {
                messagePrison.setType(TypeAction.FIN_COUP);
                messagePrison.setPasserCaseDepart(false);
            }
            observateur.notifier(messagePrison);
        }
        /* Sinon, on joue */
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
        
        /* Si le joueur a fait un double dé et qu'il n'est pas/plus en prison, on rejoue */
        if (joueur.doubleDes() && !joueur.isEnPrison())
        {
            joueur.addDoubleDes();
            Message message = new Message();
            message.setJoueur(joueur);
            
            /* On envoie en prison le joueur et on envoie le message de déplacement à l'ihm */
            if (joueur.getNbDoubleDes() == 3)
            {
                monopoly.emprisonner(joueur);
                message.setType(TypeAction.ALLER_PRISON);
                joueur.resetNbDoubleDe();
                Message msgDeplacement = new Message();
                msgDeplacement.setType(TypeAction.DEPLACER_JOUEUR);
                msgDeplacement.setJoueur(joueur);
                observateur.notifier(msgDeplacement);
            }
            else
            {
                message.setType(TypeAction.REJOUER_DOUBLE_DES);
            }
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
        monopoly.actualiserProprietesConstructibles(propriete.getProprietaire());
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
                    monopoly.addCarreau(new CarreauPrison(Integer.valueOf(caseInfos[1]), caseInfos[2]));
                }
                else if(caseType.compareTo("TX") == 0)
                {
                    System.out.println("Carreau taxe :\t" + caseInfos[2] + "\t@ case " + data.get(i)[1]);
                    monopoly.addCarreau(new CarreauTaxe(Integer.valueOf(caseInfos[1]), caseInfos[2], Integer.valueOf(caseInfos[3])));
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
    
    public void delivrerAvecCaution(Joueur joueur)
    {
        joueur.removeCash(50);
        monopoly.liberer(joueur);
        Message message = new Message();
        message.setJoueur(joueur);
        message.setType(TypeAction.FIN_COUP);
        observateur.notifier(message);
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

