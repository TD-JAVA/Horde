/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.Scanner;

/**
 *
 * @author oneiroi
 */
public class Jeu {
    //C'est un commentaire le temps de créer la classe joueur
    private Joueur[] tabJoueur;
    private int nombreJoueur;
    private int indexJoueurActuel;
    private Joueur joueurActuel;
    private Temps tempsPartie;
    private Menu menuPartie;   
    private boolean partieDemarree;
    private Carte grille;
    private Ville maVille;
    private Journal monJournal; 

    public Journal getMonJournal() {return monJournal;}
    public void setMonJournal(Journal monJournal) {this.monJournal = monJournal;}
    public Ville getMaVille() {return maVille;}
    public void setMaVille(Ville maVille) {this.maVille = maVille;}
    public Menu getMenuPartie() {return menuPartie;}
    public void setMenuPartie(Menu menuPartie) {this.menuPartie = menuPartie;}
    public void setTempsPartie(Temps tempsPartie) {this.tempsPartie = tempsPartie;}
    public Temps getTempsPartie() {return tempsPartie;}
    public Joueur getJoueur(int i){return tabJoueur[i];}
    public void setJoueur(Joueur citoyen, int i){tabJoueur[i] = citoyen;}    
    public int getNombreJoueur(){return nombreJoueur;}    
    public void setIndexJoueurActuel(int i){indexJoueurActuel=i;}    
    public int getIndexJoueurActuel(){return indexJoueurActuel;}
    public void setJoueurActuel(int i){joueurActuel=tabJoueur[i];}    
    public Joueur getJoueurActuel(){return joueurActuel;}
    public boolean getPartie(){return partieDemarree;}
    public void setPartie(boolean demarree){this.partieDemarree = demarree;}
    public Carte getGrille() {return grille;}
    public void setGrille(Carte grille) {this.grille = grille;}
    
    public Jeu(){
        this.setPartie(false);
        //String answersUser = new String();
        tempsPartie= new Temps();
        grille=new Carte(this);
        maVille=new Ville(this);
        monJournal=new Journal();
        Scanner sc = new Scanner(System.in);        
        System.out.println("Combien y a t il de joueur pour cette partie ?(entre 1 et 20 joueurs)");
        //nombreJoueur=menuPartie.conversionInt(sc.next());
        nombreJoueur=sc.nextInt();
        
        tabJoueur=new Joueur[nombreJoueur];
        for(int i=0;i<nombreJoueur;i++){
            System.out.println("Quel est le nom du joueur "+i+" ?");
            String nomJoueur=sc.next();
            Joueur unJoueur= new Joueur(this,nomJoueur);
            tabJoueur[i]=unJoueur;
            
        }
        setJoueurActuel(0);
        menuPartie=new Menu();
        //while((answersUser=="y") || (answersUser=="Y") || (answersUser=="yes") || (answersUser=="Yes") || (answersUser=="YES") || (answersUser=="O") || (answersUser=="o") || (answersUser=="Oui") || (answersUser=="oui") || (answersUser=="OUI")){
            
        //}
    }
    
    public void lancerJeu(){
        //while(tempsPartie.getNbJour()!=2){
            this.getMenuPartie().demarrer(this);   
        //}
    }
    
    public void initialisation(){
        boolean finInitialisation = true;
        setPartie(finInitialisation);
        System.out.println("Initialisation terminée, accèdez au journal par le sous menu pour connaitre les règles du jeu");    
    }
    
    public void finDePartie(){
        boolean continuerPartie = false;
        setPartie(continuerPartie);
    }
}
