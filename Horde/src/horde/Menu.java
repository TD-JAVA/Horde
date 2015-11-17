/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author oneiroi
 */
public class Menu {
    
    private Jeu partieActuelle;
    private Menu menuActuel;
    
    public Menu(){
        
    }
    
    public void setPartieActuelle(Jeu partie){
        partieActuelle = partie;
    }
    public Jeu getPartieActuelle(){
        return partieActuelle;
    } 
    
    public void setMenuActuel(Menu menuJeu){
        menuActuel = menuJeu;
    }
    public Menu getMenuActuel(){
        return menuActuel;
    }
    
    
    public static char affichage(){
        System.out.println("Demarrer(D)\nQuitter(Q)\nMenu(S)");
        System.out.println("Quel est votre choix ?");
        char answer = 'S';
        return answer;
    }
    public static void demarrer(Jeu partie, Menu menuJeu) {
        menuJeu.setMenuActuel(menuJeu);
        if(partie.getPartie()){
            System.out.println("Jeu déjà démarré, voulez-vous redémarrer la partie ?");
            boolean answer=false; 
            if(answer){
                partie.initialisation();
                if(partie.getPartie()){
                    menuJeu.setPartieActuelle(partie);
                    Menu.sousMenu(Menu.affichage(),menuJeu);
            }
        }else{
            partie.initialisation();
            if(partie.getPartie())
                    Menu.sousMenu(Menu.affichage(),menuJeu);
            }
        }
    }
    
    public static void quitter(){
    
    }
    
    public static void sousMenu(char choix,Menu menuJeu){
        
        switch (choix) {
            case 'Q':   Menu.quitter();
                        break;
            case 'D':   Menu.demarrer(menuJeu.getPartieActuelle(),menuJeu.getMenuActuel());
                        break;
            case 'S':   SousMenu.choisir(SousMenu.conversionCaractere(SousMenu.afficher()));
                        break;

        }
        
                
    }
}
