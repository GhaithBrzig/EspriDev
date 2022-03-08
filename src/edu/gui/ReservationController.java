/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.reservation;
import edu.services.evenementCRUD;
import edu.services.reservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField ResNom;
    @FXML
    private TextField ResNum;
    @FXML
    private TextField ResNbp;
    @FXML
    private DatePicker ResDate;
    @FXML
    private ComboBox<Integer> ResIDev;
    @FXML
    private Button AjouterRes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evenementCRUD CR = new evenementCRUD();
        ObservableList<Integer> listID = CR.ListId();
        ResIDev.setItems(listID);
    }

    @FXML
    private void saveReservation(ActionEvent event) {

        String nomClient = ResNom.getText();

        java.sql.Date date = java.sql.Date.valueOf(ResDate.getValue());

        //System.out.println(id_evenement);
        System.out.println("nessrine");
        
       
        try {

             try {
                
                
                
                  
            } catch (RuntimeException e) {
                ResNum.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                new animatefx.animation.Shake(ResNum).play();

            }
             
            try {
                int numero1 = Integer.parseInt(ResNum.getText());
            } catch (RuntimeException e) {
                ResNum.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                new animatefx.animation.Shake(ResNum).play();

            }
            try {
                int nbpersonne1 = Integer.parseInt(ResNbp.getText());
            } catch (RuntimeException e) {
                //JOptionPane.showMessageDialog(null, "Prix et quantite doient étre des nombres!", "Input error ", JOptionPane.ERROR_MESSAGE);
                ResNbp.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                new animatefx.animation.Shake(ResNbp).play();

            }

            if ((nomClient.isEmpty())) {
                if ((ResNom.getText().length() == 0) ) {
                    ResNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                    new animatefx.animation.Shake(ResNom).play();

                } else {
                    ResNom.setStyle(null);
                }
              

                if ((ResNum.getText().length() != 0)) {
                    try {
                        int number1 = Integer.parseInt(ResNum.getText());
                        ResNum.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");

                    } catch (RuntimeException e) {
                        //JOptionPane.showMessageDialog(null, "Prix et quantite doient étre des nombres!", "Input error ", JOptionPane.ERROR_MESSAGE);
                        ResNum.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;-fx-border-radius: 15px;-fx-background-radius: 15px;");
                        new animatefx.animation.Shake(ResNum).play();
                        // labelnumber.setText("Number is wrong"); 
                    }
                } else {
                    ResNum.setStyle(null);
                }
            } else {
                int numero = Integer.parseInt(ResNum.getText());
                int nbpersonne = Integer.parseInt(ResNbp.getText());
                int id_evenement = ResIDev.getSelectionModel().getSelectedItem();
                reservation res = new reservation(nomClient, numero, nbpersonne, date, id_evenement);
                reservationCRUD rc = new reservationCRUD();
                rc.ajouterReservation2(res);

                
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
            }
        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

}
