/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

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
    public Joueur[] getJoueur(){
        return tabJoueur;
    }
    
    public void setJoueur(Joueur citoyen, int i){
        tabJoueur[i] = citoyen; 
    }
    
    public int getNombreJoueur(){
        return nombreJoueur;
    }
    
    public void setIndexJoueurActuel(int i){
        indexJoueurActuel=i;
    }
    
    public int getIndexJoueurActuel(){
        return indexJoueurActuel;
    }
    
    
    public void setJoueurActuel(int i){
        joueurActuel=tabJoueur[i];
    }
    
    public Joueur getJoueurActuel(){
        return joueurActuel;
    }
    
    public Jeu(){
        this.setPartie(false);
        //String answersUser = new String();
        
        System.out.println("Combien y a t il de joueur pour cette partie ?(entre 1 et 10 joueurs)");
        nombreJoueur=2;
        tabJoueur=new Joueur[nombreJoueur];
        for(int i=0;i<nombreJoueur;i++){
            System.out.println("Quel est le nom du joueur "+i+" ?");
            String nomJoueur="Timmy";
            Joueur unJoueur= new Joueur(nomJoueur, 100, 6);
            tabJoueur[i]=unJoueur;
        }
        //while((answersUser=="y") || (answersUser=="Y") || (answersUser=="yes") || (answersUser=="Yes") || (answersUser=="YES") || (answersUser=="O") || (answersUser=="o") || (answersUser=="Oui") || (answersUser=="oui") || (answersUser=="OUI")){
            
        //}
    }
    private boolean partieDemarree;
    
    public boolean getPartie(){
        return partieDemarree;
    }
    
    public void setPartie(boolean demarree){
        this.partieDemarree = demarree;
    }
    
    public void lancerJeu(){
        
        Menu menuJeu = new Menu();
        menuJeu.demarrer(this);    
    
    }
    
    public void initialisation(){
        
        boolean finInitialisation = true;
        setPartie(finInitialisation);
        System.out.println("Initialisation terminée, accèdez au journal par le sous menu pour connaitre les règles du jeu");
        
    }
    
    public void finDePartie(){
    
    boolean finPartie = false;
        setPartie(finPartie);
    }
}
