/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * @author UB-Mitarbeiter
 *
 */
public class WikiPageChapter extends WikiPage {

	private ArrayList<WikiPageScanPage> scanPages;
	private ArrayList<WikiPageElement> elementPages;
	/**
	 * @param title
	 * @param wiki
	 * @throws IOException
	 */
	public WikiPageChapter() throws IOException{
		scanPages= new ArrayList<WikiPageScanPage>();
		elementPages= new ArrayList<WikiPageElement>();
	}
	/**
	 * @param title
	 * @throws IOException
	 */
	public WikiPageChapter(String title) throws IOException {		
		super(title);
		scanPages= new ArrayList<WikiPageScanPage>();
		elementPages= new ArrayList<WikiPageElement>();
	}
	public void addScanPage(WikiPageScanPage scanPage){
		this.scanPages.add(scanPage);
	}
	public void addScanPages(ArrayList<WikiPageScanPage> pages){
		this.scanPages=pages;
	}
	
	public void addElementPage(WikiPageElement elementPage){
		this.elementPages.add(elementPage);
	}
	public void addElementPages(ArrayList<WikiPageElement> pages){
		this.elementPages=pages;
	}
	
	public String[] getScanPagesProperty(){
		return super.getPropertyValue("Scanpages");		
		
	}
	public ArrayList<WikiPageScanPage> getScanPages(){
		return this.scanPages;	
		
	}
	public ArrayList<WikiPageElement> getElementPages(){
		return this.elementPages;	
		
	}
	
	public int getSortID(){
		return Integer.parseInt(super.getPropertyValue("Kapitelreihenfolge")[0]);		
		
	}
	public String getSortIDString(){
		return super.getPropertyValue("Kapitelreihenfolge")[0];		
		
	}
	
	public String getShortID(){
		return super.getPropertyValue("Kürzel")[0];
		
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

	public String[] getElementPagesProperty() {
		return super.getPropertyValue("Elemente");
	}
	public void setElementPages(ArrayList<WikiPageElement> elementPagesNew) {
		this.elementPages=elementPagesNew;
		
	}		
}
