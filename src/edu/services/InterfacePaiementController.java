/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import edu.entities.Commande;
import  edu.entities.Panier;
import edu.services.CommandeService;
import edu.services.MailerService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author broum
 */
public class InterfacePaiementController implements Initializable {

    @FXML
    private PasswordField premierChiffres;
    @FXML
    private TextField dernierChiffres;
    @FXML
    private DatePicker datexpCarte;
    @FXML
    private PasswordField codeCarte;
    @FXML
    private Button btnValider;
    @FXML
    private AnchorPane mainBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeCarte.setDisable(true);
        dernierChiffres.setDisable(true);
        datexpCarte.setDisable(true);
        premierChiffres.textProperty().addListener((observableValue,s,s2) -> {
        if(premierChiffres.getText().toString().length() == 16) {
            premierChiffres.setDisable(true);
            dernierChiffres.setDisable(false);
            dernierChiffres.requestFocus();
                    }
    });
        dernierChiffres.textProperty().addListener((observableValue,s,s2) -> {
        if(dernierChiffres.getText().toString().length() == 4) {
            datexpCarte.setDisable(false);
            codeCarte.setDisable(false);
            datexpCarte.requestFocus();
            dernierChiffres.setDisable(true);
            
                    }
    });
            codeCarte.textProperty().addListener((observableValue,s,s2) -> {
        if(codeCarte.getText().toString().length() == 4) {
            codeCarte.setDisable(true);
            btnValider.requestFocus();
            btnValider.setDisable(false);
                    }
    });
    }    
    private static Commande comm;
    public void setCommande(Commande u){
        this.comm = u;
        System.out.println(comm);
    }
    @FXML
    private void validerCommande(ActionEvent event) throws IOException {
          CommandeService cs = new CommandeService();
          cs.modifierCommande(comm);
        comm.setPaniers();
        ArrayList<Panier> paniers = comm.getPaniers();
           cs.genererQR(paniers,comm);
        MailerService ms = new MailerService();
        String email = "broumaima@yahoo.com";
        String nom = "Oumayma";
        String message = "Bonjour Mr/Mme " + nom + " votre commande : " + comm.getId() + " est validé ! \n Bonne Journée! ";
        ms.replyMail(email, nom, message);
        System.out.println("Email Envoyé a "+email);
       ((VBox) mainBox.getParent()).getChildren().remove(mainBox);
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Validation Commande");
            alert.setHeaderText("Commande "+comm.getId()+" Est Validé");
            alert.setContentText("Un email est envoyé au "+email);
            alert.show();
       

    }
    
}
