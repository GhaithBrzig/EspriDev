/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.Views;

import panier.Entity.Panier;
import panier.Entity.Produit;
import panier.Interfaces.panierInterface;
import animatefx.animation.Shake;
import animatefx.animation.ZoomIn;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author broum
 */
public class LigneCommandeInterfaceController {

    @FXML
    private AnchorPane panierBackground;
    @FXML
    private Label nomProduit;
  
    @FXML
    private Label quantite;
  
    @FXML
    private Label total;
  private panierInterface panierListener;
    private Panier panier;
    private Produit produit;
    public void setData(Panier pan, panierInterface panierListener) {
        this.panier = pan;
        System.out.println(pan);
        this.panierListener = panierListener;
        panier.setProduit();
        produit = panier.getProduit();
        nomProduit.setText(produit.getLib_prod());
        total.setText(Double.toString(panier.getQuantite()*produit.getPrix_prod()) + " DT");
        quantite.setText("" + Integer.toString(pan.getQuantite()));

    }
 @FXML
    private void click(MouseEvent event) {
        panierListener.onClickListener(panier);

    }
   
    
}
