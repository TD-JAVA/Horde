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
public class Menu {
    
    public Menu(){
    
    }
    
    public static void demarrer(Jeu partie) {
        if(partie.getPartie()){
            System.out.println("Jeu déjà démarré, voulez-vous redémarrer la partie ?");
            boolean answer=false; 
            if(answer){
            partie.initialisation();
            }
        }else{
            partie.initialisation();
            }
    }
    
    public static void quitter(){
    
    }
    
    public static void sousMenu(){
    
    }
}
