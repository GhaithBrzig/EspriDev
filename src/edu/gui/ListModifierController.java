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
import java.time.LocalDate;
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
public class ListModifierController implements Initializable {

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
    private Button btn_modifier;
    @FXML
    private PasswordField pwd;
    @FXML
    private ComboBox<String> combo_role;
    private TextField id;
    String querry = null;
    PreparedStatement ps = null;
    Utilisateur p = null;
    ResultSet rs = null;
    Connection cnx2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("ADMIN");
        data.add("PERSONNEL");
        combo_role.setItems(data);
    }

    void setTextField(String tnom, String tprenom, String tnumtel, String temail, LocalDate tdn) {
        nom.setText(tnom);
        prenom.setText(tprenom);
        numtel.setText(tnumtel);
        email.setText(temail);
        dn.setValue(tdn);

    }

    @FXML
    private void updatePersonne(ActionEvent event) {

        cnx2 = MyConnection.getInstance().getCnx();

        String role = combo_role.getSelectionModel().getSelectedItem();
        if (role == null)
            role = "C";

        try {

            String requete = "UPDATE log set nom = ?, prenom = ?, numtel = ?, email = ?, dn = ?, role = ?  where email = ?  ";
            PreparedStatement pstt = cnx2.prepareStatement(requete);
            Utilisateur p = new Utilisateur();
            pstt.setString(1, nom.getText());
            pstt.setString(2, prenom.getText());
            pstt.setString(3, numtel.getText());
            pstt.setString(4, email.getText());
            pstt.setDate(5, Date.valueOf(dn.getValue()));
            pstt.setString(6, role);
            pstt.setString(7, email.getText());

            int i = 0;
            i = pstt.executeUpdate();

            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
    if (i>0){        
            if (nom.getText().isEmpty() || prenom.getText().isEmpty() || dn.getValue().toString().isEmpty() || numtel.getText().isEmpty() || email.getText().isEmpty()  || !email.getText().matches(regex)) {

                JOptionPane.showMessageDialog(null, "Veuillez vérifier vos données");

            } else {

                JOptionPane.showMessageDialog(null, "Personne Modifier!");

                btn_modifier.getScene().getWindow().hide();
                

            }}
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
