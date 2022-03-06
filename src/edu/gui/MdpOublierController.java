/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.mail.internet.AddressException;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class MdpOublierController implements Initializable {

    @FXML
    private TextField fEmail;
    @FXML
    private TextField verif_cod;
    @FXML
    private Button btn_verifcode;
    @FXML
    private Button btn_femail;

    int randomcode;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void envoyerCode(ActionEvent event) throws Exception {

        //mail.sendMail("tanwichette@gmail.com","cfghjklmù*","Reclamation");
        //JOptionPane.showMessageDialog(null, "Code envoyé");
        try {
            Random rand = new Random();
            randomcode = rand.nextInt(9999999);
            String host = "smtp.gmail.com";
            String user = "iheb.lafi@esprit.tn";
            String pass = "213JMT0070";
            String to = fEmail.getText();
            String subject = "Initialisation Code";

            String message = "Your reset code is " + randomcode;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            pros.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "Code envoyé");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void verifierCode(ActionEvent event) throws IOException {

        if (Integer.valueOf(verif_cod.getText()) == randomcode) {
            btn_verifcode.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Reini.fxml"));
            loader.load();
            ReiniController rs = loader.getController();
            rs.setTextFields(fEmail.getText());
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier code");
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        btn_retour.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
