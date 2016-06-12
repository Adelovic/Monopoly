package model;

import java.util.ArrayList;
import model.carreaux.propriete.Propriete;
import model.carreaux.propriete.ProprieteConstructible;
import model.cartes.Carte;

public class Message
{
    
    
    /*
    * Type d'action qui a été exécuté. Est utilisé par l'IHM pour savoir quoi afficher 
    * et quelles informations récupérer
    */
    private TypeAction type;
    
    private Joueur proprietaire;
    
    private Joueur joueur;
    private TypeCarte typeCarte;
    
    /*
    * Représente le succès d'exécution de l'action
    * Si il n'y a pas succès, il y a élimination du joueur
    */
    private boolean succes;
    
    /*
    * Le message sert à notifier le joueur de l'action et de son déroulement
    */
    private String message;
    private int loyer;
    private int prix;
    private int montantTransaction;
    private Propriete propriete;
    private int[] derniersDes;
    private int montantAnniversaire;
    
    private Carte carte;
    
    private int deplacement;
    
    private boolean passerCaseDepart;
    
    private int coutParMaison;
    private int coutParHotel;
    private ProprieteConstructible proprieteConstructible;
    private ArrayList<Joueur> joueurs;
    
    private boolean estConstructible;

    
    public void setEstConstructible(boolean estConstructible)
    {
        this.estConstructible = estConstructible;
    }
    
    public boolean getEstConstructible()
    {
        return estConstructible;
    }
    public void setJoueurs(ArrayList<Joueur> joueurs) 
    {
        this.joueurs = joueurs;
    }

    public ArrayList<Joueur> getJoueurs() 
    {
        return joueurs;
    }
    
    

    public void setProprieteConstructible(ProprieteConstructible prop)
    {
        this.proprieteConstructible = prop;
    }
    
    public ProprieteConstructible getProprieteConstructible()
    {
        return proprieteConstructible;
    }
    
    public int getMontantAnniversaire() {
        return montantAnniversaire;
    }

    public void setMontantAnniversaire(int montantAnniversaire) {
        this.montantAnniversaire = montantAnniversaire;
    }

    
    
    public void setCoutParMaison(int coutParMaison) 
    {
        this.coutParMaison = coutParMaison;
    }

    public void setCoutParHotel(int coupParHotel) 
    {
        this.coutParHotel = coupParHotel;
    }

    public int getCoutParMaison() {
        return coutParMaison;
    }

    public int getCoutParHotel() {
        return coutParHotel;
    }

    public void setDeplacement(int deplacement)
    {
        this.deplacement = deplacement;
    }
    
    public int getDeplacement() 
    {
        return deplacement;
    }
    
    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Carte getCarte() {
        return carte;
    }
    
    
    public Propriete getPropriete() {
        return propriete;
    }

    public void setMontantTransaction(int montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    public int getMontantTransaction() {
        return montantTransaction;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrix() {
        return prix;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }
    
    private ArrayList<Joueur> listeJoueurs;
    
    public int getLoyer() {
        return loyer;
    }
 
    public void setType(TypeAction type) {
        this.type = type;
    }

    public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setTypeCarte(TypeCarte typeCarte) {
        this.typeCarte = typeCarte;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeAction getType() {
        return type;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public TypeCarte getTypeCarte() {
        return typeCarte;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setPropriete(Propriete prop) {
        this.propriete = prop;
    }

    public int[] getDerniersDes() {
        return derniersDes;
    }

    public void setDerniersDes(int[] dernierDes) {
        this.derniersDes = dernierDes;
    }
    
    public boolean getPasserCaseDepart()
    {
        return passerCaseDepart;
    }
    
    public void setPasserCaseDepart(boolean passerCaseDepart)
    {
        this.passerCaseDepart = passerCaseDepart;
    }

    
}