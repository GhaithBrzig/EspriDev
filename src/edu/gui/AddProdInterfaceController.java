/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import entities.Produit;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.GestionRemise;
import services.ProduitCRUD;
import services.UserCRUD;
import utils.Mailing;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class AddProdInterfaceController implements Initializable {

    @FXML
    private TextField lib_txt;
    @FXML
    private TextField price_txt;
    @FXML
    private TextField quantity_txt;
    @FXML
    private TextArea Desc_txt;
    @FXML
    private ComboBox<String> Cat_List;

    ProduitCRUD pcr = new ProduitCRUD();

    GestionRemise Gr = new GestionRemise();
    List<Produit> l = Gr.getListB();

    ObservableList<String> items = FXCollections.observableArrayList("Breakfast", "Plat", "Sandiwch", "Pizza", "Boisson", "Autre");
    ObservableList<String> prom = FXCollections.observableArrayList("0", "10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%");
    @FXML
    private ComboBox<String> Remise_cb;
    @FXML
    private Button Image_btn;
    File selectedFile;
    @FXML
    private TableView<Produit> Table_V;
    @FXML
    private TableColumn<Produit, String> Rep_col;
    @FXML
    private TableColumn<Produit, String> desc_col;
    @FXML
    private TableColumn<Produit, String> prix_col;
    @FXML
    private TableColumn<Produit, String> Qte_col;
    @FXML
    private TableColumn<Produit, String> prom_col;
    @FXML
    private TableColumn<Produit, String> cate_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Cat_List.getItems().removeAll(Cat_List.getItems());
        Cat_List.setItems(items);
        Cat_List.getSelectionModel();

        Remise_cb.getItems().removeAll(Remise_cb.getItems());
        Remise_cb.setItems(prom);
        Remise_cb.getSelectionModel();
        
                Rep_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("lib_prod"));
        desc_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        prix_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("prix_prod"));
        prom_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("remise"));
        cate_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        Qte_col.setCellValueFactory(new PropertyValueFactory<Produit, String>("quaniteDispo"));
        Table_V.getItems().setAll(l);

    }

    @FXML
    private void AddProduct(ActionEvent event) throws IOException {
        boolean checkexist = false;

        for(Produit p : l)
        {
            if(p.getLib_prod().equals(lib_txt.getText()))
                checkexist = true;
        }
        if (lib_txt.getText().equals("") || price_txt.getText().equals("") || quantity_txt.getText().equals("") || Desc_txt.getText().equals("") || Cat_List.getValue() == null ) {

            JOptionPane.showMessageDialog(null, "Champ manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);

        } else {
            if(checkexist)
            {
                            JOptionPane.showMessageDialog(null, "Nom deja existe!", "Input error ", JOptionPane.ERROR_MESSAGE);
            lib_txt.setText("");
            }
            else{
            try {
                if (selectedFile != null) {
                    int price = Integer.parseInt(price_txt.getText());
                    int quantity = Integer.parseInt(quantity_txt.getText());
                    String lib = lib_txt.getText();

                    String Description = Desc_txt.getText();
                    String categorie = Cat_List.getValue();

                    //inseretion dans la bd
                    Produit p = new Produit(lib, Description, price, quantity, categorie);

                    if (Remise_cb.getValue() != null) {
                        p.setRemise(Remise_cb.getValue());
                        if (Integer.parseInt(Remise_cb.getValue().replace("%", "")) >= 50) {
                            UserCRUD ucr = new UserCRUD();
                            List<User> lu = ucr.GetProd();
                            System.out.println("test");
                            for (User u : lu) {
                                if (u.getFollow() == 1) {

                                    Mailing.sendMail(u.getEmail(), p.getLib_prod() + "a un remise de " + p.getRemise(), "Remise");
                                }
                            }
                        }
                    }

                    try {
                        Path from;
                        Path to;

                        
                        from = Paths.get(selectedFile.toURI());
                        to = Paths.get("C:\\Users\\Nour\\Documents\\NetBeansProjects\\MyProject\\src\\GUI\\Photos\\" + lib + ".png");
                        Files.copy(from, to);
                        p.setPath(to.toString());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e, "Input error ", JOptionPane.ERROR_MESSAGE);

                    }
                    pcr.addProd(p);
                    quantity_txt.setText("");
                    price_txt.setText("");
                    lib_txt.setText("");
                    Desc_txt.setText("");
                    Cat_List.setValue(null);
                    Image_btn.setText("");
                    refresh();
                    JOptionPane.showMessageDialog(null, "Repa ajouté", "Ajouté ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Selectionner un image!", "Input error ", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Prix et quantite doient étre des nombres!", "Input error ", JOptionPane.ERROR_MESSAGE);
                quantity_txt.setText("");
                price_txt.setText("");
            }

        }

    }
    }

    @FXML
    private void ModifiyProduct(ActionEvent event) 
    {
                boolean checkexist = false;

        for(Produit p : l)
        {
            if(p.getLib_prod().equals(lib_txt.getText()))
                checkexist = true;
        }
        if (lib_txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nom manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);

        } else {
            if(checkexist)
            {
            try {

                String lib = lib_txt.getText();

                String Description = Desc_txt.getText();
                String categorie = Cat_List.getValue();
                Produit p = new Produit(lib, Description, categorie);

                if (!price_txt.getText().equals("") && !quantity_txt.getText().equals("")) {

                    p.setPrix_prod(Integer.parseInt(price_txt.getText()));
                    p.setQuaniteDispo(Integer.parseInt(quantity_txt.getText()));
                }

                if (price_txt.getText().equals("") && !quantity_txt.getText().equals("")) {
                    p.setQuaniteDispo(Integer.parseInt(quantity_txt.getText()));
                }

                if (quantity_txt.getText().equals("") && !price_txt.getText().equals("")) {
                    p.setPrix_prod(Integer.parseInt(price_txt.getText()));
                }

                pcr.updateProd(p);

                if (Cat_List.getValue() != null) {
                    p.setCategorie(Cat_List.getValue());
                    pcr.updatecategorie(p);
                }

                if (Remise_cb.getValue() != null) {
                    p.setRemise(Remise_cb.getValue());
                    pcr.updateremise(p);
                }

                quantity_txt.setText("");
                price_txt.setText("");
                lib_txt.setText("");
                Desc_txt.setText("");
                Cat_List.setValue(null);
                refresh();
                JOptionPane.showMessageDialog(null, "Repa modifié", "Modifié ", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);
                quantity_txt.setText("");
                price_txt.setText("");
            }
            } 
            else
                JOptionPane.showMessageDialog(null, "Nom n'existe pas!", "error", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void RemoveProduct(ActionEvent event
    ) {

        if (lib_txt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nom manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Verification");
                alert.setContentText("êtes-vous sûr ?");
                ButtonType okButton = new ButtonType("Oui ", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);

                alert.getButtonTypes().setAll(okButton, noButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {

                        pcr.deleteProd(lib_txt.getText());
                        quantity_txt.setText("");
                        price_txt.setText("");
                        lib_txt.setText("");
                        Desc_txt.setText("");
                        Cat_List.setValue(null);
                        refresh();
                        JOptionPane.showMessageDialog(null, "Repa Supprimé", "Supprimé ", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                    }
                });

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!", "error ", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

    @FXML
    private void ImageManipulation() {
     

        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null);
        /* fc.setInitialDirectory(new File("C:\\Users\\SBS\\Pictures"));
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        
        if (selectedFile != null) {
            Image_btn.setText(selectedFile.getName());
           from = Paths.get(selectedFile.toURI());
           to = Paths.get("C:\\Users\\SBS\\Music\\" + selectedFile.getName());
           Files.copy(from, to);
            System.out.println(fc.getInitialDirectory());*/
        if (selectedFile != null) {
            Image_btn.setText(selectedFile.getName());
        }

    }

    @FXML
    private void ShowStats(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("JavaFX Chart Demo");
        StackPane pane = new StackPane();
        pane.getChildren().add(createBarChart());

        stage.setScene(new Scene(pane, 400, 200));
        stage.show();
    }

    public ObservableList<XYChart.Series<String, Double>>
            getDummyChartData() {
        ObservableList<XYChart.Series<String, Double>> data
                = FXCollections.observableArrayList();
        XYChart.Series<String, Double> as = new XYChart.Series<>();
        /*XYChart.Series<String, Double> bs = new XYChart.Series<>();
      XYChart.Series<String, Double> cs = new XYChart.Series<>();
      XYChart.Series<String, Double> ds = new XYChart.Series<>();
      XYChart.Series<String, Double> es = new XYChart.Series<>();
      XYChart.Series<String, Double> fs = new XYChart.Series<>();*/
        as.setName("A-Series");
        /*  bs.setName("B-Series");
      cs.setName("C-Series");
      ds.setName("D-Series");
      es.setName("E-Series");
      fs.setName("F-Series");

      Random r = new Random();
      

      for (int i = 1900; i < 2017; i += 10) {

         as.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         bs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         cs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         ds.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         es.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         fs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
      }
      data.addAll(as, bs, cs, ds, es, fs);
      return data;
   }*/

        for (Produit i : l) {

            as.getData().add(new XYChart.Data<>(i.getLib_prod(), Double.valueOf(i.getQuaniteDispo())));
            /* bs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         cs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         ds.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         es.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));
         fs.getData().add(new XYChart.Data<>
         (Integer.toString(i), r.nextDouble()));*/
        }
        //  data.addAll(as, bs, cs, ds, es, fs);
        data.addAll(as);
        return data;
    }

    public XYChart<CategoryAxis, NumberAxis>
            createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart bc = new BarChart<>(xAxis, yAxis);
        bc.setData(getDummyChartData());
        bc.setTitle("Quantites Disponibles");
        return bc;
    }
    
   public void refresh(){
       Gr.ReDefine();
       l = Gr.getListB();
       Table_V.getItems().setAll(l);
   }
}
