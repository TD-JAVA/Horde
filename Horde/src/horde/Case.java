/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import static horde.Menu.conversionCaractere;
import java.util.Random;

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

    public Case(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nbZombiesRestants = 0;
        this.fouillee = false;
        this.caseTrouve = false;
    }
    
        public void caseTrouve(Jeu partie){ // peut étre inutile j'en avais besoin pour aider à faire mon code
          if (this.caseTrouve == true){ // si la case est déja trouvée alors on ne touche pas au PA
              int pa = partie.getJoueurActuel().getPa();
              System.out.println("Case déja trouvée !");
          }else{
              System.out.println("Case inconnue !");
          }   
  }
    
    public void fouiller(Jeu partie){ // On donne le choix au joueur de fouiller la case ou non
        System.out.println("Case non fouillée (coute un point d'action pour étre fouiller), que voulez vous faire ?");
        char choix = conversionCaractere('R');
        switch (choix) {
            case 1: // choix si le joueur veut fouiller la case
                if (this.fouillee == true){
                int pa = partie.getJoueurActuel().getPa() -1 ;    
                }else{
                    System.out.println("Points d'action insufisants !");
                }
            break;
            case 2: // choix si le joueur ne veut pas fouiller la case
                if (this.fouillee ==false){
                    int pa = partie.getJoueurActuel().getPa(); 
                }
            break;
    }
  }

    public void quitter(Jeu partie){ // pas encore d'idées
        

  }

    public void attaquer(Jeu partie, Zombies Zombies){ // Le switch peut étre inutile dans le cas présent, mais je le conserve au cas ou pour plus tard
        boolean attaquerZombie = false;
         System.out.println("Attaquer zombie ?");
        char choix = conversionCaractere('R');
        switch (choix) {
            case 1: // choix si le joueur veut attaquer le zombie
                if (attaquerZombie == true && Zombies.nbZombiesCase >=1 ){
                    System.out.println("Attaque en cours !");
                    int pa = partie.getJoueurActuel().getPa() -1 ;
                    int nbZombies = Zombies.nbZombiesCase-1;
                    Random ra = new Random(); 
                    int pv = partie.getJoueurActuel().getPdv();
                    int poucentageFrappe = ra.nextInt(100 - 0); // numéro aléatoire en 0 et 100
                    if(poucentageFrappe <=10){ // conditon pour les 10% de chances de perdre de la vie
                        pv = partie.getJoueurActuel().getPdv()-10;
                    }
                }else{
                    System.out.println("Points d'action inssufisants !");
                }
            break;
    }
  }
    
}
