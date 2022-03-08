/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.entities.evenement;
import edu.services.evenementCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class DisplayEvenementController implements Initializable {

    @FXML
    private TextField EvID;
    @FXML
    private TextField EvType;
    @FXML
    private TextField descEV;
    @FXML
    private TextField EVnbP;
    @FXML
    private DatePicker dateEv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(int id, String description , String type, int nbpersonne, Date daate) {
        EvType .setText("" + type);
        descEV.setText("" + description);
         LocalDate lDate = daate.toLocalDate();
        dateEv.setValue(lDate);
        EVnbP.setText(""+nbpersonne);
        
        EvID.setText("" + id);
    }

    @FXML
    private void termine(ActionEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
            stage.close();

    }

    @FXML
    private void supprimerEv(ActionEvent event) {
         String id = EvID.getText();
        String type = EvType.getText();
        String description = descEV.getText();
        String nbpersonne = EVnbP.getText();
      
        //int date= date.getDate();
        
        
         try{
       
            
            if ((type.isEmpty()) || (description.isEmpty())||(nbpersonne.isEmpty())) {
            if (EvType.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
               EvType.setStyle(null);
                
            }
            if (descEV.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
                descEV.setStyle(null);
            }
             if (EVnbP.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("le champs ne doit pas etre vide ");
                    alert.showAndWait();
            } else {
                EVnbP.setStyle(null);
            }
             
            
            
            }else{
                //evenement ev = new evenement(type,description,nbpersonne,date);
            evenementCRUD EC = new evenementCRUD();
            EC.supprimerEvenement(id);            
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
        
         String id = EvID.getText();
        String nomClient =EvType .getText();
        String numero = descEV.getText();
        String nbpersonne = EVnbP.getText();
       
        //Date dateRes = date.get;
        LocalDate dateRes = dateEv.getValue();
        String myFormatteDate = dateRes.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
     /*  String date = request.getParameter("date");
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
java.util.Date dateStr = formatter.parse(sqlBirthday );
java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());*/
         try{
       
      evenement ev = new evenement(id, numero, nbpersonne, myFormatteDate);
            evenementCRUD RC = new evenementCRUD();
            //RC.modifierRes(id,nomClient,numero,nbpersonne,myFormatteDate,id_evenement);
            RC.modifierEV(id, nomClient, numero, nbpersonne, myFormatteDate);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           
            stage.close();
            }
         catch(Exception ex)
                {
                System.out.println("error : "+ex.getMessage());
                }
        
        
    }
    
}
