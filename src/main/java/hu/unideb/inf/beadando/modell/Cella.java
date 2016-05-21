package hu.unideb.inf.beadando.modell;


/**
 * A {@code Cella} osztály a {@link Tabla} osztály egy celláját reprezentálja a programban.
 * @author Balogh Ádám
 */
public class Cella {
	
	/**
	 *	A cella sorszáma a Sudoku táblán belül. 
	 * 
	*/
	private int sorszám;
	
	/**
	 *	A cella oszlopszáma a Sudoku táblán belül. 
	 * 
	*/
	private int oszlopszám;
	
	
	/**
	 * 	A cellában szereplő érték <code>String</code> reprezentációja.
	 * 	
	 */
	private String tartalom;
	
	
	/**
     * A <code>Cella<code> típusát írja le az {@link CellaTipus} felsorolásos típus segítségével.
     */
	private CellaTipus tipus;
		
	
	
	 /**
     * Konstruktor, mely létrehoz egy új <code>Cella</code> objektumot üres tartalommal és {@link CellaTipus#ÜRES} állapottal.
     * @param sorszám   <code>int</code>, a <code>Cella</code> sorszáma a {@link Tabla}-ban.
     * @param oszlopszám    <code>int</code>, a <code>Cella</code> oszlopszáma a {@link Tabla}-ban.
     */
	public Cella(int sorszám, int oszlopszám) {
		this.sorszám = sorszám;
		this.oszlopszám = oszlopszám;
		setTartalom("");
	}
	

    /**
     * Visszaadja a <code>Cella</code> {@link Tabla}-beli sorszámát.
     * @return a {@code sorszám} példányváltozó értéke
     * @see Cella#sorszám
     */
	public int getSorszám() {
		return sorszám;
	}


	 /**
     * Visszaadja a cella {@link Tabla}-beli oszlopszámát.
     * @return {@code int} az {@code oszlopszám} példányváltozó értéke
     */
	public int getOszlopszám() {
		return oszlopszám;
	}
	

	 /**
     * Visszaadja a reprezentált <code>Cella</code> tartalmát.
     * @return {@code String} a {@link Cella#tartalom} változó értéke
     */
	public String getTartalom() {
		return tartalom;
	}
	
	
	/**
     * Visszaadja a <code>Cella</code> típusát.
     * @return {@link CellaTipus} a {@code tipus} példányváltozóváltozó értéke
     */
	public CellaTipus getCellaTipus(){
		return tipus;
	}
	
	
	/**
     * Beállítja  a {@code Cella} tartalmát az adott értékre.
     * Üres érték esetén a <code>Cella</code> tipusa {@link CellaTipus#ÜRES} lesz.
     * @param tartalom a beírandó érték <code>String</code> reprezentációja.
     */
	public void setTartalom(String tartalom){
		if(tartalom.equals(""))
			setCellaTipus(CellaTipus.ÜRES);
		this.tartalom = tartalom;
	}
	
	 /**
     * Lehetővé teszi a {@code Cella} tipusának módosítását. 
     * @param tipus az újonnan beállítandó tipus
     */
	public void setCellaTipus(CellaTipus tipus){
		this.tipus = tipus;
	}
	
	
	 /**
     * Leírást ad a <code>Cella</code> {@code tartalom} változójáról.
     * @return a {@code Cella} tartalma  <code>String</code>-ként
     */
	@Override
	public String toString() {
		return tartalom;
	}

}
