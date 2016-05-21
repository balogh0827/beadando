package hu.unideb.inf.beadando.kontroll;

import hu.unideb.inf.beadando.hiba.JatekStatuszValtasiHiba;
import hu.unideb.inf.beadando.modell.Jatek;
import hu.unideb.inf.beadando.modell.JatekStatusz;

/**
 * A {@code JatekStatusz} váltási hibáinak ellenőrzésére szolgáló statikus metódust nyújtó osztály.
 * 
 * @author Balogh Ádám
 *
 */
final class JatekStatuszEllenor {

	/**
	 * A paraméter nélküli <code>private</code> láthatóságú konstruktor megtiltja az osztály példányosítását.
	 */
	private JatekStatuszEllenor(){}
	
	/**
	 * Ellenőrzi a {@code Jatek} állapotának váltását a {@code JatekStatusz} típus felhasználásával.
	 * <p>
	 * Az ellenőrzés az alábbi szabályok alapján hajtódik végre:
	 * <br>Ha nem történik állapotváltás, akkor kivétel sem váltódik ki.
	 * <br>Újonnan létrehozott játék csak {@code JatekStatusz.ELKEZDETT} állapotba kerülhet.
	 * <br>Elkezdett játékból lehet {@code JatekStatusz.FELADOTT} vagy {@code JatekStatusz.BEFEJEZETT} játék.
	 * <br>Akár be lett fejezve a játék, akár fel lett adva  másik állapotba már nem kerülhet.
	 * </p>
	 * @param játék az aktuális játék
	 * @param újÁllapot az az állapot amire váltani szeretnénk
	 * @throws JatekStatuszValtasiHiba ha az {@code újÁllapot} nem megfelelő
	 * @see hu.unideb.inf.beadando.modell.Jatek
	 * @see hu.unideb.inf.beadando.modell.JatekStatusz
	 */
	static void érvényesítJátékStátuszVáltás(Jatek játék, JatekStatusz újÁllapot) throws JatekStatuszValtasiHiba{
		
		JatekStatusz jelenlegi = játék.getJátékÁllapot();
		
		if(jelenlegi == újÁllapot)
			return;
		
		switch(jelenlegi){
			case ÚJ:
				if(újÁllapot != JatekStatusz.ELKEZDETT){
					throw new JatekStatuszValtasiHiba(jelenlegi, újÁllapot);
				}
				break;
			case ELKEZDETT:
				if(újÁllapot == JatekStatusz.ÚJ){
					throw new JatekStatuszValtasiHiba(jelenlegi, újÁllapot);
				}
				break;
			case FELADOTT:
			case BEFEJEZETT:
				throw new JatekStatuszValtasiHiba(jelenlegi, újÁllapot);
				
		}
		
	}
	
}
