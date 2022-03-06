/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.entities.Livraison;
import edu.entities.Livreur;
import edu.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author islemferchichi
 */
public class LivraisonCRUD {

    //Connection cnx2;
   
    //cnx2 = MyConnection.getInstance().getCnx();
    
    public void addLivraison(Livraison l) {
        
        try {
            String request = "INSERT INTO Livraison(IdLivraison,IdLivreur,FraisdeLivraison) VALUES(?,?,?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
             
         pst.setInt(1,l.getIdLivraison());
         pst.setInt(2,l.getIdLivreur());
     
         pst.setDouble(3,l.getFraisdeLivraison());
   
         pst.executeUpdate();
            System.out.println("la Livraison est ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void updateLivraison(Livraison l) {
        try {
            String request = "UPDATE Livraison Set IdLivreur = ?, FraisdeLivraison = ? where idLivraison = ?  ";
          PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, l.getIdLivreur());
            pst.setDouble(2, l.getFraisdeLivraison());
            pst.setInt(3,l.getIdLivraison());
            //pst.setInt(3, l.getIdCommande());
            pst.executeUpdate();
           System.out.println("Livraison modifié! ");
  
      }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }}
    public void deleteLivraison(int x) {
        try {
            String request = "DELETE FROM Livraison  where IdLivraison = ?  ";
           PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, x);
            
            pst.executeUpdate();
           System.out.println("Livraison annulé! ");
  
      }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }}

  public List<Livraison> DisplayLivraison() {
        List<Livraison> myList = new ArrayList();
        try {
            String request = "Select * from Livraison";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Livraison gl = new Livraison();
                gl.setIdLivraison(res.getInt(1));
                gl.setIdLivreur(res.getInt(2));  
                gl.setFraisdeLivraison(res.getDouble(3));

                myList.add(gl);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
  public List<Livreur> DisplayLivraison2() {
        List<Livreur> myList = new ArrayList();
        try {
            String request = "SELECT Livreur.IdLivreur FROM Livreur,Livraison WHERE Livreur.IdLivreur=Livraison.IdLivreur";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Livreur gl = new Livreur();
                gl.setIdLivreur(res.getInt(1));  
             

                myList.add(gl);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }


    
}






