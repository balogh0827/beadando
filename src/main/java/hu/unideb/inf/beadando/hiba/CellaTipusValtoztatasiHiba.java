package hu.unideb.inf.beadando.hiba;

import hu.unideb.inf.beadando.modell.Cella;
import hu.unideb.inf.beadando.modell.CellaTipus;

/**
 * Az <code>CellaTipusValtoztatasiHiba</code> kivétel annak az esetnek a jelzésére szolgál, 
 * amikor a cella típusának megváltoztatása nem megengedett állapotot eredményezne.
 * A hibás állapot felismeréséért a <code>CellaEllenor</code> osztály egyik statikus metódusa felel. 
 * 
 * @author Balogh Ádám
 */
@SuppressWarnings("serial")
public class CellaTipusValtoztatasiHiba extends Exception {

	
	/**
	 *	A megváltozatatandó típusú <code>Cella</code>.
	 *
	 *  @see Cella
	 */
	private Cella cella;
	
	
	/**
	 *	A beállítandó, a kivételt kiváltó <code>CellaTipus</code>.
	 *
	 * @see CellaTipus
	 */
	private CellaTipus típus;
	
	
	
	/**
	 * A kivétel létrehozására és a paraméterek beállítására szolgáló konstruktor.
	 * @param cella a megváltoztatandó cella
	 * @param újTípus az új nem megengedett cellatípus
	 */
	public CellaTipusValtoztatasiHiba(Cella cella, CellaTipus újTípus) {
		super();
		this.cella = cella;
		this.típus = újTípus;
	}

	
	/**
	 * A kivétel leírását generáló metódus.
	 * 
	 * @return a leírást tartalmazó {@code String}
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "A(z) " 
				+ cella.getSorszám() + ".sor "
				+  cella.getOszlopszám() +  ".oszlopában "
				+ "levő cella nem módosítható!";
	}
	
	
	/**
	 * A hiba részletes leírását generálja nyomkövetési célból.
	 * 
	 * @return részletes leírás a kivétel okáról
	 */
	public String getRészletesHibaÜzenet(){
		return getMessage() + "\nA cella tipusa " + cella.getCellaTipus() 
				+ ", ami nem változatható " + típus.toString() + " típusra!"; 
	}
	
}
