/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.entities;

import java.util.Date;

/**
 *
 * @author gayth
 */
public class Utilisateur {
    
    private int id_util;
    private String nom;
    private String prenom;
    private String numtel;
    private Date dn;
    private String email;
    private String mdp;
    private int age;
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int id_util, String nom, String prenom, String numtel, Date dn, String email, String role) {
        this.id_util = id_util;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(int id_util, String nom, String prenom, String numtel, Date dn, String email, int age, String role) {
        this.id_util = id_util;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public Utilisateur(int id_util,String nom) {
          this.id_util = id_util;
        this.nom = nom;
    }
    

    public Utilisateur(int id_util, String nom, String prenom, String numtel, Date dn, String email, String mdp, int age, String role) {
        this.id_util = id_util;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.mdp = mdp;
        this.age = age;
        this.role = role;
    }

    public int getId_util() {
        return id_util;
    }

    public void setId_util(int id_util) {
        this.id_util = id_util;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public Date getDn() {
        return dn;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_util=" + id_util + ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", dn=" + dn + ", email=" + email + ", mdp=" + mdp + ", age=" + age + ", role=" + role + '}';
    }
    
    
    
    
}
