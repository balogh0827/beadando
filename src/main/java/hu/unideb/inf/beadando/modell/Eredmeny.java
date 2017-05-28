package hu.unideb.inf.beadando.modell;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Egy Sudoku játék tulajdonságait leíró osztály.
 * Az eredmények lekérdezéséhez használt entitás, mely más osztályok adattagjait gyűjti össze és csomagolja be.
 * 
 * @author Balogh Ádám
 *
 */
@XmlRootElement(name="eredmeny")
@XmlType(propOrder={"jatekosnev", "tablameret", "jatekallapot", "idotartam", "datum"})
public class Eredmeny {

	
	/**
	 *  A játékos nevét tartalmazó adattag.
	 *	A {@link Jatekos} osztály {@link Jatekos#név} mezőjének értékét tárolja.
	 */
	@XmlElement
	private String jatekosnev;
	
	
	/**
	 *  A tábla méretét tartalmazó adattag.
	 *	A {@link Tabla} osztály {@link Tabla#MÉRET} mezője határozza meg az értékét.
	 */
	private String tablameret;
	
	
	/**
	 *  A játék állapotát tartalmazó adattag.
	 *	A {@link Jatek} osztály {@link Jatek#állapot} mezőjének értéke <code>String</code> formában.
	 */
	private String jatekallapot;
	
	
	/**
	 *  A játék időtartamát tartalmazó adattag.
	 * 	A {@link JatekVezerlo} osztály {@link JatekVezerlo#lekérElteltidőStringként()} metódusának visszatérési értékét tartalmazza.
	 */
	private String idotartam;
	
	
	
	/**
	 *  A játék befejezésének dátumát tartalmazó adattag.
	 *	A {@link Jatek} osztály {@link Jatek#befejezésIdeje} mezőjének <code>String</code> reprezentációja. 
	 */
	private String datum;
	
	
	/**
	 *  Az {@code Eredmeny} osztály egy példányát létrehozó konstruktor.
	 *  Beállítja az osztály mezőit az alapértelmezett üres értékre. 
	 */
	public Eredmeny(){
		jatekosnev = "";
		tablameret = "";
		jatekallapot = "";
		idotartam = "";
		datum = "";
	}
	
	

	/**
	 * Visszaadja a játékos nevét. 
	 * @return a {@link #jatekosnev} mező értéke 
	 */
	public String getJatekosnev() {
		return jatekosnev;
	}
	
	
	/**
	 * Lekérdezi a játékos nevét {@link StringProperty} formában.
	 * @return a {@link #jatekosnev} propertyként
	 */
	public StringProperty getJátékosNévProperty(){
		return new SimpleStringProperty(jatekosnev);
	}

	
	/**
	 * Segítségével beállítható a játékos neve.
	 * @param játékosNév ez lesz az {@link #jatekosnev} mező új értéke
	 */
	public void setJatekosNev(String játékosNév) {
		this.jatekosnev = játékosNév;
	}

	
	/**
	 * Lekérdezi a tábla méretét.
	 * @return a {@link #tablameret} mező értéke.
	 */
	public String getTablameret() {
		return tablameret;
	}

	/**
	 * Lekérdezi a tábla méretét {@link StringProperty} formában.
	 * @return a {@link #tablameret} propertyként
	 */
	public StringProperty getTáblaMéretProperty(){
		return new SimpleStringProperty(tablameret);
	}
	
	/**
	 * Beállítja a tábla méretét a paraméter értékére.
	 * @param táblaméret ez lesz a {@link #tablameret} mező új értéke.
	 */
	public void setTablameret(String táblaméret) {
		this.tablameret = táblaméret;
	}

	/**
	 * Lekérdezi a játék állapotát.
	 * @return a {@link #jatekallapot} mező értéke
	 */
	public String getJatekallapot() {
		return jatekallapot;
	}
	
	
	/**
	 * Lekérdezi a {@link #jatekallapot} tartalmát StringProperty formában.
	 * @return a {@code játékállapot} proertyként
	 */
	public StringProperty getJátékÁllapotProperty(){
		return new SimpleStringProperty(jatekallapot);
	}

	/** Beállíja a játékállapot értékét a paraméterként megadottra.
	 * @param játékállapot a {@link #jatekallapot} mező új állapota
	 */
	public void setJatekallapot(String játékállapot) {
		this.jatekallapot = játékállapot;
	}

	/**
	 * Lekérdezi mennyi ideje tart a játék.
	 * @return a {@link #idotartam} mező értéke
	 */
	public String getIdotartam() {
		return idotartam;
	}
	
	/**
	 * Lekérdezi az {@link #idotartam} értékét SimpleStringProperty-ként.
	 * @return az időtartam property formában
	 */
	public StringProperty getIdőtartamProperty(){
		return new SimpleStringProperty(idotartam);
	}

	/**
	 * Beállítja a játék kezdete óta eltelt időt a paraméter által megadottra.
	 * @param időtartam a {@link #idotartam} mező új értéke
	 */
	public void setIdotartam(String időtartam) {
		this.idotartam = időtartam;
	}

	/**
	 * Lekérdezi a játék befejezésének dátumát.
	 * @return a {@link #datum} mező értéke
	 */
	public String getDatum() {
		return datum;
	}

	
	/**
	 * Lekérdezi a {@link #datum} változó értékét SimpleStringProperty-ként.
	 * @return a dátumot tartalmazó property
	 */
	public StringProperty getDátumProperty(){
		return new SimpleStringProperty(datum);
	}
	
	/**
	 * Beállítja a játék befejezésének dátumát a paraméternek megfelelően.
	 * @param dátum a {@link #datum} új értékét tartalmazza
	 */
	public void setDatum(String dátum) {
		this.datum = dátum;
	}

	/**
	 * Leírást ad az {@code Eredmeny} osztály adatagjairól és azok értékeiről.
	 */
	@Override
	public String toString() {
		return "Eredmeny [játékosnév=" + jatekosnev + ", táblaméret=" + tablameret + ", játékállapot=" + jatekallapot
				+ ", időtartam=" + idotartam + ", dátum=" + datum + "]";
	}
	
	
	
}
