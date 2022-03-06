/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import javafx.scene.control.TableColumn;

/**
 *
 * @author islemferchichi
 */
public class Livreur {
    

    


    public int IdLivreur;   
    public String NomLivreur;
    public String prenomLivreur;
    public int telLivreur; 
    
   public Livreur(){}
       public Livreur(int IdLivreur){
           this.IdLivreur = IdLivreur;
   }
   
    public Livreur(int IdLivreur, String NomLivreur, String prenomLivreur) {
        this.IdLivreur = IdLivreur;
        this.NomLivreur = NomLivreur;
        this.prenomLivreur = prenomLivreur;
       
    }
    public Livreur(int IdLivreur, String NomLivreur, String prenomLivreur, int telLivreur) {
        this.IdLivreur = IdLivreur;
        this.NomLivreur = NomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.telLivreur = telLivreur;
    }

    public Livreur(TableColumn<Livreur, Integer> Id, TableColumn<Livreur, String> Nom, TableColumn<Livreur, String> Prenom, TableColumn<Livreur, Integer> Tel) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return String.valueOf(IdLivreur) ;
    }
}
   