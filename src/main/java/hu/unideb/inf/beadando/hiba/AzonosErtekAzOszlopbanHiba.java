package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>AzonosErtekAzOszlopbanHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor az aktuális cellába beírni kívánt érték már szerepel a Sudoku tábla ugyanazon oszlopának egy másik cellájában.
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class AzonosErtekAzOszlopbanHiba extends Exception {

	/**
	 *	A hibaüzenet generálásához szükséges mező, mely az oszlopban már szereplő, a hibát kiváltó értéket tartalmazza. 
	 */
	private String érték;
	
	
	/**
	 * A hibaüzenet generálásához szükségez mező, mely a hibát kiváltó cella oszlopszámát határozza meg a táblában.
	 */
	private int oszlopszám;
	
	
	/**
	 * A mezők értékeinek beállítására szolgáló konstruktor.
	 * 
	 * @param oszlopszám a kivételt kiváltó <code>Cella</code>
	 *  oszlopszáma a <code>Tabla</code>-ban.
	 * @param érték az oszlopban már szereplő érték
	 * @see hu.unideb.inf.beadando.modell.Cella
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	public AzonosErtekAzOszlopbanHiba(int oszlopszám, String érték) {
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
				+ oszlopszám + ". oszlopban!";
	}
	
}
