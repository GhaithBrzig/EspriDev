/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Produit;
import edu.entities.Reclamation;
import edu.services.ProduitService;
import edu.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class ReclamationsAdminController implements Initializable {

    ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Reclamation, Integer> RId;
    @FXML
    private TableColumn<Reclamation, String> RNomClient;
    @FXML
    private TableColumn<Reclamation, String> Rmail;
    @FXML
    private TableColumn<Reclamation, String> Rdescription;
    @FXML
    private TableColumn<Reclamation, Date> Rdate;
    @FXML
    private TableColumn<Reclamation, Boolean> Rsolved;
    @FXML
    private TableView<Reclamation> ReclamationView;

    Reclamation reclamation = null;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        LoadData();
    }

    public void initiateCols() {
        RId.setCellValueFactory(new PropertyValueFactory<>("id"));
        RNomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        Rmail.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
        Rdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        Rdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Rsolved.setCellValueFactory(new PropertyValueFactory<>("isSolved"));
    }

    public void LoadData() {
        reclamationList.removeAll(reclamationList);
        ReclamationService rsv = new ReclamationService();
        for (Reclamation m : rsv.DisplayReclamations()) {
            reclamationList.add(m);
        }
        ReclamationView.getItems().addAll(reclamationList);

    }

    @FXML
    private void delete(ActionEvent event) {
        if (ReclamationView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un reclamation a supprimer !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ReclamationService rsv = new ReclamationService();
                rsv.deleteReclamation(ReclamationView.getSelectionModel().getSelectedItem().getId());
                ReclamationView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    @FXML
    private void Solved(ActionEvent event) {
        if (ReclamationView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un reclamation a modifier !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                reclamation = ReclamationView.getSelectionModel().getSelectedItem();
                Reclamation r = new Reclamation(reclamation.getId(), reclamation.getIsSolved());

                ReclamationService rsv = new ReclamationService();
                rsv.updateReclamation(r);
                ReclamationView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    @FXML
    private void Close(ActionEvent event) throws IOException {
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
