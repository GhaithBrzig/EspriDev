/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import Entity.Commande;
import Interfaces.commandeInterface;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author broum
 */
public class UneCommandeInterfaceController {
    
     private commandeInterface commandeListener ;
     private Commande commande;
    @FXML
    private AnchorPane commandeBackground;
    @FXML
    private Text commandNumber;
    @FXML
    private Text nomProd;
    @FXML
    private Text prixProd;
   
   @FXML
    private void click(MouseEvent event) {
        commandeListener.onClickListener(commande);
    }
  public void setData(Commande commande, commandeInterface myListener) {
        this.commande = commande;
        this.commandeListener = myListener;
        if(commande.isEtat_cmd()){
            commandeBackground.setStyle("-fx-background-color: green;");
        }
        else {
            commandeBackground.setStyle("-fx-background-color: #ff1212;");
        }
    
    commandNumber.setText("#"+commande.getId());
        System.out.println(commande);


    }

    
}
