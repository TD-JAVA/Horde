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
    int nbZombies;
    int nbZombiesCase;
    int listeJoueursMort;

    public Zombies(int nbZombies, int listeJoueursMort , int nbZombiesCase) {
        this.nbZombies = nbZombies;
        this.listeJoueursMort = listeJoueursMort;
        this.nbZombiesCase = nbZombiesCase;
    }
    
    public Zombies(){
    
    }
    
    
    public int attaqueNuitZombies(Temps temps){
        Random ra = new Random();
        int n=temps.getNbJour();
        this.nbZombies = n*10 + ra.nextInt(10);
        
        return this.nbZombies;   
    }
    
    public int nbZombiesCase(){
        Random ra = new Random();
        this.nbZombiesCase = ra.nextInt(10 - 0); 
        if(this.nbZombiesCase>3){
            return this.nbZombiesCase-3;
        }else{
            return 0;
        }
    }
    
    public int listeJoueursMort(){
        return this.listeJoueursMort;
    }

    public int getNbZombies() {
        return this.nbZombies;
    }

    public void setNbZombies(int nbZombies) {
        this.nbZombies = nbZombies;
    }

    public int getListeJoueursMort() {
        return this.listeJoueursMort;
    }

    public void setListeJoueursMort(int listeJoueursMort) {
        this.listeJoueursMort = listeJoueursMort;
    }

    public int getNbZombiesCase() {
        return nbZombiesCase;
    }

    public void setNbZombiesCase(int nbZombiesCase) {
        this.nbZombiesCase = nbZombiesCase;
    }

}
