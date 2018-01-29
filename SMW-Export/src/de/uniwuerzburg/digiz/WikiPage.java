/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Martin Gruner
 *
 */
public class WikiPage {
	private String title;
	private Boolean hasSubpages;
	private String lastEdit;
	private Wiki wiki;
	private String pageContent;
	private Map pageInfo;
	protected HashMap<String, String[]> properties;
	

public WikiPage(String title) throws IOException
{
    this.title=title;
    //this.pageContent=wiki.getPageText(this.title);
    //this.pageInfo=wiki.getPageInfo(this.title);
}
public WikiPage() throws IOException{
	
}

public void requestWikiContent() throws IOException{

	pageContent=wiki.getPageText(this.title);
	pageInfo=wiki.getPageInfo(this.title);
	
}
public void setProperties (HashMap<String, String[]> prop){
	this.properties=prop;	
}

public void setProperty(String key, String value){
	String[] values = {value};
	this.properties.put(key, values);
}

public void setProperty(String key, String[] values){
	this.properties.put(key, values);
}

public void printProperties(){
	System.out.println("All Properties:");
	//System.out.println(properties.toString());
	for (String key: properties.keySet()) {
		System.out.print ("\nProperty: "+key);
		for (String value: properties.get(key)) {
			System.out.print(" - " + value+" ");			
		}
	 	
	}
	System.out.print ("\n");
}
public String getTitle(){
	return this.title;
}
public String[] getPropertyValue(String propertyName) {
	if(this.properties.get(propertyName)!=null){
	return this.properties.get(propertyName);}
	else{
		String[] empty={"", ""};
		return empty;
		}

	}
}