/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.gui.Login;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author iheb
 */
public class List extends Application{
    
 @Override
    public void start(Stage primaryStage) {

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("List.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Liste Utilisateur");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    public static void main(String[] args) {
        launch(args);
    }

    
   
}
