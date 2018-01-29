/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;


/**
 * @author UB-Mitarbeiter
 *
 */
public class WikiPageLemma extends WikiPage  {
	public String XMLTag;
	public String XMLId;
	
	public WikiPageLemma() throws IOException{		
		
	}
	public WikiPageLemma(String title) throws IOException {		
		super(title);
		//XML-ID wird normalisiert: Umlaute beseitigen, Kommata, Klammern, Punkte entfernen, erstes Zeichen kleingeschrieben
		//this.XMLId=StringUtils.capitalize(this.XMLId);
		this.XMLId=Normalizer.normalize(title, Normalizer.Form.NFKD);
		this.XMLId=this.XMLId.replaceAll("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+", "");
		this.XMLId=this.XMLId.replaceAll(",", "");
		this.XMLId=this.XMLId.replaceAll("\\.", "");
		this.XMLId=this.XMLId.replaceAll(":", "");
		this.XMLId=this.XMLId.replaceAll("\\s+","_");
		this.XMLId=this.XMLId.replaceAll("[()]", "_");
		this.XMLId=this.XMLId.replaceAll("\"", "");
		this.XMLId=Character.toLowerCase(this.XMLId.charAt(0)) +(this.XMLId.length() > 1 ? this.XMLId.substring(1) : "");		
		// TODO Auto-generated constructor stub
	}
	public String getXMLId() {
		if(this.XMLId!=null)
		{return this.XMLId;}
		else {return "";}
	}
	public String genXMLTag(String origText){
		if (this.getPropertyValue("Lemmatyp")[0]=="Person"){
			return "<persName ref="+this.getPropertyValue("LinkURL")[0] + ">"+origText+"</persName>";
			
		}
		else if (this.getPropertyValue("Lemmatyp")[0]=="Ort"){
			return "<placeName ref="+this.getPropertyValue("LinkURL")[0] + "><choice><orig>"+origText+"</orig><reg>"+this.getTitle()+"</reg></choice></placeName>"; 
		}
		return origText;
	}
	
	public String getLemmaTyp(){
		try{
		return super.getPropertyValue("Lemmatyp")[0];
		}
		catch (Exception ex)
		{
			System.out.println("--ERROR lemmatyp " + this.XMLId +"\n" + ex.toString() );
			return "";
		}
	}
	
	public String[] getRefURLs(){
		try{
		return super.getPropertyValue("URLs");
		}
		catch (Exception ex)
		{
			System.out.println("--ERROR get Ref URLs " + ex.toString());
			return null;
		}
	}
	public String getComment() {
		try{
			String comment=String.join(" ", super.getPropertyValue("Kommentar"));
			if(comment!="{{{Kommentar}}}")
				return comment;
			else 
				return "";
			}
			catch (Exception ex)
			{
				//System.out.println("--ERROR Kommentar " + ex.toString());
				return "";
			}
	}
	

}
