package bussiness;


import javafx.scene.image.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(
					getClass().getResource(
							"/presentation/GUIViewRegistry.fxml")
					);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image image = new Image(getClass().getResource("/Imagen/icono.png").toString());
			primaryStage.getIcons().add(image);
			primaryStage.setTitle("Gestor de Vuelos Reservación y Administración de Tiquetes");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		launch(args);
	}
}
