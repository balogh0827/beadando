package hu.unideb.inf.beadando.kontroll;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.beadando.adatkezeles.*;
import hu.unideb.inf.beadando.hiba.*;
import hu.unideb.inf.beadando.modell.*;


/**
 * A játék irányításáért felelős osztály.
 * @author Balogh Ádám
 *
 */
public class JatekVezerlo{

	/**
	 * A naplózást végző logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(JatekVezerlo.class);
	
	
	/**
	 *  Az aktuálisan játszott {@code Jatek} példány.
	 */
	private Jatek aktuálisJáték;
	
	
	/**
	 *  Az eltelt idő számlálását végző belső osztály.
	 */
	private Stopper stopper;
	
	
	/**
	 *  A  játék feladását jelzi, ha az értéke igaz.
	 */
	private boolean feladta;
	
	
	/**
	 * A játék befejeződését jelzi ha az értéke igaz.
	 */
	private boolean befejezte;
	
	/**
	 *  Megadja, hogy fut e még a játék.
	 */
	private boolean fut;
	
	/**
	 *  A táblával kapcsolatos műveleteket végzi és figyeli.
	 *  @see TablaVezerlo
	 */
	private TablaVezerlo táblavezérlő;
	
	
	/**
	 * Korábbi játékállapot beolvasásakor itt tárolódik a feladvánnyal eddig etöltött idő. 
	 */
	private long betöltöttIdő;
	
	
	/**
	 * A játémenet eredményének tárolására szolgál.
	 */
	private Eredmenyek eredmények;
	
	/**
	 *  Adatkezelésért felelős {@code Interface}.
	 *  @see hu.unideb.inf.beadando.adatkezeles.AdatkezeloInterface
	 */
	private AdatkezeloInterface adatkezelő;	
	
	
	/**
	 * Megmondja, hogy a játékot betölteni vagy generálni kell -e. 
	 */
	private boolean betölt;
	
	/**
	 * A tábla generálásáért felelős {@link Generator} osztály egy példánya.
	 */
	private Generator generátor;
	
	/**
	 * Új {@code JatekVezerlo} létrehozását végző paraméter nélküli konstruktor.
	 */
	public JatekVezerlo(){
		táblavezérlő = new TablaVezerlo();
		generátor = new Generator();
	}

	/**
	 * Beállítja a {@link JatekVezerlo#betölt} flag-et igaz értékre.
	 */
	public void setBetölt(){
		betölt  = true;
	}
	
	
	/**
	 * Lekérdezi a {@link JatekVezerlo#betölt} állapotát. 
	 * @return true ha betölteni, false ha generálni kell a játékot
	 */
	public boolean getBetölt(){
		return betölt;
	}
	
	/**
	 * @param táblaméret a {@code Tabla} beállítandó mérete
	 * @throws TablaMeretHiba amennyiben nem megfelelő a {@code táblaméret} paraméter értéke
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	public void létrehozJátéktábla(int táblaméret) throws TablaMeretHiba{
		
		táblavezérlő.létrehozMegadottMéretűTábla(táblaméret);
		
		if(!getBetölt()){
			logger.info(táblaméret + "x" + táblaméret + " méretű játéktábla generálása.");
			generátor.generálTábla(táblavezérlő);
		}else{
			logger.info("Táblagenerálás kihagyva.");
		}
	}
	
	
	/**
	 * Segítségével lekérdezhető a kapcsolódott {@link TablaVezerlo}.
	 * @return az osztályhoz tartozó {@code TablaVezerlo} példány
	 */
	public TablaVezerlo lekérTáblaVezérlő(){
		
		return táblavezérlő;
	}
	
	
	/**
	 * Megvizsgálja, hogy az aktuális játékpéldány kapott -e értéket.
	 * @return true ha az aktuális játék {@code null}, egyébként {@code false}
	 */
	public boolean getAktuálisJátékIsNull(){
		return aktuálisJáték == null;
	}
	
	/**
	 * Megvizsgálja, hogy a stopper {@code null} értékű vagy sem.
	 * @return true ha a stopper @{code null}, egyébként @{code false}
	 */
	public boolean getStopperIsNull(){
		return stopper == null;
	}
	
	
	/**
	 * Alapértelmezett beállításokat végez, előkészíti a játék indulását.
	 */
	private void inicializál() {
		aktuálisJáték = new Jatek();
		beállítJátékosNév("");
		stopper = new Stopper();
		fut = false;
		feladta = false;
		befejezte = false;
		betöltöttIdő = 0;
		logger.debug("Az inicializálás megtörtént.");
	}
	
	
	/**
	 * Átállítja a {@code Jatekos} nevét a paraméterre.
	 * @param név a játékos új neve
	 * @see hu.unideb.inf.beadando.modell.Jatekos#setNév(String)
	 */
	public void beállítJátékosNév(String név){
		if(név.equals(""))
			név = "1. játékos";
		aktuálisJáték.setJátékosNév(név);
		logger.debug("A játékos neve beállításra került a " + név + " értékre.");
	}
	
	
	/**
	 * Lekérdezi a {@code Jatekos} nevét. 
	 * @return a játékos neve
	 * @see hu.unideb.inf.beadando.modell.Jatekos#getNév()
	 */
	public String lekérJátékosNév(){
		return aktuálisJáték.getJátékosNév();
	}
	
		
	/**
	 * Lekérdezi hogy a játékot feladták e vagy sem.
	 * @return <code>true</code> ha a játékot feladta a játékos, egyébként <code>false</code>
	 */
	public boolean feladott(){
		return feladta;
	}
	
	
	/**
	 * Lekérdezi hogy a játékot befejezték e vagy sem.
	 * @return <code>true</code> ha a játékot befejezte a játékos, egyébként <code>false</code>
	 */
	public boolean befejezett(){
		return befejezte;
	}
	
	/**
	 * Lekérdezi, hogy folyamatban van e még a játék.
	 * @return <code>true</code> ha még ez az aktuális játék és a játékos éppen játszik vele, máskülönben <code>false</code>.
	 */
	public boolean fut(){
		return fut;
	}
	
	
	/**
	 * Elindítja a játékot és vele együtt a {@link Stopper}-t is.
	 * 
	 * @throws JatekStatuszValtasiHiba ha nem megengedett a {@code JatekStatusz} váltása
	 * @see hu.unideb.inf.beadando.modell.JatekStatusz
	 */
	public void indít() throws JatekStatuszValtasiHiba {
			inicializál();
			JatekStatuszEllenor.érvényesítJátékStátuszVáltás(aktuálisJáték, JatekStatusz.ELKEZDETT);
			aktuálisJáték.setJátékÁllapot(JatekStatusz.ELKEZDETT);
			logger.debug("A játék állapota " + JatekStatusz.ELKEZDETT + " állapotúra módosult.");
			stopper.elindít();
			logger.debug("A stopper elindult.");
			fut = true;
			logger.info("Az aktuális játék elindítva.");
	}

	
	/**
	 * Feladja a játékot és beállítja a megfelelő értékeket, valamint megállítja a {@link Stopper}-t.
	 * 
	 * @throws JatekStatuszValtasiHiba ha nem megengedett a {@code JatekStatusz} váltása
	 * @see hu.unideb.inf.beadando.modell.JatekStatusz
	 */
	public void felad() throws JatekStatuszValtasiHiba {
		
		JatekStatuszEllenor.érvényesítJátékStátuszVáltás(aktuálisJáték, JatekStatusz.FELADOTT);
		aktuálisJáték.setJátékÁllapot(JatekStatusz.FELADOTT);
		logger.debug("A játék állapota " + JatekStatusz.FELADOTT + " állapotúra módosult.");
		fut = false;
		feladta = true;
		
		aktuálisJáték.setBefejezésIdeje(LocalDateTime.now());
		logger.debug("A játék befejezési ideje: " + aktuálisJáték.getBefejezésIdeje());
		stopper.megállít();	
		logger.debug("A stopper megállt " + stopper.getElteltIdő() + " másodperc után.");
		kiírFájlbaEredmények();
		beállítJátékosNév("");
		logger.info("A játékos feladta a játékot.");
	}

	
	/**
	 * Befejezi a játékot és beállítja a megfelelő értékeket, valamint leállítja a {@link Stopper}-t.
	 * 
	 * @throws JatekStatuszValtasiHiba ha nem megengedett a {@code JatekStatusz} váltása
	 * @see hu.unideb.inf.beadando.modell.JatekStatusz
	 */
	public void befejez() throws JatekStatuszValtasiHiba {
		
		JatekStatuszEllenor.érvényesítJátékStátuszVáltás(aktuálisJáték, JatekStatusz.BEFEJEZETT);
		aktuálisJáték.setJátékÁllapot(JatekStatusz.BEFEJEZETT);
		logger.debug("A játék állapota " + JatekStatusz.BEFEJEZETT + " állapotúra módosult.");
		fut = false;
		befejezte = true;
		aktuálisJáték.setBefejezésIdeje(LocalDateTime.now());
		logger.debug("A játék befejezési ideje: " + aktuálisJáték.getBefejezésIdeje());
		stopper.megállít();
		logger.debug("A stopper megállt " + stopper.getElteltIdő() + " másodperc után.");
		if(táblavezérlő.készATábla()){
			kiírFájlbaEredmények();
		}
		logger.info("A játékos befejezte a játékot.");
	}
	
	
	/**
	 * Előállítja a játékmappa elérési útját.
	 * @return az elérési utat tartalmazó {@code String}
	 */
	private String lekérElérésiÚt(){
		String elválasztó = System.getProperty("file.separator");
		String felhasználóimappa = System.getProperty("user.home");
		String mappa = ".sudoku";
		String út = felhasználóimappa + elválasztó + mappa + elválasztó;
		logger.debug("Játékmappa helye: " + út);
		return út;
	}
	
	
	/**
	 * A tábla mérete alapján meghatározza a kimeneti állomány nevét ahová a tábla mentése történik.
	 * <br>Majd elküldi a {@link TablaVezerlo} által előállított adatokat az adatkzelő osztálynak.
	 * 
	 * @throws IOException ha hiba történt a kimeneti fálj elérése közben
	 * @throws SorszamHiba ha nem megfelelő a tábla sorszáma
	 * @throws OszlopszamHiba ha nem megfelelő a tábla oszlopszáma
	 */
	public void táblaMentés() throws IOException, SorszamHiba, OszlopszamHiba{
		
		adatkezelő = new DOMAdatkezelo();
		String kimenetiÁllománynév = "";
		int táblaméret = táblavezérlő.lekérTáblaMéret();
		switch(táblaméret){
			case 4:
				kimenetiÁllománynév ="konnyu.xml";
				break;
			case 9:
				kimenetiÁllománynév = "normal.xml";
				break;
			case 16:
				kimenetiÁllománynév = "nehez.xml";
		}
		
		logger.debug("A tábla mentési fájljának neve: " + kimenetiÁllománynév);;

		String útvonal = lekérElérésiÚt() + kimenetiÁllománynév;		
		
		adatkezelő.kimentFájlba(táblavezérlő.leképezTábla(), útvonal);
		
	}
	
	
	/**
	 * Meghatározza a forrásállomány nevét a paraméter alapján.
	 * <br>Azután utasítja a {@link TablaVezerlo}-t az adatkezelőtől származó adatok feldolgozására.
	 * 
	 * @param minősítő a fálj nevének meghatározására szolgál
	 * @throws IOException ha hiba történt a kimeneti fálj elérése közben
	 * @throws SorszamHiba ha nem megfelelő a tábla sorszáma
	 * @throws OszlopszamHiba ha nem megfelelő a tábla oszlopszáma
	 * @throws CellaTartalomHiba ha nem megfelelő a cellába írandó érték
	 * @throws CellaTipusValtoztatasiHiba ha nem megengedett a típusváltoztatás
	 * @throws AzonosErtekASorbanHiba ha már található azonos tartalmú cella a sorban
	 * @throws AzonosErtekAzOszlopbanHiba ha már található azonos tartalmú cella az oszlopban
	 * @throws AzonosErtekARekeszbenHiba ha már található azonos tartalmú cella a rekeszben
	 */
	@SuppressWarnings("unchecked")
	public void betöltTábla(String minősítő) throws IOException, SorszamHiba, OszlopszamHiba, CellaTartalomHiba, CellaTipusValtoztatasiHiba, AzonosErtekASorbanHiba, AzonosErtekAzOszlopbanHiba, AzonosErtekARekeszbenHiba{
		
		
		adatkezelő = new DOMAdatkezelo();
		String fájlnév = minősítő + ".xml";

		String útvonal = lekérElérésiÚt() + fájlnév;
		File fájl = new File(útvonal); 
		logger.debug("A tábla betöltési fájlja: " + útvonal);
		if(fájl.exists()){	
			táblavezérlő.feldolgoz((List<String>)adatkezelő.betöltFájlból(útvonal));
		}else{
			logger.error("A fájl nem létezik: " + útvonal);
			throw new IOException();
		}
	}
	
	
	/**
	 * Betölti a legutóbb elmentett játékállást.
	 * @param minősítő a mentett játékállás fokozata
	 * @throws IOException ha valamilyen fájlműveleti hiba történik
	 */
	@SuppressWarnings("unchecked")
	public void betöltJátékállás(String minősítő) throws IOException{
		adatkezelő = new JsonAdatkezelo();

		String fájlnév = minősítő + "_adatok.json";

		String útvonal = lekérElérésiÚt() + fájlnév;
		logger.debug("A legutóbbi \"" + minősítő + "\" játékállás fájlja: " + útvonal);
		File fájl = new File(útvonal); 

		if(fájl.exists()){	
			
			List<String> adatok = (List<String>)adatkezelő.betöltFájlból(útvonal);
			
			beállítJátékosNév(adatok.get(0));
			betöltöttIdő = Long.parseLong(adatok.get(1));
			logger.debug("Az adattagok beállítása megtörént a mentési fájl alapján.");
		}else{
			logger.error("A fájl nem található: " + útvonal);
			throw new IOException();
		}
	}
	
	
	/**
	 * Fájlba menti az aktuális játék jelenlegi állását.
	 * @throws IOException ha valamilyen fájlműveleti hiba történik
	 */
	public void játékállásMentés() throws IOException{
		adatkezelő = new JsonAdatkezelo();

		String fájlnév = "";
		
		switch(táblavezérlő.lekérTáblaMéret()){
		case 4:
			fájlnév = "konnyu_adatok.json";
			break;
		case 9:
			fájlnév = "normal_adatok.json";
			break;
		case 16:
			fájlnév = "nehez_adatok.json";
		}
		
		String útvonal = lekérElérésiÚt() + fájlnév;
		logger.debug("A játékállás mentési fájlja: " + útvonal);
		adatkezelő.kimentFájlba(lekérAdatok(), útvonal);
	}
	
	
	/**
	 * Összegyűjti a játékos adatait.
	 * @return a lekérdezett adatok listája
	 */
	private List<String> lekérAdatok(){
		List<String> adatlista = new ArrayList<>();
		
		adatlista.add(lekérJátékosNév());
		adatlista.add(String.valueOf(lekérElteltIdő()));
		
		return adatlista;
	}
	
	
	/**
	 * Fájlba menti az összes játék eredményét.
	 */
	public void kiírFájlbaEredmények() {
		
		String fájlnév = lekérElérésiÚt() + "eredmenyek.xml";
		logger.debug("Az eredmények mentési fájlja: " + fájlnév);
		java.io.File f = new java.io.File(fájlnév);
		
		adatkezelő = new JAXBAdatkezelo();
		eredmények = new Eredmenyek(); 
		
		List<Eredmenyek> lista = new ArrayList<>();
		
		if(!f.exists()){
			logger.warn("Az eredménymentési fájl nem létezik.");
			try {
				lista.add(eredmények);
				adatkezelő.kimentFájlba(lista, fájlnév);
				return;
			} catch (IOException e1) {
				logger.error("Hiba történt az eredmények mentési fájljának létrehozásakor.");
			}
		}
		
		String állapot = befejezett() ? "befejezett" : "feladott";
		
		Eredmeny eredmény = new Eredmeny();

		
		List<Eredmeny> eredményekmentés =  betöltFájlbólEredmények();
		
		for(Eredmeny e : eredményekmentés){
			if(e.getJatekosnev().equals("%TEST%"))
				continue;
			eredmények.hozzáadEredmény(e);
		}
		
		int méret = táblavezérlő.lekérTáblaMéret();
		eredmény.setJatekosNev(aktuálisJáték.getJátékosNév());
		eredmény.setTablameret(méret + "x" + méret);
		eredmény.setJatekallapot(állapot);
		eredmény.setIdotartam(lekérÖsszidőStringként());
		eredmény.setDatum(aktuálisJáték.getBefejezésIdeje()
				.format(DateTimeFormatter.ofPattern("YYYY.MM.dd. HH:mm:ss"))
				.toString());
		
		eredmények.hozzáadEredmény(eredmény);
		
		
		lista.add(eredmények);
		
		try {
			adatkezelő.kimentFájlba(lista, fájlnév);
		} catch (IOException e) {
			logger.error("Hiba történt az eredmények mentési fájljának létrehozásakor.");
		}
	}
	
	
	
	/**
	 * Betölti a mentési fájlból a korábbi játékok adatait.
	 * @return a korábban lejátszott játékok eredményeinek listája
	 */
	@SuppressWarnings("unchecked")
	public List<Eredmeny> betöltFájlbólEredmények(){
		
		adatkezelő = new JAXBAdatkezelo();
		
		List<Eredmeny> lista =  new ArrayList<>();
		List<Eredmeny> tmp = new ArrayList<>();
		
		String fájl = lekérElérésiÚt() + "eredmenyek.xml";
		
		try {
			tmp = (List<Eredmeny>)adatkezelő.betöltFájlból(fájl);
			logger.debug("Az eredmények betöltése az alábbi fájlból: " + fájl);
		} catch (IOException e) {
			logger.error("Hiba az eredmények betöltésekor.");
		}
		
		for(Eredmeny e : tmp){
			if(e.getJatekosnev().equals("%TEST%")){
				continue;
			}else{
				lista.add(e);
			}
		}

		return lista;
	}
	
	
	 /**
	 * A {@link Stopper} osztálytól lekérdezi az eltelt időt.
	 * @return az eltelt idő {@code String}-ként
	 */
	public String lekérElteltidőStringként(){
		 return átalakít(stopper.getElteltIdő());
	 }
	 

	/**
	 * Lekérdezi az aktuális feladvánnyal eddig játszott időt {@code String} formában.
	 * Figyelembe veszi betöltött játék esetén a játékállás mentése előtti eltelt időt is.
	 * @return az eltelt összidő
	 */
	public String lekérÖsszidőStringként(){
		
		return átalakít(lekérElteltIdő() + betöltöttIdő);
	}
	
	 /**
	  * A {@link Stopper} osztálytól lekérdezi az eltelt időt.
	 * @return az eltelt idő nanoszekundumban
	 */
	public long lekérElteltIdő(){
		 return stopper.getElteltIdő();
	 }


	/**
	 * A nanoszekundumban tárolt, stopper által mért eltelt időt átlakítja könnyen értelmezhető formátumra.
	 * @param érték az eltelt idő
	 * @return az eltelt idő emberi fogyasztásra szánt <code>String</code> reprezentációja
	 */
	private String átalakít(long érték) {
		long óra, perc, másodperc;
		érték /= 1000000000; // másodperccé alakítás

		óra = érték / 3600;
		perc = (érték - (óra * 3600)) / 60;
		másodperc = érték % 60;

		return (óra < 10 ? "0" + óra : óra) + ":" + (perc < 10 ? "0" + perc : perc) + ":"
				+ (másodperc < 10 ? "0" + másodperc : másodperc);
	}

	
	/**
	 * Az idő számlálását külön szálon végző belső osztály.
	 * 
	 * @author Balogh Ádám
	 *@see Thread
	 */
	private class Stopper extends Thread {

		
		/**
		 *  A számlálás kezdete.
		 */
		private long kezdésiIdő;
		
		/**
		 * A {@link #kezdésiIdő} óta eltelt idő nanoszekundumban.
		 */
		private long elteltIdő;

		/**
		 * A számlálást végző metdus.
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			while (fut) {
				try {
					elteltIdő = System.nanoTime() - kezdésiIdő;
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}

		/**
		 *  Elindítja a stoppert és beállítja a {@link #kezdésiIdő} értékét a jelenlegi időpillanatra.
		 */
		public void elindít() {
			fut= true;
			kezdésiIdő = System.nanoTime();
			start();
		}

		/**
		 *  Megállítja a stoppert.
		 */
		public void megállít() {
			fut = false;
			interrupt();
		}
	
		
		/**
		 * Lekérdezi az {@link #elteltIdő}-t.
		 * @return a stopper indítása óta eltelt idő nanoszekundumban
		 */
		public long getElteltIdő(){
			return elteltIdő;
		}
		
	}
}
