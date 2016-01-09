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
    //C'est un commentaire le temps de créer la classe joueur
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
    
    public void lancerJeu(){
        //this.setPartie(false);
        //String answersUser = new String();
        
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("******************* H O R D E ********************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("Bienvenue dans Horde, une reproduction simplifié du Jeu de Twinoid");
        System.out.println("Ce programme a été réalisé dans un cadre scolaire");
        System.out.println("Il n'est pas commercialisable. Les créateurs de ce programme et les intervenants de l'université de Lorraine ne pourront être tenu responsable des effets de ce logiciel et de ce logiciel");
        System.out.println("Programme réalisé par Sébastien Brogniart, Gabriel Giroud et Valère Richier alors tous trois étudiants en année de Licence MIASHS.\n");
        tempsPartie= new Temps();
        grille=new Carte(this);
        maVille=new Ville(this);
        monJournal=new Journal();
        menuPartie=new Menu();
        Scanner sc = new Scanner(System.in);        
        System.out.println("Combien y a t il de joueur pour cette partie ?(entre 1 et 20 joueurs)");
        nombreJoueur=menuPartie.conversionInt(sc.next());
        //nombreJoueur=sc.nextInt();
        
        tabJoueur=new ArrayList<Joueur>(nombreJoueur);
        for(int i=0;i<nombreJoueur;i++){
            System.out.println("Quel est le nom du joueur "+i+" ?");
            String nomJoueur=sc.next();
            Joueur unJoueur= new Joueur(this,nomJoueur);
            tabJoueur.add(unJoueur);
            
        }
        setJoueurActuel(0);
        
        //while((answersUser=="y") || (answersUser=="Y") || (answersUser=="yes") || (answersUser=="Yes") || (answersUser=="YES") || (answersUser=="O") || (answersUser=="o") || (answersUser=="Oui") || (answersUser=="oui") || (answersUser=="OUI")){
            
        //}
        //while(tempsPartie.getNbJour()!=2){
            this.getMenuPartie().demarrer(this);   
        //}
    }
    
    public void initialisation(){
        boolean finInitialisation = true;
        setPartie(finInitialisation);
        System.out.println("Initialisation terminée, accèdez au journal par le sous menu pour connaitre les règles du jeu");    
    }
    
    public boolean dernierJoueur(){
        String str="";
        if(!tempsPartie.getNuit()){
            System.out.println("\nVous êtes mort ! Fin de partie pour vous.");
            str= "\n"+joueurActuel.getNom()+" est décédé(e) le "+tempsPartie.getNumTour()+" tour(s) du jour"+tempsPartie.getNbJour()+" par une attaque de zombies";
        }else{
            str= "\n"+joueurActuel.getNom()+" est décédé(e) dans la nuit du jour"+tempsPartie.getNbJour()+" par une attaque de zombies";
        }
            monJournal.ajouterListeDeMorts(str);
            nombreJoueur-=1;
            getTabJoueur().remove(getIndexJoueurActuel());
            if(getNombreJoueur()<=1){return true;}else{return false;}
    }
    
    public boolean dernierJoueur(Joueur joueur,int i,boolean k){
        String str="";
        if(!tempsPartie.getNuit()){
            if(k){
            System.out.println("\nVous êtes mort ! Fin de partie pour vous.");
            str= "\n"+joueur.getNom()+" est décédé(e) le "+tempsPartie.getNumTour()+" tour(s) du jour"+tempsPartie.getNbJour()+" par une attaque de zombies.";
            }else{
            str= "\n"+joueur.getNom()+" est décédé(e) le "+tempsPartie.getNumTour()+" tour(s) du jour"+tempsPartie.getNbJour()+" à cause de votre dépendance.";
            }
        }else{
            str= "\n"+joueur.getNom()+" est décédé(e) dans la nuit du jour"+tempsPartie.getNbJour()+" par une attaque de zombies.";
        }
            monJournal.ajouterListeDeMorts(str);
            nombreJoueur-=1;
            getTabJoueur().remove(i);
            if(getNombreJoueur()<=1){return true;}else{return false;}
    }

    public void finDePartie(){
        boolean continuerPartie = false;
        Scanner sc=new Scanner(System.in);
        System.out.println("\n"+this.tabJoueur.get(0).getNom()+" a gagné la partie.");
        sc.next();
        System.out.println("\nLa partie est terminée. Merci d'avoir joué!");
        System.out.println("\nSouhaitez vous rejouer ?(O/N)");
        if(Menu.conversionBoolean(sc.next())){
            setPartie(continuerPartie);
        }else{
            menuPartie.menuNiveauZero('Q');
        }
        
    }
}
