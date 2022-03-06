/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dao;

import edu.entities.StockCategory;
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
public class StockCategoryCRUD {

    public void addStockCategory(StockCategory c) {
        try {
            String request = "INSERT INTO StockCategory (nom) VALUES(?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setString(1, c.getNom());
            pst.executeUpdate();
            System.out.println(" Categorie ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<StockCategory> DisplayStockCategories() {
        List<StockCategory> myList = new ArrayList();
        try {
            String request = "Select * from StockCategory";
            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                StockCategory c = new StockCategory();
                c.setId(res.getInt(1));
                c.setNom(res.getString(2));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void updateStockCategory(StockCategory c) {
        try {
            String request = "UPDATE StockCategory Set nom = ? where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setString(1, c.getNom());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
            System.out.println("categorie modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteStockCategory(int x) {
        try {
            String request = "DELETE FROM StockCategory where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, x);
            pst.executeUpdate();
            System.out.println("categorie supprimé! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
