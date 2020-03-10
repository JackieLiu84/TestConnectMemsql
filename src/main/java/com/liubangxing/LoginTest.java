package com.liubangxing;

import java.sql.*;
import java.util.*;

public class LoginTest {
    public static void main(String[] args) {
        Map<String,String> loginInfo = init();
        boolean loginSuccess = login(loginInfo);
        System.out.println(loginSuccess ? "Login Success!" : "Login failed!");
    }

    private static boolean login(Map<String, String> loginInfo) {
        boolean loginSuccess = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://saa090.ip.lab.chn.arrisi.com:3306/tenant1", "root", "12345!");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from job_config");
            if(rs.next()){
                loginSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
        return loginSuccess;
    }


    private static Map<String,String> init() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input login Username:");
        String loginName = sc.nextLine();
        System.out.println("Input login Password:");
        String loginPwd = sc.nextLine();

        Map<String,String> loginInfo = new HashMap<>();
        loginInfo.put("userName",loginName);
        loginInfo.put("userPwd",loginPwd);

        return loginInfo;
    }
}
