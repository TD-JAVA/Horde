/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Seb
 */
public class Journal {
    private ArrayList<String> listeDeMorts=new ArrayList<String>(19);
    private ArrayList<Construction> tabConstruction=new ArrayList<Construction>(7);
    private ArrayList<String> carte = new ArrayList<String>(625);
    
    
    public ArrayList<Construction> getTabConstruction(){
        return tabConstruction;
    }
    
    public Construction getConstruction(int a){
        return tabConstruction.get(a);
    }
    
    public Construction getConstruction(String a){
     int i=0;
        while(!tabConstruction.get(i).getNom().equals(a)){
            i++;
        }
        if(tabConstruction.get(i).getNom().equals(a)){return tabConstruction.get(i);}else{ return null;}
        
     }
    public Journal(){
        ArrayList tab=new ArrayList(2);
        tab.add(0, 10);tab.add(1,20);
        tabConstruction.add(0,new Construction("Mur d'enceinte",tab,10,20));
        tab.add(0, 10);tab.add(1,20);
        tabConstruction.add(1,new Construction("Fils barbelés",tab,20,30));
        tab.add(0, 50);tab.add(1,25);
        tabConstruction.add(2,new Construction("Fosses à Zombies",tab,30,50));
        tab.add(0, 10);tab.add(1,50);
        tabConstruction.add(3,new Construction("Mines autour de la ville",tab,30,50));
        tab.add(0, 50);tab.add(1,50);
        tabConstruction.add(4,new Construction("Portes blindées",tab,40,100));
        tab.add(0, 75);tab.add(1,75);
        tabConstruction.add(5,new Construction("Miradors avec mitrailleuses",tab,50,200));
        tab.add(0, 100);tab.add(1,200);
        tabConstruction.add(6,new Construction("Abris anti-atomique",tab,60,500));
        for(int i =0;i<625;i++){
            carte.add(i,"");
        }

    }
// Permet de voir la liste et les détails des constructions
    public String consulterConstruction() {
        String tabNom="\n   Nom de la construction - Ressources nécessaires - PA - Réssistance\n";
        for(int i=0;i<tabConstruction.size();i++){
            tabNom+=i+" :"+tabConstruction.get(i).getNom()+" - "+tabConstruction.get(i).getRessources(0)+";"+tabConstruction.get(i).getRessources(1)+" - "+tabConstruction.get(i).getConso_pa()+" - +"+tabConstruction.get(i).getResistance()+"\n";
        }
        return tabNom;
    }

// Permer de voir la liste des objets et leur détails dans l'entrepot
    public Item[] consulterEntrepot(Ville ville) {
         return ville.getEntrepot();
    }

// Permet d'obtenir des infos sur le jeu (jour, tour)
    public int ConsulterTemps(Temps temps) {
        return temps.getNbJour()& temps.getNumTour();
    }

//Permet d'afficher un résumé du jeu
    public String toString(Jeu partie, char choix) {
        Temps temps=partie.getTempsPartie();
        Joueur joueur=partie.getJoueurActuel();
        Ville ville=partie.getMaVille();
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
            case 'I':   string=ville.consulterEntrepot();
                        break;
            case 'C':   string=consulterConstruction();
                        break;
            case 'N':   string=afficherContenuSac(joueur);
                        break;
            case 'M':   this.miseAJourCarte(joueur);
                        break; 
            case 'V':   this.voirCarte();
                        break; 
            case 'R':   partie.getMenuPartie().retournerMenu(1);
                        break;
            default:    
                        break;
        }
        return string;
    }
    
    public String afficherPosition(Jeu partie,Joueur ceJoueur){
        if(ceJoueur.getAbsysseActuelle()== partie.getGrille().getxVille() && ceJoueur.getOrdonneeActuelle()==partie.getGrille().getyVille()){
            return ""+ceJoueur.getNom()+" est dans la ville";
        }else{
            return ""+ceJoueur.getNom()+" est sur la case ("+ceJoueur.getAbsysseActuelle()+";"+ceJoueur.getOrdonneeActuelle()+") et la ville se trouve en (13;-13).";
        }
    }
    public static String afficherDescriptionJoueur(Joueur ceJoueur){
        return ""+ceJoueur.getNom()+" a "+ceJoueur.getPa()+" point(s) d'action et "+ ceJoueur.getPdv()+" points de vie";
    }
    public static String afficherContenuSac(Joueur ceJoueur){
        String str="\n";
        if(!ceJoueur.getSac().isEmpty()){
            for(int index=0;index<ceJoueur.getSac().size();index++){str+=index+" "+ceJoueur.getSac().get(index).getNom()+";\n";}
        }else{
            str+="Le sac ne contient rien.";
        }
        
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
    public static String afficherMort(Jeu partie, boolean nuit,boolean pasDrogue){
        String str="";
        if(!nuit){
            if(pasDrogue){
                str= "\n"+partie.getJoueurActuel().getNom()+" est décédé(e) le "+partie.getTempsPartie().getNumTour()+" tour(s) du jour"+partie.getTempsPartie().getNbJour()+" par une attaque de zombies";
            }else{
                str= "\n"+partie.getJoueurActuel().getNom()+" est décédé(e) le "+partie.getTempsPartie().getNumTour()+" tour(s) du jour"+partie.getTempsPartie().getNbJour()+" à cause de votre dépendance.";
            }
            return str;
        }else{
            str= "\n"+partie.getJoueurActuel().getNom()+" est décédé(e) dans la nuit du jour"+partie.getTempsPartie().getNbJour()+" par une attaque de zombies";
            return str;
        }
    }
    public static String consulterDescription(int choix){
        String description="";
        
        switch (choix){
            case 0: description="La gourde permet de récupérer 6 points d'action.\n Elle n'est pas réutilisable. Elle occupe une place de la sac.\n On ne peut boire qu'une fois par jour.";
                    break;
            case 1: description="La ration permet de récupérer 6 points d'action.\n Elle n'est pas réutilisable. Elle occupe une place de la sac.\n On ne peut manger qu'une fois par jour.\nAttention au stock, il n'y a que 50 rations dans l'entrepôt.";
                    break;
            case 2: description="Les planches sont des items. Elles peuvent être trouvée en les cases.\nElles occupent une place dans le sac par item.\nElles servent à construire les défenses de la ville.\nEn tout, il y a en 1000 sur la carte.";
                    break;
            case 3: description="Les plaques de métal sont des items. Elles peuvent être trouvée en les cases.\nElles occupent une place dans le sac par item.\nElles servent à construire les défenses de la ville\nEn tout, il y en a 500 sur la carte.";
                    break;
            case 4: description="Les boissons énergissantes permettent de récupérer 4 points d'action.\nElles ne sont pas réutilisable mais sont cummulables.\nLes boissons énergisantes se trouvent en fouillant les cases.\nElles occupent une place dans le sac par item.\nUne fois qu’on a consommé une boisson énergisante, il faut en consommer une au moins une fois tous les 3 tours,\nsinon on perd 5 points de vie par tour jusqu’à consommation d’une autre boisson énergisante.";
                    break;
            case 5: description="Vous ne possèdez pas assez de point d'action pour cette action";
                    break;
            case 6: description="\nEntrez une lettre correspond au menu";
                    break;
            case 7: description="Tant qu'il y aura des zombies, vous ne pourrez pas vous déplacer. Alors dégommez les!";
                    break;
            case 8: description="Il n'y a plus de place dans votre sac";
                    break;
            case 9: description="Jeu déjà démarré, voulez-vous redémarrer la partie ?(O/N)";
                    break;
            case 10:description="Lire le journal";
                    break;
            case 11:description="La porte est fermée, vous ne pouvez pas entrer.";
                    break;
            case 12:description="La porte est fermée, vous ne pouvez pas sortir.";
                    break;
            case 13:description="Souhaitez-vous construire un nouveau batiment ?(O/N)";
                    break;
            case 14:description="Quelle construction souhaitez vous construire ?";
                    break;
            case 15:description="Souhaitez vous prendre un objet ?(O/N)";
                    break;
            case 16:description="Quel objet souhaitez vous prendre ?";
                    break;
            case 17:description="Voulez vous prendre une ration ?(O/N)";    
                    break;
            case 18:description="Voulez vous prendre une boisson énergissante ?(O/N)";
                    break;
            case 19:description="Cet objet ne peut être mis dans le sac.";
                    break;
            case 20:description="\"Voulez vous remplir une gourde ?(O/N)\"";
                    break;
            case 21:description="Votre total défense est de :";
                    break;
            case 22:description="\nIl y a ";
                    break;
            case 23:description=" zombies sur cette case.";
                    break;
            case 24:description="\nSouhaitez vous les attaquer ?(O/N)";
                    break;
            case 25:description="\nIl reste ";
                    break;
            case 26:description="Vous avez déjà bu ce jour";
                    break;
            case 27:description="Vous avez déjà mangé ce jour";
                    break;
            case 28:description="De quoi souhaitez vous vous séparer?";
                    break;
            case 29:description="Souhaitez vous vraiment jetter l'objet ";
                    break;
            case 30:description=" ?(O/N)";
                    break;
            case 31:description="L'objet ";
                    break;
            case 32:description=" est mis dans l'entrepot";
                    break;
            case 33:description=" est jeté";
                    break;
            case 34:description="Le sac ne contient rien.";
                    break;
            case 35:description="\nDemarrer(D)\nQuitter(Q)\nMenu(S)";
                    break;
            case 36:description="Quel est votre choix ?\n";
                    break;
            case 37:description="\nLire le journal(J)\nSe déplacer(D)\nIntéragir avec la case(I)\nInteragir avec le sac(S)\nFinir le tour(F)\nRetour(R)\n";
                    break;
            case 38:description="\nConstruire(C)\nParticiper aux chantiers(P)\nConsulter les défenses(D)\nConsulter l'entrepot(E)\nInteragir avec la porte(I)\nRemplir une gourde(B)\nPrendre une ration(M)\nRetour(R)\n";
                    break;
            case 39:description="\nAttaquer les zombies(A)\nRécolter les objets(O)\nRetour(R)";
                    break;
            case 40:description="\nFouiller la case(F)\nRetour(R)";
                    break;
            case 41:description="\n0. Résumé du jeu(J)\n1. Situation(S)\n2. Règle du jeu(K)\n3. Item dans l'entrepôt(I)\n4. Liste des constructions(C)\n5.Afficher le contenu du sac(N)\n6. Mettre à jour la carte(M)\n7. Voir la carte(V)\nRetour (R)";
                    break;
            case 42:description="\n0. Résumé du jeu(J)\n1. Situation(S)\n2. Règle du jeu(K)\n3. Afficher le contenu du sac(N)\n4. Mettre à jour la carte(M)\n5. Voir la carte(V)\nRetour (R)";
                    break;
            case 43:description="\nBoire (B)\nManger (M)\nVider le sac(V)\nRetour(R)\n";
                    break;
            case 44:description="Chaque déplacement coute un point d'action dans quelle direction souhaitez aller ?";
                    break;
            case 45:description="En haut(Z)\nÀ gauche(Q)\nÀ droite(D)\nEn Bas(S)\nRetour(R)\n";
                    break;
            case 46:description="La saisie est erronée veuillez réessayer. \n Entrez une réponse (O/N):";
                    break;
            case 47:description="La saisie est erronée veuillez réessayer. \n Entrez votre choix:";
                    break;
            case 48:description="Saississez un nombre entre 1 et 20:";
                    break;
            case 49:description="Saississez un nombre entre 0 et ";
                    break;
            case 50:description="Erreur";
                    break;
            case 51:description="Ration";
                    break;
            case 52:description="Gourde";
                    break;
            case 53:description="Boisson énergissante";
                    break;
            case 54:description="Planche";
                    break;
            case 55:description="Plaque de métal";
                    break;
            case 56:description="**************************************************\n**************************************************\n******************* H O R D E ********************\n**************************************************\n**************************************************\nBienvenue dans Horde, une reproduction simplifié du Jeu de Twinoid.\nCe programme a été réalisé dans un cadre scolaire.\nIl n'est pas commercialisable. Les créateurs de ce programme et les intervenants de l'université de Lorraine ne pourront être tenu responsable des effets de ce logiciel et de ce logiciel.\nProgramme réalisé par Sébastien Brogniart, Gabriel Giroud et Valère Richier alors tous trois étudiants en année de Licence MIASHS.\n";
                    break;
            case 57:description="Combien y a t il de joueur pour cette partie ?(entre 1 et 20 joueurs)";
                    break;
            case 58:description="Quel est le nom du joueur ";
                    break;
            case 59:description="Initialisation terminée, accèdez au journal par le sous menu pour connaitre les règles du jeu";
                    break;
            case 60:description="\nVous êtes mort ! Fin de partie pour vous.";
                    break;
            case 61:description=" a gagné la partie.";
                    break;
            case 62:description="\nLa partie est terminée. Merci d'avoir joué!\nSouhaitez vous rejouer ?(O/N)";
                    break;
            case 63:description="Vous n'avez pas de gourde !";
                    break;
            case 64:description="Vous avez récupéré 6 points d'actions !";
                    break;
            case 65:description="Vous n'avez pas de ration !";
                    break;
            case 66:description="\nSouhaitez vous boire une boisson énergissante ?(O/N)";
                    break;
            case 67:description="Vous récupérez 4 points d'action.";
                    break;
            case 68:description="\nVous ne possèdez pas de boisson énergissante.";
                    break;
            case 69:description="Position sexuelle";
                    break;
            case 70:description="La case n'est pas fouillée, souhaitez vous le faire ?($1PA)(O/N)";
                    break;
            case 71:description="Chaque construction coûte un point d'action.";
                    break;
            case 72:description="Ressources ou points d'action insuffisants";
                    break;
            case 73:description="Ce batiment est déjà fait";
                    break;
            case 74:description="Il reste ";
                    break;
            case 75:description=" points d'action à dépenser pour terminer ce batiment.";
                    break;    
            case 76:description="Souhaitez vous investir ?(O/N)";
                    break; 
            case 77:description="Combien de points d'action souhaitez vous utiliser ?";
                    break;
            case 78:description="La porte est ouverte !";
                    break;
            case 79:description="Souhaitez-vous la fermer ?(O/N)($1PA)";
                    break;
            case 80:description="La porte est fermée !";
                    break;
            case 81:description="Souhaitez-vous l'ouvrir ?(O/N)($1PA)";
                    break;
            case 82:description="\nNom de la construction - Réssistance\n";
                    break; 
            case 83:description="\nNom de la construction - PA - Réssistance\n";
                    break; 
            case 84:description="\nIl n' y a plus de ration.";
                    break; 
            case 85:description="\nIl n' y a plus de boisson énergissante.";
                    break; 
            //case 86:description=;
            //        break; 
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
