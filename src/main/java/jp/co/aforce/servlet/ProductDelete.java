package jp.co.aforce.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig
public class ProductDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String productId = request.getParameter("productId");
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.search02(productId);
			
			String mangaId = product.getMangaId();
			String imgURL = product.getImgURL();
			
			int line = productDAO.delete01(productId); // 商品をデータベースから削除
			if(line != 1) {
				session.setAttribute("productManagementMessage", Message.E_W0009);
			}else {
				MangaDAO mangaDAO = new MangaDAO();
				Manga manga = mangaDAO.search03(product.getMangaId());
				manga.reduceTotalNumber(); // 総巻数を減らす
				mangaDAO.update02(manga);
				
				if(manga.getTotalNumber() == 0) {
					mangaDAO.update04(mangaId, null);
				}else {
					// 最新の単行本の画像をマンガタイトルの画像に変更
					mangaDAO.update04(mangaId, productDAO.search04(mangaId));
				}
				session.setAttribute("adminManga", mangaDAO.search03(mangaId));
				
				// 削除した商品の画像ファイルをフォルダから削除
				String path=getServletContext().getRealPath("/img/item");
				Files.delete(Paths.get(path+File.separator+imgURL));
				
				// 表示するマンガタイトルリスト、商品リストを削除後の状態に更新
				String title = (String) session.getAttribute("adminSearchTitle");
				List<Manga> mangaList = mangaDAO.search04(title); 
				List<Product> productList = productDAO.search03(product.getMangaId());
				session.setAttribute("adminMangaList", mangaList);
				session.setAttribute("adminProductList", productList);
			}
			response.sendRedirect("/ShoppingSite/views/product-management.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
