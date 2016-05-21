package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>AzonosErtekASorbanHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor az aktuális cellába beírni kívánt érték már szerepel a Sudoku tábla ugyanazon sorának egy másik cellájában.
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class AzonosErtekASorbanHiba extends Exception{

	/**
	 *	A hibaüzenet generálásához szükséges mező, mely a sorban már szereplő, a hibát kiváltó értéket tartalmazza. 
	 */
	private String érték;
	

	/**
	 *	A hibás cella sorszámát tartalmazó mező.  
	 */
	private int sorszám;
	
	
	/**
	 * A mezők értékeinek beállítására szolgáló konstruktor.
	 * 
	 * @param sorszám a kivételt kiváltó <code>Cella</code>
	 *  sorszáma a <code>Tabla</code>-ban.
	 * @param érték a sorban már szereplő érték
	 * @see hu.unideb.inf.beadando.modell.Cella
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	public AzonosErtekASorbanHiba(int sorszám, String érték) {
		this.sorszám = sorszám;
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
				+ sorszám + ". sorban!";
	}
	
}
