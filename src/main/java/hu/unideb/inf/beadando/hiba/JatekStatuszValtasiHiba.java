package hu.unideb.inf.beadando.hiba;

import hu.unideb.inf.beadando.modell.JatekStatusz;

/**
 * Az <code>JatekStatuszValtasiHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor a <code>Jatek</code> állapota nem megfelelő állapotra módosul.
 * A módosításokat a <code>JatekStatuszEllenor</code> osztály statikus metódusa ellenőrzi
 *  a <code>JatekStatusz</code> típus felhasználásával.
 *  
 * @author Balogh Ádám
 * @see hu.unideb.inf.beadando.modell.Jatek
 * @see hu.unideb.inf.beadando.modell.JatekStatusz
 */
@SuppressWarnings("serial")
public class JatekStatuszValtasiHiba extends Exception {
	
	
	
	/**
	 *	Az aktuális <code>Jatek</code> jelenleg érvényes állapota. 
	 */
	private JatekStatusz jelenlegi;
	
	
	/**
	 *	A kivételt kiváltó nem megengedett játékállapot. 
	 */
	private JatekStatusz új;
	
	
	
	/**
	 * A kivétel létrehozásáért felelős konstruktor.
	 * 
	 * @param jelenlegi a <code>Jatek</code> aktuális állapota
	 * @param új az újonnan beállítandó hibás állapot
	 */
	public JatekStatuszValtasiHiba(JatekStatusz jelenlegi, JatekStatusz új) {
		this.jelenlegi = jelenlegi;
		this.új = új;
	}
	
	
	/**
	 * Hibaüzenet generálása a kiváltó okok megnevezésével.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage(){
		return "Játék státusz változtatása nem engedélyezett ["
				+ jelenlegi.toString() + " -> " + új.toString() + "]";
	}
	
}
