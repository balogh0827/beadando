package hu.unideb.inf.beadando.nezet;

import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import hu.unideb.inf.beadando.modell.Eredmeny;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	private ChoiceBox<String> választó;
	
	@FXML
	private TextField szövegmező;
	
	JatekVezerlo j = new JatekVezerlo();
	
	ObservableList<Eredmeny> elist;
	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.főablak  = főablak;
	}
	
	
	@FXML
	private void initialize(){
		
		feltöltTábla();

		választó.setItems(FXCollections.observableArrayList("játékosnév", "táblaméret", "játékállapot"));
	}
	
	
	private void feltöltTábla(){
		táblázat.setPlaceholder(new Label("A táblázat üres"));
		elist = FXCollections.observableArrayList(j.betöltFájlbólEredmények());
		táblázat.setItems(elist);
		játékosnév.setCellValueFactory(jn -> jn.getValue().getJátékosNévProperty());
		táblaméret.setCellValueFactory(tm -> tm.getValue().getTáblaMéretProperty());
		játékállapot.setCellValueFactory(já -> já.getValue().getJátékÁllapotProperty());
		időtartam.setCellValueFactory(it -> it.getValue().getIdőtartamProperty());
		dátum.setCellValueFactory(d -> d.getValue().getDátumProperty());
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
		
		feltöltTábla();
		
		if(választó.getSelectionModel().isEmpty()){
			return;
		}
		
		String tulajdonság = választó.getSelectionModel().getSelectedItem();
		String feltétel = szövegmező.getText();
		
		
		if(tulajdonság.equals("játékosnév")){
			táblázat.getItems().removeIf(e->!e.getJatekosnev().equals(feltétel));
		}else if(tulajdonság.equals("táblaméret")){
			táblázat.getItems().removeIf(e->!e.getTablameret().contains(feltétel));
		}else if(tulajdonság.equals("játékállapot")){
			táblázat.getItems().removeIf(e->!e.getJatekallapot().equals(feltétel));
		}
		
		if(táblázat.getItems().isEmpty()){
			táblázat.setPlaceholder(new Label("Nem található a feltételeknek megfelelő elem."));
		}
		
	}

	
	@FXML
	private void szűrőTörlése(){
		feltöltTábla();
		szövegmező.setText("");
		választó.getSelectionModel().clearSelection();
	}
	
}
