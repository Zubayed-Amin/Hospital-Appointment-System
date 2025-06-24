/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> colID;
    @FXML
    private TableColumn<User, String> colFullname;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPass;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, String> colContact;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button logoutBtn;

    ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private Button viewAppointBtn;
    

@Override
public void initialize(URL url, ResourceBundle rb) {
    colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
    colFullname.setCellValueFactory(new PropertyValueFactory<User, String>("fullname"));
    colUsername.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    colPass.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
    colRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
    colContact.setCellValueFactory(new PropertyValueFactory<User, String>("contact"));
}    

    @FXML
    private void AddUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminAddUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add User Form");
        stage.show();
        ((Stage) addBtn.getScene().getWindow()).close();
    }

    @FXML
    private void EditUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit User Form");
        stage.show();
        ((Stage) addBtn.getScene().getWindow()).close();
    }

    @FXML
    private void DeleteUser(ActionEvent event) {
    }
    
    @FXML
    private void ViewApointment(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Form");
        stage.show();
        ((Stage) viewAppointBtn.getScene().getWindow()).close();
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSignup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Form");
        stage.show();
        ((Stage) logoutBtn.getScene().getWindow()).close();
    }

}
