/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hms;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class AdminViewController implements Initializable {


    @FXML
    private TableView<DocAppointment> appointmentTable;

    @FXML
    private TableColumn<DocAppointment, String> colPName;

    @FXML
    private TableColumn<DocAppointment, String> colPcontact;

    @FXML
    private TableColumn<DocAppointment, String> colDate;

    @FXML
    private TableColumn<DocAppointment, String> colTime;

    @FXML
    private TableColumn<DocAppointment, String> colStatus;

    @FXML
    private Button backBtn;
    @FXML
    private Button logoutBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colPName.setCellValueFactory(new PropertyValueFactory<DocAppointment, String>("patientName"));
        colPcontact.setCellValueFactory(new PropertyValueFactory<DocAppointment, String>("contact"));
        colDate.setCellValueFactory(new PropertyValueFactory<DocAppointment, String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<DocAppointment, String>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<DocAppointment, String>("status"));
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin Dashboard");
        stage.show();
        ((Stage) backBtn.getScene().getWindow()).close();
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
