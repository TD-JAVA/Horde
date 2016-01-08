package horde;

import java.util.ArrayList;

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

    //Nom du joueur 
    private String nom;

    // Points de vie du joueur
    private int pdv;

    // Points d'action du joueur
    private int pa;

    //Affiche le tour courant
    private int nbTours;

    // Affiche le jour courant
    private int nbJours;

    // nb de gourdes par jour
    //private int gourde;
    private int ordonneeActuelle;
    private int absysseActuelle;
    private int indiceCase;
    private boolean dejaBu;
    private boolean dejaMange;
  
    //Tableau de 10 objets
    private ArrayList<Item> sac = new ArrayList<Item>(10);
    private ArrayList<String> carteJoueur = new ArrayList<String>();
   
    
    public int getOrdonneeActuelle() {
        return ordonneeActuelle;
    }

    public void setOrdonneeActuelle(int ordonneeActuelle) {
        this.ordonneeActuelle = ordonneeActuelle;
    }

    public int getAbsysseActuelle() {
        return absysseActuelle;
    }

    public void setAbsysseActuelle(int absysseActuelle) {
        this.absysseActuelle = absysseActuelle;
    }

    public void setCoordonneeActuelle(int absysseActuelle, int ordonneeActuelle) {
        this.ordonneeActuelle = ordonneeActuelle;
        this.absysseActuelle = absysseActuelle;
    }

    // Constructeur
    public Joueur(Jeu partie, String nom) {
        this.nom = nom;
        this.pdv = 100;
        this.pa = 6;
        absysseActuelle = partie.getGrille().getxVille();
        ordonneeActuelle = partie.getGrille().getyVille();
        this.sac = new ArrayList<Item>();
        this.carteJoueur = new ArrayList<String>();
        this.indiceCase =338;
        this.dejaBu=false;
        this.dejaMange=false;
    }
    public int getIndiceCase(){
        return indiceCase;
    }
    
    public void setIndiceCase(int indice){
        indiceCase=indice;
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
    public boolean setPdv(int pdv) {
        this.pdv = pdv;
        boolean changement=false;
        if(this.pdv<=0){
            changement=true;
        }
        return changement;
    }

    /**
     * @return the pa
     */
    public int getPa() {
        if(this.pa>10){
            this.pa=10;
        }
        return pa;
    }

    /**
     * @param pa the pa to set
     */
    public void setPa(int pa) {
        if(pa<10){
            this.pa = pa;
        }else{
            this.pa=11;
        }
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

    public ArrayList<Item> getSac() {
        return sac;
    }

    public void setSac(ArrayList<Item> sac) {
        this.sac = sac;
    }
    
    public ArrayList<String> getCarteJoueur() {
        return carteJoueur;
    }

    public void setCarteJoueur(ArrayList<String> carteJoueur) {
        this.carteJoueur = carteJoueur;
    }
    
    public boolean getDejaBu(){
        return dejaBu;
    }
    public boolean getDejaMange(){
        return dejaMange;
    }
    
    public void setNouveauJour(){
        dejaBu=false;
        dejaMange=false;
        
    }

    /**
     * @return the gourde
     */
    /*public int getGourde() {
     return gourde;
     }*/
    /**
     * @param gourde the gourde to set
     */
    /* public void setGourde(int gourde) {
     this.gourde = gourde;
     }*/
    // ---------------DEBUT DES METHODES---------------------
    // Permet au joueur de boire de l'eau
    /**
     * @return changement to know a change has made
     */
    public boolean boire() {
        int i=0;
        boolean changement=false;
        
        if(!sac.isEmpty()){
            while(!(this.sac.get(i).getNom().equals("Gourde")) && i < this.sac.size()){
                i++;
            }
            if(this.sac.get(i).getNom().equals("Gourde")){
                this.setPa(this.getPa() + 6);
                this.sac.remove(i);
                System.out.println("Vous avez récupéré 6 points d'actions !");
                changement=true;
                dejaBu=true;
            }else{
                if(i == this.sac.size()){
                    System.out.println("Vous n'avez pas de gourde !");
                }
            }
        }else{
            System.out.println("Vous n'avez pas de gourde !");
        }
        
        return changement;
    }

// correspond à la méthode déplacement
    public void deplacement() {

    }

    //Permet au joueur de manger une ration s'il en dispose
    public boolean manger() {
        
        int i=0;
        boolean changement=false;
        
        if(!sac.isEmpty()){
            while(!(this.sac.get(i).getNom().equals("Ration")) && i < this.sac.size()-1){
                i++;
            }
            if(this.sac.get(i).getNom().equals("Ration")){
                this.setPa(this.getPa() + 6);
                this.sac.remove(i);
                System.out.println("Vous avez récupéré 6 points d'actions !");
                changement=true;
                dejaMange=true;
            }else{
                if(i == this.sac.size()){
                    System.out.println("Vous n'avez pas de ration !");
                }
            }
        }else{
            System.out.println("Vous n'avez pas de ration !");
        }
        
        return changement;
        
        /*for (int i = 0; i < this.sac.size(); i++) {
            if (this.sac.get(i).getNom() == "Ration") {
                this.setPa(this.getPa() + 6);
                if (this.sac.get(i).getQuantite() == 1) {
                    this.sac.remove(i);
                } else {
                    this.sac.get(i).setQuantite(this.sac.get(i).getQuantite() - 1);
                }

                System.out.print("Vous avez récupéré 6 points d'actions !");
                break;
            }
        }
        System.out.print("Vous n'avez pas de gourde !");*/
    }

    // Permet au joueur de construire des défenses

    // Permet au joueur de terminer son tour sans consommer tous ses points d'action
    public void terminerTour() {

    }

    // Permet au joueur de remplir son sac
    public void remplirSac() {

    }

    // Permet au joueur de vider son sac
    public void viderSac() {

    }
}
