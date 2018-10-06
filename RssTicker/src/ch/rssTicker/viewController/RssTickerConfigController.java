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
	
	public void initialize() {
		rssConfig = new RssConfig();
		//rssUrlTF.textProperty().bindBidirectional(rssConfig.getUrl());
		nameTF.textProperty().bindBidirectional(rssConfig.getName());
		//subberTF.textProperty().bindBidirectional(rssConfig.getSubber());
		frequencySL.valueProperty().bindBidirectional(rssConfig.getFrequency());
		mailReceiversTF.textProperty().bindBidirectional(rssConfig.getMailReceivers());
	}
	
	@FXML
	public void confirm() {
		System.out.println(rssConfig.getName().toString());
		System.out.println(rssConfig.getFrequency());
		System.out.println(rssConfig.getMailReceivers());
		
	
	}
	
	@FXML
	public void cancel() {
		
	}
	
}
