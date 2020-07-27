package com.myschool.programmer.dao;


import com.myschool.programmer.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 86176
 * @Date: 2020/6/11 23:32
 * @Description:
 *管理员数据库操作封装
 */
public class AdminDao extends BaseDao {

    public Admin login(String name , String password){
        //创建一个Map集合对象，储存用户名及密码
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);

        String sql = "select * from s_admin where name = ? and password = ?";

        ResultSet resultSet = query(sql,map);
        try {
            if(resultSet.next()){
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
                admin.setStatus(resultSet.getInt("status"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //修改密码
    public boolean editPassword(Admin admin , String newPassword) {
        String sql = "update s_admin set password = '"+newPassword+"' where id = " + admin.getId();
        //改
        return update(sql);
    }
}
