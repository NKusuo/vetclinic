package command.executor;

import command.CommandType;
import command.find.PatientFindById;
import connectionDB.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static connectionDB.DBConnection.closeDBConnection;

public class PatientUpdater implements CommandExecutor{

    @Override
    public int execute(String command) throws SQLException, IOException {
        return updateFullName();
    }

    private int updateFullName() throws SQLException, IOException {

        Connection connection = DBConnection.getConnection();
        AllPatientsPrinter.print(connection);
        int id = PatientFindById.findById(connection);

        if(id == -1){
            closeDBConnection(connection);
            return 1;
        }

        Scanner str = new Scanner(System.in);
        System.out.print("Enter new full name: ");
        String newName = str.nextLine();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update patient set full_name = ? where id_patient = ?");
            preparedStatement.setString(1,newName);
            preparedStatement.setInt(2,id);
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
        return CommandType.UPDATE_FULL_NAME;
    }
}
