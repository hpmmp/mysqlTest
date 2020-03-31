package com.cqut;

import com.cqut.util.DESUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenTengfei
 * @date 2019/12/23 15:11
 **/
public class Test2 {
    public static void main(String[] args) {
        Map<String, Object> infos = new HashMap();
        String userInfo_str = "1234!@#qaz";


        if (userInfo_str != null) {
            String[] array_str = DESUtil.decrypt(userInfo_str).split("#");
            if (array_str != null && array_str.length > 0) {
                String[] arr$ = array_str;
                int len$ = array_str.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String s = arr$[i$];
                    String[] temp = s.split(":");
                    if (temp.length == 2) {
                        infos.put(temp[0], temp[1]);
                    }
                }
            }
        }
        System.out.println(infos.get("LoginId").toString());
    }
}