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
import model.Joueur;

public class Ihm 
{
    private Controleur controleur;
    
    public Ihm(Controleur controleur)
    {
        this.controleur = controleur;
    }
    
    public void notifier(String msg)
    {
        System.out.println(msg);
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
    
    public String demanderNom(int numeroJoueur)
    {
        System.out.println("----------------------------------------------------");
        System.out.println("Nom du joueur " + numeroJoueur + " ?");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    
    public int demanderNombreJoueur()
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
        System.out.println("- Argent : " + joueur.getCash());
        System.out.println("- Nombre de propriétés à construire : " + joueur.getNbProprieteAConstruire());
        System.out.println("- Nombre de gares : " + joueur.getNbGares());
        System.out.println("- Nombre de compagnies : " + joueur.getNbCompagnies());
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
}
