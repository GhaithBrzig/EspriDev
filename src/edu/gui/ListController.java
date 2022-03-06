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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import java.util.Date;
import static javax.swing.SpringLayout.WIDTH;


/**
 * FXML Controller class
 *
 * @author iheb
 */
public class ListController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableUtil;
    @FXML
    private TableColumn<Utilisateur, String> idCol;
    @FXML
    private TableColumn<?, String> nomCol;
    @FXML
    private TableColumn<?, String> prenomCol;
    @FXML
    private TableColumn<?, String> numtelCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private TableColumn<?, String> dnCol;
    @FXML
    private TableColumn<?, String> ageCol;
    @FXML
    private TableColumn<?, String> roleCol;
    @FXML
    private ImageView imageAdd;
    @FXML
    private ImageView refresh;

    String querry = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Utilisateur p = null;

    ObservableList<Utilisateur> PersonneList = FXCollections.observableArrayList();
    @FXML
    private Button btn_deconn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        loadDate();
        getData();
    }

    @FXML
    private void edit(ActionEvent event) throws IOException {

        p = tableUtil.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ListModifier.fxml"));

        loader.load();
        ListModifierController updateProfil = loader.getController();
        updateProfil.setTextField(p.getNom(), p.getPrenom(), p.getNumtel(), p.getEmail(), p.getDn().toLocalDate());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void delete(ActionEvent event) {
        
    int opt = JOptionPane.showConfirmDialog(null, "Vous êtes sur?", "Supprimer", JOptionPane.YES_NO_OPTION);
    if(opt==0){
        try {
            p = tableUtil.getSelectionModel().getSelectedItem();

            querry = "DELETE FROM `log` WHERE id  =" + p.getId_util();
            con = MyConnection.getInstance().getCnx();
            PreparedStatement pstt = con.prepareStatement(querry);
            pstt.execute();
            refrech();

        } catch (SQLException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}

    @FXML
    private void getADD(MouseEvent event) throws IOException {
        //imageAdd.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjouterPersonne.fxml"));
        loader.load();
        AjouterPersonneController updateProfil = loader.getController();

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void refrech() {
        PersonneList.clear();

        getData();

    }

    void getData() {
        try {
            PersonneList.clear();
            querry = "SELECT id, nom, prenom ,numtel, dn  ,email , role FROM log";
            ps = con.prepareStatement(querry);
            rs = ps.executeQuery();
            while (rs.next()) {
                PersonneList.add(new Utilisateur(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("numtel"),
                        rs.getDate("dn"),
                        rs.getString("email"),
                        2022 - Integer.parseInt(rs.getDate("dn").toString().substring(0, 4)),
                        rs.getString("role")
                ));

                tableUtil.setItems(PersonneList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {
        con = MyConnection.getInstance().getCnx();

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numtelCol.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        dnCol.setCellValueFactory(new PropertyValueFactory<>("dn"));

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

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
    }
    }
    
}
