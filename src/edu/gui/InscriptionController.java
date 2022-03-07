/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Utilisateur;
import edu.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class InscriptionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField numtel;
    @FXML
    private DatePicker dn;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button btn_inscrire;

    ResultSet rs = null;
    Connection cnx2;
    @FXML
    private Button btn_retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public InscriptionController() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    @FXML
    private void inscrireP(ActionEvent event) throws SQLException {
        String verifAccount = "Select * from login where email = ?";
        PreparedStatement ps = cnx2.prepareStatement(verifAccount);
        ps.setString (1,email.getText());
        boolean b = false; 
        b = ps.execute();
      /*  if(b){
        JOptionPane.showMessageDialog(null, "Compte déjà existe");
            }**/
      //  else{
        try {

            String requete = " INSERT INTO login (id, nom, prenom, numtel,dn, email, mdp) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstt = cnx2.prepareStatement(requete);
            Utilisateur p = new Utilisateur();
            pstt.setInt(1, p.getId_util());
            pstt.setString(2, nom.getText());
            pstt.setString(3, prenom.getText());
            pstt.setString(4, numtel.getText());
            pstt.setDate(5, Date.valueOf(dn.getValue()));
            pstt.setString(6, email.getText());
            pstt.setString(7, pwd.getText());

            pstt.executeUpdate();

            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            if (nom.getText().isEmpty() || prenom.getText().isEmpty() || dn.getValue().toString().isEmpty() || numtel.getText().isEmpty() || email.getText().isEmpty() || pwd.getText().isEmpty() || !email.getText().matches(regex) || pwd.getText().length() < 8) {

                JOptionPane.showMessageDialog(null, "Veuillez vérifier vos données");

            } else {

                JOptionPane.showMessageDialog(null, "Compte créée!");

                btn_inscrire.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                 Stage mainStage = new Stage();
                 Scene scene = new Scene(root);
                 mainStage.setScene(scene);
                 mainStage.show();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
