package command;

import command.executor.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

/*
*
* Commands:
* - create patient
* - update full name
* - delete patient
* - create doctor
* - create appointment
* - update status
* - all appointments
* - all patients
* - exit
*
* example: create patient
* */
public class CommandReader {

    private final static Map<CommandType, CommandExecutor> COMMAND_EXECUTORS_GROUPED_BY_COMMAND =  Map.of(
            CommandType.CREATE_PATIENT, new PatientCreator(),
            CommandType.DELETE_PATIENT, new PatientDeleter(),
            CommandType.UPDATE_FULL_NAME, new PatientUpdater(),
            CommandType.CREATE_DOCTOR, new DoctorCreator(),
            CommandType.CREATE_APPOINTMENT, new AppointmentCreator(),
            CommandType.UPDATE_STATUS, new AppointmentStatusUpdater(),
            CommandType.ALL_PATIENTS, new AllPatientsPrinter(),
            CommandType.ALL_APPOINTMENTS, new AllAppointmentsPatient()
    );

    public static void startReading() throws SQLException, IOException {

        Scanner str = new Scanner(System.in);
        int i = 1;
        while (i != 0) {
            System.out.print("Choose a command:\n" +
                    " - create patient\n" +
                    " - update full name\n" +
                    " - delete patient\n" +
                    " - create doctor\n" +
                    " - create appointment\n" +
                    " - update status\n" +
                    " - all appointments\n" +
                    " - all patients\n" +
                    " - exit\n");
            i = readCommand(str);
        }

        str.close();
    }


    public static int readCommand(Scanner str) throws SQLException, IOException {

        String commandString = str.nextLine();

        CommandType commandType = getCommandType(commandString);

        if (COMMAND_EXECUTORS_GROUPED_BY_COMMAND.containsKey(commandType)) {
            var commandExecutor = COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(commandType);
            return commandExecutor.execute(commandString);
        }

        if (commandType == CommandType.EXIT) {
            return 0;
        }

        System.out.println("Unknown command.");
        return -1;
    }

    private static CommandType getCommandType(String commandString) {

        switch (commandString){
            case("create patient"):
                return CommandType.CREATE_PATIENT;
            case("update full name"):
                return CommandType.UPDATE_FULL_NAME;
            case("update status"):
                return CommandType.UPDATE_STATUS;
            case("delete patient"):
                return CommandType.DELETE_PATIENT;
            case("create doctor"):
                return CommandType.CREATE_DOCTOR;
            case("create appointment"):
                return CommandType.CREATE_APPOINTMENT;
            case("all patients"):
                return CommandType.ALL_PATIENTS;
            case("all appointments"):
                return CommandType.ALL_APPOINTMENTS;
            case("exit"):
                return CommandType.EXIT;
        }

        return CommandType.UNDEFINED;
    }
}
