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
import model.cartes.Carte;
import model.cartes.CarteLiberationPrison;
import model.cartes.deplacement.CarteDeplacement;
import model.cartes.transaction.CarteAnniversaire;
import model.cartes.transaction.CarteReparation;
import model.cartes.transaction.CarteTransactionFixe;

public class Controleur
{
    private Monopoly monopoly;
    
    
    private Ihm ihm;
    
    
    public Controleur()
    {
        monopoly = new Monopoly(); 
        ihm = new Ihm(this);
        lancerJeu();
    }
    
    public static void main(String[] args)
    {
        new Controleur();
    }
    
    /*
    * Lance le jeu en créant propriétés, groupes et joueurs, puis gère la boucle de jeu principale
    */
    private void lancerJeu()
    {
        creerGroupes();
        creerPlateau("src/data/data.txt");
        initialiserJoueurs();
        
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
        ihm.notifierGagnant(gagnant);
        ihm.afficherClassement(gagnant, monopoly.getJoueursElimines());
    }
    
    /*
    * Initialise les joueurs en demandant les noms à l'IHM
    */
    private void initialiserJoueurs()
    {
        int nombreJoueur = ihm.demanderNombreJoueur();
        
        for (int i = 1; i <= nombreJoueur; i++)
        {
           String nom = ihm.demanderNom(i);
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
        boolean peutJouer = true;
        
        // Lancer les dés
        Carreau carreau = lancerDesEtAvancer(joueur);
        
        // Notification à l'ihm du lancé de dés
        Message msg = new Message();
        msg.setType(TypeAction.LANCER_DES);
        msg.setDerniersDes(joueur.getDernierDes());
        ihm.notifier(msg);
        
        // Si en prison
            // Incrémenter tourPrison
            // Si double ou si tourPrison = 3
                // Libérer et réinitialiser tourPrison
        // Sinon
        boolean peutRejouer = true;
        while (peutRejouer && joueur.getCash() > 0)
        {
            Message message = carreau.action(joueur);


            switch (message.getType())
            {
                case TIRER_CARTE:
                    Carte carte = monopoly.tirerCarte(message.getTypeCarte());
                    Message messageCarte = carte.actionCarte(joueur);
                            switch (messageCarte.getType())
                            {
                                case C_LIBERATION :
                                case C_TRANSACTION_FIXE :
                                    int montantTransaction = message.getMontantTransaction();
                                    joueur.retirerCash(montantTransaction);
                                case C_ANNIVERSAIRE : 
                                case C_REPARATION : 
                                case C_DEPLACEMENT :
                                case PRISON : 
                            }


                case PRISON:
                    ihm.notifier(message);
                    monopoly.emprisonner(joueur);
                case PAYER_LOYER:
                    Joueur proprio = message.getPropriete().getProprietaire();
                    int loyer = message.getPropriete().calculLoyer();
                    int cashJ = joueur.getCash();
                    int aPrendre = cashJ > loyer ? loyer : cashJ;
                    joueur.removeCash(aPrendre);
                    proprio.addCash(aPrendre);     
                    ihm.notifier(message);
                case ACHAT:
                    ihm.notifier(message);
                    break;
                case RIEN: 
                    ihm.notifier(message);
                    break;


            }
    //        if (action != null)
    //        {
    //            ihm.notifier(action.getMessage());
    //            boolean reponseJ = true;
    //            if (action.entraineDemande())
    //            {
    //                reponseJ = ihm.demanderJoueur();
    //            }
    //
    //            Message res = action.faireAction(reponseJ);
    //
    //            ihm.notifier(res.getMessage());
    //            
    //            if (joueur.getCash() == 0)
    //            {
    //                monopoly.eliminerJoueur(joueur);
    //                ihm.joueurElimine(joueur);
    //                elimine = true;
    //            }
    //            else
    //            {
    //                ihm.afficherInfoJoueur(joueur);
    //                
    //            }
    //            
    //        }
    //        else
    //        {
    //            ihm.notifierCarreauSansAction(joueur, carreau);
    //            ihm.afficherInfoJoueur(joueur);
    //        }
    //        
    //        if (!elimine && joueur.getDernierDes()[0] == joueur.getDernierDes()[1])
    //        {
    //            ihm.notifierDoubleDes(joueur);
    //            jouerCoup(joueur);
    //        }

            ihm.notifier(message);
        }
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
    * Lance les des, met à jour les derniers dés du joueur
    * et renvoie sa position courant mise à jour
    */
    private Carreau lancerDesEtAvancer(Joueur j)
    {
        int[] des = lancerDes();
        j.setDernierDes(des);
        
        monopoly.avancerJoueur(j, des);
        
        return j.getPositionCourante();
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
                    for (int j = 6; j <= 10; i++)
                    {
                        loyers.add(Integer.valueOf(caseInfos[i]));
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
                else if(carteType.compareTo("DR") == 0)
                {
                    
                    monopoly.addCarte(type, new CarteDeplacement(type, carteInfos[2], Integer.parseInt(carteInfos[3]), true, Boolean.valueOf(carteInfos[3])));
                }
                // Déplacement fixe
                else if(carteType.compareTo("DF") == 0)
                {
                    monopoly.addCarte(type, new CarteDeplacement(type, carteInfos[2], Integer.parseInt(carteInfos[3]), false, Boolean.valueOf(carteInfos[3])));
                }
                // Transaction propriété
                else if(carteType.compareTo("TP") == 0)
                {
                   monopoly.addCarte(type, new CarteReparation(type, carteInfos[2], Integer.parseInt(carteInfos[3]))); 
                }
                // Transaction fixe 
                else if(carteType.compareTo("TF") == 0)
                {
                    monopoly.addCarte(type, new CarteTransactionFixe(type, carteInfos[2], Integer.parseInt(carteInfos[3])));
                }
                // Transaction anniversaire 
                else if(carteType.compareTo("TA") == 0)
                {
                    monopoly.addCarte(type, new CarteAnniversaire(type, carteInfos[2], Integer.parseInt(carteInfos[3])));
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
}

