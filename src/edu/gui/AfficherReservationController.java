/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.entities.reservation;
import edu.services.reservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private TableView<reservation> TableRes;
    @FXML
    private TableColumn<reservation, Integer> idRes;
    @FXML
    private TableColumn<reservation, String> ResnomC;
    @FXML
    private TableColumn<reservation, Integer> ResNum;
    @FXML
    private TableColumn<reservation, Integer> ResNbp;
    @FXML
    private TableColumn<reservation, String> ResDate;
    @FXML
    private TableColumn<reservation, Integer> ResidEv;
    @FXML
    private Button Refresh;
    ObservableList<reservation> ResList = FXCollections.observableArrayList();
    @FXML
    private Button AZ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        
    }   


    @FXML
    private void refresh() {
        ResList.clear();
                reservationCRUD RC = new reservationCRUD();
        ResList.addAll(RC.afficheReservation());
        RC.afficheReservation();
        
        TableRes.setItems(ResList);
    }

    private void loadDate() {
          refresh();
        idRes.setCellValueFactory(new PropertyValueFactory<>("id"));
        ResnomC.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        ResNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ResNbp.setCellValueFactory(new PropertyValueFactory<>("nbpersonne"));
         ResDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         ResidEv.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
            TableRes.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

               
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("displayReservation.fxml"));
                try{
                    Loader.load();
                }catch (IOException ex) {
                // ex.printStackTrace();
                    
                    System.out.println("error : "+ex.getMessage());;
                }
                DisplayReservationController Rdc = Loader.getController();
                System.out.println(TableRes.getSelectionModel().getSelectedItem().getDate());
                Rdc.setData(TableRes.getSelectionModel().getSelectedItem().getId()
                        ,""+TableRes.getSelectionModel().getSelectedItem().getNomClient()
                        ,TableRes.getSelectionModel().getSelectedItem().getNumero()
                        ,TableRes.getSelectionModel().getSelectedItem().getNbpersonne()
                        ,TableRes.getSelectionModel().getSelectedItem().getDate()
                        ,TableRes.getSelectionModel().getSelectedItem().getId_evenement());
                
                
             Parent p = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            }
            
            
        });

        
        
    }

    @FXML
    private void Ajouter2(ActionEvent event){
         
        try {
            Parent root;
            root=FXMLLoader.load(getClass().getResource("Reservation.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
           stage.initStyle(StageStyle.UTILITY);
            stage.show();
              
        } catch (IOException ex) {
            Logger.getLogger(AfficherReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
         
        
        
        
        
        
    }


   
          
            

        
           
        
        
        
        
        
        
        
        
        
    }
    

