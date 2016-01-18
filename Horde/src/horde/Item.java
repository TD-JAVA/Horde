/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horde;

/**
 *
 * @author Gabriel, Sébastien & Valère */

/*
La classe Item permettre de créer tous les Items présents dans le jeu :  
Les différents Items présents dans le jeu sont : les boissons énergisantes, les rations, les gourdes, ou encore les planches, les plaques de métal.
*/

public class Item {

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public Item(String nom, int quantite, String description) {
        this.nom = nom;
        this.quantite = quantite;
        this.description = description;
        
    }
    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    private String nom; // Indique le nom de l'objet
    private int quantite; // Indique la quantité disponible de l'objet
    private String description; // Renseigne sur la description de l'objet
    
}
