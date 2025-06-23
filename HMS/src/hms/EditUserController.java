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
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditUserController implements Initializable {

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
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceRole.getItems().addAll("Doctor", "Patient", "Admin");
    }    

    @FXML
    private void handleUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin Dashboard");
        stage.show();
        ((Stage) btnUpdate.getScene().getWindow()).close();
    }
    
}
