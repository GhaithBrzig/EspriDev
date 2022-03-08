/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Entity;

import panier.Services.ProduitCRUD;
import panier.Services.ProduitService;

/**
 *
 * @author broum
 */
public class Panier {

    private int id;
    private int id_user;
    private int id_produit;
    private int id_commande;
    private int quantite;
    private double total;
    private Produit produit;
    ProduitCRUD ps = new ProduitCRUD() ;
    
    public Produit getProduit() {
        return produit;
    }

    public void setProduit() {
        this.produit = ps.produitLoadData(this.id_produit);
    }
  
 
    public Panier() {
    }   
       public Panier(int id) {
        this.id = id;
    }
    public Panier(int id_user, int id_produit, int id_commande, int quantite, double total) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.quantite = quantite;
        this.total=total;
    }

       public Panier(int id, int id_user, int id_produit, int id_commande, int quantite, double total) {
        this.id = id;
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.quantite = quantite;
        this.total=total;

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

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
  public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", id_user=" + id_user + ", id_produit=" + id_produit +", quantite=" + quantite + ", total=" + total + '}';
    }

}
