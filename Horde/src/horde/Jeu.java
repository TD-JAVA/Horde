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
    // C'est un commentaire le temps de créer la classe joueur
    /*private Joueur[] tabJoueur= new Joueur[10];
    public Joueur getJoueur(){
        return tabJoueur;
    }
    
    public void setJoueur(Joueur citoyen){
    
    }*/
    
    public Jeu(){
        this.setPartie(false);
        
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
        Menu.demarrer(this,menuJeu);    
    
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
