/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sébastien,Gabriel,Valère
 */
public class Menu {
    
    //Variable memoire pour conserver le menu et le jeu 
    private Jeu partieActuelle;
    private Menu menuActuel;
    private ArrayList<Case> tabGrille=new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private boolean consommationDePA=false;
    private FenetrePrincipale monInterface;
    private String strReceived="";
    //Constructor
    public Menu(FenetrePrincipale i){
        monInterface=i;
    }
    
    /****************Getters&Setters**********************/
    public void setStrReceived(String str){
        strReceived=str;
    }
    public String getStrReceived(){
        return strReceived;
    }
    public void setTabGrille(ArrayList<Case> t){
        for(int i=0;i<t.size();i++){
            tabGrille.add(t.get(i));
        }
    }

    public FenetrePrincipale getMonInterface(){return monInterface;}
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
    public ArrayList<Case> getTabGrille(){return tabGrille;}
    
    /**********Fin des getters setters**************/
    
    /**************Fonctions du menu de démarrage********************/
    
    
    /*
     * @param partie est ici la partie en cours de jeu. Ce paramètre est à 
     * conserver jusqu'à l'arrêt du jeu ou le démarrage d'une nouvelle partieActuelle.
     *
     *
     * On teste si il y a une partie en cours si c'est le cas, actuellement ne 
     * fait rien. Je demande à l'utilisateur s'il veut vraiment recommencer la
     * la partieActuelle. S'il le souhaite, j'initialise la partie en écrasant la 
     * précédente. Sinon, je le lui affiche à nouveau le menu niveau 0. 
     * S' il n'y a pas de partie, alors j'en initialise une nouvelle. Puis je le
     * lui affiche à nouveau le menu niveau 0. 
     *
     */
   
    
    public void demarrer(Jeu partie,FenetrePrincipale uneInterface) {
        //this.setMenuActuel(this);
        monInterface = uneInterface;
        if(partie.getPartie()){
            //Outils.affichage(Journal.consulterDescription(9),this.getMonInterface());            
            while(strReceived==""){}
            if(Outils.conversionBoolean(strReceived,this.partieActuelle)){
                partie.setPartie(false);
                partie.lancerJeu(monInterface);
                outilInitPartie(partie);
            }else{
                strReceived="";
                outilInitGrille(partie);
            }
        }else{
            this.outilInitPartie(partie);
        }
            
        
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
    }
    /************************ Fin des outils de démarrage***********************/
    /*
     * @param choix est le choix de menu donnée par l'utilisateur.
     */
    
    /**
     * Fin des outils de démarrage
     * @param choix
     */
    public void menuNiveauZero(char choix){
        
        switch (choix) {
            case 'Q':   this.quitter();
                        break;
            case 'D':   if(partieActuelle.getPartie()){
                        Outils.affichage(Journal.consulterDescription(9),this.getMonInterface());monInterface.setCpt(monInterface.getCpt()+28);}else{monInterface.getjButton4().doClick();}
                        
                        break;
            case 'S':   Outils.affichage(Journal.consulterDescription(37),monInterface);
                        Outils.affichage(Journal.consulterDescription(36),monInterface);
                        
                        monInterface.setCpt(monInterface.getCpt()+1);
                        
                        break;
            default :   Outils.affichage(Journal.consulterDescription(6),this.getMonInterface());
                        break;
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
            
                        monInterface.setCpt(monInterface.getCpt()+1);
                        break;
            case 'D':   if(!tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()||tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()==0){
                            Outils.afficher(5,partieActuelle);
                            monInterface.setCpt(monInterface.getCpt()+3);
                        }else{
                            Outils.affichage(Journal.consulterDescription(5),this.getMonInterface());
                        }
                
                        //
                        break;
            case 'I':   
                        monInterface.setCpt(monInterface.getCpt()+5);
                        
                        if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            Outils.affichage(Journal.consulterDescription(38),partieActuelle.getMonInterface());
                        }else{
                            if(partieActuelle.getMenuPartie().getTabGrille().get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
                                Outils.affichage(Journal.consulterDescription(39),partieActuelle.getMonInterface());
                            }else{
                                Outils.affichage(Journal.consulterDescription(40),partieActuelle.getMonInterface());
                            }
                        }
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        
                        break;
            case 'F':   monInterface.getjTextArea1().setText("");
                        this.finirTour();
                        break;
            case 'S':   Outils.afficher(4,partieActuelle);
                        monInterface.setCpt(monInterface.getCpt()+23);
                        break;      
            case 'R':   this.retournerMenu(0);
                        break;
            default :   Outils.affichage(Journal.consulterDescription(6),this.getMonInterface());
                        break;
        }
    }
    
    /*
     * @param niveau est le menu vers lequel on s'oriente.
     */
    
    public void retournerMenu(int niveau){
        monInterface.setCpt(monInterface.getCpt()-1);
        Outils.afficher(niveau,partieActuelle);
    }
    
    public void retournerMenu(){
        monInterface.setCpt(monInterface.getCpt()-1);
        Outils.afficher(monInterface.getCpt(),partieActuelle);
    }
    
    
    public void retournerMenu(int i,int niveau){
        monInterface.setCpt(monInterface.getCpt()+i);
        Outils.afficher(niveau,partieActuelle);
    }
    
    public  void lireJournal(){
        Outils.affichage(Journal.consulterDescription(10),this.getMonInterface());
        Journal.afficherPosition(partieActuelle, partieActuelle.getJoueurActuel());
        if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
            Outils.affichage(Journal.consulterDescription(41),monInterface);
        }else{
            Outils.affichage(Journal.consulterDescription(42),monInterface);
        }
        Outils.affichage(Journal.consulterDescription(36),monInterface);
        monInterface.setCpt(monInterface.getCpt()+1);
    }
    
    public void seDeplacer(char choix){
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
                case 'R':   this.retournerMenu(2);
                            break;
                default :   Outils.affichage(Journal.consulterDescription(6),this.getMonInterface());
                            break;
            }
    
            consommerPA();
            this.retournerMenu(-3);    
        }else{
            Outils.affichage(Journal.consulterDescription(7),this.getMonInterface());
            this.retournerMenu(2);
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
                    Outils.affichage(Journal.consulterDescription(11),this.getMonInterface());
                }else{
                    if(getxy==getxyville){
                        Outils.affichage(Journal.consulterDescription(12),this.getMonInterface());
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
    /*******************Fin fonctions du menu de niveau 1************************/
    
    /**********************Fonctions de interaction case*******************/
    public boolean accederConstruction(String str){
        return Outils.conversionBoolean(str,this.partieActuelle);
    }
    
    public boolean accederEntrepot(String str){    
        boolean b=Outils.conversionBoolean(str,this.partieActuelle);
        if(b){
            Outils.affichage(Journal.consulterDescription(16),this.getMonInterface());
            monInterface.setCpt(monInterface.getCpt()+2);
        }else{
            monInterface.setCpt(monInterface.getCpt()-2);
            retournerMenu();
        }
        return b;
    }

    public void accesObjet(boolean b, int a){
        if(a==1){    
            if(b){
                if(partieActuelle.getJoueurActuel().getSac().size()<10){
                    partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreRation(this.partieActuelle));
                }else{
                    Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
                }
            }
        }else{
            if(b){
                if(partieActuelle.getJoueurActuel().getSac().size()<10){
                    partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreBoisson(this.partieActuelle));
                }else{
                    Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
                }
            }
        }
        partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()-11);
        menuNiveauUn('I');
    }
    
    public int prendreObjet(int num){
        if(partieActuelle.getMaVille().getEntrepot()[num].getNom().equals(Journal.consulterDescription(51))){
            Outils.affichage(Journal.consulterDescription(17),this.getMonInterface());
            partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()+1);
            return 1;   
        }else{
            if(partieActuelle.getMaVille().getEntrepot()[num].getNom().equals(Journal.consulterDescription(53))){
                Outils.affichage(Journal.consulterDescription(18),this.getMonInterface());
                partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()+1);
                return 2;   
            }else{
                Outils.affichage(Journal.consulterDescription(19),this.getMonInterface());
                partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()-10);
                menuNiveauUn('I');
                return -1;
            }
        }
    }
    
    
    public void interagirPorte(boolean b){
        if(partieActuelle.getJoueurActuel().getPa()>0){
            consommationDePA=partieActuelle.getMaVille().actionnerPorte(b);
            consommerPA();
        }else{
            Outils.affichage(Journal.consulterDescription(5),this.getMonInterface());
        }
    }
    
    public void accederChantier(boolean b){
    }
    
    public void accederDefense(){
        Outils.affichage(partieActuelle.getMaVille().afficherConstruction(),this.getMonInterface());
        Outils.affichage(Journal.consulterDescription(21)+partieActuelle.getMaVille().defenseVille(),this.getMonInterface());
    }
    
    public void prendreGourde(boolean b){
        
        if(b){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().remplirGourde(this.partieActuelle));
            }else{
                Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
            }
        }
    }
    
    public void prendreRation(boolean b){
        
        if(b){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().prendreRation(this.partieActuelle));
            }else{
                Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
            }
        }
    }
    
    public void fouillerCase(boolean b){
        consommationDePA=tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).fouiller(this.partieActuelle,b);
        consommerPA();
    }
    
    public void attaquerZombies(){
        
        Outils.affichage(Journal.consulterDescription(22)+tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants() +Journal.consulterDescription(23),this.getMonInterface());
        if(tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()!=0){
            Outils.affichage(Journal.consulterDescription(24),this.getMonInterface());
            this.partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()+14);    
        }else{
            this.partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()-4);
            retournerMenu();
        }
        
    }
    
    public void attaquer(boolean b){
        boolean joueurMort=false;
    if(b){
        joueurMort=tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).attaquer(partieActuelle.getJoueurActuel());
        Outils.affichage(Journal.consulterDescription(25)+tabGrille.get(partieActuelle.getJoueurActuel().getIndiceCase()).getNbZombiesRestants()+Journal.consulterDescription(23),this.getMonInterface());
        }
    if(joueurMort){
            if(partieActuelle.dernierJoueur()){partieActuelle.finDePartie();}else{finirTour();}
        }
    }
    
    public void accederObjet(){
        if(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
          partieActuelle.getJoueurActuel().getCarteJoueur().add(partieActuelle.getJoueurActuel().getIndiceCase()+":"+partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).itemCarte());
        }
        Outils.affichage(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).afficherItems(),this.getMonInterface());
        Outils.affichage(Journal.consulterDescription(15),this.getMonInterface());
    }
    
    public void prendreObjetCase(int num){
    
  
        if(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getQuantite()!=0){
            String nom=partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getNom();
            String description=partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getDescription();
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(new Item(nom,description));
                partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).setQuantite(partieActuelle.getGrille().getTabCase().get(partieActuelle.getJoueurActuel().getIndiceCase()).getItem().get(num).getQuantite()-1);
                Outils.affichage(Journal.consulterDescription(31)+nom+Journal.consulterDescription(110),this.getMonInterface());
            }else{
                Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
            }
        }else{
            Outils.affichage(Journal.consulterDescription(116),this.getMonInterface());
        }
        
    }
  
    /**********************Fin de méthode d'interation Case****************************/
    // des méthodes d'interaction avec le sac.
    
    public void accederBoire(){
        boolean consoSup=true;
        if(partieActuelle.getJoueurActuel().getPa()>4){consoSup=false;}
        if(!partieActuelle.getJoueurActuel().getDejaBu()){
            if(partieActuelle.getJoueurActuel().getPa()>0){
                consommationDePA=partieActuelle.getJoueurActuel().boire(partieActuelle);
                if(!consommationDePA && partieActuelle.getJoueurActuel().getIndiceCase()==338){
                    Outils.affichage(Journal.consulterDescription(20),this.getMonInterface());
                    monInterface.setCpt(monInterface.getCpt()+3);
                    
                }else{
                    consommationDePA=consoSup;
                    consommerPA();
                    monInterface.setCpt(monInterface.getCpt()-23);
                    menuNiveauUn('S');
                }
            }else{
                Outils.affichage(Journal.consulterDescription(5),this.getMonInterface());            
                monInterface.setCpt(monInterface.getCpt()-23);
                    menuNiveauUn('S');
            }
        }else{
                Outils.affichage(Journal.consulterDescription(26),this.getMonInterface());            
                monInterface.setCpt(monInterface.getCpt()-23);
                    menuNiveauUn('S');
        }
    }
    
    public void accederManger(){
        boolean consoSup=true;
        if(partieActuelle.getJoueurActuel().getPa()>4){consoSup=false;}
        if(!partieActuelle.getJoueurActuel().getDejaMange()){
            if(partieActuelle.getJoueurActuel().getPa()>0){
                consommationDePA=partieActuelle.getJoueurActuel().manger(partieActuelle);
                if(!consommationDePA && partieActuelle.getJoueurActuel().getIndiceCase()==338){
                    Outils.affichage(Journal.consulterDescription(17),this.getMonInterface());
                    monInterface.setCpt(monInterface.getCpt()+2);
                }else{
                    consommationDePA=consoSup;
                    consommerPA();
                    monInterface.setCpt(monInterface.getCpt()-23);
                    menuNiveauUn('S');

                }
            }else{
                Outils.affichage(Journal.consulterDescription(5),this.getMonInterface()); 
                monInterface.setCpt(monInterface.getCpt()-23);
                menuNiveauUn('S');
            }
        }else{
                Outils.affichage(Journal.consulterDescription(27),this.getMonInterface());            
                monInterface.setCpt(monInterface.getCpt()-23);
                menuNiveauUn('S');
        }
    }
    
    public void actionManger(boolean b){
        if(b){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                Item ration=partieActuelle.getMaVille().prendreRation(this.partieActuelle);
                if(ration.getNom().equals(Journal.consulterDescription(51))){
                    partieActuelle.getJoueurActuel().getSac().add(ration);
                }

            }else{
                Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
            }
        }
    }
    
    public void actionBoire(boolean b){
        if(b){
            if(partieActuelle.getJoueurActuel().getSac().size()<10){
                partieActuelle.getJoueurActuel().getSac().add(partieActuelle.getMaVille().remplirGourde(this.partieActuelle));
            }else{
                Outils.affichage(Journal.consulterDescription(8),this.getMonInterface());
            }
        }
    }
    //Permet de vider le sac du joueur une boisson énergissante
    public boolean accederVider(){
        return partieActuelle.getJoueurActuel().viderSac(partieActuelle);
        
    }
    //Permet de boire une boisson énergissante
    public void accederBoireE(){
        consommationDePA=true;
        if(partieActuelle.getJoueurActuel().getPa()>6){consommationDePA=false;}
        monInterface.setChoix(partieActuelle.getJoueurActuel().boireBoisson(this.partieActuelle));
        monInterface.setCpt(monInterface.getCpt()+1);
        
    }   
    
    //fin des méthodes d'interaction avec le sac.
    
    //Permet les intéraction avec le sac(menu et appel des fonctions)
    public void interagirSac(char choix){
        
        switch(choix){
            case 'B':   accederBoire();
                        break;
            case 'M':   accederManger();
                        break;
            case 'V':   monInterface.setB(accederVider());    
                        break;
            case 'E':   accederBoireE();
                        break;
            case 'R':   monInterface.setCpt(monInterface.getCpt()-22);
                        this.retournerMenu();
                        break;
            default:    Outils.affichage(Journal.consulterDescription(36), monInterface);
                        Outils.afficher(4,partieActuelle);
                    }
        
    }
    
    //Permet de finir le tour.
    public void finirTour() {
        partieActuelle.getTempsPartie().incrementerTour(partieActuelle);
    }
    /********Fin des fonctions du menu de niveau 1********/
}

