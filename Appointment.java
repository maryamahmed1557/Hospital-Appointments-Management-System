enum AppointmentStatus{
    Confirmed,
    Completed,
    Cancelled
}

public class Appointment {
    private  String appointmentID;
    private String DoctorID;
    private String PatientID;
    private String Date;
    private String Time;
    private AppointmentStatus Status;
public Appointment(){}

public Appointment(String appointmentID, String DoctorID, String PatientID, String Date, String Time) {
    this.appointmentID = appointmentID;
    this.DoctorID = DoctorID;
    this.PatientID = PatientID;
    this.Date = Date;
    this.Time = Time;
    this.Status = AppointmentStatus.Confirmed;
}
public String getAppointmentID() {
    return appointmentID;
}

public String getDoctorID() {
    return DoctorID;
}

public String getPatientID() {
    return PatientID;
}

public String getDate() {
    return Date;
}


public String getTime() {
    return Time;
}


public AppointmentStatus getStatus() {
    return Status;
}

public void UpDateStatus(AppointmentStatus newStatus) {

    if (this.Status== AppointmentStatus.Cancelled && newStatus == AppointmentStatus.Completed) {
        System.out.println(" A Cancelled Appointment Cann't be marked as Completed!");
    }else {
        this.Status = newStatus;

    }
}
public void Display(){
    System.out.println("Appointment ID:"+appointmentID);
    System.out.println("Doctor ID:"+DoctorID);
    System.out.println("PatientID:"+PatientID);
    System.out.println("Date:"+Date);
    System.out.println("Time:"+Time);
    System.out.println("Status:"+Status);
}
 //setters
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }








}
