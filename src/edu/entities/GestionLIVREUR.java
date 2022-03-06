/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//test
package edu.entities;

/**
 *
 * @author islemferchichi
 */
public class GestionLIVREUR {
    


    public int IdLivreur;   
    public String NomLivreur;
    public String prenomLivreur;
    public int telLivreur; 
    
   public GestionLIVREUR(){
   }
    public GestionLIVREUR(int IdLivreur, String NomLivreur, String prenomLivreur, int telLivreur) {
        this.IdLivreur = IdLivreur;
        this.NomLivreur = NomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.telLivreur = telLivreur;
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

    public String getPrenomLivreur() {
        return prenomLivreur;
    }

    public void setPrenomLivreur(String prenomLivreur) {
        this.prenomLivreur = prenomLivreur;
    }

    public int getTelLivreur() {
        return telLivreur;
    }

    public void setTelLivreur(int telLivreur) {
        this.telLivreur = telLivreur;
    }

    @Override
    public String toString() {
        return "ListeLivreur{" + "IdLivreur=" + IdLivreur + ", NomLivreur=" + NomLivreur + ", prenomLivreur=" + prenomLivreur + ", telLivreur=" + telLivreur + '}';
    }
    
}

