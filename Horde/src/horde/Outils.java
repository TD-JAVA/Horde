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
public class Outils {
    
    /***************Outils d'Outils.affichage dans la console******************/
     
    /*
     * 
     * 
     * @return  
     * @param niveau est le menu à afficher. 
     */
      
    public static void affichage(String str){
        System.out.println(str);
    }
    
    public static char afficher(int niveau, Jeu partieActuelle){
        Scanner sc=new Scanner(System.in);
        Outils.affichage(partieActuelle.getMonJournal().toString(partieActuelle, 'S'));
        switch (niveau) {
            case 0:     Outils.affichage(Journal.consulterDescription(35));
                        Outils.affichage(Journal.consulterDescription(36));
                        
                        return Outils.verification(sc.next(),0);
                        
            case 1:     Outils.affichage(Journal.consulterDescription(37));
                        Outils.affichage(Journal.consulterDescription(36));
                        
                        return Outils.verification(sc.next(),0);
                
            case 2:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            Outils.affichage(Journal.consulterDescription(38));
                        }else{
                            if(partieActuelle.getMenuPartie().getTabGrille().get(partieActuelle.getJoueurActuel().getIndiceCase()).getFouillee()){
                                Outils.affichage(Journal.consulterDescription(39));
                            }else{
                                Outils.affichage(Journal.consulterDescription(40));
                            }
                        }
                        Outils.affichage(Journal.consulterDescription(36));
                        return Outils.verification(sc.next(),0);
            case 3:     if(partieActuelle.getJoueurActuel().getAbsysseActuelle()== partieActuelle.getGrille().getxVille() && partieActuelle.getJoueurActuel().getOrdonneeActuelle()==partieActuelle.getGrille().getyVille()){
                            Outils.affichage(Journal.consulterDescription(41));
                        }else{
                            Outils.affichage(Journal.consulterDescription(42));
                        }
                        Outils.affichage(Journal.consulterDescription(36));
                        return Outils.verification(sc.next(),0);
            case 4:     Outils.affichage(Journal.consulterDescription(43));
                        Outils.affichage(Journal.consulterDescription(36));
                        return Outils.verification(sc.next(),0);
            case 5:     Outils.affichage(Journal.consulterDescription(44));
                        Outils.affichage(Journal.consulterDescription(45));
                        Outils.affichage(Journal.consulterDescription(36));
                        return Outils.verification(sc.next(),0);
                        
        }
        return 'e';
    }
    
    /*
     * @param lettre est le caractère à convertir en majuscule est le choix de menu donnée par l'utilisateur.
     */
    public static char conversionCaractere(char lettre){
    
        return Character.toUpperCase(lettre);
    }
    
    public static char verification(String str, int choix){
        str = str.toUpperCase();
        Scanner sc=new Scanner(System.in);
        char[] lettres = str.toCharArray();
        if(choix==1) {
           
                    while((str!="Y")||(str!="O")||(str!="YES")||(str!="OUI")||(str!="N")||(str!="NON")||(str!="NO")){
                        Outils.affichage(Journal.consulterDescription(46));
                        Outils.verification(sc.next(),1);
                    }
                    return lettres[0];
                    
        }else{
            
                  while(lettres[0]<'A' && lettres[0]>'Z'){
                      Outils.affichage(Journal.consulterDescription(47));
                      Outils.verification(sc.next(),0);
                  }
                  return lettres[0];
                  
        }     
    }
    
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
        
    public static int conversionInt(String str){
        Scanner sc=new Scanner(System.in);
        char[] lettres=str.toCharArray();
        int longueur=str.length();
        int num=0;
        
        if((longueur<2)){
            if((int)(lettres[0])>57 ||(int)(lettres[0])<49){
                Outils.affichage(Journal.consulterDescription(48));
                
                num=conversionInt(sc.next());
            }else{
                num=Integer.parseInt(str);
            }
        }else{
                if((int)(lettres[0])>50 ||(int)(lettres[0])<49 && (int)(lettres[1])>57 ||(int)(lettres[1])<48){
                    Outils.affichage(Journal.consulterDescription(48));
                    
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
                Outils.affichage(Journal.consulterDescription(50));
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
            Outils.affichage(Journal.consulterDescription(49)+max);
            str=sc.next();
            longueur=str.length();
            lettres=str.toCharArray();
        }while(longueur!=1 ||((int)(lettres[0])>57 ||(int)(lettres[0])<48));
        num=Integer.parseInt(str);
        return num;
    }
    
    /**********************Fin des outils************************/
}
