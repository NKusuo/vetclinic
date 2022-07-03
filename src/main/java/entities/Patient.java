package entities;

import java.util.Date;

public class Patient {

    private final Integer idPatient;
    private final String fullName;
    private final Date registrationDate;

    public Patient(Integer idPatient, String fullName, Date registrationDate){
        this.idPatient = idPatient;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString(){
        return "Id: " + idPatient.toString() + "  Full name: " + fullName + "  Date: " + registrationDate.toString();
    }

}
