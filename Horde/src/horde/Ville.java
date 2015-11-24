/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.conversionCaractere;

/**
 *
 * @author Gabriel
 */
public class Ville extends Case {

    private int[] entrepot = new int[5]; // valeur par défault//
    private int nbRation;
    private int tauxDefence;
    Construction[] batiment = new Construction[6]; // valeur par défault//

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

    public Ville(Jeu partie) {
        super(13, -13);
        int i = ((13 + 1) * (-1 * (-13)));
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

    public Construction[] afficherConstruction() {
        return this.batiment;
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

    public int defenceVille(boolean ouverturePorte) {
        this.tauxDefence = 0;
        if (ouverturePorte == false) {
            this.tauxDefence = 20;
        } else {
            System.out.print("La porte est ouverte !");
        }
        return this.tauxDefence;
    }

    public boolean ouverturePorte() {
        //bool ouverte -> 1 sinon 0
        boolean ouverturePorte = false;

        if (ouverturePorte == true) {
            System.out.print("La porte est ouverte !");
        } else {
            System.out.print("La porte est fermée !");
        }
        return ouverturePorte;
    }

    public void remplirSac() {

    }

    public void boire() {

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

    public void setBatiment(String[] Construction) {
        this.batiment = batiment;
    }

}
