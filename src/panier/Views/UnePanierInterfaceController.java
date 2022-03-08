/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Views;

import panier.Entity.Panier;
import panier.Entity.Produit;
import panier.Entity.Utilisateur;
import panier.Interfaces.panierInterface;
import panier.Services.PanierService;
import animatefx.animation.Shake;
import animatefx.animation.ZoomIn;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author broum
 */
public class UnePanierInterfaceController {

    @FXML
    private AnchorPane panierBackground;
    @FXML
    private ImageView imgProd;
    @FXML
    private Text nomProd;
    @FXML

    private Text prixPord;
    @FXML

    private TextField quantiteField;
    @FXML

    private Text totalLabel;
    private panierInterface panierListener;
    private Panier panier;
    private Produit produit;
    PanierService ps = new PanierService();

    public void setData(Panier pan, panierInterface panierListener) {
        this.panier = pan;
        this.panierListener = panierListener;
        panier.setProduit();
        produit = panier.getProduit();
        nomProd.setText(produit.getLib_prod());
        prixPord.setText(Double.toString(produit.getPrix_prod()) + " DT");
        quantiteField.setText("" + Integer.toString(pan.getQuantite()));
        totalLabel.setText("" + produit.getPrix_prod()* pan.getQuantite() + " DT");
//      Image image = new Image(getClass().getResourceAsStream("../Images/" + produit.getPath()));
//       imgProd.setImage(image);
    }

    @FXML
    private void click(MouseEvent event) {
        panierListener.onClickListener(panier);

    }

    @FXML
    private void decrementerQuantite(ActionEvent event) {
        if (Integer.parseInt(quantiteField.getText()) > 1) {
            int total = (Integer.parseInt(quantiteField.getText()) - 1);
            quantiteField.setText("" + total);
            totalLabel.setText("Total: " + total * panier.getProduit().getPrix_prod());
            ps.modifierPanier(panier, total);
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
        if (Integer.parseInt(quantiteField.getText()) < panier.getProduit().getQuaniteDispo()) {
            int total = (Integer.parseInt(quantiteField.getText()) + 1);
            quantiteField.setText("" + total);
            totalLabel.setText("Total: " + total * panier.getProduit().getPrix_prod());
            ps.modifierPanier(panier, total);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat du Plat");
            alert.setHeaderText("Quantite Erreur");
            alert.setContentText("La Quantite est maximal ");
            alert.show();
        }
    }

    @FXML
    private void supprimerPanier(ActionEvent event) {
        panierBackground.setStyle("-fx-background-color: red;");
        new Shake(panierBackground).play();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Panier");
        alert.setHeaderText("Suppression");
        alert.setContentText("Supprimer le Produit " + panier.getProduit().getLib_prod()+ " Du votre panier?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            if (ps.supprimerPanier(panier.getId())) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UnePanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }

                ((GridPane) panierBackground.getParent()).getChildren().remove(panierBackground);
                Alert aler = new Alert(Alert.AlertType.INFORMATION);
                aler.setTitle("Panier");
                aler.setHeaderText("Suppression");
                aler.setContentText("Produit " + panier.getProduit().getLib_prod()+ " supprimé!");
                aler.show();
            } else {
                panierBackground.setStyle("-fx-background-color: green;");
                new ZoomIn(panierBackground).play();
                Alert aler = new Alert(Alert.AlertType.ERROR);
                aler.setTitle("Panier");
                aler.setHeaderText("Suppression");
                aler.setContentText("Echec");
                aler.show();
            }

        } else {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UnePanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }

            panierBackground.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");

        }

    }

    /**
     * Initializes the controller class.
     */
}
