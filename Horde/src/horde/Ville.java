/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.conversionCaractere;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Ville extends Case {
    Scanner sc=new Scanner(System.in);
    private int[] entrepot = new int[3]; // valeur par défaut//
    private String[] tabItems= {"Planche","Clou","Boisson énergissante"};
    private int nbRation;
    private int tauxDefense;
    Construction[] batiment = new Construction[6]; // valeur par défaut//
    private boolean ouverturePorte = true;

    public Ville(int nbRation, int tauxDefense, int longitude, int lattitude) {
        super(longitude, lattitude);
        this.nbRation = nbRation;
        this.tauxDefense = tauxDefense;
    }

    public int getTauxDefense() {
        return tauxDefense;
    }

    public void setTauxDefense(int tauxDefense) {
        this.tauxDefense = tauxDefense;
    }

    public Ville(Jeu partie) {
        super(13, -13);
        int i = ((26*26)/2);
        //entrepot={50,0,0,0}; //ration, planche,clou,boisson
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(13, -13);

    }

    public Ville(Jeu partie, int absysse, int ordonnee) {
        super(absysse, ordonnee);
        int i = ((absysse + 1) * (-1 * ordonnee));
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(absysse, ordonnee);
    }

    public int[] afficherEntrepot() {
        return this.entrepot;

    }
    
    public Construction[] afficherConstruction(Journal Journal){
        return Journal.getTabConstruction();
    }

    public void construire(Jeu partie) {
        Construction c;
        int[] ressource;
        System.out.println("Chaque construction coute un point d'action. Quelle construction souhaitez vous batir ?");
        char choix = conversionCaractere('R');
        switch (choix) {
            case '1':
                if (this.entrepot[1] >= 20 && this.entrepot[2] >= 5 && partie.getJoueurActuel().getPa() >= 10) {
                    ressource = new int[]{20, 5};
                    c = new Construction("Mur d’enceinte", ressource, 10, 20);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '2':
                if (this.entrepot[1] >= 20 && this.entrepot[2] >= 30 && partie.getJoueurActuel().getPa() >= 20) {
                    ressource = new int[]{20, 30};
                    c = new Construction("Fils barbelés", ressource, 20, 30);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '3':
                if (this.entrepot[1] >= 50 && this.entrepot[2] >= 25 && partie.getJoueurActuel().getPa() >= 30) {
                    ressource = new int[]{50, 25};
                    c = new Construction("Fosses à zombies", ressource, 30, 50);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '4':
                if (this.entrepot[1] >= 10 && this.entrepot[2] >= 50 && partie.getJoueurActuel().getPa() >= 30) {
                    ressource = new int[]{10, 50};
                    c = new Construction("Mines autour de la ville", ressource, 30, 50);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '5':
                if (this.entrepot[1] >= 50 && this.entrepot[2] >= 50 && partie.getJoueurActuel().getPa() >= 40) {
                    ressource = new int[]{50, 50};
                    c = new Construction("Portes blindées", ressource, 40, 100);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '6':
                if (this.entrepot[1] >= 75 && this.entrepot[2] >= 75 && partie.getJoueurActuel().getPa() >= 50) {
                    ressource = new int[]{75, 75};
                    c = new Construction("Portes blindées", ressource, 50, 200);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;

            case '7':
                if (this.entrepot[1] >= 100 && this.entrepot[2] >= 200 && partie.getJoueurActuel().getPa() >= 60) {
                    ressource = new int[]{100, 200};
                    c = new Construction("Portes blindées", ressource, 60, 500);
                } else {
                    System.out.println("Ressources ou points d'action inssufisants");
                }
                break;
        }
    }
    
    public String consulterEntrepot(){
         String tabEntrepot="";
        for(int i=0;i<entrepot.length;i++){
            tabEntrepot+=""+tabItems[i]+" "+entrepot[i]+'\n';
        }
   
        return tabEntrepot;
    }

    public int defenseVille(boolean porteOuverte) {
        this.tauxDefense = 0;
        if (porteOuverte == false) {
            this.tauxDefense = 20;
        } else {
            System.out.print("La porte est ouverte !");
        }
        return this.tauxDefense;
    }

    public boolean ouverturePorte() {
        //bool ouverte -> 1 sinon 0
        boolean changement=false;
        if (ouverturePorte == true) {
            System.out.print("La porte est ouverte !");
            System.out.println("Souhaitez-vous la fermer ?(O/N)($1PA)");
            if(Menu.conversionBoolean(sc.next())){
                ouverturePorte=false;
                changement=true;
            }
        } else {
            System.out.print("La porte est fermée !");
            System.out.println("Souhaitez-vous l'ouvrir ?(O/N)($1PA)");
            if(Menu.conversionBoolean(sc.next())){
                ouverturePorte=true;
                changement=true;
            }
        }
        return changement;
    }
    public boolean getOuverturePorte(){
        return ouverturePorte;
    }
    public void remplirSac() {

    }
    
    public void boire() {

    }
    
    public Item remplirGourde(){
        Item gourde = new Item("Gourde",Journal.consulterDescription(0));
        return gourde;
    }
    

    public int manger() {
        boolean manger = false;
        if (manger == true) {
            this.nbRation = this.nbRation - 1;
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

    public void setBatiment(Construction[] construction) {
        this.batiment = construction;
    }

}
