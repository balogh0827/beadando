package hu.unideb.inf.beadando.kontroll;

import java.io.IOException;
import java.time.ZonedDateTime;
import hu.unideb.inf.beadando.adatkezeles.AdatkezeloInterface;
import hu.unideb.inf.beadando.adatkezeles.XMLAdatkezelo;
import hu.unideb.inf.beadando.hiba.AzonosErtekARekeszbenHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekASorbanHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekAzOszlopbanHiba;
import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.JatekStatuszValtasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.modell.Jatek;
import hu.unideb.inf.beadando.modell.JatekStatusz;


/**
 * A játék irányításáért felelős osztály.
 * @author Balogh Ádám
 *
 */
public class JatekVezerlo{

	
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
	 *  Adatkezelésért felelős {@code Interface}.
	 *  @see hu.unideb.inf.beadando.adatkezeles.AdatkezeloInterface
	 */
	private AdatkezeloInterface adatkezelő;	
	
	/**
	 * Új {@code JatekVezerlo} létrehozását végző paraméter nélküli konstruktor.
	 */
	public JatekVezerlo(){
	
		táblavezérlő = new TablaVezerlo();
	}

	
	
	/**
	 * @param táblaméret a {@code Tabla} beállítandó mérete
	 * @throws TablaMeretHiba amennyiben nem megfelelő a {@code táblaméret} paraméter értéke
	 * @see hu.unideb.inf.beadando.modell.Tabla
	 */
	public void létrehozJátéktábla(int táblaméret) throws TablaMeretHiba{
		
		táblavezérlő.létrehozMegadottMéretűTábla(táblaméret);
	}
	
	
	/**
	 * Segítségével lekérdezhető a kapcsolódott {@link TablaVezerlo}.
	 * @return az osztályhoz tartozó {@code TablaVezerlo} példány
	 */
	public TablaVezerlo lekérTáblaVezérlő(){
		
		return táblavezérlő;
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
		stopper.elindít();
		fut = true;
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
		fut = false;
		feladta = true;
		
		aktuálisJáték.setBefejezésIdeje(ZonedDateTime.now());
		stopper.megállít();	
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
		fut = false;
		befejezte = true;
	
		aktuálisJáték.setBefejezésIdeje(ZonedDateTime.now());
		stopper.megállít();
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
		
		adatkezelő = new XMLAdatkezelo();
		
		String kimenetiÁllománynév = "";
		
		switch(táblavezérlő.lekérTáblaMéret()){
			case 4:
				kimenetiÁllománynév ="konnyu.xml";
				break;
			case 9:
				kimenetiÁllománynév = "normal.xml";
				break;
			case 16:
				kimenetiÁllománynév = "nehez.xml";
		
		}
		
		adatkezelő.kimentFájlba(táblavezérlő.leképezTábla(), kimenetiÁllománynév);
		
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
	public void betöltTábla(String minősítő) throws IOException, SorszamHiba, OszlopszamHiba, CellaTartalomHiba, CellaTipusValtoztatasiHiba, AzonosErtekASorbanHiba, AzonosErtekAzOszlopbanHiba, AzonosErtekARekeszbenHiba{
		
		System.out.println("betöltés fájlból...");
		
		
		
//		
//			if(minősítő.equals("konnyu")){
//				fájl = getClass().getResource("konnyu.xml").getFile();
//			}else if(minősítő.equals("normal")){
//				fájl = getClass().getResource("normal.xml").getFile();
//			}
		System.out.println(getClass().getResource("Fomenu.fxml") == null);
//		fájl = getClass().getClassLoader().getResource("konnyu.xml").getPath();
//		String teljesNév = fájl;
		//System.out.println(getClass().getResource("konnyu.xml").toString());
		//String fájlnév = getClass().getResource(teljesNév).toString();
		//System.out.println("A fájl:" + fájlnév);
		//
		//táblavezérlő.feldolgoz(adatkezelő.betöltFájlból("konnyu.xml"));
		//táblavezérlő.feldolgoz(adatkezelő.betöltFájlból("src/main/resources/konnyu.xml"));
		
	}
	
	
	 /**
	 * A {@link Stopper} osztálytól lekérdezi az eltelt időt
	 * @return az eltelt idő {@code String}-ként
	 */
	public String lekérElteltidőStringként(){
		 return átalakít(stopper.getElteltIdő());
	 }
	 
	 
	 /**
	  * A {@link Stopper} osztálytól lekérdezi az eltelt időt
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
					//System.out.println(átalakít(elteltIdő));
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
