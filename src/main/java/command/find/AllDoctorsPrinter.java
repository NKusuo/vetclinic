package command.find;

import entities.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
* Вывод всех работающих в клинике врачей
* */
public class AllDoctorsPrinter {
    public static void print(Connection connection){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from doctor");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Doctor doctor = new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                System.out.println(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
