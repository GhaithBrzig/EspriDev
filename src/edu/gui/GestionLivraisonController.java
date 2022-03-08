/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Livraison;
import edu.entities.Livreur;
import edu.services.LivraisonCRUD;
import edu.services.LivreurCRUD;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JTable;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author islemferchichi
 */
public class GestionLivraisonController implements Initializable {

    String action;
    @FXML
    private TableView<Object> tabLivraison;
    @FXML
    private TableColumn<Livraison, Integer> idlivraison;
    @FXML
    private TableColumn<Livraison, Double> prixLiv;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Pane formliv;
    @FXML
    private Button backbtn;
    @FXML
    private TextField idinput;
    @FXML
    private ChoiceBox<Livreur> idliv;
    @FXML
    private TextField pricliv;
    @FXML
    private Button save;
    @FXML
    private Label erreur;
    @FXML
    private Button imprimerliv;
    @FXML
    private TableColumn<Livraison, Integer> idLivreur;

    /**
     * Initializes the controller class.
     */
    LivraisonCRUD ll = new LivraisonCRUD();
    ObservableList<Livreur> Listdata = FXCollections.observableArrayList();
    ObservableList<Object> listdata = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> id_commande;
    @FXML
    private TableColumn<Livraison, Integer> idcmd;
    @FXML
    private TableColumn<Livraison, Double> totcmd;
    @FXML
    private Button backbtn1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ObservableList<Integer> Listidcommande = FXCollections.observableArrayList(ll.DisplayCommandeId());

        formliv.setOpacity(0);
        LivraisonCRUD l = new LivraisonCRUD();
        for (Livreur lv : l.DisplayLivraison2()) {
            Listdata.add(lv);
        }
        idliv.setItems(Listdata);
        System.out.print(Listdata);
        id_commande.setItems(Listidcommande);
        // pour tabiew naccepte ke les  observableliste
        
        idlivraison.setCellValueFactory(new PropertyValueFactory<>("IdLivraison"));
        idLivreur.setCellValueFactory(new PropertyValueFactory<>("IdLivreur"));
        prixLiv.setCellValueFactory(new PropertyValueFactory<>("FraisdeLivraison"));
        idcmd.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        totcmd.setCellValueFactory(new PropertyValueFactory<>("total"));
        LivraisonCRUD lc = new LivraisonCRUD();
        List<Livraison> lv = lc.DisplayLivraison();
        listdata.addAll(lv);
        System.out.print(listdata);
        tabLivraison.setItems(listdata);
        edit.setDisable(true);
        delete.setDisable(true);
        ObservableList selectedCells = tabLivraison.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Livraison livselected = (Livraison) tabLivraison.getSelectionModel().getSelectedItem();
                if (livselected != null) {
                    edit.setDisable(false);
                    delete.setDisable(false);
                } else {
                    edit.setDisable(true);
                    delete.setDisable(true);
                }
            }

        });
    }

    public void refreshLivraison() {
        ObservableList<Object> listdata = FXCollections.observableArrayList();
        idlivraison.setCellValueFactory(new PropertyValueFactory<>("IdLivraison"));
        idLivreur.setCellValueFactory(new PropertyValueFactory<>("IdLivreur"));
        prixLiv.setCellValueFactory(new PropertyValueFactory<>("FraisdeLivraison"));
        idcmd.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        totcmd.setCellValueFactory(new PropertyValueFactory<>("total"));
        LivraisonCRUD lc = new LivraisonCRUD();
        listdata.addAll(lc.DisplayLivraison());
        tabLivraison.setItems(listdata);

    }

    @FXML
    private void backtogestionLiv(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestionlivreur.fxml"));
            Parent root = loader.load();
            GestionlivreurController dpc = loader.getController();

            backbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void addLiv(ActionEvent event) {
        formliv.setOpacity(1);
        action = "add";
        idinput.setText("");
        pricliv.setText("");
        //idliv.setText("");

    }

    @FXML

    private void editLiv(ActionEvent event) {
        Livraison livselected = (Livraison) tabLivraison.getSelectionModel().getSelectedItem();
        formliv.setOpacity(1);
        action = "Edit";
        idinput.setText(String.valueOf(livselected.getIdLivraison()));
        pricliv.setText(String.valueOf(livselected.getFraisdeLivraison()));
        idliv.setValue(idliv.getValue());
        idinput.setDisable(true);

    }

    @FXML
    private void DeleteLiv(ActionEvent event) {
        Livraison livselected = (Livraison) tabLivraison.getSelectionModel().getSelectedItem();
        LivraisonCRUD lc = new LivraisonCRUD();
        lc.deleteLivraison(livselected.getIdLivraison());
        refreshLivraison();

    }

    @FXML
    private void savebutton(ActionEvent event) {
        if (idinput.getText().compareTo("") == 0 || pricliv.getText().compareTo("") == 0 || idliv.getValue() == null || id_commande.getValue()==null ) {
            erreur.setText("erreur");
        } //si les inputs far8in affichi erreur
        else {
            erreur.setText("");

            LivraisonCRUD lc = new LivraisonCRUD();

            //si l'action est ajouter 
            if (action.compareTo("add") == 0) {
                Livraison l = new Livraison(Integer.valueOf(idinput.getText()), Integer.valueOf(idliv.getValue().getIdLivreur()), Double.valueOf(pricliv.getText()),id_commande.getValue());
                lc.addLivraison(l);
            } else {
                //si l'action est modification
                Livraison livraisonselected = (Livraison) tabLivraison.getSelectionModel().getSelectedItem(); //livraison selected from tableview
                livraisonselected.setFraisdeLivraison(Double.valueOf(pricliv.getText())); //modifier 
                livraisonselected.setIdLivreur(Integer.valueOf(idliv.getValue().getIdLivreur())); //modifier
              livraisonselected.setId_commande(Integer.valueOf(id_commande.getValue())); //modifier

                lc.updateLivraison(livraisonselected);

            }
            refreshLivraison();
            idinput.setDisable(false);
            idinput.clear();

        }

    }

    @FXML
    private void printtliv(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tabLivraison;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }

            refreshLivraison();
        }
    }


/*{Scanner clavier=new Scanner(System.in);
        double HT=0,tva=0,r=0,netc=0,ttc=0;
        System.out.println("entrer N");
           int n=clavier.nextInt();
           for(int i=0;i<n;i++){
            System.out.println("Entrer prix");
            double prix=clavier.nextDouble();
            HT+=prix;}
        if(HT>100){
        r=HT*0.1; 
        netc=HT-r;
        }
        tva=netc*0.2;
        ttc=netc+tva;
        
       
    System.out.println("le montant est" +HT+"dinars");
    System.out.println("La remise 1% est"+r+"dinars");
    System.out.println("net commercial est"+netc+"dinars");
    System.out.println("TVA est"+tva+"dinars");*/

//}}}}
/*
public List<Livraison> Remise(List<Livraison> lv){
    
 for(Livraison i : lv)   
 {
     if(i.getFraisdeLivraison() > 100)
     {
         i.setFraisdeLivraison(0);
     }
 }
        return lv;
}
*/

    @FXML
    private void back(ActionEvent event) throws IOException {
        backbtn1.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AdminMenu.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }
}