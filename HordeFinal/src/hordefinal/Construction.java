/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hordefinal;

import java.util.ArrayList;

/**
 *
 * @author Seb
 */

public class Construction {

    //Nom de la construction
    private String nom;

    // les ressources necessaires pour la construction
    private ArrayList<Integer> ressources_necessaire= new ArrayList<Integer>();
    //private int[] ressources_necessaire;
    // nombre de points d'action utilisés pour cette construction
    private int conso_pa;

    // indique le nombre de zombies auquel la construction peut resister
    private int resistance;

    // Constructeur
    public Construction(String nom, ArrayList<Integer> ressources_necessaire, int conso_pa, int resistance) {
        this.nom = nom;
        this.ressources_necessaire = ressources_necessaire;
        this.conso_pa = conso_pa;
        this.resistance = resistance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Integer> getRessources_necessaire() {
        return ressources_necessaire;
    }
    public int getRessources( int choix){
        return ressources_necessaire.get(choix);
    }

    public void setRessources_necessaire(ArrayList ressources_necessaire) {
        this.ressources_necessaire = ressources_necessaire;
    }

    public int getConso_pa() {
        return conso_pa;
    }

    public boolean setConso_pa(int conso_pa) {
        boolean fini=false;
        this.conso_pa = conso_pa;
        if(this.conso_pa<=0){
            this.conso_pa=0;
            fini=true;
        }
        return fini;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
