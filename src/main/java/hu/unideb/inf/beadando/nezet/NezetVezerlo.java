package hu.unideb.inf.beadando.nezet;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.*;


public class NezetVezerlo extends Application{

	private Stage ablak;
	private final String hibaüzenet = "Nem várt hiba történt ";
	
	private static Logger logger = LoggerFactory.getLogger(NezetVezerlo.class);
	
	public void mutatAblak(){
		ablak.show();
	}
	
	public void elrejtAblak(){
		ablak.hide();
	}
	
	public void bezárAblak(){
		logger.info("Kilépés.");
		ablak.close();
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		ablak = primaryStage;
		ablak.setTitle("Sudoku Főmenü");
		létrehozFőmenü();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	private void létrehozFőmenü(){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Fomenu.fxml"));
		
		try {
			AnchorPane főmenü = (AnchorPane)loader.load();
			FomenuController controller = loader.getController();
			controller.setNézetVezérlő(this);
			Scene scene = new Scene(főmenü);
			ablak.setScene(scene);
			ablak.setResizable(false);
			mutatAblak();
			
		} catch (Exception e) {
			String üzenet = hibaüzenet;
			üzenet += "a főmenü létrehozásakor.\n\t";
			üzenet += "Az alkalmazás nem tudja folytatni a működését, ezért leáll.";
			logger.error(üzenet);
			e.printStackTrace();
			System.exit(0);
		}
		
		logger.info("A főmenü sikeresen létrejött.");
	}
	
	
	public void létrehozJátékNézet(){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Jatek.fxml"));
		
		try {
			
			AnchorPane pane = (AnchorPane)loader.load();
			JatekNezetVezerlo vezérlő = loader.getController();
			vezérlő.setNézetVezérlő(this);
			Scene sc = new Scene(pane);
			elrejtAblak();
			Stage játék = new Stage();
			játék.setScene(sc);
			játék.setTitle("Sudoku Játék");
			játék.show();
			vezérlő.setSage(játék);
		} catch (Exception e) {
			String üzenet = hibaüzenet;
			üzenet += "a játéknézet létrehozásakor.\n\t";
			üzenet += e.getMessage();
			logger.error(üzenet);
			return;
		}
		
		logger.info("A játéknézet sikeresen létrejött.");
		
	}
	
	
	public void létrehozEredményNézet(){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Eredmenyek.fxml"));
		
		try {
				
			BorderPane eredmények = (BorderPane) loader.load();
			EredmenyekNezetVezerlo vezérlő = loader.getController();
			Scene sc = new Scene(eredmények);
			vezérlő.setNézetVezérlő(this);
			elrejtAblak();
			Stage eredmény = new Stage();
			eredmény.setTitle("Eredmények");
			eredmény.setScene(sc);
			eredmény.show();
			vezérlő.setSage(eredmény);
		} catch (Exception e) {
			String üzenet = hibaüzenet;
			üzenet += "az eredménynézet létrehozásakor.\n\t";
			üzenet += e.getMessage();
			logger.error(üzenet);
			return;
		}
		
		logger.info("Az eredménynézet sikeresen létrejött.");
	}
	
	
	public void betöltSúgó(int lapszám){
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Sugo.fxml"));
		
		try{
			
			AnchorPane súgó = (AnchorPane)loader.load();
			SugoVezerlo vezérlő = loader.getController();
			vezérlő.kiválaszt(lapszám);
			Scene tartalom = new Scene(súgó);
			Stage segítség = new Stage();
			segítség.setTitle("Súgó");
			segítség.setScene(tartalom);
			segítség.show();
			
		}catch(Exception e){
			String üzenet = hibaüzenet;
			üzenet += "a súgónézet létrehozásakor.\n\t";
			üzenet += e.getMessage();
			logger.error(üzenet);
			return;
		}
		
		logger.info("A súgónézet sikeresen létrejött.");
		
	}

}
