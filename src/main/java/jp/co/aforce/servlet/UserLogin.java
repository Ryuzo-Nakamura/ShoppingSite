package jp.co.aforce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.UserDAO;


@WebServlet("/servlet/user-login")
public class UserLogin extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			
			UserDAO dao = new UserDAO();
			int count = dao.search01(userId);	// ユーザーIDが存在するかどうかチェック
			if(count != 1) {
				session.setAttribute("loginMessage", Message.W_C0001);
				session.setAttribute("userId", userId);
				session.setAttribute("password", password);
				response.sendRedirect("/ShoppingSite/views/user-login.jsp");
			}else {
				User user = dao.search02(userId); // ユーザー情報を取得
				if(user.getPassword().equals(password)) {	// パスワードが正しいかどうかチェック
					session.setAttribute("user", user);
					request.getRequestDispatcher("/servlet/cart-list").include(request, response);
					response.sendRedirect("/ShoppingSite/views/user-home.jsp"); // ログイン成功時、ホーム画面に遷移
				}else {
					session.setAttribute("loginMessage", Message.W_C0001);
					session.setAttribute("userId", userId);
					session.setAttribute("password", password);
					response.sendRedirect("/ShoppingSite/views/user-login.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

}
