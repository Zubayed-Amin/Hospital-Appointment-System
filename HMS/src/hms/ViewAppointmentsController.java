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

public class ViewAppointmentsController implements Initializable {

    @FXML
    private TableView<PatAppointment> appointmentTable;
    @FXML
    private TableColumn<PatAppointment, String> colDoctor;
    @FXML
    private TableColumn<PatAppointment, String> colDate;
    @FXML
    private TableColumn<PatAppointment, String> colTime;
    @FXML
    private TableColumn<PatAppointment, String> colStatus;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button backBtn;

    private String username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colDoctor.setCellValueFactory(new PropertyValueFactory<PatAppointment, String>("doctor"));
        colDate.setCellValueFactory(new PropertyValueFactory<PatAppointment, String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<PatAppointment, String>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<PatAppointment, String>("status"));
    }    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientDashboard.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Patient Dashboard");
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
