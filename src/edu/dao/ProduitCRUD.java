/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dao;

import com.mysql.jdbc.Blob;
import edu.entities.Produit;
import edu.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gayth
 */
public class ProduitCRUD {

    public void addProduit(Produit p) {
        try {
            String request = "INSERT INTO stock (id_categorie,nom,unite,quantite,categorie,prix_unitaire,prix_total) VALUES(?,?,?,?,?,?,?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, p.getId_categorie());
            pst.setString(2, p.getNom());
            pst.setString(3, p.getUnite());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getCategorie());
            pst.setDouble(6, p.getPrix_unitaire());
            pst.setDouble(7, p.getPrix_unitaire() * p.getQuantite());
            pst.executeUpdate();
            System.out.println("Produit ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Produit> DisplayStock() {
        List<Produit> myList = new ArrayList();
        try {
            String request = "Select id,id_categorie,nom,unite,quantite,categorie,prix_unitaire,ROUND( prix_total, 1 ) as prix_total from stock";
            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Produit p = new Produit();
                p.setId(res.getInt(1));
                p.setId_categorie(res.getInt(2));
                p.setNom(res.getString(3));
                p.setUnite(res.getString(4));
                p.setQuantite(res.getInt(5));
                p.setCategorie(res.getString(6));
                p.setPrix_unitaire(res.getDouble(7));
                p.setPrix_total(res.getDouble(8));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void updateProduit(Produit p) {
        try {
            String request = "UPDATE stock Set id_categorie = ?, nom = ?, unite = ?, quantite = ?, categorie = ?, prix_unitaire = ?, prix_total = ?  where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, p.getId_categorie());
            pst.setString(2, p.getNom());
            pst.setString(3, p.getUnite());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getCategorie());
            pst.setDouble(6, p.getPrix_unitaire());
            pst.setDouble(7, p.getPrix_unitaire() * p.getQuantite());
            pst.setInt(8, p.getId());
            pst.executeUpdate();
            System.out.println("Produit modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteProduit(int x) {
        try {
            String request = "DELETE FROM stock where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, x);
            pst.executeUpdate();
            System.out.println("Produit supprimé! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
