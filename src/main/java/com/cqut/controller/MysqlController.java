package com.cqut.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqut.util.ConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author ChenTengfei
 * @date 2019/12/19 15:43
 **/
@Controller
public class MysqlController {

    @RequestMapping("/getInfo")
    @ResponseBody
    public JSONArray getInfo(@RequestParam("tableName") String tableName ){
        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            String sql = "select * from "+tableName;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            JSONArray jsonArray = new JSONArray();
            //获取列数
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while(resultSet.next()){
                //遍历每一列
                JSONObject jsonObject = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(metaData.getColumnName(i));
                    jsonObject.put(columnName, value);
                }
                jsonArray.add(jsonObject);
            }
            return  jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}