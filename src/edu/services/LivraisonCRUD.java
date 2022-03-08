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
            String request = "INSERT INTO livraison(IdLivraison,IdLivreur,FraisdeLivraison,id_commande) VALUES(?,?,?,?) ";
            PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
             
         pst.setInt(1,l.getIdLivraison());
         pst.setInt(2,l.getIdLivreur());
         pst.setInt(4,l.getId_commande());
         pst.setDouble(3,l.getFraisdeLivraison());
   
         pst.executeUpdate();
            System.out.println("la Livraison est ajouté! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void updateLivraison(Livraison l) {
        try {
            String request = "UPDATE livraison Set IdLivreur = ?, FraisdeLivraison = ?, id_commande = ? where idLivraison = ?  ";
          PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, l.getIdLivreur());
            pst.setDouble(2, l.getFraisdeLivraison());
            pst.setInt(3,l.getId_commande());
            pst.setInt(4,l.getIdLivraison());

            //pst.setInt(3, l.getIdCommande());
            pst.executeUpdate();
           System.out.println("Livraison modifié! ");
  
      }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
       }}
    public void deleteLivraison(int x) {
        try {
            String request = "DELETE FROM livraison  where IdLivraison = ?  ";
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
            String request = "select sum(pr.prix_prod * p.quantite) as tot , l.IdLivraison , l.IdLivreur , l.FraisdeLivraison , l.id_commande from livraison l inner join panier p inner join produit pr where p.id_commande = l.id_commande and p.id_produit = pr.id_prod group by l.IdLivraison";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                Livraison gl = new Livraison();
                gl.setIdLivraison(res.getInt("l.IdLivraison"));          
                gl.setTotal(res.getDouble("tot"));
                gl.setIdLivreur(res.getInt("l.IdLivreur")); 
                if(gl.getTotal()>100.0){
                     gl.setFraisdeLivraison(0);
                }
                else {
                     gl.setFraisdeLivraison(res.getDouble("l.FraisdeLivraison"));
                }
               
                gl.setId_commande(res.getInt("l.id_commande"));
                myList.add(gl);
                System.out.println(gl);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                System.out.println(myList);

        return myList;
    }
  public List<Livreur> DisplayLivraison2() {
        List<Livreur> myList = new ArrayList();
        try {
            String request = "SELECT livreur.IdLivreur FROM livreur ";
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
 public List<Integer> DisplayCommandeId() {
        List<Integer> myList = new ArrayList();
        try {
            String request = "Select id from commande where id > 0 ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(request);

            while (res.next()) {
                myList.add(res.getInt(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
 
 
    
}






