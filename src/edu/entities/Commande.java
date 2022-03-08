/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import edu.services.CommandeService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author broum
 */
public class Commande {

   
    private int id;
    private int id_user;
    private boolean etat_cmd;
    private Date date_cmd;
    private ArrayList<Panier> Paniers;
    CommandeService c = new CommandeService();
    public ArrayList<Panier> getPaniers() {
        return Paniers;
    }

    public void setPaniers() {
        this.Paniers = c.getPaniers(this.id);
    }
    
    
    public Commande() {
    }
     public Commande(int id_commande) {
        this.id = id_commande;
    }

    public Commande(int id, int id_user, boolean etat_cmd, Date date_cmd) {
        this.id = id;
        this.id_user = id_user;
        this.etat_cmd= etat_cmd;
        this.date_cmd=date_cmd;
    }
    
    public Commande(int id_user,boolean etat_cmd,Date date_cmd) {
        this.id_user = id_user; 
        this.etat_cmd= etat_cmd;
        this.date_cmd=date_cmd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public boolean isEtat_cmd() {
        return etat_cmd;
    }

    public void setEtat_cmd(boolean etat_cmd) {
        this.etat_cmd = etat_cmd;
    }

    public Date getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", id_user=" + id_user + ", Validé=" + etat_cmd + ", date_cmd=" + date_cmd + '}';
    }

}
