package controller;

import view.Ihm;
import model.Action;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import model.AutreCarreau;
import model.Carreau;
import model.Compagnie;
import model.CouleurPropriete;
import model.Gare;
import model.Groupe;
import model.Joueur;
import model.Monopoly;
import model.ProprieteAConstruire;
import model.ResultatAction;

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
        Carreau carreau = lancerDesEtAvancer(joueur);
        
        ihm.notifierLancerDes(joueur);
        
        Action action = carreau.action(joueur);
        
        boolean elimine = false;
        
        if (action != null)
        {
            ihm.notifier(action.getMessage());
            boolean reponseJ = true;
            if (action.entraineDemande())
            {
                reponseJ = ihm.demanderJoueur();
            }

            ResultatAction res = action.faireAction(reponseJ);

            ihm.notifier(res.getMessage());
            
            if (!res.getSucces())
            {
                monopoly.eliminerJoueur(joueur);
                ihm.joueurElimine(joueur);
                elimine = true;
            }
            else
            {
                ihm.afficherInfoJoueur(joueur);
                
            }
            
        }
        else
        {
            System.out.println(joueur.getNom() + " est tombé sur le carreau " + carreau.getNom() + " et ne peut rien faire !");
            ihm.afficherInfoJoueur(joueur);
        }
        
        if (!elimine && joueur.getDernierDes()[0] == joueur.getDernierDes()[1])
        {
            ihm.notifierDoubleDes(joueur);
            jouerCoup(joueur);
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
                    monopoly.addCarreau(new ProprieteAConstruire(Integer.valueOf(caseInfos[1]), caseInfos[2], monopoly.getGroupe(caseInfos[3]), Integer.valueOf(caseInfos[4]), Integer.valueOf(caseInfos[5])));
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

