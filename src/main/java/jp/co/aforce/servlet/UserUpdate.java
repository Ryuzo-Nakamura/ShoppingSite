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
			
			UserDAO dao = new UserDAO();
			
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			String update = request.getParameter("update");
			if(update.equals("info")) {
				user.setPassward(request.getParameter("password"));
				user.setLastName(request.getParameter("lastName"));
				user.setFirstName(request.getParameter("firstName"));
				user.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
				user.setBirthMonth(Integer.parseInt(request.getParameter("birthMonth")));
				user.setBirthDay(Integer.parseInt(request.getParameter("birthDay")));
				user.setPhoneNumber(request.getParameter("phoneNumber").replace("-", ""));
				user.setMailAddress(request.getParameter("mailAddress"));
				user.setAddress(request.getParameter("address"));
				
				boolean error = false;
				
				int countPhoneNumber = dao.search05(user.getUserId(), user.getPhoneNumber());
				if(countPhoneNumber >= 1) {
					error = true;
					session.setAttribute("phoneNumberError", Message.W_C0002.replace("{0}", ItemName.PHONE_NUMBER));
				}
				int countMailAddress = dao.search06(user.getUserId(),user.getMailAddress());
				if(countMailAddress >= 1) {
					error = true;
					session.setAttribute("mailAddressError", Message.W_C0002.replace("{0}", ItemName.MAIL_ADDRESS));
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
				session.setAttribute("update", "info");
			}else if(update.equals("id")){
				String newUserId = request.getParameter("newUserId");
				user.setMailAddress(request.getParameter("mailAddress"));
				int count = dao.search01(newUserId);
				if(count != 0) {
					session.setAttribute("userIdManagementMessage", Message.W_C0002.replace("{0}", ItemName.USER_ID));
					session.setAttribute("newUserId", newUserId);
				}else {
					int line = dao.update02(user, newUserId);
					if(line != 1) {
						session.setAttribute("userIdManagementMessage", Message.E_W0010.replace("{0}", ItemName.USER_ID));
					}else {
						user = dao.search02(newUserId);
						session.setAttribute("userIdManagementMessage", Message.I_W0009.replace("{0}", ItemName.USER_ID));
						session.setAttribute("user", user);
					}
				}
				session.setAttribute("update", "id");
			}else {
				String currentPass = request.getParameter("currentPassword");
				String userPass = dao.search02(user.getUserId()).getPassword();
				String newPass = request.getParameter("newPassword");
				String newPass2 = request.getParameter("newPassword2");
				String errorMessage = "";
				if(!currentPass.equals(userPass)) {
					errorMessage = errorMessage + Message.W_C0006 + "<br>";
				}
				if(!newPass.equals(newPass2)) {
					errorMessage = errorMessage + Message.W_C0007;
				}
				if(errorMessage.length() != 0) {
					session.setAttribute("passwordManagementMessage", errorMessage);
					session.setAttribute("currentPassword", currentPass);
					session.setAttribute("newPassword", newPass);
					session.setAttribute("newPassword2", newPass2);
				} else {
					int line = dao.update03(user, newPass);
					if(line != 1) {
						session.setAttribute("passwordManagementMessage", Message.E_W0010.replace("{0}", ItemName.PASSWARD));
					}else {
						user = dao.search02(user.getUserId());
						session.setAttribute("passwordManagementMessage", Message.I_W0009.replace("{0}", ItemName.PASSWARD));
						session.setAttribute("user", user);
					}
				}
				session.setAttribute("update", "password");
			}
			response.sendRedirect("/ShoppingSite/views/user-info.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

