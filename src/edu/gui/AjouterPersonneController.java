/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.Utilisateur;
import edu.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class AjouterPersonneController implements Initializable {
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
    private Button btn_ajouter;
    @FXML
    private PasswordField pwd;
    @FXML
    private ComboBox<String> combo_role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String>  data = FXCollections.observableArrayList();
      data.add("ADMIN");
      data.add("PERSONNEL");
      combo_role.setItems(data);
     
    }
     ResultSet rs = null;
      Connection cnx2;
 public AjouterPersonneController() {
        cnx2 = MyConnection.getInstance().getCnx();

    }
    
        void setTextField(String tnom , String tprenom){
            nom.setText(tnom);
            prenom.setText(tprenom);
        }
        
    
    @FXML
    private void savePerson(ActionEvent event) {
           
       String role =  combo_role.getSelectionModel().getSelectedItem();
       if (role == null)
            role = "C";
       try {

            String requete = " INSERT INTO log (id, nom, prenom, numtel,dn, email, mdp,role) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstt = cnx2.prepareStatement(requete);
            Utilisateur p = new Utilisateur();
            pstt.setInt(1, p.getId_util());
            pstt.setString(2, nom.getText());
            pstt.setString(3, prenom.getText());
            pstt.setString(4, numtel.getText());
            pstt.setDate(5, Date.valueOf(dn.getValue()));
            pstt.setString(6, email.getText());
            pstt.setString(7, pwd.getText());
            pstt.setString(8, role);
           
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            if (nom.getText().isEmpty() || prenom.getText().isEmpty() || dn.getValue().toString().isEmpty()  || numtel.getText().isEmpty() || email.getText().isEmpty() || pwd.getText().isEmpty() || !email.getText().matches(regex) || pwd.getText().length() < 8) {

                JOptionPane.showMessageDialog(null, "Veuillez vérifier vos données");

            } else {
                pstt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Personne ajouter!");
                btn_ajouter.getScene().getWindow().hide();
               

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
  
}
