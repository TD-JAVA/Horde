/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hordefinal;


import java.util.Scanner;


/*
La classe outils est une classe abstraite car il n'y a pas d'instantiation d'objet.
Elle contient tous les outils nécessaires au bon fonctionnement du programme.
Convertion, affichage...
*/

/**
 *
 * @author Sébastien,Gabriel,Valère
 */
public abstract class Outils  {
    
    /***************Outils d'Outils.affichage dans la console******************/
     
    /*
     * 
     * 
     * @return  
     * @param niveau est le menu à afficher. 
     */
    
   
    //Permet d'ajouter du texte dans le textArea ou dans un label lors d'un cas particulier.
    public static void affichage(String str,FenetrePrincipale uneInterface){
        if(uneInterface.getjPanel2().isVisible()){
            uneInterface.getjLabel1().setText(str); 
        }else{
            uneInterface.getjTextArea1().append(str);
            uneInterface.pack();
        }
    }
    //permet d'afficher les menus jeu.
    public static void afficher(int niveau, Jeu partieActuelle){ 

        switch (niveau) {
            case 0:     
                        Outils.affichage(Journal.consulterDescription(35),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        break;
                        
                        
            case 1:     Outils.affichage(Journal.toString(partieActuelle,'S'),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(37),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        break;
                
            case 2:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
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
            case 3:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            Outils.affichage(Journal.consulterDescription(41),partieActuelle.getMonInterface());
                        }else{
                            Outils.affichage(Journal.consulterDescription(42),partieActuelle.getMonInterface());
                        }
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        
                        break;
            case 4:     Outils.affichage(Journal.consulterDescription(43),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        
                        break;
            case 5:     Outils.affichage(Journal.consulterDescription(44),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(45),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                        break;
            case 6:     if(partieActuelle.getMaVille().getBatimentEnCours().isEmpty()){
                            Outils.affichage(partieActuelle.getMaVille().afficherConstructionEnCours(),partieActuelle.getMonInterface());
                            Outils.affichage(Journal.consulterDescription(36),partieActuelle.getMonInterface());
                            partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()+2);
                        }else{
                            Outils.affichage(Journal.consulterDescription(115),partieActuelle.getMonInterface());
                            partieActuelle.getMenuPartie().menuNiveauUn('I');
                        }
                        
                        break;
            case 7:     Outils.affichage(partieActuelle.getMaVille().consulterEntrepot(),partieActuelle.getMonInterface());
                        Outils.affichage(Journal.consulterDescription(15),partieActuelle.getMonInterface());
                        partieActuelle.getMonInterface().setCpt(partieActuelle.getMonInterface().getCpt()+3);
                        break;
        }
        
    }
    
    /*
     * @param lettre est le caractère à convertir en majuscule est le choix de menu donnée par l'utilisateur.
     */
    public static char conversionCaractere(char lettre){
    
        return Character.toUpperCase(lettre);
    }
    
    //Vérification de la saisie de l'utilisateur. Soit la vérification porte un caractère soit sur une réponse convertible en booléen.
    //Cette fonction servait pour la version du programme sans interface graphique.
    public static char verification(String str, int choix, Jeu partieActuelle){
        if(!str.isEmpty()){
            str = str.toUpperCase();
        }
        Scanner sc=new Scanner(System.in);
        char[] lettres = str.toCharArray();
        if(choix==1) {
           
                    while((str!="Y")||(str!="O")||(str!="YES")||(str!="OUI")||(str!="N")||(str!="NON")||(str!="NO")){
                        Outils.affichage(Journal.consulterDescription(46),partieActuelle.getMonInterface());
                        Outils.verification(sc.next(),1,partieActuelle);
                    }
                    return lettres[0];
                    
        }else{
            
                  while(lettres[0]<'A' && lettres[0]>'Z'){
                      Outils.affichage(Journal.consulterDescription(47),partieActuelle.getMonInterface());
                      Outils.verification(sc.next(),0,partieActuelle);
                  }
                  return lettres[0];
                  
        }     
    }
    
    //
    //Vérification de la saisie de l'utilisateur. Soit la vérification porte un caractère.
    public static char verifier(char choix,Jeu partieActuelle){
    if(partieActuelle.getJoueurActuel().getIndiceCase()!=338){
                    if(choix=='C'||choix=='E'||choix=='I'||choix=='B'||choix=='D'||choix=='P'){
                        choix='K';
                    }
                    if(!partieActuelle.getMenuPartie().getTabGrille().get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
                        if(choix=='M'||choix=='A'||choix=='O'){
                            choix='K';
                        }
                    }
                }
                return choix;
    }
    
    
    //Convertie une string en int
    public static int conversionInt(String str,Jeu partieActuelle){
        Scanner sc=new Scanner(System.in);
        char[] lettres=str.toCharArray();
        int longueur=str.length();
        int num=0;
        
        if((longueur<2)){
            if((int)(lettres[0])>57 ||(int)(lettres[0])<49){
                Outils.affichage(Journal.consulterDescription(48),partieActuelle.getMonInterface());
            }else{
                num=Integer.parseInt(str);
            }
        }else{
                if((int)(lettres[0])>50 ||(int)(lettres[0])<49 && (int)(lettres[1])>57 ||(int)(lettres[1])<48){
                    Outils.affichage(Journal.consulterDescription(48),partieActuelle.getMonInterface());
                }else{
                    num = Integer.parseInt(str);
                    if(num>20){
                    Outils.affichage(Journal.consulterDescription(48),partieActuelle.getMonInterface());    
                    num=0;
                    }
                }
        }
        return num;
    }
    
    //Convertie une string en boolean
    public static boolean conversionBoolean(String str,Jeu partieActuelle){
        char[] lettres =  str.toCharArray();
        if(conversionCaractere(lettres[0])=='Y'||conversionCaractere(lettres[0])=='O'){
            return true;
        }else{ 
            if(conversionCaractere(lettres[0])=='N'){
                return false;
            }else{
                Outils.affichage(Journal.consulterDescription(50),partieActuelle.getMonInterface());
                return false;
            }
        }
    }
    
    //Convertie une str en char
    public static char conversionChar(String str){
        char[] lettres =  str.toCharArray();
        return lettres[0];
    }
    
    
    //Convertie une str en int dans un contexte donné
    public static int donnerReponseChiffre(int max,Jeu partieActuelle,String str){
        
        int longueur=str.length(),num;
        char[] lettres=str.toCharArray();
        if(longueur!=1 ||((int)(lettres[0])>57 ||(int)(lettres[0])<48)){
            if(max==0){
                Outils.affichage(Journal.consulterDescription(117),partieActuelle.getMonInterface());
            }else{
                Outils.affichage(Journal.consulterDescription(49)+max,partieActuelle.getMonInterface());
            }
            
            return -1;
        }else{
            
            num=Integer.parseInt(str);
            if(num<=max&&num>=0){
                return num;
            }else{
                return -1;
            }
            
        }
        
        
    }
    
    /**********************Fin des outils************************/
}
