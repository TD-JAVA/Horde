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
public class Ville {
    int[] entrepot= new int[100]; // valeur par défault//
    int nbRation;
    String [] batiment = new String[50]; // valeur par défault//

    public Ville(int nbRation) {
        this.nbRation = nbRation;
    }
    
    public int[] afficherEntrepot(){
        return this.entrepot;
    
    }
    
    public String[] afficherConstruction(){
        return this.batiment;
    }
    
    public void construire(){
       String construire;
       
        
    }
    
    public boolean ouverturePorte(){
    //bool ouverte -> 1 sinon 0
        boolean ouverte = false;
        
         if(ouverte==true){
            System.out.print("La porte est ouverte !");
         }else{
           System.out.print("La porte est fermée !");
         }
        return ouverte;
    }
    
    public void remplirSac(){
   
    }
    
    public void boire(){
   
    }
    
    public int manger(){
       boolean manger = false;
       if(manger == true){
           nbRation = nbRation-1 ; 
       }
           return nbRation;
    }
    
}
