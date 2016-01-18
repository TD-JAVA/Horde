/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.Random;

/**
 *
 * @author Gabriel
 */
public class Zombies {
   
    // Méthode qui calcule aléatoirement et en fonction du temps passé, le nombre de zombies qui vont attaquer la ville pendant la prochaine nuit
    public  static int attaqueNuitZombies(Temps temps){
        Random ra = new Random();
        int n=temps.getNbJour();
        //On calcule le nombre de zombies qui attaqueront
        return n*10 + ra.nextInt(10);
    }
    
    // Méthode qui retourne le nombre de zombies qui seront sur une case
    public static int nbZombiesCase(){
        Random ra = new Random();
        //On génère le nombre de zombies
         int nbZombiesCase = ra.nextInt(10 - 0); 
         //S'il y a plus de 3 zombies sur la case
        if(nbZombiesCase>3){
            return nbZombiesCase-3;
        }else{
            return 0;
        }
    }
}
