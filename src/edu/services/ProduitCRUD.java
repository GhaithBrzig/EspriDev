/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.services;

import edu.entities.Produit;
import edu.entities.ProduitM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.utils.MyConnection;


/**
 *
 * @author SBS
 */
public class ProduitCRUD {

    /*  public void addRepaST(){
        try {
            String request = "INSERT INTO repas (name,price) VALUES('soup ',15) ";
            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement() ;
            st.executeUpdate(request);
            System.out.println("repa ajouté!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     */
    public void addProd(ProduitM r) {
        try {
            String request = "INSERT INTO produit (lib_prod,description,prix_prod,quantiteDispo,Remise,categorie,Image_prod) VALUES(?,?,?,?,?,?,?) ";
                   PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setString(1, r.getLib_prod());
            pst.setString(2, r.getDescription());
            pst.setDouble(3, r.getPrix_prod());
            pst.setInt(4, r.getQuaniteDispo());
            pst.setString(5, r.getRemise());
            pst.setString(6, r.getCategorie());
            pst.setString(7, r.getPath());

            pst.executeUpdate();
            System.out.println("Repa ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updateProd(ProduitM r) {
        try {
            String plus ="";
            if(r.getPrix_prod()!= 0)
                plus +=", prix_prod = "+r.getPrix_prod();
            if(r.getQuaniteDispo() !=0)
                plus +=", quantiteDispo = "+r.getQuaniteDispo();
            String request = "UPDATE produit Set description = ?"+plus+" where lib_prod = ?  ";
                   PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);

            pst.setString(1, r.getDescription());
           // pst.setDouble(2, r.getPrix_prod());
           // pst.setInt(3, r.getQuaniteDispo());
            
            pst.setString(2, r.getLib_prod());
            pst.executeUpdate();
            System.out.println("Repa modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

       public void updateremise(ProduitM r) {
        try {

            String request = "UPDATE produit Set Remise = ? where lib_prod = ?  ";
                 PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);

            pst.setString(1, r.getRemise());
   
            pst.setString(2, r.getLib_prod());
            pst.executeUpdate();
            System.out.println("Remise modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
              public void updatecategorie(ProduitM r) {
        try {

            String request = "UPDATE produit Set categorie = ? where lib_prod = ?  ";
                PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);

            pst.setString(1, r.getCategorie());
   
            pst.setString(2, r.getLib_prod());
            pst.executeUpdate();
            System.out.println("Categorie modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void deleteProd(String name) {
        try {
            String request = "DELETE FROM produit WHERE lib_prod = ?  ";
               PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);

            pst.setString(1, name);
            pst.executeUpdate();
            System.out.println("Repa supprimé! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    List<ProduitM> GetProd() {
        List<ProduitM> myList = new ArrayList();
        try {

            String request = "SELECT * FROM produit";

            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                ProduitM r = new ProduitM();

                r.setLib_prod(res.getString(2));
                r.setDescription(res.getString(3));
                r.setPrix_prod(res.getDouble(4));
                r.setQuaniteDispo(res.getInt(5));
                r.setRemise(res.getString(6));
                r.setCategorie(res.getString(7));
                r.setPath(res.getString(8));
                
                myList.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    private static final String REQUETTE_SELECT_BYID = "select * from produit where id_prod=?";
    public ProduitM produitLoadData(int id) {
        ProduitM c = new ProduitM();
        try {
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(REQUETTE_SELECT_BYID);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.setId_prod(rs.getInt("id_prod"));
                
                c.setPrix_prod(rs.getDouble("prix_prod"));
                c.setQuaniteDispo(rs.getInt("quantiteDispo"));
                c.setLib_prod(rs.getString("lib_prod"));
                c.setDescription(rs.getString("description"));
                c.setPath(rs.getString("Image_prod"));
                
                return c;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Erreur");
        }
        return c;
    }
}
    
    

