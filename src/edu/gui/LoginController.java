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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button btn_login;
    @FXML
    private Label motOublier;
    @FXML
    private Button btn_inscrire;

    PreparedStatement ps;
    Connection con = null;
    ResultSet rs = null;
    int i = 0 ; 
    int j = 2500;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     public Button getButton() {
            return this.btn_inscrire;
}
    @FXML
    private void login(ActionEvent event) {
        con = MyConnection.getInstance().getCnx();
            this.i = this.i +1 ;

        try {
            String sql = "SELECT * FROM log where email like ? AND mdp like ? ";
            ps = con.prepareCall(sql);
            Utilisateur p = new Utilisateur();
            ps.setString(1, email.getText());
            ps.setString(2, pwd.getText());
            boolean b = false; 
            b = ps.execute();
            if (b){
            
             String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            if (email.getText().isEmpty() || pwd.getText().isEmpty() || !email.getText().matches(regex) || pwd.getText().length() < 8) {

                JOptionPane.showMessageDialog(null, "Veuillez vérifier vos données");

            } else {
                if (email.getText().equals("admin@admin.com") && pwd.getText().equals("adminadmin")) {
                    JOptionPane.showMessageDialog(null, "Connexion etablie");

                    btn_login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("List.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                }
                else if (email.getText().equals("personnel@personnel.com") && pwd.getText().equals("personnel123")){
                JOptionPane.showMessageDialog(null, "Connexion etablie");

                    btn_login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("ListPersonnel.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
                
                }
                else {
                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "Connexion etablie");

                        btn_login.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ProfilU.fxml"));
                        loader.load();
                        ProfilUController updateProfil = loader.getController();
                        updateProfil.setUpdate(true);
                        updateProfil.setLabel(email.getText(), rs.getString("nom"), rs.getString("prenom"), rs.getString("dn"));
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();

                    } else {

                        JOptionPane.showMessageDialog(null, "Connexion echouée");
                    }

                }

            }

            
            }
            else {
                            btn_login.setDisable(true);
            Timer timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
               btn_login.setDisable(false);
            });
        }
    }, 5000);
  
            
            }

           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrecte!");


            if ((this.i) % 3 == 0) {
                                this.j = this.j * 2 ;

                  btn_login.setDisable(true);
            Timer timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
               btn_login.setDisable(false);
            });
        }
    }, j);
    
            
            
            }
      

        
        }

    }

  

    @FXML
    private void mdpOublier(MouseEvent event) {
         try {
            motOublier.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("MdpOublier.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void Inscription(MouseEvent event) {
    }

    @FXML
    private void Inscrit(ActionEvent event) {
        
         try {
            btn_inscrire.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void setEnabled() {
        this.btn_login.setDisable(false);
    }

    

  

}
