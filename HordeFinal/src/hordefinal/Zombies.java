/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;

import java.util.Random;

/**
 *
 * @author Gabriel, Sébastien, Valère.
 */
/*
Ici, nous nous sommes aperçus que l'instantiation d'un objet Zombies n'était 
pas nécessaire. Mais l'idée d'une classe zombies nous paraissait cohérente.
Le mot clé abstract précise cela. Ainsi, toutes les méthodes possèderont le
mot clé static pour signifier qu'elles ne seront pas appelés pas avec des 
objets.
*/

public abstract class Zombies {
   //Les deux méthodes qui suivent décrivent les attaques des zombies. Une pour
   //celle se déroulant chaque une nuit et une pour celle aléatoire lorsqu'on 
   //fouille une case. 
    /*
    *
    *@return on retourne un entier correspondant au nombre de zombies attaquant
    *la ville. Cette entier est dépendant du temps et en particulier, du nombre
    *de jours écoulés
    */
    
    /*
    *
    *@param Temps temps on appelle l'objet temps afin d'obtenir les informations
    *temporelles de la partie. Ici, nous utilisons le nombre de jours écoulés.
    */
    
    public  static int attaqueNuitZombies(Temps temps){
        Random ra = new Random();
        int n=temps.getNbJour();
        return n*10 + ra.nextInt(10);//Cela donne un entier qui oscille entre
        //10n et 10(n+1)
    }
    
    /*
    *
    *@return on retourne un entier qui correspond au nombre de zombies présents
    sur la case une fois fouillée. Ce nombre est donnée aléatoirement. Si le 
    résultat est supérieur à 3, on le retourne en soustrayant 3 car le nombre
    de zombies doit être compris entre 1 et 7. Si il est inférieur ou égale à 3,
    on revoit 0 car il y a 30% de chance qu'il n'y ait pas de zombies.
    */
    public static int nbZombiesCase(){
        Random ra = new Random();
         int nbZombiesCase = ra.nextInt(10 - 0); 
        if(nbZombiesCase>3){
            return nbZombiesCase-3;
        }else{
            return 0;
        }
    }
}
