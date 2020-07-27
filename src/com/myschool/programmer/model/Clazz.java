package com.myschool.programmer.model;

/**
 * @Author: 86176
 * @Date: 2020/6/12 11:30
 * @Description:
 *班级实体表
 */
public class Clazz {
    private int id;
    private String name;
    private String info;
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
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
