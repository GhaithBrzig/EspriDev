/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Produit;
import edu.entities.StockCategory;
import edu.services.ProduitService;
import edu.services.StockCategoryService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class ModifierProduitController implements Initializable {

    ObservableList<StockCategory> CategoryList = FXCollections.observableArrayList();

    @FXML
    TextField PName;
    @FXML
    TextField PUnite;
    @FXML
    TextField PQuantite;
    @FXML
    TextField PPrixUnitaire;
    @FXML
    ChoiceBox<StockCategory> Categorie;
    int produitId;
    URL url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
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
        // TODO
    }

    void setTextField(int id, String name, String Unite, int qte, StockCategory ca, double Prix) {

        produitId = id;
        PName.setText(name);
        PUnite.setText(Unite);
        PQuantite.setText(Integer.toString(qte));
        Categorie.setValue(ca);
        PPrixUnitaire.setText(String.valueOf(Prix));

    }

    @FXML
    private void Enregistrer(ActionEvent event) {
        if (PName.getText().equals("") || PUnite.getText().equals("") || PQuantite.getText().equals("") || PPrixUnitaire.getText().equals("") || Categorie.getValue() == null) {

            JOptionPane.showMessageDialog(null, "Champ manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.url);
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            stage.close();
        } else {
            try {
                String nom = PName.getText();
                int id_categorie;
                id_categorie = Categorie.getValue().getId();
                String unite = PUnite.getText();
                int qte = Integer.parseInt(PQuantite.getText());
                String categorie = Categorie.getValue().getNom();
                Double prix_unitaire = Double.parseDouble(PPrixUnitaire.getText());

                Produit p = new Produit(produitId, id_categorie, nom, unite, qte, categorie, prix_unitaire);

                ProduitService psv = new ProduitService();
                psv.updateProduit(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                JOptionPane.showMessageDialog(null, "produit modifié", "Modifié ", JOptionPane.INFORMATION_MESSAGE);

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Prix et quantite doivent étre des nombres!", "Input error ", JOptionPane.ERROR_MESSAGE);
                PQuantite.setText("");
                PPrixUnitaire.setText("");
            }

        }
    }

    @FXML
    private void Effacer(ActionEvent event) {
        PName.setText("");
        PUnite.setText("");
        PQuantite.setText("");
        Categorie.setValue(null);
        PPrixUnitaire.setText("");
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
