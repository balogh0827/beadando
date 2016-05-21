package hu.unideb.inf.beadando;

import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.kontroll.Generator;
import hu.unideb.inf.beadando.kontroll.TablaVezerlo;
import hu.unideb.inf.beadando.nezet.NezetVezerlo;



/**
 * A projekt {@code main()} metódusát tartalmazó osztálya.
 * @author Balogh Ádám
 *
 */
public class Kezelo{

	
	/**
	 * A program fő belépési pontja.
	 * <p>Elindítja a {@code NezetVezerlo} osztályt.
	 * @param args parancssori argumentumok
	 */
	public static void main(String[] args){
	
		NezetVezerlo.main(args);
		
//		Generator g = new Generator();
//		TablaVezerlo t = g.lekérTáblavazérlő();
//		
//		try {
//			g.generálTábla(9);
//		} catch (SorszamHiba | OszlopszamHiba e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		t.kiírTábla();
		
		
	}

}
