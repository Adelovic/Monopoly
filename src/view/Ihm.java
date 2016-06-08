/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.Controleur;
import model.carreaux.Carreau;
import model.Joueur;
import model.Message;

public class Ihm implements Observateur
{
    private Controleur controleur;
    
    public Ihm(Controleur controleur)
    {
        this.controleur = controleur;
        controleur.setObservateur(this);
        
        controleur.initialiserJoueurs(demanderNoms());
        controleur.lancerJeu();
    }
    
    public boolean demanderJoueur()
    {
        Scanner scan = new Scanner(System.in);
        
        String reponse = "";
        while (!reponse.equals("oui") && !reponse.equals("non"))
        {
            System.out.println("Oui ou non ?");
            reponse = scan.nextLine().toLowerCase();
        }   
        return reponse.equals("oui");
    }
    
    private String demanderNom(int numeroJoueur)
    {
        String nom = "";
        while (nom.isEmpty() || nom.contains(" "))
        {
            System.out.println("----------------------------------------------------");
            System.out.println("Nom du joueur " + numeroJoueur + " ? (Sans espaces)");
            Scanner scan = new Scanner(System.in);
            nom = scan.nextLine();
        }
        return nom;
    }
    
    private ArrayList<String> demanderNoms()
    {
        int nombreJoueur = demanderNombreJoueur();
        ArrayList<String> nomJoueurs = new ArrayList<String>();
        int i = 0;
        while (i < nombreJoueur)
        {
            String nom = demanderNom(i+1);
            if (nomJoueurs.contains(nom))
            {
                System.out.println("Le nom existe déjà!");
            }
            else
            {
                nomJoueurs.add(nom);
                i++;
            }
        }
        return nomJoueurs;
    }
    
    private int demanderNombreJoueur()
    {
        int nombreJoueur = 0;
        while (nombreJoueur <= 1 || nombreJoueur > 6)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Combien de joueurs souhaitez-vous inscrire ? (2-6)");
            try
            {
                nombreJoueur = scan.nextInt();
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Merci de rentrer un entier !");
            }
        }
        return nombreJoueur;
    }
    
    public void afficherInfoJoueur(Joueur joueur)
    {   System.out.println("------------------- INFORMATIONS -------------------");
        System.out.println(joueur.getNom() + " :");
        System.out.println("- Argent : " + joueur.getCash() + "$");
        System.out.println("- Position : " + joueur.getPositionCourante().getNumero());
        System.out.println("- Nombre de propriétés à construire : " + joueur.getNbProprieteAConstruire());
        System.out.println("- Nombre de gares : " + joueur.getNbGares());
        System.out.println("- Nombre de compagnies : " + joueur.getNbCompagnies());
        System.out.println("");
        System.out.println("");
        
    }
    
    public void notifierCarreauSansAction(Joueur joueur, Carreau carreau)
    {
        System.out.println(joueur.getNom() + " est tombé sur le carreau " + carreau.getNom() + " et ne peut rien faire !");
    }
    
    public void joueurElimine(Joueur j)
    {
        System.out.println(j.getNom() + " est éliminé(e)");
    }
    
    public void notifierDoubleDes(Joueur j)
    {
        System.out.println("----------------------------------------------------");                
        System.out.println(j.getNom() + " a fait un double de " + j.getDernierDes()[0] + " au dernier tour et peut donc rejouer !");
    }
    
    public void notifierLancerDes(Joueur j)
    {
        System.out.println("----------------------------------------------------");        
        System.out.println(j.getNom() + " a lancé les dés et a fait " + j.getDernierDes()[0] + " et " + j.getDernierDes()[1]);
    }
    
    public void notifierGagnant(Joueur j)
    {
        System.out.println("----------------------------------------------------");
        System.out.println(j.getNom() + " a gagné la partie ! ");
    }
    
    public void afficherClassement(Joueur gagnant, ArrayList<Joueur> joueursElimine)
    {
        System.out.println("Classement final : ");
        System.out.println("1 : " + gagnant.getNom());
        for (int i = 0; i < joueursElimine.size(); i++)
        {
            System.out.println((i+2) + " : " + joueursElimine.get(joueursElimine.size()-i-1).getNom());
        }
        System.out.println("----------------------------------------------------");
    }

    @Override
    public void notifier(Message message) 
    {
        switch (message.getType())
        {
            case TIRER_CARTE:
                System.out.println("Le joueur " + message.getJoueur().getNom() + " tire une carte!");
                break;
            case LANCER_DES:
            case PRISON:
            case PAYER_LOYER:
            case REFUS_ACHAT:
            case ACHAT:
            case RIEN:
            default:
                break;
        }
    }
}
