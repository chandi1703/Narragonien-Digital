package de.uniwuerzburg.digiz;

import java.io.IOException;
import java.text.Normalizer;

public class WikiPageBibl extends WikiPage {
	public String XMLId;

	public WikiPageBibl(String title) throws IOException {
		super(title);
		//XML-ID wird normalisiert: Umlaute beseitigen, Kommata, Klammern, Punkte entfernen, erstes Zeichen kleingeschrieben
		//this.XMLId=StringUtils.capitalize(this.XMLId);		
		this.XMLId=Normalizer.normalize(title, Normalizer.Form.NFKD);
		this.XMLId=this.XMLId.replaceAll("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+", "");
		this.XMLId=this.XMLId.replaceAll(":", "");
		this.XMLId=this.XMLId.replaceAll(",", "");
		this.XMLId=this.XMLId.replaceAll("\\.", "");
		this.XMLId=this.XMLId.replaceAll("\\s+","_");
		this.XMLId=this.XMLId.replaceAll("[()]", "_");
		this.XMLId=this.XMLId.replaceAll("\"", "");
		this.XMLId=Character.toLowerCase(this.XMLId.charAt(0)) +(this.XMLId.length() > 1 ? this.XMLId.substring(1) : "");		
				// TODO Auto-generated constructor stub
	}

	public WikiPageBibl() throws IOException {
		// TODO Auto-generated constructor stub
	}

	public String getXMLId() {
		if(this.XMLId!=null)
		{return this.XMLId;}
		else {return "";}
	}

	
	public String[] getRefURLs(){
		try{
		return super.getPropertyValue("LinkURL");
		}
		catch (Exception ex)
		{
			System.out.println("--ERROR get LinkURL " + ex.toString());
			return null;
		}
	}
	
	
	public String getComment() {
		try{
			return String.join(" ", super.getPropertyValue("Kommentar"));
			}
			catch (Exception ex)
			{
				System.out.println("--ERROR Kommentar " + ex.toString());
				return "";
			}
	}

	public String getRefs() {
		try{
			return String.join(" ", super.getPropertyValue("ÜbergeordneterBeleg"))+ " " + String.join(" ", super.getPropertyValue("UntergeordneterBeleg"));
			}
			catch (Exception ex)
			{
				System.out.println("--ERROR Belege " + ex.toString());
				return "";
			}
	}

}
