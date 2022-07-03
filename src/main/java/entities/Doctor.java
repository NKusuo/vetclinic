package entities;

public class Doctor {

    private final Integer idDoctor;
    private final String fullName;
    private final String speciality;

    public Doctor(Integer idDoctor, String fullName, String speciality) {
        this.idDoctor = idDoctor;
        this.fullName = fullName;
        this.speciality = speciality;
    }

   @Override
    public String toString(){
        return "Id: " + idDoctor.toString() + "  Full name: " + fullName + "  Speciality: " + speciality;
   }
}
