/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import Entity.Commande;
import Entity.Panier;
import Entity.Produit;
import Entity.Utilisateur;
import Interfaces.commandeInterface;
import Interfaces.panierInterface;
import Interfaces.produitInterface;
import Services.CommandeService;
import Services.MailerService;
import Services.PanierService;
import Services.ProduitCRUD;
import Services.ProduitService;
import animatefx.animation.ZoomIn;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author broum
 */
public class InterfaceMenuController implements Initializable {

    @FXML
    private VBox vboxElement;

    private Parent fxml;

    @FXML
    private GridPane grid;
    private List<Produit> produits = new ArrayList<>();
    private List<Panier> paniers = new ArrayList<>();
    private List<Commande> commandes = new ArrayList<>();

    private produitInterface myListener;
    private panierInterface panierInterface;
    private commandeInterface commandeInterface;

    @FXML
    private AnchorPane interfacePlats;

    private AnchorPane panierMenu;
    @FXML
    private ImageView ImageProduit;
    @FXML
    private Label PromotionProduit;
    @FXML
    private Label NomProduit;
    @FXML
    private Label PrixProduit;
    @FXML
    private TextField quantiteField;
    @FXML
    private AnchorPane anchorElement;

    @FXML
    private VBox vboxPlatmenu;
    @FXML
    private Text totalLignePanier;
    @FXML
    private AnchorPane interfacePaniers;

    @FXML
    private GridPane grid1;

    @FXML
    private Button btnValider;
    @FXML
    private Text panierLabel;
    @FXML
    private AnchorPane interfaceCommande;
    @FXML
    private GridPane grid2;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ScrollPane scroll1;

    @FXML
    private ScrollPane scroll2;
    @FXML
    private VBox vboxCommandes;
    @FXML
    private Label EtatCommande;
    @FXML
    private GridPane grid3;
    @FXML
    private Text commandId;
    @FXML
    private HBox commandTitleinterface;
    @FXML
    private VBox PaiementP;
    @FXML
    private AnchorPane PaimentPaneBg;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnValiderCommande;

    private List<Commande> getDataCommande(Utilisateur u) {
        CommandeService cs = new CommandeService();
        List<Commande> commandesList = cs.afficherCommande(u.getId());
        return commandesList;
    }

    private List<Panier> getDataPanier(Utilisateur u) {
        PanierService ps = new PanierService();
        List<Panier> panierss = ps.afficherPanier(u.getId());
        return panierss;
    }
    private static Produit prod;
    private static Panier pan;
    private static Commande comm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshPanier();
    }

    @FXML
    private void afficherCommandes() {
        interfacePaniers.setVisible(false);
        
        interfacePlats.setVisible(false);
        listDesCommandes();
        interfaceCommande.setVisible(true);
    }

    @FXML
    private void afficherPlats(ActionEvent event) {
        interfaceCommande.setVisible(false);
        vboxCommandes.setVisible(false);

        interfacePaniers.setVisible(false);
        vboxPlatmenu.setVisible(false);
        anchorElement.setVisible(false);
        listDesPlats();
        interfacePlats.setVisible(true);
    }

    private void refreshPanier() {
        Utilisateur u = new Utilisateur(1, "Oumayma");
        PanierService ps = new PanierService();
        ArrayList<Panier> p = ps.afficherPanier(u.getId());
        if (p.size() > 0) {
            btnValider.setText("Valider Panier");
            btnValider.setVisible(true);
            panierLabel.setText("Valider");

        } else {
            btnValider.setText("Panier Vide");
            btnValider.setVisible(false);
            panierLabel.setText("Panier Vide");
            new ZoomIn(panierLabel).play();
        }
    }

    public void setProduitDetails(Produit p) {
        if (prod.getQuaniteDispo()== 0) {
            quantiteField.setText("0");
            totalLignePanier.setText("");
            PromotionProduit.setStyle("-fx-background-color: red ;");
            PromotionProduit.setText("Hors Stock");
        } else {
            quantiteField.setText("1");
            totalLignePanier.setText("Total: " + prod.getPrix_prod());
            PromotionProduit.setStyle("-fx-background-color: green ;");
            PromotionProduit.setText("Disponible");
        }
        String bgcolor;
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        switch (value) {
            case 1:
                bgcolor = "FAB605";
            case 2:
                bgcolor = "FFB605";
                break;
            case 3:
                bgcolor = "80080C";
                break;
            case 4:
                bgcolor = "FB5D03";
                break;
            case 5:
                bgcolor = "22371D";
                break;
            case 6:
                bgcolor = "291D36";
                break;
            case 7:
                bgcolor = "F16C31";
                break;
            case 8:
                bgcolor = "A7745B";
                break;
            case 9:
                bgcolor = "6A7324";
                break;
            case 10:
                bgcolor = "ABB605";
                break;
            default:
                bgcolor = "5F060E";
        }
        Image image = new Image(getClass().getResourceAsStream("../Images/" + p.getPath()));
        ImageProduit.setImage(image);
        PrixProduit.setText(Double.toString(p.getPrix_prod()));
        NomProduit.setText(p.getLib_prod());
        anchorElement.setStyle("-fx-background-color: #" + bgcolor + ";\n"
                + "    -fx-background-radius: 30;");
        vboxElement.setVisible(true);
        vboxPlatmenu.setVisible(true);
        anchorElement.setVisible(true);

    }

    public void setCommandeDetails(Commande c) {
       
        anchorElement.setVisible(true);
        vboxCommandes.setVisible(true);
        comm = c;
        if (!c.isEtat_cmd()) {
            commandId.setText("Command " + c.getId());
            
            EtatCommande.setStyle("-fx-background-color: red ;");
            EtatCommande.setText("Pas Validé");
                       btnValiderCommande.setVisible(true);

        } else {
            commandId.setText("Command " + c.getId());
            EtatCommande.setStyle("-fx-background-color: green ;");
            EtatCommande.setText("En Cours");
            btnValiderCommande.setVisible(false);
        }
        String bgcolor;
        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;
        switch (value) {
            case 1:
                bgcolor = "FAB605";
            case 2:
                bgcolor = "FFB605";
                break;
            case 3:
                bgcolor = "80080C";
                break;
            case 4:
                bgcolor = "FB5D03";
                break;
            case 5:
                bgcolor = "22371D";
                break;
            case 6:
                bgcolor = "291D36";
                break;
            case 7:
                bgcolor = "F16C31";
                break;
            case 8:
                bgcolor = "A7745B";
                break;
            case 9:
                bgcolor = "6A7324";
                break;
            case 10:
                bgcolor = "ABB605";
                break;
            default:
                bgcolor = "5F060E";
        }
        commandTitleinterface.setStyle("-fx-background-color: " + bgcolor + ";");
        commandTitleinterface.setStyle("-fx-border-raduis:40;");

        grid3.getChildren().clear();

        c.setPaniers();
        if (c.getPaniers().size() > 0) {
            panierInterface = (Panier panier) -> {
                pan = panier;
                System.out.println(pan);
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < c.getPaniers().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Views/LigneCommandeInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                LigneCommandeInterfaceController myController = fxmlLoader.getController();
                myController.setData(c.getPaniers().get(i), panierInterface);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid3.add(anchorPane, column++, row);
                grid3.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid3.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid3.setMaxWidth(Region.USE_PREF_SIZE);
                grid3.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid3.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid3.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    @FXML
    private void afficherPanier(ActionEvent event) {
        Utilisateur u = new Utilisateur(1, "Oumayma");
        listDesPaniers(u);
        anchorElement.setVisible(false);
        interfacePlats.setVisible(false);
        vboxPlatmenu.setVisible(false);
        interfaceCommande.setVisible(false);
        interfacePaniers.setVisible(true);
        vboxCommandes.setVisible(false);

    }

    private List<Produit> getData() {
        ProduitCRUD ps = new ProduitCRUD();
        List<Produit> produitss = ps.afficherProduits();
        return produitss;
    }

    private void listDesPaniers(Utilisateur u) {
        grid1.getChildren().clear();
        paniers.clear();
        paniers.addAll(getDataPanier(u));
        if (paniers.size() > 0) {
            panierInterface = (Panier panier) -> {
                pan = panier;
                System.out.println(pan);
            };
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < paniers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Views/unePanierInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnePanierInterfaceController myController = fxmlLoader.getController();
                myController.setData(paniers.get(i), panierInterface);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                grid1.add(anchorPane, column++, row);
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private void listDesPlats() {
        grid.getChildren().clear();
        produits.clear();
        produits.addAll(getData());
        if (produits.size() > 0) {

            myListener = (Produit produit) -> {
                prod = produit;
                setProduitDetails(produit);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Views/unProduitInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnProduitInterfaceController myController = fxmlLoader.getController();
                myController.setData(produits.get(i), myListener);
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listDesCommandes() {
        grid2.getChildren().clear();
        commandes.clear();

        commandes.addAll(getDataCommande(new Utilisateur(1, "Oumayma")));
        if (commandes.size() > 0) {

            commandeInterface = (Commande commande) -> {
                System.out.println(commande.getId());
                comm = commande;
                setCommandeDetails(commande);
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < commandes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Views/uneCommandeInterface.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UneCommandeInterfaceController myController = fxmlLoader.getController();
                myController.setData(commandes.get(i), commandeInterface);
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid2.add(anchorPane, column++, row);
                grid2.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid2.setMaxWidth(Region.USE_PREF_SIZE);
                grid2.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid2.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid2.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void decrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) > 1) {
            int total = (Integer.parseInt(quantiteField.getText()) - 1);
            quantiteField.setText("" + total);
            totalLignePanier.setText("Total: " + total * prod.getPrix_prod());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite doit etre plus que 1");
            alert.show();
        }

    }

    @FXML
    private void incrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) < prod.getQuaniteDispo()) {
            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
            totalLignePanier.setText("Total: " + total * prod.getPrix_prod());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est maximal ");
            alert.show();
        }
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {

        PanierService ps = new PanierService();
        Utilisateur u = new Utilisateur(1, "Oumayma");
        int Quantite = Integer.parseInt(quantiteField.getText());
        if (Quantite < prod.getQuaniteDispo()) {
            boolean test = ps.ajouterPanier(prod, u, Quantite);
            if (test) {
                refreshPanier();
                   Notification notification = Notifications.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Plat Ajouté!");
        tray.setMessage("Merci!");
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(6));
             
        }
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Panier");
                aler.setHeaderText("Plat ajout");
                aler.setContentText("Plat Ajouté au panier");
                aler.show();
            } else {
                refreshPanier();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Panier");
                alert.setHeaderText("Produit déja existé dans votre Panier");
                alert.setContentText("Mettre a jours la quantite? ");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    Panier p = new Panier(ps.getIdPanier(prod, u));
                    ps.modifierPanier(p, Quantite);
                    Alert aler = new Alert(Alert.AlertType.INFORMATION);
                    aler.setTitle("Achat du Plat");
                    aler.setHeaderText("Modification Du Quantité");
                    aler.setContentText("La Quantite est modifié");
                    aler.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est insuffisante");
            alert.show();

        }
    }

    @FXML
    private void validerPanier(ActionEvent event) {
        Utilisateur u = new Utilisateur(1, "Oumayma");
        CommandeService cs = new CommandeService();
        if (cs.ajouterCommande(new Utilisateur(1, "Oumayma"))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Panier");
            alert.setHeaderText("Validation");
            alert.setContentText("Votre Panier est validé !");
            alert.show();
            grid1.getChildren().clear();
            btnValider.setText("Panier Vide");
            btnValider.setVisible(false);
            panierLabel.setText("Panier Vide");
            new ZoomIn(panierLabel).play();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Panier");
            alert.setHeaderText("Validation");
            alert.setContentText("Erreur!");
            alert.show();

        }
    }

    @FXML
    private void validerCommande(ActionEvent event) {
        
        try {
            fxml =  FXMLLoader.load(getClass().getResource("../Views/InterfacePaiement.fxml"));
            InterfacePaiementController ip = new InterfacePaiementController();
            ip.setCommande(comm);
            btnAnnuler.setVisible(true);
              PaiementP.getChildren().addAll(fxml);
               PaimentPaneBg.setStyle("-fx-background-color: white ;");
              PaimentPaneBg.toFront();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
          
                

    }

    @FXML
    private void annulerPaiement(ActionEvent event) {
        btnAnnuler.setVisible(false);
         PaiementP.getChildren().clear();
               PaimentPaneBg.setStyle("-fx-background-color: transparent ;");
               PaimentPaneBg.setStyle("-fx-opacity: 0.5 ;");
              PaimentPaneBg.toBack();
              afficherCommandes();
    }
    
}
