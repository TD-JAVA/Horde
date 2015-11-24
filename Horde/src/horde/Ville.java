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

    private int[] entrepot= new int[5]; // valeur par défault//
    private int nbRation;
    private int tauxDefence;
    Construction [] batiment = new Construction[6]; // valeur par défault//

    public Ville(int nbRation, int tauxDefence, int longitude, int latitude) {
        super(longitude, latitude);
        this.nbRation = nbRation;
        this.tauxDefence = tauxDefence;
    }

    public int getTauxDefence() {
        return tauxDefence;
    }

    public void setTauxDefence(int tauxDefence) {
        this.tauxDefence = tauxDefence;
    }


    
    

    public Ville(Jeu partie){
        super(13,-13);
        int i=((13+1)*(-1*(-13)));
        //entrepot={50,0,0,0}; //ration, planche,clou,boisson
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
    
    public Construction[] afficherConstruction(Journal Journal){
        return Journal.getTabConstruction();
    }
    
    public void construire(){
       String construire;
       
        
    }

    public int defenceVille(boolean ouverturePorte ){
        this.tauxDefence = 0;
        if (ouverturePorte==false){
            this.tauxDefence = 20;
        }else{
            System.out.print("La porte est ouverte !");
       }
        return this.tauxDefence;
    }
    
    
    public boolean ouverturePorte(){
    //bool ouverte -> 1 sinon 0
        boolean ouverturePorte = false;
        
         if(ouverturePorte==true){
            System.out.print("La porte est ouverte !");
         }else{
           System.out.print("La porte est fermée !");
         }
        return ouverturePorte;
    }
    
    public void remplirSac(){
   
    }
    
    public void remplirGourde(){
        
    }
    
    public void boire(){
   
    }
    
    public int manger(){
       boolean manger = false;
       if(manger == true){
           this.nbRation = this.nbRation-1 ; 
       }
           return this.nbRation;
    }

    public int[] getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(int[] entrepot) {
        this.entrepot = entrepot;
    }

    public int getNbRation() {
        return nbRation;
    }

    public void setNbRation(int nbRation) {
        this.nbRation = nbRation;
    }

    public Construction[] getBatiment() {
        return batiment;
    }

    public void setBatiment(String[] Construction) {
        this.batiment = batiment;
    }
    
}
