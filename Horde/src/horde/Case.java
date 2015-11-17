/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Gabriel
 */
public class Case {
    public int longitude;
    public int latitude;
    public int nbZombies;
    int[] items= new int[20]; // valeur par défault//
    public Boolean fouiller;

    public Case(int longitude, int latitude, int nbZombies, Boolean fouiller) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nbZombies = nbZombies;
        this.fouiller = fouiller;
    }
    
    public void fouiller(){

  }

    public void quitter(){

  }

    public void attaquer(){

  }
    
}
