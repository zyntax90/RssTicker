package ch.rssTicker.viewController;


import com.sun.javafx.application.LauncherImpl;

import ch.rssTicker.model.RssConfig;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Starter extends Application {

	private ObservableList<RssConfig> rssConfigList;

	public static void main(String[] args) {
		LauncherImpl.launchApplication(Starter.class, CustomSplashScreen.class, args);
	}

	@Override
	public void init() throws Exception {
		try {
			rssConfigList = RssConfig.get();
		} catch (Exception e) {
			System.out.println("Error while loading data " + e);
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrmRssTicker.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		RssTickerController rssTickerController = fxmlLoader.<RssTickerController>getController();
		rssTickerController.setRssConfig(rssConfigList);
		rssTickerController.initLayout();
		Scene scene = new Scene(root);
		primaryStage.setTitle("RssTorrentTicker");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
