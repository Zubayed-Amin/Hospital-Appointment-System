package hms;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PatAppointment {

    private SimpleIntegerProperty id;
    private SimpleStringProperty doctor;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty status;
    
    public PatAppointment(int id, String doctor, String date, String time, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.doctor = new SimpleStringProperty(doctor);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public String getDoctor() {
        return doctor.get();
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
