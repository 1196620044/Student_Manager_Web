package com.myschool.programmer.model;

/**
 * @Author: 86176
 * @Date: 2020/6/15 15:22
 * @Description:
 *选课表实体
 */
public class SelectedCourse {
    private int id;
    private int studentId;
    private int courseId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}
