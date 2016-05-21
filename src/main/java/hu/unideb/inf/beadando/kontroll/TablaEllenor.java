package hu.unideb.inf.beadando.kontroll;

import hu.unideb.inf.beadando.hiba.TablaMeretHiba;

/**
 *  A {@code Tabla} méretét vizsgáló statikus metódust szolgáltató osztály.
 *  
 * @author Balogh Ádám
 * @see hu.unideb.inf.beadando.modell.Tabla
 *
 */
 final class TablaEllenor {
	
	
	/**
	 * Paraméter nélküli konstruktor, mely nem engedi az osztály példányosítását.
	 */
	private TablaEllenor(){}
	

	 /**
	 * Ellenőrzi a {@code táblaméret} paraméter értékét, hogy támogatott e.
	 * @param táblaméret a vizsgálandó méret
	 * @throws TablaMeretHiba amennyiben a {@code táblaméret} paraméter nem támogatott
	 */
	static void ellenőrizTáblaMéret(int táblaméret) throws TablaMeretHiba{
		
		int[] megengedettTáblaMéretek = {4, 9, 16};
		
		for(Integer méret : megengedettTáblaMéretek){
			if(méret == táblaméret){
				return;
			}
		}
		
		throw new TablaMeretHiba(táblaméret);
		
	}
	
}
