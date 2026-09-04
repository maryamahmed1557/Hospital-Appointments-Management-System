import java.util.ArrayList;
public class Patient extends User {
    private String age;
    private String gender;
    private String phoneNumber;
    private Doctor assignedDoctor;
    private ArrayList<Appointment> appointments;

    public Patient() {
        Admin.count++;
    }

    public Patient(String id, String name, String userName, String password, String age, String gender,
            String phoneNumber) {
        super(id, name, userName, password);
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.role = "Patient";
        appointments = new ArrayList<Appointment>();
        Admin.count++;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(Doctor assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

   public ArrayList<Appointment> getAppointments() {
    if (this.appointments == null)
        this.appointments = new ArrayList<>();
    return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void viewProfile() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phoneNumber);
    }
     
    public void viewMyAppointments() {
        System.out.println("=== Your Appointments ===");
        boolean hasAppointments = false;
        for (Appointment app : HospitalSystem.appointments) {
            if (app.getPatientID().equals(this.id)) {
                System.out.println("Appointment Id"+app.getAppointmentID()+"\nDate: " + app.getDate() + "\nDoctor: " + app.getDoctorID());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
        System.out.println("You have no appointments.");
        }
    }
    
    public void cancelAppointment(String cancelID) {
        HospitalSystem.CancelAppointment(cancelID);
    }
}
