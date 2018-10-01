package ch.rssTicker.controller;

import java.util.Timer;
import java.util.TimerTask;

import ch.rssTicker.model.RssConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RssTickerController {

	private TimerTask rssFetcher;
	private RssConfig rssConfig;

	public void initialize() {

		rssConfig = new RssConfig();
		rssConfig.setUrl("https://nyaa.si/?page=rss");
		rssConfig.setSubber("HorribleSubs");

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

	@FXML
	public void startRssTicker() {

		rssFetcher = new RssFetcher(rssConfig.getUrl());
		Timer timer = new Timer();
		timer.schedule(rssFetcher, rssConfig.getFrequency());
	}
}
