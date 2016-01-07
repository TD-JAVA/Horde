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
        //System.out.println(partie.getIndexJoueurActuel());
        if(partie.getIndexJoueurActuel()<partie.getNombreJoueur()-1){
            partie.setIndexJoueurActuel(partie.getIndexJoueurActuel()+1);
            
        }else{
            partie.setIndexJoueurActuel(0);
            //System.out.println("coucou"+partie.getJoueur(partie.getIndexJoueurActuel()).getNom()+this.numTour);
            numTour+=1;
            for(int i=0;i<partie.getNombreJoueur();i++){
                    partie.getJoueur(i).setPa(partie.getJoueur(i).getPa()+4);
                }
            if(numTour==4){
                nuit=true;
                deroulementNuit();
                nuit=false;
                numTour=0;
                for(int i=0;i<partie.getNombreJoueur();i++){
                    partie.getJoueur(i).setNouveauJour();
                }
                incrementerNbJour();
            }
        }
        partie.setJoueurActuel(partie.getIndexJoueurActuel());    
        this.debuterTour(partie);
    }
    private void incrementerNbJour(){nbJours+=1;}
    public void deroulementNuit(){}
    public void debuterTour(Jeu partie){
        partie.getMenuPartie().menuNiveauUn(Menu.conversionCaractere(partie.getMenuPartie().afficher(1)));
    }
}
