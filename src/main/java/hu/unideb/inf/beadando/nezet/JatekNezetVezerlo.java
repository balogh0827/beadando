package hu.unideb.inf.beadando.nezet;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.unideb.inf.beadando.hiba.*;
import hu.unideb.inf.beadando.kontroll.JatekVezerlo;
import hu.unideb.inf.beadando.kontroll.TablaVezerlo;
import hu.unideb.inf.beadando.modell.CellaTipus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class JatekNezetVezerlo{
	
	private static Logger logger = LoggerFactory.getLogger(JatekNezetVezerlo.class); 
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
	 
	
	public void setNézetVezérlő(NezetVezerlo főablak){
		this.főablak  = főablak;
	}
	
	public void setSage(Stage stage){
		this.stage = stage;
	}
	
	
	private void init(){
		alaphelyzetbeÁllít();
		visszaállítÜzenetek();
		tábla.setDisable(false);
		gombsor.setDisable(false);
		frissítCellaszám();
		feladás.setDisable(false);
		mentés.setDisable(false);
		értékek.setDisable(false);
		értékek.setSelected(false);
		kapcsolatok.setDisable(false);
		kapcsolatok.setSelected(false);
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
		frissítCellaNézet();
		frissítJátékosNév();
		tábla.setVisible(true);
		gombsor.setVisible(true);
	}	
	
	
	private void alaphelyzetbeÁllít(){
		visszaállítSzínezés();
		visszaállítÜzenetek();
		cellaszám.setText("");
		feladás.setDisable(true);
		mentés.setDisable(true);
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
		üzenetek.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
	}

	
	public void újJátékIndítás(int táblaméret){
		táblaVezérlő = játékVezérlő.lekérTáblaVezérlő();
		try {
			játékVezérlő.létrehozJátéktábla(táblaméret);
		} catch (TablaMeretHiba e) {
			logger.error("Hibás táblaméret miatt nem hozható létre a tábla.");
		}
		
		try {
			játékVezérlő.indít();
		} catch (JatekStatuszValtasiHiba e) {
			logger.error("Sikertelen játékindítás, nem megengedett a játék új státusza.");
		}
		
		
		if(tábla != null){
			tábla.setVisible(false);
			tábla = null;
		}
		
		if(gombsor!=null){
			gombsor.setVisible(false);
			gombsor = null;
		}
	}
	
	
	@FXML
	private void újKönnyűJáték(){
		logger.info("Új 4x4-es játék indítása...");
		újJátékIndítás(4);
		
		tábla = kicsi;
		gombsor = kicsiGombsor;

		init();
	}
	
	
	
	@FXML
	private void újNormálJáték(){
		logger.info("Új 9x9-es játék indítása...");
		újJátékIndítás(9);
		
		tábla = közepes;
		gombsor = közepesGombsor;
		
		init();
	}
	
	
	public void frissítCellaNézet(){
		
		List<String> teljesTábla = null;
		try {
			teljesTábla = táblaVezérlő.leképezTábla();
		} catch (SorszamHiba e) {
			logger.error("Hibás sorszám lett megadva!");
			logger.debug("SorszamHiba keletkezett táblafrissítéskor: " + e.getMessage());
		} catch(OszlopszamHiba e){
			logger.error("Hibás oszlopszám lett megadva!");
			logger.debug("OszlopszamHiba keletkezett táblafrissítéskor: " + e.getMessage());
		}
		
		
		for(int i = 0; i < tábla.getChildren().size() ; i++){
			 
			((TextField)tábla.getChildren().get(i)).setText(teljesTábla.get(4*i + 3));
			
			if(teljesTábla.get(4*i).equals(CellaTipus.MEGADOTT.toString())){
				((TextField)tábla.getChildren().get(i)).setEditable(false);
				tábla.getChildren().get(i).setStyle("-fx-border-color: black;");
			}
		}
		
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
			} catch (CellaTartalomHiba | SorszamHiba | OszlopszamHiba |
					AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e){
				üzenetek.setText(e.getMessage());
				logger.warn("A játékos hibás műveletet próbált végrehajtani.");
				logger.debug("A játékos hibás értéket adott meg az aktuális cellába: " + e.getMessage());
			} catch(CellaTipusValtoztatasiHiba e){
				logger.warn("A játékos hibás műveletet próbált végrehajtani.");
				logger.debug(e.getRészletesHibaÜzenet());
			}
		
		
		visszaállítSzínezés();
		frissítCellaNézet();
		frissítCellaszám();
		jelezKapcsolódóCellák();
		mutatAzonosÉrtékek();
		ellenőrizVége();
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
		
		logger.debug("Az aktuális cella: " + "(" + aktSorszám + ", " + aktOszlopszám + ")");
		
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
		
		if(gomb.getId() != null && gomb.getId().equals("újra")){
			újrakezd();
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
				beállítJátékosNév();
				frissítJátékosNév();
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
	
	private void frissítJátékosNév(){
		
		String név = játékVezérlő.lekérJátékosNév();
		if(név.equals("1. játékos")){
			játékosnév.setText("");
			játékosnév.setPromptText(név);
		}else{
			játékosnév.setText(név);
		}
	}
	
	
	private void megjelenítGratuláció(){
		Alert grat = new Alert(AlertType.INFORMATION);
		grat.setTitle("Játék vége");
		grat.setResizable(false);
		grat.setHeaderText("Gratulálok, " + játékVezérlő.lekérJátékosNév() +  " sikeresen kitöltötted a táblát!");
		grat.setContentText("Felhasznált időd: " + játékVezérlő.lekérÖsszidőStringként());
		grat.show();
		logger.debug("Megjelent a játék végén felugró gratulációs dialógus.");
	}
	
	
	
	@FXML
	private void felad(){
		logger.info("A játékos a játék feladását kezdeményezte kattintott.");
		tábla.setDisable(true);
		gombsor.setDisable(true);
		try {
			játékVezérlő.felad();
		} catch (JatekStatuszValtasiHiba e) {
			logger.error("Nem megfelelő a játék új státusza a játék feladásakor.");
			logger.debug("A játék feldásakor JatekStatuszValtasiHiba keletkezett: " + e.getMessage());
		}
		visszaállítSzínezés();
		alaphelyzetbeÁllít();
		beállítJátékosNév();
		frissítJátékosNév();
	}
	
	
	@FXML
	private void ment(){
		logger.info("A játékos a játék mentését kezdeményezte.");
		try {
			játékVezérlő.táblaMentés();
			játékVezérlő.játékállásMentés();
		} catch ( SorszamHiba e){
			logger.error("Hibás sorszám a tábla mentésekor!");
			logger.debug("Mentés közben SorszamHiba váltódott ki: " + e.getMessage());
		}catch(OszlopszamHiba e) {
			logger.error("Hibás oszlopszám a tábla mentésekor!");
			logger.debug("Mentés során OszlopszámHiba váltódott ki: " + e.getMessage());
		}catch(IOException e){
			üzenetek.setText("Hiba történt a kimeneti fájl létrehozásakor!");
			logger.error("A mentési fájl létrehozása nem sikerült!");
		}

	}
	
	
	private void betölt(String minősítő){
		
		logger.info("A " +  minősítő + " mentés betöltése...");
		
		try {
			játékVezérlő.betöltTábla(minősítő);
			játékVezérlő.betöltJátékállás(minősítő);
		} catch ( SorszamHiba | OszlopszamHiba | CellaTartalomHiba | CellaTipusValtoztatasiHiba
				| AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
			logger.warn("A mentési fájl hibás!");
			logger.debug("A mentési fájl hibákat tartalmaz: " + e.getMessage());
		}catch (IOException e){
			üzenetek.setText("Hiba a mentés betöltése közben.");
			üzenetek.appendText("\nElőfordulhat, hogy még nincs mentése az adott szintből.");
			logger.error("Nem sikerült a mentés betöltése!");
		}
		
		frissítCellaNézet();
		frissítCellaszám();
		frissítJátékosNév();
	}
	
	
	@FXML
	private void betöltKicsi(){
		logger.info("A játékos a \"Kezdő (4x4)\" játékbetöltő menüpontra kattintott.");
		játékVezérlő.setBetölt();
		újKönnyűJáték();
		betölt("konnyu");
	}
	
	@FXML
	private void betöltKözepes(){
		logger.info("A játékos a \"Átlagos (9x9)\" játékbetöltő menüpontra kattintott.");
		játékVezérlő.setBetölt();
		újNormálJáték();
		betölt("normal");
	}

	
	@FXML
	private void törölCella(){
		
		if(aktCella == null ) return;
		
		try {
			táblaVezérlő.kitöltCella(aktSorszám, aktOszlopszám, "");;
		} catch (CellaTartalomHiba | SorszamHiba | OszlopszamHiba | 
				AzonosErtekASorbanHiba | AzonosErtekAzOszlopbanHiba | AzonosErtekARekeszbenHiba e) {
			üzenetek.setText(e.getMessage());
			logger.warn("Nem sikerült a cella tartalmát kitörölni.");
			logger.debug("A(z) " + "("+ aktSorszám  + ", " + aktOszlopszám +  ")" 
						+ " cella törlése nem sikerült az alábbi hiba miatt: " + e.getMessage());
		}catch(CellaTipusValtoztatasiHiba e){
			logger.debug("A cellatörlés hibára futott: " + e.getRészletesHibaÜzenet());
		}
		
		frissítCellaszám();
		frissítCellaNézet();
		visszaállítSzínezés();
		visszaállítÜzenetek();
		
		jelezKapcsolódóCellák();
		mutatAzonosÉrtékek();
		
		
		
	}
	
	
	@FXML
	private void újrakezd(){
		
		logger.info("A játékos a \"Játék újrakezdése\" gombra kattintott.");
		
		táblaVezérlő.újrakezd();
		
		
		frissítCellaszám();
		frissítCellaNézet();
		visszaállítSzínezés();
		visszaállítÜzenetek();
		mutatAzonosÉrtékek();
		jelezKapcsolódóCellák();
		
		
	}
	
	
	private void visszaállítSzínezés(){
		tábla.getChildren().forEach(c -> c.setStyle("-fx-background-color: white; -fx-border-color: black;"));
		if(kapcsolatok.isSelected()){
			jelezKapcsolódóCellák();
		}
		if(értékek.isSelected()){
			mutatAzonosÉrtékek();
		}
	}
	
	
	@FXML
	private void mutatAzonosÉrtékek(){
		
		frissítCellaNézet();
		
		if(aktCella == null){
			return;
		}
		
		if(értékek.isSelected()){
			
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
				visszaállítSzínezés();
		}
	}
	
	
	@FXML
	private void jelezKapcsolódóCellák(){
		
		frissítCellaNézet();
		
		if(aktCella == null){
			return;
		}

		if(kapcsolatok.isSelected()){
			
			java.util.Set<int[]> cellák = null;
			
			try {
				cellák = táblaVezérlő.kapcsolatbanÁllóCellák(aktSorszám, aktOszlopszám);
			} catch (SorszamHiba | OszlopszamHiba e) {
				üzenetek.setText(e.getMessage());
				logger.warn("Hibás sor vagy oszlopszám a kapcsolódó cellák vizsgálatakor.");
				logger.debug("Kapcsolódó cellák vizsgálata közben az alábbi hiba lépett fel: " + e.getMessage());
			}

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
				visszaállítSzínezés();
		}
			
	}
	
	
	@FXML
	private void névjegy(){
		
		logger.info("A felhasználó a \"Névjegy\" menüpontra kattintott.");
		
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Sudoku névjegye");
		a.setResizable(false);
		a.setHeaderText("Információ");
		a.setContentText("Sudoku version: 1.0");
		a.show();
		
		logger.debug("A névjegyet kiíró dialógus megjelent.");
	}
	
	
	@FXML
	private void sugo(){
		logger.info("A felhasználó a \"Segítség\" menüpontra kattintott.");
		főablak.betöltSúgó(2);
	}
	
	@FXML
	private void vissza(){
		
		logger.info("A felhasználó a \"Vissza a főmenübe\" menüpontra kattintott.");
		
		if(játékVezérlő.fut()){
			try {
				játékVezérlő.befejez();
			} catch (JatekStatuszValtasiHiba e) {
				üzenetek.setText(e.getMessage());
				logger.error("Hiba a főmenübe történő visszalépéskor.");
				logger.debug("Nem lehet visszalépni a főmenübe: " + e.getMessage());
			}
		}
		
		stage.close();
		főablak.mutatAblak();
	}

}
