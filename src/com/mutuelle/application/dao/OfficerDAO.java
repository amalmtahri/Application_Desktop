package com.mutuelle.application.dao;

import com.mutuelle.application.dao.Interface.OfficerDAOInterface;
import com.mutuelle.databaseConnection.DatabaseConnection;

import java.sql.*;

public class OfficerDAO implements OfficerDAOInterface{

    private static final String SELECT_QUERY = "SELECT * FROM officer WHERE email = ? and password = ?";

    @Override
    public boolean validateLogin(String email, String password) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        System.out.println(connectDB);
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(SELECT_QUERY); {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
