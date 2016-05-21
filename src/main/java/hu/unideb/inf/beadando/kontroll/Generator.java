package hu.unideb.inf.beadando.kontroll;

import java.util.ArrayList;
import java.util.List;

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



/**
 * A tábla generálásáért felelős osztály.
 * @author Balogh Ádám
 *
 */
public class Generator {

	
	
	private TablaVezerlo táblavezérlő = new TablaVezerlo();
	
	private boolean hibás;
	
	private int tűréshatár = 1;
	
	private List<String> mentés = new ArrayList<>();
	
	private List<List<Integer>> módosítások = new ArrayList<List<Integer>>();
	
	private int visszatöltésSzáma = 0;
	
	public TablaVezerlo lekérTáblavazérlő(){
		return táblavezérlő;
	}
	
	
	
	public void generálTábla(int táblaMéret) throws SorszamHiba, OszlopszamHiba{
		
		
		
		try {
			TablaEllenor.ellenőrizTáblaMéret(táblaMéret);
			táblavezérlő.létrehozMegadottMéretűTábla(táblaMéret);
		} catch (TablaMeretHiba e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		//System.out.println("indul");
		
		//táblavezérlő.kiírTábla();
		
		try {
//			táblavezérlő.kitöltCella(1, 4, "5");
//			táblavezérlő.kitöltCella(1, 6, "2");
//			táblavezérlő.kitöltCella(2, 2, "3");
//			táblavezérlő.kitöltCella(2, 5, "7");
//			táblavezérlő.kitöltCella(2, 8, "8");
//			táblavezérlő.kitöltCella(3, 2, "9");
//			táblavezérlő.kitöltCella(3, 3, "4");
//			táblavezérlő.kitöltCella(3, 4, "8");
//			táblavezérlő.kitöltCella(3, 6, "1");
//			táblavezérlő.kitöltCella(3, 7, "6");
//			táblavezérlő.kitöltCella(3, 8, "5");
//			táblavezérlő.kitöltCella(4, 1, "5");
//			táblavezérlő.kitöltCella(4, 3, "8");
//			táblavezérlő.kitöltCella(4, 7, "9");
//			táblavezérlő.kitöltCella(4, 9, "7");
//			táblavezérlő.kitöltCella(5, 4, "2");
//			táblavezérlő.kitöltCella(5, 6, "6");
//			táblavezérlő.kitöltCella(6, 1, "3");
//			táblavezérlő.kitöltCella(6, 3, "2");
//			táblavezérlő.kitöltCella(6, 7, "8");
//			táblavezérlő.kitöltCella(6, 9, "4");
//			táblavezérlő.kitöltCella(7, 2, "5");
			táblavezérlő.kitöltCella(7, 3, "3");
//			táblavezérlő.kitöltCella(7, 4, "1");
//			táblavezérlő.kitöltCella(7, 6, "8");
//			táblavezérlő.kitöltCella(7, 7, "7");
//			táblavezérlő.kitöltCella(7, 8, "4");
//			táblavezérlő.kitöltCella(8, 2, "4");
//			táblavezérlő.kitöltCella(8, 5, "5");
//			táblavezérlő.kitöltCella(8, 8, "1");
//			táblavezérlő.kitöltCella(9, 4, "4");
//			táblavezérlő.kitöltCella(9, 6, "3");
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		//táblavezérlő.kiírTábla();
		
		
		
		int size = 0;
		
		
		//System.out.println("legkevesebb lehetőségű cella DB: " + size);
		
		int sorszám, oszlopszám;
		int érték;
		int számláló = 0;
		
		lementÁllapot();
		
		
		
		
		while(!táblavezérlő.készATábla() && számláló < tűréshatár ){
			
			
			///biztosakat beír
			if(legkevesebbBeírhatóDarab() == 1){
				

				List<int[]> l = legkevesebbBeírhatóDarabszámúCella();
				size = legkevesebbBeírhatóDarabszámúCella().size();
				boolean voltHiba = false;
				
				for(int i = 0; i < size; i++){
					
					int[] cella = l.get(i);
					
					sorszám = cella[0];
					oszlopszám = cella[1];
					
					if(táblavezérlő.cellábaÍrhatóSzámjegyek(sorszám, oszlopszám).isEmpty()){
						break;
					}
					
					érték = táblavezérlő.cellábaÍrhatóSzámjegyek(sorszám, oszlopszám).get(0);
					
					
					try {
						táblavezérlő.kitöltCella(sorszám, oszlopszám, String.valueOf(érték));
					} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba
							| AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
						voltHiba = true;
					}
					
				}
				
				if(voltHiba){
					visszatöltÁllapot();
				}
				
			}else{					
				
			}		
			
			táblavezérlő.kiírTábla();
		}
		
		
		
		if(számláló >= tűréshatár){
			hibás = true;
			System.err.println("hibás a tábla!!!");
		}
		
		//táblavezérlő.kiírTábla();
		
	}

	
	private void csere(int sor1, int sor2, int oszlop1, int oszlop2){
		
		
		try {
			String tartalom1 = táblavezérlő.lekérCellaAdatok(sor1, oszlop1).get(3);
			String tartalom2 = táblavezérlő.lekérCellaAdatok(sor2, oszlop2).get(3);
			
			táblavezérlő.kitöltCella(sor1, oszlop1, tartalom2);
		
			táblavezérlő.kitöltCella(sor2, oszlop2, tartalom1);
			
		} catch (SorszamHiba | OszlopszamHiba | CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	///jónak tűnik
	private int legkevesebbBeírhatóDarab() throws SorszamHiba, OszlopszamHiba{
		

		int méret = táblavezérlő.lekérTáblaMéret();
		int legkevesebbDarab = méret;
		int aktuálisÉrtékszám = méret;

		
		for(int sor = 1; sor <= méret; sor++){
			
			for(int oszlop = 1; oszlop <= méret; oszlop++){
				
				if(!táblavezérlő.lekérCellaAdatok(sor, oszlop).get(0).equals(CellaTipus.KITÖLTÖTT.toString()))
					aktuálisÉrtékszám = táblavezérlő.cellábaÍrhatóSzámjegyek(sor, oszlop).size();
				
				if(aktuálisÉrtékszám == 0 )
					continue;
				
				if( legkevesebbDarab > aktuálisÉrtékszám){
					legkevesebbDarab = aktuálisÉrtékszám;
				}
			}
			
		}
		
		return legkevesebbDarab;
		
	}
	
	//jónak tűnik
	private List<int[]> legkevesebbBeírhatóDarabszámúCella() throws SorszamHiba, OszlopszamHiba{
		
		List<int[]> lista = new ArrayList<>();
		
		int méret = táblavezérlő.lekérTáblaMéret();
		int legkevesebbDarab = legkevesebbBeírhatóDarab();
		int aktuálisÉrtékszám = méret;
		

		for(int sor = 1; sor <= méret; sor++){
			
			for(int oszlop = 1; oszlop <= méret; oszlop++){
				aktuálisÉrtékszám = táblavezérlő.cellábaÍrhatóSzámjegyek(sor, oszlop).size();
				
				if(legkevesebbDarab == aktuálisÉrtékszám){
					lista.add(new int[]{sor, oszlop});
				}
			}
			
		}
		
		
		return lista;
	} 
	
	
	private void lementÁllapot(){
		
		System.out.println("mentés...");
		
		mentés.clear();
		
		try {
			mentés = táblavezérlő.leképezTábla();
		} catch (SorszamHiba | OszlopszamHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//táblavezérlő.kiírTábla();
		
	}
	
	
	private void visszatöltÁllapot(){
		
		System.out.println("visszatöltés");
		
		táblavezérlő.újrakezd();
		
		try {
			táblavezérlő.feldolgoz(mentés);
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba
				| AzonosErtekARekeszbenHiba | SorszamHiba | OszlopszamHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//táblavezérlő.kiírTábla();
	}
	
	
	
}
