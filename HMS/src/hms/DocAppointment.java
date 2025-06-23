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
public class DocAppointment {

    private SimpleIntegerProperty id;
    private SimpleStringProperty patientName;
    private SimpleStringProperty contact;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty status;

    public DocAppointment(int id, String patientName, String contact, String date, String time, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.patientName = new SimpleStringProperty(patientName);
        this.contact = new SimpleStringProperty(contact);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public String getPatientName() {
        return patientName.get();
    }

    public String getContact() {
        return contact.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getStatus() {
        return status.get();
    }
}
