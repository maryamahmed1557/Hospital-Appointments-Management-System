import java.io.*;
import java.util.ArrayList;
class FileManager{
    // Save the new doctor
    public void savedoctor(Doctor d){
        // to check if this object is already exist
        ArrayList<Doctor>CurrenDoctors=readDocfile();
        // Arrraylist loop( compare with the id)
        for (Doctor existdoctor : CurrenDoctors) {
            if (existdoctor.getiD().equals(d.getiD())) {
                System.out.println("The Doctor is already Exist !(ID) : "+ existdoctor.getiD());
                return;
            }
        }// if the Doctor isn't exist
        try {
            FileWriter file=new FileWriter("doctors.txt",true);
            file.write(d.getiD()+","+d.getName()+","+d.getUserName()+","+d.getPassword()+","+
            d.getSpecialization()+","+d.getDepartment()+","+d.getPhoneNumber()+","+d.getAvaliable()+"\n");
            System.out.println("The Doctor : "+d.getName()+" Saved !");
            file.close();
        } catch (IOException e) {
          System.out.println("Error Saving Doctor !!");
        }
    }
    //read The file doctor and store it in arraylist with his/her data
    public ArrayList<Doctor> readDocfile(){
        // create the ArrayList
        ArrayList<Doctor> doctors=new ArrayList<>();
      try {
          BufferedReader reader=new BufferedReader(new FileReader("doctors.txt"));
          String line;
          while ((line=reader.readLine())!=null) { 
              String []data=line.split(",");
              Doctor d=new Doctor();
              d.setiD(data[0]);
              d.setName(data[1]);
              d.setUserName(data[2]);
              d.setPassword(data[3]);
              d.setSpecialization(data[4]);
              d.setDepartment(data[5]);
              d.setPhoneNumber(data[6]);
              d.setAvaliable(data[7]);
              // i will add this data in arraylist
              doctors.add(d);
          }
          reader.close();//close the file
      }catch(FileNotFoundException e){
       System.out.println("Error : The File Not Found !");
      }
      catch(IOException e){
       System.out.println("Error Reading File !");
      }//return arraylist
      return doctors;
    }
    //==========Patient=============
    // this method for saving new patient
    public void savepatient(Patient p){
     // check if he is exist
     ArrayList<Patient> currePatients=readpatfile();
     for (Patient exisPatient : currePatients) {
        if (exisPatient.getiD().equals(p.getiD())) {
            System.out.println("The Patient "+exisPatient.getiD()+" is already Exist !");
            return;
        } }
        try {
            FileWriter file=new FileWriter("patients.txt",true);
            file.write(p.getiD()+","+p.getName()+","+p.getUserName()+","+p.getPassword()+","+p.getAge()+","
            +p.getGender()+","+p.getPhoneNumber()+"\n");
            System.out.println("The Patient "+p.getName()+" saved !");
            file.close();
        } catch (IOException e) {
           System.out.println("Error Saving Patient !!");
        }
    }
    // this method to read the file and store in arraylist
    public ArrayList<Patient> readpatfile(){
     ArrayList<Patient> patientlist =new ArrayList<>();
     try {
         BufferedReader reader=new BufferedReader(new FileReader("patients.txt"));
         String line;
         while ((line=reader.readLine())!=null) { 
             String []data=line.split(",");
             Patient p=new Patient();
             p.setiD(data[0]);
             p.setName(data[1]);p.setUserName(data[2]);p.setPassword(data[3]);p.setAge(data[4]);p.setGender(data[5]);
             p.setPhoneNumber(data[6]);
             patientlist.add(p);
         }
         reader.close();
     } catch (FileNotFoundException e) {
        System.out.println("Error : The File Not Found !");
     }catch(IOException e){
        System.out.println("Error reading File !");

     }return patientlist;}
 // =======Appointment========
 public void saveappoint(Appointment b){
//check if the appointment is already exist
 ArrayList<Appointment> currentAppointments=readAppointments();
 for (Appointment exisAppointment: currentAppointments) {
     if (exisAppointment.getAppointmentID().equals(b.getAppointmentID())) {
         System.out.println("The Appointment is already Exist (ID):"+exisAppointment.getAppointmentID());
         return;
     }
 }try {
     FileWriter file=new FileWriter("appointments.txt",true);
     file.write(b.getAppointmentID()+","+b.getDoctorID()+","+b.getPatientID()+","+b.getDate()+","+b.getTime()+","+b.getStatus()+"\n");
     System.err.println("The Appointment "+b.getAppointmentID()+" is saved ");
     file.close();
 } catch (IOException e) {
    System.out.println("Error : Saving Appointment !!");
 }
 }
//read and store in arraylist
public ArrayList<Appointment> readAppointments(){
    ArrayList<Appointment> appointlist=new ArrayList<>();
    // read the file
    try {
        BufferedReader reader=new BufferedReader(new FileReader("appointments.txt"));
        String line;
        while ((line=reader.readLine())!=null) { 
            String[]data=line.split(",");
            Appointment b=new Appointment();
            b.setAppointmentID(data[0]);
            b.setDoctorID(data[1]);
            b.setPatientID(data[2]);
            b.setDate(data[3]);
            b.setTime(data[4]);
            // to convert from String to enum and store in arraylist as string 
            String statustr=data[5];
            AppointmentStatus s=AppointmentStatus.valueOf(statustr);
            b.UpDateStatus(s);
            appointlist.add(b);
        }
        reader.close();
    } catch (FileNotFoundException e) {
        System.out.println("Error : the file not found !");
    }catch(IOException e){
        System.out.println("Error : reading file !!");
    }
    return appointlist;
}
//=======user========
//save data
public void saveuser(User u){
ArrayList<User> currList=readuser();
for (User exist : currList) {
    if (exist.getiD().equals(u.getiD())) {
        System.out.println("The "+exist.getRole()+" : "+exist.getName()+" is already exist !");
        return;
    }
}
try {
    FileWriter file=new FileWriter("Users.txt",true);
    file.write(u.getiD()+","+u.getName()+","+u.getRole()+","+u.getUserName()+","+u.getPassword()+"\n");
    file.close();
} catch (IOException e) {
    System.out.println("Error : Saving !");
}
}
//read and store in array list
public ArrayList<User> readuser(){
    ArrayList<User> userlList=new ArrayList<>();
    try {
        BufferedReader reader=new BufferedReader(new FileReader("Users.txt"));
        String line;
        while ((line=reader.readLine())!=null) { 
            String []data=line.split(",");
            User u;//abstract can't take an object
            if(data[2].equals("Doctor")){
                u=new Doctor();
            }else if (data[2].equals("Patient")) {
                u=new Patient();
            }else{
                u=new Admin();
            }
            u.setiD(data[0]);
            u.setName(data[1]);
            u.setRole(data[2]);
            u.setUserName(data[3]);
            u.setPassword(data[4]);
           userlList.add(u);
        }
        reader.close();
    } catch (FileNotFoundException e) {
        System.out.println("Error : the file not found !");
    }catch(IOException e){
       System.out.println("Error : Reading file !");
    }
    return userlList;
    }
  //display arraylist for doctor======================optional=============
    public void dispDoc(){
    ArrayList<Doctor> listDoctor=readDocfile();
    for (Doctor elment : listDoctor) {
        System.out.println("The Doctor name is : "+elment.getName());
        System.out.println("The Doctor ID : "+elment.getiD());
        System.out.println("The Doctor Department : "+elment.getDepartment());
        System.out.println("The Doctor specialization is : "+elment.getSpecialization());
        System.out.println("The Doctor Department is : "+elment.getDepartment());
        System.out.println("The phone number is : "+elment.getPhoneNumber());
        System.out.println("The password is : "+elment.getPassword());
        System.out.println("The Doctor user name is : "+elment.getUserName());
        System.out.println("The Doctor is : "+elment.getAvaliable());
        System.err.println("----------------");
    }
    }
    // display all patients if needed
    public void dispatient(){
        ArrayList<Patient> patientlist=readpatfile();
        for (Patient elment : patientlist) {
            System.out.println("The patient id is : "+elment.getiD());
            System.out.println("The name is : "+elment.getName());
            System.out.println("The Age is : "+elment.getAge());
            System.out.println("The passward is : "+elment.getPassword());
            System.out.println("The phone number is : "+elment.getPhoneNumber());
            System.out.println("The username is : "+elment.getUserName());
             System.err.println("----------------");
        }
    }
    // display all appointment
    public void disAppoint(){
        ArrayList<Appointment> list=readAppointments();
        for(Appointment elment:list){
        System.out.println("The ID Appointment is : "+elment.getAppointmentID());
        System.out.println("The ID Doctor is : "+elment.getDoctorID() );
        System.out.println("The ID Patient is : "+elment.getPatientID());
        System.out.println("The date is : "+elment.getDate());
        System.out.println("The time is : "+elment.getTime());
         System.err.println("----------------");
        }
    }

}