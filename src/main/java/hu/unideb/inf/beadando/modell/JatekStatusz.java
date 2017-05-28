package hu.unideb.inf.beadando.modell;


/**
 * A <code>JatekStatusz</code> nevű felsorolásos típus a {@link Jatek#állapot} lehetséges értékeit írja le.
 * <pre>Ez az alábbi értékeket veheti fel: <br>
 *    <b>{@link #ÚJ}</b><br>
 *    <b>{@link #ELKEZDETT}</b><br>
 *    <b>{@link #FELADOTT}</b><br>
 *    <b>{@link #BEFEJEZETT}</b><br>
 * </pre>
 * 
 * @author Balogh Ádám
 */
public enum JatekStatusz {
	/**
	 * Egy játék állapota <code>ÚJ</code>, ha létrehozásra került a játék, de még nem indították el.
	 * @see JatekStatusz
	 */
	ÚJ, 
	/**
	 * Egy játék állapota <code>ELKEZDETT</code>, ha elindították és éppen folyamatban van, azaz a játékos éppen játszik vele.
	 * @see JatekStatusz
	 */
	ELKEZDETT, 
	/**
	 * Egy játék állapota <code>FELADOTT</code>, ha a játékos abbahagyta, de még nincs minden cella kitöltve a táblában.
	 * @see JatekStatusz
	 */
	FELADOTT, 
	/**
	 * Egy játék állapota <code>BEFEJEZETT</code>,  ha a játékos sikeresen végigjátszotta a játékot és kitöltötte a táblát.
	 * @see JatekStatusz
	 */
	BEFEJEZETT
}
