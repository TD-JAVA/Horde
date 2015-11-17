package horde;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seb
 */
public class Joueur {
    
    private String nom; //Nom du joueur 
    private int pdv; // Points de vie du joueur
    private int pa; // Points d'action du joueur
    private int nbTours; //Affiche le tour courant
    private int nbJours; // Affiche le jour courant
    private int gourde; // nb de gourdes par jour

    public Joueur(String nom, int pdv, int pa, int nbTours, int nbJours, int gourde) {
        this.nom = nom;
        this.pdv = pdv;
        this.pa = pa;
        this.nbTours = nbTours;
        this.nbJours = nbJours;
        this.gourde = gourde;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the pdv
     */
    public int getPdv() {
        return pdv;
    }

    /**
     * @param pdv the pdv to set
     */
    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    /**
     * @return the pa
     */
    public int getPa() {
        return pa;
    }

    /**
     * @param pa the pa to set
     */
    public void setPa(int pa) {
        this.pa = pa;
    }

    /**
     * @return the nbTours
     */
    public int getNbTours() {
        return nbTours;
    }

    /**
     * @param nbTours the nbTours to set
     */
    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    /**
     * @return the nbJours
     */
    public int getNbJours() {
        return nbJours;
    }

    /**
     * @param nbJours the nbJours to set
     */
    public void setNbJours(int nbJours) {
        this.nbJours = nbJours;
    }

    /**
     * @return the gourde
     */
    public int getGourde() {
        return gourde;
    }

    /**
     * @param gourde the gourde to set
     */
    public void setGourde(int gourde) {
        this.gourde = gourde;
    }
    
    
    // ---------------DEBUT DES METHODES---------------------
    
    // Permet au joueur de boire de l'eau
    public void boire(){
        
    }
    
    // correspond à la méthode déplacement
    public void deplacement(){
        
    }
    
    //Permet au joueur de manger une ration s'il en dispose
    public void manger(){
        
    }
    
    // Permet au joueur de construire des défenses
    public void construire(){
        
    }
    
    // Permet au joueur de terminer son tour sans consommer tous ses points d'action
    public void terminerTour(){
        
        
    }
    
    // Permet au joueur de remplir son sac
    public void remplirSac(){
        
    }
    
    // Permet au joueur de vider son sac
    public void viderSac(){
        
        
    }
}   
