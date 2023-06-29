package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.dao.ItemDAO;

@WebServlet("/servlet/cart-delete")

public class CartDelete extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String itemId = request.getParameter("itemId");
			ItemDAO itemDAO = new ItemDAO();
			if(itemId != null) {
				itemDAO.delete01(itemId);
			}else {
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				itemDAO.delete02(user.getUserId());
			}
			
			
			request.getRequestDispatcher("/servlet/cart-list").include(request, response);
			response.sendRedirect("/ShoppingSite/views/cart-list.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
