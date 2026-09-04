import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static HospitalSystem hospital = new HospitalSystem();
    static Admin admin = new Admin();
    static FileManager fm = new FileManager();
    static Doctor doc = new Doctor();
    static Patient p = new Patient();

    public static void main(String[] args) {
        try {
            ArrayList<Doctor> loadedDocs = fm.readDocfile();
            for (Doctor d : loadedDocs) admin.addDoctor(d);

            ArrayList<Patient> loadedPats = fm.readpatfile();
            for (Patient p : loadedPats) admin.addPatient(p);

            HospitalSystem.appointments = fm.readAppointments();
            System.out.println("System: Data loaded successfully.");
  for (Appointment app : HospitalSystem.appointments) {
    Doctor d = admin.FindDoctor(app.getDoctorID());
    if (d != null) {
        d.getAppointments().add(app);
    }
    Patient p = admin.findPatient(app.getPatientID());
    if (p != null) {
        if (!d.getAppointments().contains(app)) {
    d.getAppointments().add(app);
}
if (!d.getPatients().contains(p)) {
    d.getPatients().add(p);
}        
    }
}
        } catch (Exception e) {
            System.out.println("System: No existing data found. Starting fresh.");
        }

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n=== Hospital Management System ===");
                System.out.println("1. Login as Admin");
                System.out.println("2. Login as Doctor");
                System.out.println("3. Login as Patient");
                System.out.println("4. Exit");
                System.out.print("Choose: ");

                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        showAdminMenu();
                        break;
                    case 2:
                        handleDoctorLogin();
                        break;
                    case 3:
                        handlePatientLogin();
                        break;
                    case 4:
                        System.out.println("Exiting... Saving data.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                input.nextLine(); //   Buffer Infinite Loop
            }
        }
    }

    public static void showAdminMenu() {
        boolean run = true;
        while (run) {
            try {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Doctor");
                System.out.println("2. Register Patient");
                System.out.println("3. Assign Patient to Doctor");
                System.out.println("4. Create Appointment");
                System.out.println("5. View All Doctors");
                System.out.println("6. View All Patients");
                System.out.println("7. View All Appointments");
                System.out.println("8. Search Patient by ID");
                System.out.println("9. Search Doctor by ID");
                System.out.println("10. Generate Reports");
                System.out.println("11. Save Data");
                System.out.println("12. Logout");
                System.out.print("Select an option: ");
                
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter ID Doctor");
                        String id = input.next();
                        System.out.println("Enter name Doctor");
                        String name = input.next();
                        System.out.println("Enter username Doctor");
                        String userName = input.next();
                        System.out.println("Enter password Doctor");
                        String password = input.next();
                        System.out.println("Enter specialization Doctor");
                        String specialization = input.next();
                        System.out.println("Enter department Doctor");
                        String department = input.next();
                        System.out.println("Enter phone number Doctor");
                        String phoneNumber = input.next();
                        System.out.println("Enter isAvailable : ");
                        String isAalible = input.next();
                        admin.addDoctor(id, name, userName, password, specialization, department, phoneNumber, isAalible);
                        break;
                    case 2:
                        System.out.println("Enter ID patient");
                        String i = input.next();
                        System.out.println("Enter name ");
                        String nam = input.next();
                        System.out.println("Enter username ");
                        String userNam = input.next();
                        System.out.println("Enter password ");
                        String passwor = input.next();
                        System.out.println("Enter age ");
                        String age = input.next();
                        System.out.println("Enter female or male");
                        String gender = input.next();
                        System.out.println("Enter phone number");
                        String phoneNumbe = input.next();
                        admin.addPatient(i, nam, userNam, passwor, age, gender, phoneNumbe);
                        break;
                    case 3:
                        System.out.println("Enter Patient ID");
                        String patID = input.next();
                        System.out.println("Enter Doctor ID");
                        String docID = input.next();
                        admin.assignedPatienttoDoctor(patID, docID);
                        break;
                    case 4:
                        System.out.println("Enter Appointment ID");
                        String appId = input.next();
                        System.out.println("Enter Patient ID");
                        String p_Id = input.next();
                        System.out.println("Enter Doctor ID");
                        String d_Id = input.next();
                        System.out.println("Enter Date");
                        String date = input.next();
                        System.out.println("Enter Time");
                        String time = input.next();
                        admin.createAppointment(appId, p_Id, d_Id, date, time);
                        break;
                    case 5:
                        admin.viewAllDoctors();
                        break;
                    case 6:
                        admin.viewAllPatients();
                        break;
                    case 7:
                        admin.viewallAppointment();
                        break;
                    case 8:
                        System.out.println("Enter Patient ID to search");
                        String sPId = input.next();
                        Patient patientFound = admin.findPatient(sPId);
                        if (patientFound != null) patientFound.viewProfile();
                        else System.out.println("Not Found");
                        break;
                    case 9:
                        System.out.println("Enter Doctor ID to search");
                        String sDId = input.next();
                        Doctor doctorFound = admin.FindDoctor(sDId);
                        if (doctorFound != null) doctorFound.viewProfile();
                        else System.out.println("Not Found");
                        break;
                    case 10:
                        admin.Reports();
                        break;
                    case 11:
                        admin.Saveandload();
                        System.out.println("Data Saved Successfully!");
                        break;
                    case 12:
                        System.out.println("Logging out...");
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid selection!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine();
            }
        }
    }

    public static void handleDoctorLogin() {
    System.out.print("Enter Doctor ID: ");
    String docId = input.next();
    doc = admin.FindDoctor(docId);
    System.out.println("Enter user name");
    String uname=input.next();
    System.out.println("Enter passowrd");  
    String pass=input.next();  
    if (doc != null&&doc.login(uname, pass)) {
    boolean n = true;
    while (n) {
        {
            try {
                System.out.println("\n--- Doctor Menu ---");
                System.out.println("1. View My Profile ");
                System.out.println("2. View Assigned Patients");
                System.out.println("3. View My Appointments");
                System.out.println("4. Update Appointment Status");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");
                int number = input.nextInt();
                switch (number) {
                    case 1:
                        doc.viewProfile();
                        break;
                    case 2:
                        doc.viewAssignedPatients();
                        break;
                    case 3:
                        doc.viewMyAppointments();
                        break;
                    case 4:
                        System.out.print("Enter Appointment ID: ");
                        String appId = input.next();
                        System.out.println("1. Confirmed\n2. Completed\n3. Cancelled");
                        int s = input.nextInt();
                        AppointmentStatus status = AppointmentStatus.Confirmed;
                        if (s == 2) status = AppointmentStatus.Completed;
                        else if (s == 3) status = AppointmentStatus.Cancelled;
                        doc.updateStatus(appId, status);
                        break;
                    case 5:
                        n = false;
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Numbers only.");
                input.nextLine();
            }
        }}}else{
            System.out.println("Doctor not found!");
            return;
        }
    }

    public static void handlePatientLogin() {
    System.out.print("Enter Patient ID: ");
    String patId = input.next();
    p = admin.findPatient(patId);
    System.out.println("Enter user name");
    String usname=input.next();
    System.out.println("Enter password");
    String pass=input.next();
    if (p != null&&p.login(usname, pass)) {
    boolean m = true;
        while (m) {
            try {
                System.out.println("\n--- Patient Menu ---");
                System.out.println("1. View My Profile ");
                System.out.println("2. View Assigned Doctor");
                System.out.println("3. View My Appointments");
                System.out.println("4. Book Appointment");
                System.out.println("5. Cancel Appointment");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int opt = input.nextInt();
                switch (opt) {
                    case 1:
                        p.viewProfile();
                        break;
                    case 2:
                        if (p.getAssignedDoctor() != null) {
                            System.out.println("Your Doctor: " + p.getAssignedDoctor().getName());
                        } else {
                            System.out.println("No doctor assigned yet.");
                        }
                        break;
                    case 3:
                        p.viewMyAppointments();
                        break;
                    case 4:
                        System.out.println("Enter Appointment ID, Doctor ID, Date, Time: ");
                        String aid = input.next();
                        String did = input.next();
                        String d = input.next();
                        String t = input.next();
                        admin.createAppointment(aid, p.getiD(), did, d, t);
                        break;
                    case 5:
                        System.out.println("Enter Appointment ID to cancel: ");
                        String cancelID = input.next();
                        p.cancelAppointment(cancelID);
                        break;
                    case 6:
                        m= false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Numbers only.");
                input.nextLine();
            }
        }}else{
            System.out.println("Patient not found!");

        }
    }
}
            
        
        
                   