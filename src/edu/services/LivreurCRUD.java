/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;


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
//public class LivreurCRUD {
     //Connection cnx2;
    
    public class LivreurCRUD{
        //cnx2 = MyConnection.getInstance().getCnx();
    
    public void addLivreur(Livreur glv) {
        try {
            String request = "INSERT INTO Livreur (IdLivreur,NomLivreur,prenomLivreur,telLivreur) VALUES(?,?,?,?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
             
           pst.setInt(1,glv.getIdLivreur());
     
           pst.setString(2,glv.getNomLivreur());
           
           pst.setString(3,glv.getPrenomLivreur());
           
           pst.setInt(4,glv.getTelLivreur());
           
           pst.executeUpdate();
           
           
            System.out.println("la Livreur ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void updateLivreur(Livreur glv) {
        try {
            String request = "UPDATE Livreur Set NomLivreur = ?,prenomLivreur = ?,telLivreur =? where idLivreur = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
     
           pst.setString(1,glv.getNomLivreur());
           
           pst.setString(2,glv.getPrenomLivreur());
           
           pst.setInt(3,glv.getTelLivreur());
                       pst.setInt(4,glv.getIdLivreur());

           
            pst.executeUpdate();
            
           System.out.println("Livreur modifié! ");
  
      }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }}
    public void deleteLivreur(int IdLivreur) {
         try {
            String request = "DELETE FROM Livreur  where IdLivreur = ?  ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, IdLivreur);
            
            pst.executeUpdate();
           System.out.println("Livreure supprimé ! ");
  
      }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }
     }

   public List<Livreur> DisplayLivreur() {
        List<Livreur> myList = new ArrayList();
        try {
            String request = "Select * from Livreur";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Livreur glv = new Livreur();
               glv.setIdLivreur(res.getInt(1));
                glv.setNomLivreur(res.getString(2));
                glv.setPrenomLivreur(res.getString(3));
                glv.setTelLivreur(res.getInt(4));

                myList.add(glv);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    
}




    

