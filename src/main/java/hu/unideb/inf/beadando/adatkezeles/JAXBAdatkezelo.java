package hu.unideb.inf.beadando.adatkezeles;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.unideb.inf.beadando.modell.Eredmenyek;

/**
 * Az osztály XML formátumú adatok kezelését valósítja meg JAXB technológiával.
 * @author Balogh Ádám
 *
 */
public class JAXBAdatkezelo implements AdatkezeloInterface{
	
	/**
	 * Naplózásért felelős logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(JAXBAdatkezelo.class); 

	@SuppressWarnings("unchecked")
	@Override
	public void kimentFájlba(List<? extends Object> adatok, String kimenetiÁllomány) throws IOException {
		
		logger.info("Eredmények fájlba mentése.");
		
		try {

			Eredmenyek e = ((List<Eredmenyek>)adatok).get(0);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(e, new FileWriter(kimenetiÁllomány));
		} catch (JAXBException e) {
			logger.error("Hiba a fájl mentése közben: " + e.getMessage());
		}
		
	}

	
	@Override
	public List<? extends Object> betöltFájlból(String forrásÁllomány) throws IOException {
		
		JAXBContext jaxbContext;
		Eredmenyek eredmények = null;
		
		java.io.File fájl = new java.io.File(forrásÁllomány);
		
		logger.info("Eredmények betöltése fájlból.");
		
		if(!fájl.exists()){
			logger.warn("A fájl még nem létezik, üres sablon létrehozása.");
			java.util.ArrayList<Eredmenyek> lis = new java.util.ArrayList<>();
			lis.add(new Eredmenyek());
			kimentFájlba(lis, forrásÁllomány);
		}
		
		try {
			
			jaxbContext = JAXBContext.newInstance(Eredmenyek.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			eredmények = (Eredmenyek)unmarshaller.unmarshal(new FileReader(forrásÁllomány));
			
		} catch (JAXBException e) {
			logger.error("Hiba a fájl betöltése közben: " + e.getMessage());
		}
		
		return eredmények.getEredmények();
	}

}
