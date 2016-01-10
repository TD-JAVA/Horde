/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author oneiroi
 */
public class Menu {
    
    //Variable memoire pour conserver le menu et le jeu 
    private Jeu partieActuelle;
    private Menu menuActuel;
    private ArrayList<Case> tabGrille;
    private Scanner sc = new Scanner(System.in);
    private boolean consommationDePA;

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
            affichage(Journal.consulterDescription(9));
            if(conversionBoolean(sc.next())){
                partie.setPartie(false);
                partie.lancerJeu();
                outilInitPartie(partie);
            }else{
                outilInitGrille(partie);
            }
        }else{
            outilInitPartie(partie);
        }
    }
    
    public static void affichage(String str){
        System.out.println(str);
    }
    /********************* Outils de démarrage **************************/
    //Ces deux méthodes servent à simplifier la première.
    //Pour outilInitPartie: on lance le jeu, on l'initialise.
    //Si l'initialisation a fonctionné on lance la seconde méthode.
    //Pour outilInitGrille: on stocke la partie en cours dans partieActuelle.
    //on conserve les cases dans tabGrille. Et on lance le menu 0. 
    /*
     * @param partie est la jeu est en cours.
     */
    public void outilInitPartie(Jeu partie){
        partie.initialisation();
        if(partie.getPartie()){
            outilInitGrille(partie);    
        }
    }
    /*
     * @param partie est la jeu est en cours.
     */
    public void outilInitGrille(Jeu partie){
        this.setPartieActuelle(partie);
        tabGrille=partieActuelle.getGrille().getTabCase();
        this.menuNiveauZero(this.getMenuActuel().conversionCaractere(this.afficher(0)));
    }
    /************************ Fin des outils de démarrage***********************/
    /*
     * @param choix est le choix de menu donnée par l'utilisateur.
     */
    
    public void menuNiveauZero(char choix){
        
        switch (choix) {
            case 'Q':   this.quitter();
                        break;
            case 'D':   //this.getPartieActuelle().lancerJeu();
                        this.demarrer(this.getPartieActuelle());
                        break;
            case 'S':   this.menuNiveauUn(Menu.conversionCaractere(this.afficher(1)));
                        break;
            default :   affichage(Journal.consulterDescription(6));
                        this.menuNiveauZero(afficher(0));
        }           
    }
    
    public static void quitter(){
        System.exit(0);
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
            case 'I':   this.interagirCase(verifier(this.afficher(2)));
                        break;
            case 'F':   this.finirTour();
                        break;
            case 'S':   this.interagirSac(afficher(4));
                        break;      
            case 'R':   this.retournerMenu(0);
                        break;
            default :   affichage(Journal.consulterDescription(6));
                        this.menuNiveauUn(afficher(1));
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
        affichage(Journal.consulterDescription(10));
        partieActuelle.getMonJournal().afficherPosition(partieActuelle, partieActuelle.getJoueurActuel());
        affichage(partieActuelle.getMonJournal().toString(partieActuelle,afficher(3))+"\n");
        sc.nextLine();
        String str = sc.nextLine();
        affichage("");
        menuNiveauUn(conversionCaractere(afficher(1)));
    }
    
    public void seDeplacer(){
        if(!tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()||tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()==0){
            char choix=afficher(5);
            consommationDePA=false;
                if(partieActuelle.getJoueurActuel().getPa()>0||(choix!='Z'&&choix!='Q'&&choix!='D'&&choix!='S')){
                    switch (choix) {
                        case 'Z':   outilDeplacement(false,true);
                                    break;
                        case 'Q':   outilDeplacement(true,false);
                                    break;
                        case 'D':   outilDeplacement(true,true);
                                    break;
                        case 'S':   outilDeplacement(false,false);
                                    break;
                        case 'R':   this.retournerMenu(1);
                                    break;
                        default :   affichage(Journal.consulterDescription(6));
                                    this.seDeplacer();
                    }
            }else{
                affichage(Journal.consulterDescription(5));
            }
            consommerPA();
            sc.nextLine();
            this.seDeplacer();
        }else{
            affichage(Journal.consulterDescription(7));
            this.retournerMenu(1);
        }
        
    }
    
    public void consommerPA(){
        if (consommationDePA){partieActuelle.getJoueurActuel().setPa(partieActuelle.getJoueurActuel().getPa()-1);}
    }
    
    public void outilDeplacement(boolean absysse,boolean positif){
        int getVal;
        boolean test;
        int indice;
        int getxyville;
        int getxy;
        if(absysse){
            getxyville=partieActuelle.getGrille().getxVille();
            getxy=partieActuelle.getJoueurActuel().getAbsysseActuelle();
            if(positif){
                getVal=1;
                indice=1;
                test=getxy<24;
            }else{
                getVal=-1;
                indice=-1;
                test=getxy>0;
            }
        }else{
            getxyville=partieActuelle.getGrille().getyVille();
            getxy=partieActuelle.getJoueurActuel().getOrdonneeActuelle();
            if(positif){
                getVal=1;
                indice=-25;
                test= getxy<0;
            }else{
                getVal=-1;
                indice=25;
                test=getxy>-24;
            }
        }   
        
        if(test){
            if(!partieActuelle.getMaVille().getOuverturePorte()){
                if(getxy+getVal==getxyville){
                    affichage(Journal.consulterDescription(11));
                }else{
                    if(getxy==getxyville){
                        affichage(Journal.consulterDescription(12));
                    }else{
                        if(absysse){partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()+getVal,partieActuelle.getJoueurActuel().getOrdonneeActuelle());}else{partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()+getVal);}
                        partieActuelle.getJoueurActuel().setIndiceCase(partieActuelle.getJoueurActuel().getIndiceCase()+indice);
                        consommationDePA=true;
                    }
                }
            }else{
                if(absysse){partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle()+getVal,partieActuelle.getJoueurActuel().getOrdonneeActuelle());}else{partieActuelle.getJoueurActuel().setCoordonneeActuelle(partieActuelle.getJoueurActuel().getAbsysseActuelle(),partieActuelle.getJoueurActuel().getOrdonneeActuelle()+getVal);}
                partieActuelle.getJoueurActuel().setIndiceCase(partieActuelle.getJoueurActuel().getIndiceCase()+indice);
                consommationDePA=true;
            }
        }
    }
    
    public void accederConstruction(){
        affichage(Journal.consulterDescription(13));
        if(conversionBoolean(sc.next())){
            affichage(partieActuelle.getMonJournal().consulterConstruction());
            affichage(Journal.consulterDescription(14));
            partieActuelle.getMaVille().construire(partieActuelle, donnerReponseChiffre(6) );
        }
    }
    public void accederEntrepot(){
        affichage(partieActuelle.getMaVille().consulterEntrepot());
        affichage(Journal.consulterDescription(15));
        if(conversionBoolean(sc.next())){
            affichage(Journal.consulterDescription(16));
            int num=Menu.donnerReponseChiffre(partieActuelle.getMaVille().getEntrepot().length-1);
            if(partieActuelle.getMaVille().getEntrepot()[num].getNom().equals(Journal.consulterDescription(51))){
                affichage(Journal.consulterDescription(17));
                if(conversionBoolean(sc.next())){
                    if(partieActuelle.getJoueurActuel().getSac().size()<10){
                        partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreRation());
                    }else{
                        affichage(Journal.consulterDescription(8));
                    }
                }
            }else{
                if(partieActuelle.getMaVille().getEntrepot()[num].getNom().equals(Journal.consulterDescription(53))){
                    affichage(Journal.consulterDescription(18));
                    if(conversionBoolean(sc.next())){
                        if(partieActuelle.getJoueurActuel().getSac().size()<10){
                            partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreBoisson());
                        }else{
                            affichage(Journal.consulterDescription(8));
                        }
                    }   
                }else{
                    affichage(Journal.consulterDescription(19));
                }
            }
        }
    }
    public void interagirPorte(){
        if(partieActuelle.getJoueurActuel().getPa()>0){
            consommationDePA=partieActuelle.getMaVille().ouverturePorte();
            consommerPA();
        }else{
            affichage(Journal.consulterDescription(5));
        }
    }
    
    public void accederChantier(){
        String[] str=partieActuelle.getMaVille().participerAuChantier(partieActuelle.getJoueurActuel());
        if(conversionBoolean(str[0])){
            partieActuelle.getMaVille().setNouveauBatiment(partieActuelle.getMonJournal().getConstruction(str[1]));
        }
    }
    
    public void accederDefense(){
        affichage(partieActuelle.getMaVille().afficherConstruction());
        affichage(Journal.consulterDescription(21)+partieActuelle.getMaVille().defenseVille());
    }
    
    public void prendreGourde(){
        affichage(Journal.consulterDescription(20));
        if(conversionBoolean(sc.next())){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().remplirGourde());
            }else{
                affichage(Journal.consulterDescription(8));
            }
        }
    }
    
    public void prendreRation(){
        affichage(Journal.consulterDescription(17));
        if(conversionBoolean(sc.next())){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                        partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreRation());
                    }else{
                        affichage(Journal.consulterDescription(8));
                    }
        }
    }
    
    public void fouillerCase(){
        consommationDePA=tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).fouiller();
        consommerPA();
    }
    public void attaquerZombies(){
        boolean joueurMort=false;
        affichage(Journal.consulterDescription(22)+tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants() +Journal.consulterDescription(23));
        if(tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()!=0){
            affichage(Journal.consulterDescription(24));
            if(conversionBoolean(sc.next())){
                joueurMort=tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).attaquer(partieActuelle.getJoueurActuel());
                affichage(Journal.consulterDescription(25)+tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()+Journal.consulterDescription(23));
            }    
        }
        if(joueurMort){
            if(partieActuelle.dernierJoueur()){partieActuelle.finDePartie();}else{finirTour();}
        }
    }
    public void accederObjet(){
        /*if(partieActuelle.getJoueurActuel().getCarteJoueur().get(partieActuelle.getJoueurActuel().getIndiceCase()).isEmpty()){
            partieActuelle.getJoueurActuel().getCarteJoueur().get(partieActuelle.getJoueurActuel().getIndiceCase()).set('ff');
        }*/
        
        if(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
          partieActuelle.getJoueurActuel().getCarteJoueur().add(partieActuelle.getJoueurActuel().getIndiceCase()+":"+partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).itemCarte());
        }
        affichage(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).afficherItems());
        affichage(Journal.consulterDescription(15));
        if(conversionBoolean(sc.next())){
            affichage(Journal.consulterDescription(16));
            int num=Menu.donnerReponseChiffre(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().size()-1);
            String nom=partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getNom();
            String description=partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getDescription();
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(new Item(nom,description));
                partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).setQuantite(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getQuantite()-1);
                affichage(Journal.consulterDescription(31)+nom+Journal.consulterDescription(110));
            }else{
                affichage(Journal.consulterDescription(8));
            }
        }
    }
    
    public void interagirCase(char choix) {    
        if(!tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()||tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()==0||choix=='A'){
            switch (choix) {
                case 'C':   accederConstruction();
                            break;
                case 'E':   accederEntrepot();
                            break;
                case 'I':   interagirPorte();
                            break;
                case 'P':   accederChantier();
                            break;
                case 'D':   accederDefense();
                            break;
                case 'B':   prendreGourde();
                            break;
                case 'M':   prendreRation();
                            break;
                case 'F':   fouillerCase();
                            break;
                case 'A':   attaquerZombies();
                            break;
                case 'O':   accederObjet();                   
                            break;
                case 'R':   this.retournerMenu(1);
                            break;
                default :   affichage(Journal.consulterDescription(6));
                            this.interagirCase(afficher(2));
            }    
        }else{
            affichage(Journal.consulterDescription(7));
        }
        this.retournerMenu(1);
    }
    
    public void accederBoire(){
        boolean consoSup=true;
        if(partieActuelle.getJoueurActuel().getPa()>4){consoSup=false;}
        if(!partieActuelle.getJoueurActuel().getDejaBu()){
            if(partieActuelle.getJoueurActuel().getPa()>0){
                consommationDePA=partieActuelle.getJoueurActuel().boire();
                if(!consommationDePA && partieActuelle.getJoueurActuel().getIndiceCase()==338){
                    affichage(Journal.consulterDescription(20));
                    if(conversionBoolean(sc.next())){
                        if(partieActuelle.getJoueurActuel().getSac().size()<10){
                            partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().remplirGourde());
                        }else{
                            affichage(Journal.consulterDescription(8));
                        }
                    }
                }else{
                    consommationDePA=consoSup;
                    consommerPA();
                }
            }else{
                affichage(Journal.consulterDescription(5));            
            }
        }else{
                affichage(Journal.consulterDescription(26));            
        }
    }
    
    public void accederManger(){
        boolean consoSup=true;
        if(partieActuelle.getJoueurActuel().getPa()>4){consoSup=false;}
        if(!partieActuelle.getJoueurActuel().getDejaMange()){
            if(partieActuelle.getJoueurActuel().getPa()>0){
                consommationDePA=partieActuelle.getJoueurActuel().manger();
                if(!consommationDePA && partieActuelle.getJoueurActuel().getIndiceCase()==338){
                    affichage(Journal.consulterDescription(17));
                    if(conversionBoolean(sc.next())){
                        if(partieActuelle.getJoueurActuel().getSac().size()<10){
                            Item ration=partieActuelle.getMaVille().prendreRation();
                            if(ration.getNom().equals(Journal.consulterDescription(51))){
                                partieActuelle.getJoueurActuel().getSac().add(ration);
                            }

                        }else{
                            affichage(Journal.consulterDescription(8));
                        }
                    }
                }else{
                    consommationDePA=consoSup;
                    consommerPA();

                }
            }else{
                affichage(Journal.consulterDescription(5));            
            }
        }else{
                affichage(Journal.consulterDescription(27));            
        }
    }
    
    public void accederVider(){
        partieActuelle.getJoueurActuel().viderSac(partieActuelle);
}
    public void accederBoireE(){
        consommationDePA=true;
        if(partieActuelle.getJoueurActuel().getPa()>6){consommationDePA=false;}
        if(partieActuelle.getJoueurActuel().boireBoisson()){
            consommerPA();
        }
    }   
    public void interagirSac(char choix){
        
        switch(choix){
            case 'B':   accederBoire();
                        break;
            case 'M':   accederManger();
                        break;
            case 'V':   accederVider();    
                        break;
            case 'E':   accederBoireE();
                        break;
            case 'R':   this.retournerMenu(1);
                        break;
                    }
        this.interagirSac(afficher(4));
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
        affichage(partieActuelle.getMonJournal().toString(partieActuelle, 'S'));
        switch (niveau) {
            case 0:     affichage(Journal.consulterDescription(35));
                        affichage(Journal.consulterDescription(36));
                        
                        return this.verification(sc.next(),0);
                        
            case 1:     affichage(Journal.consulterDescription(37));
                        affichage(Journal.consulterDescription(36));
                        
                        return this.verification(sc.next(),0);
                
            case 2:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            affichage(Journal.consulterDescription(38));
                        }else{
                            if(tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
                                affichage(Journal.consulterDescription(39));
                            }else{
                                affichage(Journal.consulterDescription(40));
                            }
                        }
                        affichage(Journal.consulterDescription(36));
                        return this.verification(sc.next(),0);
            case 3:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            affichage(Journal.consulterDescription(41));
                        }else{
                            affichage(Journal.consulterDescription(42));
                        }
                        affichage(Journal.consulterDescription(36));
                        return this.verification(sc.next(),0);
            case 4:     affichage(Journal.consulterDescription(43));
                        affichage(Journal.consulterDescription(36));
                        return this.verification(sc.next(),0);
            case 5:     affichage(Journal.consulterDescription(44));
                        affichage(Journal.consulterDescription(45));
                        affichage(Journal.consulterDescription(36));
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
                        affichage(Journal.consulterDescription(46));
                        verification(sc.next(),1);
                    }
                    return lettres[0];
                    
        }else{
            
                  while(lettres[0]<'A' && lettres[0]>'Z'){
                      affichage(Journal.consulterDescription(47));
                      verification(sc.next(),0);
                  }
                  return lettres[0];
                  
        }     
    }
    
    public char verifier(char choix){
    if(partieActuelle.getJoueurActuel().getIndiceCase()!=338){
                    if(choix=='C'||choix=='E'||choix=='I'||choix=='B'||choix=='D'||choix=='P'){
                        choix='K';
                    }
                    if(!tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
                        if(choix=='M'||choix=='A'||choix=='O'){
                            choix='K';
                        }
                    }
                }
                return choix;
    }
        
    public int conversionInt(String str){
        char[] lettres=str.toCharArray();
        int longueur=str.length();
        int num=0;
        
        if((longueur<2)){
            if((int)(lettres[0])>57 ||(int)(lettres[0])<49){
                affichage(Journal.consulterDescription(48));
                
                num=conversionInt(sc.next());
            }else{
                num=Integer.parseInt(str);
            }
        }else{
                if((int)(lettres[0])>50 ||(int)(lettres[0])<49 && (int)(lettres[1])>57 ||(int)(lettres[1])<48){
                    affichage(Journal.consulterDescription(48));
                    
                    num=conversionInt(sc.next());
                }else{
                num = Integer.parseInt(str);
                }
        }
        
        
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
                affichage(Journal.consulterDescription(50));
                return false;
            }
        }
    }
    
    public static char conversionChar(String str){
        char[] lettres =  str.toCharArray();
        return lettres[0];
    }
    
    public static int donnerReponseChiffre(int max){
        String str;
        int longueur,num;
        Scanner sc = new Scanner(System.in);
        char[] lettres;
        do{
            affichage(Journal.consulterDescription(49)+max);
            str=sc.next();
            longueur=str.length();
            lettres=str.toCharArray();
        }while(longueur!=1 ||((int)(lettres[0])>57 ||(int)(lettres[0])<48));
        num=Integer.parseInt(str);
        return num;
    }
    
    /**********************Fin des outils************************/
}

