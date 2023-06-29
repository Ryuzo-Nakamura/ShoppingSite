package jp.co.aforce.tool;

import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Product;

public class ProductInfo {
	
	public static void setProductInfo(HttpSession session, Product item) {
		session.setAttribute("number", item.getNumber());
		session.setAttribute("price", item.getPrice());
		session.setAttribute("description", item.getDescription());
	}
	
	public static void removeProductInfo(HttpSession session) {
		session.removeAttribute("number");
		session.removeAttribute("price");
		session.removeAttribute("description");
	}
	
}
