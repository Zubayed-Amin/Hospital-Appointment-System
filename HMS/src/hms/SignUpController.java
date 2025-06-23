package hms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private TextField tfFullname;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfContact;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmPassword;
    @FXML
    private ComboBox<String> choiceRole;
    @FXML
    private Label lblMsg;
    @FXML
    private Button btnSignUp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add role options
        choiceRole.getItems().addAll("Doctor", "Patient");

        // Set default prompt text without selecting any value
        choiceRole.setPromptText("Select Role");
    }

    
    @FXML
    private void handleSignUp(ActionEvent event) throws SQLException, IOException {
        {
        String fullname = tfFullname.getText();
        String username = tfUsername.getText();
        String password = pfPassword.getText();
        String contact = tfContact.getText();
        String confirmPassword = pfConfirmPassword.getText();
        String role = choiceRole.getValue();

        if (!password.equals(confirmPassword)) {
            lblMsg.setText("Passwords do not match!");
            return;
        }
        
        if (fullname.isEmpty() || username.isEmpty() || contact.isEmpty() || password.isEmpty() || role == null) {
            lblMsg.setText("All fields are required.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO users (fullname, username, password, contact, role) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, fullname);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, contact);
                stmt.setString(5, role);
                
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    lblMsg.setText("Registration successful!");
                } else {
                    lblMsg.setText("Registration failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                lblMsg.setText("Error" + e.getMessage());
            }
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSignup.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Form");
        stage.show();
        ((Stage) btnSignUp.getScene().getWindow()).close();
    }
}
