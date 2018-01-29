/**
 * 
 */
package de.uniwuerzburg.digiz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.Date;

import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author UB-Mitarbeiter
 *
 */
public class SMWiki extends Wiki {

	private ArrayList<WikiPageLemma> allLemmaPages;
	private ArrayList<String> allLemmas;
	private ArrayList<WikiPageChapter> allChapterPages;
	private ArrayList<String> allChapter;
	private ArrayList<WikiPageScanPage> allScanPages;
	private ArrayList<String> allScans;
	private ArrayList<WikiPageBibl> allBiblPages;
	private ArrayList<String> allBibls;
	private String gw;
	public int lineCounter;

	public SMWiki() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param domain
	 */
	public SMWiki(String domain) {
		super(domain);
		allBiblPages = new ArrayList<WikiPageBibl>();
		allLemmaPages = new ArrayList<WikiPageLemma>();
		allLemmas = new ArrayList<String>();
		allChapterPages = new ArrayList<WikiPageChapter>();
		allChapter = new ArrayList<String>();
		allScanPages = new ArrayList<WikiPageScanPage>();
		allScans = new ArrayList<String>();
		this.lineCounter = 1;

	}

	/**
	 * @param domain
	 * @param scriptPath
	 */
	public SMWiki(String domain, String scriptPath) {
		super(domain, scriptPath);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param domain
	 * @param scriptPath
	 * @param protocol
	 */
	public SMWiki(String domain, String scriptPath, String protocol) {
		super(domain, scriptPath, protocol);
		// TODO Auto-generated constructor stub
	}

	public String doAskQuery(String query, String caller) throws IOException {
		// String response = post(apiUrl + "action=ask", query, "ask");
		String response = fetch(apiUrl + "action=ask&" + query, "ask");
		return response;
	}

	public void generateBiblList() throws IOException {
		String q = "[[Category:Beleg]]" + "|?Kommentar"
				+ "|?UntergeordneterBeleg" + "|?GehörtZuSeite"
				+ "|?ÜbergeordneterBeleg" + "|limit=50000";
		allBiblPages = new ArrayList<WikiPageBibl>();
		allBibls = new ArrayList<String>();
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageBiblHandler handler = new WikiPageBiblHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			allBiblPages = handler.getBiblPages();
			allBibls = handler.getBibls();

		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}

	}

	public void generateLemmaList() throws IOException {
		String q = "[[Category:Lemma]]" + "|?URLs" + "|?Kommentar"
				+ "|?Lemmatyp" + "|?GehörtZuSeite" + "|?Schreibweisen"
				+ "|limit=50000";
		allLemmaPages = new ArrayList<WikiPageLemma>();
		allLemmas = new ArrayList<String>();
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageLemmaHandler handler = new WikiPageLemmaHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			allLemmaPages = handler.getLemmaPages();
			allLemmas = handler.getLemmas();

		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}

	}

	public void generateLemmaList(String gw) throws IOException {
		String q = "[[Category:Lemma]][[Category:" + gw + "]]" + "|?URLs"
				+ "|?Kommentar" + "|?Lemmatyp" + "|?GehörtZuSeite"
				+ "|?Schreibweisen" + "|limit=50000";
		allLemmaPages = new ArrayList<WikiPageLemma>();
		allLemmas = new ArrayList<String>();
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageLemmaHandler handler = new WikiPageLemmaHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			allLemmaPages = handler.getLemmaPages();
			allLemmas = handler.getLemmas();

		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}

	}

	public WikiPageChapter getChapterPageData(String chapterPageName)
			throws IOException {
		WikiPageChapter wikiPageChapter = new WikiPageChapter(chapterPageName);
		String q = "[["
				+ chapterPageName
				+ "]]|?GehörtZuExemplar|?GehörtZuWerk|?Kapitelreihenfolge|?Kürzel";
		try {
			// System.out.println(doAskQuery("&query="+ URLEncoder.encode(q,
			// "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageChapterHandler handler = new WikiPageChapterHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			wikiPageChapter.setProperties(handler.getCurrentChapter()
					.getProperties());

		} catch (SAXException ex) {
			System.out.println("--ERROR 1 " + ex.toString());
		} catch (Exception ex) {
			System.out.println("--ERROR 2 " + ex.toString());
		}

		return wikiPageChapter;

	}

	public void generateChapterList(WikiPageEdition edition) {
		this.generateChapterList(edition.getGWNumber(), "1000");
	}

	public void generateChapterList(String gw) {
		this.generateChapterList(gw, "1000");
	}

	public void generateChapterList(String gw, String limit) {
		this.gw = gw;

		ArrayList<WikiPageChapter> tmpChapters = new ArrayList<WikiPageChapter>();
		String q = "[[GehörtZuWerk::" + gw + "]]" + "[[Category:Kapitel]]"
				+ "[[Scanpages::+]]" + "|?Scanpages" + "|?Elemente"
				+ "|?Kapitelreihenfolge" + "|?GehörtZuWerk" + "|?Kürzel"
				+ "|sort=Kapitelreihenfolge" + "|limit=" + limit;
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageChapterHandler handler = new WikiPageChapterHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			this.allChapterPages = handler.getChapterPages();
			int counter = 0;
			for (WikiPageChapter currentChapter : this.allChapterPages) {
				// tmpChapters.set(counter, currentChapter);
				tmpChapters.add(this.generateChapterContent(currentChapter));
				// this.allChapter.add(currentChapter.getTitle());
			}
		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}
		// System.out.println("LÄNGE CHAPTER:::" +
		// Integer.toString(tmpChapters.size()));
		this.allChapterPages = tmpChapters;
	}

	public WikiPageChapter generateChapterContent(WikiPageChapter chapter) {
		/*
		 * System.out.println( "++++++++++++++++++ CHAPTER START:" +
		 * chapter.getTitle() + " +++++++++++++++++");
		 * chapter.printProperties();
		 */
		String gw = chapter.getIncunabulaID();
		String shortId = chapter.getShortID();
		ArrayList<String> pageTitlesElemente = new ArrayList<String>();
		ArrayList<WikiPageElement> elementPages = new ArrayList<WikiPageElement>();
		String q = "[[GehörtZuKapitel::" + shortId + "]]" + "[[GehörtZuWerk::"
				+ gw + "]]" + "[[Category:Elemente]]" + "|?Initiale"
				+ "|?Lesetext" + "|?ElementTyp" + "|?FirstElement"
				+ "|?GehörtZuSeite" + "|?SuperSortierung" + "|?Typographie"
				+ "|?OCRText" + "|limit=100" + "|sort=SuperSortierung";
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			WikiPageElement elementPage = new WikiPageElement();
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageElementHandler handler = new WikiPageElementHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			elementPages = handler.getSubElementPages();
		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}

		chapter.setElementPages(elementPages);
		/*
		 * System.out.println( "CHAPTER ENDE:" + chapter.getTitle() +
		 * " +++++++++++++++++++++++++++++++++++"); chapter.printProperties();
		 * System.out.println(
		 * "++++++++++++++++++++++++++++++++++++++++++++++++");
		 */
		return chapter;
	}

	private String replaceLineBreak(String tmpText) {
		// tmpText=tmpText.replaceAll("<br>", "");
		tmpText=tmpText.replaceAll("<br>", "<lb/>");
		String lines[] = tmpText.split("\\r?\\n");
		String newText = "";
		for (String line : lines) {
			newText = newText + "<l>" + line + "</l>\n";
			
		}
		//return tmpText.replaceAll("<br>", "<lb/>");
		 return newText;
	}

	private String replaceLineBreakOCR(String tmpText) {
		return tmpText.replaceAll("<br>", "");
	}

	private WikiPageScanPage addChapterContent(WikiPageScanPage scanPage,
			String currentChapter, ArrayList<WikiPageLemma> lemma) {
		String mainPage = scanPage.getTitle();
		String[] subPages = scanPage.getSubElements();
		ArrayList<String> pageTitles = new ArrayList<String>();
		ArrayList<WikiPageElement> elementResult = new ArrayList<WikiPageElement>();
		System.out
				.println("######################################################################"
						+ mainPage);
		String q = "[[GehörtZuSeite::" + mainPage + "]][[Category:Elemente]]"
				+ "|?GehörtZuKapitel" + "|?GehörtZuSeite" + "|?ElementTyp"
				+ "|?Lemma" + "|?BelegstelleText" + "|?Belegstelle"
				+ "|?Lesetext" + "|?OCRText" + "|?Sortierung" + "|?Typographie"
				+ "|sort=Sortierung";
		try {
			// System.out.println("EXE:" + doAskQuery("&query="+
			// URLEncoder.encode(q, "UTF-8"), "query"));
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageElementHandler handler = new WikiPageElementHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			// elementResult.addScanPage(handler.getCurrentSubElement());
			/*
			 * for (String elementTitle:pageTitles){
			 * System.out.println("START PARSING:::::::::::::"); WikiPageElement
			 * tmpElementPage= new WikiPageElement(elementTitle);
			 * //tmpElementPage=getChapterPageData(elementTitle); //
			 * System.out.println ("DATA: " +
			 * tmpChapterPage.getPropertyValue("Kürzel"));
			 * elements.add(tmpElementPage);
			 * System.out.println("myTitle:::"+title); // chapters.add(new
			 * WikiPageChapter(title)); }
			 */

		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}
		return scanPage;
	}

	public ArrayList<WikiPageScanPage> generateScanList(WikiPageChapter chapter) {
		System.out.println("+++ Control:::"
				+ chapter.getPropertyValue("GehörtZuWerk")[0]
				+ chapter.getPropertyValue("Kürzel")[0]);
		String q = "[[GehörtZuWerk::"
				+ chapter.getPropertyValue("GehörtZuWerk")[0]
				+ "]][[GehörtZuKapitel::"
				+ chapter.getPropertyValue("Kürzel")[0]
				+ "]][[Category:Scanseite]]" + "|?Kolumnen"
				+ "|?GenerierteTranskription" + "|?GehörtZuKapitel"
				+ "|?Elemente" + "|?Elementtypen" + "|?OCRText"
				+ "|?Randleiste" + "|?Schriftklasse" + "|?Typographie"
				+ "|?Kommentar" + "|sort=Scannummer" + "|Limit=4";
		ArrayList<WikiPageScanPage> scan = new ArrayList<WikiPageScanPage>();
		ArrayList<String> pageTitles = new ArrayList<String>();
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			WikiPageScanPageHandler handler = new WikiPageScanPageHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(doAskQuery(
					"&query=" + URLEncoder.encode(q, "UTF-8"), "query"))));
			pageTitles = handler.getScans();
			System.out.println("SCAN-HANDLERRESULT:" + pageTitles.toString());
			/*
			 * for (String title:pageTitles){
			 * System.out.println("START PARSING:::::::::::::"); WikiPageChapter
			 * tmpChapterPage= new WikiPageChapter(title);
			 * tmpChapterPage=getChapterPageData(title); // System.out.println
			 * ("DATA: " + tmpChapterPage.getPropertyValue("Kürzel"));
			 * chapters.add(tmpChapterPage);
			 * System.out.println("myTitle:::"+title); // chapters.add(new
			 * WikiPageChapter(title)); }
			 */

		} catch (SAXException e) {
			System.out.println("SAX Error:::" + e.toString());
		} catch (Exception e) {
			System.out.println("XML Error:::" + e.toString());
		}
		return null;
	}

	public void setLemmaPages(ArrayList<WikiPageLemma> lemmaList) {
		this.allLemmaPages = lemmaList;
	}

	public ArrayList<String> getLemmas() {
		return this.allLemmas;
	}

	public ArrayList<WikiPageLemma> getLemmaPages() {
		return this.allLemmaPages;
	}

	public String replaceLemma(String text) {
		String[] subStrings = StringUtils.substringsBetween(text, "[[Lemma::",
				"]]");
		try {
			for (String s : subStrings) {
				String lemmaID = "";
				String lemmaText = "";
				boolean twoParts = false;
				if (s.contains("|")) {
					// Split it.
					String[] parts = s.split("\\|");
					lemmaID = parts[0];
					lemmaText = parts[1];
					twoParts = true;
				} else {
					lemmaID = s;
					lemmaText = s;
				}
				String xmlId = lemmaID;
				xmlId = Normalizer.normalize(xmlId, Normalizer.Form.NFKD);
				xmlId = xmlId
						.replaceAll(
								"[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+",
								"");
				xmlId = xmlId.replaceAll(",", "");
				xmlId = xmlId.replaceAll("\\.", "");
				xmlId = xmlId.replaceAll("\\s+", "");
				xmlId = xmlId.replaceAll("[()]", "");
				xmlId = xmlId.replaceAll("\"", "");
				xmlId = Character.toLowerCase(xmlId.charAt(0))
						+ (xmlId.length() > 1 ? xmlId.substring(1) : "");

				// System.out.println("REPLACE LEMMA-FOUND:::: " + lemmaID +
				// " --> " +xmlId + " <-- " + lemmaText);
				if (twoParts) {
					text = text.replace("[[Lemma::" + lemmaID + "|" + lemmaText
							+ "]]", " <ref target=\"narragonienLemmata.xml#"
							+ xmlId + "\">" + lemmaText + "</ref>");
				} else {
					text = text.replace("[[Lemma::" + lemmaID + "]]",
							" <ref target=\"narragonienLemmata#" + xmlId + "\">"
									+ lemmaText + "</ref>");
				}
			}
		} catch (NullPointerException ex) {
			// System.out.println("Error in replaceLemma: " + ex.toString());
			return text;
		}

		return text;
	}

	public void generateRegisterTEI() {
		try {
			Namespace xml = Namespace.getNamespace("xml",
					"http://www.w3.org/XML/1998/namespace");

			ProcessingInstruction pi = new ProcessingInstruction(
					"xml-model",
					"href=\"http://www.tei-c.org/release/xml/tei/custom/schema/relaxng/tei_all.rng\""
							+ "type=\"application/xml\""
							+ "schematypens=\"http://purl.oclc.org/dsdl/schematron\"");
			ProcessingInstruction piTEI = new ProcessingInstruction(
					"xml-model",
					"href=\"http://www.tei-c.org/release/xml/tei/custom/schema/relaxng/tei_all.rng\""
							+ " type=\"application/xml\""
							+ " schematypens=\"http://relaxng.org/ns/structure/1.0\"");

			Element tei = new Element("TEI");
			tei.setAttribute("XMLNS", "http://www.tei-c.org/ns/1.0");
			Element teiHeader = new Element("teiHeader");
			Document teiXML = new Document();
			teiXML.addContent(0, pi);
			teiXML.addContent(1, piTEI);
			teiXML.setRootElement(tei);

			Element listPerson = new Element("listPerson");
			Element listPlace = new Element("listPlace");
			Element listBibl = new Element("list");
			listBibl.setAttribute("type", "loc");

			Element fileDesc = new Element("fileDesc");
			Element titleStmt = new Element("titleStmt");
			Element titleStmtTitle = new Element("title");
			titleStmtTitle.setAttribute("type", "main");
			Element titleStmtAuthor = new Element("author");
			titleStmtAuthor.setText("NAME DES VERFASSERS");
			titleStmt.addContent(titleStmtTitle);
			titleStmt.addContent(titleStmtAuthor);

			Element publicationStmt = new Element("publicationStmt");
			Element publicationStmtP = new Element("p");
			publicationStmtP.setText("INFORMATION ÜBER DIE PUBLIKATION");
			publicationStmt.addContent(publicationStmtP);

			Element profileDesc = new Element("profileDesc");
			Element langUsage = new Element("langUsage");
			Element language = new Element("language");
			language.setAttribute("ident", "deu");
			language.setText("SPRACHE DES WERKES");
			langUsage.addContent(language);
			profileDesc.addContent(langUsage);

			Element biblFull = new Element("biblFull");
			Element sDTitleStmt = new Element("titleStmt");
			Element sDTitle = new Element("title");
			Element sDpublicationStmt = new Element("publicationStmt");
			Element publisher = new Element("publisher");
			Element name = new Element("name");
			name.setText("VERLAG/DRUCKEREI");
			publisher.addContent(name);
			sDpublicationStmt.addContent(publisher);
			sDTitle.setText("Haupttitel");
			sDTitleStmt.addContent(sDTitle);
			biblFull.addContent(sDTitleStmt);
			biblFull.addContent(sDpublicationStmt);

			Element sourceDesc = new Element("sourceDesc");

			for (WikiPageLemma lemma : this.allLemmaPages) {
				// System.out.println("NORMAL:" + lemma.getTitle() + " -- "+
				// lemma.getXMLId());
				if (lemma.getLemmaTyp().equals("Person")) {
					Element person = new Element("person");
					Element note = new Element("note");
					person.addNamespaceDeclaration(xml);
					person.setAttribute(new Attribute("id", lemma.getXMLId(),
							xml));

					person.addContent(new Element("persName").setText(lemma
							.getTitle()));
					if (lemma.getPropertyValue("Schreibweisen") != null) {
						for (String altName : lemma
								.getPropertyValue("Schreibweisen")) {
							person.addContent(new Element("persName").setText(
									altName)
									.setAttribute("type", "alternative"));
						}
					}
					// If Source not empty set Attribute
					String[] sourceText = lemma.getRefURLs();
					if (sourceText != null) {
						for (String url : sourceText) {
							url = url.trim();
							note.addContent(new Element("ref").setAttribute(
									"source", url));

						}
					}
					if (!lemma.getComment().isEmpty()) {
						note.addContent(new Element("p").setText(lemma
								.getComment()));
					}
					person.addContent(note);
					listPerson.addContent(person);

				} else if (lemma.getLemmaTyp().equals("Ort")) {
					// log(Level.INFO, "generateLemmaTEI", "Person");
					Element place = new Element("place");
					Element note = new Element("note");
					place.setAttribute(new Attribute("id", lemma.getXMLId(),
							xml));
					// If Source not empty set note and sorce
					String[] sourceText = lemma.getRefURLs();
					if (sourceText != null) {
						for (String url : sourceText) {
							url = url.trim();
							note.addContent(new Element("ref").setAttribute(
									"source", url));

						}
					}
					if (!lemma.getComment().isEmpty()) {
						note.addContent(new Element("p").setText(lemma
								.getComment()));
					}

					place.addContent(new Element("placeName").setText(lemma
							.getTitle()));
					if (lemma.getPropertyValue("Schreibweisen") != null) {
						for (String altName : lemma
								.getPropertyValue("Schreibweisen")) {
							place.addContent(new Element("placeName").setText(
									altName).setAttribute("type", "variante"));
						}
					}
					place.addContent(note);
					listPlace.addContent(place);

				} else {
					// Do nothing
					log(Level.INFO, "generateLemmaTEI", "NO MATCH "
							+ lemma.getPropertyValue("Lemmatyp").toString());
				}
			}
			for (WikiPageBibl bibl : this.allBiblPages) {
				// bibl.printProperties();
				// log(Level.INFO, "generateLemmaTEI", "Ort");
				Element biblTag = new Element("item");
				Element note = new Element("note");
				biblTag.addNamespaceDeclaration(xml);
				biblTag.setAttribute(new Attribute("id", bibl.getXMLId(), xml));
				biblTag.addContent(new Element("title").setText(bibl.getTitle()));
				// If Source not empty set note and sorce
				String[] sourceText = bibl.getRefURLs();
				if (sourceText != null) {
					for (String url : sourceText) {
						url = url.trim();
						note.addContent(new Element("ref").setAttribute(
								"source", url));

					}
				}
				if (!bibl.getComment().isEmpty()) {
					note.addContent(new Element("p").setText(bibl.getComment()));
				}
				if (bibl.getRefs() != "") {
					Element ref = new Element("ref");
					ref.setText(bibl.getRefs());
					ref.setAttribute("type", "beleg");
					biblTag.addContent(ref);
					listBibl.addContent(biblTag);
				}

			}

			// Dummy Bodytext
			Element text = new Element("text");
			Element textBody = new Element("body");
			Element pTextBody = new Element("p");
			pTextBody.setText("FIND THE LEMMA LIST IN THE HEADER.");
			textBody.addContent(pTextBody);
			text.addContent(textBody);

			// Build Tree

			sourceDesc.addContent(listPerson);
			sourceDesc.addContent(listPlace);
			sourceDesc.addContent(listBibl);
			sourceDesc.addContent(biblFull);

			fileDesc.addContent(titleStmt);
			fileDesc.addContent(publicationStmt);
			fileDesc.addContent(sourceDesc);

			teiHeader.addContent(fileDesc);
			teiHeader.addContent(profileDesc);
			teiXML.getRootElement().addContent(teiHeader);
			teiXML.getRootElement().addContent(text);
			XMLOutputter xmlOutput = new XMLOutputter();
			// display nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(teiXML, new FileWriter("narragonienLemmata.xml"));
			log(Level.INFO, "generateLemmaTEI", "File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	public void generateBodyTEI() {
		try {
			Namespace xml = Namespace.getNamespace("xml",
					"http://www.w3.org/XML/1998/namespace");
			// Namespace xml =
			// Namespace.getNamespace("http://www.tei-c.org/ns/1.0");
			// Namespace xmlns = Namespace.getNamespace("xmlns",
			// "http://www.w3.org/2000/xmlns/");
			ProcessingInstruction pi = new ProcessingInstruction(
					"xml-model",
					"href=\"http://www.tei-c.org/release/xml/tei/custom/schema/relaxng/tei_all.rng\""
							+ " type=\"application/xml\""
							+ " schematypens=\"http://purl.oclc.org/dsdl/schematron\"");
			ProcessingInstruction piTEI = new ProcessingInstruction(
					"xml-model",
					"href=\"http://www.tei-c.org/release/xml/tei/custom/schema/relaxng/tei_all.rng\""
							+ " type=\"application/xml\""
							+ " schematypens=\"http://relaxng.org/ns/structure/1.0\"");

			Element tei = new Element("TEI");
			tei.setAttribute("XMLNS", "http://www.tei-c.org/ns/1.0");
			// tei.setNamespace(xmlns);
			Document teiXML = new Document();
			// teiXML.setContent(pi);
			teiXML.addContent(0, pi);
			teiXML.addContent(1, piTEI);

			// teiXML.setContent(piTEI);
			teiXML.setRootElement(tei);
			Element teiHeader = new Element("teiHeader");
			Element fileDesc = new Element("fileDesc");
			// fileDesc.setAttribute("lang", "SPRACHE DES WERKES");
			Element titleStmt = new Element("titleStmt");
			Element author = new Element("author");
			author.setText("NAME DES VERFASSERS");
			Element title = new Element("title");
			title.setAttribute("type", "main");
			titleStmt.addContent(title);
			titleStmt.addContent(author);

			Element publicationStmt = new Element("publicationStmt");
			Element pPublicationStmt = new Element("p");
			pPublicationStmt.setText("INFORMATION ÜBER DIE PUBLIKATION");
			publicationStmt.addContent(pPublicationStmt);

			Element sourceDesc = new Element("sourceDesc");
			Element biblFull = new Element("biblFull");
			Element sDTitleStmt = new Element("titleStmt");
			Element sDTitle = new Element("title");
			Element sDpublicationStmt = new Element("publicationStmt");
			Element publisher = new Element("publisher");
			Element name = new Element("name");
			Element profileDesc = new Element("profileDesc");
			Element langUsage = new Element("langUsage");
			Element language = new Element("language");
			name.setText("VERLAG/DRUCKEREI");
			publisher.addContent(name);
			sDpublicationStmt.addContent(publisher);

			sDTitle.setText("Haupttitel");
			sDTitleStmt.addContent(sDTitle);
			biblFull.addContent(sDTitleStmt);
			biblFull.addContent(sDpublicationStmt);
			sourceDesc.addContent(biblFull);

			language.setAttribute("ident", "deu");
			language.setText("SPRACHE DES WERKES");
			langUsage.addContent(language);
			profileDesc.addContent(langUsage);

			fileDesc.addContent(titleStmt);
			fileDesc.addContent(publicationStmt);
			fileDesc.addContent(sourceDesc);

			teiHeader.addContent(fileDesc);
			teiHeader.addContent(profileDesc);

			Element text = new Element("text");
			// Element front = new Element("front");
			// Element titlePage = new Element("titlePage");
			// Element docTitle= new Element("docTitle");
			// Element titlePart= new Element("titlePart");
			// titlePart.setAttribute("type", "main");
			// titlePart.setText("INFORMATIONEN ZUR TITELSEITE (OPTIONAL");

			// docTitle.addContent(titlePart);
			// titlePage.addContent(docTitle);
			// front.addContent(titlePage);
			// text.addContent(front);

			Element body = new Element("body");

			for (WikiPageChapter chapter : this.allChapterPages) {
				this.lineCounter = 1;
				System.out.println("looping XML - chapters "
						+ chapter.getTitle());
				Element divChapter = new Element("div");
				divChapter.setAttribute("n", chapter.getSortIDString());
				divChapter.setAttribute("id", chapter.getShortID(), xml);
				divChapter.setAttribute("type", "chapter");

				// ArrayList<WikiPageElement>
				// elementPages=chapter.getElementPages();
				// log(Level.INFO, "generateBodyTEI:::ELEMENTE",
				// Integer.toString(chapter.getElementPages().size()));
				log(Level.INFO, "generateBodyTEI:::LOOPING",
						Integer.toString(chapter.getElementPages().size()));
				for (WikiPageElement element : chapter.getElementPages()) {
					if (element.getEnglishValue(element.getElementTyp()) != "woodcut") {
						Element ab = new Element("ab");
						Element choice = new Element("choice");
						Element reg = new Element("reg");
						ab.setAttribute("type", element.getEnglishValue(element
								.getElementTyp()));
						Element lg = new Element("lg");
						lg.setAttribute("type", "normalized");
						Element orig = new Element("orig");
						// OCR-Text holen
						String tempOCR = this.replaceLineBreakOCR(element
								.getOCRText());

						// Deutsche Begriffe ersetzen
						ab.setAttribute("rend", element.getEnglishValue(element
								.getTypography()));
						String tmpText = element.getAnnotatedText();
						tmpText = this.replaceLemma(tmpText);					
						tmpText = this.replaceLineBreak(tmpText);
						String initial = "";
						try {
							initial = element.getInitial();
							if (initial != null && initial != ""
									&& initial.length() == 1) {
								tmpText = tmpText.replaceFirst(initial,
										"<hi rend=\"initial\">" + initial
												+ "</hi>");
							}
						} catch (Exception ex) {
							log(Level.INFO,
									"generateBodyTEI:::Fehler Intitiale",
									initial + " -- " + element.getTitle());
						}

						element.setAnnotatedText(tmpText);
						lg.setText(element.getAnnotatedText());
						orig.setText(tempOCR);
						reg.addContent(lg);
						choice.addContent(reg);
						choice.addContent(orig);
						ab.addContent(choice);
						divChapter.addContent(ab);
					}

					//Sonderfall Holzschnitt
					else {
						Element ab = new Element("ab");
						String tmpText = element.getAnnotatedText();
						tmpText = this.replaceLemma(tmpText);
						//tmpText = this.replaceLineBreak(tmpText);
						tmpText=tmpText.replaceAll("<br>", "<lb/>");
						element.setAnnotatedText(tmpText);
						ab.setText(element.getAnnotatedText());
						divChapter.addContent(ab);

					}
				}
				body.addContent(divChapter);
			}

			text.addContent(body);
			teiXML.getRootElement().addContent(teiHeader);
			teiXML.getRootElement().addContent(text);
			XMLOutputter xmlOutput = new XMLOutputter();
			String xmlString = xmlOutput.outputString(teiXML);
			// AUFRÄUMEN
			xmlString = xmlString.replace("[", "");
			xmlString = xmlString.replace("]", "");
			xmlString = xmlString.replace("&lt;", "<");
			xmlString = xmlString.replace("&gt;", ">");
			xmlString = xmlString.replace("<lb/><lb/>", "<lb/>");

			// display nice
			// xmlOutput.setFormat(Format.getRawFormat());
			PrintWriter writer = new PrintWriter(getSimpleDateFormat() + "-" + this.gw + ".xml", "UTF-8");
			writer.println(xmlString);
			writer.close();

			// xmlOutput.setFormat(Format.getPrettyFormat());
			// SAXBuilder builder = new SAXBuilder();
			// Document doc = builder.build(new StringReader (xmlString));
			// xmlOutput.output(doc, new FileWriter("narragonienGW.xml"));
			// xmlOutput.output(teiXML, new FileWriter("narragonienGW.xml"));
			log(Level.INFO, "generateBodyTEI", "File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage() + io.toString());
			io.printStackTrace();
		} catch (Exception io) {
			System.out.println("Error TEIXML:::" + io.getMessage()
					+ io.toString());
			io.printStackTrace();

		}
	}

	public ArrayList<String> getChapters() {
		return this.allChapter;
	}
	static String getSimpleDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd__HH-mm-ss ");
        Date currentTime = new Date();
        //System.out.println(formatter.format(currentTime));// 2012.04.14 - 21:34:07
        return formatter.format(currentTime);
    } 

}
