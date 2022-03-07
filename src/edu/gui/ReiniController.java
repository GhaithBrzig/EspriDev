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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class ReiniController implements Initializable {

    @FXML
    private AnchorPane btn_reset;
    @FXML
    private TextField newPwd;
    @FXML
    private TextField verifNewMdp;
    PreparedStatement ps;
    Connection con = null;
    ResultSet rs = null;
    public String email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setTextFields(String email) {
        this.email = email;
    }

    @FXML
    private void reset(ActionEvent event) throws IOException{
          Connection cnx2;
        cnx2 = MyConnection.getInstance().getCnx();
        try {
        
        if (newPwd.getText().equals(verifNewMdp.getText())) {
           
                String updateQuerry = "UPDATE login set mdp = ? where email = ?";
                ps = cnx2.prepareStatement(updateQuerry);
                ps.setString(1, newPwd.getText());
                ps.setString(2, email);
                int i = 0;
                i = ps.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Réinistialisation terminée avec succés");
                    btn_reset.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
                    
                }
            
        } else {
            JOptionPane.showMessageDialog(null, "Mot de pass ne correspond pas");
        }
   }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
       }

}
