package hu.unideb.inf.beadando.kontroll;


import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.modell.Cella;
import hu.unideb.inf.beadando.modell.CellaTipus;



/**
 * A tábla celláinak ellenőrzésére szolgáló statikus metódusokat szolgáltató osztály.
 * Az osztály segítséget nyújt a hibás sorszám, oszlopszám és tartalom 
 * valamint a nem megengedett típusváltoztatás felderítéséhez.
 * 
 * @author Balogh Ádám
 *
 */
final class CellaEllenor {
	
	
	
	/**
	 * Ez a paraméter nélküli konstruktor {@code private} láthatósága miatt nem engedi az osztály példányosítását.
	 */
	private CellaEllenor(){}
	
	
	
	/**
	 *  A {@code Cella} tartalmának és típusának változását ellenőrző metódusok meghívásáért felel.
	 *  <p>Mivel a cella tartalmának megváltoztatása maga után vonhatja a típusának változását is,
	 *  ezért érdemes ezeket együtt kezelni és ellenőrizni. 
	 *  <br>A külvilág felé csak ez a metódus látszik, a benne meghívásra kerülő eljárások rejtve maradnak.
	 *  
	 * @param cella az ellenőrizendő {@code Cella}
	 * @param újTartalom a cellába írandó érték
	 * @param újTípus a cella új típusa a beírt érték alapján
	 * @param maximálisSzám a cellába írható maximális érték, mely egyenlő a {@code Tabla} méretével
	 * @throws CellaTartalomHiba ha az {@code újTartalom} paraméter helytelen
	 * @throws CellaTipusValtoztatasiHiba ha az {@code újTípus} paraméter nem megfelelő
	 * @see hu.unideb.inf.beadando.modell.Cella
	 */
	static void ellenőrizCella(Cella cella, String újTartalom, CellaTipus újTípus, int maximálisSzám)
			throws CellaTartalomHiba, CellaTipusValtoztatasiHiba{
		
		
		ellenőrizÚjCellaTartalom(cella, újTartalom, maximálisSzám);
		ellenőrizCellaTípusátmenet(cella, újTípus);
	}

	
	/**
	 * A <code>Cella<code> új tartalmának ellenőrzését végző metódus.
	 * <p>
	 * Ha az új tartalom üres vagy megegyezik a régi tartalommal, akkor nem váltódik ki kivétel.
	 * <br>Abban az esetben viszont ha a tartalom nem számértéket tartalmaz 
	 * vagy szám esetén nem felel meg az érvényesítés folyamán, akkor {@code CellaTartalomHiba} váltódik ki.
	 *  
	 * @param cella a vizsgálandó cella
	 * @param tartalom a cellába írandó új érték
	 * @param maximálisSzám a {@code tartalom} maximum megengedett értéke
	 * @throws CellaTartalomHiba ha a {@code tartalom} paraméter nem megfelelő
	 */
	private static void ellenőrizÚjCellaTartalom(Cella cella, String tartalom, int maximálisSzám) throws CellaTartalomHiba{
		
		if(tartalom.equals("")) return;
		
		if(tartalom.equals(cella.getTartalom())) return;
		
		int számTartalom;
		
		try{
			számTartalom = Integer.parseInt(tartalom);
		}catch(NumberFormatException e){
			throw new CellaTartalomHiba(cella.getSorszám(), cella.getOszlopszám(), tartalom, maximálisSzám);
		}
		
		if(!érvényes(számTartalom, maximálisSzám)){
			throw new CellaTartalomHiba(cella.getSorszám(), cella.getOszlopszám(), tartalom, maximálisSzám);
		}
		
		
	}
	
	
	
	/**
	 * A <code>Cella<code> új típusának ellenőrzését végző metódus.
	 * <p>
	 * Ha a <code>Cella</code> új típusa megegyezik a jelenlegivel, akkor nem váltódik ki kivétel.
	 * <br>Ha a {@code Cella} típusa {@code CellaTipus.ÜRES} 
	 * akkor bármely típusba történő átváltás engedélyezett.
	 * <br>Amennyiben a típus {@code CellaTipus.MEGADOTT}, abban az esetben  tartalma már nem változhat
	 *  (mivel azt a program adja meg a játék elején), így minden azonostól eltérő típus esetén 
	 *  {@code CellaTipusValtoztatasiHiba} kivétel fog kiváltódni.
	 *  <br>Amikor a cella típusa {@code CellaTipus.KITÖLTÖTT} akkor csak a {@code CellaTipus.MEGADOTT}
	 *  típusra történő változtatáskor kapunk {@code CellaTipusValtoztatasiHiba} kivételt. 
	 * 
	 * @param cella a vizsgálandó cella
	 * @param újTípus  az új beállítandó típus 
	 * @throws CellaTipusValtoztatasiHiba amennyiben az {@code újTípus} nem megfelelő
	 */
	private static void ellenőrizCellaTípusátmenet(Cella cella, CellaTipus újTípus) throws CellaTipusValtoztatasiHiba{
		CellaTipus típus = cella.getCellaTipus();
		
		if(típus == újTípus)
			return;
		
		switch(típus){
			case ÜRES:
				break;
			case MEGADOTT:
					throw new CellaTipusValtoztatasiHiba(cella, újTípus);
			case KITÖLTÖTT:
				if(újTípus == CellaTipus.MEGADOTT){
					throw new CellaTipusValtoztatasiHiba(cella, újTípus);
				}
		}
		
	}
	
	
	/**
	 * Egy {@code Tabla} sorszámának helyességét ellenőrzi.
	 * @param sorszám a tesztelendő sorszám
	 * @param maximum a {@code sorszám} által maximálisan felvehető érték
	 * @throws SorszamHiba amikor a {@code sorszám} paraméter nem érvényes
	 */
	static void ellenőrizSorszám(int sorszám, int maximum) throws SorszamHiba{
		
		if(!érvényes(sorszám, maximum)){
			throw new SorszamHiba(sorszám, maximum);
		}
	}
	
	
	/**
	 * Egy {@code Tabla} oszlopszámának helyességét ellenőrzi.
	 * @param oszlopszám a vizsgálandó oszlopszám
	 * @param maximum az {@code oszlopszám} által felvehető maximális érték
	 * @throws OszlopszamHiba amikor az {@code oszlopszám} paraméter nem érvényes
	 */
	static void ellenőrizOszlopSzám(int oszlopszám, int maximum) throws OszlopszamHiba{
		if(!érvényes(oszlopszám, maximum)){
			throw new OszlopszamHiba(oszlopszám, maximum);
		}
	}
	
	
	/**
	 * Összehasonlítja a paraméterül kapott {@code érték} nagyságát a maximálisan megengedettel.
	 *
	 * @param érték a tesztelendő érték
	 * @param max a legnagyobb felvehető érték
	 * @return <code>true</code> ha az érték a tartományon belül van, egyébként <code>false</code> 
	 */
	private static boolean érvényes(int érték, int max){
		
		return (érték >= 1 && érték <= max);
	}
	
}
