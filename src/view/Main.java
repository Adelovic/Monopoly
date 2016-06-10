package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author schaelth
 */
public class Main 
{
    private static ArrayList<Joueur> list;
    private static int[] Des;
    private static int i = 0;
    
    public static void main(String[] args) 
    {
        list = new ArrayList();
        Des = new int[] {6,6};
        PlayerFrame fenetre = new PlayerFrame();
        fenetre.setSize(620,380);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(fenetre.isB());
        while (fenetre.isB())
        {
            System.out.print("");
        }
        fenetre.setVisible(fenetre.isB());
        list = fenetre.getListJoueurs();
        Ihm2 fenetre1 = new Ihm2(list, list.get(0),Des);
        fenetre1.setSize(1680,1000);
        fenetre1.setVisible(true);
    }
}
