package hu.unideb.inf.beadando.modell;

/**
 * A <code>TablaAllapot</code> nevű felsorolásos típus a {@link Tabla#állapot} lehetséges értékeit írja le.
 * <pre>Ezek az alábbi értékek lehetnek: <br>
 *    <b>HIÁNYOS</b>, ha van olyan  {@link Cella} a {@link Tabla}-ban, melynek tipusa {@link CellaTipus#ÜRES};<br>
 *    <b>MEGOLDOTT</b>, amennyiben a {@link Tabla}-ban minden {@link Cella} tipusa vagy {@link CellaTipus#MEGADOTT} vagy {@link CellaTipus#KITÖLTÖTT};<br>
 * </pre>
 * 
 * @author Balogh Ádám
 */
public enum TablaAllapot {
	HIÁNYOS, MEGOLDOTT
}
