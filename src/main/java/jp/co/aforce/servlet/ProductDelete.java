package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.bean.Product;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-delete")
public class ProductDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String productId = request.getParameter("productId");
			
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.search02(productId);
			
			int line = productDAO.delete01(productId); // 商品をデータベースから削除
			if(line != 1) {
				session.setAttribute("mangaManagementMessage", Message.E_W0006);
			}else {
				MangaDAO mangaDAO = new MangaDAO();
				Manga manga = mangaDAO.search03(product.getMangaId());
				manga.reduceTotalNumber(); // 総巻数を減らす
				mangaDAO.update02(manga);
				
				// 表示するマンガタイトルリスト、商品リストを削除後の状態に更新
				String title = (String) session.getAttribute("adminSearchTitle");
				List<Manga> mangaList = mangaDAO.search04(title); 
				List<Product> productList = productDAO.search03(product.getMangaId());
				session.setAttribute("adminMangaList", mangaList);
				session.setAttribute("adminProductList", productList);
			}
			response.sendRedirect("/ShoppingSite/views/manga-management.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
