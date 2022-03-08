/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import java.sql.Date;

/**
 *
 * @author Nesrine
 */
public class evenement {
    private int id;
    private String type;
    private String description;
    private int nbpersonne;
    private Date date;
    //dateEvenement 

    public evenement() {
    }
     public evenement(String type, String description, String nbpersonne, String myFormatteDate) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public evenement(int id, String type, String description, int nbpersonne, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.nbpersonne = nbpersonne;
        this.date = date;
    }

    public evenement(String type, String description, int nbpersonne, Date date) {
        this.type = type;
        this.description = description;
        this.nbpersonne = nbpersonne;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", type=" + type + ", description=" + description + ", nbpersonne=" + nbpersonne + ", date=" + date + '}'+"\n";
    }
    
    
}
