package ch.rssTicker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RssTickerController {

	
	@FXML
	private TextField nameTF;
	@FXML
	private TextField subberTF;
	@FXML
	private TextField urlTF;
	@FXML
	private TextField criteriasTF;
		
	public RssTickerController() {

	}

	@FXML
	public void setConfig() {
		try {
			Parent rootConfig = FXMLLoader.load((getClass().getResource("/ch/rssTicker/view/FrmRssTickerConfig.fxml")));
			Stage stage = new Stage();
			stage.setScene(new Scene(rootConfig));
			stage.show();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
