/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Gabriel
 */
public class Horde {
    //

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
