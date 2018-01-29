package de.uniwuerzburg.digiz;


import java.util.logging.Level;

/**
 * @author Martin Gruner (UB-Würzburg)
 * @version 1.0
 */
public class Run {


	/**
	 * @param args Command line parameters
	 */
	public static void main(String[] args) throws Exception {
		SMWiki smw= new SMWiki("localhost", "/mediawiki-1.27.1", "http://");
		smw.setUsingCompressedRequests(false);
		smw.log(Level.INFO, "main","START Parsing");

		try
		{
		//narragonien.login(user, pwd);
		//fastily.jwiki.core.Wiki wiki = new fastily.jwiki.core.Wiki(user, pwd, okhttp3.HttpUrl.parse("http://kallimachos.de/narragonien-test"));
		//wiki.setThrottle(5000);
		//cWiki.login(user, pwd); 
		
		//System.out.println("user"+ cWiki.getCurrentUser().toString());
		//System.out.println(wiki.getCurrentUser().toString());
		//System.out.println(cWiki.getRenderedText("GW5046"));
		//String q="[[GehörtZuWerk::GW5046]][[Category:Scanseite]]|?Scannummer|?Elemente|?Limit=1000";
		//System.out.println(cWiki.doAskQuery("&query="+ URLEncoder.encode(q, "UTF-8"), "query"));
		
			//Load lists in Object
		smw.generateLemmaList();
		smw.generateBiblList();
			//write data into file
			//Person + Ort + Belege
		smw.generateRegisterTEI();		
		//smw.log(Level.INFO, "main","Lemmas::" + smw.getLemmas().size() + "--" + smw.getLemmas().toString());		
		
		//CHANGE HERE TO GW-Nummer SET limit=1000 default der WikiAPI =2 20
		//load chapters in Object
		smw.generateChapterList("GW5046", "10");
		//smw.log(Level.INFO, "main","Kapitel::" + smw.getChapters().size() + "--" + smw.getChapters().toString());
		
		//write data into file
		smw.generateBodyTEI();
		}
		catch (Exception ex)
		{
			System.out.println("--ERROR RUN " + ex.toString() + ex.getMessage());
			ex.printStackTrace();
		}	
	}
}
