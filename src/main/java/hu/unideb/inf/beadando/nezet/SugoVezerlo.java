package hu.unideb.inf.beadando.nezet;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class SugoVezerlo {

	@FXML
	private TabPane pane;
	
	@FXML
	private Tab szabaly;
	
	@FXML
	private Tab jatek;
	
	@FXML
	private Tab erdmeny;
	
	
	public void kiválaszt(int lapszám){
		pane.getSelectionModel().clearAndSelect(lapszám - 1);
	}

	
	
}
