package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.constant.Constant.Message;
import jp.co.aforce.dao.MangaDAO;


@WebServlet("/servlet/manga-update")
public class MangaUpdate extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Manga manga = new Manga();
			manga.setMangaId(request.getParameter("adminMangaId"));
			manga.setTitle(request.getParameter("adminTitle"));
			manga.setAuthor(request.getParameter("adminAuthor"));
			manga.setPublisher(request.getParameter("adminPublisher"));
			manga.setGenre(request.getParameter("adminGenre"));
			
			MangaDAO dao = new MangaDAO();
			
			int line = dao.update01(manga);
			if(line != 1) {
				session.setAttribute("mangaManagementMessage", Message.E_W0005);
			}else {
				session.setAttribute("adminManga", dao.search03(manga.getMangaId()));
				session.setAttribute("mangaManagementMessage", Message.I_W0002.replace("{0}", manga.getMangaId()));
			}
			response.sendRedirect("/ShoppingSite/views/manga-info.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
