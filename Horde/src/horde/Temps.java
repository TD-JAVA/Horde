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
public class Temps {
    private int numTour;
    private int nbJours;
    private boolean nuit;
    // getters&setter
    public void setNumTour(int i){}
    public int getNumTour(){return numTour;}
    public int getNbJour(){return nbJours;}
    public void setNuit(boolean i){}
    public boolean getNuit(){return nuit;}
    
    public Temps(){
     numTour=0;
     nbJours=0;
     nuit=false;
     
    }
    
    public void incrementerTour(Jeu partie){
        numTour+=1;
        if(numTour==12){
            nuit=true;
            deroulementNuit();
            nuit=false;
            numTour=0;
            incrementerNbJour();
        }
        if(partie.getIndexJoueurActuel()<partie.getNombreJoueur()){
            partie.setIndexJoueurActuel(partie.getIndexJoueurActuel()+1);
            
        }else{
            partie.setIndexJoueurActuel(0);
        }
        partie.setJoueurActuel(partie.getIndexJoueurActuel());    
        this.debuterTour(partie);
    }
    private void incrementerNbJour(){nbJours+=1;}
    public void deroulementNuit(){}
    public void debuterTour(Jeu partie){
        partie.getMenuPartie().afficher(1);
    }
}
