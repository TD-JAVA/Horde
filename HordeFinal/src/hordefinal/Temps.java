/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;

import java.util.Random;

/**
 *
 * @author Sébastien,Gabriel,Valère
 */
public class Temps {
    private int numTour;//indique le nombre de tour
    private int nbJours;//indique le nombre de jour
    private boolean nuit;//indique s'il fait nuit ou non
    Random ra=new Random();//Objet pour récupérer des nombres aléatoires.
    
    // getters&setter
    public void setNumTour(int i){}
    public int getNumTour(){return numTour;}
    public int getNbJour(){return nbJours;}
    public void setNuit(boolean i){}
    public boolean getNuit(){return nuit;}
    
   
    //Constructeur du temps de la partie en cours
    public Temps(){
        numTour=0;
        nbJours=0;
        nuit=false; 
    }
    /********************Gestion du temps***************************/
    //changement du joueur actuel si le tour des joueurs est fait, il y a increment de tour.
    //si les tours arrives à douze, on effectue le déroulement de la nuit et on incrémente les jours.
    //enfin on débute un nouveau tour.
    public void incrementerTour(Jeu partie){
        if(partie.getIndexJoueurActuel()<partie.getNombreJoueur()-1){
            partie.setIndexJoueurActuel(partie.getIndexJoueurActuel()+1);
            
        }else{
            partie.setIndexJoueurActuel(0);
            numTour+=1;
            for(int i=0;i<partie.getNombreJoueur();i++){
                    partie.getJoueur(i).setPa(partie.getJoueur(i).getPa()+4);
                }
            if(numTour==12){
                nuit=true;
                deroulementNuit(partie);
                nuit=false;
                numTour=0;
                for(int i=0;i<partie.getNombreJoueur();i++){
                    partie.getJoueur(i).setNouveauJour(partie,i);
                }
                incrementerNbJour();
            }
        }
        partie.setJoueurActuel(partie.getIndexJoueurActuel());    
        this.debuterTour(partie);
    }
    //Permet incrémenter le nombre de jour.
    private void incrementerNbJour(){nbJours+=1;}
    //Permet d'effectue le déroulement de la nuit avec les attaques de zombies
    public void deroulementNuit(Jeu partie){
        nuit=true;
        for(int i=0;i<partie.getNombreJoueur();i++){
            if(partie.getJoueur(i).getIndiceCase()!=338){
               partie.getJoueur(i).setPdv(0);
            }
                if(partie.getMaVille().defenseVille()<Zombies.attaqueNuitZombies(this)){
                int nbATue= (int)(partie.getNombreJoueur()/2);
                for(int j=0; j<nbATue;j++){
                    int k=ra.nextInt(partie.getNombreJoueur()-1);
                    Joueur ceJoueur=partie.getJoueur(k);
                    ceJoueur.setPdv(0);
                    if(partie.dernierJoueur(ceJoueur,k,true)){partie.finDePartie();}
                }
            }
        }
    }
    //Permet de debuter un nouveau tour.
    public void debuterTour(Jeu partie){
        partie.getMonInterface().setCpt(1);
        Outils.afficher(1,partie);
    }
}
