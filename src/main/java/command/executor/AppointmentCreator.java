package command.executor;

import command.checking.NumberEnter;
import command.find.AllDoctorsPrinter;
import command.CommandType;
import command.find.AppointmentFindBy;
import connectionDB.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static connectionDB.DBConnection.closeDBConnection;

public class AppointmentCreator implements CommandExecutor{
    @Override
    public int execute(String command) throws SQLException, IOException {
        return createAppointment();
    }

    private int createAppointment() throws SQLException, IOException {
        Date registrationOfDate;

        Connection connection = DBConnection.getConnection();
        AllPatientsPrinter.print(connection);

        System.out.print("Enter id patient: ");
        int idPatient = NumberEnter.check();

        if(idPatient == -1){
            closeDBConnection(connection);
            return 1;
        }


        AllDoctorsPrinter.print(connection);
        System.out.print("Enter id doctor: ");
        int idDoctor = NumberEnter.check();

        if(idDoctor == -1){
            closeDBConnection(connection);
            return 1;
        }


        System.out.print("Enter the date in the format 'yyyy-MM-dd HH:mm:ss': ");
        Scanner str = new Scanner(System.in);
        String date = str.nextLine();

        try {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           registrationOfDate = formatter.parse(date);
        } catch (Exception ex) {
            System.out.println("Invalid date format.");
            closeDBConnection(connection);
            return 1;
        }

        boolean isFreeTime = AppointmentFindBy.findByIdDoctorAndData(connection, registrationOfDate, idDoctor);
        //если выбранное время у врача занято, то возвращаемся в главное меню
        if(isFreeTime){
            System.out.println("This date is busy.");
            closeDBConnection(connection);
            return 1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into attendance(id_attendance,date_of_appointment, id_doctor, id_patient, status) values(default,?,?,?,'new')");
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(registrationOfDate.getTime()));
            preparedStatement.setInt(2, idDoctor);
            preparedStatement.setInt(3, idPatient);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Appointment created.");
        closeDBConnection(connection);
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_APPOINTMENT;
    }
}
