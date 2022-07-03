package command.executor;

import command.CommandType;
import connectionDB.DBConnection;
import entities.Patient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static connectionDB.DBConnection.closeDBConnection;

public class AllPatientsPrinter implements CommandExecutor{

    @Override
    public int execute(String command) throws SQLException, IOException {
        return printAllPatients();
    }

    private int printAllPatients() throws SQLException, IOException {
        Connection connection = DBConnection.getConnection();
        AllPatientsPrinter.print(connection);
        closeDBConnection(connection);
        return 1;
    }

    public static void print(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from patient");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Patient patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3));
                System.out.println(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ALL_PATIENTS;
    }
}
