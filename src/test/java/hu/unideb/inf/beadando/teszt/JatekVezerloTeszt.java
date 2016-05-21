package hu.unideb.inf.beadando.teszt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import hu.unideb.inf.beadando.hiba.JatekStatuszValtasiHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import hu.unideb.inf.beadando.modell.JatekStatusz;

public class JatekVezerloTeszt {
	
	private JatekVezerlo vezérlő;
	private String elvártÜzenet;
	private String aktuálisÜzenet;
	
	@Before
	public void létrehoz(){
		vezérlő = new JatekVezerlo();
	}
	
	@Test(expected = TablaMeretHiba.class)
	public void létrehozJátékTáblanegatívMérettelTeszt() throws TablaMeretHiba{
		vezérlő.létrehozJátéktábla(-3);
	}

	@Test(expected = TablaMeretHiba.class)
	public void létrehozJátékTáblaHibásPozitívMérettelTeszt() throws TablaMeretHiba{
		vezérlő.létrehozJátéktábla(5);
	}
	
	@Test
	public void lérehozJátékTáblaHelyesMérettelTeszt(){
		try {
			vezérlő.létrehozJátéktábla(4);
		} catch (TablaMeretHiba e) {
			fail("Nem kellene kivételnek kiváltódnia helyes táblaméretre.");
		}
	}
	
	
	@Test
	public void lekérTáblaVezérlőTeszt(){
		
		assertNotNull("Nem kellene null-nak lennie.", vezérlő.lekérTáblaVezérlő());
	}
	
	
	@Test
	public void beállítJátékosNévTeszt(){
		try {
			vezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			e.printStackTrace();
		}
		vezérlő.beállítJátékosNév("Ádám");
		assertEquals(vezérlő.lekérJátékosNév(), "Ádám");
	}


	@Test
	public void indítInicializálásTeszt(){
		try {
			vezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			e.printStackTrace();
		}
		assertNotNull("Az aktuálisJáték nem kapott értéket.", vezérlő.lekérJátékosNév());
		assertEquals("A játékosNév hibás értéket kapott.", "1. játékos", vezérlő.lekérJátékosNév());
		assertTrue("Nem helyes a fut értéke.", vezérlő.fut());
		assertFalse("Nem helyes a feladta értéke.", vezérlő.feladott());
		assertFalse("Nem helyes a befejezte értéke.", vezérlő.befejezett());
	}
	
	@Test
	public void indítKivétekTeszt(){
		
		try {
			vezérlő.indít();
			vezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			elvártÜzenet = "Játék státusz változtatása nem engedélyezett ["
				+ JatekStatusz.ELKEZDETT + " -> " + JatekStatusz.ELKEZDETT + "]";
			aktuálisÜzenet = e.getMessage();
			assertEquals("Hibás üzenet generálódott.", elvártÜzenet, aktuálisÜzenet);
		}
	}
	
	
	@Test
	public void feladTeszt(){
		try {
			vezérlő.indít();
			vezérlő.felad();
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kellene kivételnek kiváltódnia szabályos állapotváltáskor.");
		}
		
		assertFalse("Hamisnak kellene lennie, mivel a játékot feladták.", vezérlő.fut());
		assertTrue("Igaznak kellene lennie, mivel a játékot feladták.", vezérlő.feladott());
	}
	
	@Test
	public void befejezTeszt(){
		try {
			vezérlő.indít();
			vezérlő.befejez();
		} catch (JatekStatuszValtasiHiba e) {
			fail("Nem kellene kivételnek kiváltódnia szabályos állapotváltáskor.");
		}
		assertFalse("Hamisnak kellene lennie, mivel a játékot befejezték.", vezérlő.fut());
		assertTrue("Igaznak kellene lennie, mivel a játékot befejezték.", vezérlő.befejezett());
		
	}
	
	
	@Test(timeout = 5000)
	public void lekérElteltIdőTeszt(){
		try {
			vezérlő.indít();
			Thread.sleep(2000);
			vezérlő.befejez();
		} catch (JatekStatuszValtasiHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("Nem kellene nullának lennie.",  vezérlő.lekérElteltIdő() > 0);
		assertFalse("Nem kellene azonosnak lennie.", vezérlő.lekérElteltidőStringként().equals("00:00:00"));
	}
	
	
}
