/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.entities.evenement;
import edu.services.evenementCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField AjEV;
    @FXML
    private TextField nbpEV;
    @FXML
    private DatePicker dateEV;
    @FXML
    private TextField desEV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterEV(ActionEvent event) {
         String type = AjEV.getText();
        String description=desEV.getText();
        
        // java.sql.Date dateEV = java.sql.Date.valueOf(date.getValue());
        java.sql.Date date=java.sql.Date.valueOf(dateEV.getValue());
     
        try {

            
             
            try {
                int nbpersonne1 = Integer.parseInt(nbpEV.getText());
            } catch (RuntimeException e) {
                nbpEV.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                new animatefx.animation.Shake(nbpEV).play();

            }
            

            if ((type.isEmpty())&&(description.isEmpty())) {
                if ((AjEV.getText().length() == 0) ) {
                    AjEV.setStyle("-fx-border-color: red ; -fx-border-width: 2px;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                    new animatefx.animation.Shake(AjEV).play();

                } else {
                    AjEV.setStyle(null);
                }
              

                if ((desEV.getText().length() == 0)) {
                      desEV.setStyle("-fx-border-color: red ; -fx-border-width: 2px;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                    new animatefx.animation.Shake(desEV).play();

                    
                } else {
                    desEV.setStyle(null);
                }
                
            } else {
                int nbpersonne=Integer.parseInt(nbpEV.getText());
                evenement  ev = new evenement(type, description, nbpersonne, date);
                evenementCRUD ec = new evenementCRUD();
                ec.ajouterEvenement2(ev);
             

       
         try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("evenement.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage() ;
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
        
        
    }
            } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    
    }}
