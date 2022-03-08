/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class AdminMenuController implements Initializable {

    @FXML
    private Button btn_addProd;
    @FXML
    private Button btn_stock;
    @FXML
    private Button btn_livraison;
    @FXML
    private Button btn_reservation;
    @FXML
    private Button btn_reclamation;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToAddProd(ActionEvent event) throws IOException {
        btn_addProd.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AddProdInterface.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void goToStock(ActionEvent event) throws IOException {
        btn_stock.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AjouterProduit.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void goToLivraison(ActionEvent event) throws IOException {
          btn_livraison.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("GestionLivraison.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void goToReservation(ActionEvent event) throws IOException {
         btn_reservation.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("menu.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void goToReclamation(ActionEvent event) throws IOException {
         btn_reservation.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ReclamationsAdmin.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }
    
}
