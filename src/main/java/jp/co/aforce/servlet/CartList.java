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

@WebServlet("/servlet/cart-list")
public class CartList extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			session.setAttribute("cart", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
