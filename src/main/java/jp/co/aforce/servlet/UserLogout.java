package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/servlet/user-logout")
public class UserLogout extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			session.removeAttribute("user");
			session.removeAttribute("cart");
			session.removeAttribute("searchWord");
			session.removeAttribute("manga");
			session.removeAttribute("product");
			session.removeAttribute("item");
			session.removeAttribute("amount");
			session.removeAttribute("mangaList");
			session.removeAttribute("productList");
			response.sendRedirect("/ShoppingSite/views/user-login.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
