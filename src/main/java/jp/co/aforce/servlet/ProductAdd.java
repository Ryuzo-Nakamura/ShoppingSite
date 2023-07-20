package jp.co.aforce.servlet;

import java.io.File;
import java.io.IOException;
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
import jp.co.aforce.tool.ProductInfo;


@WebServlet("/servlet/product-add")
@MultipartConfig
public class ProductAdd extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Product product = new Product();
			product.setNumber(Integer.parseInt(request.getParameter("number")));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setDescription(request.getParameter("description"));
			
			String mangaId = request.getParameter("mangaId");
			if(mangaId.length() == 0) {
				ProductInfo.setProductInfo(session, product);
			}else {
				product.setMangaId(mangaId);
				MangaDAO mangaDAO = new MangaDAO();
				Manga manga = mangaDAO.search03(mangaId);
				
				String productId = String.valueOf(product.getNumber());
				while(productId.length() < 3) {
					productId = "0" + productId;
				}
				productId = product.getMangaId() + productId;
				
				ProductDAO productDAO = new ProductDAO();
				int count = productDAO.search01(productId);
				if(count != 0) {
					session.setAttribute("productAddMessage", Message.W_C0005);
					session.setAttribute("adminManga", manga);
					ProductInfo.setProductInfo(session, product);
				}else {
					product.setProductId(productId);
					Part part = request.getPart("img");
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					String path=getServletContext().getRealPath("/img/item");
					product.setImgURL(filename);
					
					int line = productDAO.insert01(product);
					if(line != 1) {
						session.setAttribute("productAddMessage", Message.E_W0007);
						session.setAttribute("adminManga", manga);
						ProductInfo.setProductInfo(session, product);
					}else {
						part.write(path+File.separator+filename);
						manga.raiseTotalNumber(); // 総巻数を増やす
						mangaDAO.update02(manga);
						mangaDAO.update04(mangaId, productDAO.search04(mangaId));
						manga.setImgURL(mangaDAO.search03(mangaId).getImgURL());
						session.setAttribute("adminManga", manga);
						String title = (String) session.getAttribute("adminSearchTitle");
						List<Manga> mangaList = mangaDAO.search04(title); 
						session.setAttribute("adminMangaList", mangaList);
						session.setAttribute("productAddMessage", Message.I_W0004);
					}
				}
			}
			response.sendRedirect("/ShoppingSite/views/product-regist.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
