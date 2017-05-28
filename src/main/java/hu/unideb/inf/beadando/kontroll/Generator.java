package hu.unideb.inf.beadando.kontroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import hu.unideb.inf.beadando.hiba.AzonosErtekARekeszbenHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekASorbanHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekAzOszlopbanHiba;
import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.modell.CellaTipus;

/**
 * A tábla generálásáért felelős osztály.
 * 
 * @author Balogh Ádám
 *
 */
public class Generator {

	/**
	 * A tábla kezelését végző {@link TablaVezerlo} osztály egy példánya.
	 */
	private TablaVezerlo táblavezérlő;

	/**
	 * Legfeljebb ennyiszer próbálkozik a program sikertelen táblagenerálás esetén.
	 */
	private int tűréshatár = 150;

	/**
	 * A generálás során lementett cellák információit tartalmazó lista.
	 */
	private List<String> mentés = new ArrayList<>();

	/**
	 * A cellákba beírható értékek listáját tartalmazó lista.
	 */
	List<List<Integer>> lehetségesÉrtékek = new ArrayList<List<Integer>>();

	/**
	 * A táblagenerálás próbálkozásainak száma.
	 */
	private int számláló = 0;

	/**
	 * Megadja, hogy a generált tábla tartalmaz -e hibás cellákat.
	 */
	private boolean hibás = false;

	/**
	 * A generálandó tábla mérete.
	 */
	private int méret = 0;

	/**
	 * Elkéri a {@link TablaVezerlo} aktuális példányát.
	 * @return a {@code TablaVezerlo} példánya
	 */
	public TablaVezerlo lekérTáblavazérlő() {
		return táblavezérlő;
	}

	/**
	 * A tábla generálását koordináló metódus.
	 * @param t az aktuális {@link TablaVezerlo}
	 */
	public void generálTábla(TablaVezerlo t) {
		
		táblavezérlő = t;

		int táblaMéret = t.lekérTáblaMéret();
		
		try {
			TablaEllenor.ellenőrizTáblaMéret(táblaMéret);
			táblavezérlő.létrehozMegadottMéretűTábla(táblaMéret);
		} catch (TablaMeretHiba e) {
			//System.out.println(e.getMessage());
		}

		méret = táblaMéret;

		while (!táblavezérlő.készATábla()) {
			mag();

		}
		
		kitöröl();
		
		konvertál();
		
	}
	
	
	/**
	 * 
	 */
	private void konvertál(){
		
		String régiÉrték = "";
		
		for(int sor = 1; sor <= méret; sor++){
			
			for(int oszlop = 1; oszlop <= méret; oszlop++){
				
				List<String> aktuálisCella = null;
				try {
					 aktuálisCella = táblavezérlő.lekérCellaAdatok(sor, oszlop);
				} catch (SorszamHiba | OszlopszamHiba e) {
				}
				
				if(aktuálisCella.get(0).equalsIgnoreCase("kitöltött")){
					
					régiÉrték = aktuálisCella.get(3);
					
					try {
						táblavezérlő.kitöltCella(sor, oszlop, "");
					} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
							| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
					}
					
					try {
						táblavezérlő.megadCella(sor, oszlop, régiÉrték);
					} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
							| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
					}
					
				}
			}
			
		}
		
	}
	

	/**
	 * A metódus a generált táblából véletlenszerűen töröl ki értékeket.
	 */
	private void kitöröl() {

		int véletlen = 0;

		for (int sor = 1; sor <= méret; sor++) {

			for (int oszlop = 1; oszlop <= méret; oszlop++) {

				véletlen = new Random().nextInt(199 * 11) + 23;

				if (véletlen % 2 == 0) {
					try {
						táblavezérlő.kitöltCella(sor, oszlop, "");
					} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
							| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
					}
				}

			}

		}

	}
	

	/**
	 * A táblagenerálás lényegi részét végző metódus. 
	 */
	private void mag() {

		számláló = 0;

		táblavezérlő.újrakezd();

		//System.out.println("indul");

		try {
			táblavezérlő.megadCella((int) (Math.random() * méret + 1), (int) (Math.random() * méret + 1),
					String.valueOf((int) (Math.random() * méret + 1)));
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e1) {
		}

		lementÁllapot();

		while (!táblavezérlő.készATábla()) {

			if (számláló > tűréshatár) {
				break;
			}

			hibás = false;
			int darabszám = -1;

			try {
				darabszám = legkevesebbBeírhatóDarab();
			} catch (SorszamHiba | OszlopszamHiba e4) {
			}
			List<int[]> cellalista = null;
			try {
				cellalista = legkevesebbBeírhatóDarabszámúCella();
			} catch (SorszamHiba | OszlopszamHiba e3) {
			}

			// biztosakat beír
			if (darabszám == 1) {

				for (int i = 0; i < cellalista.size(); i++) {

					int[] cella = cellalista.get(i);
					int s = cella[0];
					int o = cella[1];

					try {
						if (táblavezérlő.cellábaÍrhatóSzámjegyek(s, o).isEmpty()) {
							if (!táblavezérlő.készATábla()) {
								visszatöltÁllapot();
							}
							hibás = true;
							break;
						}
					} catch (SorszamHiba | OszlopszamHiba e2) {
					}

					int számjegy = -1;
					try {
						számjegy = táblavezérlő.cellábaÍrhatóSzámjegyek(s, o).get(0);
					} catch (SorszamHiba | OszlopszamHiba e1) {
					}

					try {
						táblavezérlő.kitöltCella(cella[0], cella[1], String.valueOf(számjegy));
					} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
							| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
						hibás = true;
						visszatöltÁllapot();
						break;
					}

				}

				if (!hibás) {
					lementÁllapot();
				}

			} else {
				// tippel
				hibás = false;
				try {
					cellalista = legkevesebbBeírhatóDarabszámúCella();
				} catch (SorszamHiba | OszlopszamHiba e2) {
				}

				if (cellalista.isEmpty()) {
					if (!táblavezérlő.készATábla()) {
						visszatöltÁllapot();
					}
					hibás = true;
					break;
				}
				int db = cellalista.size();

				int[] tipp = cellalista.get((int) (Math.random() * db));
				int s = tipp[0];
				int o = tipp[1];
				int max = -1;
				try {
					max = táblavezérlő.cellábaÍrhatóSzámjegyek(s, o).size();
				} catch (SorszamHiba | OszlopszamHiba e1) {
				}

				int számjegy = -1;
				try {
					számjegy = táblavezérlő.cellábaÍrhatóSzámjegyek(s, o).get((int) (Math.random() * max));
				} catch (SorszamHiba | OszlopszamHiba e1){
				}

				try {
					táblavezérlő.kitöltCella(tipp[0], tipp[1], String.valueOf(számjegy));
				} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
						| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
					visszatöltÁllapot();
					hibás = true;
				}

			}

			számláló++;
			//System.out.println(számláló);

		}
	}

	
	/**
	 * Megadja, hogy mennyi a táblában a cellákba beírható értékek számának minimuma.
	 * @return a beírható értékek legkisebb darabszáma
	 * @throws SorszamHiba ha hibás a vizsgált cella sorszáma
	 * @throws OszlopszamHiba ha hibás a vizsgált cella oszlopszáma
	 */
	private int legkevesebbBeírhatóDarab() throws SorszamHiba, OszlopszamHiba {

		int méret = táblavezérlő.lekérTáblaMéret();
		int legkevesebbDarab = méret;
		int aktuálisÉrtékszám = méret;

		for (int sor = 1; sor <= méret; sor++) {

			for (int oszlop = 1; oszlop <= méret; oszlop++) {

				if (!táblavezérlő.lekérCellaAdatok(sor, oszlop).get(0).equals(CellaTipus.KITÖLTÖTT.toString()))
					aktuálisÉrtékszám = táblavezérlő.cellábaÍrhatóSzámjegyek(sor, oszlop).size();

				if (aktuálisÉrtékszám == 0)
					continue;

				if (legkevesebbDarab > aktuálisÉrtékszám) {
					legkevesebbDarab = aktuálisÉrtékszám;
				}
			}

		}

		return legkevesebbDarab;

	}
	

	/**
	 * Megadja, hogy mely cellákba kerülhet a legkevesebb darabszámú érték.
	 * @return a cellák listája (egy cella a sor- és oszlopszámát tartalmazó kételemű tömbbel van megadva)
	 * @throws SorszamHiba ha a vizsgált cella sorszáma hibás
	 * @throws OszlopszamHiba ha a vizsgált cella oszlopszáma hibás
	 */
	private List<int[]> legkevesebbBeírhatóDarabszámúCella() throws SorszamHiba, OszlopszamHiba {

		List<int[]> lista = new ArrayList<>();

		int méret = táblavezérlő.lekérTáblaMéret();
		int legkevesebbDarab = legkevesebbBeírhatóDarab();
		int aktuálisÉrtékszám = méret;

		for (int sor = 1; sor <= méret; sor++) {

			for (int oszlop = 1; oszlop <= méret; oszlop++) {
				aktuálisÉrtékszám = táblavezérlő.cellábaÍrhatóSzámjegyek(sor, oszlop).size();

				if (legkevesebbDarab == aktuálisÉrtékszám) {
					lista.add(new int[] { sor, oszlop });
				}
			}

		}

		return lista;
	}

	/**
	 * Lementi a generálás aktuális állapotát.
	 */
	private void lementÁllapot() {

		//System.out.println("mentés...");

		mentés.clear();

		try {
			mentés = táblavezérlő.leképezTábla();
		} catch (SorszamHiba | OszlopszamHiba e) {

		}

		//táblavezérlő.kiírTábla();

	}

	/**
	 * Visszatölti az utolsó mentett állapotot.
	 */
	private void visszatöltÁllapot() {

		//System.out.println("visszatöltés");

		táblavezérlő.újrakezd();

		try {
			táblavezérlő.feldolgoz(mentés);
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {

		}
		számláló++;
		//táblavezérlő.kiírTábla();
	}

}
