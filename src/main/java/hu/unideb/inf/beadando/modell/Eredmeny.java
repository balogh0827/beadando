package hu.unideb.inf.beadando.modell;

import hu.unideb.inf.beadando.kontroll.JatekVezerlo;

/**
 * Egy Sudoku játék tulajdonságait leíró osztály.
 * Az eredmények lekérdezéséhez használt entitás, mely más osztályok adattagjait gyűjti össze és csomagolja be.
 * 
 * @author Balogh Ádám
 *
 */
public class Eredmeny {

	
	/**
	 *  A játékos nevét tartalmazó adattag.
	 *	A {@link Jatekos} osztály {@link Jatekos#név} mezőjének értékét tárolja.
	 */
	private String játékosNév;
	
	
	/**
	 *  A tábla méretét tartalmazó adattag.
	 *	A {@link Tabla} osztály {@link Tabla#MÉRET} mezője határozza meg az értékét.
	 */
	private String táblaméret;
	
	
	/**
	 *  A játék állapotát tartalmazó adattag.
	 *	A {@link Jatek} osztály {@link Jatek#állapot} mezőjének értéke <code>String</code> formában.
	 */
	private String játékállapot;
	
	
	/**
	 *  A játék időtartamát tartalmazó adattag.
	 * 	A {@link JatekVezerlo} osztály {@link JatekVezerlo#lekérElteltidőStringként()} metódusának visszatérési értékét tartalmazza.
	 */
	private String időtartam;
	
	
	
	/**
	 *  A játék befejezésének dátumát tartalmazó adattag.
	 *	A {@link Jatek} osztály {@link Jatek#befejezésIdeje} mezőjének <code>String</code> reprezentációja. 
	 */
	private String dátum;
	
	
	/**
	 *  Az {@code Eredmeny} osztály egy példányát létrehozó konstruktor.
	 *  Beállítja az osztály mezőit az alapértelmezett üres értékre. 
	 */
	public Eredmeny(){
		játékosNév = "";
		táblaméret = "";
		játékállapot = "";
		időtartam = "";
		dátum = "";
	}
	
	

	/**
	 * Visszaadja a játékos nevét. 
	 * @return a {@link #játékosNév} mező értéke 
	 */
	public String getJátékosNév() {
		return játékosNév;
	}

	
	/**
	 * Segítségével beállítható a játékos neve.
	 * @param játékosNév ez lesz az {@link #játékosNév} mező új értéke
	 */
	public void setJátékosNév(String játékosNév) {
		this.játékosNév = játékosNév;
	}

	
	/**
	 * Lekérdezi a tábla méretét.
	 * @return a {@link #táblaméret} mező értéke.
	 */
	public String getTáblaméret() {
		return táblaméret;
	}

	
	/**
	 * Beállítja a tábla méretét a paraméter értékére.
	 * @param táblaméret ez lesz a {@link #táblaméret} mező új értéke.
	 */
	public void setTáblaméret(String táblaméret) {
		this.táblaméret = táblaméret;
	}

	/**
	 * Lekérdezi a játék állapotát.
	 * @return a {@link #játékállapot} mező értéke
	 */
	public String getJátékállapot() {
		return játékállapot;
	}

	/** Beállíja a játékállapot értékét a paraméterként megadottra.}
	 * @param játékállapot a {@link #játékállapot} mező új állapota
	 */
	public void setJátékállapot(String játékállapot) {
		this.játékállapot = játékállapot;
	}

	/**
	 * Lekérdezi mennyi ideje tart a játék.
	 * @return a {@link #időtartam} mező értéke
	 */
	public String getIdőtartam() {
		return időtartam;
	}

	/**
	 * Beállítja a játék kezdete óta eltelt időt a paraméter által megadottra.
	 * @param időtartam a {@link #időtartam} mező új értéke
	 */
	public void setIdőtartam(String időtartam) {
		this.időtartam = időtartam;
	}

	/**
	 * Lekérdezi a játék befejezésének dátumát.
	 * @return a {@link #dátum} mező értéke
	 */
	public String getDátum() {
		return dátum;
	}

	/**
	 * Beállítja a játék befejezésének dátumát a paraméternek megfelelően.
	 * @param dátum a {@link #dátum} új értékét tartalmazza
	 */
	public void setDátum(String dátum) {
		this.dátum = dátum;
	}

	/**
	 * Leírást ad az {@code Eredmeny} osztály adatagjairól és azok értékeiről.
	 */
	@Override
	public String toString() {
		return "Eredmeny [játékosNév=" + játékosNév + ", táblaméret=" + táblaméret + ", játékállapot=" + játékállapot
				+ ", időtartam=" + időtartam + ", dátum=" + dátum + "]";
	}
	
	
	
}
