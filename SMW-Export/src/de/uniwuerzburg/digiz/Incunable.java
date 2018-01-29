package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.util.ArrayList;

public class Incunable {
	
	private ArrayList<WikiPageLemma> allLemmaPages;
	private ArrayList<String> allLemmas;
	private ArrayList<WikiPageChapter> allChapterPages;
	private ArrayList<String> allChapters;	
	private ArrayList<WikiPageScanPage> allScanPages;
	private ArrayList<String> allScans;
	private ArrayList<WikiPageElement> allSubElementPages;
	private ArrayList<String> allSubElement;
	private String title;
	
	public Incunable(String title) throws IOException
	{
	    this.title=title;

	}
	public Incunable() throws IOException{
		
	}
	

}
