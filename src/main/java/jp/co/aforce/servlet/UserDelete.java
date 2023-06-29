package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.UserDAO;


@WebServlet("/servlet/user-delete")
public class UserDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String uri = request.getParameter("URI");
		
			String userId = request.getParameter("userId");
			
			UserDAO dao = new UserDAO();
			int line = dao.delete01(userId);
			if(line != 1) {
				session.setAttribute("userManagementMessage", Message.E_W0003);
				response.sendRedirect(uri);
			}else {
				session.removeAttribute("user");
				response.sendRedirect("/ShoppingSite/views/user-deleted.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

