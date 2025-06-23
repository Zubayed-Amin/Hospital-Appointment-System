/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zubay
 */
public class User {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty fullname;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty role;
    private SimpleStringProperty contact;

    public User(Integer id, String fullname, String username, String password, String role, String contact) {
        this.id = new SimpleIntegerProperty(id);
        this.fullname = new SimpleStringProperty(fullname);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
        this.contact = new SimpleStringProperty(contact);
    }
    
    public int getId() {
        return id.get();
    }

    public String getFullname() {
        return fullname.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getRole() {
        return role.get();
    }

    public String getContact() {
        return contact.get();
    }
    
}
