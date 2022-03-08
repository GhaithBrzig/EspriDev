/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.gui.UpdateProfillUController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class ProfilUController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label prenom;
    @FXML
    private Label dn;
    @FXML
    private Label email;
    @FXML
    private Button update_btn;
    @FXML
    private Label age;
    private boolean update;
    @FXML
    private Button btn_deconn;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setUpdate(boolean b) {
        this.update = b;

    }
     
     void setLabel (String temail,String tnom , String tprenom , String tdn){
     email.setText(temail);
     name.setText(tnom);
     prenom.setText(tprenom);
     dn.setText(tdn);
     age.setText(String.valueOf((2022 - Integer.parseInt(tdn.substring(0, 4)))));
    
     }

    

    @FXML
    private void onUpdate(ActionEvent event)throws IOException {
        
       
        /*update_btn.getScene().getWindow().hide();*/

          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("UpdateProfill.fxml"));
                            loader.load();
                            UpdateProfillUController updateProfil = loader.getController();
                           
                            updateProfil.setTextFields(name.getText(),prenom.getText(),dn.getText(),email.getText());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        int opt = JOptionPane.showConfirmDialog(null, "Vous êtes sur?", "Supprimer", JOptionPane.YES_NO_OPTION);
    if(opt==0){
        btn_deconn.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                 Stage mainStage = new Stage();
                 Scene scene = new Scene(root);
                 mainStage.setScene(scene);
                 mainStage.show();
    }}

    @FXML
    private void back(ActionEvent event) throws IOException {
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