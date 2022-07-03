package command.executor;

import command.CommandType;
import command.find.PatientFindById;
import connectionDB.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static connectionDB.DBConnection.closeDBConnection;

public class PatientDeleter implements CommandExecutor{
    @Override
    public int execute(String command) throws SQLException, IOException {
        return deletePatient();
    }

    private int deletePatient() throws SQLException, IOException {

        Connection connection = DBConnection.getConnection();
        AllPatientsPrinter.print(connection);

        int id = PatientFindById.findById(connection);
        if(id == -1){
            closeDBConnection(connection);
            return 1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from patient where id_patient = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Done.\n");
        closeDBConnection(connection);
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_PATIENT;
    }
}
