/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class UpdateProfillUController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField prenom;
    @FXML
    private TextField dn;
    @FXML
    private TextField email;
    @FXML
    private Button savedata;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     void setTextFields(String tname, String tprenom, String tdn, String temail) {
       name.setText(tname);
       prenom.setText(tprenom);
       dn.setText(tdn);
       email.setText(temail);
       
    }
     
     @FXML
    private void update(ActionEvent event) throws IOException {
    Connection cnx2;
     cnx2 = MyConnection.getInstance().getCnx();
        
           try {
            String request = "UPDATE log set nom = ?, prenom = ?,dn = ? where email = ?  ";
            PreparedStatement pst = cnx2.prepareStatement(request);
            pst.setString(1, name.getText());
            pst.setString(2, prenom.getText());
            pst.setString(3, dn.getText());
            pst.setString(4, email.getText());
            
            int i = 0;
            i=pst.executeUpdate();
            
            if (i>0){
            System.out.println("Personne modifié! ");
              savedata.getScene().getWindow().hide();
                 FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ProfilU.fxml"));
                            loader.load();
                            ProfilUController updateProfil = loader.getController();
                            updateProfil.setUpdate(true);
                            updateProfil.setLabel(email.getText(),name.getText(),prenom.getText(),dn.getText());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          }    

    }

   
    
    

