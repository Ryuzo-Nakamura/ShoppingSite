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


@WebServlet("/servlet/manga-add")
public class MangaAdd extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
		
			Manga manga = new Manga();
			manga.setTitle(request.getParameter("title"));
			manga.setAuthor(request.getParameter("author"));
			manga.setPublisher(request.getParameter("publisher"));
			manga.setGenre(request.getParameter("genre"));
			manga.setTotalNumber(0);
			
			MangaDAO dao = new MangaDAO();
			
			int count = dao.search01(manga.getTitle());
			if(count != 0) {
				session.setAttribute("titleError", Message.W_C0004.replace("{0}", manga.getTitle()));
				response.sendRedirect("/ShoppingSite/views/manga-add.jsp");
			}else {
				String mangaId = String.valueOf(dao.lastID01() + 1);
				while(mangaId.length() < 8) {
					mangaId = "0" + mangaId;
				}
				manga.setMangaId(mangaId);
				
				int line = dao.insert01(manga);
				if(line != 1) {
					session.setAttribute("mangaAddMessage", Message.E_W0004);
					response.sendRedirect("/ShoppingSite/views/manga-add.jsp");
				} else {
					session.setAttribute("mangaAddMessage", Message.I_W0001);
					response.sendRedirect("/ShoppingSite/views/manga-add.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
