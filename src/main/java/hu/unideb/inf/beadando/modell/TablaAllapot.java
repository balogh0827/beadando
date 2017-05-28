package hu.unideb.inf.beadando.modell;

/**
 * A <code>TablaAllapot</code> nevű felsorolásos típus a {@link Tabla#állapot} lehetséges értékeit írja le.
 * <pre>Ezek az alábbi értékek lehetnek: <br>
 *    <b>{@link #HIÁNYOS}</b><br>
 *    <b>{@link #MEGOLDOTT}</b><br>
 * </pre>
 * 
 * @author Balogh Ádám
 */
public enum TablaAllapot {
	/**
	 * Egy tábla állapota <code>HIÁNYOS</code>, ha van olyan cella a táblában, melynek tipusa {@link CellaTipus#ÜRES}.
	 * @see TablaAllapot
	 */
	HIÁNYOS,
	/**
	 * Egy tábla állapota <code>MEGOLDOTT</code>, amennyiben a táblában minden cella tipusa vagy {@link CellaTipus#MEGADOTT} vagy {@link CellaTipus#KITÖLTÖTT}.
	 * @see TablaAllapot
	 */
	MEGOLDOTT
}
