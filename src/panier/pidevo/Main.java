/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier.pidevo;

import panier.Services.CommandeService;
import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author broum
 */
public class Main extends Application {
    
     public void start(Stage primaryStage) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../Views/InterfaceMenu.fxml"));
            Scene scene = new Scene(root,1300,700);
            primaryStage.setTitle("Plat");
            primaryStage.setScene(scene);
            primaryStage.show();
           
        } catch (IOException ex) {
            ex.getMessage();
        }
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
     //  CommandeService s = new CommandeService();
      //  s.ajouter_points_fidelite();
      
   
    }
    
    
}
