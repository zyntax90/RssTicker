package ch.rssTicker.viewController;

import ch.rssTicker.model.RssConfig;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class RssTickerConfigController {

	@FXML
	private TextField rssUrlTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField subberTF;
	@FXML
	private Slider frequencySL;
	@FXML
	private TextField mailReceiversTF;
	@FXML
	private TextField criteriasTF;

	private RssConfig rssConfig;
	private IController parentController;

	@FXML
	public void confirm() {
		System.out.println(rssConfig.getName());
		System.out.println(rssConfig.getFrequency());
		System.out.println(rssConfig.getMailReceivers());
	}

	@FXML
	public void cancel() {
		this.parentController.closeStage();
	}

	public void initData(RssConfig rssConfig, IController parentController) {
		this.rssConfig = rssConfig;
		this.parentController = parentController;
	}

	public void initBindings() {
		rssUrlTF.textProperty().bindBidirectional(rssConfig.getUrlProperty());
		nameTF.textProperty().bindBidirectional(rssConfig.getNameProperty());
		subberTF.textProperty().bindBidirectional(rssConfig.getSubberProperty());
		frequencySL.valueProperty().bindBidirectional(rssConfig.getFrequencyProperty());
		mailReceiversTF.textProperty().bindBidirectional(rssConfig.getMailReceiversProperty());
		criteriasTF.textProperty().bindBidirectional(rssConfig.getCriteriasProperty());
	}

}
