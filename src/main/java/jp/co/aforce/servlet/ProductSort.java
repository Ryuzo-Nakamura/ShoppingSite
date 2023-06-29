package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Product;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-sort")
public class ProductSort extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String mangaId = request.getParameter("mangaId");
			int sort = Integer.parseInt(request.getParameter("sort"));
			ProductDAO productDAO = new ProductDAO();
			List<Product> productList = productDAO.search03(mangaId); // 同じマンガIDをもつ製品のリストを取得
			if(sort == 2) {
					Collections.reverse(productList);
			}
			session.setAttribute("sort", sort);
			
			session.setAttribute("productList", productList);
			response.sendRedirect("/ShoppingSite/views/product-list.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
