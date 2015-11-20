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
    int listeJoueursMort;

    public Zombies(int nbZombies, int listeJoueursMort) {
        this.nbZombies = nbZombies;
        this.listeJoueursMort = listeJoueursMort;
    }
    
    public int nbZombies(Temps Temps){
        Random ra = new Random();
        if(Temps.getNbJour()==1){
        this.nbZombies = ra.nextInt(20 - 10);
        }else if (Temps.getNbJour()==2){
        this.nbZombies = ra.nextInt(30 - 20);
        }else if (Temps.getNbJour()==3){
        this.nbZombies = ra.nextInt(40 - 30);
        }
        return this.nbZombies;   
    }
    
    public int listeJoueursMort(){
        return listeJoueursMort;
    }

    public int getNbZombies() {
        return nbZombies;
    }

    public void setNbZombies(int nbZombies) {
        this.nbZombies = nbZombies;
    }

    public int getListeJoueursMort() {
        return listeJoueursMort;
    }

    public void setListeJoueursMort(int listeJoueursMort) {
        this.listeJoueursMort = listeJoueursMort;
    }
  
}
