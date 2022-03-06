/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.StockCategory;

import edu.services.StockCategoryService;
import edu.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class InterfaceCategorieController implements Initializable {

    ObservableList<StockCategory> CategoryList = FXCollections.observableArrayList();
    @FXML
    private TextField CName;
    @FXML
    private TableColumn<StockCategory, Integer> ColId;
    @FXML
    private TableColumn<StockCategory, String> ColNom;
    @FXML
    private TableView<StockCategory> CategoryView;
    
     StockCategory categorie = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateCols();
        LoadData();
    }

    public void initiateCols() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    public void LoadData() {
        CategoryList.removeAll(CategoryList);
        StockCategoryService csv = new StockCategoryService();

        for (StockCategory s : csv.DisplayStockCategories()) {
            CategoryList.add(s);
        }
        CategoryView.getItems().addAll(CategoryList);
    }

    @FXML
    private void Save(ActionEvent event) {
        if (CName.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Inserer un nom !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String nom = CName.getText();

                StockCategory c = new StockCategory(nom);

                StockCategoryService csv = new StockCategoryService();
                csv.addStockCategory(c);
                CategoryView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (RuntimeException e) {
                Logger.getLogger(InterfaceCategorieController.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (CategoryView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un categorie a supprimer !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                StockCategoryService csv = new StockCategoryService();
                csv.deleteStockCategory(CategoryView.getSelectionModel().getSelectedItem().getId());
                CategoryView.getItems().clear();
                initiateCols();
                LoadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    @FXML
    private void update(ActionEvent event) {
         if (CategoryView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un categorie a modifier !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            categorie = CategoryView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifierCategorie.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ModifierCategorieController modifierCategorieController = loader.getController();
            modifierCategorieController.setTextField(categorie.getId(), categorie.getNom());

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            CategoryView.getItems().clear();
                initiateCols();
                LoadData();
            
        }
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
