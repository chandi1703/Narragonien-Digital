package de.uniwuerzburg.digiz;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * @author UB-Mitarbeiter
 *
 */
public class WikiPageBiblHandler implements ContentHandler {

	  private ArrayList<String> allBibls = new ArrayList<String>();	 
	  private ArrayList<WikiPageBibl> allBiblPages = new ArrayList<WikiPageBibl>();
	  private String currentValue;
	  private String wikiPageTitle;	 
	  private WikiPageBibl bibl;
	  private HashMap<String, String[]> properties;
	  private String propertyName;
	  private String propertyValue;
	  private ArrayList<String> propertyValues;
	  private StringBuilder currentText = new StringBuilder();
	  
	@Override
	public void startDocument(){
		System.out.println(":::Bibl starts.");
	}
	@Override
	public void endDocument(){
		System.out.println(":::Bibl ends.");
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		 if (localName.equals("subject")) {
			//System.out.println("SUBJECT::: "+ atts.getValue("fulltext") + currentValue);		     
			 try{
				 wikiPageTitle = new String(atts.getValue("fulltext"));
				 bibl =  new WikiPageBibl(atts.getValue("fulltext"));
			 }
			 catch (Exception ex) {
				 System.out.println(ex.toString());
			 }
		    }
		 else if (localName.equals("printouts")) {
			 properties=new HashMap<String, String[]>();
			 propertyValues = new ArrayList<String>();
			// System.out.println ("PRINTOUTS:::" + currentValue +" -- " + properties.toString());
		    }
		 else if (localName.equals("property")) {			 
			 propertyName=atts.getValue("label");			 
			 //System.out.println ("PROPERTY::: " + propertyName +" -- "  + propertyValue +" -- " + currentValue +" -- " + properties.toString());
			 
		    }
		 else if (localName.equals("value")) {
			 if(atts.getValue("fulltext")!=null) { 
			 propertyValue=atts.getValue("fulltext");
			 }
		    }
		 else {
			 //System.out.println ("++ELEMENT(open):::" + localName +" -- " + propertyName +" -- "  + propertyValue);
			 
		 }
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("subject")) {
		      allBibls.add(wikiPageTitle);
		      bibl.setProperties(properties);		     
		      allBiblPages.add(bibl);
		      //chapter.printProperties();    	      
		    }
		else if (localName.equals("property")) {
		properties.put(propertyName, propertyValues.toArray(new String[propertyValues.size()]));			
		 //System.out.println("Property-Ende:::" + wikiPageTitle + " -- " + propertyName + " -- " +  propertyValue);
		    propertyName=null;
		    propertyValue=null;
		    currentValue="";
		    propertyValues.clear();
		    }
		else if (localName.equals("value")) {
			if (propertyValue==null){
				propertyValue=currentValue;				
			}
			propertyValues.add(propertyValue);
			propertyValue=null;
			currentValue="";
			//System.out.println ("value end gefunden::: " + currentValue + " -- " + wikiPageTitle +" -- " + propertyName +" -- " +  propertyValue + "###"+ propertyValues.toString());						
		    }
		else {
			 //System.out.println ("++ELEMENT(close):::" + localName);
			 
		 }
		
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue += new String(ch, start, length);
		currentText.append(new String(ch,start,length));
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	public WikiPageBibl getCurrentBibl() {
		// TODO Auto-generated method stub
		return this.bibl;
	}
	
	public ArrayList<String> getBibls() {
		// TODO Auto-generated method stub
		return this.allBibls;
	}
	public ArrayList<WikiPageBibl> getBiblPages() {
		// TODO Auto-generated method stub
		return this.allBiblPages;
	}
}


