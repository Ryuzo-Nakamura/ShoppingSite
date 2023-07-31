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

			List<Product> productList = new ArrayList<>();
			List<Integer> amountList = new ArrayList<>();
			ProductDAO productDAO = new ProductDAO();
			MangaDAO mangaDAO = new MangaDAO();
			Manga manga = new Manga();

			String[] ids = request.getParameterValues("productId");
			String[] amounts = request.getParameterValues("amount");

			int addType = Integer.parseInt(request.getParameter("add-type"));
			if(addType == 0) { // 単品
				String addId = request.getParameter("add-productId");
				int number = 0;
				for(int i = 0; i < ids.length; i++) {
					if(addId.equals(ids[i])) {
						number = i;
					}
				}
				int amount = Integer.parseInt(amounts[number]);
				amountList.add(amount);

				Product product = productDAO.search02(addId);
				productList.add(product);

				manga = mangaDAO.search03(product.getMangaId());
			}else if(addType == 1) { // 複数

				String[] checks = request.getParameterValues("check");
				int number = 0;
				for(int i = 0; i < ids.length; i++) {
					if(ids[i].equals(checks[number])) {
						Product product = productDAO.search02(checks[number]);
						productList.add(product);
						amountList.add(Integer.parseInt(amounts[i]));
						number++;
					}
					if(number >= checks.length) {
						break;
					}
				}
				manga = mangaDAO.search03(productList.get(0).getMangaId());
			}else { // まとめ買い
				String mangaId = request.getParameter("mangaId");
				productList = productDAO.search03(mangaId);
				for(int i = 0; i < productList.size(); i++) {
					amountList.add(1);
				}
				manga = mangaDAO.search03(mangaId);
			}
			for(int i = 0; i < productList.size(); i++) {
				Product p = productList.get(i);
				String itemId = p.getProductId() + user.getUserId();
				Item item = new Item();

				int count = itemDAO.search01(itemId);
				if(count == 0) {
					item.setItemId(itemId);
					item.setUserId(user.getUserId());
					item.setProductId(p.getProductId());
					item.setAmount(amountList.get(i));
					cart.add(item);
					itemDAO.insert01(item);
				}else {
					item = itemDAO.search02(itemId);
					item.setAmount(item.getAmount() + amountList.get(i));
					itemDAO.update01(item);
				}
			}

			session.setAttribute("manga", manga);
			session.setAttribute("addedProductList", productList);
			session.setAttribute("amountList", amountList);
			request.getRequestDispatcher("/servlet/cart-list").include(request, response);
			response.sendRedirect("/ShoppingSite/views/cart-added.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
