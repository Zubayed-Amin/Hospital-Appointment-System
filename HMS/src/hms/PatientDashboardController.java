/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private Button appointBtn;
    @FXML 
    private Button viewAppointBtn;
    @FXML 
    private ComboBox<String> ampmComboBox;
    @FXML
    private ComboBox<String> hourComboBox;
    @FXML
    private ComboBox<String> minComboBox;
    @FXML
    private ComboBox<String> deptComboBox;
    @FXML
    private TextField tffullname;
    @FXML
    private TextField tfcontact;
    
    private String patientUsername;
    
    private int patient_id;
    @FXML
    private Button loginBtn;
    @FXML
    private TextArea taDesc;
    
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
        hourComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        hourComboBox.setPromptText("hour");

        minComboBox.getItems().addAll("00", "15", "30", "45");
        minComboBox.setPromptText("min");

        ampmComboBox.getItems().addAll("AM", "PM");
        ampmComboBox.setPromptText("AM or PM");

        loadDepartments();

        // 🔄 When a department is selected, show related doctors + description
        deptComboBox.setOnAction(e -> {
            String selectedDept = deptComboBox.getValue();
            if (selectedDept != null) {
                loadDoctorsAndDepartmentDescription(selectedDept);
            }
        });
    }
    
    private void loadDepartments() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT DISTINCT department FROM users WHERE role = 'doctor'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                deptComboBox.getItems().add(rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void loadDoctorsAndDepartmentDescription(String department) {
        doctorComboBox.getItems().clear();
        String deptDescription = "";

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT fullname, description FROM users WHERE role = 'doctor' AND department = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, department);
            ResultSet rs = stmt.executeQuery();

            boolean first = true;
            while (rs.next()) {
                doctorComboBox.getItems().add(rs.getString("fullname"));

                // Only take description from first doctor found
                if (first) {
                    deptDescription = rs.getString("description");
                    first = false;
                }
            }

            taDesc.setText(deptDescription != null ? deptDescription : "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    @FXML
    private void BookAppointment(ActionEvent event) {
        String department = deptComboBox.getValue();
        String doctor = doctorComboBox.getValue();
        LocalDate date = datePicker.getValue();
        String hour = hourComboBox.getValue();
        String minute = minComboBox.getValue();
        String ampm = ampmComboBox.getValue();
        String patientName = tffullname.getText();
        String contact = tfcontact.getText();

        if (department == null || doctor == null || date == null || hour == null || minute == null || ampm == null) {
            System.out.println("Please fill in all fields.");
            return;
        }

        String time = hour + ":" + minute + " " + ampm;
        

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO appointment (patient_name, doctor_name, department, patient_contact, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?, ?, 'pending')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientName);
            ps.setString(2, doctor);
            ps.setString(3, department);
            ps.setString(4, contact);
            ps.setDate(5, Date.valueOf(date));
            ps.setString(6, time);
            ps.executeUpdate();

            System.out.println("Appointment Booked Successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to book appointment: " + e.getMessage());
        }
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
    private void Login(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSignup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Form");
        stage.show();
        ((Stage) loginBtn.getScene().getWindow()).close();
    }
    
}
