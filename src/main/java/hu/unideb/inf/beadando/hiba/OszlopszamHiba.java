package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>OszlopSzamHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor a megadott oszlopszám kívül esik a tábla megengedett oszlopszámainak tartományán.
 * az oszlopszám értékének ellenőrzésére a <code>CellaEllenor</code> osztály egyik statikus metódusa szolgál.
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class OszlopszamHiba extends Exception {

	
	/**
	 *	A hibát kiváltó oszlopszám. 
	 */
	private int oszlop;
	
	
	/**
	 *	A maximálisan megengedett oszlopszám értéke.
	 *  Ez az érték megegyezik a <code>Tabla</code> méretével.
	 *  
	 *   @see hu.unideb.inf.beadando.modell.Tabla#MÉRET
	 */
	private final int MAX;
	
	
	
	/**
	 * A kivétel létrehozására szolgáló konstruktor.
	 * Beállítja a hibát kiváltó oszlopszámot és a megengedett maximális értéket.
	 * @param oszlop a hibát kiváltó oszlopszám
	 * @param max a maximálisan megadható oszlopszám
	 */
	public OszlopszamHiba(int oszlop, int max) {
		super();
		MAX = max;
		this.oszlop = oszlop;
	}

	/**
	 *  A felhasználó számára érthető hibaüzenet generálása a fellépő hiba forrásának megadásával.
	 *  Az üzenet tipp formájában segítséget nyújt ugyanezen hiba elkerüléséhez.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		
		return "Hibás oszlopszám [ " + oszlop + " ] került megadásra!\n"
				+ "TIPP: Csak 1 és " + MAX + " közötti érékek megengedettek.";
	}
	
	

}
