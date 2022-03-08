/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Entity;

import java.sql.Date;

/**
 *
 * @author broum
 */
public class Utilisateur {

  
     private int id;
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
      public Utilisateur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Utilisateur(int id, String nom, String numtel, Date dn, String email, String role) {
        this.id = id;
        this.nom = nom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(int id, String nom, String prenom, String numtel, Date dn, String email, int age, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    
    public Utilisateur(int id, String nom, String prenom, String numtel, Date dn, String email, String mdp, int age, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.dn = dn;
        this.email = email;
        this.mdp = mdp;
        this.age = age;
        this.role = role;
    }

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    public Date getDn() {
        return dn;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", dn=" + dn + ", email=" + email + ", mdp=" + mdp + ", age=" + age + ", role=" + role + '}';
    }
    
    
}
