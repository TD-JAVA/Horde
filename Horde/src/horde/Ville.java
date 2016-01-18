/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Outils.affichage;
import static horde.Outils.conversionBoolean;
import static horde.Outils.conversionCaractere;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Gabriel, Sébastien & Valère
 */


/*
    Nous nous sommes renseigné sur la notion d'héritage que nous avions pas vu en cours afin de l'utiliser dans la classe Ville.
    Ici, la classe Ville hérite  donc de  la classe Case.
*/

public class Ville extends Case {
    
    Scanner sc=new Scanner(System.in);
    private Item[] entrepot= new Item[4]; // L'entrepôt est un tableau de quatre Objets Items différents
    private int tauxDefense; // Indique le taux de défense de la ville
    ArrayList<Construction> batiment = new ArrayList<Construction>(7); // C'est le tableau regroupant tous les batiments construits dans la partie
    ArrayList<Construction> batimentEnCours = new ArrayList<Construction>(7); // C'est le tableau regroupant les batiments en cours de construction
    private boolean ouverturePorte = true; // L'attribut indique si la porte de la ville est ouverte ou fermée
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
    
    //Méthode appelé au moment de la confirmation de la construction du batiment
    public void construire(Jeu partie,int choix) {
        Outils.affichage(Journal.consulterDescription(71));
        boolean batimentDejaFait=false;
        //Pour chaque batiment existant
        for(int i=0;i<batiment.size();i++){
            // Si un batiment est déja construit
            if(partie.getMonJournal().getConstruction(choix).getNom().equals(batimentEnCours.get(i).getNom())||partie.getMonJournal().getConstruction(choix).getNom().equals(batiment.get(i).getNom())){
                batimentDejaFait=true;
            }
        }
        //Si le batiment n'est pas encore construit
        if(!batimentDejaFait){
            // Si le joueur possède les ressources et les points d'action nécessaires
            if (this.entrepot[1].getQuantite() >=partie.getMonJournal().getConstruction(choix).getRessources_necessaire().get(0)  && this.entrepot[2].getQuantite() >= partie.getMonJournal().getConstruction(choix).getRessources_necessaire().get(1) && partie.getJoueurActuel().getPa() >= 1) {
                partie.getJoueurActuel().setPa(partie.getJoueurActuel().getPa()-1);
                batimentEnCours.add(partie.getMonJournal().getConstruction(choix));
            } else {
                Outils.affichage(Journal.consulterDescription(72));
            }
        }else{
            Outils.affichage(Journal.consulterDescription(72));
        }    
    }
    
    //Méthode qui permet à un joueur de participer aux constructions en cours.
    public String[] participerAuChantier(Joueur ceJoueur){
        String[] fini={"",""};
        int num,choix,pointUse;
        //S'il existe des batiments en cours de construction
        if(!this.getBatimentEnCours().isEmpty()){
            Outils.affichage(afficherConstructionEnCours());
            Outils.affichage(Journal.consulterDescription(36));
            choix=Outils.donnerReponseChiffre(batimentEnCours.size()-1);
            Outils.affichage(Journal.consulterDescription(74)+batimentEnCours.get(choix).getConso_pa()+Journal.consulterDescription(75));
            Outils.affichage(Journal.consulterDescription(76));
            //Si le joueur confirme vouloir participer aux constructions
            if(Outils.conversionBoolean(sc.next())){
                Outils.affichage(Journal.consulterDescription(77));
                num=Outils.donnerReponseChiffre(ceJoueur.getPa());
                pointUse=batimentEnCours.get(choix).getConso_pa();
                if(batimentEnCours.get(choix).setConso_pa((batimentEnCours.get(choix).getConso_pa()-num))){
                    fini[0]="Y";
                    fini[1]=batimentEnCours.get(choix).getNom();
                    ceJoueur.setPa(ceJoueur.getPa()-pointUse);
                }else{
                    ceJoueur.setPa(ceJoueur.getPa()-num);
                }
        }
        }else{
            fini[0]="N";
            fini[1]=Journal.consulterDescription(115);
        }
        
        return fini;
    }
    
    // Méthode permettant au joueur de consulter l'entrepot
    public String consulterEntrepot(){
         String tabEntrepot="\n";
         // Pour chaque objet de l'entrepot
        for(int i=0;i<entrepot.length;i++){
            // On affiche sa quantité et son nom
            tabEntrepot+=i+" | "+entrepot[i].getNom()+" | "+entrepot[i].getQuantite()+'\n';
        }
        return tabEntrepot;
    }
    //Méthode qui retourne le taux de défense de la ville
    public int defenseVille() {
        this.tauxDefense = 0;
        //Si la porte de la ville est fermée
        if (!ouverturePorte) {
            this.tauxDefense = 20;
            //Si des batiments ont été construits
            if(!batiment.isEmpty())
            {
                //Pour chaque batiment construit
                for(int i=0;i<batiment.size();i++){
                    //On récupère les points de défense et on les ajoute au taux de défense
                    this.tauxDefense+=batiment.get(i).getResistance();
                }
            }
        }
        return this.tauxDefense;
    }

    //Méthode qui indique si la porte est ouverte en renvoyant un booléen
    public boolean ouverturePorte() {
        //bool ouverte -> 1 sinon 0
        boolean changement=false;
        //Si la porte est ouverte
        if (ouverturePorte == true) {
            System.out.print(Journal.consulterDescription(78));
            Outils.affichage(Journal.consulterDescription(79));
            //Si on confirme vouiloir ouvrir la porte
            if(Outils.conversionBoolean(sc.next())){
                ouverturePorte=false;
                changement=true;
            }
        } else {
            System.out.print(Journal.consulterDescription(80));
            Outils.affichage(Journal.consulterDescription(81));
            //Si on confirme vouloir fermer la porte
            if(Outils.conversionBoolean(sc.next())){
                ouverturePorte=true;
                changement=true;
            }
        }
        return changement;
    }

    //Méthode qui affiche tous les batiments construits
    public String afficherConstruction() {
        String tabNom=Journal.consulterDescription(82);
        for(int i=0;i<batiment.size();i++){
            tabNom+=batiment.get(i).getNom()+" - "+batiment.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    //Méthode qui affiche tous les batiments en cours de construction
    public String afficherConstructionEnCours() {
        String tabNom=Journal.consulterDescription(83);
        for(int i=0;i<batimentEnCours.size();i++){
            tabNom+=batimentEnCours.get(i).getNom()+" - "+batimentEnCours.get(i).getConso_pa()+" - "+batimentEnCours.get(i).getResistance()+"\n";
        }
        return tabNom;
    }
    
    //Méthode qui ajoute une gourde dans le sac du joueur
    public Item remplirGourde(){
        Item gourde = new Item(Journal.consulterDescription(52),Journal.consulterDescription(0));
        affichage(Journal.consulterDescription(31)+Journal.consulterDescription(52)+Journal.consulterDescription(110));
        return gourde;
    }
    
    // Méthode qui ajoute une ration dans le sac du joueur
    public Item prendreRation() {
        Item ration;
        //S'il reste des rations
        if (this.entrepot[0].getQuantite()>0) {
            this.entrepot[0].setQuantite(this.entrepot[0].getQuantite() - 1);
            ration = new Item(Journal.consulterDescription(51),Journal.consulterDescription(1));
            affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110));
        }else{ration = null;Outils.affichage(Journal.consulterDescription(84));}
        return ration;
    }
    
    //Méthode qui rajoute une boisson énergisante dans le sac du joueur
    public Item prendreBoisson() {
        Item boisson;
        //S'il reste des boissons énergisantes
        if (this.entrepot[3].getQuantite()>0) {
            this.entrepot[3].setQuantite(this.entrepot[3].getQuantite() - 1);
            boisson = new Item(Journal.consulterDescription(53),Journal.consulterDescription(4));
            affichage(Journal.consulterDescription(31)+Journal.consulterDescription(51)+Journal.consulterDescription(110));
        }else{boisson = null;Outils.affichage(Journal.consulterDescription(85));}
        return boisson;
    }

}
