package sample.database;

import java.sql.*;

public class DataBase {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DataBase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void setConnection(){
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/feedback?"
                    + "user=sqluser&password=sqluserpw");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private void closeConnection(){
        try{
            if(connect !=null){
                connect.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
