package command.executor;

import command.CommandType;
import connectionDB.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static connectionDB.DBConnection.closeDBConnection;

public class DoctorCreator implements CommandExecutor{
    @Override
    public int execute(String command) throws SQLException, IOException {
        return createDoctor();
    }

    private int createDoctor() throws SQLException, IOException {
        Scanner str = new Scanner(System.in);
        System.out.print("Enter full name: ");
        String name = str.nextLine();
        System.out.print("Enter speciality: ");
        String speciality = str.nextLine();

        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into doctor(id_doctor,full_name, speciality) values(default,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, speciality);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeDBConnection(connection);

        System.out.println("Doctor created.");
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_DOCTOR;
    }
}
