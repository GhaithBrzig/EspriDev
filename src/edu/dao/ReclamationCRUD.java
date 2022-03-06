/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dao;

import edu.entities.Reclamation;
import edu.utils.MyConnection;
import java.sql.Date;
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
public class ReclamationCRUD {

    public void addReclamation(Reclamation r) {
        try {
            String request = "INSERT INTO Reclamation (nomClient,emailClient,description,date,isSolved) VALUES(?,?,?,?,?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setString(1, r.getNomClient());
            pst.setString(2, r.getEmailClient());
            pst.setString(3, r.getDescription());
            pst.setDate(4, (Date) r.getDate());
            pst.setBoolean(5, false);
            pst.executeUpdate();
            System.out.println("Reclamation ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Reclamation> DisplayReclamations() {
        List<Reclamation> myList = new ArrayList();
        try {
            String request = "Select * from reclamation ";
            Statement st = (Statement) MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setId(res.getInt(1));
                r.setNomClient(res.getString(2));
                r.setEmailClient(res.getString(3));
                r.setDescription(res.getString(4));
                r.setDate(res.getDate(5));
                r.setIsSolved(res.getBoolean(6));
                myList.add(r);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void updateReclamation(Reclamation r) {
        try {
            String request = "UPDATE reclamation Set isSolved = ? where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setBoolean(1, true);
            pst.setInt(2, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation modifié! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteReclamation(int x) {
        try {
            String request = "DELETE FROM reclamation where id = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, x);
            pst.executeUpdate();
            System.out.println("Reclamation supprimé! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void deleteReclamationD() {
        try {
           String query = "DELETE FROM Reclamation WHERE NOW() >= ADDDATE(date, INTERVAL 7 DAY);" ;
                PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
