/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.conversionBoolean;
import static horde.Menu.conversionCaractere;
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
    private int[] items= new int[20]; // valeur par défault//
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
    }
    
    public boolean getFouillee(){
        return fouillee;
    }
    public int getNbZombiesRestants(){
        return nbZombiesRestants;
    }
    
        public void caseTrouve(Jeu partie){ // peut étre inutile j'en avais besoin pour aider à faire mon code
          if (this.caseTrouve == true){ // si la case est déja trouvée alors on ne touche pas au PA
              int pa = partie.getJoueurActuel().getPa();
              System.out.println("Case déja trouvée !");
          }else{
              System.out.println("Case inconnue !");
          }   
  }
    
    public boolean fouiller(){ // On donne le choix au joueur de fouiller la case ou non
        System.out.println("Case non fouillée (coute un point d'action pour étre fouiller), que voulez vous faire ?");
        char choix = conversionCaractere('R');
        
        boolean changement=false;
        System.out.println("La case n'est pas fouillée, souhaitez vous le faire ?(O/N)");
        
        if(conversionBoolean(sc.next())){
            nbZombiesRestants=zomb.nbZombiesCase();
            System.out.println("Il y a "+nbZombiesRestants+" zombies sur cette case");
            fouillee=true;
            changement=true;
        }
        return changement;
  }

    public void quitter(Jeu partie){ // pas encore d'idées
        

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
