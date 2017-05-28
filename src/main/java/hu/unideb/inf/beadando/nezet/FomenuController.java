package hu.unideb.inf.beadando.nezet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;

public class FomenuController {
	
	private NezetVezerlo vezérlő;
	private static Logger logger = LoggerFactory.getLogger(FomenuController.class);
	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.vezérlő  = főablak;
	}
	
	
	@FXML
	private void ujJatekotIndit(){
		
		logger.info("Játék indítása...");
		vezérlő.létrehozJátékNézet();
	}
	
	
	@FXML
	private void elozmenyeketMutat(){
		
		logger.info("Eredmények betöltése...");
		vezérlő.létrehozEredményNézet();

	}
	
	
	@FXML
	private void segitsegetNyujt(){
		
		logger.info("Súgó betöltése...");
		vezérlő.betöltSúgó(1);
	}
	
	@FXML
	private void kilep(){
		vezérlő.bezárAblak();
	}
}
