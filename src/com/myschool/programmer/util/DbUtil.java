package com.myschool.programmer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @Author: 86176
 * @Date: 2020/6/12 10:07
 * @Description:
 *数据库链接util
 */
public class DbUtil {

    //使用资源绑定器绑定属性配置文件
    ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private String driver = bundle.getString("driver");
    private String dbUrl = bundle.getString("url");
    private String dbUser = bundle.getString("user");
    private String dbPassword = bundle.getString("password");


    private Connection connection = null;
    public Connection getConnection(){
        try {
            //类加载机制执行静态代码块，进行驱动注册
            Class.forName(driver);
            //获取连接
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            //System.out.println("数据库链接成功！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("数据库链接失败！");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeCon(){
        if(connection != null)
            try {
                connection.close();
                //System.out.println("数据库链接已关闭！");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DbUtil dbUtil = new DbUtil();
        dbUtil.getConnection();
    }

}
