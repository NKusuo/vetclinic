package command.executor;

import command.CommandType;
import connectionDB.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import static connectionDB.DBConnection.closeDBConnection;


public class PatientCreator implements CommandExecutor{
    @Override
    public int execute(String command) throws SQLException, IOException {
        return createPatient();
    }

    private int createPatient() throws SQLException, IOException {
        Scanner str = new Scanner(System.in);
        System.out.print("Enter full name: ");
        String name = str.nextLine();

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into patient(id_patient,full_name, registration_date) values(default,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);

        System.out.println("Patient created.");
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_PATIENT;
    }
}
