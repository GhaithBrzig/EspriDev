/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class Panier2Controller implements Initializable {

    @FXML
    private Button panierID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void panierButton(ActionEvent event) {
         try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("InterfaceMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
           stage.initStyle(StageStyle.UTILITY);
            stage.show();
              
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
