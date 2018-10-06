package ch.rssTicker.viewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

	public static void main(String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("FrmRssTicker.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("RssTorrentTicker");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();

	}

}
