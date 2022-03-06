/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.utils.ScheduledTask;
import java.awt.Color;
import java.io.IOException;
import java.util.Timer;
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
 * @author gayth
 */
public class FXmain extends Application {

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            Scene scene = new Scene(root, 1100, 700);
            primaryStage.setTitle("Ajouter Produit");
            primaryStage.setScene(scene);
            primaryStage.show();
            Timer time = new Timer(); // Instantiate Timer Object
            ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
            time.schedule(st, 0, 1000*60*60*24); // Create Repetitively task for every 1 secs

        } catch (IOException ex) {
            Logger.getLogger(FXmain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
