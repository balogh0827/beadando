package hu.unideb.inf.beadando.nezet;

import java.io.IOException;

import hu.unideb.inf.beadando.Kezelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NezetVezerlo extends Application{

	private Stage ablak;
	private String hibaüzenet = "Nem várt hiba történt ";
	
	public void mutatAblak(){
		létrehozFőmenü();
		ablak.show();
	}
	
	public void bezárAbalak(){
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
			ablak.show();
			
			
		} catch (IOException e) {
			String üzenet = hibaüzenet;
			üzenet += "a főmenü létrehozásakor.";
			System.err.println(üzenet);
			System.exit(0);
		}
		
	}
	
	
	public void létrehozJátékNézet(){
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Jatek.fxml"));
		
		try {
			
			AnchorPane pane = (AnchorPane)loader.load();
			JatekNezetVezerlo vezérlő = loader.getController();
			
			vezérlő.setNézetVezérlő(this);
			
			
			Scene sc = new Scene(pane);
			
			ablak.close();
			
			
			Stage játék = new Stage();
			játék.setScene(sc);
			játék.setTitle("Sudoku Játék");
			játék.show();
			
			vezérlő.setSage(játék);
		} catch (Exception e) {
			String üzenet = hibaüzenet;
			üzenet += "a játéknézet létrehozásakor.";
			System.err.println(üzenet);
			e.printStackTrace();
		}
		
	}
	
	
	public void létrehozEredményNézet(){
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Eredmenyek.fxml"));
		
		try {
				
			BorderPane eredmények = (BorderPane) loader.load();
			EredmenyekNezetVezerlo vezérlő = loader.getController();
			Scene sc = new Scene(eredmények);
		
			vezérlő.setNézetVezérlő(this);
			
			ablak.close();
		
			Stage eredmény = new Stage();
			eredmény.setTitle("Eredmények");
			eredmény.setScene(sc);
			eredmény.show();
			
			vezérlő.setSage(eredmény);
		} catch (Exception e) {
			String üzenet = hibaüzenet;
			üzenet += "az eredménynézet létrehozásakor.";
			System.err.println(üzenet);
		}
		
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
			üzenet += "az súgónézet létrehozásakor.";
			System.err.println(üzenet);
		}
		
	}

}
