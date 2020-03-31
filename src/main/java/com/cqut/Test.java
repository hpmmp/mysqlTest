package com.cqut;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqut.util.ConnectionManager;

import java.sql.*;

/**
 * @author ChenTengfei
 * @date 2019/12/19 11:04
 **/
public class Test {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String tableName = args[0];
            String sql = "select * from "+tableName;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            //printRes(resultSet);
            JSONArray mapper = mapper(resultSet);
            System.out.println(mapper.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JSONArray mapper(ResultSet rSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        //获取列数
        ResultSetMetaData metaData = rSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while(rSet.next()){
            //遍历每一列
            JSONObject jsonObject = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                //获取列名
                String columnName = metaData.getColumnName(i);
                Object value = rSet.getObject(metaData.getColumnName(i));
                jsonObject.put(columnName, value);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    public static void printRes(ResultSet resultSet) throws SQLException {
        ResultSetMetaData data = resultSet.getMetaData();
        while (resultSet.next()){
            int columnCount = data.getColumnCount();
            for(int i=1;i<=columnCount;i++){
                System.out.print(resultSet.getObject(data.getColumnName(i))+" ");
            }
            System.out.println();
        }
    }
}