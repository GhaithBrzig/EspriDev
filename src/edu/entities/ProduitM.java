/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.entities;

import java.util.Objects;

/**
 *
 * @author SBS
 */
public class ProduitM {

    private int id_prod;
    private String lib_prod;
    private String description;
    private double prix_prod;
    private int quaniteDispo;
    private String categorie;
    private String remise = "0%";
    private String Path;

    
    public ProduitM() {
    }

    public ProduitM(String lib_prod, String categorie) {
        this.lib_prod = lib_prod;
        this.categorie = categorie;
        prix_prod = 0;
        quaniteDispo = 0;
    }

    public ProduitM(String lib_prod, int prix_prod, String categorie) {
        this.lib_prod = lib_prod;
        this.prix_prod = prix_prod;
        this.categorie = categorie;
        quaniteDispo = 0;
    }

    public ProduitM(String lib_prod, String description, String categorie) {
        this.lib_prod = lib_prod;
        this.description = description;
        this.categorie = categorie;
    }

    public ProduitM(String lib_prod, String description, double prix_prod, int quaniteDispo, String categorie) {
        this.lib_prod = lib_prod;
        this.description = description;
        this.prix_prod = prix_prod;

        this.quaniteDispo = quaniteDispo;
        this.categorie = categorie;
    }

    public ProduitM(int id_prod, String lib_prod, String description, double prix_prod, int quaniteDispo, String categorie) {
        this.id_prod = id_prod;
        this.lib_prod = lib_prod;
        this.description = description;
        this.prix_prod = prix_prod;
        this.quaniteDispo = quaniteDispo;
        this.categorie = categorie;
    }

    public ProduitM(int id_prod, String lib_prod, String description, double prix_prod, int quaniteDispo, String categorie, String Path) {
        this.id_prod = id_prod;
        this.lib_prod = lib_prod;
        this.description = description;
        this.prix_prod = prix_prod;
        this.quaniteDispo = quaniteDispo;
        this.categorie = categorie;
        this.Path = Path;
    }

    
    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getLib_prod() {
        return lib_prod;
    }

    public void setLib_prod(String lib_prod) {
        this.lib_prod = lib_prod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(double prix_prod) {
        this.prix_prod = prix_prod;
    }

    public int getQuaniteDispo() {
        return quaniteDispo;
    }

    public void setQuaniteDispo(int quaniteDispo) {
        this.quaniteDispo = quaniteDispo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    
    @Override
    public String toString() {
        return "Produit{" + "lib_prod=" + lib_prod + ", description=" + description + ", prix_prod=" + prix_prod + "dt, quaniteDispo=" + quaniteDispo + ", categorie=" + categorie + '}';
    }

}
