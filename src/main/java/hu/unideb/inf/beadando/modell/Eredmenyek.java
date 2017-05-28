package hu.unideb.inf.beadando.modell;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A játékok eredményeinek tárolására szolgáló osztály.
 * @author Balogh Ádám
 *
 */
@XmlRootElement(namespace="hu.unideb.inf.beadando.modell", name = "eredmenyek")
public class Eredmenyek {

	/**
	 * Az eredmények tárolására szolgáló lista.
	 */
	@XmlElementWrapper(name="eredmenyLista")
	@XmlElement(name="eredmeny")
	private List<Eredmeny> eredménylista = new ArrayList<>();
	
	
	/**
	 * Visszaadja az eredmények listáját.
	 * @return az {@link #eredménylista}
	 */
	public List<Eredmeny> getEredmények(){
		return eredménylista;
	} 
	
	/**
	 * Bővíti az eredmények listáját a paraméterként megadott elemmel.
	 * @param újEredmény az {@link #eredménylista} új eleme
	 */
	public void hozzáadEredmény(Eredmeny újEredmény){
		
		eredménylista.add(újEredmény);
	}
	
	
	/**
	 * Beállítja az {@link #eredménylista} értékét a paraméter által megadottra.
	 * @param eredmények a beállítandő eredménylista
	 */
	public void beállítEredmények(List<Eredmeny> eredmények){
		eredménylista = eredmények;
	}
	
	
	
}
