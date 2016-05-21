package hu.unideb.inf.beadando.modell;

/**
 * A Sudoku játék egy játékosát reprezentáló osztály.
 * @author Balogh Ádám
 *
 */
public class Jatekos {
	
	
	
	/**
	 *  A játékos nevét tartalmazó adattag.
	 */
	private String név;
	
	
	/**
	 *	Egy játékospéldány létrehozására szolgáló paraméter nélküli konstruktor.
	 * Beállítja a {@link #név} tartalmát az alapértelmezett  {@literal "1. játékos"} értékre.
	 */
	public Jatekos(){
		név = "1. játékos";
	}
	
	
	/**
	 * Lekérdezi a játékos nevét.
	 * @return a {@link #név} mező értéke
	 */
	public String getNév() {
		return név;
	}

	
	/**
	 *  Beállítja a paraméterül kapott játékosnevet.
	 * @param játékosNév a {@link #név} mező új értékét hordozó <code>String</code>
	 */
	public void setNév(String játékosNév) {
		this.név = játékosNév;
	}
}
