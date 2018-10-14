package ch.rssTicker.viewController;

import java.util.Timer;
import java.util.TimerTask;

import ch.rssTicker.model.RssConfig;
import ch.rssTicker.worker.RssFetcher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RssTickerController implements IController {

	@FXML
	private GridPane rssConfigGP;

	private TimerTask rssFetcher;
	private ObservableList<RssConfig> rssConfigList;
	private Stage rssTickerConfigStage;

	public void setConfig(RssConfig selectedRssConfig) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrmRssTickerConfig.fxml"));
			Parent rootConfig = (Parent) fxmlLoader.load();
			RssTickerConfigController rssTickerConfigController = fxmlLoader.<RssTickerConfigController>getController();
			rssTickerConfigController.initData(selectedRssConfig, this);
			rssTickerConfigController.initListeners();
			rssTickerConfigStage = new Stage();
			rssTickerConfigStage.setScene(new Scene(rootConfig));
			rssTickerConfigStage.show();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void startRssTicker(RssConfig selectedRssConfig) {

		rssFetcher = new RssFetcher(selectedRssConfig.getUrl());
		Timer timer = new Timer();
		timer.schedule(rssFetcher, selectedRssConfig.getFrequency());
	}

	public void initLayout() {

		for (int i = 0; i < rssConfigList.size(); i++) {

			Button startBtn = new Button("Start");
			Button configBtn = new Button("Config");
			Label stateLbl = new Label("state");
			Label nameLbl = new Label(rssConfigList.get(i).getName());

			final RssConfig d = rssConfigList.get(i);
			startBtn.setOnAction(e -> startRssTicker(d));
			configBtn.setOnAction(e -> setConfig(d));

			rssConfigGP.add(stateLbl, 0, i + 1);
			rssConfigGP.add(nameLbl, 1, i + 1);
			rssConfigGP.add(startBtn, 2, i + 1);
			rssConfigGP.add(configBtn, 3, i + 1);
		}
	}

	public void setRssConfig(ObservableList<RssConfig> rssConfigList) {
		this.rssConfigList = rssConfigList;
	}

	@Override
	public void closeStage() {
		rssTickerConfigStage.close();
	}
}
