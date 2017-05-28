package hu.unideb.inf.beadando.adatkezeles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Lehetővé teszi XML dokumentumok tartalmának értelmezését
 *  és adatok megfelelő formátumban történő kiíratását DOM segítségével.
 * @author Balogh Ádám
 *
 */
public class DOMAdatkezelo implements AdatkezeloInterface {

	/**
	 * Naplózásért felelős logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(DOMAdatkezelo.class);
	
	@Override
	public void kimentFájlba(List<? extends Object> adatok, String kimenetiÁllomány) throws IOException {

		logger.info("Játékállás fájlba mentése.");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element gyökérelem = doc.createElement("tábla");
			doc.appendChild(gyökérelem);
			

			for (int i = 0; i < adatok.size(); i += 4) {

				Element cellaElem = doc.createElement("cella");
				gyökérelem.appendChild(cellaElem);

				Attr ct = doc.createAttribute("CellaTipus");
				ct.setValue((String)adatok.get(i));
				cellaElem.setAttributeNode(ct);

				Element sorElem = doc.createElement("sorszám");
				sorElem.appendChild(doc.createTextNode((String)adatok.get(i + 1)));
				cellaElem.appendChild(sorElem);

				Element oszlopElem = doc.createElement("oszlopszám");
				oszlopElem.appendChild(doc.createTextNode((String)adatok.get(i + 2)));
				cellaElem.appendChild(oszlopElem);

				Element tartalomElem = doc.createElement("tartalom");
				tartalomElem.appendChild(doc.createTextNode((String)adatok.get(i + 3)));
				cellaElem.appendChild(tartalomElem);

			}

			TransformerFactory trf = TransformerFactory.newInstance();
			Transformer tr = trf.newTransformer();

			DOMSource forrás = new DOMSource(doc);
			StreamResult eredmény = new StreamResult(new File(kimenetiÁllomány));
			tr.transform(forrás, eredmény);
		} catch (ParserConfigurationException | TransformerException e) {
			logger.error("Hiba történt a " + kimenetiÁllomány + " létrehozása közben.");
		}

	}

	
	@Override
	public List<String> betöltFájlból(String forrásÁllomány) throws IOException {

		List<String> lista = new ArrayList<>();
		
		logger.info("Játékállás betöltése fájlból.");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(forrásÁllomány);
			System.out.println("BETÖLTÖTT: " + getClass().getClassLoader().getResource("konnyu.xml"));
			NodeList list = doc.getElementsByTagName("cella");

			String tipus;
			String sor;
			String oszlop;
			String tartalom;

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) node;

					tipus = elem.getAttributeNode("CellaTipus").getValue();
					sor = elem.getElementsByTagName("sorszám").item(0).getTextContent();
					oszlop = elem.getElementsByTagName("oszlopszám").item(0).getTextContent();
					tartalom = elem.getElementsByTagName("tartalom").item(0).getTextContent();

					lista.add(tipus);
					lista.add(sor);
					lista.add(oszlop);
					lista.add(tartalom);

				}
			}

		} catch (ParserConfigurationException | SAXException e) {
			logger.error("Hiba történt a  " + forrásÁllomány + "  fájl feldolgozása közben.");
		} 
		
		return lista;
	}
	

}
