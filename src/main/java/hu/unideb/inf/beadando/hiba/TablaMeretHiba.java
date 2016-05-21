package hu.unideb.inf.beadando.hiba;

/**
 * Az <code>TablaMeretHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor a létrehozandó táblának megadott méretszám helytelen.
 * Jelenleg csak a <code>4-es, 9-es és 16-os</code> értékek vannak megengedve.
 * Ha a fentiektől eltérő méretszám kerül megadásra a tábla létrehozásakor, akkor kiváltódik a kivétel.
 * A méretszám ellenőrzéséért a <code>CellaEllenor</code> osztály statikus metódusai felelnek.
 * @author Balogh Ádám
 * 
 */
@SuppressWarnings("serial")
public class TablaMeretHiba extends Exception {
	
	
	/**
	 *	A hibás táblaméretet tároló adattag. 
	 */
	private int méret;
	
	
	/**
	 * A kivétel létrehozásához használt konstruktor.
	 * @param méret a hibás méretet tartalmazza
	 */
	public TablaMeretHiba(int méret) {
		this.méret = méret;
	}
	
	
	/**
	 * Hibaüzenet a kivételt kiváltó okokról.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage(){
		return "A tábla mérete nem állítható be erre az értékre: " + méret + " !"
				+ "\nFolytatás alapértelmezett táblaméret alkalmazásával.";
	}
}
