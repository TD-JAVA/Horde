/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.Scanner;

/**
 *
 * @author oneiroi
 */
public class Menu {
    
    //Variable memoire pour conserver le menu et le jeu 
    private Jeu partieActuelle;
    private Menu menuActuel;
    private Scanner sc = new Scanner(System.in);

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
            boolean answer=conversionBoolean(sc.next()); 
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
            case 'I':   this.interagirCase(Menu.conversionCaractere(this.afficher(2)));
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
        if(niveau==1){this.menuNiveauUn(this.afficher(niveau));
        }else{this.menuNiveauZero(this.afficher(niveau));}
    }
    
    public  void lireJournal(){
        System.out.println("Lire le journal");
        partieActuelle.getMonJournal().afficherPosition(partieActuelle, partieActuelle.getJoueurActuel());
        System.out.println(partieActuelle.getMonJournal().toString(partieActuelle.getTempsPartie(), partieActuelle.getMaVille(),partieActuelle,partieActuelle.getJoueurActuel(),afficher(3)));
        sc.nextLine();
        menuNiveauUn(conversionCaractere(afficher(1)));
    }
    
    public void seDeplacer(){
        System.out.println("Chaque déplacement coute un point d'action dans quelle direction souhaitez aller ?");
        System.out.println(partieActuelle.getJoueurActuel().getNom()+":("+partieActuelle.getJoueurActuel().getAbsysseActuelle()+";"+partieActuelle.getJoueurActuel().getOrdonneeActuelle()+")");
        System.out.println("Ville:("+partieActuelle.getGrille().getxVille()+";"+partieActuelle.getGrille().getyVille()+")");
        System.out.println("En haut(Z)\nÀ gauche(Q)\nÀ droite(D)\nEn Bas(S)\nRetour(R)\n");
        
        char choix=verification(sc.next(),0);
        
        switch (choix) {
            case 'Z':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()<0){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()+1);
                        }
                        break;
            case 'Q':   if(partieActuelle.getJoueurActuel().getAbsysseActuelle()>0){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()-1,partieActuelle.getJoueurActuel().getOrdonneeActuelle());
                        }
                        break;
            case 'D':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()<24){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()+1,partieActuelle.getJoueurActuel().getOrdonneeActuelle());
                        }
                        break;
            case 'S':   if(partieActuelle.getJoueurActuel().getOrdonneeActuelle()>-24){
                            partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()-1);
                        }
                        break;
            case 'R':   this.retournerMenu(1);
                        break;
        }
        System.out.println("("+partieActuelle.getJoueurActuel().getAbsysseActuelle()+";"+partieActuelle.getJoueurActuel().getOrdonneeActuelle()+")");
        this.retournerMenu(1);
    }
    
    public void interagirCase(char choix) {
        
        
        switch (choix) {
            case 'C':   partieActuelle.getMaVille().afficherConstruction(partieActuelle.getMonJournal());
                        System.out.println("Souhaitez-vous construire un nouveau batiment ?");
                        String answersUser=sc.next();
                        if((answersUser=="y") || (answersUser=="Y") || (answersUser=="yes") || (answersUser=="Yes") || (answersUser=="YES") || (answersUser=="O") || (answersUser=="o") || (answersUser=="Oui") || (answersUser=="oui") || (answersUser=="OUI")){
                            partieActuelle.getMaVille().getBatiment();
                        }
                        break;
            case 'E':   partieActuelle.getMaVille().afficherEntrepot();
                        break;
            case 'I':   partieActuelle.getMaVille().ouverturePorte();
                        break;
            case 'B':   partieActuelle.getMaVille().boire();
                        System.out.println("Voulez vous remplir une gourde ?(Y/n)");
                        answersUser=sc.next();
                        if((answersUser=="y") || (answersUser=="Y") || (answersUser=="yes") || (answersUser=="Yes") || (answersUser=="YES") || (answersUser=="O") || (answersUser=="o") || (answersUser=="Oui") || (answersUser=="oui") || (answersUser=="OUI")){
                            partieActuelle.getMaVille().remplirGourde();
                        }
                        break;
            case 'R':   this.retournerMenu(1);
                        break;
        }
        this.retournerMenu(1);
        
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
        
        switch (niveau) {
            case 0:     System.out.println("Demarrer(D)\nQuitter(Q)\nMenu(S)");
                        System.out.println("Quel est votre choix ?\n");
                        
                        return this.verification(sc.next(),0);
                        
            case 1:     System.out.println("Lire le journal(J)\nSe déplacer(D)\nIntéragir avec la case(I)\nFinir le tour(F)\nRetour(R)\n");
                        System.out.println("Quel est votre choix ?\n");
                        
                        return this.verification(sc.next(),0);
                
            case 2:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            System.out.println("Construire(C)\nConsulter l'entrepot(E)\nInteragir avec la porte(I)\nBoire(B)\nRetour(R)\n");
                        }else{
                            System.out.println("Fouiller la case(F)\nRetour(R)");
                        }
                        return this.verification(sc.next(),0);
            case 3:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            System.out.println("0. Résumé du jeu(J)\n1. Situation(S)\n2. Règle du jeu(K)\n3. Item dans l'entrepôt(I)\n4. Liste des constructions(C)\nRetour (R)");
                        }else{
                            System.out.println("0. Résumé du jeu(J)\n1. Situation(S)\n2. Règle du jeu(K)\nRetour (R)");
                        }
                        return this.verification(sc.next(),0);
        }
        return 'e';
    }
    
    /*
     * @param lettre est le caractère à convertir en majuscule est le choix de menu donnée par l'utilisateur.
     */
    public static char conversionCaractere(char lettre){
    
        return Character.toUpperCase(lettre);
    }
    
    public char verification(String str, int choix){
        str = str.toUpperCase();
        char[] lettres = str.toCharArray();
        if(choix==1) {
           
                    while((str!="Y")||(str!="O")||(str!="YES")||(str!="OUI")||(str!="N")||(str!="NON")||(str!="NO")){
                        System.out.println("La saisie est erronée veuillez réessayer. \n Entrez une réponse (O/N):");
                        verification(sc.next(),1);
                    }
                    return lettres[0];
                    
        }else{
                    
                    while(lettres[0]<'A' && lettres[0]>'Z'){
                        System.out.println("La saisie est erronée veuillez réessayer. \n Entrez votre choix:");
                        verification(sc.next(),0);
                    }
                    return lettres[0];
        }     
        }
        
    public int conversionInt(String str){
        char[] lettres=str.toCharArray();
        int num=0;
        System.out.println((int)lettres[0]);
        while((int)(lettres[0])>20 && (int)(lettres[0])<1){
            System.out.println("Saississez un nombre entre 1 et 20");
           lettres=sc.next().toCharArray();
        }
        num=lettres[0];
        return num;
    }
    public static boolean conversionBoolean(String str){
        char[] lettres =  str.toCharArray();
        if(conversionCaractere(lettres[0])=='Y'||conversionCaractere(lettres[0])=='O'){
            return true;
        }else{ 
            if(conversionCaractere(lettres[0])=='N'){
                return false;
            }else{
                System.out.println("Erreur");
                return false;
            }
        }
    }
    
    public static char conversionChar(String str){
        char[] lettres =  str.toCharArray();
        return lettres[0];
    }
    
    /**********************Fin des outils************************/
}

