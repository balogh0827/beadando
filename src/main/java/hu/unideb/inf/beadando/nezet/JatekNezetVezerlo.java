package hu.unideb.inf.beadando.nezet;

import java.io.IOException;
import java.util.List;

import hu.unideb.inf.beadando.hiba.AzonosErtekARekeszbenHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekASorbanHiba;
import hu.unideb.inf.beadando.hiba.AzonosErtekAzOszlopbanHiba;
import hu.unideb.inf.beadando.hiba.CellaTartalomHiba;
import hu.unideb.inf.beadando.hiba.CellaTipusValtoztatasiHiba;
import hu.unideb.inf.beadando.hiba.JatekStatuszValtasiHiba;
import hu.unideb.inf.beadando.hiba.OszlopszamHiba;
import hu.unideb.inf.beadando.hiba.SorszamHiba;
import hu.unideb.inf.beadando.hiba.TablaMeretHiba;
import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import hu.unideb.inf.beadando.kontroll.TablaVezerlo;
import hu.unideb.inf.beadando.modell.CellaTipus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class JatekNezetVezerlo{
	
	
	private JatekVezerlo játékVezérlő = new JatekVezerlo();
	public TablaVezerlo táblaVezérlő;

	@FXML
	private GridPane kicsi;
	
	@FXML
	private GridPane kicsiGombsor;
	
	
	@FXML
	private GridPane közepes;
	
	
	@FXML
	private GridPane közepesGombsor;
			
	@FXML
	private TextField elso;
	
	@FXML
	private MenuItem vissza;
	
	
	TextField aktCella;
	int aktSorszám;
	int aktOszlopszám;
	
	
	private GridPane tábla;
	private GridPane gombsor;
	
	
	@FXML
	private AnchorPane panel;
	
	@FXML
	private TextField játékosnév;
	
	
	
	@FXML
	private Label cellaszám;
	
	
	@FXML
	private TextArea üzenetek;
	
	
	@FXML 
	private MenuItem törölMenü;
	
	
	@FXML
	private MenuItem újrakezdMenü;
	
	
	@FXML
	private CheckMenuItem mennyiségek;
	
	
	@FXML
	private CheckMenuItem értékek;
	
	
	@FXML
	private CheckMenuItem kapcsolatok;
	
	@FXML
	private Button feladás;
	
	@FXML
	private Button mentés;	
	
	@FXML
	private MenuButton betöltés;
	
	@FXML 
	private Menu újMenü;
	
	@FXML
	private MenuItem betöltMenü;
	
	@FXML
	private MenuItem feladMenü;
	
	@FXML
	private MenuItem mentMenü;
	
	@FXML
	private MenuButton újGomb;
	
	@FXML
	private MenuItem kezdőLegördülő;
	
	@FXML
	private MenuItem átlagosLegördülő;
	
	
	NezetVezerlo főablak;
	Stage stage;
	
	
	Platform pl;
  
	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.főablak  = főablak;
	}
	
	public void setSage(Stage stage){
		this.stage = stage;
	}
	
	
	private void init(){
		tábla.setDisable(false);
		gombsor.setDisable(false);
		frissítCellaszám();
		feladás.setDisable(false);
		mentés.setDisable(false);
		mennyiségek.setDisable(false);
		értékek.setDisable(false);
		kapcsolatok.setDisable(false);
		mentés.setDisable(true);
		újMenü.setDisable(true);
		újrakezdMenü.setDisable(false);
		törölMenü.setDisable(false);
		feladMenü.setDisable(false);
		mentMenü.setDisable(false);
		betöltMenü.setDisable(true);
		mentés.setDisable(false);
		betöltés.setDisable(true);
		újGomb.setDisable(true);
		try {
			játékVezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			üzenetek.setText(e.getMessage());
		}
	}	
	
	
	private void alaphelyzetbeÁllít(){
		visszaállítSzínezés();
		visszaállítÜzenetek();
		cellaszám.setText("");
		feladás.setDisable(true);
		mentés.setDisable(true);
		mennyiségek.setDisable(true);
		értékek.setDisable(true);
		kapcsolatok.setDisable(true);
		mentés.setDisable(false);
		újMenü.setDisable(false);
		újrakezdMenü.setDisable(true);
		törölMenü.setDisable(true);
		feladMenü.setDisable(true);
		mentMenü.setDisable(true);
		betöltMenü.setDisable(false);
		mentés.setDisable(true);
		betöltés.setDisable(false);
		újGomb.setDisable(false);
		aktCella = null;
		aktSorszám = -1;
		aktOszlopszám = -1;
	}
	
	
	private void visszaállítÜzenetek(){
		üzenetek.setText("");
	}

	
	public void újJátékIndítás(int táblaméret){
		
		táblaVezérlő = játékVezérlő.lekérTáblaVezérlő();
		try {
			játékVezérlő.létrehozJátéktábla(táblaméret);
		} catch (TablaMeretHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			játékVezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			üzenetek.setText(e.getMessage());
		}
		
		
		if(tábla != null)
			tábla.setVisible(false);
		
		if(gombsor!=null)
			gombsor.setVisible(false);
	}
	
	
	@FXML
	private void újKönnyűJáték(){
		System.out.println("új 4x4-es játék létrehozása");
		
		újJátékIndítás(4);
		
		tábla = kicsi;
		gombsor = kicsiGombsor;
		
		alaphelyzetbeÁllít();
		init();
		frissítCellaNézet();
		
		tábla.setVisible(true);
		gombsor.setVisible(true);
		
	}
	
	
	
	@FXML
	private void újNormálJáték(){
		System.out.println("új 9x9-es játék létrehozása");
		
		újJátékIndítás(9);
		
		
		tábla = közepes;
		gombsor = közepesGombsor;
		
		alaphelyzetbeÁllít();
		init();
		frissítCellaNézet();
		
		tábla.setVisible(true);
		gombsor.setVisible(true);

	}
	
	
	public void frissítCellaNézet(){
		
		List<String> teljesTábla = null;
		try {
			teljesTábla = táblaVezérlő.leképezTábla();
		} catch (SorszamHiba | OszlopszamHiba e) {
			
			System.err.println("Sorszám vagy oszlopszám hiba!!!");
		}
		
		
		for(int i = 0; i < tábla.getChildren().size() ; i++){
			 
			
			((TextField)tábla.getChildren().get(i)).setText(teljesTábla.get(4*i + 3));
			
			
			if(teljesTábla.get(4*i).equals(CellaTipus.MEGADOTT.toString())){
				((TextField)tábla.getChildren().get(i)).setEditable(false);
				tábla.getChildren().get(i).setStyle("-fx-font-weight: bold;  -fx-border-color: black;");
			}
		}
		
		//táblaVezérlő.kiírCellákTípusa();
	}
	
	
	private void frissítCellaszám(){
		cellaszám.setText(String.valueOf(táblaVezérlő.kitöltendőCellákSzámaATáblában()));
	}

	
	@FXML
	private void frissítCella(){
		
		
		visszaállítÜzenetek();
		
			try {
				
				String text = aktCella.getText();
		
				if(text.equals("") || !aktCella.isEditable()){
					törölCella();
				}
				
				táblaVezérlő.kitöltCella(aktSorszám, aktOszlopszám, text);
			} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba | SorszamHiba | OszlopszamHiba |
					AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e){
				üzenetek.setText(e.getMessage());
			}
		
		
		visszaállítSzínezés();
		frissítCellaNézet();
		frissítCellaszám();
		mutatAzonosÉrtékek();
		jelezKapcsolódóCellák();
		ellenőrizVége();
		
		táblaVezérlő.kiírTábla();
		táblaVezérlő.kiírCellákTípusa();
	}
	
	
	@FXML
	private void beállítAktuálisCella(){
		for(int i = 0; i < tábla.getChildren().size(); i++){
			if(tábla.getChildren().get(i).isFocused()){
				aktCella = (TextField) tábla.getChildren().get(i);
			}
		}
		
		aktSorszám = GridPane.getRowIndex(aktCella) + 1;
		aktOszlopszám = GridPane.getColumnIndex(aktCella) + 1;
		visszaállítÜzenetek();
		visszaállítSzínezés();
		
		
		mutatAzonosÉrtékek();
		jelezKapcsolódóCellák();
		frissítCella();
	}
	
	
	@FXML
	private void beadÉrtéket(){
		
		Button gomb = null;
		
		for(Node b : gombsor.getChildren()){
			
			if(b.isFocused()){
				gomb = (Button)b;
				break;
			}
		}
		
		
		if(aktCella != null){
			
			if(gomb.getId() == null){
				aktCella.setText(gomb.getText());
				frissítCella();
			}else if(gomb.getId().equals("töröl")){
				törölCella();
			}
			else if(gomb.getId().equals("újra")){
				újrakezd();
			}
			
		}
	}
	
	
	private void ellenőrizVége(){
		
		if(táblaVezérlő.készATábla()){
			try {
				játékVezérlő.befejez();
				tábla.setDisable(true);
				gombsor.setDisable(true);
				megjelenítGratuláció();
				alaphelyzetbeÁllít();
			} catch (JatekStatuszValtasiHiba e) {}
		}
	}
	
	
	@FXML
	private void beállítJátékosNév(){
		
		if(játékVezérlő.fut()){
			
			if(játékosnév.getText().isEmpty())
				játékVezérlő.beállítJátékosNév(játékosnév.getPromptText());
			else
				játékVezérlő.beállítJátékosNév(játékosnév.getText());
		}
	}
	
	
	private void megjelenítGratuláció(){
		Alert grat = new Alert(AlertType.INFORMATION);
		grat.setTitle("Játék vége");
		grat.setResizable(false);
		grat.setHeaderText("Gratulálok, " + játékVezérlő.lekérJátékosNév() +  " sikeresen kitöltötted a táblát!");
		grat.setContentText("Felhasznált időd: " + játékVezérlő.lekérElteltidőStringként());
		grat.show();
	}
	
	
	
	@FXML
	private void felad(){
		System.out.println("feladás");
		System.out.println(játékVezérlő.lekérJátékosNév());
		tábla.setDisable(true);
		gombsor.setDisable(true);
		try {
			játékVezérlő.felad();
		} catch (JatekStatuszValtasiHiba e) {
			e.printStackTrace();
		}
		visszaállítSzínezés();
		alaphelyzetbeÁllít();
	}
	
	
	@FXML
	private void ment(){
		System.out.println("mentés fájlba...");
		
		try {
			játékVezérlő.táblaMentés();
		} catch ( SorszamHiba | OszlopszamHiba e) {
			//logolás
		}catch(IOException e){
			üzenetek.setText("Hiba történt a kimeneti fájl létrehozásakor.");
		}

	}
	
	
	private void betölt(String minősítő){
		
		try {
			játékVezérlő.betöltTábla(minősítő);
		} catch (IOException | SorszamHiba | OszlopszamHiba | CellaTartalomHiba | CellaTipusValtoztatasiHiba
				| AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void betöltKicsi(){
		újKönnyűJáték();
		betölt("konnyu");
	}
	
	@FXML
	private void betöltKözepes(){
		
		újNormálJáték();
		betölt("normal");
	}

	
	@FXML
	private void törölCella(){
		if(aktCella == null ) return;
		
		try {
			táblaVezérlő.kitöltCella(aktSorszám, aktOszlopszám, "");;
		} catch (CellaTartalomHiba | CellaTipusValtoztatasiHiba 
				 | SorszamHiba | OszlopszamHiba | AzonosErtekASorbanHiba 
				 | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
			üzenetek.setText(e.getMessage());
		}
		
//		táblaVezérlő.kiírTábla();
//		táblaVezérlő.kiírCellákTípusa();
		
		frissítCellaNézet();
		frissítCellaszám();
		visszaállítSzínezés();
		jelezKapcsolódóCellák();
		mutatAzonosÉrtékek();
	}
	
	
	@FXML
	private void újrakezd(){
		
		táblaVezérlő.újrakezd();
		
//		táblaVezérlő.kiírTábla();
//		táblaVezérlő.kiírCellákTípusa();
		
		frissítCellaNézet();
		frissítCellaszám();
		visszaállítSzínezés();
		jelezKapcsolódóCellák();
		mutatAzonosÉrtékek();
	}
	
	
	@FXML
	private void mutatMennyiség(){
		if(mennyiségek.isSelected())
			System.out.println("mennyiségek mutatása");
		
	}
	
	private void visszaállítSzínezés(){
		tábla.getChildren().forEach(c -> c.setStyle("-fx-background-color: white; -fx-border-color: black;"));
	}
	
	
	@FXML
	private void mutatAzonosÉrtékek(){
		
		
		if(aktCella == null){
			return;
		}
		
		if(értékek.isSelected()){
			
			frissítCellaNézet();
			
			java.util.List<int[]> lista = 
					táblaVezérlő.azonosÉrtéketTartalmazóCellák(aktCella.getText());
			
			int méret = lista.size();
			int sor, oszlop;	
			
			for(int i = 0; i < méret; i++){
				
				sor = lista.get(i)[0] - 1;
				oszlop = lista.get(i)[1] - 1;
				
				for(int j = 0; j < tábla.getChildren().size(); j++){
					
					TextField vizsgált = (TextField)tábla.getChildren().get(j);
					
					if(GridPane.getRowIndex(vizsgált) == sor
					&& GridPane.getColumnIndex(vizsgált) == oszlop){
						vizsgált.setStyle("-fx-background-color: yellow;  -fx-border-color: black;");
					}
				}
				
			}
			
		}else{
			if(kapcsolatok.isSelected()){
				visszaállítSzínezés();
				jelezKapcsolódóCellák();
			}else{
				visszaállítSzínezés();
			}
		}
	}
	
	
	@FXML
	private void jelezKapcsolódóCellák(){
		
		if(aktCella == null){
			return;
		}
		

		if(kapcsolatok.isSelected()){
			
			java.util.Set<int[]> cellák = null;
			
			try {
				cellák = táblaVezérlő.kapcsolatbanÁllóCellák(aktSorszám, aktOszlopszám);
			} catch (SorszamHiba | OszlopszamHiba e) {
				üzenetek.setText(e.getMessage());
			}
			
			frissítCellaNézet();
			
				cellák.stream().forEach(t -> {
					
					int sor = t[0] - 1;
					int oszlop = t[1] - 1;
				
					for(int j = 0; j < tábla.getChildren().size(); j++){
						
						TextField vizsgált = (TextField)tábla.getChildren().get(j);
						
						if(GridPane.getRowIndex(vizsgált) == sor
						&& GridPane.getColumnIndex(vizsgált) == oszlop){
							vizsgált.setStyle("-fx-background-color: lightgreen;  -fx-border-color: black;");
						}
					}
				});

		} else{
			if(értékek.isSelected()){
				visszaállítSzínezés();
				mutatAzonosÉrtékek();
			}else{
				visszaállítSzínezés();
			}
		}
			
	}
	
	
	@FXML
	private void névjegy(){
		System.out.println("Sudoku version: 1.0");
		
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Sudoku névjegye");
		a.setResizable(false);
		a.setHeaderText("Információ");
		a.setContentText("Sudoku version: 1.0");
		a.show();
	}
	
	
	@FXML
	private void sugo(){
		főablak.betöltSúgó(2);
	}
	
	@FXML
	private void vissza(){
		if(játékVezérlő.fut()){
			try {
				játékVezérlő.befejez();
			} catch (JatekStatuszValtasiHiba e) {
				üzenetek.setText(e.getMessage());
			}
		}
		
		stage.close();
		főablak.mutatAblak();
	}

}
