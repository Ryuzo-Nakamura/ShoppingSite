package jp.co.aforce.tool;

import javax.servlet.http.HttpSession;

public class MangaInfo {
	
	public static void removeMangaInfo(HttpSession session) {
		session.removeAttribute("title");
		session.removeAttribute("author");
		session.removeAttribute("publisher");
		session.removeAttribute("genre");
	}
}
