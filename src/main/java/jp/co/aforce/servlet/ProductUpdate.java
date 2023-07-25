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
import javax.servlet.http.Part;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.bean.Product;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/product-update")
@MultipartConfig
public class ProductUpdate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String productId = request.getParameter("productId");
			
			ProductDAO productDAO = new ProductDAO();
			
			String mangaId = productDAO.search02(productId).getMangaId();
			String imgURL = productDAO.search02(productId).getImgURL();
			
			Product product = new Product();
			product.setProductId(productId);
			product.setNumber(Integer.parseInt(request.getParameter("number")));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setDescription(request.getParameter("description"));
			
			if(product.getNumber() != productDAO.search02(productId).getNumber()
				&&
				productDAO.search05(mangaId, product.getNumber()) != 0) {	// 巻数が変わっているかつ商品があるかどうかチェック
					session.setAttribute("number", product.getNumber());
					session.setAttribute("price", product.getPrice());
					session.setAttribute("description", product.getDescription());
					session.setAttribute("productManagementMessage", Message.W_C0008.replace("{0}", ((Integer)product.getNumber()).toString()));
				
			}else {
				Part part = request.getPart("img");
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				String path=getServletContext().getRealPath("/img/item");
				int line;
				if(filename.length() == 0) {
					line = productDAO.update01(product);
				}else {
					product.setImgURL(filename);
					line = productDAO.update03(product);
				}
				if(line != 1) {
					session.setAttribute("productManagementMessage", Message.E_W0008);
				}else {
					if(filename.length() != 0) {
						// 更新前の商品の画像ファイルをフォルダから削除
						Files.delete(Paths.get(path+File.separator+imgURL));

						part.write(path+File.separator+filename);
					}
					product = productDAO.search02(productId);
					String number = String.valueOf(product.getNumber());
					while(number.length() < 3) {
						number = "0" + number;
					}
					String newProductId = mangaId + number;
					productDAO.update02(product, newProductId);

					MangaDAO mangaDAO = new MangaDAO();
					// 最新の単行本の画像をマンガタイトルの画像に変更
					mangaDAO.update04(mangaId, productDAO.search04(mangaId));
					session.setAttribute("adminManga", mangaDAO.search03(mangaId));
					session.setAttribute("adminProduct", productDAO.search02(newProductId));
					session.setAttribute("productManagementMessage", Message.I_W0005.replace("{0}",newProductId));

					// 表示するマンガタイトルリスト、商品リストを削除後の状態に更新
					String title = (String) session.getAttribute("adminSearchTitle");
					List<Manga> mangaList = mangaDAO.search04(title); 
					List<Product> productList = productDAO.search03(product.getMangaId());
					session.setAttribute("adminMangaList", mangaList);
					session.setAttribute("adminProductList", productList);
				}
			}
			response.sendRedirect("/ShoppingSite/views/product-info.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
