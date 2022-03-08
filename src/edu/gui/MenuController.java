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
 * @author Nesrine
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MenuRes(ActionEvent event) {
          try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
           stage.initStyle(StageStyle.UTILITY);
            stage.show();
              
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void menuEv(ActionEvent event) {
          try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("evenement.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
           stage.initStyle(StageStyle.UTILITY);
            stage.show();
              
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stats(ActionEvent event) {
         try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
           stage.initStyle(StageStyle.UTILITY);
            stage.show();
              
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AdminMenu.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }
    
}
