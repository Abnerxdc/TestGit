package com.bc.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;


/**
 * Created by Administrator on 2017/5/31.
 */
public class DBDao {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://127.0.0.1:3306/Database?useUnicode=true&characterEncoding=GBK";
    public static final String username = "root";
    public static final String password = "xu5219";

    /**
     * 数据库执行查询操作
     * @param sql
     * @return resultArray(JsonArray格式)
     */
    public static JSONArray select(String sql){
        JSONArray resultArray = new JSONArray();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                //获取列的数量
                int columnCount = data.getColumnCount();
                for(int i =1;i<columnCount+1;i++){
                    String columnName = data.getColumnName(i);

                    String columnValue = rs.getString(i);
                    jsonObject.put(columnName, columnValue);
                }
                resultArray.add(jsonObject);
            }
            rs.close();
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultArray;
    }

    /**
     * 当程序执行插入 、删除、更新等操作
     * @param sql
     * @return boolean
     */
    public static boolean update(String sql){
        boolean result = false;
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            int i = stm.executeUpdate(sql);
            if(i>0){
                result = true;
            }else{
                result = false;
            }
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 只查询一条数据，返回JsonObject
     * @param sql
     * @return
     */
    public static JSONObject selectOne(String sql){
        JSONObject resultObject = new JSONObject();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()){
                //获取列的数量
                int columnCount = data.getColumnCount();
                for(int i =1;i<columnCount+1;i++){
                    String columnName = data.getColumnName(i);
                    String columnValue = rs.getString(i);
                    resultObject.put(columnName, columnValue);
                }
            }
            rs.close();
            stm.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultObject;
    }
}

