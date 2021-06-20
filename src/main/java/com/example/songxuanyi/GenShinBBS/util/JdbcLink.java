package com.example.songxuanyi.GenShinBBS.util;

import java.sql.*;

public class JdbcLink {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/HtmlDB?serverTimezone=UTC";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Darkness";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }

    public static void close(ResultSet rs,Statement stat,Connection conn){
        if (rs!=null) {
            try {
                rs.close();
                stat.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
