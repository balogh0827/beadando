package hu.unideb.inf.beadando.nezet;

import javafx.fxml.FXML;

public class FomenuController {
	
	private NezetVezerlo vezérlő;

	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.vezérlő  = főablak;
	}
	
	
	@FXML
	private void ujJatekotIndit(){
		
		System.out.println("Játék indítása");
		vezérlő.létrehozJátékNézet();
	}
	
	
	@FXML
	private void elozmenyeketMutat(){
		
		System.out.println("Eredmények betöltése");
		vezérlő.létrehozEredményNézet();

	}
	
	
	@FXML
	private void segitsegetNyujt(){
		
		System.out.println("Súgó betöltése...");
		vezérlő.betöltSúgó(1);
	}
	
	@FXML
	private void kilep(){
		vezérlő.bezárAbalak();
	}
}
