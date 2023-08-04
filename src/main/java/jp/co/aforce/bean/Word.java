package jp.co.aforce.bean;

public class Word implements java.io.Serializable {
	
	private String wordId;
	private String word;
	private int wordType;
	
	public String getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getWordType() {
		return wordType;
	}
	public void setWordType(int wordType) {
		this.wordType = wordType;
	}
}
