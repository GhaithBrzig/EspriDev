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
import javax.swing.JOptionPane;

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
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_deconnecter;



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
         btn_reclamation.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ReclamationsAdmin.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void goToUser(ActionEvent event) throws IOException {
         btn_user.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("List.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
         int opt = JOptionPane.showConfirmDialog(null, "Vous êtes sur?", "Supprimer", JOptionPane.YES_NO_OPTION);
    if(opt==0){
        btn_deconnecter.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                 Stage mainStage = new Stage();
                 Scene scene = new Scene(root);
                 mainStage.setScene(scene);
                 mainStage.show();
    }
    }
    
}
