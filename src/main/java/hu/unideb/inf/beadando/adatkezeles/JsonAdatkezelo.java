package hu.unideb.inf.beadando.adatkezeles;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Az osztály JSON formátumú adatok kezelését valósítja meg.
 * @author Balogh Ádám
 *
 */
public class JsonAdatkezelo implements AdatkezeloInterface{
	
	/**
	 * Naplózásért felelős logger. 
	 */
	private static Logger logger = LoggerFactory.getLogger(JsonAdatkezelo.class);

	@Override
	public void kimentFájlba(List<? extends Object> adatok, String kimenetiÁllomány) throws IOException {
		
		logger.info("Játékosinformációk kimentése fájlba.");
		
		Gson gson = new GsonBuilder().create();
		
		String kimenet = gson.toJson(adatok);
		
		try(FileWriter writer = new FileWriter(kimenetiÁllomány)){
			gson.toJson(kimenet, writer);
		}
	}
	

	@Override
	public List<String> betöltFájlból(String forrásÁllomány) throws IOException {
		
		logger.info("Játékosinformációk betöltése.");
		
		Gson gson = new GsonBuilder().create();
		
		String adatok = "";
		
		try(FileReader fr = new FileReader(forrásÁllomány)){
			adatok = gson.fromJson(fr, String.class);
			adatok = adatok.substring(2, adatok.length()-2);
		} 
		
		String[] elemek = adatok.split("\",\"");
		
		List<String> lista = new ArrayList<>();
		
		for(String elem : elemek){
			lista.add(elem);
		}
		
		return lista;
	}

}
