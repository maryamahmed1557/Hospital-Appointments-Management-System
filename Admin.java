import java.util.ArrayList;
import java.io.*;
public class Admin extends User {
    public static int count;
    HospitalSystem hospitalSystem=new HospitalSystem();
    private ArrayList<Doctor> Doctors = new ArrayList<Doctor>();
    private ArrayList<Patient> Patients = new ArrayList<Patient>();
    FileManager fm=new FileManager();

    public Admin() {
    }

    public Admin(String id, String name, String userName, String password) {
        super(id, name, userName, password);
        this.role="Admin";
    }

    public void addDoctor(String id, String name, String userName, String password, String specialization,
            String department, String phoneNumber,String isAalible) {
                
        Doctor newDoctor = new Doctor(id, name, userName, password, specialization, department, phoneNumber,isAalible);
        Doctors.add(newDoctor);
        fm.savedoctor(newDoctor);
        fm.saveuser(newDoctor);
    }

    public void addDoctor(Doctor d) {
        Doctors.add(d);
    }

    public void addPatient(String id, String name, String userName, String password, String age, String gender,
            String phoneNumber) {
        Patient newPatient = new Patient(id, name, userName, password, age, gender, phoneNumber);
        Patients.add(newPatient);
        fm.savepatient(newPatient);
        fm.saveuser(newPatient);
    }

    public void addPatient(Patient p) {
        Patients.add(p);
    }

    public Doctor FindDoctor(String id) {
        for (Doctor doc : Doctors) {
            if (doc.getiD().equals(id)) {
                return doc;
            }
        }
        return null;
    }

    public Patient findPatient(String id) {
        for (Patient pat : Patients) {
            if (pat.getiD().equals(id)) {
                return pat;
            }
        }
        return null;
    }
   
    public void assignedPatienttoDoctor(String PatientID, String DoctorID) {
        Patient targetPatient = findPatient(PatientID);
        Doctor targetDoc = FindDoctor(DoctorID);
        if (targetPatient != null && targetDoc != null) {
            targetPatient.setAssignedDoctor(targetDoc);
            targetDoc.getPatients().add(targetPatient);
            System.out.println("Assign successfully " + targetPatient.name + " assign to " + targetDoc.name);
        } else {
            System.out.println("Error Doctor or patient not Found");
        }
    }

    public void createAppointment(String appointmentID, String PatientID, String DoctorID, String Data, String Time) {
        Doctor d = FindDoctor(DoctorID);
        Patient p = findPatient(PatientID);
            Appointment app = new Appointment(appointmentID, DoctorID, PatientID, Data, Time);
            if (hospitalSystem.addAppointment(app)) {        
            if (d != null) {
                d.getAppointments().add(app);
            }
            if (p != null) {
                p.getAppointments().add(app);
            }
           
            //part save in file
            fm.saveappoint(app);
            
        }else{
            System.out.println("Error Could not book appointment");   

        }
        }
   
        public void viewAllDoctors() {
        System.out.println("=== All Doctors ===");
        if (Doctors.isEmpty()) {
            System.out.println("No doctors.");
            return;
        }
        for (Doctor doc : Doctors) {
            doc.viewProfile();
        }
    }

         public void viewAllPatients() {
        System.out.println("=== All Patients ===");
        if (Patients.isEmpty()) {
            System.out.println("No patients.");
            return;
        }
        for (Patient pat : Patients) {
            pat.viewProfile();
        }
    }
public void viewallDoctorsandPatients() {
        viewAllDoctors();
        viewAllPatients();
    }

    public void viewallAppointment() {
        System.out.println("=== All Appointments ===");
        if (HospitalSystem.appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment app : HospitalSystem.appointments) {
            app.Display();
        }
    }
    public void Reports() {
        System.out.println("____________________________________________________________________");
        System.out.println("Total number of doctors and patients: " + count);

        int Confirmed = 0;
        int Completed = 0;
        int Cancelled = 0;

        for (Appointment app : HospitalSystem.appointments) {
            if (app.getStatus() == AppointmentStatus.Confirmed) {
                Confirmed++;
            } else if (app.getStatus() == AppointmentStatus.Completed) {
                Completed++;
            } else if (app.getStatus() == AppointmentStatus.Cancelled) {
                Cancelled++;
            }
        }

        System.out.println("===Total number of appointments (each status)===");
        System.out.println("Confirmed: " + Confirmed);
        System.out.println("Completed: " + Completed);
        System.out.println("Cancelled: " + Cancelled);

        Doctor fristDoctor = null;
        Doctor secondDoctor = null;
        Doctor thirdDoctor = null;
        int max1 = -1, max2 = -1, max3 = -1;
        int curtop;

        for (Doctor d : Doctors) {
            curtop = d.getAppointments().size();
            if (curtop > max1) {
                max3 = max2;
                thirdDoctor = secondDoctor;
                max2 = max1;
                secondDoctor = fristDoctor;
                max1 = curtop;
                fristDoctor = d;
            } else if (curtop > max2) {
                max3 = max2;
                thirdDoctor = secondDoctor;
                max2 = curtop;
                secondDoctor = d;
            } else if (curtop > max3) {
                max3 = curtop;
                thirdDoctor = d;
            }
        }

        System.out.println("===Top 3 Doctors===");
        if (fristDoctor != null) {
            System.out.println("1st: " + fristDoctor.name + " Appointments: " + fristDoctor.getAppointments().size());
        }
        if (secondDoctor != null) {
            System.out.println("2nd: " + secondDoctor.name + " Appointments: " + secondDoctor.getAppointments().size());
        }
        if (thirdDoctor != null) {
            System.out.println("3rd: " + thirdDoctor.name + " Appointments: " + thirdDoctor.getAppointments().size());
        }
        System.out.println("____________________________________________________________________");
    }
    public void Saveandload() {
    try {
        FileWriter fw1 = new FileWriter("doctors.txt", false);
        for (Doctor d : Doctors) {
            fw1.write(d.getiD()+","+d.getName()+","+d.getUserName()+","+d.getPassword()+","+d.getSpecialization()+","+d.getDepartment()+","+d.getPhoneNumber()+","+d.getAvaliable()+"\n");
        }
        fw1.close();

        FileWriter fw2 = new FileWriter("patients.txt", false);
        for (Patient p : Patients) {
            fw2.write(p.getiD()+","+p.getName()+","+p.getUserName()+","+p.getPassword()+","+p.getAge()+","+p.getGender()+","+p.getPhoneNumber()+"\n");
        }
        fw2.close();
       FileWriter fw3 = new FileWriter("appointments.txt",false);
       for (Appointment A:HospitalSystem.appointments) {
           fw3.write(A.getAppointmentID()+","+A.getDoctorID()+","+A.getPatientID()+","+A.getDate()+","+A.getTime()+","+A.getStatus()+"\n");
       }
        fw3.close();
        
    } catch (IOException e) {
        System.out.println("Error saving!");
    }
}
    
}