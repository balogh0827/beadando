package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>SorszamHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor a megadott sorszám kívül esik a tábla megengedett sorszámainak tartományán.
 * A sorszámérték ellenőrzése a <code>CellaEllenor</code> osztály egyik statikus metódusa által történik.
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class SorszamHiba extends Exception {

	
	/**
	 * A hibás sorszámot tartalmazó mező.
	 */
	private int sorszám;
	
	
	/**
	 *	A megengedett maximális sorszám.
	 *	Értéke megegyezik a <code>Tabla</code> méretével.
	 *
	 * @see hu.unideb.inf.beadando.modell.Tabla#MÉRET
	 */
	private final int MAX;
	
	
	/**
	 * A kivétel létrehozásához és az értékek beállításához használatos konstruktor.
	 * @param sorszám a hibás sorszám
	 * @param maximális a megadható maximális sorszám értéke
	 */
	public SorszamHiba(int sorszám, int maximális){
		super();
		this.sorszám = sorszám;
		MAX = maximális;
	}

	/**
	 *  A felhasználó számára érthető hibaüzenet generálása a fellépő hiba forrásának megadásával.
	 *  Az üzenet segítséget ad a további hasonló jellegű hibák elkerüléséhez tipp formában.
	 *  
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Hibás sorszám [ " + sorszám + " ] került megadásra!\n"
				+ "TIPP: Csak 1 és " + MAX + " közötti értékek megengedettek.";
	}
	
	
}
