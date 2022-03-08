/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import java.sql.Statement;
import edu.utils.MyConnection;
import edu.entities.reservation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;
import javax.management.Notification;

/**
 *
 * @author Nesrine
 */
public class reservationCRUD {
    public void ajouterReservation(){
        try {
            String requete="INSERT INTO reservation(nomClient,numero,nbpersonne,date,id_evenement)"
                    + "VALUES('nomClient',2,5,STR_TO_DATE('1-01-2012', '%d-%m-%Y'),6)";
            Statement st= new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("personne ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
}
    }
public void ajouterReservation2(reservation R){  
    String requete2="INSERT INTO reservation(nomClient,numero,nbpersonne,date,id_evenement)"
            + "VALUES(?,?,?,?,?)";
        try {
            
            System.out.println(R.getDate());
            
            PreparedStatement pst= new MyConnection().getCnx().prepareCall(requete2);
            pst.setString(1,R.getNomClient());
            pst.setInt(2,R.getNumero());
            pst.setInt(3,R.getNbpersonne());
            pst.setDate(4,R.getDate());
            pst.setInt(5,R.getId_evenement());
            pst.executeUpdate();
            System.out.println("Votre réservation est bien ajoutée");
            
            Notifications  notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("reservation done!");
        tray.setMessage("bonne journée!");
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(6));
           
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
public List<reservation> afficheReservation(){
       List<reservation> MyList = new ArrayList<>();
        try {
            
            String requete3="SELECT * FROM reservation";
            Statement st = new MyConnection().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete3);
             while (rs.next()){
             reservation r= new reservation();
             r.setId(rs.getInt(1));
             r.setNomClient(rs.getString(2));
             r.setNumero(rs.getInt(3));
             r.setNbpersonne(rs.getInt(4));
             r.setDate(rs.getDate(5));
             r.setId_evenement(rs.getInt(6));
             MyList.add(r);
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return MyList;
}
public void modifierReservation(reservation r) {
        try {
            String query = "UPDATE reservation SET nomClient='" + r.getNomClient() +"',numero='" + r.getNumero() + "',nbpersonne='" + r.getNbpersonne()+ "',date='" + r.getDate() +"'id_evenement='"+r.getId_evenement()+ "' WHERE id=" + r.getId();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Reservation  modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public void supprimerReservation( int id) {
        try {
            String query = "DELETE FROM reservation where id=" +id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


public void archiverReservation() {
        List<reservation> listereservation = new ArrayList<reservation>();
        listereservation = afficheReservation();

        Date date;
        date = new Date(0);

        Timestamp currentDate = new Timestamp(date.getTime());
       
       
        //archivage aprés 7 jours de la création du reclamation

        listereservation.stream()
                .filter(r -> r.getDate().getDate() - currentDate.getDate() < 7)
                .forEach((P) -> {
                    String requete = null;
                    try {
                        requete = "UPDATE reservation SET "
                                + "archived = 1 ";

                        Statement st = new MyConnection().getCnx().createStatement();
                        st.executeUpdate(requete);
                        System.out.println("reservation arrchivée");

                    } catch (SQLException e) {
                        System.out.println("erreur d'archivage reservation");
                    }
                });
    }

    public void setTest(String toString) {
         //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerReservation(String id) {
        try {
            String query = "DELETE FROM reservation where id=" +id;
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*public boolean modifierRes(String id, String nomClient, String numero, String nbpersonne, DatePicker date, String id_evenement) {
        String qry = "UPDATE reservation  SET nomClient='" +nomClient + "',numero='" + numero + "',nbpersonne='" + nbpersonne+ "',date='" + date +"'id_evenement='"+id_evenement+ "' WHERE id=" + id;

        try {
            Statement st = new MyConnection().getCnx().createStatement();
               st.executeUpdate(qry);
            System.out.println("reservation est bien modifiée");
                return true;
            

        } catch (SQLException ex) {
            System.out.println("error en modification de réservation");
            System.out.println(ex.getMessage());
            
        }
        return false;
    }*/

    public boolean modifierRes(String id, String nomClient, String numero, String nbpersonne, String myFormatteDate, String id_evenement) {
        String qry = "UPDATE reservation SET nomClient= '" + nomClient + "' ,numero= '" + numero + "',nbpersonne='" + nbpersonne + "',date='"+myFormatteDate +"',id_evenement='"+id_evenement+"' WHERE id = "+id;
                    
        try {
            Statement st = new MyConnection().getCnx().createStatement();
               st.executeUpdate(qry);
            System.out.println("reservation est bien modifiée");
                return true;
            

        } catch (SQLException ex) {
            System.out.println("error en modification de réservation");
            System.out.println(ex.getMessage());
            
        }
        return false;
        

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
    }





    


































































