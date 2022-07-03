package command.executor;

import command.CommandType;
import command.checking.NumberEnter;
import connectionDB.DBConnection;
import entities.Appointment;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static connectionDB.DBConnection.closeDBConnection;

/*
* Вывод всех приемов пацинта
* */
public class AllAppointmentsPatient implements CommandExecutor{
    @Override
    public int execute(String command) throws SQLException, IOException {
        return printAllAppointments();
    }

    private int printAllAppointments() throws SQLException, IOException {

        Connection connection = DBConnection.getConnection();
        AllPatientsPrinter.print(connection);

        System.out.print("Enter id patient: ");
        int id = NumberEnter.check();

        //возвращаемся в главное меню, в случае ввода неверных данных
        if(id == -1){
            closeDBConnection(connection);
            return 1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from attendance where id_patient = ?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                System.out.println("Not found.");
                closeDBConnection(connection);
                return -1;
            }
            else{
                Appointment appointment = new Appointment(resultSet.getInt(1),
                        resultSet.getInt(4), resultSet.getInt(3),
                        resultSet.getString(5), resultSet.getTimestamp(2));
                System.out.println(appointment);
                while (resultSet.next()){
                    appointment = new Appointment(resultSet.getInt(1),
                            resultSet.getInt(4), resultSet.getInt(3),
                            resultSet.getString(5), resultSet.getTimestamp(2));
                    System.out.println(appointment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDBConnection(connection);
        return 1;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ALL_APPOINTMENTS;
    }
}
