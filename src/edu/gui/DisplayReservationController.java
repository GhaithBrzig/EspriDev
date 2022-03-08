/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import edu.entities.reservation;
import edu.services.reservationCRUD;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Locale;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class DisplayReservationController implements Initializable {

    @FXML
    private TextField idRes;
    @FXML
    private TextField ResNC;
    @FXML
    private TextField ResNum;
    @FXML
    private TextField ResNbP;
    @FXML
    private TextField ResIDEven;
    @FXML
    private DatePicker date;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ResIDEven.setEditable(false);
          
            idRes.setEditable(false);
            
       
                date.setDisable(true);
                date.setStyle("-fx-opacity: 1");
             date.getEditor().setStyle("-fx-opacity: 1");

           
        
        
    }    

    @FXML
    private void cancel(ActionEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
            stage.close();

        
    }

    public void setData(int id, String nomClient, int numero, int nbpersonne, Date daate,int id_evenement) {
        ResNC.setText("" + nomClient);
        ResNum.setText("" + numero);
        LocalDate lDate = daate.toLocalDate();
        date.setValue(lDate);
        ResNbP.setText(""+nbpersonne);
        ResIDEven.setText(""+id_evenement);
        idRes.setText("" + id);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        String id = idRes.getText();
        String nomClient =ResNC .getText();
        String numero = ResNum.getText();
        String nbpersonne = ResNbP.getText();
        String  id_evenement= ResIDEven.getText();
        //int date= date.getDate();
        
        
         try{
       
             //System.out.println("hejer");
            if ((nomClient.isEmpty()) || (numero.isEmpty())||(nbpersonne.isEmpty())||(id_evenement.isEmpty())) {
            if (ResNC.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
               ResNC.setStyle(null);
                
            }
            if (ResNum.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
                ResNum.setStyle(null);
            }
             if (ResNbP.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
                ResNbP.setStyle(null);
            }
              if (ResIDEven.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
                ResIDEven.setStyle(null);
            }
            
            
            }else{
            reservation res = new reservation(nomClient, numero, nbpersonne, date, id_evenement);
            reservationCRUD RC = new reservationCRUD();
            RC.supprimerReservation(id);            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           // g.loadDate();
            stage.close();
            }
        } catch(Exception ex)
                {
                System.out.println("error : "+ex.getMessage());
                }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
         String id = idRes.getText();
        String nomClient =ResNC .getText();
        String numero = ResNum.getText();
        String nbpersonne = ResNbP.getText();
        String  id_evenement= ResIDEven.getText();
        //Date dateRes = date.get;
        LocalDate dateRes = date.getValue();
        String myFormatteDate = dateRes.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
     /*  String date = request.getParameter("date");
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
java.util.Date dateStr = formatter.parse(sqlBirthday );
java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());*/
         try{
       
      
            reservation res = new reservation(id,nomClient, numero, nbpersonne, myFormatteDate, id_evenement);
            reservationCRUD RC = new reservationCRUD();
            //RC.modifierRes(id,nomClient,numero,nbpersonne,myFormatteDate,id_evenement);
            RC.modifierRes(id, nomClient, numero, nbpersonne, myFormatteDate, id_evenement);
           
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
            stage.close();
            }
         catch(Exception ex)
                {
                System.out.println("error : "+ex.getMessage());
                }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

   

    
    
}
