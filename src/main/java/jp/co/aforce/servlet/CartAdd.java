package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Item;
import jp.co.aforce.bean.Manga;
import jp.co.aforce.bean.Product;
import jp.co.aforce.bean.User;
import jp.co.aforce.dao.ItemDAO;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/servlet/cart-add")

public class CartAdd extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			
			User user = (User)session.getAttribute("user");
			
			ItemDAO itemDAO = new ItemDAO();
			List<Item> cart = itemDAO.search03(user.getUserId());
			
			if(cart.size() == 0) {
				cart = new ArrayList<Item>();
			}
			
			String mangaId = request.getParameter("mangaId");
			List<Product> productList = new ArrayList<>();
			ProductDAO productDAO = new ProductDAO();
			MangaDAO mangaDAO = new MangaDAO();
			Manga manga = new Manga();
			int amount = 1;
			if(mangaId == null) {	// 単品購入
				String productId = request.getParameter("productId");
				Product product = productDAO.search02(productId); 
				productList.add(product);
				amount = Integer.parseInt(request.getParameter("amount"));
				
				manga = mangaDAO.search03(product.getMangaId());
			}else {		// まとめ買い
				productList = productDAO.search03(mangaId);
				manga = mangaDAO.search03(mangaId);
			}
			for(Product p : productList) {
				String itemId = p.getProductId() + user.getUserId();
				Item item = new Item();
				
				int count = itemDAO.search01(itemId);
				if(count == 0) {
					item.setItemId(itemId);
					item.setUserId(user.getUserId());
					item.setProductId(p.getProductId());
					item.setAmount(amount);
					cart.add(item);
					itemDAO.insert01(item);
				}else {
					item = itemDAO.search02(itemId);
					item.setAmount(item.getAmount() + amount);
					itemDAO.update01(item);
				}
			}
			
			session.setAttribute("manga", manga);
			session.setAttribute("addedProductList", productList);
			session.setAttribute("amount", amount);
			request.getRequestDispatcher("/servlet/cart-list").include(request, response);
			response.sendRedirect("/ShoppingSite/views/cart-added.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
