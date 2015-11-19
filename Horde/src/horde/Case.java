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
    protected int longitude;
    protected int latitude;
    protected int nbZombiesRestants;
    private int[] items= new int[20]; // valeur par défault//
    private boolean fouillee;
    protected boolean laVille = false;

    public Case(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nbZombiesRestants = 0;
        this.fouillee = false;
    }
    
    public void fouiller(){

  }

    public void quitter(){

  }

    public void attaquer(){

  }
    
}
