package hu.unideb.inf.beadando.modell;


/**
 * A <code>JatekStatusz</code> nevű felsorolásos típus a {@link Jatek#állapot} lehetséges értékeit írja le.
 * <pre>Ez az alábbi értékeket veheti fel: <br>
 *    <b>ÚJ</b>, ha létrehozásra került a {@link Jatek}, de még nem indították el;<br>
 *    <b>ELKEZDETT</b>, ha elindították és éppen folyamatban van, azaz a {@link Jatekos} éppen játszik vele;<br>
 *    <b>FELADOTT</b>, ha a <code>Jatekos</code> abbahagyta, de még nincs minden {@link Cella} kitöltve a {@code Tabla}-ban;<br>
 *    <b>BEFEJEZETT</b>, ha a játékos sikeresen végigjátszotta a játékot és kitöltötte a {@link Tabla}-t.<br>
 * </pre>
 * 
 * @author Balogh Ádám
 */
public enum JatekStatusz {
	ÚJ, ELKEZDETT, FELADOTT, BEFEJEZETT
}
