/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;

import java.util.ArrayList;
import java.util.Scanner;
/*
La classe Ville instancie un objet contenant l'ensemble des intéractions et des 
actions que le joueur peut faire dans la ville. Cette classe hérite de la classe Case.
Puisque la ville est une case particulière du plateau de jeu. 
*/
/**
 *
 * @author Gabriel, Sébastien, Valère.
 */

public class Ville extends Case {
    /***************Attribut de la classe******************/    
    private Item[] entrepot= new Item[4]; // valeur par défaut// tableau d'item correspondant à l'entrepot
    private int tauxDefense;//indique le taux de défense de la ville. 
    ArrayList<Construction> batiment = new ArrayList<>(7); // valeur par défaut// tableau de construction correspondant au batiment construit dans la ville
    ArrayList<Construction> batimentEnCours = new ArrayList<>(7); // valeur par défaut// tableau de construction correspondant au batiment en cours de construction dans la ville
    private boolean ouverturePorte = true;//indique si la porte de la ville est fermée
    
    /****************************************************/
    
    /***************Constructeur&Surcharge******************/
     /* 
      *  @param partie correspond à la partie actuelle.
      */       
    // Constructeur de la classe. le mot-clé super() indique que la construction de l'objet passe par la construction d'une case.
    // On remplie l'entrepôt avec les rations, les planches, les boissons énergissantes et les plaques de métal.
    public Ville(Jeu partie) {
        super(13, -13);
        int i = ((26*26)/2);
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(13, -13);
        entrepot[0]=new Item(Journal.consulterDescription(51),50,Journal.consulterDescription(1));
        entrepot[1]=new Item(Journal.consulterDescription(54),0,Journal.consulterDescription(2));
        entrepot[2]=new Item(Journal.consulterDescription(55),0,Journal.consulterDescription(3));
        entrepot[3]=new Item(Journal.consulterDescription(53),0,Journal.consulterDescription(4));
    }
    // Sucharge du constructeur pour plus de challenge en mettant la ville à une position voulu. de maniere aléatoire par exemple
    public Ville(Jeu partie, int absysse, int ordonnee) {
        super(absysse, ordonnee);
        int i = ((absysse + 1) * (-1 * ordonnee));
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(absysse, ordonnee);
        entrepot[0]=new Item(Journal.consulterDescription(51),50,Journal.consulterDescription(1));
        entrepot[1]=new Item(Journal.consulterDescription(54),0,Journal.consulterDescription(2));
        entrepot[2]=new Item(Journal.consulterDescription(55),0,Journal.consulterDescription(3));
        entrepot[3]=new Item(Journal.consulterDescription(53),0,Journal.consulterDescription(4));
    }
/***************Fin Constructeur&Surcharge******************/

/***************Getter&Setter******************/    
    public ArrayList<Construction> afficherConstruction(Journal Journal){return Journal.getTabConstruction();}
    public Item[] getEntrepot() {return entrepot;}
    public void setEntrepot(Item[] entrepot) {this.entrepot = entrepot;}
    public ArrayList<Construction> getBatiment() {return batiment;}
    public ArrayList<Construction> getBatimentEnCours() {return batimentEnCours;}
    public void setBatiment(ArrayList<Construction> construction) {this.batiment = construction;}
    public void setNouveauBatiment(Construction construction){this.batiment.add(construction);}
    public boolean getOuverturePorte(){return ouverturePorte;}
    public int getTauxDefense() {return tauxDefense;}
    public void setTauxDefense(int tauxDefense) {this.tauxDefense = tauxDefense;}
/*********************************/
    
    
/***************Début des méthodes******************/    

/***************méthode construire******************/  
  // Permet la construction d'un batiment choisi non construit jusqu'à présent.
    public void construire(Jeu partie,int choix) {
        Outils.affichage(Journal.consulterDescription(71),partie.getMonInterface());
        boolean batimentDejaFait=false;
        for(int i=0;i<batiment.size();i++){
            if(partie.getMonJournal().getConstruction(choix).getNom().equals(batimentEnCours.get(i).getNom())||partie.getMonJournal().getConstruction(choix).getNom().equals(batiment.get(i).getNom())){
                batimentDejaFait=true;
            }
        }
        if(!batimentDejaFait){
            if (this.entrepot[1].getQuantite() >=partie.getMonJournal().getConstruction(choix).getRessources_necessaire().get(0)  && this.entrepot[2].getQuantite() >= partie.getMonJournal().getConstruction(choix).getRessources_necessaire().get(1) && partie.getJoueurActuel().getPa() >= 1) {
                partie.getJoueurActuel().setPa(partie.getJoueurActuel().getPa()-1);
                batimentEnCours.add(partie.getMonJournal().getConstruction(choix));
            } else {
                Outils.affichage(Journal.consulterDescription(72),partie.getMonInterface());
                partie.getMonInterface().setCpt(partie.getMonInterface().getCpt()-9);
                partie.getMenuPartie().menuNiveauUn('I');
                
            }
        }else{
            Outils.affichage(Journal.consulterDescription(72),partie.getMonInterface());
            partie.getMonInterface().setCpt(partie.getMonInterface().getCpt()-9);
            partie.getMenuPartie().menuNiveauUn('I');
        }    
    }
    /**************************************************************************/
    
    /***************Méthode servant la participation au chantier***************/
    
    
    
    //Evenementiel oblige: le contenu de ces méthodes n'en forné qu'une seule
    // mais on attend la saisie d'un utilisateur donc nous sommes obligés de les 
    // séparer. La première sert à afficher le nombre pa necessaire pour finir la construction choisie.
    public void accederAuChantier(Joueur ceJoueur,Jeu partieActuelle,int num){
        Outils.affichage(Journal.consulterDescription(74)+batimentEnCours.get(num).getConso_pa()+Journal.consulterDescription(75),partieActuelle.getMonInterface());
        Outils.affichage(Journal.consulterDescription(76),partieActuelle.getMonInterface());
    }
    //La seconde sert à investir le nombre de pa voulu dans une construction choisie.
    public void participerAuChantier(Jeu partieActuelle, int num,int choix){
       int pointUse;
        Joueur ceJoueur=partieActuelle.getJoueurActuel();
        pointUse=batimentEnCours.get(choix).getConso_pa();
            if(batimentEnCours.get(choix).setConso_pa((batimentEnCours.get(choix).getConso_pa()-num))){
                partieActuelle.getMaVille().setNouveauBatiment(partieActuelle.getMonJournal().getConstruction(batimentEnCours.get(choix).getNom()));
                ceJoueur.setPa(ceJoueur.getPa()-pointUse);
            }else{
                ceJoueur.setPa(ceJoueur.getPa()-num);
            }
    }
    /**************************************************************************/
    
    /*************************Getters améliorés********************************/
    //permet de récupérer le contenu du tableau Entrepot sous forme de String
    public String consulterEntrepot(){
         String tabEntrepot="\n";
        for(int i=0;i<entrepot.length;i++){
            tabEntrepot+=i+" | "+entrepot[i].getNom()+" | "+entrepot[i].getQuantite()+'\n';
        }
        return tabEntrepot;
    }
    //permet de récupérer le contenu du tableau des Constructions dans la ville sous forme de String
    public String afficherConstruction() {
        String tabNom=Journal.consulterDescription(82);
        for(int i=0;i<batiment.size();i++){
            tabNom+=batiment.get(i).getNom()+" - "+batiment.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    //permet de récupérer le contenu du tableau des constructions en cours de la ville  sous forme de String
    public String afficherConstructionEnCours() {
        String tabNom=Journal.consulterDescription(83);
        for(int i=0;i<batimentEnCours.size();i++){
            tabNom+=batimentEnCours.get(i).getNom()+" - "+batimentEnCours.get(i).getConso_pa()+" - "+batimentEnCours.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    // permet de calculer le taux de défense de la ville et de la renvoyer sous forme de int
    public int defenseVille() {
        this.tauxDefense = 0;
        if (!ouverturePorte) {
            this.tauxDefense = 20;
            if(!batiment.isEmpty())
            {
                for(int i=0;i<batiment.size();i++){
                    this.tauxDefense+=batiment.get(i).getResistance();
                }
            }
        }
        return this.tauxDefense;
    }
/**************************************************************************/
    
    
    /**********************Intéraction avec la Porte*************************************/
    //Demande à l'utilisateur s'il veut ouvrir ou fermer la porte.
    public boolean ouverturePorte(Jeu partieActuelle) {
        //bool ouverte -> 1 sinon 0
        
        if (ouverturePorte == true) {
            Outils.affichage(Journal.consulterDescription(78),partieActuelle.getMonInterface());
            Outils.affichage(Journal.consulterDescription(79),partieActuelle.getMonInterface());
            return true;
            
        } else {
            Outils.affichage(Journal.consulterDescription(80),partieActuelle.getMonInterface());
            Outils.affichage(Journal.consulterDescription(81),partieActuelle.getMonInterface());
            return false;
        }
        
    }
    //permet d'ouvrir ou de ferme la porte.
    public boolean actionnerPorte(boolean b){
        if(b){
            ouverturePorte=false;
        }else{
            ouverturePorte=true;
        }
        return true;    
    }
/**************************************************************************/
    
    
 /**********************Création  d'Item***************************/
    // Permet de créer l'objet Gourde
    public Item remplirGourde(Jeu partieActuelle){
        Item gourde = new Item(Journal.consulterDescription(52),Journal.consulterDescription(0));
        Outils.affichage(Journal.consulterDescription(31)+Journal.consulterDescription(52)+Journal.consulterDescription(110),partieActuelle.getMonInterface());
        return gourde;
    }
    
    // Permet de prendre l'objet Ration de l'entrepot.
    public Item prendreRation(Jeu partieActuelle) {
        Item ration;
        if (this.entrepot[0].getQuantite()>0) {
            this.entrepot[0].setQuantite(this.entrepot[0].getQuantite() - 1);
            ration = new Item(Journal.consulterDescription(51),Journal.consulterDescription(1));
            Outils.affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110),partieActuelle.getMonInterface());
        }else{ration = null;Outils.affichage(Journal.consulterDescription(84),partieActuelle.getMonInterface());}
        return ration;
    }
    
    // Permet de prendre l'objet Boisson de l'entrepot.
    public Item prendreBoisson(Jeu partieActuelle) {
        Item boisson;
        if (this.entrepot[3].getQuantite()>0) {
            this.entrepot[3].setQuantite(this.entrepot[3].getQuantite() - 1);
            boisson = new Item(Journal.consulterDescription(53),Journal.consulterDescription(4));
            Outils.affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110),partieActuelle.getMonInterface());
        }else{boisson = null;Outils.affichage(Journal.consulterDescription(85),partieActuelle.getMonInterface());}
        return boisson;
    }
/**************************************************************************/
}
