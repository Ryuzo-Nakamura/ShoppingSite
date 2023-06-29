package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.dao.MangaDAO;


@WebServlet("/servlet/manga-management")
public class MangaManagement extends HttpServlet {	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			String uri = request.getParameter("uri");
			String mangaId = request.getParameter("mangaId");
			
			MangaDAO dao = new MangaDAO();
			Manga manga = dao.search03(mangaId);
			
			session.setAttribute("adminManga", manga);
			if(uri == null) {
				response.sendRedirect("/ShoppingSite/views/manga-info.jsp");
			}else {
				response.sendRedirect("/ShoppingSite/views/product-regist.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
