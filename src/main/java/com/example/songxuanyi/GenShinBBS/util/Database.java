package com.example.songxuanyi.GenShinBBS.util;

import com.example.songxuanyi.GenShinBBS.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement prestmt = null;

    public Database() {
        try {
            Properties properties = new Properties();
            InputStream in = Database.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(in);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User login(String username, String password) {
        try {
            String sql = "select * from User where username=? and password=?";
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, username);
            prestmt.setString(2, password);
            rs = prestmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return new User(id, username);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User register(String username, String password) {
        try {
            String sql = "insert into User (username,password) values (?,?)";
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, username);
            prestmt.setString(2, password);
            int rscount = prestmt.executeUpdate();
            if (rscount > 0) {
                String nextsql = "select id from User where username=?";
                prestmt = conn.prepareStatement(nextsql);
                prestmt.setString(1, username);
                rs = prestmt.executeQuery();
                rs.next();
                int id = rs.getInt("id");
                return new User(id, username);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
