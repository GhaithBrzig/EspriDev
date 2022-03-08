/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author broum
 */
import  edu.entities.Produit;
import  edu.entities.Utilisateur;
import edu.gui.produitInterface;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UnProduitInterfaceController  {

    @FXML
    private ImageView imgProd;
    @FXML
    private Text nomProd;
  
    @FXML
    private Text prixProd;
    Parent root;
    @FXML
    private AnchorPane platBackground;

    @FXML
    private void click(MouseEvent mouseEvent) {
        produitListener.onClickListener(produit);
    }
    private Produit produit;
    private produitInterface produitListener;

    public void setData(Produit produit, produitInterface produitListener) {
        this.produit = produit;
        this.produitListener = produitListener;
        nomProd.setText(produit.getLib_prod());
        prixProd.setText(Double.toString(produit.getPrix_prod())+" DT");
        if(produit.getQuaniteDispo()==0){
            platBackground.setStyle("-fx-background-color: #ff1212;");
            
        }
        else{
            platBackground.setStyle("-fx-background-color: #aeff3d;");
        }
        System.out.println(produit.getPath());
        Image image = new Image(getClass().getResourceAsStream("../Images/"+produit.getPath())); 
        imgProd.setImage(image);
    }


   
}
