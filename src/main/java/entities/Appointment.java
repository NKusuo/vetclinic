package entities;

import java.util.Date;

public class Appointment{

        private final Integer idAppointment;
        private final Integer idPatient;
        private final Integer idDoctor;
        private final String status;
        private final Date dateOfAppointment;

        public Appointment(Integer idAppointment, Integer idPatient, Integer idDoctor, String status, Date dateOfAppointment) {
            this.idAppointment = idAppointment;
            this.idPatient = idPatient;
            this.idDoctor = idDoctor;
            this.status = status;
            this.dateOfAppointment = dateOfAppointment;
        }

        @Override
        public String toString(){
            return "Id: " + idAppointment.toString() + "  Data and time: "
                    + dateOfAppointment.toString() +  "  Id doctor: " + idDoctor.toString()
                    + "  Id patient: " + idPatient.toString()+ "  Status: " + status;
        }
}
