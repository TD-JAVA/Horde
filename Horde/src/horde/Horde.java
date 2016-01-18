/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Gabriel, Sébastien & Valère
 */
public class Horde {
    /*
    
        Cette version de Horde est entièrement textuelle. 
        Nous n'avons pas utilisé de Try Catch, car nous nous n'avions pas vu cette notion dans le premier semestre.
        Nous n'avons pas vu la notion de threads, nous avons donc fait en sorte que le programme
            ne sorte pas des boucles afin qu'il ne s'arrête pas tant que le joueur ne quitte pas le jeu.
        Nous avons pensé ce projet comme une version multilingue, c'est pour cela que nous avons regroupé toutes les phrases dans la méthode consulterDescription(), dans la classe Journal. 
             Mais nous ne savions pas comment gérer les différentes versions.
    
    */

    /*
     * @param args the command line arguments
     */
    //Méthode qui crée le jeu et appelle la méthode de lancement du jeu
    public static void main(String[] args) {
        //Création de l'objet Jeu appelée partie
        Jeu partie = new Jeu();
        //Appel de la méthode lancerjeu
        partie.lancerJeu();
    }
}
