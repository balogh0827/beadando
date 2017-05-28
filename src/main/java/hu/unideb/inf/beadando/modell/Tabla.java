package hu.unideb.inf.beadando.modell;


/**
 * 	Sudoku táblát reprezentáló osztály.
 * 
 * @author Balogh Ádám
 *
 */
public class Tabla {
	
	
	
	/**
	 *	A {@link Tabla} méretét megadó konstans, mely a tábla létrehozásakor kap értéket a konstruktoron keresztül. 
	 */
	private final int MÉRET;
	
	
	/**
	 *	A teljes tábla reprezentálására szolgáló, {@link Cella} objektumokból álló kvadratikus márix. 
	 */
	private Cella[][] teljesTábla;
	

	/**
	 *	A <code>Tabla</code> állapotát írja le a {@link TablaAllapot} felsorolásos típus segítségével. 
	 */
	private TablaAllapot állapot;
	
	
	/**
	 * Egy <code>Tabla</code> objektum létrehozására szolgáló konstruktor.
	 * @param táblaméret a {@link Tabla#MÉRET}-et állítja be a paraméter értékére
	 */
	public Tabla(int táblaméret){
		MÉRET = táblaméret;
		inicializál();
	}
	
	
	/**
	 * A {@link Tabla#MÉRET} értékét lekérdező metódus.
	 * @return a <code>Tabla</code> mérete
	 */
	public int getTáblaMéret(){
		return MÉRET;
	}
	
	
	
	/**
	 * Az {@link Tabla#állapot} mező értékét lekérdező metódus.
	 * @return a <code>Tabla</code> állapota
	 */
	public TablaAllapot getTáblaÁllapot(){
		return állapot;
	}
	
	
	/**
	 * Az {@link Tabla#állapot} mező értékét állítja be a paraméter értékére.
	 * @param állapot a <code>Tabla</code> állapota
	 */
	public void setTáblaÁllapot(TablaAllapot állapot){
		this.állapot = állapot;
	}
	

	
	/**
	 * 	A <code>Tabla</code> inicializálására szolgáló {@code private} metódus.
	 *  Létrehozza a {@link #teljesTábla} mátrixot a {@link Cella#Cella(int, int)} konstruktor használatával,
	 *  majd beállítja az {@link #állapot}-ot {@link TablaAllapot#HIÁNYOS}-ra
	 */
	private void inicializál(){
		
		teljesTábla = new Cella[MÉRET][MÉRET];
		
		for(int sor = 0; sor < teljesTábla.length; sor++){
			for(int oszlop = 0; oszlop < teljesTábla[sor].length; oszlop++){
				teljesTábla[sor][oszlop] = new Cella(sor + 1, oszlop + 1);
			}
		}
		
		állapot = TablaAllapot.HIÁNYOS;
		
	}
	
	
	/**
	 * Az aktuális <code>Tabla</code> egy {@link Cella}-ját visszaadó metódus.
	 * @param sor a {@code Cella} sorszáma a táblában.
	 * @param oszlop a {@code Cella} oszlopszáma a táblában.
	 * @return a paraméterek alapján meghatározott {@link Cella}
	 */
	public Cella getCella(int sor, int oszlop){
		return teljesTábla[sor - 1][oszlop - 1];
	}

	
}
