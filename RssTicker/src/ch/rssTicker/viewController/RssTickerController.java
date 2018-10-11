package ch.rssTicker.viewController;

import java.util.Timer;
import java.util.TimerTask;

import ch.rssTicker.model.RssConfig;
import ch.rssTicker.worker.RssFetcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RssTickerController implements IController {

	private TimerTask rssFetcher;
	private RssConfig rssConfig;

	private Stage rssTickerConfigStage;

	@FXML
	public void setConfig() {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrmRssTickerConfig.fxml"));
			Parent rootConfig = (Parent) fxmlLoader.load();
			RssTickerConfigController rssTickerConfigController = fxmlLoader.<RssTickerConfigController>getController();
			rssTickerConfigController.initData(rssConfig, this);
			rssTickerConfigController.initBindings();
			rssTickerConfigStage = new Stage();
			rssTickerConfigStage.setScene(new Scene(rootConfig));
			rssTickerConfigStage.show();
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

	public void setRssConfig(RssConfig rssConfig) {
		this.rssConfig = rssConfig;
	}

	@Override
	public void closeStage() {
		rssTickerConfigStage.close();
	}
}
