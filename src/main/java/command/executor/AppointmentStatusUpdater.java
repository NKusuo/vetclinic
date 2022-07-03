package command.executor;

import command.CommandType;
import command.find.AllAppointmentsPrinter;
import command.find.AppointmentFindBy;
import connectionDB.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static connectionDB.DBConnection.closeDBConnection;

public class AppointmentStatusUpdater implements CommandExecutor{

    private final static String [] ARRAY_STATUS = {"new", "in process", "canceled", "awaiting payment", "completed"};

    @Override
    public int execute(String command) throws SQLException, IOException {
        return updateStatusAppointment();
    }

    private int updateStatusAppointment() throws SQLException, IOException {

        Connection connection = DBConnection.getConnection();
        AllAppointmentsPrinter.print(connection);
        int id = AppointmentFindBy.findById(connection);

        if(id == -1){
            closeDBConnection(connection);
            return 1;
        }

        System.out.print(" - new\n" +
                " - in process\n" +
                " - canceled\n" +
                " - awaiting payment\n" +
                " - completed\n");

        Scanner str = new Scanner(System.in);
        System.out.print("Enter new status: ");
        String newStatus = str.nextLine();

        int resultCheck = checkStatus(newStatus);

        //в случае ввода неизвестного статуса возвращаемся в главное меню
        if(resultCheck == -1){
            System.out.println("Unknown status.");
            closeDBConnection(connection);
            return 1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update attendance set status = ? where id_attendance = ?");
            preparedStatement.setString(1,newStatus);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDBConnection(connection);
        System.out.println("Status updated.");
        return 1;
    }

    private int checkStatus(String newStatus) {
        for(String str:ARRAY_STATUS){
            if(newStatus.equals(str)){
                return 1;
            }
        }
        return -1;
    }


    @Override
    public CommandType getCommandType() {
        return CommandType.UPDATE_STATUS;
    }
}
