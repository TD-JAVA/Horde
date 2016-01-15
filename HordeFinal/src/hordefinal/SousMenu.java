/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oneiroi
 */
public class SousMenu implements ActionListener{

    public String str="";
    public FenetrePrincipale i;
    public SousMenu(FenetrePrincipale inter){
        i=inter;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         str=i.getjTextField2().getText();//To change body of generated methods, choose Tools | Templates.
    }
    
    /*public static void choisir(char choix){
        switch (choix) {
            case 'J':   SousMenu.lireJournal();
                        break;
            case 'D':   SousMenu.seDeplacer();
                        break;
            case 'V':   SousMenu.interagirVille();
                        break;
            case 'F':   SousMenu.finirTour();
                        break;

        }
    }
    
    public static char conversionCaractere(char lettre){
    
        return lettre;
    }
    
    public static void lireJournal(){
        System.out.println("Lire le journal");
    }
    
    public static void seDeplacer(){
        
        
    }
    
    public static void interagirVille() {
        
        
    }
    
    public static void finirTour() {
        
    
    }
    
    public static char afficher(){
        char choix ='J';
        return choix;
    } */  
}
