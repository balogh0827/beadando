package hu.unideb.inf.beadando.kontroll;

import hu.unideb.inf.beadando.hiba.AzonosErtekARekeszbenHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekASorbanHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekAzOszlopbanHiba;
import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.modell.Cella;
import hu.unideb.inf.beadando.modell.CellaTipus;
import hu.unideb.inf.beadando.modell.Tabla;
import hu.unideb.inf.beadando.modell.TablaAllapot;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 	A Sudoku játéktábla kezelésével kapcsolatos funkcionalitásokat tartalmazó osztály.
 * 	@author Balogh Ádám
 */
public class TablaVezerlo {


	/**
	 * A kezelendő {@code Tabla} osztály egy példánya.
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	private Tabla tábla;
	
	
	/**
	 *	A logoláshoz használt logger.
	 *@see org.slf4j.Logger
	 *@see org.slf4j.LoggerFactory 
	 */
	private static Logger logger = LoggerFactory.getLogger(TablaVezerlo.class);

	/**
	 * Létrehoz egy új üres Sudoku táblát a {@code méret} paraméter értéke alapján.
	 * @param méret a létrehozni kívánt tábla mérete
	 * @throws TablaMeretHiba ha a {@code méret} paraméter értéke nem megfelelő
	 */
	public void létrehozMegadottMéretűTábla(int méret) throws TablaMeretHiba {

		logger.debug("Tábla létrehozása {} mérettel...", méret);
		TablaEllenor.ellenőrizTáblaMéret(méret);
		logger.debug("Tábla létrehozása {} mérettel - sikeres", méret);
		tábla = new Tabla(méret);
	}
	

	/**
	 * Lekérdezi a {@code TablaVezerlo} által kezelt tábla méretét.
	 * @return a tábla mérete
	 */
	public int lekérTáblaMéret() {
		return tábla.getTáblaMéret();
	}
	
	
	/**
	 * Átalakítja a  paraméterként kapott {@code String}-eket tartalmazó listát.
	 * @param lista az adatokat tartalmazó lista
	 * @throws CellaTartalomHiba ha nem érvényes cellatartalom lett megadva
	 * @throws CellaTipusValtoztatasiHiba ha a cella típusa nem változtatható az új típusra
	 * @throws AzonosErtekASorbanHiba ha már van azonos érték a hibát okozó cella sorában
	 * @throws AzonosErtekAzOszlopbanHiba ha már van azonos érték a hibát okozó cella oszlopában
	 * @throws AzonosErtekARekeszbenHiba ha már van azonos érték a hibát okozó cella rekeszében
	 * @throws SorszamHiba ha a sorszám nem megfelelő
 	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 */
	public void feldolgoz(List<String> lista) 
			throws CellaTartalomHiba, CellaTipusValtoztatasiHiba,
				   AzonosErtekASorbanHiba, AzonosErtekAzOszlopbanHiba,
				   AzonosErtekARekeszbenHiba, SorszamHiba, OszlopszamHiba{
		
		String tipus;
		int sor;
		int oszlop;
		String tartalom;
		
		
		for(int i = 0; i < lista.size(); i+=4){
			
			tipus = lista.get(i);
			sor = Integer.parseInt(lista.get(i+1));
			oszlop = Integer.parseInt(lista.get(i+2));
			tartalom = lista.get(i+3);
			
			betöltCellaAdatforrásból(sor, oszlop, tartalom, tipus);
		}
	}
	
	
	
	/**
	 * Átalakítja a kezelt {@code Tabla}-t {@code String}-eket tartalmazó listává.
	 * @return az átalakított elemek listája
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 * 
	 */
	public List<String> leképezTábla() throws SorszamHiba, OszlopszamHiba{
		List<String> lista = new ArrayList<>();
		
		Cella c;
		
		int hossz = lekérTáblaMéret();
		
		for(int i = 1; i <= hossz; i++){
			for(int j = 1; j <= hossz; j++){
				c = lekérCella(i, j);
				
				lista.add(c.getCellaTipus().toString());
				lista.add(String.valueOf(c.getSorszám()));
				lista.add(String.valueOf(c.getOszlopszám()));
				lista.add(c.getTartalom());
			}
		}
		
		return lista;
	}
	

	/**
	 * Adott {@code Cella} lekérdezése a {@code Tabla}-ból 
	 * @param sor a cella sorszáma
	 * @param oszlop a cella oszlopszáma
	 * @return a tábla {@code sor} sorának {@code oszlop} oszlopában található cellája
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 */
	private Cella lekérCella(int sor, int oszlop) throws SorszamHiba, OszlopszamHiba {
		int max = tábla.getTáblaMéret();
		CellaEllenor.ellenőrizSorszám(sor, max);
		CellaEllenor.ellenőrizOszlopSzám(oszlop, max);

		return tábla.getCella(sor, oszlop);
	}
	
	
	
	
	/**
	 * Lekérdezi a paraméterek által meghatározott cella adatait és átalaktíja {@code String}-ek listájává.
	 * <p>A lekérdezett adatok sorrendben a következők:
	 * <br> - a cella típusa 
	 * <br> - a cella sorszáma 
	 * <br> - a cella oszlopszáma 
	 * <br> - a cella tartalma
	 * @param sor a lekérdezendő cella sorszáma  
	 * @param oszlop a lekérdezendő cella oszlopszáma
	 * @return az átalakított adatok listája
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 * 
	 * @see hu.unideb.inf.beadando.modell.Cella
	 * @see hu.unideb.inf.beadando.modell.CellaTipus
	 */
	public List<String> lekérCellaAdatok(int sor, int oszlop) throws SorszamHiba, OszlopszamHiba{
		
		List<String> cellaAdat = new ArrayList<>();
		
		Cella cella = lekérCella(sor, oszlop);
		
		cellaAdat.add(cella.getCellaTipus().toString());
		cellaAdat.add(String.valueOf(sor));
		cellaAdat.add(String.valueOf(oszlop));
		cellaAdat.add(cella.getTartalom());
		
		return cellaAdat;
	}
	

	/**
	 * Megadja, hogy a tábla teljesen ki van e töltve.
	 * @return {@code true} ha nincs a táblában egy üres cellla sem, egyébként {@code false}
	 */
	public boolean készATábla() {

		return kitöltendőCellákSzámaATáblában() == 0 && tábla.getTáblaÁllapot() == TablaAllapot.MEGOLDOTT;
	}
	
	
	/**
	 * Lekérdezi mennyi cellát kell még kitölteni a táblában.
	 * @return az üres cellák száma a táblában
	 */
	public int kitöltendőCellákSzámaATáblában() {
		
		int cellaszám = 0;
		
		for(int i = 0; i < tábla.getTáblaMéret(); i++){
			cellaszám += üresCellákMennyiségeSzámonként().get(i);
		}
		
		return cellaszám;
	}
	
	
	/**
	 * Lekérdezi, hogy az adott {@code Cella} esetén milyen értékek közül választhatunk.
	 * @param sorszám a lekérdezendő cella sorszáma
	 * @param oszlopszám a lekérdezendő cella oszlopszáma
	 * @return a cellába beírható értékek listája
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 */
	public List<Integer> cellábaÍrhatóSzámjegyek(int sorszám, int oszlopszám) throws SorszamHiba, OszlopszamHiba{
		
		Cella cella = lekérCella(sorszám, oszlopszám);
		
		if(cella.getCellaTipus() != CellaTipus.ÜRES)
			return new ArrayList<>();
		
		List<Integer> lista = new ArrayList<>();
		
		lista = IntStream
				 .rangeClosed(1, lekérTáblaMéret())
				 .boxed()
				 .collect(Collectors.toList());
		lista.removeAll(kapcsolatbanÁllóCellák(sorszám, oszlopszám)
						.stream()
						.map(t -> tábla.getCella(t[0], t[1]))
						.filter(cell -> !cell.getTartalom().equals(""))
						.map(cell -> Integer.parseInt(cell.getTartalom()))
						.distinct().collect(Collectors.toList()));
		
		return lista;
	}
	
	
	/**
	 * Megvizsgálja, hogy mely cellák tartalmazzák ugyanazt az értéket.
	 * @param érték a keresendő érték
	 * @return a cellák sorszámát és oszlopszámát tartalmazó lista
	 */
	public List<int[]> azonosÉrtéketTartalmazóCellák(String érték) {
		
		if(érték.equals("")){
			return new ArrayList<>();
		}

		List<int[]> cellaLista = new ArrayList<int[]>();

		int hossz = tábla.getTáblaMéret();

		for (int sor = 1; sor <= hossz; sor++) {
			for (int oszlop = 1; oszlop <= hossz; oszlop++) {
				if (tábla.getCella(sor, oszlop).getTartalom().equals(érték)) {
					cellaLista.add(new int[]{sor, oszlop});
				}
			}
		}

		return cellaLista;
	}
	
	
	/**
	 * Kiszámolja minden beírható számhoz, hogy mennyi darabot lehet még belőlük a táblába írni.
	 * @return a számokat és beírható darabszámukat tartalmazó lista
	 */
	public List<Integer> üresCellákMennyiségeSzámonként() {
		int maximálisDarabszám = tábla.getTáblaMéret();
		List<Integer> számLista = new ArrayList<>(maximálisDarabszám);

		for (int i = 0; i < maximálisDarabszám; i++) {
			számLista.add(i, maximálisDarabszám - azonosÉrtéketTartalmazóCellák(String.valueOf(i + 1)).size());
		}

		return számLista;
	}
	
	
	/**
	 * Lekérdezi azokat a cellákat, amelyek a paraméterek által meghatározottal kapcsolatban állnak.
	 * <p>Ez azt jelenti, hogy a metódus visszaad minden olyan cellát,
	 * <br> amely az aktuálissal azonos sorban, oszlopban vagy rekeszben található.
	 * @param sor a cella sorszáma 
	 * @param oszlop a cella oszlopszáma
	 * @return a kapcsolódó cellák sor-és oszlopszámait tartalmazó kollekció
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
	 */
	public Set<int[]> kapcsolatbanÁllóCellák(int sor, int oszlop) throws SorszamHiba, OszlopszamHiba {

		Set<Cella> kapcsolódóCellák = new HashSet<>();

		kapcsolódóCellák.addAll(teljesSor(sor));
		kapcsolódóCellák.addAll(teljesOszlop(oszlop));
		kapcsolódóCellák.addAll(teljesRekesz(sor, oszlop));
		kapcsolódóCellák.remove(lekérCella(sor, oszlop));
		
		
		return kapcsolódóCellák.stream().map(c -> new int[]{c.getSorszám(), c.getOszlopszám()}).collect(Collectors.toSet());
	}
	

	/**
	 * Kitölt egy meghatározott cellát egy meghatározott érékkel.
	 * 
	 * @param sorszám a kitöltendő cella sorszáma
	 * @param oszlopszám a kitöltendő cella oszlopszáma
	 * @param érték a kitöltendő cellába írandó tartalom
	 * @throws CellaTartalomHiba ha  atartalom nem megfelelő
	 * @throws CellaTipusValtoztatasiHiba ha a típus nem módosítható
	 * @throws AzonosErtekASorbanHiba ha már van azonos értéket tartalmazó cella a sorban
	 * @throws AzonosErtekAzOszlopbanHiba ha már van azonos értéket tartalmazó cella az oszlopban
	 * @throws AzonosErtekARekeszbenHiba ha már van azonos értéket tartalmazó cella a rekeszben
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
 	 */
	public void kitöltCella(int sorszám, int oszlopszám, String érték) throws CellaTartalomHiba, CellaTipusValtoztatasiHiba,
			AzonosErtekASorbanHiba, AzonosErtekAzOszlopbanHiba, AzonosErtekARekeszbenHiba, SorszamHiba, OszlopszamHiba {

		logger.debug("Cella kitöltésének megkísérlése...");
		
		logger.debug("  ");
		Cella aktuálisCella = lekérCella(sorszám, oszlopszám);
		
		if(érték.equals("")){
			törölCella(aktuálisCella);
			return;
		}
		
		if(aktuálisCella.getTartalom().equals(érték)) return;
		
		
		CellaEllenor.ellenőrizCella(aktuálisCella, érték, CellaTipus.KITÖLTÖTT, tábla.getTáblaMéret());
		

		if (vanAzonosÉrtékASorban(sorszám, érték))
			throw new AzonosErtekASorbanHiba(sorszám, érték);

		if (vanAzonosÉrtékAzOszlopban(oszlopszám, érték))
			throw new AzonosErtekAzOszlopbanHiba(oszlopszám, érték);

		if (vanAzonosÉrtékARekeszben(sorszám, oszlopszám, érték))
			throw new AzonosErtekARekeszbenHiba(sorszám, oszlopszám, érték);

		aktuálisCella.setCellaTipus(CellaTipus.KITÖLTÖTT);
		aktuálisCella.setTartalom(érték);
		
		if (kitöltendőCellákSzámaATáblában() == 0) {
			tábla.setTáblaÁllapot(TablaAllapot.MEGOLDOTT);
		}
	}
	

	/**
	 * Megad egy meghatározott cellát egy meghatározott érékkel.
	 * <p>Ezt használja a program a kezdőtábla betöltéséhez. 
	 * 
	 * @param sorszám a kitöltendő cella sorszáma
	 * @param oszlopszám a kitöltendő cella oszlopszáma
	 * @param tartalom a kitöltendő cellába írandó tartalom
	 * @throws CellaTartalomHiba ha  atartalom nem megfelelő
	 * @throws CellaTipusValtoztatasiHiba ha a típus nem módosítható
	 * @throws AzonosErtekASorbanHiba ha már van azonos értéket tartalmazó cella a sorban
	 * @throws AzonosErtekAzOszlopbanHiba ha már van azonos értéket tartalmazó cella az oszlopban
	 * @throws AzonosErtekARekeszbenHiba ha már van azonos értéket tartalmazó cella a rekeszben
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
 	 */
	public void megadCella(int sorszám, int oszlopszám, String tartalom) throws CellaTartalomHiba, CellaTipusValtoztatasiHiba,
	AzonosErtekASorbanHiba, AzonosErtekAzOszlopbanHiba, AzonosErtekARekeszbenHiba, SorszamHiba, OszlopszamHiba {
		
		Cella aktuálisCella = lekérCella(sorszám, oszlopszám);
				
		
		CellaEllenor.ellenőrizCella(aktuálisCella, tartalom, CellaTipus.MEGADOTT, tábla.getTáblaMéret());
		
		if (vanAzonosÉrtékASorban(sorszám, tartalom))
			throw new AzonosErtekASorbanHiba(sorszám, tartalom);

		if (vanAzonosÉrtékAzOszlopban(oszlopszám, tartalom))
			throw new AzonosErtekAzOszlopbanHiba(oszlopszám, tartalom);

		if (vanAzonosÉrtékARekeszben(sorszám, oszlopszám, tartalom))
			throw new AzonosErtekARekeszbenHiba(sorszám, oszlopszám, tartalom);
		
		aktuálisCella.setTartalom(tartalom);
		aktuálisCella.setCellaTipus(CellaTipus.MEGADOTT);
	}
	

	/**
	 * Külső adtforrásból származó adatok alapján kitölt egy meghatározott cellát.
	 * 
	 * @param sorszám a kitöltendő cella sorszáma
	 * @param oszlopszám a kitöltendő cella oszlopszáma
	 * @param tartalom a kitöltendő cellába írandó tartalom
	 * @param tipus a cella típusa
	 * @throws CellaTartalomHiba ha  atartalom nem megfelelő
	 * @throws CellaTipusValtoztatasiHiba ha a típus nem módosítható
	 * @throws AzonosErtekASorbanHiba ha már van azonos értéket tartalmazó cella a sorban
	 * @throws AzonosErtekAzOszlopbanHiba ha már van azonos értéket tartalmazó cella az oszlopban
	 * @throws AzonosErtekARekeszbenHiba ha már van azonos értéket tartalmazó cella a rekeszben
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
 	 */
	public void betöltCellaAdatforrásból(int sorszám, int oszlopszám, String tartalom, String tipus)
			throws CellaTartalomHiba, CellaTipusValtoztatasiHiba, AzonosErtekASorbanHiba,
			AzonosErtekAzOszlopbanHiba, AzonosErtekARekeszbenHiba, SorszamHiba, OszlopszamHiba {

		Cella cella = lekérCella(sorszám, oszlopszám);
		
		CellaEllenor.ellenőrizCella(cella, tartalom, CellaTipus.valueOf(tipus), tábla.getTáblaMéret());

		if (tipus.equalsIgnoreCase("megadott"))
			megadCella(sorszám, oszlopszám, tartalom);
		else if (tipus.equalsIgnoreCase("kitöltött"))
			kitöltCella(sorszám, oszlopszám, tartalom);
		else
			törölCella(cella);
	}
	
	/**
	 * Kitölt egy meghatározott cellát üres érékkel, ha engedélyezett a tartalom változtatása.
	 * 
	 * @param cella a kitöltendő cella
	 * @throws CellaTartalomHiba ha  atartalom nem megfelelő
	 * @throws CellaTipusValtoztatasiHiba ha a típus nem módosítható
	 * @throws SorszamHiba ha a sorszám nem megfelelő
	 * @throws OszlopszamHiba ha az oszlopszám nem megfelelő
 	 */
	private void törölCella(Cella cella) throws CellaTartalomHiba, CellaTipusValtoztatasiHiba, SorszamHiba, OszlopszamHiba {
		
		CellaEllenor.ellenőrizCella(cella, "", CellaTipus.ÜRES, tábla.getTáblaMéret());
		cella.setTartalom("");
	}
	

	/**
	 * újra kezdi a játékot, azaz kitöröl minden nem {@code CellaTipus.MEGADOTT} típusú cellát.
	 */
	public void újrakezd() {

		int táblaméret = tábla.getTáblaMéret();

		for (int sor = 1; sor <= táblaméret; sor++) {
			for (int oszlop = 1; oszlop <= táblaméret; oszlop++) {
				if (tábla.getCella(sor, oszlop).getCellaTipus() != CellaTipus.MEGADOTT)
					tábla.getCella(sor, oszlop).setTartalom("");
			}
		}
	}
	
	
	
	/**
	 * Lekérdezi, hogy a {@code sorszám} által megadott sorban van e {@code újÉrték}kel megegyező érték.
	 * @param sorszám a sor száma
	 * @param újÉrték a keresendő érték
	 * @return {@code true}, ha van a sorban azonos tartalmú cella, egyébként {@code false}
	 */
	private boolean vanAzonosÉrtékASorban(int sorszám, String újÉrték) {

		return lekérTartalom(teljesSor(sorszám)).contains(újÉrték);
	}

	/**
	 * Lekérdezi, hogy a {@code oszlopszám} által megadott oszlopban van e {@code újÉrték}kel megegyező érték.
	 * @param oszlopszám az oszlop száma
	 * @param újÉrték a keresendő érték
	 * @return {@code true}, ha van az oszlopban azonos tartalmú cella, egyébként {@code false}
	 */
	private boolean vanAzonosÉrtékAzOszlopban(int oszlopszám, String újÉrték) {

		return lekérTartalom(teljesOszlop(oszlopszám)).contains(újÉrték);
	}

	/**
	 * Lekérdezi, hogy a @{@code sorszám} és a {@code oszlopszám} által megadott oszlopban
	 * <br>található e {@code újÉrték}kel megegyező érték.
	 * @param sorszám a sor száma
	 * @param oszlopszám az oszlop száma
	 * @param újÉrték a keresendő érték
	 * @return {@code true}, ha van a rekeszben azonos tartalmú cella, egyébként {@code false}
	 */
	private boolean vanAzonosÉrtékARekeszben(int sorszám, int oszlopszám, String újÉrték) {

		return lekérTartalom(teljesRekesz(sorszám, oszlopszám)).contains(újÉrték);
	}

	/**
	 * Átalakítja a paraméterként megadott cellalistát a cellák tartalmát hordozó listává. 
	 * @param lista a feldolgozandó cellák listája
	 * @return a cellák tartalmának listája
	 */
	private List<String> lekérTartalom(List<Cella> lista) {
		return lista.stream().map(cella -> cella.getTartalom()).collect(Collectors.toList());
	}

	/**
	 * Visszaad egy cellához tartozó teljes sort a táblából.
	 * @param sor a lekérdezett sor száma
	 * @return a sorban található cellák listája
	 */
	private List<Cella> teljesSor(int sor) {
		List<Cella> sorLista = new ArrayList<>();

		int hossz = tábla.getTáblaMéret();

		for (int oszlop = 1; oszlop <= hossz; oszlop++) {
			sorLista.add(tábla.getCella(sor, oszlop));
		}

		return sorLista;
	}

	/**
	 * Visszaad egy cellához tartozó teljes oszloot a táblából.
	 * @param oszlop a lekérdezett oszlop száma
	 * @return az oszlopban található cellák listája
	 */
	private List<Cella> teljesOszlop(int oszlop) {
		List<Cella> oszlopLista = new ArrayList<>();

		int hossz = tábla.getTáblaMéret();

		for (int sor = 1; sor <= hossz; sor++) {
			oszlopLista.add(tábla.getCella(sor, oszlop));
		}

		return oszlopLista;
	}

	/**
	 * Visszaad egy cellához tartozó teljes rekeszt a táblából.
	 * @param sor a cella sorszáma 
	 * @param oszlop a cella oszlopszáma
	 * @return a rekeszben található cellák listája
	 */
	private List<Cella> teljesRekesz(int sor, int oszlop) {
		List<Cella> rekesz = new ArrayList<>();

		int maxSorIndex = maxIndex(sor);
		int maxOszlopIndex = maxIndex(oszlop);
		int minSorIndex = minIndex(sor);
		int minOszlopIndex = minIndex(oszlop);

		for (int sorIndex = minSorIndex; sorIndex <= maxSorIndex; sorIndex++) {
			for (int oszlopIndex = minOszlopIndex; oszlopIndex <= maxOszlopIndex; oszlopIndex++) {
				rekesz.add(tábla.getCella(sorIndex, oszlopIndex));
			}
		}

		return rekesz;
	}

	/**
	 * Kiszámolja a paraméterhez tartozó legnagyobb indexet.
	 * @param index ehhez keressük amegfelelő legnagyobb indexet
	 * @return a legnagyobb index
	 */
	private int maxIndex(int index) {

		int tartományszám = (int) Math.sqrt(tábla.getTáblaMéret());

		for (int i = 1; i <= tartományszám; i++) {

			if (index <= i * tartományszám) {
				return i * tartományszám;
			}
		}

		return 0;
	}

	/**
	 * Kiszámolja a paraméterhez tartozó legkisebb indexet.
	 * @param index ehhez keressük amegfelelő legkisebb indexet
	 * @return a legkisebb index
	 */
	private int minIndex(int index) {

		int tartományszám = (int) Math.sqrt(tábla.getTáblaMéret());

		for (int i = 0; i < tartományszám; i++) {

			if (index <= (i + 1) * tartományszám) {
				return i * tartományszám + 1;
			}

		}

		return 0;
	}

	
	/**
	 * Kiírja a standard kimenetre a teljes Sudoku táblát. 
	 */
	public void kiírTábla() {

		int hossz = tábla.getTáblaMéret();

		System.out.println("A tábla: ");
		System.out.print("-");
		for (int i = 0; i < hossz; i++)
			System.out.print("----");
		System.out.println();
		for (int sor = 1; sor <= hossz; sor++) {
			System.out.print("| ");
			for (int oszlop = 1; oszlop <= hossz; oszlop++) {
				if (tábla.getCella(sor, oszlop).getTartalom().equals(""))
					System.out.print("  | ");
				else
					System.out.print(tábla.getCella(sor, oszlop).getTartalom() + " | ");
			}
			System.out.println();
			System.out.print("-");
			for (int i = 0; i < hossz; i++)
				System.out.print("----");
			System.out.println();
		}
	}



	/**
	 * Kiírja a standard kimenetre a cellák típusát.
	 */
	public void kiírCellákTípusa() {

		System.out.println("\n\n");

		int hossz = tábla.getTáblaMéret();

		for (int sor = 1; sor <= hossz; sor++) {
			for (int oszlop = 1; oszlop <= hossz; oszlop++) {
				System.out.printf("%-10s  ", tábla.getCella(sor, oszlop).getCellaTipus());
			}
			System.out.println();
		}
	}

	
}
