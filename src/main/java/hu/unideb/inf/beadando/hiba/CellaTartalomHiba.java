package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>CellaTartalomHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor az aktuális cellába beírni kívánt érték nem felel meg a beírható értékekre vonatkozó feltételeknek.
 * A fent említett feltételek teljesülését a <code>CellaEllenor</code> osztály statikus metódusai vizsgálják.
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class CellaTartalomHiba extends Exception {
	
	
	/**
	 *	A hibás <code>Cella</code> sorszámát tartalmazó mező. 
	 * @see hu.unideb.inf.beadando.modell.Cella
	 */
	int sorszám;
	
	
	/**
	 * A hibás <code>Cella</code> oszlopszámát tartalmazó mező.
	 *  @see hu.unideb.inf.beadando.modell.Cella
	 */
	int oszlopszám;
	
	
	/**
	 * A cellába írandó nem megengedett értéket tartalmazó mező.
	 */
	String érték;
	
	
	
	/**
	 *  A maximális cellába írható értéket tároló mező.
	 *  Ez az érték megegyezik a <code>Tabla</code> méretével.
	 *  
	 *  @see hu.unideb.inf.beadando.modell.Tabla#MÉRET
	 */
	private final int MAX;

	
	
	/**
	 * A mezők értékeinek beállítására és a hiba létrehozására szolgáló konstruktor.
	 * @param sorszám annak a cellának a sorszáma, amelyikbe a hibás értéket akarták beírni
	 * @param oszlopszám annak a cellának az oszlopszáma, amelyikbe a hibás értéket akarták beírni
	 * @param érték a kivételt kiváltó érték
	 * @param max a maximálisan beírható érték
	 */
	public CellaTartalomHiba(int sorszám, int oszlopszám, String érték, int max) {
		super();
		this.sorszám = sorszám;
		this.oszlopszám = oszlopszám;
		this.érték = érték;
		MAX = max;
	}

	
	/**
	 *  A felhasználó számára érthető hibaüzenet generálása a fellépő hiba forrásának megadásával.
	 *  A hibaüzenet segítséget is nyújt a további hibák elkerüléséhez tipp formájában.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		
		return "Hibás értéket [ " + érték + " ] próbált beírni a(z) "
				+ sorszám + ". sor "
				+ oszlopszám + ". oszlopában "
				+ "található cellába!\n"
				+ "TIPP: Csak 1 és " + MAX + " közötti számokat írjon a cellákba.";
	}
	
	

}
