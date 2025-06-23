/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientDashboardController implements Initializable {

    @FXML 
    private ComboBox<String> doctorComboBox;
    @FXML 
    private DatePicker datePicker;
    @FXML 
    private TextField timeField;
    @FXML 
    private Button appointBtn;
    @FXML 
    private Button viewAppointBtn;
    @FXML 
    private Button logoutBtn;
    @FXML 
    private ComboBox<String> ampmComboBox;

    private String patientUsername;
    
    private int patient_id;
    public void setPatientId(int id) {
    this.patient_id = id;
    }
    
    public void setPatientUsername(String username) {
        this.patientUsername = username;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.patient_id = rs.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ampmComboBox.getItems().addAll("AM", "PM");
        ampmComboBox.setPromptText("AM or PM");
    }    

    @FXML
    private void BookAppointment(ActionEvent event) {
    }

    @FXML
    private void ViewAppointments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAppointments.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Appointments");
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
