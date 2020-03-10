package com.liubangxing;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args){
        System.out.println("------Starting to connect to database------");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //1, register driver
            Class.forName("com.mysql.jdbc.Driver");
            //2, get  connection
            conn = DriverManager.getConnection("jdbc:mysql://saa090.ip.lab.chn.arrisi.com:3306/tenant1", "root", "12345!");
            //3, get database operation object
            stmt = conn.createStatement();
            //4, execute the db sql
            rs = stmt.executeQuery("select * from job_config");
            //5, get the sql result
            while(rs.next()){
                System.out.println(rs.getString("job_id") + " " + rs.getString("job_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("------close connection from db------");
            //6, release the resource
            if(rs != null) {
                try {
                        rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
