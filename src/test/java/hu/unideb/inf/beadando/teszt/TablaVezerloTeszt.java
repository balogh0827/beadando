package hu.unideb.inf.beadando.teszt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hu.unideb.inf.beadando.hiba.AzonosErtekARekeszbenHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekASorbanHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekAzOszlopbanHiba;
import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.kontroll.TablaVezerlo;
import hu.unideb.inf.beadando.modell.CellaTipus;

public class TablaVezerloTeszt {
	
	private TablaVezerlo vezérlő;
	private String elvártÜzenet;
	private String aktuálisÜzenet;
	private int táblaméret = 4;

	@Before
	public void példányosít(){
		vezérlő = new TablaVezerlo();
		try {
			vezérlő.létrehozMegadottMéretűTábla(táblaméret);
		} catch (TablaMeretHiba e) {}
	}
	
	
	@Test
	public void példányosításTeszt() {
		assertNotNull(vezérlő);
	}

	
	@Test
	public void létrehozMegadottMéretűTáblaHelyesMérettelTeszt(){
		
		try {
			vezérlő.létrehozMegadottMéretűTábla(16);
			vezérlő.létrehozMegadottMéretűTábla(9);
			vezérlő.létrehozMegadottMéretűTábla(4);
		} catch (TablaMeretHiba e) {
			fail("Nem kellett volna TablaMeretHiba kivételnek kivátódnia a 9-es méretre.");
		}
		
	}
	
	
	@Test(expected = TablaMeretHiba.class)
	public void létrehozMegadottMéretűTáblaNullásMérettelTeszt() throws TablaMeretHiba{
		
		vezérlő.létrehozMegadottMéretűTábla(0);
	}
	
	
	@Test(expected = TablaMeretHiba.class)
	public void létrehozMegadottMéretűTáblaNegatívMérettelTeszt() throws TablaMeretHiba{
		
		vezérlő.létrehozMegadottMéretűTábla(-4);
	}
	
	@Test(expected = TablaMeretHiba.class)
	public void létrehozMegadottMéretűTáblaPozitívHibásMérettelTeszt() throws TablaMeretHiba{
		
		vezérlő.létrehozMegadottMéretűTábla(2);
	}
	
	
	@Test
	public void létrehozMegadottMéretűTáblaHibásMérettelÜzenetTeszt(){
		
		int méret = -2;
		
		try {
			vezérlő.létrehozMegadottMéretűTábla(méret);
			fail("TablaMeretHiba kivételnek kellett volna kiváltódnia.");
		} catch (TablaMeretHiba e) {
			elvártÜzenet = "A tábla mérete nem állítható be erre az értékre: " + méret + " !"
					+ "\nFolytatás alapértelmezett táblaméret alkalmazásával.";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Nem megfelelő a hibaüzenet.", elvártÜzenet, aktuálisÜzenet);
		}
	}
	
	
	@Test
	public void lekérTáblaMéretTeszt(){
		assertEquals(táblaméret, vezérlő.lekérTáblaMéret());
	}
	
	@Test(expected = SorszamHiba.class)
	public void lekérCellaNegatívSorszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
			vezérlő.lekérCellaAdatok(-1, 1);
		
	}
	
	@Test(expected = SorszamHiba.class)
	public void lekérCellaNullásSorszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
		vezérlő.lekérCellaAdatok(0, 1);
	}
	
	
	@Test(expected = SorszamHiba.class)
	public void lekérCellaPozitívHibásSorszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
		vezérlő.lekérCellaAdatok(10, 1);
		
	}
	
	
	@Test
	public void lekérCellaHibásSorszámÜzenetTeszt(){
	
		int sorszám = -3;
		int MAX = vezérlő.lekérTáblaMéret();
		
		try {
			vezérlő.lekérCellaAdatok(-3, 2);
			fail("SorszamHiba kivételt kellett volna eredményeznie a negatív értéknek.");
		} catch (SorszamHiba e) {
			elvártÜzenet ="Hibás sorszám [ " + sorszám + " ] került megadásra!\n"
					+ "TIPP: Csak 1 és " + MAX + " közötti értékek megengedettek.";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Nem megfelelő a hibaüzenet tartalma.", elvártÜzenet, aktuálisÜzenet);
		} catch (OszlopszamHiba e) {
			fail("Nem kellett volna OszlopszamHiba kivételnek kiváltódnia 2-re.");
		}
		
	}
	
	
	@Test
	public void lekérCellaHelyesSorszámTeszt(){
		
		try {
			vezérlő.lekérCellaAdatok(1, 1);
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail();
		}
	}
	
	
	@Test(expected = OszlopszamHiba.class)
	public void lekérCellaNullásOszlopszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
		vezérlő.lekérCellaAdatok(1, 0);
		
	}
	
	@Test(expected = OszlopszamHiba.class)
	public void lekérCellaNegatívOszlopszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
		vezérlő.lekérCellaAdatok(1, -3);
		
	}
	
	
	@Test(expected = OszlopszamHiba.class)
	public void lekérCellaPozitívHibásOszlopszámTeszt() throws SorszamHiba, OszlopszamHiba{
		
		vezérlő.lekérCellaAdatok(1, 22);
		
	}
	
	
	@Test
	public void lekérCellaHelyesOszlopszámTeszt(){
		
		try {
			vezérlő.lekérCellaAdatok(1, 3);
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna OszlopszámHiba kivételnek kiváltódnia helyes értékre.");
		}
		
	}
	
	
	@Test
	public void lekérCellaHibásoszlopszámÜzenetTeszt(){
		
		int oszlop = -7;
		int MAX = vezérlő.lekérTáblaMéret();
		
		try {
			vezérlő.lekérCellaAdatok(1, oszlop);
		} catch (SorszamHiba e) {
			fail("Nem kellett volna SorszámHiba kivételnek kiváltódnia helyes aorszám értékre.");
		} catch (OszlopszamHiba e) {
			elvártÜzenet = "Hibás oszlopszám [ " + oszlop + " ] került megadásra!\n"
					+ "TIPP: Csak 1 és " + MAX + " közötti érékek megengedettek.";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Nem megfelelő hibaüzenet.", elvártÜzenet,  aktuálisÜzenet);
		}
		
	}
	
	
	@Test
	public void készATáblaÜresTeszt(){
		assertFalse("Üres tábla nem lehet kész.", vezérlő.készATábla());
	}
	
	
	@Test
	public void cellábaÍrhatóSzámjegyekNemÜresCellaTeszt(){
		try {
			try {
				vezérlő.kitöltCella(1, 1, "3");
			} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
					| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
				fail("Nem kellett volna kivételnek lennie.");
			}
			assertTrue("Nem ÜRES cella esetén üres listát kellene kapni.", 
					vezérlő.cellábaÍrhatóSzámjegyek(1, 1).isEmpty());
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("nem kellett volna kivételnek kiváltódnia.");
		}
	}
	
	@Test
	public void cellábaÍrhatóSzámjegyekÜresCellaTeszt(){

		try {
			assertArrayEquals("Üres tábla bármely cellájába bármely megengedett érték kerülhet.",
					vezérlő.cellábaÍrhatóSzámjegyek(1, 1).toArray(new Integer[0]),
					new Integer[]{1,2,3,4});
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Kivétel váltódott ki.");
		}
		
	}
	
	
	@Test
	public void cellábaÍrhatóSzámjegyekNemÜresTáblaTeszt(){
		
		try {
			vezérlő.megadCella(1, 1, "1");
			vezérlő.megadCella(2, 2, "2");
			vezérlő.megadCella(2, 1, "3");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
		}
		
		try {
			assertArrayEquals(new Integer[]{4}, vezérlő.cellábaÍrhatóSzámjegyek(1, 2).toArray(new Integer[0]));
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia helyes sor- és oszlopszámra.");
		}
		
	}
	
	
	@Test
	public void azonosÉrtéketTartalmazóCellákÜresCellaTeszt(){
		
		assertNotNull("Nem szabad null referenciát kapni.", vezérlő.azonosÉrtéketTartalmazóCellák(""));
		assertTrue("Üres listát kellett volna visszaadnia.", vezérlő.azonosÉrtéketTartalmazóCellák("").isEmpty());
	}
	
	
	@Test
	public void kitöltCellaHibásSzámTartalommalÜzenetTeszt(){
		
		
		int sorszám = 4;
		int oszlopszám = 2;
		String érték = "5";
		
		try {
			vezérlő.kitöltCella(sorszám, oszlopszám, érték);
		} catch ( CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("CellaTartalomHiba kivételnek kellett volna kiváltódnia.");
		}catch(CellaTartalomHiba e){
			elvártÜzenet = "Hibás értéket [ " + érték + " ] próbált beírni a(z) "
					+ sorszám + ". sor "
					+ oszlopszám + ". oszlopában "
					+ "található cellába!\n"
					+ "TIPP: Csak 1 és " + vezérlő.lekérTáblaMéret() + " közötti számokat írjon a cellákba.";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Hibás a CellaTartalomHiba kivétel által előállított üzenet.", elvártÜzenet, aktuálisÜzenet);
		}
		
	}
	
	
	@Test
	public void kitöltCellaHibásSzövegesTartalommalÜzenetTeszt(){
		
		
		int sorszám = 4;
		int oszlopszám = 2;
		String érték = "A";
		
		try {
			vezérlő.kitöltCella(sorszám, oszlopszám, érték);
		} catch ( CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("CellaTartalomHiba kivételnek kellett volna kiváltódnia.");
		}catch(CellaTartalomHiba e){
			elvártÜzenet = "Hibás értéket [ " + érték + " ] próbált beírni a(z) "
					+ sorszám + ". sor "
					+ oszlopszám + ". oszlopában "
					+ "található cellába!\n"
					+ "TIPP: Csak 1 és " + vezérlő.lekérTáblaMéret() + " közötti számokat írjon a cellákba.";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Hibás a CellaTartalomHiba kivétel által előállított üzenet.", elvártÜzenet, aktuálisÜzenet);
		}
		
	}
	
	
	@Test
	public void kitöltCellaÜresStringgelTeszt(){
		
		try {
			vezérlő.kitöltCella(1, 1, "");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Üres Stringre nem szabad kivételt kapnunk.");
		}
		
		try {
			vezérlő.megadCella(1, 1, "2");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem lett volna szabad kivételnek kiváltódni.");
		}
		
		
		try {
			vezérlő.kitöltCella(1, 1, "");
			fail("Kivételt kellett volna kapni, mivel MEGADOTT típusú cellát akartunk változtatni.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
		}
		
	}
	
	
	@Test
	public void kitöltCellaAzonosÉrtékkelTeszt(){
		
		int sorszám = 3;
		int oszlopszám = 2;
		
		try {
			vezérlő.kitöltCella(1, 1, "3");
			vezérlő.kitöltCella(1, 1, "3");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Azonos értékkel történő kitöltéskor nem kellene kivételt kapni.");
		}
		
		
		
		try {
			vezérlő.kitöltCella(sorszám, oszlopszám, "2");
			vezérlő.megadCella(sorszám, oszlopszám, "2");
			fail("CellaTipusValtoztatasiHiba kivételnek kellett volna kiváltódnia.");
		} catch (CellaTartalomHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("CellaTipusValtoztatasiHiba kivételnek kellett volna kiváltódnia.");
		}catch(CellaTipusValtoztatasiHiba e){
			elvártÜzenet = "A(z) " 
					+ sorszám + ".sor "
					+  oszlopszám +  ".oszlopában "
					+ "levő cella nem módosítható!";
			aktuálisÜzenet = e.getMessage();
			String részletesÜzenet = e.getMessage() + "\nA cella tipusa " + CellaTipus.KITÖLTÖTT 
			+ ", ami nem változatható " + CellaTipus.MEGADOTT + " típusra!";
			assertEquals("Hibás CellaTipusValtoztatasiHiba üzenet generálódott.", elvártÜzenet, aktuálisÜzenet);
			assertEquals("A részletes üzenet hibás.", részletesÜzenet,  e.getRészletesHibaÜzenet());
		}
	}
	
	
	@Test
	public void kitöltCellaSorbanMárTalálhatóÉrtékkelÜzenetTeszt(){
		
		String érték = "2";
		int sorszám = 1;
		
		try {
			vezérlő.megadCella(sorszám, 4, érték);
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt kapni.");
		}
		
		try {
			vezérlő.kitöltCella(sorszám, 1, érték);
			fail("kivételt kellett volna kapni.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("AzonosErtekASorbanHiba kivételt kellett volna kapni.");
		} catch (AzonosErtekASorbanHiba e) {
			elvártÜzenet = "Már szerepel " +  érték + " a(z) "
					+ sorszám + ". sorban!";
			aktuálisÜzenet = e.getMessage();
			assertEquals("AzonosErtekASorbanHiba üzenete hibás.", aktuálisÜzenet, elvártÜzenet);
		}
		
		
	}
	
	@Test
	public void kitöltCellaOszlopbanMárTalálhatóÉrtékkelÜzenetTeszt(){
		
		String érték = "2";
		int oszlopszám = 3;
		
		try {
			vezérlő.megadCella(1, oszlopszám, érték);
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt kapni.");
		}
		
		try {
			vezérlő.kitöltCella(3, oszlopszám, érték);
			fail("kivételt kellett volna kapni.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt kapni.");
		} catch (AzonosErtekAzOszlopbanHiba e) {
			elvártÜzenet = "Már szerepel " +  érték + " a(z) "
					+ oszlopszám + ". oszlopban!";
			aktuálisÜzenet = e.getMessage();
			assertEquals("AzonosErtekAzOszlopbanHiba üzenete helytelen", elvártÜzenet, aktuálisÜzenet);
		}
		
		
	}
	
	
	
	@Test
	public void kitöltCellaRekeszbenMárTalálhatóÉrtékkelÜzenetTeszt(){
		
		String érték = "1";
		int sorszám = 2;
		int oszlopszám = 2;
		
		try {
			vezérlő.megadCella(sorszám - 1, oszlopszám - 1, érték);
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt kapni.");
		}
		
		try {
			vezérlő.kitöltCella(sorszám, oszlopszám, érték);
			fail("kivételt kellett volna kapni.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
				| AzonosErtekAzOszlopbanHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt kapni.");
		} catch (AzonosErtekARekeszbenHiba e) {
			elvártÜzenet = "Már szerepel " +  érték + " a(z) "
					+ sorszám + " .sorhoz és a(z) "
					+ oszlopszám + " .oszlophoz tartozó rekeszben!";
			aktuálisÜzenet = e.getMessage();
			assertEquals("AzonosErtekARekeszbenHiba üzenete helytelen", elvártÜzenet, aktuálisÜzenet);
		}
		
		
	}
	
	
	@Test
	public void kitöltCellaUtolsóÜresCellaHelyesKitöltéseTeszt(){
		
		try {
			vezérlő.megadCella(1, 1, "1");
			vezérlő.megadCella(1, 2, "2");
			vezérlő.megadCella(1, 3, "3");
			vezérlő.megadCella(1, 4, "4");
			vezérlő.megadCella(2, 1, "3");
			vezérlő.megadCella(2, 2, "4");
			vezérlő.megadCella(2, 3, "1");
			vezérlő.megadCella(2, 4, "2");
			vezérlő.megadCella(3, 1, "2");
			vezérlő.megadCella(3, 2, "3");
			vezérlő.megadCella(3, 3, "4");
			vezérlő.megadCella(3, 4, "1");
			vezérlő.megadCella(4, 1, "4");
			vezérlő.megadCella(4, 2, "1");
			vezérlő.megadCella(4, 3, "2");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}
		
		try {
			vezérlő.kitöltCella(4, 4, "3");
			assertEquals("Kitöltendő cellák számának nullának kellene lennie.", 0, vezérlő.kitöltendőCellákSzámaATáblában());
			assertTrue("A táblának MEGOLDOTT állapotban kellene lennie.", vezérlő.készATábla());
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}

	}
	
	
	@Test(expected = AzonosErtekASorbanHiba.class)
	public void megadCellaVanAzonosÉrtékASorbanTeszt() throws AzonosErtekASorbanHiba{
		
		String érték = "2";
		int sorszám = 1;
		
		try {
			vezérlő.megadCella(sorszám, 1, érték);
			vezérlő.megadCella(sorszám, 3, érték);
			fail("AzonosErtekASorbanHiba kivételnek kellett volna kiváltódnia.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba  | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("AzonosErtekASorbanHiba kivételnek kellett volna kiváltódnia, nem másiknak.");
		}
	}
	
	
	@Test(expected = CellaTipusValtoztatasiHiba.class)
	public void megadCellaKitöltöttCellaVáltoztatásTeszt() throws CellaTipusValtoztatasiHiba{
		
		int sorszám = 2;
		int oszlopszám = 4;
		String tartalom = "3";
		
		try {
			vezérlő.kitöltCella(sorszám, oszlopszám, tartalom);
			vezérlő.megadCella(sorszám, oszlopszám, tartalom);
			fail("CellaTipusValtoztatasiHiba kivételnek kellett volna kiváltódnia.");
		} catch (CellaTartalomHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("CellaTipusValtoztatasiHiba kivételnek kellett volna kiváltódnia.");
		}
		
	}
	
	
	@Test(expected = AzonosErtekAzOszlopbanHiba.class)
	public void megadCellaVanAzonosÉrtékAzOszlopbanTeszt() throws AzonosErtekAzOszlopbanHiba{
		
		String érték = "4";
		int oszlopszám = 3;
		
		try {
			vezérlő.megadCella(1, oszlopszám, érték);
			vezérlő.megadCella(4, oszlopszám, érték);
			fail("AzonosErtekAzOszlopbanHiba kivételnek kellett volna kiváltódnia.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekARekeszbenHiba 
				| SorszamHiba | OszlopszamHiba | AzonosErtekASorbanHiba e) {
			fail("AzonosErtekAzOszlopbanHiba kivételnek kellett volna kiváltódnia, nem másiknak.");
		}
	}
	
	
	@Test(expected = AzonosErtekARekeszbenHiba.class)
	public void megadCellaVanAzonosÉrtékARekeszbenTeszt() throws AzonosErtekARekeszbenHiba{
		
		String érték = "1";
		int sorszám = 4;
		int oszlopszám = 2;
		
		try {
			vezérlő.megadCella(sorszám - 1 , oszlopszám - 1, érték);
			vezérlő.megadCella(sorszám, oszlopszám, érték);
			fail("AzonosErtekARekeszbenHiba kivételnek kellett volna kiváltódnia.");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekAzOszlopbanHiba 
				| SorszamHiba | OszlopszamHiba | AzonosErtekASorbanHiba e) {
			fail("AzonosErtekARekeszbenHiba kivételnek kellett volna kiváltódnia, nem másiknak.");
		}
	}
	
	
	@Test
	public void betöltCellaAdatforrásbólTeszt(){
		
		try {
			vezérlő.betöltCellaAdatforrásból(1, 1, "1", "ÜRES");
			vezérlő.betöltCellaAdatforrásból(2, 2, "2", "MEGADOTT");
			vezérlő.betöltCellaAdatforrásból(3, 3, "3", "KITÖLTÖTT");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételt dobnia.");
		}
		
	}
	
	
	@Test
	public void újrakezdTest(){
		
		try {
			vezérlő.kitöltCella(1, 1, "1");
			vezérlő.megadCella(2, 2, "2");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}
		
		
		vezérlő.újrakezd();
		
		String tartalom = null;
		String tipus = null;
		
		try {
			tipus = vezérlő.lekérCellaAdatok(1, 1).get(0);
			tartalom = vezérlő.lekérCellaAdatok(1, 1).get(3);
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}
		
		
		assertTrue("A cellának üresnek kellene lennie.", tartalom.equals(""));
		assertEquals("A cellának ÜRES tipusúnak kellene lennie.", tipus, "ÜRES");
		
		try {
			tipus = vezérlő.lekérCellaAdatok(2, 2).get(0);
			tartalom = vezérlő.lekérCellaAdatok(2, 2).get(3);
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}
		
		assertTrue("A cellának nem kellene üresnek lennie.", tartalom.equals("2"));
		assertEquals("A cellának MEGADOTT tipusúnak kellene lennie.", tipus, "MEGADOTT");
	}
	
	
	@Test
	public void feldolgozTeszt(){
		
		java.util.List<String> lista = new java.util.ArrayList<>();
		lista.add("ÜRES");
		lista.add("1");
		lista.add("1");
		lista.add("");
		lista.add("MEGADOTT");
		lista.add("2");
		lista.add("2");
		lista.add("3");
		lista.add("KITÖLTÖTT");
		lista.add("4");
		lista.add("4");
		lista.add("4");
		
		try {
			vezérlő.feldolgoz(lista);
			assertEquals("MEGADOTT-nak kellene lennie.", vezérlő.lekérCellaAdatok(2, 2).get(0), "MEGADOTT");
			assertEquals("2-nek kellene lennie.", vezérlő.lekérCellaAdatok(2, 2).get(1), "2");
			assertEquals("2-nek kellene lennie.", vezérlő.lekérCellaAdatok(2, 2).get(2), "2");
			assertEquals("3-nak kellene lennie.", vezérlő.lekérCellaAdatok(2, 2).get(3), "3");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódnia.");
		}
		
	}
	
	
	@Test
	public void leképezTáblaÜresTáblaTeszt(){
		
		java.util.List<String> tábla = new java.util.ArrayList<>();
		
		int index = 0;
		
		for(int i = 1; i <= vezérlő.lekérTáblaMéret(); i++){
			for(int j = 1; j <= vezérlő.lekérTáblaMéret(); j++){
				tábla.add(index++, "ÜRES");
				tábla.add(index++, String.valueOf(i));
				tábla.add(index++, String.valueOf(j));
				tábla.add(index++, "");
			}
		}
	
		try {
			assertArrayEquals("Azonosnak kellene lennie.", vezérlő.leképezTábla().toArray(new String[0]), tábla.toArray(new String[0]));
		} catch (SorszamHiba | OszlopszamHiba e) {
			fail("Nem kellett volna kivételnek kiváltódni.");
		}
		
	}
	
		
}
