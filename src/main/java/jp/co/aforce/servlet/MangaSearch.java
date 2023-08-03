package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.Manga;
import jp.co.aforce.dao.MangaDAO;
import jp.co.aforce.dao.WordDAO;


@WebServlet("/servlet/manga-search")
public class MangaSearch extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			session.removeAttribute("sort");
			
			String uri = request.getParameter("uri");
			String word;
			if(uri != null) { // manga-management.jspからの場合
				word = request.getParameter("adminSearchTitle");
			}else {
				word = request.getParameter("searchWord");
				if(word == null) {
					word = (String)session.getAttribute("searchWord");
				}
			}
			String[] wordList = word.split("[ ,　、]");
			
			MangaDAO mangaDAO = new MangaDAO();
			WordDAO wordDAO = new WordDAO();
			List<Manga> mangaList = new ArrayList<>();
			if(word.equals("")) {
				mangaList = mangaDAO.search04(word); // 検索ワードを含むマンガタイトルリスト
			}else if(wordList.length == 1) {
				List<Manga> list = new ArrayList<>();
				for(String w : wordList) {
					if(wordDAO.search01(w, 1) >= 1) {
						list.addAll(mangaDAO.search05(w));
					}else if(wordDAO.search01(w, 2) >= 1) {
						list.addAll(mangaDAO.search06(w));
					}else if(wordDAO.search01(w, 3) >= 1) {
						list.addAll(mangaDAO.search07(w));
					}else {
						list.addAll(mangaDAO.search04(w));
					}
				}
				List<String> mangaIdList = new ArrayList<>();
				for(int i = 0 ; i < list.size(); i++) {
					boolean isAdd = true;
					for(String mangaId : mangaIdList) {
						if(mangaId.equals(list.get(i).getMangaId())) {
							list.remove(i);
							i--;
							isAdd = false;
							break;
						}
					}
					if(isAdd) {
						mangaIdList.add(list.get(i).getMangaId());
					}
				}
				mangaList = list;
			}else {
				List<Manga> searchList = new ArrayList<>();
				List<Manga> list = new ArrayList<>();
				for(String w : wordList) {
					if(wordDAO.search01(w, 1) >= 1) {
						searchList.addAll(mangaDAO.search05(w));
					}else if(wordDAO.search01(w, 2) >= 1) {
						searchList.addAll(mangaDAO.search06(w));
					}else if(wordDAO.search01(w, 3) >= 1) {
						searchList.addAll(mangaDAO.search07(w));
					}else {
						searchList.addAll(mangaDAO.search04(w));
					}
				}
				for(int i = 0; i < searchList.size() ; i++) {
					int count = 0;
					for(int j = 0; j < searchList.size(); j++) {
						if(searchList.get(i).getMangaId().equals(searchList.get(j).getMangaId())) {
							count += 1;
						}
						if(count == wordList.length) {
							list.add(searchList.get(i));
						}
					}
				}
				List<String> mangaIdList = new ArrayList<>();
				for(int i = 0 ; i < list.size(); i++) {
					boolean isAdd = true;
					for(String mangaId : mangaIdList) {
						if(mangaId.equals(list.get(i).getMangaId())) {
							list.remove(i);
							i--;
							isAdd = false;
							break;
						}
					}
					if(isAdd) {
						mangaIdList.add(list.get(i).getMangaId());
					}
				}
				mangaList = list;
			}
			if(request.getParameter("sort") != null) {
				int sort = Integer.parseInt(request.getParameter("sort"));
				if(sort == 2) {
					Collections.reverse(mangaList);
				}else if(sort == 3 || sort == 4) {
					List<String> titleList = new ArrayList<>();
					for(Manga manga : mangaList) {
						titleList.add(manga.getTitle());
					}
					Map<String, Manga> map = new LinkedHashMap<>();
					for(int i = 0; i < mangaList.size(); i++) {
						map.put(titleList.get(i), mangaList.get(i));
					}
					Collections.sort(titleList);
					mangaList = new ArrayList<>();
					for(String key : titleList) {
						mangaList.add(map.get(key));
					}
					if(sort == 4) {
						Collections.reverse(mangaList);
					}
				}
			}
			if(uri != null) { // manga-management.jspからの場合
				session.setAttribute("adminMangaList", mangaList);
				session.setAttribute("adminSearchTitle", word);
				response.sendRedirect(uri);
			}else {
				session.setAttribute("mangaList", mangaList);
				session.setAttribute("searchWord", word);
				request.setAttribute("sort", 1);
				response.sendRedirect("/ShoppingSite/views/manga-list.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
