/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Nesrine
 */
public class reservation {

  
   
    private int id;
    private String nomClient;
    private int numero;
    private int nbpersonne;
    private Date date ;
    private int id_evenement;
    
    public reservation(){
        
    }

    public reservation(int id, String nomClient, int numero, int nbpersonne, Date date, int id_evenement) {
        this.id = id;
        this.nomClient = nomClient;
        this.numero = numero;
        this.nbpersonne = nbpersonne;
        this.date = date;
        this.id_evenement = id_evenement;
    }

    public reservation(String nomClient, int numero, int nbpersonne, Date date, int id_evenement) {
        this.nomClient = nomClient;
        this.numero = numero;
        this.nbpersonne = nbpersonne;
        this.date = date;
        this.id_evenement = id_evenement;
    }
    

    

    public reservation(String ahmed, int i, String string) {
         //To change body of generated methods, choose Tools | Templates.
    }

    public reservation(String nomClient, String numero, String nbpersonne, DatePicker date, String id_evenement) {
 //To change body of generated methods, choose Tools | Templates.
    }

    public reservation(String nomClient, String numero, String nbpersonne, String myFormatteDate, String id_evenement) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public reservation(String id, String nomClient, String numero, String nbpersonne, String myFormatteDate, String id_evenement) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

     public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }
       

    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", nomClient=" + nomClient + ", numero=" + numero + ", nbpersonne=" + nbpersonne + ", date=" + date + ", id_evenement=" + id_evenement + '}'+"\n";
    }
   
}
