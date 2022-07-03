package command.find;

import entities.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* Вывод всех приемов клиники
* */
public class AllAppointmentsPrinter {
    public static void print(Connection connection) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from attendance");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment(resultSet.getInt(1),
                        resultSet.getInt(4), resultSet.getInt(3),
                        resultSet.getString(5), resultSet.getTimestamp(2));
                System.out.println(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
