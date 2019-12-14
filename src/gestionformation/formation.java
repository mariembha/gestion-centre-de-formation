/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionformation;


public class formation {
     int code,tarif ,nombreh;
     String nom;

    public formation(int code, String nom, int tarif, int nombreh) {
        this.code = code;
        this.tarif = tarif;
        this.nombreh = nombreh;
        this.nom = nom;
    }
     public formation( String nom, int tarif, int nombreh) {
       
        this.tarif = tarif;
        this.nombreh = nombreh;
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public int getTarif() {
        return tarif;
    }

    public int getNombreh() {
        return nombreh;
    }

    public String getNom() {
        return nom;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setNombreh(int nombreh) {
        this.nombreh = nombreh;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    
    
    
}