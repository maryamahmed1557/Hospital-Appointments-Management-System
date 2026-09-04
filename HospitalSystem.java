import java.util.ArrayList;

public class HospitalSystem {

 protected static ArrayList<Appointment> appointments = new ArrayList<Appointment>();

public boolean addAppointment(Appointment newappointment){
    for(Appointment a : appointments){

        if (a.getDoctorID()==newappointment.getDoctorID()&&a.getDate().equals(newappointment.getDate())
        &&a.getTime().equals(newappointment.getTime())){

            System.out.println(" Doctor has an appointment with the same time");
            return false;

        }
    }
    if(newappointment.getDate().isEmpty()||newappointment.getTime().isEmpty()){
        System.out.println(" The date or the time is empty");
        return false;
    }
    appointments.add(newappointment);
    
    return true;
}
public static void viewAppointments(){
    for(Appointment a : appointments){
        a.Display();
    }
}
public static Appointment FindByID(String id){
    for(Appointment a : appointments){
        if (a.getAppointmentID().equals(id)){
            return a;
        }
    }
    return null;
}
public static void CancelAppointment(String id){
    Appointment a =  FindByID(id);
    if(a!=null){
        a.UpDateStatus(AppointmentStatus.Cancelled);
        System.out.println("Appointment has been cancelled");
    }else{
        System.out.println("The appointment not found");
    }
}
public void UpdateStatus(String id,AppointmentStatus status){
    Appointment a =  FindByID(id);
    if(a!=null){
        a.UpDateStatus(status);
        System.out.println("Appointment has been updated");
    }else{
        System.out.println("The appointment not found");
}

}

}
