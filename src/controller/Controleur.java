package controller;

import view.Ihm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import model.carreaux.AutreCarreau;
import model.carreaux.Carreau;
import model.carreaux.propriete.Compagnie;
import model.CouleurPropriete;
import model.carreaux.propriete.Gare;
import model.Groupe;
import model.Joueur;
import model.Monopoly;
import model.carreaux.propriete.ProprieteAConstruire;
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
import model.cartes.deplacement.CarteDeplacement;
import model.cartes.deplacement.CarteDeplacementAbsolu;
import model.cartes.deplacement.CarteDeplacementRelatif;
import model.cartes.transaction.CarteAnniversaire;
import model.cartes.transaction.CarteReparation;
import model.cartes.transaction.CarteTransactionFixe;
import view.Observateur;

public class Controleur
{
    private Monopoly monopoly;
    
    private Observateur observateur;
    
    
    public Controleur()
    {
        monopoly = new Monopoly(); 
    }
    
    public static void main(String[] args)
    {
        new Ihm(new Controleur());
    }
    
    /*
    * Lance le jeu en créant propriétés, groupes et joueurs, puis gère la boucle de jeu principale
    */
    public void lancerJeu()
    {
        creerGroupes();
        creerPlateau("src/data/data.txt");
        creerCartes("src/data/data_cartes.txt");
        
        
        // Placer les joueurs sur la case départ
        for (Joueur joueur : monopoly.getJoueurs())
        {
            joueur.setPositionCourante(monopoly.getCarreau(1));
        }
        
        int numJoueurCourant = 0;
        while (monopoly.getJoueurs().size() > 1)
        {
            Joueur joueurCourant = monopoly.getJoueurs().get(numJoueurCourant);
            jouerCoup(joueurCourant);
            
            numJoueurCourant++;
            if (numJoueurCourant >= monopoly.getJoueurs().size())
            {
                numJoueurCourant = 0;
            }
        }
        Joueur gagnant = monopoly.getJoueurs().get(0);
        //ihm.notifierGagnant(gagnant);
        //ihm.afficherClassement(gagnant, monopoly.getJoueursElimines());
    }
    
    /*
    * Initialise les joueurs en demandant les noms à l'IHM
    */
    public void initialiserJoueurs(ArrayList<String> noms)
    {
        for (String nom : noms)
        {
           monopoly.addJoueur(new Joueur(nom));
        }
    }
    
    /*
    * Joue un coup du joueur
    * Execute l'action renvoyée par le carreau et traite la réponse de l'exécution
    * Gère les doubles dés et se rappelle si les dés sont identiques
    */
    private void jouerCoup(Joueur joueur)
    {
        if (joueur.isEnPrison())
        {
            joueur.addTourPrison();
            if (joueur.doubleDes() || joueur.getTourPrison() > 3)
            {
                monopoly.liberer(joueur);
                jouerCoup(joueur);
            }
        }
        else
        {
            // Lancer les dés
            int[] des = lancerDes();
            joueur.setDernierDes(des);
            Carreau carreau = monopoly.deplacerJoueur(joueur, des[0]+des[1]);
            carreau.setDernierJoueur(joueur);
            // Notification à l'ihm du lancé de dés
            Message msg = new Message();
            msg.setType(TypeAction.LANCER_DES);
            msg.setDerniersDes(joueur.getDernierDes());
            observateur.notifier(msg);
            
            boolean peutRejouer = true;
            while (peutRejouer && joueur.getCash() > 0)
            {
                peutRejouer = false;
                Message message = carreau.action(joueur);
                message.setJoueur(joueur);


                switch (message.getType())
                {
                    case TIRER_CARTE:
                        Carte carte = monopoly.tirerCarte(message.getTypeCarte());
                        message.setCarte(carte);
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
                                break;
                            case C_REPARATION:
                                break;
                            case C_DEPLACEMENT_RELATIF:
                                peutRejouer = true;
                                int deplacement = messageCarte.getDeplacement();
                                carreau = monopoly.deplacerJoueur(joueur, deplacement);
                                break;
                            case C_DEPLACEMENT_ABSOLU:
                                peutRejouer = true;
                                deplacement = messageCarte.getDeplacement();
                                carreau = monopoly.getCarreau(deplacement);
                                joueur.setPositionCourante(carreau);
                                break;
                            case PRISON:
                                monopoly.emprisonner(joueur);
                                break;
                        }
                        break;
                    case PRISON:
                        monopoly.emprisonner(joueur);
                        break;
                    case PAYER_LOYER:
                        Joueur proprio = message.getPropriete().getProprietaire();
                        int loyer = message.getLoyer();
                        int cashJ = joueur.getCash();
                        int aPrendre = cashJ > loyer ? loyer : cashJ;
                        joueur.removeCash(aPrendre);
                        proprio.addCash(aPrendre);     
                        break;
                    case ACHAT:
                        break;
                    case RIEN: 
                        break;


                }
                observateur.notifier(message);
            }
            
            if (joueur.getCash() <= 0)
            {
                System.out.println("Le joueur " + joueur.getNom() + " a été éliminé");
                monopoly.eliminerJoueur(joueur);
            }
        }
    }
    
    
    public void acheterPropriete(Joueur joueur, Propriete propriete)
    {
        propriete.acheter(joueur);
    }
    /*
    * Lance deux dés et les renvoie sous forme de liste
    */
    private int[] lancerDes()
    { 
        Random random = new Random();
        return new int[] { random.nextInt(6)+1, random.nextInt(6)+1 };
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
    
    /*
    * Crée le plateau en initialisant les propriétés
    * et en les ajoutant au monopoly
    */
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
                    
                    monopoly.addCarreau(new ProprieteAConstruire(Integer.valueOf(caseInfos[1]), caseInfos[2], monopoly.getGroupe(CouleurPropriete.valueOf(caseInfos[3])), Integer.valueOf(caseInfos[4]), Integer.valueOf(caseInfos[5]), loyers, Integer.valueOf(caseInfos[11]), Integer.valueOf(caseInfos[12]) ));
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
                    monopoly.addCarreau(new CarreauTirage(Integer.valueOf(caseInfos[1]), caseInfos[2]));
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
  
    private void creerCartes(String dataFilename)
    {
        try
        {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            
            for(int i=0; i<data.size(); ++i)
            {
                String[] carteInfos = data.get(i);
                
                TypeCarte type = carteInfos.equals("ch") ? TypeCarte.CHANCE : TypeCarte.COMMUNAUTE;
                
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
    
    /*
    * Lis le fichier des carreaux et le renvoie sous forme de matrice
    */
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

    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }
}

