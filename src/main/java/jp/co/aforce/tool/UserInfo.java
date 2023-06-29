package jp.co.aforce.tool;

import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.User;

public class UserInfo {
	
	public static void setUserInfo(HttpSession session, User user) {
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("lastName", user.getLastName());
		session.setAttribute("firstName", user.getFirstName());
		session.setAttribute("birthYear", user.getBirthYear());
		session.setAttribute("birthMonth", user.getBirthMonth());
		session.setAttribute("birthDay", user.getBirthDay());
		session.setAttribute("phoneNumber", user.getPhoneNumber());
		session.setAttribute("mailAddress", user.getMailAddress());
		session.setAttribute("address", user.getAddress());
	}
	
	public static void removeUserInfo(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("password");
		session.removeAttribute("gender");
		session.removeAttribute("lastName");
		session.removeAttribute("firstName");
		session.removeAttribute("birthYear");
		session.removeAttribute("birthMonth");
		session.removeAttribute("birthDay");
		session.removeAttribute("phoneNumber");
		session.removeAttribute("mailAddress");
		session.removeAttribute("address");
	}
}
