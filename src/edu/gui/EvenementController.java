/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.evenement;
import edu.services.evenementCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class EvenementController implements Initializable {

    @FXML
    private TableView<evenement> tableEv;
    @FXML
    private Button refreshE;
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<evenement, Integer> idE;
    @FXML
    private TableColumn<evenement, String> typeE;
    @FXML
    private TableColumn<evenement, String> desE;
    @FXML
    private TableColumn<evenement, Integer> Enp;
    @FXML
    private TableColumn<evenement, String> date;
    ObservableList<evenement> EvList = FXCollections.observableArrayList();
    @FXML
    private TextField rechercheTest;
    @FXML
    private Button serachbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    @FXML

    private void Refresh() {
        EvList.clear();
        evenementCRUD EC = new evenementCRUD();
        EvList.addAll(EC.afficheEvenement());
        EC.afficheEvenement();

        tableEv.setItems(EvList);
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ajouterEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadDate() {
        Refresh();
        idE.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeE.setCellValueFactory(new PropertyValueFactory<>("type"));
        desE.setCellValueFactory(new PropertyValueFactory<>("description"));
        Enp.setCellValueFactory(new PropertyValueFactory<>("nbpersonne"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableEv.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("displayEvenement.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    // ex.printStackTrace();

                    System.out.println("error : " + ex.getMessage());;
                }
                DisplayEvenementController Edc = Loader.getController();
                Edc.setData(tableEv.getSelectionModel().getSelectedItem().getId(),
                         "" + tableEv.getSelectionModel().getSelectedItem().getType(),
                         "" + tableEv.getSelectionModel().getSelectedItem().getDescription(),
                         tableEv.getSelectionModel().getSelectedItem().getNbpersonne(),
                         tableEv.getSelectionModel().getSelectedItem().getDate());

                Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            }

        });

    }

    @FXML
    private void recherche(ActionEvent event) {

        String searchEV = rechercheTest.getText();
        evenementCRUD EC = new evenementCRUD();

        EvList.clear();

        EvList.addAll(EC.SearchEV(searchEV));

        //tableEv.setItems(EvList);

    }

}
