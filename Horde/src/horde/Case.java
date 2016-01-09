/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.conversionBoolean;
import static horde.Menu.conversionCaractere;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Case {
    protected int longitude;
    protected int latitude;
    protected int nbZombiesRestants;
    private ArrayList<Item> items= new ArrayList<Item>(); // valeur par défault//
    private boolean fouillee;
    private boolean caseTrouve;
    protected boolean laVille = false;
    private Zombies zomb=new Zombies();
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
    
    public String afficherItems(){
        String tabItem="\n";
        for(int i=0;i<items.size();i++){
            tabItem+=i+" | "+items.get(i).getNom()+" | "+items.get(i).getQuantite()+'\n';
        }
   
        return tabItem;
    }
    
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
    public boolean fouiller(){ // On donne le choix au joueur de fouiller la case ou non
        boolean changement=false;
        Menu.affichage(Journal.consulterDescription(70));
        
        if(conversionBoolean(sc.next())){
            nbZombiesRestants=Zombies.nbZombiesCase();
            Menu.affichage(Journal.consulterDescription(22)+nbZombiesRestants+Journal.consulterDescription(23));
            fouillee=true;
            changement=true;
        }
        return changement;
  }

    public boolean attaquer(Joueur ceJoueur){
        int pourcentage;
        boolean changement=false;
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
