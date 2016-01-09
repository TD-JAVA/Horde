/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author oneiroi
 */
public class Carte {
    private ArrayList<Case> tabCase=new ArrayList<Case>();
    private int xVille;
    private int yVille;
    private Ville maVille;
    private Item[] tabItem= new Item[3];
    
    public int getxVille() {return xVille;}
    
    public int getyVille() {return yVille;}
    public void setxyVille(int x,int y) {this.xVille = x;this.yVille = y;}
    
    public ArrayList<Case> getTabCase() {return tabCase;}
    public void setTabCase(int i,Case cetteCase) {this.tabCase.add(cetteCase);}
    
    
    public Carte(Jeu partie){
        tabItem[0]=new Item("Planche",1000,Journal.consulterDescription(2));
        tabItem[1]=new Item("Plaques de métal",500,Journal.consulterDescription(3));
        tabItem[2]=new Item("Boisson énergissante",100,Journal.consulterDescription(4));
        
        int i=0,j=0,k;
        while(i<625){
            for(k=0;k<25;k++){
                //if(i==338){System.out.println(j+";"+k+"  "+i);}
                tabCase.add(new Case(k,j));
                i+=1;
            }
            j-=1;
            
        }
        dispertionDesItems();
        
    }
    
    public void dispertionDesItems(){
        int r=-1;
        int n=1;
        int u=312;
        int i;
        Random ra=new Random();
        while(u!=624){
            i=0;
            while(i<n&&u!=624){
                u=u+((-1)*((int)Math.pow(r,n)));
                if(u!=338){
                    ajouterItem(0,u,5);
                    ajouterItem(1,u,2);
                    ajouterItem(2,u,2);
                }
                u=u+((-25)*((int)Math.pow(r,n)));
                if(u!=338){
                    ajouterItem(0,u,5);
                    ajouterItem(1,u,2);
                    ajouterItem(2,u,2);
                }
                i++;
            }
            n++;
        }
        for(i=0;i<3;i++){
            if(tabItem[i].getQuantite()!=0){
                while(tabItem[i].getQuantite()!=0){
                    ajouterItem(i,ra.nextInt(624),2);
                }
            }
        }
        
        
    }
    
    public void ajouterItem(int i, int j, int k){
        Random ra=new Random();
        if(tabItem[i].getQuantite()!=0){
            if(i==2){
                if(ra.nextInt(100)>90){
                    int u=ra.nextInt(k);
                    if(j>tabItem[i].getQuantite()){j=tabItem[i].getQuantite();}
                        this.getTabCase().get(j).getItem().get(i).setQuantite(u);}
                        tabItem[i].setQuantite(tabItem[i].getQuantite()-this.getTabCase().get(j).getItem().get(i).getQuantite());
                    }else{
                        int u=ra.nextInt(k);
                        if(u>tabItem[i].getQuantite()){u=tabItem[i].getQuantite();}
                        this.getTabCase().get(j).getItem().get(i).setQuantite(u);
                        tabItem[i].setQuantite(tabItem[i].getQuantite()-this.getTabCase().get(j).getItem().get(i).getQuantite());
            }
        }
    }
}
    

