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
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.ProductDAO;


@WebServlet("/servlet/manga-delete")
public class MangaDelete extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String mangaId = request.getParameter("adminMangaId");
			
			MangaDAO mangaDAO = new MangaDAO();
			int line = mangaDAO.delete01(mangaId); // マンガタイトルをデータベースから削除
			if(line != 1) {
				session.setAttribute("mangaManagementMessage", Message.E_W0006);
			}else {
				ProductDAO productDAO = new ProductDAO();
				productDAO.delete02(mangaId); // 削除したマンガタイトルの製品をデータベースから削除
				
				// 表示するマンガリストを削除後の状態に更新
				String title = (String) session.getAttribute("adminSearchTitle");
				List<Manga> list = mangaDAO.search04(title);
				session.setAttribute("adminMangaList", list);
				
				session.setAttribute("mangaManagementMessage", Message.I_W0003.replace("{0}", mangaId));
			}
			response.sendRedirect("/ShoppingSite/views/manga-management.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
