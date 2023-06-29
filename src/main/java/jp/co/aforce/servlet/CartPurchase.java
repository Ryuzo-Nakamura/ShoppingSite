package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/servlet/cart-purchase")

public class CartPurchase extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			User user = (User)session.getAttribute("user");
			
			ItemDAO itemDAO = new ItemDAO();
			List<Item> itemList = itemDAO.search03(user.getUserId());
			
			ProductDAO productDAO = new ProductDAO();
			List<Product> productList = new ArrayList<>();
			for(Item item : itemList) {
				productList.add(productDAO.search02(item.getProductId()));
			}
			
			Map<Product, Manga> productMap = new LinkedHashMap<>();
			MangaDAO mangaDAO = new MangaDAO();
			for(Product product : productList) {
				productMap.put(product, mangaDAO.search03(product.getMangaId()));
			}
			Map<Item, Map.Entry<Product, Manga>> cart = new LinkedHashMap<>();
			int count = 0;
			for(Map.Entry<Product, Manga> entry : productMap.entrySet()) {
				cart.put(itemList.get(count), entry);
				count += 1;
			}
			
			// item_infoからカート商品を削除
			itemDAO.delete02(user.getUserId());
			
			// 購入情報
			session.setAttribute("deliveryAddress", request.getParameter("deliveryAddress"));
			session.setAttribute("payment", request.getParameter("payment"));
			session.setAttribute("deliveryDate", request.getParameter("deliveryDate"));
			session.setAttribute("deliveryTime", request.getParameter("deliveryTime"));
			session.setAttribute("purchaseList", cart);
			session.setAttribute("cart", new LinkedHashMap<>());
			response.sendRedirect("/ShoppingSite/views/cart-purchased.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
