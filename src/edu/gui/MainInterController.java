/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.gui;

import edu.entities.Produit;
import edu.entities.ProduitM;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import edu.services.GestionRemise;
import edu.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class MainInterController implements Initializable {

    @FXML
    private TableView<ProduitM> Main_Table;
    @FXML
    private TableColumn<ProduitM, String> Rep_col;
    @FXML
    private TableColumn<ProduitM, String> desc_col;
    @FXML
    private TableColumn<ProduitM, String> prix_col;
    @FXML
    private TableColumn<ProduitM, String> prom_col;
    @FXML
    private TableColumn<ProduitM, String> cate_col;
    @FXML
    private ComboBox<String> categ_cb;
    @FXML
    private ComboBox<String> sort_cb;

    ObservableList<String> categories = FXCollections.observableArrayList("Breakfast", "Plat", "Sandiwch", "Pizza", "Boisson", "Autre", "Tout");
    ObservableList<String> sortitems = FXCollections.observableArrayList("Prix", "Promotions", "Nom");

    GestionRemise Gr = new GestionRemise();
    List<ProduitM> l = Gr.getListB();
    List<ProduitM> lb = Gr.getListA();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        categ_cb.getItems().removeAll(categ_cb.getItems());
        categ_cb.setItems(categories);
        categ_cb.getSelectionModel();

        sort_cb.getItems().removeAll(sort_cb.getItems());
        sort_cb.setItems(sortitems);
        sort_cb.getSelectionModel();

        Gr.taxPerProd(10);

        Rep_col.setCellValueFactory(new PropertyValueFactory<ProduitM, String>("lib_prod"));
        desc_col.setCellValueFactory(new PropertyValueFactory<ProduitM, String>("description"));
        prix_col.setCellValueFactory(new PropertyValueFactory<ProduitM, String>("prix_prod"));
        prom_col.setCellValueFactory(new PropertyValueFactory<ProduitM, String>("remise"));
        cate_col.setCellValueFactory(new PropertyValueFactory<ProduitM, String>("categorie"));
        Main_Table.getItems().setAll(l);

   

        // Image_col.setCellFactory((Callback<TableColumn<Images, ImageView>, TableCell<Images, ImageView>>) Im.getProd_images());
    }

    @FXML
    private void groupBy(ActionEvent event) {
        List<ProduitM> l2 = new ArrayList();
        if (categ_cb.getValue().equals("Tout")) {
            Main_Table.getItems().setAll(l);
        }
        else{
        for (ProduitM p : l) {
            if (p.getCategorie().equals(categ_cb.getValue())) {
                l2.add(p);
            }
        }
        Main_Table.getItems().setAll(l2);}
    }

    @FXML
    private void sortBy(ActionEvent event) {
        TreeSet<ProduitM> ts = null;
        if (sort_cb.getValue().equals("Nom")) {
            ts = l.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getLib_prod().compareTo(b.getLib_prod()))));
        } else if (sort_cb.getValue().equals("Prix")) {
            ts = l.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> (int) a.getPrix_prod() - (int) b.getPrix_prod())));
        } else {
            ts = l.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getRemise().compareTo(b.getRemise()))));
        }
        Main_Table.getItems().setAll(ts);
    }

}
