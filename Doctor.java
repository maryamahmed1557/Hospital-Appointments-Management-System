import java.util.ArrayList;
public class Doctor extends User {
    private String specialization;
    private String department;
    private String phoneNumber;
    private String Avaliable;
    ArrayList<Patient> patients;
    ArrayList<Appointment> appointments; 

    public Doctor(){
        Admin.count++;
    }
    public Doctor(String id, String name, String userName, String password, String specialization, String department,
            String phoneNumber,String Avaliable) {
        super(id, name, userName, password);
        this.specialization = specialization;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.Avaliable=Avaliable;
        this.role="Doctor";
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
        Admin.count++;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Patient> getPatients() {
    if (this.patients == null) {
        this.patients = new ArrayList<>();
        }
    return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Appointment> getAppointments() {
    if (this.appointments == null)
        this.appointments = new ArrayList<>();
    return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public void setAvaliable(String Avaliable){
        this.Avaliable=Avaliable;
    }
   public String getAvaliable(){
        return Avaliable;
   }
    public void viewProfile() {
        System.out.println("Doctor ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Specialization: " + this.specialization);
        System.out.println("Department: " + this.department);
        System.out.println("Phone: " + this.phoneNumber);
    }

    // View assigned patients
    public void viewAssignedPatients() {
        if (patients == null || patients.isEmpty()) {
            System.out.println("No patients assigned to you yet.");
            return;
        }
        System.out.println("=== Your Assigned Patients ===");
        for (Patient p : patients) {
            p.viewProfile();
        }
    }
 
    // View my appointments
    public void viewMyAppointments() {
        if (appointments == null || appointments.isEmpty()) {
            System.out.println("You have no appointments.");
            return;
        }
        System.out.println("=== Your Appointments ===");
        for (Appointment app : appointments) {
            app.Display();
        }
    }
 
    // Update appointment status
    public void updateStatus(String appId, AppointmentStatus status) {
        for (Appointment app : appointments) {
            if (app.getAppointmentID().equals(appId)) {
                app.UpDateStatus(status);
                // Also update in the global list
                HospitalSystem hs = new HospitalSystem();
                hs.UpdateStatus(appId, status);
                return;
            }
        }
        System.out.println("Appointment ID not found in your list.");
    }
   
}
