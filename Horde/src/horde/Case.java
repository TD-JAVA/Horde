/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Outils.conversionBoolean;
import static horde.Outils.conversionCaractere;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
  * @author Gabriel, Sébastien & Valère
 */

/*
    La classe Case permet de créer un tableau de Case qui sera la carte du jeu. Chaque case comporte un certain nombre d'objets et de zombies ( à part en ville)
*/

public class Case {
    protected int longitude; // Indique la longitude de la case
    protected int latitude; // Indique la latitude de la case
    protected int nbZombiesRestants; // Indique le nombre de zombies présents sur une case
    private ArrayList<Item> items= new ArrayList<Item>(); // C'est la liste d'Items présents dans chaque case , sauf dans celle comportat la ville.
    private boolean fouillee; // Cet attribut indique si la case a déja été fouillée. 
    private boolean caseTrouve; // Cet attribut indique si la case a déja été trouvé par un joueur
    protected boolean laVille = false; // Cet attribut indique si la case contient la ville 
    private Zombies zomb=new Zombies(); // Création d'un objet Zombie( servira à éparpiller un nombre aléatoire de zombies sur les cases)
    Scanner sc= new Scanner(System.in);
    Random ra=new Random();

    public Case(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nbZombiesRestants = 0;
        this.fouillee = false;
        this.caseTrouve = false;
        this.items.add(0,new Item(Journal.consulterDescription(54),0,Journal.consulterDescription(2)));
        this.items.add(1,new Item(Journal.consulterDescription(55),0,Journal.consulterDescription(3)));
        this.items.add(2,new Item(Journal.consulterDescription(53),0,Journal.consulterDescription(4)));
    }
    
    public Zombies getZombies(){
        return zomb;
    }
    
    //Méthode qui renvoie une phrase indiquant les items et leur quantité sur la case courante
    public String afficherItems(){
    
        String tabItem="\n";
        
        // Pour chaque objets dans le tableau d'items
        for(int i=0;i<items.size();i++){
            //On affiche leur nom et leur quantité
            tabItem+=i+" | "+items.get(i).getNom()+" | "+items.get(i).getQuantite()+'\n';
        }
        return tabItem;
    }
    
    // Méthode qui renvoie une chaine de caractères qui indiquera sur la carte les objets présents( seulement si la case est fouillée)
    public String itemCarte(){
        return "P"+this.items.get(0).getQuantite()+" M"+this.items.get(1).getQuantite()+" B"+this.items.get(2).getQuantite();
    }
    
    public boolean getFouillee(){
        return fouillee;
    }
    public int getNbZombiesRestants(){
        return nbZombiesRestants;
    }
    
    public ArrayList<Item> getItem(){
        return items;
    }    
    
    // Méthode appelé quand un joueur fouille la case courante
    public boolean fouiller(){ // On donne le choix au joueur de fouiller la case ou non
    
        boolean changement=false;
        Outils.affichage(Journal.consulterDescription(70));
        
        // Si on valide notre choix
        if(conversionBoolean(sc.next())){
            nbZombiesRestants=Zombies.nbZombiesCase();
            Outils.affichage(Journal.consulterDescription(22)+nbZombiesRestants+Journal.consulterDescription(23));
            fouillee=true;
            changement=true;
        }
        return changement;
  }
    // Méthode appelée quand un joueur attaque les zombies sur la case courante
    public boolean attaquer(Joueur ceJoueur){
        int pourcentage;
        boolean changement=false;
        //Pour chaque zombie restant
        if(ceJoueur.getPa()>=nbZombiesRestants){
            for(int i=0; i<nbZombiesRestants;i++){
                ceJoueur.setPa(ceJoueur.getPa()-1);
                pourcentage = ra.nextInt(100); // numéro aléatoire en 0 et 100
                if(pourcentage <=10){ // conditon pour les 10% de chances de perdre de la vie
                    changement=ceJoueur.setPdv(ceJoueur.getPdv()-10);
                }
            }
            nbZombiesRestants=0;
        }else{
              for(int i=0; i<ceJoueur.getPa();i++){
                nbZombiesRestants-=1;
                pourcentage = ra.nextInt(100); // numéro aléatoire en 0 et 100
                if(pourcentage <=10){ // conditon pour les 10% de chances de perdre de la vie
                    changement=ceJoueur.setPdv(ceJoueur.getPdv()-10);
                }
            }
            ceJoueur.setPa(0);
        }
        return changement;
    }
  }
