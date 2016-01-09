/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

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
    private String[] tabItems= {"Planche","Clou","Boisson énergissante"};
    private int nbRation=50;
    private int tauxDefense;
    ArrayList<Construction> batiment = new ArrayList<Construction>(7); // valeur par défaut//
    ArrayList<Construction> batimentEnCours = new ArrayList<Construction>(7); // valeur par défaut//
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
        entrepot[0]=new Item("Ration",50,Journal.consulterDescription(1));
        entrepot[1]=new Item("Planche",0,Journal.consulterDescription(2));
        entrepot[2]=new Item("Plaques de métal",0,Journal.consulterDescription(3));
        entrepot[3]=new Item("Boisson énergissante",0,Journal.consulterDescription(4));

    }

    public Ville(Jeu partie, int absysse, int ordonnee) {
        super(absysse, ordonnee);
        int i = ((absysse + 1) * (-1 * ordonnee));
        partie.getGrille().setTabCase(i, this);
        super.laVille = true;
        partie.getGrille().setxyVille(absysse, ordonnee);
    }

    public void afficherEntrepot(String str) {
        System.out.println(str);
    }
    
    public ArrayList<Construction> afficherConstruction(Journal Journal){
        return Journal.getTabConstruction();
    }

    public void construire(Jeu partie,int choix) {
        System.out.println("Chaque construction coute un point d'action.");
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
                System.out.println("Ressources ou points d'action insuffisants");
            }
        }else{
            System.out.println("Ce batiment est déjà fait");
        }    
    }
    
    public String[] participerAuChantier(Joueur ceJoueur){
        String[] fini={"",""};
        int num,pointUse;
        System.out.println(afficherConstructionEnCours());
        System.out.println("Quel est votre choix ?\n");
        num=Menu.donnerReponseChiffre(batimentEnCours.size()-1);
        
        System.out.println("Il reste "+batimentEnCours.get(num).getConso_pa()+" points d'action à dépenser pour terminer ce batiment.");
        System.out.println("Souhaitez vous investir ?(O/N)");
        if(Menu.conversionBoolean(sc.next())){
            System.out.println("Combien de points d'action souhaitez vous utiliser ?");
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
    
    public String afficherConstruction() {
        String tabNom="\nNom de la construction - Réssistance\n";
        for(int i=0;i<batiment.size();i++){
            tabNom+=batiment.get(i).getNom()+" - "+batiment.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    public String afficherConstructionEnCours() {
        String tabNom="\nNom de la construction - PA - Réssistance\n";
        for(int i=0;i<batimentEnCours.size();i++){
            tabNom+=batimentEnCours.get(i).getNom()+" - "+batimentEnCours.get(i).getConso_pa()+" - "+batimentEnCours.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    public void boire() {

    }
    
    public Item remplirGourde(){
        Item gourde = new Item("Gourde",Journal.consulterDescription(0));
        return gourde;
    }
    

    public Item prendreRation() {
        Item ration;
        if (this.entrepot[0].getQuantite()>0) {
            this.entrepot[0].setQuantite(this.entrepot[0].getQuantite() - 1);
            ration = new Item("Ration",Journal.consulterDescription(1));
    
        }else{ration = null;System.out.println("\nIl n' y a plus de ration.");}
        return ration;
    }
    
    public Item prendreBoisson() {
        Item boisson;
        if (this.entrepot[3].getQuantite()>0) {
            this.entrepot[3].setQuantite(this.entrepot[3].getQuantite() - 1);
            boisson = new Item("Boisson énergissante",Journal.consulterDescription(4));
    
        }else{boisson = null;System.out.println("\nIl n' y a plus de boisson énergissante.");}
        return boisson;
    }

    public Item[] getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Item[] entrepot) {
        this.entrepot = entrepot;
    }

    public int getNbRation() {
        return nbRation;
    }

    public void setNbRation(int nbRation) {
        this.nbRation = nbRation;
    }

    public ArrayList<Construction> getBatiment() {
        return batiment;
    }
    
    public ArrayList<Construction> getBatimentEnCours() {
        return batimentEnCours;
    }
    public void setBatiment(ArrayList<Construction> construction) {
        this.batiment = construction;
    }
    
    public void setNouveauBatiment(Construction construction){
        this.batiment.add(construction);
    }

}
