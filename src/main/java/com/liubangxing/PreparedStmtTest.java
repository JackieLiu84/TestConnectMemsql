package com.liubangxing;

import java.sql.*;

public class PreparedStmtTest {
    public static void main(String[] args) {
        System.out.println("------Starting to connect to database------");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //1, register driver
            Class.forName("com.mysql.jdbc.Driver");
            //2, get  connection
            conn = DriverManager.getConnection("jdbc:mysql://saa061.ip.lab.chn.arrisi.com:3306/tenant1", "root", "12345!");
            //3, get prepared statement object
            String  sqlString = "select * from job_config where job_id = ? or job_name like ?";
            ps = conn.prepareStatement(sqlString);
            //4, set the sql parameters and execute the db sql
            ps.setInt(1,4);
            ps.setString(2,"%cm_scq_up_day%");
            rs = ps.executeQuery();
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
            if(ps != null) {
                try {
                    ps.close();
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
