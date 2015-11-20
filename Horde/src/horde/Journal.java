/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Seb
 */
public class Journal {

    public Journal(){}
// Permet de voir la liste et les détails des constructions
    public String consulterConstruction(Construction Construction) {
        return Construction.getNom();
        

    }

// Permer de voir la liste des objets et leur détails dans l'entrepot
    public int[] consulterEntrepot(Ville Ville) {
         return Ville.getEntrepot();
    }

// Permet d'obtenir des infos sur le jeu (jour, tour)
    public int ConsulterTemps(Temps Temps) {
        return Temps.getNbJour()& Temps.getNumTour();
   

    }

//Permet d'afficher un résumé du jeu
    public String toString(Temps Temps , Ville Ville , Construction Construction) {
        String string ="Résumé du Jeu. Le nombre de tours est de "+Temps.getNumTour()+" tour(s), le nombre de jours est de "+Temps.getNbJour()+" jour(s), les items dans l'entrepot sont :"+Ville.getEntrepot()+" ,la liste des batiments sont "+Construction.getNom()+"";

        return string;
    }
    
    public void afficherPosition(Jeu partie,Joueur ceJoueur){
        if(ceJoueur.getAbsysseActuelle()== partie.getGrille().getxVille() & ceJoueur.getOrdonneeActuelle()==partie.getGrille().getyVille()){
            System.out.println(ceJoueur.getNom()+" est dans la ville");
        }
    }
}
