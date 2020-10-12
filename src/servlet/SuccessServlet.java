package servlet;

import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request域中共享的user对象
        User user = (User)request.getAttribute("user");
        if(user != null){
            //设置编码
            response.setContentType("text/html;charset=utf-8");
            //输出
            response.getWriter().write("登录成功"+user.getUsername()+",欢迎您");
            //通过name获取value
            String parameter = request.getParameter("tenDayLogin");
            if("ok".equals(parameter)) {//说明用户选择了十天内免登陆
                //创建Cookie对象
                Cookie cookie1 = new Cookie("username",username);
                Cookie cookie2 = new Cookie("password",password);
                //设置有效时间
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                //设置关联路径,默认根路径就行
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                //发送Cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
