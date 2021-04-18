package com.HZC.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class myConn{
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mytestdb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "root";
    private static int minSize = 5;
    private static int maxSize = 10;
    private static LinkedList<Connection> connections = new LinkedList<>();

    static {//最先加载
        try {
            Class.forName(driver);//进行一个驱动的加载，jdbc8会自动加载,但最好还是手动加载
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {//这个方法初始化connectio链表的五个接口
            connections.addFirst(initCoon());
        }
    }


    private static Connection initCoon(){//仅供内部调用，用来初始化多接口集合或者增加(只在该类内部使用)

        try {
            return DriverManager.getConnection(url,user,password);//创建成功返回一个接口
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;//建立接口失败就返回一个null值
    }

    public static Connection borrowOne(){//借一个接口使用
        if(connections.size()>0&&connections.size()<=maxSize){
            return connections.removeFirst();
        }else {
            connections.addFirst(initCoon());
            return connections.removeFirst();
        }//接口列表中有东西就返回一个
    }

    public static int returnOne(Connection con){
        if(null != con){//接口不为空则返回给它放回列表
            connections.addLast(con);
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 1;
        }else {
            return 0;
        }

    }



}//End



