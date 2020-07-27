
package com.myschool.programmer.servlet;

import com.myschool.programmer.dao.ClazzDao;
import com.myschool.programmer.model.Clazz;
import com.myschool.programmer.model.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 86176
 * @Date: 2020/6/12 11:07
 * @Description:
 *班级信息管理servlet
 */
public class ClazzServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //网页获取使用者进行什么选项
        String method = request.getParameter("method");
        //通过equals方法进行判断
        if("toClazzListView".equals(method)){
            //显示班级列表页面
            clazzList(request,response);
        }else if("getClazzList".equals(method)){
            //获取班级列表数据
            getClazzList(request, response);
        }else if("AddClazz".equals(method)){
            //添加班级
            addClazz(request, response);
        }else if("DeleteClazz".equals(method)){
            //删除班级
            deleteClazz(request, response);
        }else if("EditClazz".equals(method)){
            //编辑班级
            editClazz(request, response);
        }
    }
    //编辑班级方法
    private void editClazz(HttpServletRequest request,
                           HttpServletResponse response) {
        // TODO Auto-generated method stub
        //网页获取输入的班级名称，id以及简介
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        Clazz clazz = new Clazz();
        //调用set方法修改班级名称，id以及简介
        clazz.setName(name);
        clazz.setInfo(info);
        clazz.setId(id);
        ClazzDao clazzDao = new ClazzDao();
        //调用clazzDao的editClazz方法
        if(clazzDao.editClazz(clazz)){
            try {
                //修改成功
                response.getWriter().write("success");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                //用完数据库，要关闭数据库
                clazzDao.closeCon();
            }
        }
    }
    private void deleteClazz(HttpServletRequest request,
                             HttpServletResponse response) {
        // TODO Auto-generated method stub
        //获取班级id
        Integer id = Integer.parseInt(request.getParameter("clazzid"));
        ClazzDao clazzDao = new ClazzDao();
        //删除班级
        if(clazzDao.deleteClazz(id)){
            try {
                //向网页发出删除成功
                response.getWriter().write("success");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                //关闭数据库
                clazzDao.closeCon();
            }
        }
    }
    private void addClazz(HttpServletRequest request,
                          HttpServletResponse response) {
        // TODO Auto-generated method stub
        //获取输入的班级名称及班级简介
        String name = request.getParameter("name");
        String info = request.getParameter("info");
        //新增班级对象
        Clazz clazz = new Clazz();
        //给班级取名，写简介
        clazz.setName(name);
        clazz.setInfo(info);
        ClazzDao clazzDao = new ClazzDao();
        //添加班级
        if(clazzDao.addClazz(clazz)){
            try {
                response.getWriter().write("success");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                clazzDao.closeCon();
            }
        }

    }
    private void clazzList(HttpServletRequest request,
                           HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            //显示班级列表
            request.getRequestDispatcher("view/clazzList.jsp").forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void getClazzList(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("clazzName");
        Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));
        Clazz clazz = new Clazz();
        clazz.setName(name);
        ClazzDao clazzDao = new ClazzDao();
        List<Clazz> clazzList = clazzDao.getClazzList(clazz, new Page(currentPage, pageSize));
        int total = clazzDao.getClazzListTotal(clazz);
        clazzDao.closeCon();
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("total", total);
        ret.put("rows", clazzList);
        try {
            String from = request.getParameter("from");
            if("combox".equals(from)){
                response.getWriter().write(JSONArray.fromObject(clazzList).toString());
            }else{
                response.getWriter().write(JSONObject.fromObject(ret).toString());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
