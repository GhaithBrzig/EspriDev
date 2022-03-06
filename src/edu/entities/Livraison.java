/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

/**
 *
 * @author islemferchichi
 */
  
public class Livraison {
    private int IdLivraison;
    private int IdLivreur;
    //private String TelClient;
    //private String adresse;
    private double FraisdeLivraison;

    public Livraison() {}
        public Livraison(int IdLivraison, double FraisdeLivraison) {
        this.IdLivraison = IdLivraison;
         
        
        this.FraisdeLivraison = FraisdeLivraison;
    }
    

    public Livraison(int IdLivraison,int IdLivreur, double FraisdeLivraison) {
        this.IdLivraison = IdLivraison;
         this.IdLivreur = IdLivreur;
        
        this.FraisdeLivraison = FraisdeLivraison;
    }

    public int getIdLivraison() {
        return IdLivraison;
    }

    public void setIdLivraison(int IdLivraison) {
        this.IdLivraison = IdLivraison;
    }

    public int getIdLivreur() {
        return IdLivreur;
    }

    public void setIdLivreur(int IdLivreur) {
        this.IdLivreur = IdLivreur;
    }

    public double getFraisdeLivraison() {
        return FraisdeLivraison;
    }

    public void setFraisdeLivraison(double FraisdeLivraison) {
        this.FraisdeLivraison = FraisdeLivraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "IdLivraison=" + IdLivraison + ", IdLivreur=" + IdLivreur + ", FraisdeLivraison=" + FraisdeLivraison + '}';
    }
}



    

