package de.uniwuerzburg.digiz;

import java.io.IOException;

public class WikiPageEdition extends WikiPage {
	private WikiPageChapter[] chapters;
	private String GWNumber;
	
	public WikiPageEdition(String pageName) throws IOException{
		super(pageName);
		
	}
	public void setGWNumber(String gw){
		this.GWNumber=gw;
	}
	public String getGWNumber(){
		return this.GWNumber;
	}
	public void setChapters(WikiPageChapter[] chapters){
		this.chapters=chapters;
	}
	public WikiPageChapter[] getChapters(){
		return this.chapters;
	}
	
}

