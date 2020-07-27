
package com.myschool.programmer.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 86176
 * @Date: 2020/6/12 00:03
 * @Description:
 *拦截用户未登录状态下的操作
 */
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)rep;
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            //未登录
            response.sendRedirect("/Web/index.jsp");
            return;
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
