package command.find;

import command.checking.NumberEnter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static connectionDB.DBConnection.closeDBConnection;

public class PatientFindById {

    public static int findById(Connection connection){

        System.out.print("Enter id patient: ");

        int id = NumberEnter.check();
        if(id == -1){
            closeDBConnection(connection);
            return -1;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from patient where id_patient = ?");
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
}
