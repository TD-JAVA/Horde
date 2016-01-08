/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.ArrayList;

/**
 *
 * @author Seb
 */
public class Journal {
    private ArrayList<String> listeDeMorts=new ArrayList<String>(19);
    private Construction[] tabConstruction=new Construction[7];
    private ArrayList<String> carte = new ArrayList<String>(625);
    
    
    public Construction[] getTabConstruction(){
        return tabConstruction;
    }
    
    public Construction getIndexConstruction(int a){
        return tabConstruction[a];
    }
    
    public Journal(){
        int tab []={20,5};    
        tabConstruction[0]= new Construction("Mur d'enceinte",tab,10,20);
        int tab1 []={20,30};
        tabConstruction[1]= new Construction("Fils barbelés",tab1,20,30);
        int tab2 []={50,25};
        tabConstruction[2]= new Construction("Fosses à Zombies",tab2,30,50);
        int tab3 []={10,50};
        tabConstruction[3]= new Construction("Mines autour de la ville",tab3,30,50);
        int tab4 []={50,50};
        tabConstruction[4]= new Construction("Portes blindées",tab4,40,100);
        int tab5 []={75,75};
        tabConstruction[5]= new Construction("Miradors avec mitrailleuses",tab5,50,200);
        int tab6 []={100,200};
        tabConstruction[6]= new Construction("Abris anti-atomique",tab6,60,500);
        for(int i =0;i<625;i++){
            carte.add(i,"");
        }
    }
// Permet de voir la liste et les détails des constructions
    public String consulterConstruction() {
        String tabNom="";
        for(int i=0;i<tabConstruction.length;i++){
            tabNom+=tabConstruction[i].getNom()+'\n';
        }
        return tabNom;
    }

// Permer de voir la liste des objets et leur détails dans l'entrepot
    public int[] consulterEntrepot(Ville ville) {
         return ville.getEntrepot();
    }

// Permet d'obtenir des infos sur le jeu (jour, tour)
    public int ConsulterTemps(Temps temps) {
        return temps.getNbJour()& temps.getNumTour();
    }

//Permet d'afficher un résumé du jeu
    public String toString(Temps temps , Ville ville,Jeu partie,Joueur joueur, char choix) {
        String string=""; //="Résumé du Jeu. Le nombre de tours est de "+temps.getNumTour()+" tour(s), le nombre de jours est de "+temps.getNbJour()+" jour(s), les items dans l'entrepot sont :"+ ville.consulterEntrepot()+" ,la liste des batiments sont : \n"+this.consulterConstruction()+"";
        if(joueur.getIndiceCase()!=338){
            if(choix=='C'||choix=='I'){
                choix='E';
            }
        }
        
        switch (choix){
            case 'J':
                break;
            case 'S':
                string = "Le nombre de tours est de "+temps.getNumTour()+" tour(s), le nombre de jours est de "+temps.getNbJour()+" jour(s)"
                        + "\nLa position du joueur est la suivante:"+this.afficherPosition(partie, joueur)+"\n"+afficherDescriptionJoueur(joueur);
                break;
            case 'K':
                break;
            case 'I':
                break;
            case 'C':
                break;
            case 'N':   string=afficherContenuSac(joueur);
                        break;
            case 'M':   this.miseAJourCarte(joueur);
                        break; 
            case 'V':   this.voirCarte();
                        break; 
            case 'R':
                break;
        }
        return string;
    }
    
    public String afficherPosition(Jeu partie,Joueur ceJoueur){
        if(ceJoueur.getAbsysseActuelle()== partie.getGrille().getxVille() && ceJoueur.getOrdonneeActuelle()==partie.getGrille().getyVille()){
            return ""+ceJoueur.getNom()+" est dans la ville";
        }else{
            return ""+ceJoueur.getNom()+" est sur la case ("+ceJoueur.getAbsysseActuelle()+";"+ceJoueur.getOrdonneeActuelle()+")";
        }
    }
    public static String afficherDescriptionJoueur(Joueur ceJoueur){
        return ""+ceJoueur.getNom()+" a "+ceJoueur.getPa()+" point(s) d'action et "+ ceJoueur.getPdv()+" point de vie";
    }
    public static String afficherContenuSac(Joueur ceJoueur){
        String str="";
        for(int index=0;index<ceJoueur.getSac().size();index++){str+=ceJoueur.getSac().get(index).getNom()+";\n";}
        return str;
    }
    public void ajouterListeDeMorts(String str){
        listeDeMorts.add(str);
    }
    public String afficherListeDeMort(){
        String str="\n";
        for(int index=0;index<listeDeMorts.size();index++){str+=listeDeMorts.get(index)+";\n";}
        return str;
    }
    public static String consulterDescription(int choix){
        String description="";
        
        switch (choix){
            case 0: description="La gourde permet de récupérer 6 points d'action.\n Elle n'est pas réutilisable. Elle occupe une place de la sac.\n On ne peut boire qu'une fois par jour.";
                    break;
            case 1: description="La ration permet de récupérer 6 points d'action.\n Elle n'est pas réutilisable. Elle occupe une place de la sac.\n On ne peut manger qu'une fois par jour.\nAttention au stock, il n'y a que 50 rations dans l'entrepôt.";
                    break;
        }
        
        return description;
    }
    
    public void miseAJourCarte(Joueur joueur){
    
        //System.out.println(joueur.getIndiceCase());
       int index = 0;
     
       while(!joueur.getCarteJoueur().isEmpty()){
           String indice[] = joueur.getCarteJoueur().get(0).split(":");
           index = Integer.parseInt(indice[0]);
           if (!carte.get(joueur.getIndiceCase()).isEmpty()){
                carte.remove(joueur.getIndiceCase());
           }
            carte.add(index,indice[1]);
            joueur.getCarteJoueur().remove(0);
            
        }
       System.out.println("La carte a été mise à jour");
    }
    
    public void voirCarte(){
        carte.add(1,"sss");
        carte.add(621,"dds");
        System.out.println("test :"+carte.get(1));
         for(int j=1;j<26;j++){
            for(int i=1;i<26;i++){
                if(!carte.get(i*j).equals("")){
                    System.out.printf("|"+carte.get(i*j)+"|");
                }
                else{
                    System.out.printf("| |");
                }
            }
            System.out.println("");
        }
    }
}
