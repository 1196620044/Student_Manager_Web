package com.myschool.programmer.dao;

import com.myschool.programmer.model.Clazz;
import com.myschool.programmer.model.Page;
import com.myschool.programmer.util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 86176
 * @Date: 2020/6/12 11:34
 * @Description:
 *班级信息数据库操作
 */
public class ClazzDao extends BaseDao {
    public List<Clazz> getClazzList(Clazz clazz,Page page){
        List<Clazz> ret = new ArrayList<>();
        String sql = "select * from s_clazz ";
        if(!StringUtil.isEmpty(clazz.getName())){
            sql += "where name like '%" + clazz.getName() + "%'";
        }
        sql += " limit " + page.getStart() + "," + page.getPageSize();//分页显示
        ResultSet resultSet = query(sql);//执行查询语句
        try {
            //对查询的数据进行循环
            while(resultSet.next()){
                Clazz cl = new Clazz();
                cl.setId(resultSet.getInt("id"));
                cl.setName(resultSet.getString("name"));
                cl.setInfo(resultSet.getString("info"));
                ret.add(cl);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
    public int getClazzListTotal(Clazz clazz){
        int total = 0;
        String sql = "select count(*)as total from s_clazz ";
        if(!StringUtil.isEmpty(clazz.getName())){
            sql += "where name like '%" + clazz.getName() + "%'";
        }
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return total;
    }
    public boolean addClazz(Clazz clazz){
        //sql语句
        String sql = "insert into s_clazz values(null,'"+clazz.getName()+"','"+clazz.getInfo()+"') ";
        //更新数据库
        return update(sql);
    }
    public boolean deleteClazz(int id){
        //sql语句
        String sql = "delete from s_clazz where id = "+id;
        //更新数据库
        return update(sql);
    }
    public boolean editClazz(Clazz clazz) {
        // TODO Auto-generated method stub
        //sql语句
        String sql = "update s_clazz set name = '"+clazz.getName()+"',info = '"+clazz.getInfo()+"' where id = " + clazz.getId();
        //更新数据库
        return update(sql);
    }

}
