package com.mutuelle.application.dao;

import com.mutuelle.application.dao.Interface.OfficerDAOInterface;
import com.mutuelle.databaseConnection.DatabaseConnection;

import java.sql.*;

public class OfficerDAO implements OfficerDAOInterface{
	RequetteDAO requetteDAO = new RequetteDAO();
    @Override
    public boolean validateLogin(String email, String password) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        System.out.println(connectDB);
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(RequetteDAO.LOGIN_QUERY); {
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
