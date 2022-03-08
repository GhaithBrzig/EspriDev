/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Reclamation;
import edu.services.ReclamationService;
import edu.utils.JavaMailUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.mail.event.MailEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author gayth
 */
public class InterfaceReclamationController implements Initializable {

    @FXML
    private TextField CName;
    @FXML
    private TextField CEmail;
    @FXML
    private TextArea Description;
    @FXML
    private Button btn_back;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @FXML
    private void envoyer(ActionEvent event) {
        if (CName.getText().equals("") || CEmail.getText().equals("") || Description.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Champ manquant!", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else if (!(isValidEmailAddress(CEmail.getText()) == true)) {
            JOptionPane.showMessageDialog(null, "Email invalide !", "Input error ", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String nom = CName.getText();
                String email = CEmail.getText();
                String description = Description.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Reclamation r = new Reclamation(nom, email, description, sqlDate);

                ReclamationService rsv = new ReclamationService();
                rsv.addReclamation(r);
                String mailDescription = "Nom client : " + nom + "\r\n" + "Email client : " + email + "\r\n" + "Description : " + description;
                 try {
            JavaMailUtil.sendMail("tanwichette@gmail.com",mailDescription,"Reclamation");
        } catch (Exception ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
                JOptionPane.showMessageDialog(null, "Reclamation envoyé !", "Input error ", JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException e) {
                Logger.getLogger(InterfaceReclamationController.class.getName()).log(Level.SEVERE, null, e);
            }

        }

    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
       btn_back.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../../panier/Views/InterfaceMenu.fxml"));
                        loader.load();
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }


}
