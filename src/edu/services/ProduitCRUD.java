/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.services;

import entities.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;


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
    public void addProd(Produit r) {
        try {
            String request = "INSERT INTO produit (lib_prod,description,prix_prod,quantiteDispo,Remise,categorie,Image_prod) VALUES(?,?,?,?,?,?,?) ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().clientPrepareStatement(request);
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

    public void updateProd(Produit r) {
        try {
            String plus ="";
            if(r.getPrix_prod()!= 0)
                plus +=", prix_prod = "+r.getPrix_prod();
            if(r.getQuaniteDispo() !=0)
                plus +=", quantiteDispo = "+r.getQuaniteDispo();
            String request = "UPDATE produit Set description = ?"+plus+" where lib_prod = ?  ";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().clientPrepareStatement(request);

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

       public void updateremise(Produit r) {
        try {

            String request = "UPDATE produit Set Remise = ? where lib_prod = ?  ";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().clientPrepareStatement(request);

            pst.setString(1, r.getRemise());
   
            pst.setString(2, r.getLib_prod());
            pst.executeUpdate();
            System.out.println("Remise modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
              public void updatecategorie(Produit r) {
        try {

            String request = "UPDATE produit Set categorie = ? where lib_prod = ?  ";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().clientPrepareStatement(request);

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
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().clientPrepareStatement(request);

            pst.setString(1, name);
            pst.executeUpdate();
            System.out.println("Repa supprimé! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    List<Produit> GetProd() {
        List<Produit> myList = new ArrayList();
        try {

            String request = "SELECT * FROM produit";

            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Produit r = new Produit();

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
    
    
}
