
package com.myschool.programmer.servlet;

import com.myschool.programmer.dao.AdminDao;
import com.myschool.programmer.dao.StudentDao;
import com.myschool.programmer.dao.TeacherDao;
import com.myschool.programmer.model.Admin;
import com.myschool.programmer.model.Student;
import com.myschool.programmer.model.Teacher;
import com.myschool.programmer.util.StringUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Author: 86176
 * @Date: 2020/6/11 19:11
 * @Description: 登录验证servlet
 */
public class LoginServlet extends HttpServlet {


    private static final long serialVersionUID = -5870852067427524781L;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String method = request.getParameter("method");
        //退出
        if ("logout".equals(method)) {
            //调用logout方法
            logout(request, response);
            return;
        }

        //获取用户信息
        String vcode = request.getParameter("vcode");
        String name = request.getParameter("account");
        String password = request.getParameter("password");
        int type = Integer.parseInt(request.getParameter("type"));
        String loginCpacha = request.getSession().getAttribute("loginCapcha").toString();

        if (StringUtil.isEmpty(vcode)) {
            response.getWriter().write("vcodeError");//登录失败
            return;
        }
        //先判断验证码是否正确
        if (!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {//如果验证码不正确
            response.getWriter().write("vcodeError");//向网页返回错误信息
            return;
        }
        //验证码验证通过，对比用户名密码是否正确
        //先给登录状态变量赋值，赋值为登录失败
        String loginStatus = "loginFaild";
        //判断登录类型
        switch (type) {
            //1为管理员类，执行这段代码
            case 1: {
                AdminDao adminDao = new AdminDao();
                //判断用户名密码是否正确
                //long startTime = System.currentTimeMillis();
                Admin admin = adminDao.login(name, password);
                //long endTime = System.currentTimeMillis();
                //System.out.println("登录花费时间" + (endTime-startTime)+"毫秒");
                //关闭数据库，释放资源
                adminDao.closeCon();
                //如果返回为空，则程序失败
                if (admin == null) {
                    response.getWriter().write("loginError");
                    return;
                }
                //程序进行到这里，说明用户名及密码正确
                HttpSession session = request.getSession();
                //将用户信息放入session里
                session.setAttribute("user", admin);
                session.setAttribute("userType", type);
                //登录状态改为登录成功
                loginStatus = "loginSuccess";
                break;
            }
            //2为学生类
            case 2: {
                StudentDao studentDao = new StudentDao();
                //调用studentDao的login方法
                Student student = studentDao.login(name, password);
                //关闭数据库
                studentDao.closeCon();
                //如果返回为空，则登录失败
                if (student == null) {
                    response.getWriter().write("loginError");
                    return;
                }
                //程序到此，登陆成功
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                session.setAttribute("userType", type);
                //登录状态改为登录成功
                loginStatus = "loginSuccess";
                break;
            }
            //3为教师类
            case 3: {
                TeacherDao teahcerDao = new TeacherDao();

                //判断用户名及密码
                Teacher teacher = teahcerDao.login(name, password);
                //关闭数据库
                teahcerDao.closeCon();
                //如果返回null，判断老师不存在
                if (teacher == null) {
                    //登陆失败
                    response.getWriter().write("loginError");
                    return;
                }
                //登录验证成功
                HttpSession session = request.getSession();
                session.setAttribute("user", teacher);
                session.setAttribute("userType", type);
                //登陆成功
                loginStatus = "loginSuccess";
                break;
            }
            default:
                break;
        }
        //向网页返回登录状态
        response.getWriter().write(loginStatus);
    }

    //登出，返回主界面
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userType");
        response.sendRedirect("index.jsp");

    }
}
