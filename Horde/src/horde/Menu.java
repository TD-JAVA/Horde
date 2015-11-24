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
    
    //Variable memoire pour conserver le menu et le jeu 
    private Jeu partieActuelle;
    private Menu menuActuel;
    

    //Constructor
    public Menu(){
        
    }
    
    /****************Getters&Setters**********************/
    
    /*
     * @param partie est ici la partie en cours de jeu
     */
    public void setPartieActuelle(Jeu partie){partieActuelle = partie;}
    
    /*
     * @return partieActuelle
     */
    public Jeu getPartieActuelle(){return partieActuelle;} 
    
    /*
     * @param menuJeu est le menu en cours d'utilisation
     */
    public void setMenuActuel(Menu menuJeu){menuActuel = menuJeu;}
    
    /*
     * @return menuActuel
     */
    public Menu getMenuActuel(){return menuActuel;}
    
    /**********Fin des getters setters**************/
    
    /**************Fonctions du menu de démarrage********************/
    
    
    /*
     * @param partie est ici la partie en cours de jeu. Ce paramètre est à 
     * conserver jusqu'à l'arrêt du jeu ou le démarrage d'une nouvelle partie.
     *
     *
     * On teste si il y a une partie en cours si c'est le cas, actuellement ne 
     * fait rien. Je demande à l'utilisateur s'il veut vraiment recommencer la
     * la partie. S'il le souhaite, j'initialise la partie en écrasant la 
     * précédente. Sinon, je le lui affiche à nouveau le menu niveau 0. 
     * S' il n'y a pas de partie, alors j'en initialise une nouvelle. Puis je le
     * lui affiche à nouveau le menu niveau 0. 
     *
     */
    
    public void demarrer(Jeu partie) {
        //this.setMenuActuel(this);
        if(partie.getPartie()){
            System.out.println("Jeu déjà démarré, voulez-vous redémarrer la partie ?");
            boolean answer=false; 
            if(answer){
                partie.initialisation();
                if(partie.getPartie()){
                    this.setPartieActuelle(partie);
                    this.menuNiveauZero(this.getMenuActuel().conversionCaractere(this.afficher(0)));
                }
            }else{
                this.setPartieActuelle(partie);
                this.menuNiveauZero(this.getMenuActuel().conversionCaractere(this.afficher(0)));
            
            }
        }else{
            partie.initialisation();
            if(partie.getPartie())
                    this.setPartieActuelle(partie);
                    this.menuNiveauZero(this.getMenuActuel().conversionCaractere(this.afficher(0)));
            }
    }
    
    /*
     * @param choix est le choix de menu donnée par l'utilisateur.
     */
    
    public void menuNiveauZero(char choix){
        
        switch (choix) {
            case 'Q':   this.quitter();
                        break;
            case 'D':   this.demarrer(this.getPartieActuelle());
                        break;
            case 'S':   this.menuNiveauUn(Menu.conversionCaractere(this.afficher(1)));
                        break;

        }           
    }
    
    public static void quitter(){
    
    }
    /********************Fin des fonctions du menu de démarrage*************************/
    
    /**********************Fonctions du menu de niveau 1 (Menu de jeu)****************************/
    
    /*
     * @param choix est le choix de menu donnée par l'utilisateur.
     *
     *
     *
     * 
     */
    
    public void menuNiveauUn(char choix){
        switch (choix) {
            case 'J':   this.lireJournal();
                        break;
            case 'D':   this.seDeplacer();
                        break;
            case 'I':   this.interagirCase();
                        break;
            case 'F':   this.finirTour();
                        break;
            case 'R':   this.retournerMenu(0);
                        break;
        }
    }
    
    /*
     * @param niveau est le menu vers lequel on s'oriente.
     */
    
    public void retournerMenu(int niveau){
        this.afficher(niveau);
    }
    
    public  void lireJournal(){
        System.out.println("Lire le journal");
        partieActuelle.getMonJournal().afficherPosition(partieActuelle, partieActuelle.getJoueurActuel());
        System.out.println(partieActuelle.getMonJournal().toString(partieActuelle.getTempsPartie(), partieActuelle.getMaVille()));
    }
    
    public void seDeplacer(){
        System.out.println("Chaque déplacement coute un point d'action dans quelle direction souhaitez aller ?");
        System.out.println("En haut(Z)\nÀ gauche(Q)\nÀ droite(D)\nEn Bas(S)\nRetour(R)\n");
        char choix=conversionCaractere('R');
        switch (choix) {
            case 'Z':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()<=-1){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()+1);
                        }
                        break;
            case 'Q':   if(partieActuelle.getJoueurActuel().getAbsysseActuelle()>=1){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()-1,partieActuelle.getJoueurActuel().getOrdonneeActuelle());
                        }
                        break;
            case 'D':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()<=24){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()+1,partieActuelle.getJoueurActuel().getOrdonneeActuelle());
                        }
                        break;
            case 'S':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()>=-24){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()-1);
                        }
                        break;
            case 'R':   this.retournerMenu(1);
                        break;
        }
    }
    
    public void interagirCase() {
        
        
    }
    
    public void finirTour() {
        partieActuelle.getTempsPartie().incrementerTour(partieActuelle);
    }
    
    /********Fin des fonctions du menu de niveau 1********/
    
    /***************Outils d'affichage dans la console******************/
    /*
     * @param niveau est le menu à afficher. 
     */
    public char afficher(int niveau){
        char answer = 's';
        switch (niveau) {
            case 0:     System.out.println("Demarrer(D)\nQuitter(Q)\nMenu(S)");
                        System.out.println("Quel est votre choix ?\n");
                        
                        return answer;
                        
            case 1:     System.out.println("Lire le journal(J)\nSe déplacer(D)\nIntéragir avec la case(I)\nFinir le tour(F)\nRetour(R)\n");
                        System.out.println("Quel est votre choix ?\n");
                        answer ='j';
                        return answer;
                
            case 2:     
                        return answer;

        }
        return 'e';
    }
    
    /*
     * @param lettre est le caractère à convertir en majuscule est le choix de menu donnée par l'utilisateur.
     */
    public static char conversionCaractere(char lettre){
    
        return Character.toUpperCase(lettre);
    }
    
    /**********************Fin des outils************************/
}

