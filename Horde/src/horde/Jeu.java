/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author oneiroi
 */
public class Jeu {
    private ArrayList<Joueur> tabJoueur;
    private int nombreJoueur;
    private int indexJoueurActuel;
    private Joueur joueurActuel;
    private Temps tempsPartie;
    private Menu menuPartie;   
    private boolean partieDemarree=false;
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
    public Joueur getJoueur(int i){return tabJoueur.get(i);}
    public ArrayList<Joueur> getTabJoueur(){return tabJoueur;}
    public void setJoueur(Joueur citoyen, int i){tabJoueur.add(i, citoyen);}    
    public int getNombreJoueur(){return nombreJoueur;}    
    public void setIndexJoueurActuel(int i){indexJoueurActuel=i;}    
    public int getIndexJoueurActuel(){return indexJoueurActuel;}
    public void setJoueurActuel(int i){if(nombreJoueur==1){finDePartie();}else{joueurActuel=tabJoueur.get(i);}}    
    public Joueur getJoueurActuel(){return joueurActuel;}
    public boolean getPartie(){return partieDemarree;}
    public void setPartie(boolean demarree){
        this.partieDemarree = demarree;
        if(!this.partieDemarree){
        lancerJeu();
        }
    }
    public Carte getGrille() {return grille;}
    public void setGrille(Carte grille) {this.grille = grille;}
    public Jeu(){
    }
    
    // Méthode qui lance le jeu
    public void lancerJeu(){
   
        Outils.affichage(Journal.consulterDescription(56));
        
        tempsPartie= new Temps();
        grille=new Carte(this);
        maVille=new Ville(this);
        monJournal=new Journal();
        menuPartie=new Menu();
        Scanner sc = new Scanner(System.in);        
        Outils.affichage(Journal.consulterDescription(57));
        nombreJoueur=Outils.conversionInt(sc.next());   
        tabJoueur=new ArrayList<Joueur>(nombreJoueur);
        // Pour chaque joueurs
        for(int i=0;i<nombreJoueur;i++){
            //On demande au joueur d'entrer son nom
            Outils.affichage(Journal.consulterDescription(58)+i+" ?");
            String nomJoueur=sc.next();
            Joueur unJoueur= new Joueur(this,nomJoueur);
            //Le joueur est ajouté au tableau de joueurs
            tabJoueur.add(unJoueur);
        }
        setJoueurActuel(0);
        //On démarre la partie
        this.getMenuPartie().demarrer(this);   
    }
    
    // Méthode qui permet d'initialiser la partie
    public void initialisation(){
        boolean finInitialisation = true;
        setPartie(finInitialisation);
        Outils.affichage(Journal.consulterDescription(59));    
    }
    
    //Méthode qui indique par un booléen si il ne reste plus qu'un joueur en vie.
    public boolean dernierJoueur(){
        String str="";
        if(!tempsPartie.getNuit()){
            Outils.affichage(Journal.consulterDescription(60));
            Journal.afficherMort(this, !tempsPartie.getNuit(), true);
        }else{
            Journal.afficherMort(this, tempsPartie.getNuit(), true);
        }
        monJournal.ajouterListeDeMorts(str);
        nombreJoueur-=1;
        getTabJoueur().remove(getIndexJoueurActuel());
        //S'il reste moins de 2 joueurs
        if(getNombreJoueur()<=1){return true;}else{return false;}
    }
    
    public boolean dernierJoueur(Joueur joueur,int i,boolean k){
        String str="";
        if(!tempsPartie.getNuit()){
            if(k){
                Outils.affichage(Journal.consulterDescription(60));
                Journal.afficherMort(this, !tempsPartie.getNuit(), k);
            }else{
                Journal.afficherMort(this, !tempsPartie.getNuit(), !k);
            }
        }else{
            Journal.afficherMort(this, tempsPartie.getNuit(), false);
        }
        
        monJournal.ajouterListeDeMorts(str);
        nombreJoueur-=1;
        getTabJoueur().remove(i);
        if(getNombreJoueur()<=1){return true;}else{return false;}
    }
    
    // Méthode qui met fin à la partie
    public void finDePartie(){
        boolean continuerPartie = false;
        Scanner sc=new Scanner(System.in);
        Outils.affichage("\n"+this.tabJoueur.get(0).getNom()+Journal.consulterDescription(61));
        sc.next();
        Outils.affichage(Journal.consulterDescription(62));
        //Si le joueur souhaite rejouer
        if(Outils.conversionBoolean(sc.next())){
            setPartie(continuerPartie);
        }else{
            menuPartie.menuNiveauZero('Q');
        }
    }
}
