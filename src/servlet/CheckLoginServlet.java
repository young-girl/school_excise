package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //获取cookie
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;

        if(cookies != null) {
            //遍历cookie
            for(Cookie cookie : cookies) {
                if("username".equals(cookie.getName())) {//看cookie的name是不是与cookie的名字匹配
                    username = cookie.getValue();
                }else if("password".equals(cookie.getName())){
                    password = cookie.getValue();
                }
            }
        }
        if(username != null && password != null) {//
            //连接JDBC数据库验证用户名跟密码
            Connection conn =null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean login = false;//判断是否登录成功
            String realName = null;//用户
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donglijiedian","root","zhixi158");
                String sql = "select * from user where username=? and password=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);

                rs = ps.executeQuery();
                if(rs.next()) {
                    login = true;
                    realName = rs.getString("realname");
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                if(rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                }
                if(ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                }
                if(conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }
                }
            }

            //跳转页面
            if(login) {//登录成功
                System.out.print("欢迎"+ realName +"登录成功！");
            }else {//登录失败
                System.out.print("登录失败，请检查用户名或者密码！");
            }
        }else {//cookie验证失败，跳转到登录页面
            response.sendRedirect(request.getContextPath()+"/index.html");
        }

    }

}
}
