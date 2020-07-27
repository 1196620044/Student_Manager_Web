package com.myschool.programmer.model;

/**
 * @Author: 86176
 * @Date: 2020/6/11 23:27
 * @Description:
 * 管理员实体类
 */
public class Admin {
    private int id;
    private String name;
    private String password;
    private int status = 1;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
