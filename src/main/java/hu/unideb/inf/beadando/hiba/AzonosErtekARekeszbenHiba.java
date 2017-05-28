package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>AzonosErtekARekeszbenHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor az aktuális cellába beírni kívánt érték már szerepel a Sudoku táblában
 * az aktuális cellához tartozó négyzetben (melynek neve a játékban rekesz).
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class AzonosErtekARekeszbenHiba extends Exception{

	/**
	 *	A hibát kiváltó cella sorszámát tartalmazó mező.  
	 */
	private int sorszám;
	
	/**
	 * A hibát kiváltó cella oszlopszámát tartalmazó mező.
	 */
	private int oszlopszám;
	
	/**
	 *	A rekeszben már szereplő, a hibát kiváltó érték. 
	 */
	private String érték;
	
	
	/**
	 * A mezők értékeinek beállítására szolgáló konstruktor.
	 * 
	 * @param sorszám a kivételt kiváltó <code>Cella</code>
	 *  sorszáma a <code>Tabla</code>-ban.
	 *  @param oszlopszám a kivételt kiváltó <code>Cella</code>
	 *  oszlopszáma a <code>Tabla</code>-ban.
	 * @param érték a rekeszben már szereplő érték
	 * @see hu.unideb.inf.beadando.modell.Cella
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	public AzonosErtekARekeszbenHiba(int sorszám, int oszlopszám, String érték) {
		this.sorszám = sorszám;
		this.oszlopszám = oszlopszám;
		this.érték = érték;
	}
	
	/**
	 *  A felhasználó számára érthető hibaüzenet generálása a fellépő hiba forrásának megadásával.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Már szerepel " +  érték + " a(z) "
				+ sorszám + " .sorhoz és a(z) "
				+ oszlopszám + " .oszlophoz tartozó rekeszben!";
	}
	
	
}
