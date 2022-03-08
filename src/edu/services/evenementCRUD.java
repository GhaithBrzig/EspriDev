/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;
import java.sql.Statement;
import edu.utils.MyConnection;
import edu.entities.evenement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 *
 * @author Nesrine
 */
public class evenementCRUD {
    
    public void ajouterEvenement(){
        try {
            String requete="INSERT INTO evenement(type,description,nbpersonne,date)"
                    + "VALUES('nesrine','mariage',5,STR_TO_DATE('1-01-2012', '%d-%m-%Y'))";
            Statement st= new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("evenement ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
}
    }
public void ajouterEvenement2(evenement e){  
    String requete2="INSERT INTO evenement(type,description,nbpersonne,date)"
            + "VALUES(?,?,?,?)";
        try {
            System.out.println(e.getDate());
            PreparedStatement pst= new MyConnection().getCnx().prepareCall(requete2);
            pst.setString(1,e.getType());
            pst.setString(2,e.getDescription());
            pst.setInt(3,e.getNbpersonne());
            pst.setDate(4,e.getDate());
            pst.executeUpdate();
            System.out.println("Votre evenement est bien ajoutée");
           
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
public List<evenement> afficheEvenement(){
       List<evenement> MyList = new ArrayList<>();
        try {
            
            String requete3="SELECT * FROM evenement";
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
             evenement e= new evenement();
             e.setId(rs.getInt(1));
             e.setType(rs.getString(2));
             e.setDescription(rs.getString(3));
             e.setNbpersonne(rs.getInt(4));
             e.setDate(rs.getDate(5));
             MyList.add(e);
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return MyList;
}
public void modifierEvenment(evenement e) {
        try {
            String query = "UPDATE evenement SET type='" + e.getType() +"',description='" + e.getDescription() + "',nbpersonne='" + e.getNbpersonne()+ "',date='" + e.getDate() + "' WHERE id=" + e.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Evenement  modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public void supprimerEvenement(int id) {
        try {
            String query = "DELETE FROM evenement where id=" +id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Evenement supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

public static ObservableList<XYChart.Series<String, Double>> statistique(){
      ObservableList<XYChart.Series<String, Double>> stats = FXCollections.observableArrayList();
        
     try { 
            String requete3="select count(*) , e.type from reservation r join evenement e where r.id_evenement = e.id  group by e.type";
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
                 Series<String, Double> serie = new Series<>();
                 serie.setName(rs.getString("type"));
                 serie.getData().add(new XYChart.Data(rs.getString("type"),rs.getInt("count(*)")));
                  stats.addAll(serie);
                 
             }     
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      return stats;
}

    

    public boolean modifierEV(String id, String type, String description, String nbpersonne, String myFormatteDate) {
         String qry = "UPDATE evenement SET type= '" + type + "' ,description= '" + description +  "',nbpersonne='"+nbpersonne +"',date='"+myFormatteDate+"' WHERE id = "+id;
                    
        try {
            Statement st = new MyConnection().getCnx().createStatement();
               st.executeUpdate(qry);
            System.out.println("evenement est bien modifiée");
                return true;
            

        } catch (SQLException ex) {
            System.out.println("error en modification d'evenement");
            System.out.println(ex.getMessage());
            
        }
        return false;
        

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerEvenement(String id) {
        try {
            String query = "DELETE FROM evenement where id=" +id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("evenement supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public ObservableList<Integer> ListId(){
        
     ObservableList<Integer> listID = FXCollections.observableArrayList();
        
     try { 
            String requete3="select id from evenement";
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
                listID.add(rs.getInt(1));
             }     
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      return listID;
    
    
    
    
    
    
    
    }
    public List<evenement> SearchEV(String recherche) {
        List<evenement> myList = new ArrayList();

        try {
            String requete3 = "SELECT * \n"
                    + "FROM evenement AS e,reservation \n"
                    + " WHERE  e.id LIKE '%" + recherche + "%' OR\n"
                    + "         e.type LIKE '%" + recherche + "%' OR\n"
                    + "           e.description LIKE '%" + recherche + "%'";
          

            
                Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
           

                evenement e = new evenement();

                rs.next();
             e.setId(rs.getInt(1));
             e.setType(rs.getString(2));
             e.setDescription(rs.getString(3));
             e.setNbpersonne(rs.getInt(4));
             e.setDate(rs.getDate(5));
            
                myList.add(e);

            

        } catch (SQLException ex) {
            System.err.println("erreur de trouver ");
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
    

