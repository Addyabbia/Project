package com.nf.util;

import java.sql.*;

public class ConnHelper {
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=";
    private static final String DATABASE_NAME = "bdc3";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "123456";

    //不管创建多少个实例，此代码只执行一次
    //因为整个application都是运行在同一个tomcat中，
    //所以，数据库驱动，加载一次就够
    static {
        try {
            Class.forName( DRIVER );
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动有问题："+e.getMessage());
        }
    }

    public static Connection getConnection(){
        try {
            return  DriverManager.getConnection( URL+DATABASE_NAME,USER_NAME,PASSWORD );
        } catch (SQLException e) {
            System.out.println("获取连接失败："+e.getMessage());
            return null;
        }
    }

    public static void close(ResultSet rs, PreparedStatement pst,Connection conn ){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("关闭ResultSet失败："+e.getMessage());
            }
        }

        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println("关闭pst失败："+e.getMessage());
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败：" + e.getMessage());
            }
        }
    }

}
