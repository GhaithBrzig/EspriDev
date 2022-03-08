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

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
    private int IdLivraison;
    private int IdLivreur;
    //private String TelClient;
    //private String adresse;
    private double FraisdeLivraison;
    private int id_commande;
    Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Livraison() {}
        public Livraison(int IdLivraison, double FraisdeLivraison) {
        this.IdLivraison = IdLivraison;
         
        
        this.FraisdeLivraison = FraisdeLivraison;
    }
    

    public Livraison(int IdLivraison,int IdLivreur, double FraisdeLivraison , int id) {
        this.IdLivraison = IdLivraison;
         this.IdLivreur = IdLivreur;
        this.id_commande = id;
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



    

