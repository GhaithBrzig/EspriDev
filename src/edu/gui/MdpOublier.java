/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author iheb
 */
public class MdpOublier extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("MdpOublier.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Réinitialiser mot de passe");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
