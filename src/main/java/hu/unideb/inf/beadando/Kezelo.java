package hu.unideb.inf.beadando;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.unideb.inf.beadando.nezet.NezetVezerlo;


/**
 * A projekt {@code main()} metódusát tartalmazó osztálya.
 * @author Balogh Ádám
 *
 */
public class Kezelo{
	
	/**
	 * Naplózásért felelős logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(Kezelo.class);
	
	/**
	 * A program fő belépési pontja.
	 * <p>Elindítja a {@code NezetVezerlo} osztályt.
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args) {
		
		logger.info("A program elidul.");
		ellenőrizIndulásiKövetelmények();
		logger.info("Vezérlés átadása a NezetVezerlonek.");
		NezetVezerlo.main(args);
		logger.info("A program bezárul.");
	}

	
	/**
	 * Leellenőrzi, hogy  teljesülnek -e a projekt indulási követelményei.
	 * A játék által generált fájloknak létrehoz a felhasználó könyvtárában
	 * egy {@code .sudoku} mappát, amennyiben az még nem létezik.
	 */
	private static void ellenőrizIndulásiKövetelmények() {

		String userhome = System.getProperty("user.home");
		String separator = System.getProperty("file.separator"); 
		
		File mappa = new File(userhome + separator + ".sudoku");
		logger.info(mappa + " meglétének ellenőrzése...");

		if (!mappa.exists()) {
			mappa.mkdir();
			logger.info("Játékmappa létrehozva a következő helyre: " + "\"" + mappa + "\".");
		}
		
		logger.info("Az indulási követelmények ellenőrzése megtörtént.");
	}
}
