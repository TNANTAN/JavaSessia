package com.idk.fxs.DBpakage;

import com.idk.fxs.User;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
     String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

    Class.forName("com.mysql.cj.jdbc.Driver");
    dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        //System.out.println("Connected To DB");
    return dbConnection;

    }
    public boolean signUpUser(User user){
            String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                    Const.USER_NAME + "," + Const.USER_SURNAME + "," +  Const.USER_LOGIN + "," +
                    Const.USER_PASSWORD + "," + Const.USER_PHONE  + "," + Const.USER_COUNTRY + "," + Const.USER_GENDER + ","+ Const.USER_ROLE+ ", "+ Const.USER_STATUS +")" + " VALUES(?,?,?,?,?,?,?,?,?) ";
            boolean b = false;
            try {
                PreparedStatement ps = getDbConnection().prepareStatement(insert);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurName());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getPhoneNumber());
                ps.setString(6,user.getCountry());
                ps.setString(7,user.getGender());
                ps.setString(8,"0");
                ps.setString(9,"A");
                ps.executeUpdate();
                b = true;
            }catch (SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
            return b;
    }

    public ResultSet getUser(User user){
        ResultSet rs = null;
        String select = "SELECT * FROM " +
                Const.USER_TABLE + " WHERE " +
                Const.USER_LOGIN + "=? AND " +
                Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return rs;
    }
    public ArrayList<User> GetAllUsers(){
        ArrayList<User> userList = new ArrayList<>();
        try{
            PreparedStatement statement = getDbConnection().prepareStatement("SELECT * FROM " + Const.USER_TABLE);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(Const.USER_ID);
                String name = resultSet.getString(Const.USER_NAME);
                String surname = resultSet.getString(Const.USER_SURNAME);
                String login = resultSet.getString(Const.USER_LOGIN);
                String password = resultSet.getString(Const.USER_PASSWORD);
                String phoneNumber = resultSet.getString(Const.USER_PHONE);
                String country = resultSet.getString(Const.USER_COUNTRY);
                String gender = resultSet.getString(Const.USER_GENDER);
                String role = resultSet.getString(Const.USER_ROLE);
                String status = resultSet.getString(Const.USER_STATUS);
                userList.add(new User(id,name,surname,login,password,phoneNumber,country,gender, role,status));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }
    public void deleteUser(User user){
        ResultSet rs = null;
        String select = "DELETE FROM " +
                Const.USER_TABLE + " WHERE " +
                Const.USER_LOGIN + "=? AND " +
                Const.USER_PASSWORD + "=?";
        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.execute();
            System.out.println("Success");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public void deleteUserId(User user){
        ResultSet rs = null;
        String select = "DELETE FROM " +
                Const.USER_TABLE + " WHERE " +
                Const.USER_ID + "=?";
        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(1, String.valueOf(user.getId()));
            ps.execute();
            System.out.println("Success");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void blockUserId(User user){
        String select = "UPDATE " +
                Const.USER_TABLE + " SET " +
                Const.USER_STATUS + "=? WHERE " +
                Const.USER_ID + "=?" ;
        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(2, String.valueOf(user.getId()));
            ps.setString(1, "L");
            ps.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void unblockUserId(User user){
        String select = "UPDATE " +
                Const.USER_TABLE + " SET " +
                Const.USER_STATUS + "=? WHERE " +
                Const.USER_ID + "=?" ;
        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(2, String.valueOf(user.getId()));
            ps.setString(1, "A");
            ps.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
