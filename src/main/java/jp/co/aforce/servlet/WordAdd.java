package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Word;
import jp.co.aforce.dao.WordDAO;

@WebServlet("/servlet/word-add")
public class WordAdd extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String publisher = (String) request.getAttribute("publisher");	
			String[] authors = (String[]) request.getAttribute("author");
			String[] genres = (String[]) request.getAttribute("genre");
			
			WordDAO dao= new WordDAO();
			Word word = new Word();
			int countP = dao.search01(publisher, 1);
			if(countP <= 0) {
				String number = String.valueOf(dao.search02(1)+1);
				while(number.length() < 5) {
					number = "0" + number;
				}
				String wordId = 1 + number;
				word.setWordId(wordId);
				word.setWord(publisher);
				word.setWordType(1);
				dao.insert01(word);
			}
			for(String author : authors) {
				int countA = dao.search01(author, 2);
				if(countA <= 0) {
					String number = String.valueOf(dao.search02(2)+1);
					while(number.length() < 5) {
						number = "0" + number;
					}
					String wordId = 2 + number;
					word.setWordId(wordId);
					word.setWord(author);
					word.setWordType(2);
					dao.insert01(word);
				}
			}
			for(String genre : genres) {
				int countG = dao.search01(genre, 3);
				if(countG <= 0) {
					String number = String.valueOf(dao.search02(3)+1);
					while(number.length() < 5) {
						number = "0" + number;
					}
					String wordId = 3 + number;
					word.setWordId(wordId);
					word.setWord(genre);
					word.setWordType(3);
					dao.insert01(word);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
