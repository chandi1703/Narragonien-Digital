/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

/**
 * @author UB-Mitarbeiter
 *
 */
public class WikiPageElement extends WikiPage {
	private ArrayList<WikiPageLemma> lemmas;
	private HashMap<String, String> dictionary;

	/**
	 * @param title
	 * @throws IOException
	 */
	public WikiPageElement(String title) throws IOException {
		super(title);
		//MAP für ersetzungen
				this.dictionary=new HashMap<String, String>();
				this.dictionary.put("Folio", "folio");
				this.dictionary.put("Bogensignatur", "signatureMark");
				this.dictionary.put("Kapitelüberschrift", "chapterTitle");
				this.dictionary.put("Motto", "motto");
				this.dictionary.put("Haupttext", "mainText");
				this.dictionary.put("Holzschnitt", "woodcut");
				this.dictionary.put("Holzschnittbeischrift", "woodcutInscription");
				this.dictionary.put("HolzschnittBeischrift", "woodcutInscription");
				this.dictionary.put("Titelholzschnitt", "woodcutTitle");
				this.dictionary.put("Werktitel", "workTitel");
				this.dictionary.put("Subscriptio", "subscriptio");
				this.dictionary.put("Kolophon", "colophon");
				this.dictionary.put("Register", "register");
				this.dictionary.put("Marginalie", "marginalNote");
				this.dictionary.put("Envoy", "envoy");
				this.dictionary.put("Gebrochene Schrift", "gothic");
				this.dictionary.put("Antiqua", "antiqua");
				this.dictionary.put("groß", "big");
				this.dictionary.put("normal", "normal");
				this.dictionary.put("klein", "small");
				this.dictionary.put("mehrzeilig", "multiLine");
				this.dictionary.put("einzeilig", "singleLine");
				this.dictionary.put("Verse", "verses");
				this.dictionary.put("Prosa", "prose");
				this.dictionary.put("Blocksatz", "justification");
				this.dictionary.put("Flattersatz", "raggedType");
				this.dictionary.put("Distichen", "distiches");
				this.dictionary.put("Einzug", "indention");
				this.dictionary.put("zentriert", "centred");
				this.dictionary.put("rechtsbündig", "rightJustified");
				this.dictionary.put("zweispaltig", "twoColumn");
				this.dictionary.put("linksbündig", "leftJustified");
				this.dictionary.put("Flattersatz", "raggedMargin");
				this.dictionary.put("Umbruch", "wrap");
				
				
				
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException
	 */
	public WikiPageElement() throws IOException {
		// TODO Auto-generated constructor stub
	}
	public String getParentScanPage(){
		return super.getPropertyValue("GehörtZuSeite")[0];		
		
	}
		
	public int getSortID(){
		return Integer.parseInt(super.getPropertyValue("Sortierung")[0]);		
		
	}
	public String getInitial(){
		try {
			return super.getPropertyValue("Initiale")[0];
		}
		catch (Exception e) {
			return "";
		}
		
	}
	public String getEdition(){
		return super.getPropertyValue("GehörtZuExemplar")[0];
		
	}
	public HashMap<String, String[]> getProperties(){
		return this.properties;
		
	}
		
	public String getIncunabulaID(){
		try {
			return super.getPropertyValue("GehörtZuWerk")[0];
		}
		catch (Exception e) {
			return "";
		}
		
				
	}
	
	public String getAnnotatedText(){
		try {
			return super.getPropertyValue("Lesetext")[0];
		}
		catch (Exception e) {
			return "";
		}
				
	}
	public String getOCRText(){
		try {
			return super.getPropertyValue("OCRText")[0];
		}
		catch (Exception e) {
			return "";
		}
				
	}
	public String[] getTypography(){
		try {
			return super.getPropertyValue("Typographie");
		}
		catch (Exception e) {
			return null;
		}
				
	}
	public void setAnnotatedText(String newText){
		super.setProperty("Lesetext", newText);
	}

	public String getElementTyp() {
		try {
			return super.getPropertyValue("ElementTyp")[0];
		}
		catch (Exception e) {
			return "none";
		}
	}
	
	public String getEnglishValue(String de){
		if(de!=null){
		try {
			if(this.dictionary.get(de)!=null) 
			{return this.dictionary.get(de);}
			//else return "NO Translation of " + de ;			
			else {
				System.out.println ("No Translation of " + de);
				return "none" ;
			}
		}
		catch (Exception e) {
			//return "NO Translation of " + de ;
			System.out.println ("No Translation of " + de);
			return "none" ;
		}
		}
		else return "";
		
	}
	public String getEnglishValue(String[] de){
		String returnValue="";
		for (String deValue: de){
			returnValue+=this.getEnglishValue(deValue);
			returnValue+=" ";
		}
		if (returnValue.trim()==""){
			return "none";
		}
		else return returnValue.trim();
	}	
}
