/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.affichage;
import static horde.Menu.conversionBoolean;
import static horde.Menu.conversionCaractere;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Ville extends Case {
    
    Scanner sc=new Scanner(System.in);
    private Item[] entrepot= new Item[4]; // valeur par défaut//
    private int tauxDefense;
    ArrayList<Construction> batiment = new ArrayList<Construction>(7); // valeur par défaut//
    ArrayList<Construction> batimentEnCours = new ArrayList<Construction>(7); // valeur par défaut//
    private boolean ouverturePorte = true;
    public int getTauxDefense() {return tauxDefense;}
    public void setTauxDefense(int tauxDefense) {this.tauxDefense = tauxDefense;}

    public Ville(Jeu partie) {
        super(13, -13);
        int i = ((26*26)/2);
        //entrepot={50,0,0,0}; //ration, planche,clou,boisson
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(13, -13);
        entrepot[0]=new Item(Journal.consulterDescription(51),50,Journal.consulterDescription(1));
        entrepot[1]=new Item(Journal.consulterDescription(54),0,Journal.consulterDescription(2));
        entrepot[2]=new Item("Plaques de métal",0,Journal.consulterDescription(3));
        entrepot[3]=new Item(Journal.consulterDescription(53),0,Journal.consulterDescription(4));
    }

    public Ville(Jeu partie, int absysse, int ordonnee) {
        super(absysse, ordonnee);
        int i = ((absysse + 1) * (-1 * ordonnee));
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(absysse, ordonnee);
    }

    public ArrayList<Construction> afficherConstruction(Journal Journal){return Journal.getTabConstruction();}
    public Item[] getEntrepot() {return entrepot;}
    public void setEntrepot(Item[] entrepot) {this.entrepot = entrepot;}
    public ArrayList<Construction> getBatiment() {return batiment;}
    public ArrayList<Construction> getBatimentEnCours() {return batimentEnCours;}
    public void setBatiment(ArrayList<Construction> construction) {this.batiment = construction;}
    public void setNouveauBatiment(Construction construction){this.batiment.add(construction);}
    public boolean getOuverturePorte(){return ouverturePorte;}
    
    public void construire(Jeu partie,int choix) {
        Menu.affichage(Journal.consulterDescription(71));
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
                Menu.affichage(Journal.consulterDescription(72));
            }
        }else{
            Menu.affichage(Journal.consulterDescription(72));
        }    
    }
    
    public String[] participerAuChantier(Joueur ceJoueur){
        String[] fini={"",""};
        int num,pointUse;
        Menu.affichage(afficherConstructionEnCours());
        Menu.affichage(Journal.consulterDescription(36));
        num=Menu.donnerReponseChiffre(batimentEnCours.size()-1);
        Menu.affichage(Journal.consulterDescription(74)+batimentEnCours.get(num).getConso_pa()+Journal.consulterDescription(75));
        Menu.affichage(Journal.consulterDescription(76));
        if(Menu.conversionBoolean(sc.next())){
            Menu.affichage(Journal.consulterDescription(77));
            num=Menu.donnerReponseChiffre(ceJoueur.getPa());
            pointUse=batimentEnCours.get(num).getConso_pa();
            if(batimentEnCours.get(num).setConso_pa((batimentEnCours.get(num).getConso_pa()-num))){
                fini[0]="Y";
                fini[1]=batimentEnCours.get(num).getNom();
                ceJoueur.setPa(ceJoueur.getPa()-pointUse);
            }else{
                ceJoueur.setPa(0);
            }
        }
        return fini;
    }
    
    
    public String consulterEntrepot(){
         String tabEntrepot="\n";
        for(int i=0;i<entrepot.length;i++){
            tabEntrepot+=i+" | "+entrepot[i].getNom()+" | "+entrepot[i].getQuantite()+'\n';
        }
        return tabEntrepot;
    }

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

    public boolean ouverturePorte() {
        //bool ouverte -> 1 sinon 0
        boolean changement=false;
        if (ouverturePorte == true) {
            System.out.print(Journal.consulterDescription(78));
            Menu.affichage(Journal.consulterDescription(79));
            if(Menu.conversionBoolean(sc.next())){
                ouverturePorte=false;
                changement=true;
            }
        } else {
            System.out.print(Journal.consulterDescription(80));
            Menu.affichage(Journal.consulterDescription(81));
            if(Menu.conversionBoolean(sc.next())){
                ouverturePorte=true;
                changement=true;
            }
        }
        return changement;
    }

    public String afficherConstruction() {
        String tabNom=Journal.consulterDescription(82);
        for(int i=0;i<batiment.size();i++){
            tabNom+=batiment.get(i).getNom()+" - "+batiment.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    public String afficherConstructionEnCours() {
        String tabNom=Journal.consulterDescription(83);
        for(int i=0;i<batimentEnCours.size();i++){
            tabNom+=batimentEnCours.get(i).getNom()+" - "+batimentEnCours.get(i).getConso_pa()+" - "+batimentEnCours.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    public Item remplirGourde(){
        Item gourde = new Item(Journal.consulterDescription(52),Journal.consulterDescription(0));
        affichage(Journal.consulterDescription(31)+Journal.consulterDescription(52)+Journal.consulterDescription(110));
        return gourde;
    }
    

    public Item prendreRation() {
        Item ration;
        if (this.entrepot[0].getQuantite()>0) {
            this.entrepot[0].setQuantite(this.entrepot[0].getQuantite() - 1);
            ration = new Item(Journal.consulterDescription(51),Journal.consulterDescription(1));
            affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110));
        }else{ration = null;Menu.affichage(Journal.consulterDescription(84));}
        return ration;
    }

    public Item prendreBoisson() {
        Item boisson;
        if (this.entrepot[3].getQuantite()>0) {
            this.entrepot[3].setQuantite(this.entrepot[3].getQuantite() - 1);
            boisson = new Item(Journal.consulterDescription(53),Journal.consulterDescription(4));
            affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110));
        }else{boisson = null;Menu.affichage(Journal.consulterDescription(85));}
        return boisson;
    }

}
