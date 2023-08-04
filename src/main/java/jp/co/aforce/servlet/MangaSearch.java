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
			// manga-management.jspからの場合
			if(uri != null) {
				word = request.getParameter("adminSearchTitle");
			// header.jspからの場合
			}else {
				word = request.getParameter("searchWord");
				if(word == null) {
					word = (String)session.getAttribute("searchWord");
				}
			}
			// 単語ごとに検索ワードとして分割する
			String[] wordList = word.split("[ ,　、]");
			
			MangaDAO mangaDAO = new MangaDAO();
			WordDAO wordDAO = new WordDAO();
			List<Manga> mangaList = new ArrayList<>();
			// word(検索ワード)が""の場合
			if(word.equals("")) {
				// すべてのマンガ情報をリスト化
				mangaList = mangaDAO.search04(word);
			// 検索ワードの数が1個の場合
			}else if(wordList.length == 1) {
				List<Manga> list = new ArrayList<>();
				// 検索ワードの個数分繰り返し
				for(String w : wordList) {
					// 検索ワードが出版社の場合
					if(wordDAO.search01(w, 1) >= 1) {
						list.addAll(mangaDAO.search05(w));
					// 検索ワードが作者の場合
					}else if(wordDAO.search01(w, 2) >= 1) {
						list.addAll(mangaDAO.search06(w));
					// 検索ワードがジャンルの場合
					}else if(wordDAO.search01(w, 3) >= 1) {
						list.addAll(mangaDAO.search07(w));
					// それ以外(検索ワードをタイトルとして扱う)
					}else {
						list.addAll(mangaDAO.search04(w));
					}
				}
				List<String> mangaIdList = new ArrayList<>();
				// 検索ワードをもとに取得したマンガ情報リストの件数分繰り返し
				for(int i = 0 ; i < list.size(); i++) {
					boolean isAdd = true;
					for(String mangaId : mangaIdList) {
						// 重複が存在した場合
						if(mangaId.equals(list.get(i).getMangaId())) {
							// listのmangaId重複を削除
							list.remove(i);
							i--;
							isAdd = false;
							break;
						}
					}
					// 重複が存在しなかった場合
					if(isAdd) {
						// リストのi番目の要素をmangaIdListに追加
						mangaIdList.add(list.get(i).getMangaId());
					}
				}
				mangaList = list;
			// 検索ワードの数が複数の場合
			}else {
				List<Manga> searchList = new ArrayList<>();
				List<Manga> list = new ArrayList<>();
				// 検索ワードの個数分繰り返し
				for(String w : wordList) {
					// 検索ワードが出版社の場合
					if(wordDAO.search01(w, 1) >= 1) {
						searchList.addAll(mangaDAO.search05(w));
					// 検索ワードが作者の場合
					}else if(wordDAO.search01(w, 2) >= 1) {
						searchList.addAll(mangaDAO.search06(w));
					// 検索ワードがジャンルの場合
					}else if(wordDAO.search01(w, 3) >= 1) {
						searchList.addAll(mangaDAO.search07(w));
					// それ以外(検索ワードをタイトルとして扱う)
					}else {
						searchList.addAll(mangaDAO.search04(w));
					}
				}
				// 検索ワードをもとに取得したマンガ情報リストの件数分繰り返し
				for(int i = 0; i < searchList.size() ; i++) {
					int count = 0;
					for(int j = 0; j < searchList.size(); j++) {
						// 重複がある場合
						if(searchList.get(i).getMangaId().equals(searchList.get(j).getMangaId())) {
							count += 1;
						}
						// すべての検索ワードに該当するマンガ情報ある場合
						if(count == wordList.length) {
							list.add(searchList.get(i));
						}
					}
				}
				List<String> mangaIdList = new ArrayList<>();
				// すべての検索ワードに該当するマンガ情報リストの件数分繰り返し
				for(int i = 0 ; i < list.size(); i++) {
					boolean isAdd = true;
					for(String mangaId : mangaIdList) {
						// 重複が存在した場合
						if(mangaId.equals(list.get(i).getMangaId())) {
							// listのmangaId重複を削除
							list.remove(i);
							i--;
							isAdd = false;
							break;
						}
					}
					// 重複が存在しなかった場合
					if(isAdd) {
						// リストのi番目の要素をmangaIdListに追加
						mangaIdList.add(list.get(i).getMangaId());
					}
				}
				mangaList = list;
			}
			if(request.getParameter("sort") != null) {
				int sort = Integer.parseInt(request.getParameter("sort"));
				if(sort == 2) {
					// ソートが「商品番号(降順)」のため、出力するマンガ情報リストを反転
					Collections.reverse(mangaList);
				}else if(sort == 3 || sort == 4) {
					List<String> titleList = new ArrayList<>();
					for(Manga manga : mangaList) {
						titleList.add(manga.getTitle());
					}
					// タイトルをキーしてマンガ情報をもつマップを定義
					Map<String, Manga> map = new LinkedHashMap<>();
					for(int i = 0; i < mangaList.size(); i++) {
						map.put(titleList.get(i), mangaList.get(i));
					}
					// ソートが「名前順」のため、タイトルリストを文字列で昇順に並び替える
					Collections.sort(titleList);
					mangaList = new ArrayList<>();
					// 並び替えたタイトルリストと同様の順番でマンガ情報を出力するマンガ情報リストに格納する
					for(String key : titleList) {
						mangaList.add(map.get(key));
					}
					if(sort == 4) {
						// ソートが「名前順(降順)」のため、出力するマンガ情報リストを反転
						Collections.reverse(mangaList);
					}
				}
			}
			// manga-management.jspからの場合
			if(uri != null) {
				// 出力するマンガ情報リストを設定
				session.setAttribute("adminMangaList", mangaList);
				// 入力された検索ワードを設定
				session.setAttribute("adminSearchTitle", word);
				response.sendRedirect(uri);
			// header.jspからの場合
			}else {
				// 出力するマンガ情報リストを設定
				session.setAttribute("mangaList", mangaList);
				// 入力された検索ワードを設定
				session.setAttribute("searchWord", word);
				request.setAttribute("sort", 1);
				response.sendRedirect("/ShoppingSite/views/manga-list.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
