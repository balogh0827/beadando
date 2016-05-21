package hu.unideb.inf.beadando.nezet;

import java.util.List;

import hu.unideb.inf.beadando.modell.Eredmeny;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EredmenyekNezetVezerlo {

	NezetVezerlo főablak;
	Stage tartalom;
	
	@FXML
	private TableView<Eredmeny> táblázat;
	
	@FXML
	private TableColumn<Eredmeny, String> játékosnév;
	
	@FXML
	private TableColumn<Eredmeny, String> táblaméret;
	
	@FXML
	private TableColumn<Eredmeny, String> játékállapot;
	
	@FXML
	private TableColumn<Eredmeny, String> időtartam;
	
	@FXML
	private TableColumn<Eredmeny, String> dátum;
	
	@FXML
	private ChoiceBox<Eredmeny> választó;
	
	@FXML
	private TextField szövegmező;
	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.főablak  = főablak;
	}
	
	
	
	public void feltöltTábla(List<Eredmeny> lista){
		
	}
	
	
	public void setSage(Stage stage){
		this.tartalom = stage;
	}
	
	
	@FXML
	private void vissza(){
		tartalom.close();
		főablak.mutatAblak();
	}

	@FXML
	private void súgó(){
		főablak.betöltSúgó(3);
	}
	
	@FXML
	private void szűrés(){
		
	}

	
}
