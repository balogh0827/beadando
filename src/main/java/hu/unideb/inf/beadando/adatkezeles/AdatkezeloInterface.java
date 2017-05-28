package hu.unideb.inf.beadando.adatkezeles;

import java.io.IOException;
import java.util.List;


/**
 * Az interfész külső adatforrások eléréséhez szolgáltat metódusokat.
 * 
 * @author Balogh Ádám
 *
 */
public interface AdatkezeloInterface {

	
	/**
	 * A kimeneti állományokkal kapcsolatos adatkezelést valósítja meg.
	 * @param adatok a fájlba kimenteni kívánt adatok listája 
	 * @param kimenetiÁllomány azon állomány neve ahová az {@code adatok} mentése történik
	 * @throws IOException amennyiben nem várt hiba történik a {@code kimenetiÁllomány} elérése során
	 */
	public void kimentFájlba(List<? extends Object> adatok, String kimenetiÁllomány) throws IOException;
	
	/**
	 * Lehetővé teszi külső erőforrás állományok tartalmának beolvasását.
	 * @param forrásÁllomány azon állomány neve ahonnan az adatok bevitele történik
	 * @throws IOException amennyiben nem várt hiba történik a {@code forrásÁllomány} elérése során
	 * @return a beolvasott adatok listája
	 */
	public List<? extends Object> betöltFájlból(String forrásÁllomány) throws IOException;
	
	
}
