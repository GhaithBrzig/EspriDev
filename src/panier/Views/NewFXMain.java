
package panier.Views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class NewFXMain extends Application { 
    
    @Override
    public void start(Stage primaryStage) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("InterfaceMenu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestionlivreur!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        
        } finally {
            //root.close();
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
