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
public class SousMenu {
    
    public static void choisir(char choix){
        switch (choix) {
            case 'J':   SousMenu.lireJournal();
                        break;
            case 'D':   SousMenu.seDeplacer();
                        break;
            case 'V':   SousMenu.interagirVille();
                        break;

        }
    }
    
    public static void lireJournal(){
    
    }
    
    public static void seDeplacer(){
        
        
    }
    
    public static void interagirVille() {
        
        
    }
    
    
    public static char afficher(){
        char choix ='j';
        return choix;
    }   
}
