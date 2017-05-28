package hu.unideb.inf.beadando.teszt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import hu.unideb.inf.beadando.hiba.JatekStatuszValtasiHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import hu.unideb.inf.beadando.modell.JatekStatusz;

public class JatekVezerloTest {
	
	private JatekVezerlo vezérlő;
	private String elvártÜzenet;
	private String aktuálisÜzenet;
	
	@Before
	public void létrehoz(){
		vezérlő = new JatekVezerlo();
	}
	
	@Test
	public void setBetöltTeszt(){
		vezérlő.setBetölt();
		assertTrue( vezérlő.getBetölt());
	}
	
	@Test(expected = TablaMeretHiba.class)
	public void létrehozJátékTáblaNegatívMérettelTeszt() throws TablaMeretHiba{
		
		int méret = -3;
		try{
			vezérlő.létrehozJátéktábla(méret);
			fail("TablaMeretHiba kivételt kellett volna dobnia.");
		}catch(TablaMeretHiba tmh){
			assertTrue(tmh.getMessage().equals("A tábla mérete nem állítható be erre az értékre: " + méret + " !"
					+ "\nFolytatás alapértelmezett táblaméret alkalmazásával."));
			throw new TablaMeretHiba(méret);
		}
		fail("Ide nem juthatott volna el a vezérlés.");
	}

	@Test(expected = TablaMeretHiba.class)
	public void létrehozJátékTáblaHibásPozitívMérettelTeszt() throws TablaMeretHiba{
		vezérlő.létrehozJátéktábla(5);
		fail("Ide nem juthatott volna el a vezérlés.");
	}
	
	@Test
	public void lérehozJátékTáblaHelyesMérettelTeszt() throws TablaMeretHiba{
			vezérlő.létrehozJátéktábla(4);
	}
	
	
	@Test
	public void lekérTáblaVezérlőTeszt(){
		
		assertNotNull("Nem kellene null-nak lennie.", vezérlő.lekérTáblaVezérlő());
	}
	
	
	@Test
	public void beállítJátékosNévTeszt() throws JatekStatuszValtasiHiba{
		vezérlő.indít();
		vezérlő.beállítJátékosNév("Ádám");
		assertEquals(vezérlő.lekérJátékosNév(), "Ádám");
	}


	@Test
	public void indítÉsInicializálásTeszt() throws JatekStatuszValtasiHiba{
		
		vezérlő.indít();
		
		assertFalse("Az aktuálisJáték nem kapott értéket.", vezérlő.getAktuálisJátékIsNull());
		assertEquals("A játékosNév hibás értéket kapott.", "1. játékos", vezérlő.lekérJátékosNév());
		assertFalse("A stopper null maradt.", vezérlő.getStopperIsNull());
		assertTrue("Nem helyes a fut értéke.", vezérlő.fut());
		assertFalse("Nem helyes a feladta értéke.", vezérlő.feladott());
		assertFalse("Nem helyes a befejezte értéke.", vezérlő.befejezett());
	}
	
	@Test
	public void indítHiányTeszt(){
		
		try {
			vezérlő.befejez();
		} catch (Exception e) {
			assertTrue(vezérlő.getAktuálisJátékIsNull());
			assertTrue(vezérlő.getStopperIsNull());
		}
	}
	
	@Test
	public void feladTeszt(){
		try {
			vezérlő.létrehozJátéktábla(4);
			vezérlő.indít();
			vezérlő.beállítJátékosNév("%TEST%");
			vezérlő.felad();
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kellene kivételnek kiváltódnia szabályos állapotváltáskor.");
		} catch (TablaMeretHiba tmh){
			fail("Megfelelő táblaméretre nem kellet volna kivételnek kiváltódni.");
		}
		
		assertFalse("Hamisnak kellene lennie, mivel a játékot feladták.", vezérlő.fut());
		assertTrue("Igaznak kellene lennie, mivel a játékot feladták.", vezérlő.feladott());
		assertEquals(vezérlő.lekérJátékosNév(),"1. játékos");
	}
	
	@Test
	public void befejezTeszt(){
		try {
			vezérlő.létrehozJátéktábla(4);
			vezérlő.indít();
			vezérlő.befejez();
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kellene kivételnek kiváltódnia szabályos állapotváltáskor.");
		}catch (TablaMeretHiba tmh){
			fail("Megfelelő táblaméretre nem kellet volna kivételnek kiváltódni.");
		}
		assertFalse("Hamisnak kellene lennie, mivel a játékot befejezték.", vezérlő.fut());
		assertTrue("Igaznak kellene lennie, mivel a játékot befejezték.", vezérlő.befejezett());
	}
	
	
	@Test(timeout = 5000)
	public void lekérElteltIdőTeszt(){
		try {
			vezérlő.létrehozJátéktábla(4);
			vezérlő.indít();
			Thread.sleep(2000);
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kell JatekStatuszValtasiHiba kivételnek kiváltódni.");
		} catch(TablaMeretHiba tmh){
			fail("Nem váltódhat ki TablaMeretHiba.");
		}  catch (InterruptedException e) {}
		assertNotEquals("Nem kellene nullának lennie.",  vezérlő.lekérElteltIdő(), 0);
		assertFalse("Nem kellene azonosnak lennie.", vezérlő.lekérElteltidőStringként().equals("00:00:00"));
		try {
			vezérlő.befejez();
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kell kivételnek keletkeznie.");
		}
	}
	
	
}
