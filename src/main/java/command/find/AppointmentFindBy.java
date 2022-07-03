package command.find;

import command.checking.NumberEnter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AppointmentFindBy {

    public static int findById(Connection connection){

        System.out.print("Enter id appointment: ");
        int id = NumberEnter.check();

        if(id == -1){
            return -1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from attendance where id_attendance = ?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                System.out.println("Not found.");
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean findByIdDoctorAndData(Connection connection, Date date, int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from attendance where id_doctor = ? and date_of_appointment = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.setTimestamp(2,new java.sql.Timestamp(date.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
