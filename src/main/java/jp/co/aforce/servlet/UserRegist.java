package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.User;
import jp.co.aforce.constant.Constant.ItemName;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.UserInfo;


@WebServlet("/servlet/user-regist")
public class UserRegist extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {		
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			user.setPassward(password);
			user.setLastName(request.getParameter("lastName"));
			user.setFirstName(request.getParameter("firstName"));
			user.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
			user.setBirthMonth(Integer.parseInt(request.getParameter("birthMonth")));
			user.setBirthDay(Integer.parseInt(request.getParameter("birthDay")));
			user.setPhoneNumber((String)request.getParameter("phoneNumber").replace("-", ""));
			user.setMailAddress(request.getParameter("mailAddress"));
			user.setAddress(request.getParameter("address"));
			
			boolean error = false; // エラーがある場合true,ない場合false
			
			UserDAO dao = new UserDAO();
			int countUserId = dao.search01(user.getUserId()); // ユーザーIDが存在するかどうかチェック
			if(countUserId != 0) {
				error = true;
				session.setAttribute("userIdError",Message.W_C0002.replace("{0}", ItemName.USER_ID));
			}
			if(!password.equals(password2)) { // ２つのパスワードが同じかどうかチェック
				error = true;
				session.setAttribute("passwordError", Message.W_C0003);
			}
			int countPhoneNumber = dao.search03(user.getPhoneNumber()); // 入力された電話番号がすでに登録されてないかどうかチェック
			if(countPhoneNumber >= 1) {
				error = true;
				session.setAttribute("phoneNumberError", Message.W_C0002.replace("{0}", ItemName.PHONE_NUMBER));
			}
			int countMailAddress = dao.search04(user.getMailAddress());	// 入力されたメールアドレスがすでに登録されてないかどうかチェック
			if(countMailAddress >= 1) {
				error = true;
				session.setAttribute("mailAddressError", Message.W_C0002.replace("{0}", ItemName.MAIL_ADDRESS));
			}
			UserInfo.setUserInfo(session, user); // セッション属性にユーザー情報をセット
			if(error) {
				session.setAttribute("password2", password2);
				response.sendRedirect("/ShoppingSite/views/user-regist.jsp");
			}else {
				int line = dao.insert01(user);
				if(line != 1) {
					session.setAttribute("password2", password2);
					session.setAttribute("registMessage", Message.E_W0001);
					response.sendRedirect("/ShoppingSite/views/user-regist.jsp");
				}else {
					response.sendRedirect("/ShoppingSite/views/user-registered.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

