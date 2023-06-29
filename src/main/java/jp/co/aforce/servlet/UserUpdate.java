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


@WebServlet("/servlet/user-update")
public class UserUpdate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String uri = request.getParameter("URI");
		
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			user.setPassward(request.getParameter("password"));
			user.setLastName(request.getParameter("lastName"));
			user.setFirstName(request.getParameter("firstName"));
			user.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
			user.setBirthMonth(Integer.parseInt(request.getParameter("birthMonth")));
			user.setBirthDay(Integer.parseInt(request.getParameter("birthDay")));
			user.setPhoneNumber((String)request.getParameter("phoneNumber").replace("-", ""));
			user.setMailAddress(request.getParameter("mailAddress"));
			user.setAddress(request.getParameter("address"));
			
			boolean error = false;
			
			UserDAO dao = new UserDAO();
			int countPhoneNumber = dao.search05(user.getUserId(), user.getPhoneNumber());
			if(countPhoneNumber >= 1) {
				error = true;
				session.setAttribute("phoneNumberError", Message.W_C0002.replace("{0}", ItemName.PHONE_NUMBER));
			}
			int countMailAddress = dao.search06(user.getUserId(),user.getMailAddress());
			if(countMailAddress >= 1) {
				error = true;
				session.setAttribute("mailAddressError", Message.W_C0002.replace("{0}", ItemName.MAIL＿ADDRESS));
			}
			UserInfo.setUserInfo(session, user);
			if(!error) {
				int line = dao.update01(user);
				if(line != 1) {
					session.setAttribute("userManagementMessage", Message.E_W0002);
				}else {
					session.setAttribute("user", user);
					session.setAttribute("userManagementMessage", Message.I_W0007);
				}
			}
			response.sendRedirect(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

