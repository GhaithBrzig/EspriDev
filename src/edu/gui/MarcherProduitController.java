/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class MarcherProduitController implements Initializable {

    @FXML
    private Button btnReche;
    @FXML
    private ChoiceBox<?> boxCat;
    @FXML
    private VBox chosenprodPanier;
    @FXML
    private Label idnamelabel;
    @FXML
    private ImageView imgV;
    @FXML
    private Label idpricelabel;
    @FXML
    private Label idquantite;
    @FXML
    private Button btnmoin;
    @FXML
    private Label lblqte;
    @FXML
    private Button btnplus;
    @FXML
    private Button btnaddcard;
    @FXML
    private Button btnAffich;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RechercheProduit(ActionEvent event) {
    }

    @FXML
    private void decrementQte(ActionEvent event) {
    }

    @FXML
    private void incrementQte(ActionEvent event) {
    }

    @FXML
    private void AjouterAuPanier(ActionEvent event) {
    }

    @FXML
    private void AfficherProduits(ActionEvent event) {
    }

    @FXML
    private void RechercheAvance(ActionEvent event) {
    }

    @FXML
    private void GoToPanier(ActionEvent event) {
    }
    
}
