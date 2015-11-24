/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Seb
 */
public class Journal {
    
    private Construction[] tabConstruction=new Construction[7];
    
    public Construction[] getTabConstruction(){
        return tabConstruction;
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
    public int[] consulterEntrepot(Ville Ville) {
         return Ville.getEntrepot();
    }

// Permet d'obtenir des infos sur le jeu (jour, tour)
    public int ConsulterTemps(Temps Temps) {
        return Temps.getNbJour()& Temps.getNumTour();
   

    }

//Permet d'afficher un résumé du jeu
    public String toString(Temps Temps , Ville Ville) {
        String string ="Résumé du Jeu. Le nombre de tours est de "+Temps.getNumTour()+" tour(s), le nombre de jours est de "+Temps.getNbJour()+" jour(s), les items dans l'entrepot sont :"+Ville.getEntrepot()+" ,la liste des batiments sont : \n"+this.consulterConstruction()+"";

        return string;
    }
    
    public void afficherPosition(Jeu partie,Joueur ceJoueur){
        if(ceJoueur.getAbsysseActuelle()== partie.getGrille().getxVille() & ceJoueur.getOrdonneeActuelle()==partie.getGrille().getyVille()){
            System.out.println(ceJoueur.getNom()+" est dans la ville");
        }
    }
}
