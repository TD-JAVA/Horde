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
public class Carte {
    private Case[] tabCase;
    private int xVille;
    private int yVille;
    private Ville maVille;
    
    public int getxVille() {return xVille;}
    
    public int getyVille() {return yVille;}
    public void setxyVille(int x,int y) {this.xVille = x;this.yVille = yVille;}
    
    public Case[] getTabCase() {return tabCase;}
    public void setTabCase(int i,Case cetteCase) {this.tabCase[i] = cetteCase;}
    
    
    public Carte(Jeu partie){
        tabCase=new Case[625];
        int i=0,j=0,k;
        while(i<625){
            for(k=0;k<25;k++){
                tabCase[i]= new Case(j,k);
                i+=1;
            }
            j-=1;
        }
        
    }
    
}
