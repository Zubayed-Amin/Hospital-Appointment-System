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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class LoginSignupController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label lblError;
    @FXML
    private Button signupBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = tfUsername.getText().trim();
        String password = pfPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Username and Password must not be empty.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role");

                        String fxmlFile = null;
                        String title = null;

                        if ("Admin".equalsIgnoreCase(role)) {
                            fxmlFile = "AdminDashboard.fxml";
                            title = "Admin Dashboard";
                        } else if ("Doctor".equalsIgnoreCase(role)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorDashboard.fxml"));
                            Parent root = loader.load();


                            DoctorDashboardController controller = loader.getController();
                            controller.setDoctorUsername(username);


                            Stage stage = (Stage) lblError.getScene().getWindow(); 
                            stage.setScene(new Scene(root));
                            stage.setTitle("Doctor Dashboard");
                            stage.show();

                            return;
                        } else if ("Patient".equalsIgnoreCase(role)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientDashboard.fxml"));
                            Parent root = loader.load();


                            PatientDashboardController controller = loader.getController();
                            controller.setPatientUsername(username);


                            Stage stage = (Stage) lblError.getScene().getWindow(); 
                            stage.setScene(new Scene(root));
                            stage.setTitle("Patient Dashboard");
                            stage.show();

                            return;
                        }


                        if (fxmlFile != null) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                            Parent root = loader.load();


                            Stage stage = (Stage) lblError.getScene().getWindow(); 
                            stage.setScene(new Scene(root));
                            stage.setTitle(title);
                            stage.show();
                        } else {
                            lblError.setText("Unknown role.");
                        }

                    } else {
                        lblError.setText("Invalid username or password.");
                    }
                }
            }
        } catch (Exception e) {
            lblError.setText("Login failed: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Signup Form");
        stage.show();
        ((Stage) signupBtn.getScene().getWindow()).close();
    }

    
}
