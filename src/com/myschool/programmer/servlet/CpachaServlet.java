package com.myschool.programmer.servlet;

import com.myschool.programmer.util.CpachaUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: 86176
 * @Date: 2020/6/11 17:46
 * @Description:
 * 验证码servlet
 */
public class CpachaServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4919529414762301338L;
    public void doGet(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
        doPost(request, reponse);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
        String method = request.getParameter("method");
        if("loginCapcha".equals(method)){
            generateLoginCpacha(request, reponse);
            return;
        }
        reponse.getWriter().write("error method");
    }

    /**
     * 生成验证码
     * @param request
     * @param reponse
     * @throws IOException
     */
    private void generateLoginCpacha(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCapcha", generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        ImageIO.write(generatorRotateVCodeImage, "gif", reponse.getOutputStream());
    }
}

