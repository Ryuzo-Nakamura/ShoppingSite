package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Admin;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.AdminDAO;


@WebServlet("/servlet/admin-login")
public class AdminLogin extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String adminId = request.getParameter("adminId");
			String password = request.getParameter("adminPassword");
			
			
			AdminDAO dao = new AdminDAO();
			int count = dao.search01(adminId);
			if(count != 1) {
				session.setAttribute("adminLoginMessage", Message.W_C0001);
				session.setAttribute("userId", adminId);
				session.setAttribute("adminPassword", password);
				response.sendRedirect("/ShoppingSite/views/admin-login.jsp");
			}else {
				Admin admin = dao.search02(adminId);
				if(admin.getPassword().equals(password)) {
					session.setAttribute("admin", admin);
					response.sendRedirect("/ShoppingSite/views/admin-home.jsp");
				}else {
					session.setAttribute("adminLoginMessage", Message.W_C0001);
					session.setAttribute("adminId", adminId);
					session.setAttribute("adminPassword", password);
					response.sendRedirect("/ShoppingSite/views/admin-login.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
