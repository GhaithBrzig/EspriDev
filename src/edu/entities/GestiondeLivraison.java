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
public class GestiondeLivraison {
    //private int IdCommande;
    private int IdLivraison;
    private int IdLivreur;
    private String NomLivreur;
    //private String TelClient;
    //private String adresse;
    private double FraisdeLivraison;

    public GestiondeLivraison() {
    }

    public GestiondeLivraison(int IdLivraison,int IdLivreur,String NomLivreur, double FraisdeLivraison) {
        this.IdLivraison = IdLivraison;
         this.IdLivreur = IdLivreur;
        
        this.NomLivreur = NomLivreur;
        this.FraisdeLivraison = FraisdeLivraison;
    }

    public GestiondeLivraison(int IdLivraison,int IdLivreur,double FraisdeLivraison) {
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

    public String getNomLivreur() {
        return NomLivreur;
    }

    public void setNomLivreur(String NomLivreur) {
        this.NomLivreur = NomLivreur;
    }

    public double getFraisdeLivraison() {
        return FraisdeLivraison;
    }

    public void setFraisdeLivraison(double FraisdeLivraison) {
        this.FraisdeLivraison = FraisdeLivraison;
    }

    @Override
    public String toString() {
        return "GestiondeLivraison{" + "IdLivraison=" + IdLivraison + ", IdLivreur=" + IdLivreur + ", NomLivreur=" + NomLivreur + ", FraisdeLivraison=" + FraisdeLivraison + '}';
    }

        
}
