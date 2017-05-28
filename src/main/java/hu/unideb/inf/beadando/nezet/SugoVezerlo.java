package hu.unideb.inf.beadando.nezet;

import com.sun.prism.paint.Paint;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class SugoVezerlo {

	@FXML
	private TabPane pane;
	
	@FXML
	private Tab szabaly;
	
	@FXML
	private Tab jatek;
	
	@FXML
	private Tab erdmeny;
	
	
	@FXML
	private TextFlow szabály;
	
	@FXML
	private TextFlow játék;
	
	@FXML
	private TextFlow eredmény;
	
	
	@FXML
	private void initialize(){
		
		beállítJátékszabály();
		
		beállítJátékHasználat();
		
		beállítEredmények();
		
	}
	
	
	public void kiválaszt(int lapszám){
		pane.getSelectionModel().clearAndSelect(lapszám - 1);
	}
	
	
	
	private void beállítJátékszabály(){
		
		Text cím = new Text(80,30,"Sudoku játékszabály");
		cím.setFont(Font.font("Verdana", 32));
		cím.setFill(Color.CORNFLOWERBLUE);
		cím.setTextAlignment(TextAlignment.CENTER);
		
		Text térköz = new Text("\n\n");
		térköz.setFont(new Font(Font.getDefault().getName(), 34));
		
		Text leírás = new Text(20,60,getJátékLeírás());
		leírás.setFont(Font.font("Arial", 18));
		leírás.setFill(Color.ORANGERED);
		leírás.setTextAlignment(TextAlignment.JUSTIFY);
		
		szabály.getChildren().add(cím);
		szabály.getChildren().add(térköz);
		szabály.getChildren().add(leírás);
	}
	
	
	private void beállítJátékHasználat(){
		Text cím = new Text(80,30,"Játék nézet használata");
		cím.setFont(Font.font("Verdana", 32));
		cím.setFill(Color.CORNFLOWERBLUE);
		cím.setTextAlignment(TextAlignment.CENTER);
		cím.getStyleClass().add("-fx-font-weight: bold;");
		
		Text térköz = new Text("\n\n");
		térköz.setFont(new Font(Font.getDefault().getName(), 18));
		
		Text leírás = new Text(20,60,getJátékNézetLeírás());
		leírás.setFont(Font.font("Arial", 16));
		leírás.setFill(Color.ORANGERED);
		leírás.setTextAlignment(TextAlignment.JUSTIFY);
		
		játék.getChildren().add(cím);
		játék.getChildren().add(térköz);
		játék.getChildren().add(leírás);
	}
	
	
	private void beállítEredmények(){
		Text cím = new Text(80,30,"Eredmények nézet használata");
		cím.setFont(Font.font("Verdana", 30));
		cím.setFill(Color.CORNFLOWERBLUE);
		cím.setTextAlignment(TextAlignment.CENTER);
		
		Text térköz = new Text("\n\n");
		térköz.setFont(new Font(Font.getDefault().getName(), 16));
		
		Text leírás = new Text(20,60,getEredményNézetLeírás());
		leírás.setFont(Font.font("Arial", 16));
		leírás.setFill(Color.ORANGERED);
		leírás.setTextAlignment(TextAlignment.JUSTIFY);
		
		eredmény.getChildren().add(cím);
		eredmény.getChildren().add(térköz);
		eredmény.getChildren().add(leírás);
	}
	
	

	
	
	private String getJátékLeírás(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("A klasszikus Sudoku játék lényege, hogy kitöltsünk egy 9 sorból és 9 oszlopból álló táblát.\n");
		builder.append("A tábla celláiba számjegyeket írhatunk az 1-től 9-ig terjedő intervallumból.\n");
		builder.append("A játék lényege, hogy a táblát úgy töltsük ki, "
						+ "hogy annak minden sorában, oszlopában és a 3x3-as kis négyzetekben "
						+ "(a játékban a rekesz kifejezést használom rá) "
						+ "minden számjegy csak egyetlen egyszer szerepeljen.");
		
		return builder.toString();
	}
	
	
	private String getJátékNézetLeírás(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("A játék nézetben lehetőségünk van Sudoku feladványokat megfejteni.\n");
		builder.append("A legfontosabb funkciók nem csak a menüsorból érhetőek el,"
				+ " hanem az eszköztárban is helyet kaptak.\n\n");
		builder.append("Ezek a következők:  \n"
						+ " -\tÚj játék indítása\n"
						+ " -\tJáték feladása\n"
						+ " -\tAktuális játék elmentése\n"
						+ " -\tKorábban elmentett játék visszatöltése\n\n");
		builder.append("Új játék kezdése az \"új játék\" lenyíló menü vagy az \"új játék kezdése\" "
						+" menü megnyitásával lehetséges. Mindegyiknél ki kell választani a játéktábla méretét.");
		builder.append("Lehetőség van korábban elmentett játékállás visszatöltésére a \"betöltés\" lenyíló menü,"
						+ "vagy a \"Mentett játék folytatása\" menü megnyitásával."
						+ "Itt is ki kell választani a betölteni kívánt játéktábla méretét.\n\n");
		builder.append(" A játék ezután automatikusan elindul, betöltődik a tábla és a gombsor,"
						+ "majd beállítódik a kitöltendő cellák számlálója.\n");
	    builder.append("A táblázat celláiba numerikus billentyűk vagy a gombsor "
	    				+ "gombjainak segítségével vihetünk be értékeket."
						+ "A program figyeli a hibás értékek bevitelét, "
						+ "melyet a tábla alatti területen jelez.\n\n");
	    builder.append("A gombsorban és a \"Beállítások\" menüben is megtalálható az "
	    				+ "aktuális cella tartalmának kitörlését eredményező \"Cella törlése\" és"
	    				+ " a játékot újra kezdő \"Játék újrakezdése\" opció.\n\n");
	    builder.append("A \"Beállítások\" menüben helyet kapott még kettő darab"
	    				+ "választható opció, melyek segítséget nyújtanak az összefüggések áttekintéséhez.\n");
	    builder.append("Az \"Azonos értékek mutatása\" kijelöl minden olyan cellát a táblában,"
	    				+ " aminek számtartalma megegyezik az aktuális "
	    				+ "( a kurzort tartalmazó) cella tartalmával.\n\n"
	    				+ "A \"Kapcsolódó cellák jelzése\" bekapcsolása esetén "
	    				+ " a program azonos színnel megjeleníti az aktuális cellával"
	    				+ " ugyanazon sorban oszlopban és az ezekhez tartozó rekeszben található cellákat."
	    				+ "(Ez azért segíthet, mert azokba a cellákba már nem kerülhet"
	    				+ " az aktuális cellában szereplő érték.)\n\n");
	    builder.append("A játékot fel is adhatjuk, ha nagyon nem boldogulunk, "
	    				+ "vagy elmenthetjük az adott állást és később folytathatjuk ugyanonnan.\n");
	    builder.append("\n\nKellemes időtöltést és jó játékot kívánok!");
	    
	    
		
		return builder.toString();
	}
	
	
	
	private String getEredményNézetLeírás(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("A táblázatban a korábban lejátszott játékok adatai láthatóak.\n");
		builder.append("Lehetőség van a táblázat adatain szűrést végezni a jobb oldali panel segítségével.\n");
		builder.append("A szűrés folyamán a legördülő menüből kiválaszthatjuk melyik tulajdonság alapján akarunk szűrni. "
						+ "Tudunk a játékosok neve, a tábla nagysága és a játék végállapota alapján keresni.\n");
		builder.append( "A tulajdonság kiválasztása után a keresendő értéket" 
						+ " a legördülő menü mellett elhelyezkedő szövegmezőben adhatjuk meg.\n");
		builder.append("Ha készen vagyunk kattintsunk a \"szűrés\" gombra.\n");
		builder.append("Ha van a feltételeknek eleget tevő adat a táblában akkor csak az fog látszani.\n");
		builder.append("Ha nincs a keresés feltételeinek megfelelő adat "
				       + " akkor a \"Nem található a feltételeknek megfelelő elem.\" felirat jelenik meg a táblában.");
		builder.append("Amennyiben a \"szűrő törlése\" gombra kattint, a keresési feltételek törlődnek"
				+ " és újra a teljes tábla lesz látható.");
		
		return builder.toString();
	}
	
}
