/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author UB-Mitarbeiter
 *
 */
public class WikiPageScanPage extends WikiPage {
	private ArrayList<WikiPageElement> elementPages;

	/**
	 * @param title
	 * @throws IOException
	 */
	public WikiPageScanPage(String title) throws IOException {
		super(title);
		elementPages= new ArrayList<WikiPageElement>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 */
	public WikiPageScanPage() throws IOException {
		elementPages= new ArrayList<WikiPageElement>();
		// TODO Auto-generated constructor stub
	}
	public void addScanPage(WikiPageElement elementPage){
		this.elementPages.add(elementPage);
	}
	public void addScanPages(ArrayList<WikiPageElement> pages){
		this.elementPages=pages;
	}
		
		
	public int getSortID(){
		return Integer.parseInt(super.getPropertyValue("Scannummer")[0]);		
		
	}
	public String getEdition(){
		return super.getPropertyValue("GehörtZuExemplar")[0];
		
	}
	public HashMap<String, String[]> getProperties(){
		return this.properties;
		
	}
		
	public String getIncunabulaID(){
		return super.getPropertyValue("GehörtZuWerk")[0];		
	}
	public String[] getSubElements(){
		return super.getPropertyValue("Elemente");
	}
	public ArrayList<WikiPageElement> getSubElementPages(){
		return this.elementPages;
	}

}
