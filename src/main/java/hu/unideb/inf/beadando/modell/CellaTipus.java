package hu.unideb.inf.beadando.modell;


/**
 * A <code>CellaTipus</code> nevű felsorolásos típus a {@link Cella#tipus} lehetséges értékeit írja le.
 * <pre>Egy cella lehet <br>
 *    <b>{@link #ÜRES}</b><br>
 *    <b>{@link #MEGADOTT}</b><br>
 *    <b>{@link #KITÖLTÖTT}</b><br>
 * </pre>
 * @author Balogh Ádám
 */
public enum CellaTipus {
	/**
	 * Egy cella típusa <code>ÜRES</code>, ha nem tartalmaz értéket.
	 * @see CellaTipus
	 */
	ÜRES, 
	/**
	 * Egy cella típusa <code>MEGADOTT</code>, ha nem a játékos adja meg a tartalmát.
	 * @see CellaTipus
	 */
	MEGADOTT, 
	/**
	 * Egy cella típusa <code>KITÖLTÖTT</code>, ha tartalmát a játék folyamán a játékos helyesen adja meg.
	 * @see CellaTipus
	 */
	KITÖLTÖTT
}
