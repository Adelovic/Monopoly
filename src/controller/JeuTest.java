/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Scanner;
import view.Ihm2;

/**
 *
 * @author Pyroenil
 */
public class JeuTest 
{
    
    public static void main(String[] args)
    {     
        Controleur controleur = new Controleur();
        Ihm2 ihmTest = new Ihm2(controleur);
        int[][] des = new int[][] 
        { 
            {1, 1}, // P1 : Caisse de communauté
            {1, 1}, // P1 : Carte impot sur le revenu
            {2, 1}, // P1 : Carte chance
            {2, 1}, // P2 : Rue lecourbe
            {6, 6}, // P1 : Place pigalle
            {6, 5}, // P1 : Prison
            {6, 6}, // P2 : Gare de lyon
            {6, 6}, // P2 : Acheter -> 2 doubles et pas de prison
            {5, 6}, // P2 : Rue de la paix 
            {1, 1}, // P1 : Sortie de prison -> Compagnie d'electricité
            {2, 1}, // P1 : Paye Gare de Lyon à P2
            {1, 1}, // P2 : Boulevard de la ville
            {1, 1}, // P2 : Rue lecourbe -> Construire
            {3, 1}, // P2 : Carte chance
                
                
        };
                
        Scanner scanner = new Scanner(System.in);
        
        int avancement = 0;
        while (avancement < des.length)
        {     
            System.out.println("APPUYER SUR ENTRER");
            scanner.nextLine();
            controleur.jouerCoup(des[avancement][0], des[avancement][1]);
            avancement++;
        }
        
        
    }
    
}
