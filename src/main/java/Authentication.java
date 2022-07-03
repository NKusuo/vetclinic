import connectionDB.DBConnection;
import entities.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {

    public static void authenticate() throws SQLException, IOException {
        Scanner str = new Scanner(System.in);
        int chance = 3;
        boolean isAuthenticationSuccess = false;

        Connection connection = DBConnection.getConnection();

        while(chance > 0 && !isAuthenticationSuccess) {

            System.out.print("Login: ");
            String login = str.nextLine();

            System.out.print("Password: ");
            String password = str.nextLine();

            Admin admin = new Admin(login,password);

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select id_admin, login, password from admin where login = ? and password = ?");
                preparedStatement.setString(1,admin.getLogin());
                preparedStatement.setString(2,admin.getPassword());

                ResultSet resultSet = preparedStatement.executeQuery();

                if(!resultSet.next()){
                    System.out.println("Incorrect data.");
                }
                else {
                    System.out.println("Login access.");
                    isAuthenticationSuccess = true;
                }

            } catch (SQLException e) {
            e.printStackTrace();
            }
            --chance;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!isAuthenticationSuccess){
            throw new RuntimeException("Login failed.");
        }
    }
}
