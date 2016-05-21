package hu.unideb.inf.beadando.modell;


/**
 * A <code>CellaTipus</code> nevű felsorolásos típus a {@link Cella#tipus} lehetséges értékeit írja le.
 * <pre>Egy cella lehet <br>
 *    <b>ÜRES</b>, ha nincs benne számérték;<br>
 *    <b>MEGADOTT</b>, ha kezdőcella (vagyis olyan cella, aminek tartalmát nem a játékos adja meg);<br>
 *    <b>KITÖLTÖTT</b>, ha tartalmát a játék folyamán a játékos helyesen adja meg.<br>
 * </pre>
 * @author Balogh Ádám
 */
public enum CellaTipus {
	ÜRES, MEGADOTT, KITÖLTÖTT
}
