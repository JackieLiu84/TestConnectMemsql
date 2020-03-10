package com.liubangxing;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCToolTest {
    public static void main(String[] args) {
        String connectionString = "jdbc:mysql://saa090.ip.lab.chn.arrisi.com:3306/tenant1";
        String user = "root";
        String password = "12345!";
        String sql = "select * from job_history";
        Connection conn = null;
        PreparedStatement  ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCTool.createConnection(connectionString,user,password);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            JDBCTool.closeConnection(rs,ps,conn);
        }

    }
}
