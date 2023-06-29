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

import jp.co.aforce.bean.Manga;
import jp.co.aforce.bean.Product;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-list")
public class ProductList extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String uri = request.getParameter("uri");
			String mangaId = request.getParameter("mangaId");
			
			MangaDAO mangaDAO = new MangaDAO();
			Manga manga = mangaDAO.search03(mangaId);	// マンガIDからマンガ情報を取得
			
			ProductDAO productDAO = new ProductDAO();
			List<Product> productList = productDAO.search03(mangaId); // 同じマンガIDをもつ製品のリストを取得
			
			if(request.getParameter("sort") != null) {
				int sort = Integer.parseInt(request.getParameter("sort"));
				if(sort == 2) {
					Collections.reverse(productList);
				}
			}
			if(uri == null) {
				session.setAttribute("manga", manga);
				session.setAttribute("productList", productList);
				response.sendRedirect("/ShoppingSite/views/product-list.jsp");
			}else {
				session.setAttribute("adminManga", manga);
				session.setAttribute("adminProductList", productList);
				response.sendRedirect("/ShoppingSite/views/product-management.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
