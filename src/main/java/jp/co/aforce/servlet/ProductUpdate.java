package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Product;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-update")
public class ProductUpdate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String productId = request.getParameter("productId");
			
			Product product = new Product();
			product.setProductId(productId);
			product.setNumber(Integer.parseInt(request.getParameter("number")));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setDescription(request.getParameter("description"));
			
			ProductDAO dao = new ProductDAO();
			int line = dao.update01(product);
			
			if(line != 1) {
				session.setAttribute("mangaManagementMessage", Message.E_W0005);
			}else {
				product = dao.search02(productId);
				String mangaId = product.getMangaId();
				String number = String.valueOf(product.getNumber());
				while(number.length() < 3) {
					number = "0" + number;
				}
				String newProductId = mangaId + number;
				dao.update02(product, newProductId);
				session.setAttribute("adminProduct", dao.search02(newProductId));
				session.setAttribute("productManagementMessage", Message.I_W0005.replace("{0}",newProductId));
			}
			response.sendRedirect("/ShoppingSite/views/product-info.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
