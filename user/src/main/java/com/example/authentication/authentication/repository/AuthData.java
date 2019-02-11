package com.example.authentication.authentication.repository;

import backend.shared.enums.Role;
import backend.shared.models.User;
import com.example.authentication.authentication.models.UserWrapper;
import com.example.authentication.authentication.utility.Password;
import com.example.authentication.authentication.utility.RandomString;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthData {

    protected static final ArrayList<UserWrapper> loggedinusers = new ArrayList<>();
    static RandomString session = new RandomString();
    private static final Logger LOGGER = Logger.getLogger(AuthData.class.getName());

    public int getIdByAuthKey(String authkey){
        return loggedinusers.stream().filter(o -> o.getToken().equals(authkey)).findFirst().get().getUserId();
    }

    public User getUser(String authkey) {
        int id = getIdByAuthKey(authkey);

        try(Connection con = DriverManager.getConnection(DatabaseConnection.connectionUrl);Statement statement = con.createStatement();PreparedStatement pstmt = con.prepareStatement("SELECT * FROM [User] WHERE Id = ?")) {
            pstmt.setString(1, String.valueOf(id));

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return new User(rs.getString("fullName"), rs.getString("email"), Role.values()[rs.getInt("role")]);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,e.toString(), e);
        }

        return null;
    }

    public String loginUser(String email, String password) {

        try(Connection con = DriverManager.getConnection(DatabaseConnection.connectionUrl);PreparedStatement pstmt = con.prepareStatement("SELECT * FROM [User] WHERE email = ?")) {

            pstmt.setString(1, email);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                String passhash = rs.getString("password");

                if (Password.checkPassword(password, passhash)) {
                    UserWrapper wrap = new UserWrapper(session.nextString(), rs.getInt("id"));
                    loggedinusers.add(wrap);
                    return wrap.getToken();
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,e.toString(), e);
        }
        return null;
    }

    public int registerUser(User user, String password) {

        try(Connection con = DriverManager.getConnection(DatabaseConnection.connectionUrl);Statement statement = con.createStatement();PreparedStatement pstmt = con.prepareStatement("INSERT INTO [User] (fullName, email, password) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, Password.hashPassword(password));

            pstmt.executeQuery();
            try(ResultSet rs = pstmt.getGeneratedKeys()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,e.toString(), e);
        }
        return 0;
    }
}
