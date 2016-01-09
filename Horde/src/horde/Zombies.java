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
   
    public  static int attaqueNuitZombies(Temps temps){
        Random ra = new Random();
        int n=temps.getNbJour();
        return n*10 + ra.nextInt(10);
    }
    
    public static int nbZombiesCase(){
        Random ra = new Random();
         int nbZombiesCase = ra.nextInt(10 - 0); 
        if(nbZombiesCase>3){
            return nbZombiesCase-3;
        }else{
            return 0;
        }
    }
}
