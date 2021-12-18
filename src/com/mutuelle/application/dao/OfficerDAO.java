package com.mutuelle.application.dao;

import com.hierynomus.sshj.userauth.keyprovider.bcrypt.BCrypt;
import com.mutuelle.application.dao.Interface.OfficerDAOInterface;
import com.mutuelle.databaseConnection.DatabaseConnection;


import java.sql.*;

public class OfficerDAO implements OfficerDAOInterface{

	@Override
    public boolean validateLogin(String email, String password) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        System.out.println(connectDB);
        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(RequetteDAO.LOGIN_QUERY); {
                preparedStatement.setString(1, email);
                System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                	if(BCrypt.checkpw(password,resultSet.getString("password"))) {
                		 return true;
                	}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
