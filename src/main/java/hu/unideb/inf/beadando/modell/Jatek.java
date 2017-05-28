package hu.unideb.inf.beadando.modell;

import java.time.LocalDateTime;


/**
 * Egy Sudoku játékmenetet reprezentáló osztály.
 * @author Balogh Ádám
 */

public class Jatek {
	

	/**
	 *	A játék állapotát leíró mező.
	 *	Megadható értékeit a {@link JatekStatusz} felsorolásos típus adja meg.  
	 */
	private JatekStatusz állapot;
	
	
	/**
	 *  
	 *	A játék befejezésének idejét tároló mező.
	 *	 
	 */
	private LocalDateTime befejezésIdeje;
	
	
	
	/**
	 *	A játékot játszó személy adatait tároló objektum.
	 *	
	 *	@see Jatekos
	 */
	private Jatekos játékos;
	
	
	/**
	 *  A játék létrehozásakor használt paraméter nélküli konstruktor.
	 *  Alapértelmezett beállításokat szolgáltat.
	 *  Létrehoz egy új játékost a {@link Jatekos#Jatekos()} konstruktor segítségével,
	 *  majd beállítja a {@link Jatek} állapotát {@link JatekStatusz#ÚJ} állapotra.
	 */
	public Jatek(){
		játékos = new Jatekos();
		állapot = JatekStatusz.ÚJ;
	}
	
	
	/**
	 *  Beállítja a játékos nevét a paraméter által megadott étrékre.
	 * @param név a {@link Jatekos#név} új értéke
	 */
	public void setJátékosNév(String név){
		játékos.setNév(név);
	}
	
	
	/**
	 * Lekérdezi a játékos nevét.
	 * @return a {@link Jatekos#név} tartalma
	 */
	public String getJátékosNév(){
		return játékos.getNév();
	}
	
	/**
	 *  Beállítja a befejezés idejét a paraméter által megadott étrékre.
	 * @param befejezésiIdő a {@link #befejezésIdeje}-nek új értékét tartalmazza
	 */
	public void setBefejezésIdeje(LocalDateTime befejezésiIdő){
		befejezésIdeje = befejezésiIdő;
	}
	
	
	/**
	 * Lekérdezi a játék befejezésénak idejét.
	 * @return a {@link #befejezésIdeje} nevű mező tartalma
	 */
	public LocalDateTime getBefejezésIdeje(){
		return befejezésIdeje;
	}
	
	
	/**
	 * Lekérdezi a játék végállapotát.
	 * @return a {@link #állapot} mező tartalma
	 */
	public JatekStatusz getJátékÁllapot(){
		return állapot;
	}
	
	/**
	 *  Beállítja a játék állapotát a paraméter által megadott étrékre.
	 * @param újÁllapot az {@link #állapot} mező új értékét hordozza
	 */
	public void setJátékÁllapot(JatekStatusz újÁllapot){
		állapot = újÁllapot;
	}
		
	
}
