/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Livreur;
import edu.services.LivreurCRUD;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author islemferchichi
 */
public class GestionlivreurController implements Initializable {
    private String actiontype;
    @FXML
    private AnchorPane TableLivreur;
    @FXML
    private TableView<Object> tabLiv;
    @FXML
    private TableColumn<Livreur, Integer> ColID;
    @FXML
    private TableColumn<Livreur, String> ColNom;
    @FXML
    private TableColumn<Livreur, String> colPrenom;
    @FXML
    private TableColumn<Livreur, Integer> colTel;
    @FXML
    private Button btnADD;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button suppbtn;
    @FXML
    private Pane formpane;
    @FXML
    private Button savebtn;
    @FXML
    private TextField idinput;
    @FXML
    private TextField nominput;
    @FXML
    private TextField prenominput;
    @FXML
    private TextField tellinput;
    @FXML
    private Label erreur;
    @FXML
    private TextField recherche;
    @FXML
    private Button gestionlivraison;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        ObservableList<Object> listdata = FXCollections.observableArrayList();
        ColID.setCellValueFactory(new PropertyValueFactory<>("IdLivreur"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("NomLivreur"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomLivreur"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telLivreur"));
        LivreurCRUD lc = new LivreurCRUD();
        listdata.addAll(lc.DisplayLivreur());
        tabLiv.setItems(listdata);
        modifierbtn.setDisable(true);
        suppbtn.setDisable(true);
        ObservableList selectedCells = tabLiv.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                Livreur livselected = (Livreur) tabLiv.getSelectionModel().getSelectedItem();
                if(livselected!=null){
                       modifierbtn.setDisable(false);
        suppbtn.setDisable(false);}
                
                else{
           modifierbtn.setDisable(true);
        suppbtn.setDisable(true);
                }
            }
           
        });
    
         
    }    

    @FXML
    private void ADDLivreur(ActionEvent event) {
        formpane.setOpacity(1);
        actiontype="add";
        idinput.setText("");
idinput.setDisable(false);
nominput.setText("");
prenominput.setText("");
tellinput.setText("");
        
    }

    private void refreshLivreur() {
      ObservableList<Object> listdata = FXCollections.observableArrayList();
        ColID.setCellValueFactory(new PropertyValueFactory<>("IdLivreur"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("NomLivreur"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomLivreur"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telLivreur"));
        LivreurCRUD lc = new LivreurCRUD();
        listdata.addAll(lc.DisplayLivreur());
        tabLiv.setItems(listdata);
              modifierbtn.setDisable(true);
        suppbtn.setDisable(true);

    }

    @FXML
    private void modifierLivreur(ActionEvent event) {
         formpane.setOpacity(1);
        actiontype="update";
            Livreur livselected = (Livreur) tabLiv.getSelectionModel().getSelectedItem();
idinput.setText(String.valueOf(livselected.getIdLivreur()));
idinput.setDisable(true);
nominput.setText(livselected.getNomLivreur());
prenominput.setText(livselected.getPrenomLivreur());
tellinput.setText(String.valueOf(livselected.getTelLivreur()));


    }

    @FXML
    private void supprimerlivreur(ActionEvent event) {
    Livreur livselected = (Livreur) tabLiv.getSelectionModel().getSelectedItem();
        LivreurCRUD lc = new LivreurCRUD();
     lc.deleteLivreur(livselected.getIdLivreur());
     refreshLivreur();
    }

    @FXML
    private void save(ActionEvent event) {
        if(idinput.getText().length()==0||nominput.getText().length()==0||prenominput.getText().length()==0||tellinput.getText().length()==0)
        {
        erreur.setText("veillez remplir le champ");
        }else{
        erreur.setText("");
        Livreur l = new Livreur(Integer.valueOf(idinput.getText()),nominput.getText(),prenominput.getText(),Integer.valueOf(tellinput.getText()));
                LivreurCRUD lc = new LivreurCRUD();

        if(actiontype.compareTo("add")==0){
        lc.addLivreur(l);
        }else{
            lc.updateLivreur(l);
        
        }
        refreshLivreur();

        
        
        }
    }

    @FXML
    private void chercher(KeyEvent event) {
        String mot = recherche.getText();
         ObservableList<Object> listdata = FXCollections.observableArrayList();
        ColID.setCellValueFactory(new PropertyValueFactory<>("IdLivreur"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("NomLivreur"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomLivreur"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telLivreur"));
        LivreurCRUD lc = new LivreurCRUD();
        listdata.addAll(lc.DisplayLivreur().stream()
                .filter(liv->liv.getNomLivreur().contains(mot)||liv.getPrenomLivreur().contains(mot)).collect(Collectors.toList()));
               tabLiv.setItems(listdata);

    }

    @FXML
    private void navigate(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionLivraison.fxml"));
            Parent root = loader.load();
            GestionLivraisonController dpc = loader.getController();

            gestionlivraison.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionlivreurController.class.getName()).log(Level.SEVERE, null, ex);
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

         
       
