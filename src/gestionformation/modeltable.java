/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;

/**
 *
 * @author mariem
 */
public class modeltable {
    String nom;
    int codeForamtion;

    public modeltable(String nom, int codeInforamtion) {
        this.nom = nom;
        this.codeForamtion = codeInforamtion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodeForamtion() {
        return codeForamtion;
    }

    public void setCodeForamtion(int codeInforamtion) {
        this.codeForamtion = codeInforamtion;
    }
    
    
    
}
