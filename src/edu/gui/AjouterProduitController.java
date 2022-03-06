/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import com.mysql.jdbc.Blob;
import edu.dao.StockCategoryCRUD;
import edu.entities.Produit;
import edu.entities.StockCategory;
import edu.services.ProduitService;
import edu.services.StockCategoryService;
import edu.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import static javax.management.Query.value;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class AjouterProduitController implements Initializable {

    ObservableList<StockCategory> CategoryList = FXCollections.observableArrayList();
    ObservableList<Produit> produitsList = FXCollections.observableArrayList();

    @FXML
    private TextField PName;
    @FXML
    private TextField PUnite;
    @FXML
    private TextField PQuantite;
    @FXML
    private TextField PPrixUnitaire;
    @FXML
    private ChoiceBox<StockCategory> Categorie;
    @FXML
    private TableColumn<Produit, String> ColNom;
    @FXML
    private TableColumn<Produit, String> ColUnite;
    @FXML
    private TableColumn<Produit, Integer> ColQuantite;
    @FXML
    private TableColumn<Produit, String> ColCategorie;
    @FXML
    private TableColumn<Produit, Double> ColPrix;
    @FXML
     TableView<Produit> StockView;
    @FXML
    private TableColumn<Produit, Double> ColPrixT;
    @FXML
    private TableColumn<Produit, Integer> ColId;

    Produit produit = null;
    String query = null;
    ResultSet resultSet = null;
    StockCategory c = new StockCategory();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StockCategoryService csv = new StockCategoryService();
        for (StockCategory s : csv.DisplayStockCategories()) {
            CategoryList.add(s);
        }
        Categorie.setItems(CategoryList);
        Categorie.setConverter(new StringConverter<StockCategory>() {
            @Override
            public String toString(StockCategory object) {
                return object.getNom();
            }

            @Override
            public StockCategory fromString(String string) {
                return null;
            }
        });
        initiateCols();
        LoadData();
    }

    public void initiateCols() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColUnite.setCellValueFactory(new PropertyValueFactory<>("unite"));
        ColQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ColCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ColPrix.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        ColPrixT.setCellValueFactory(new PropertyValueFactory<>("prix_total"));

    }

    @FXML
    private void Btnajout(ActionEvent event) {
        if (PName.getText().equals("") || PUnite.getText().equals("") || PQuantite.getText().equals("") || PPrixUnitaire.getText().equals("") || Categorie.getValue() == null) {

            JOptionPane.showMessageDialog(null, "Champ manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String nom = PName.getText();
                int id_categorie;
                id_categorie = Categorie.getValue().getId();
                String unite = PUnite.getText();
                int qte = Integer.parseInt(PQuantite.getText());
                String categorie = Categorie.getValue().getNom();
                Double prix_unitaire = Double.parseDouble(PPrixUnitaire.getText());

                Produit p = new Produit(id_categorie, nom, unite, qte, categorie, prix_unitaire);

                ProduitService psv = new ProduitService();
                psv.addProduit(p);
                StockView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Prix et quantite doivent étre des nombres!", "Input error ", JOptionPane.ERROR_MESSAGE);
                PQuantite.setText("");
                PPrixUnitaire.setText("");
            }

        }
    }

    public void LoadData() {
        produitsList.removeAll(produitsList);
        ProduitService psv = new ProduitService();
        for (Produit e : psv.DisplayStock()) {
            produitsList.add(e);
        }
        StockView.getItems().addAll(produitsList);
    }

    @FXML
    private void DeleteRow(ActionEvent event) {
        if (StockView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un produit a supprimer !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ProduitService psv = new ProduitService();
                psv.deleteProduit(StockView.getSelectionModel().getSelectedItem().getId());
                StockView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Update(ActionEvent event) {

        if (StockView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un produit a modifier !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            produit = StockView.getSelectionModel().getSelectedItem();
            int catId = produit.getId_categorie();
            try {
                query = "SELECT * FROM stockCategory where id = ? ";
                PreparedStatement pst = (PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(query);
                pst.setInt(1, catId);
                resultSet = pst.executeQuery();
                while (resultSet.next()) {

                    c.setId(resultSet.getInt(1));
                    c.setNom(resultSet.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifierProduit.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ModifierProduitController modifierProduitController = loader.getController();
            modifierProduitController.setTextField(produit.getId(), produit.getNom(),
                    produit.getUnite(), produit.getQuantite(), c, produit.getPrix_unitaire());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            StockView.getItems().clear();
                initiateCols();
                LoadData();
            
        }
    }
}
