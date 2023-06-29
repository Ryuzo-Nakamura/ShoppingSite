package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.bean.Product;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-management")
public class ProductManagement extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String productId = request.getParameter("productId");
			
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.search02(productId);
			
			MangaDAO dao = new MangaDAO();
			Manga manga = dao.search03(product.getMangaId());
			
			session.setAttribute("adminProduct", product);
			session.setAttribute("adminManga", manga);
			response.sendRedirect("/ShoppingSite/views/product-info.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
