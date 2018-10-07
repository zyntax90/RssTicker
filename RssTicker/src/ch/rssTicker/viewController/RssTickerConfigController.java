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
	
	public RssTickerConfigController(RssConfig rssConfig) {
//		if (rssConfig != null)
//			rssConfig = new RssConfig();
	}
	
	public void initialize() {
		//rssUrlTF.textProperty().bindBidirectional(rssConfig.getUrl());
		nameTF.textProperty().bindBidirectional(rssConfig.getNameProperty());
		//subberTF.textProperty().bindBidirectional(rssConfig.getSubber());
		frequencySL.valueProperty().bindBidirectional(rssConfig.getFrequencyProperty());
		mailReceiversTF.textProperty().bindBidirectional(rssConfig.getMailReceiversProperty());
	}
	
	@FXML
	public void confirm() {
		System.out.println(rssConfig.getName());
		System.out.println(rssConfig.getFrequency());
		System.out.println(rssConfig.getMailReceivers());
	}
	
	@FXML
	public void cancel() {
		
	}
	
}
