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
public class Ville extends Case {
    private int[] entrepot= new int[100]; // valeur par défault//
    private int nbRation;
    
    String [] batiment = new String[50]; // valeur par défault//
    public Ville(Jeu partie){
        super(13,-13);
        int i=((13+1)*(-1*(-13)));
        partie.getGrille().setTabCase(i,this);        
        super.laVille = true;
        partie.getGrille().setxyVille(13,-13);
        
        
    }
    
    public Ville(Jeu partie, int absysse, int ordonnee) {
        super(absysse,ordonnee);
        int i = ((absysse+1)*(-1*ordonnee));
        partie.getGrille().setTabCase(i,this); 
        super.laVille = true;
        partie.getGrille().setxyVille(absysse,ordonnee);
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
