/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.Random;

/**
 *
 * @author Gabriel, Sébastien & Valère
 */

/*
La classe Temps renseigne le joueur sur le temps passé dans le jeu, si on est le jour ou la nuit, et le numéro du tour courant
*/

public class Temps {
    private int numTour; // Indique le nombre de tours passés dans le jeu
    private int nbJours; // Indique le nombre de jours passés dans le jeu
    private boolean nuit; // Indique si c'est la nuit
    // getters&setter
    public void setNumTour(int i){}
    public int getNumTour(){return numTour;}
    public int getNbJour(){return nbJours;}
    public void setNuit(boolean i){}
    public boolean getNuit(){return nuit;}
    Random ra=new Random();
    public Temps(){
     numTour=0;
     nbJours=0;
     nuit=false;
     
    }
    //Méthode qui incrémente le numéro du tour de 1
    public void incrementerTour(Jeu partie){
        //Si c'est le dernier joueur
        if(partie.getIndexJoueurActuel()<partie.getNombreJoueur()-1){
            //On incrémente
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
    
    // Méthode qui incrémente le nombre de jours passés dans le jeu de 1
    private void incrementerNbJour(){nbJours+=1;}
    
    //Méthode qui permet au jeu de passer à la phase de nuit
    public void deroulementNuit(Jeu partie){
        nuit=true;
        // Pour tous les joueurs
        for(int i=0;i<partie.getNombreJoueur();i++){
            //Si les joueurs ne sont pas en ville
            if(partie.getJoueur(i).getIndiceCase()!=338){
                //ils meurent
               partie.getJoueur(i).setPdv(0);
            }
            // Si les zombies surpassent la défense de la ville
            if(partie.getMaVille().defenseVille()<Zombies.attaqueNuitZombies(this)){
                // Lz moitié des joueurs sont tués
                int nbATue= (int)(partie.getNombreJoueur()/2);
                //Pour la moitié des joueurs
                for(int j=0; j<nbATue;j++){
                    //On selectionne aléatoirement les joueurs qui mourront
                    int k=ra.nextInt(partie.getNombreJoueur()-1);
                    Joueur ceJoueur=partie.getJoueur(k);
                    ceJoueur.setPdv(0);
                    //S'il ne reste plus qu'un joueur, la partie est erminée
                    if(partie.dernierJoueur(ceJoueur,k,true)){partie.finDePartie();}
                }
            }
        }
    }
    // Méthode qui initialise le tour.
    public void debuterTour(Jeu partie){
        partie.getMenuPartie().menuNiveauUn(Outils.conversionCaractere(Outils.afficher(1,partie)));
    }
}
