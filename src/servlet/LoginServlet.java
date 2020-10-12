package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //����request����
	request.setCharacterEncoding("utf-8");
	//��ȡ����
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String checkCode = request.getParameter("checkCode");
	//��ȡ���ɵ���֤��
	HttpSession session = request.getSession();
	String checkCode_session = (String) session.getAttribute("checkCode_session");
	//ɾ��session�д洢����֤��
	session.removeAttribute("checkCode_session");
	//�ж���֤���Ƿ���ȷ
	if(checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)){
		//���Դ�Сд�Ƚϣ���֤����ȷ���ж��û��������Ƿ�һ��
		if("zhangsan".equals(username) && "123".equals(password)){//��Ҫ����UserDao��ѯ���ݿ�
			//��¼�ɹ����洢��Ϣ���û���Ϣ
			session.setAttribute("user", username);
			//�ض���success.jsp
			response.sendRedirect(request.getContextPath()+"/success.jsp");
		}else{
			//��¼ʧ�ܣ��洢��ʾ��Ϣ��request
			request.setAttribute("login_error", "�û������������");
			//ת������¼ҳ��
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	
	}else{
		//��֤�벻һ�£��洢��ʾ��Ϣ��request
		request.setAttribute("cc_error", "��֤�����");
		//ת������¼����
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	this.doPost(request,response);
}
}