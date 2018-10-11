package ch.rssTicker.viewController;

import java.util.Optional;

import ch.rssTicker.model.RssConfig;
import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

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

	private ButtonType dialogYesBtn = new ButtonType("Yes",ButtonData.YES);
	private ButtonType dialogNoBtn = new ButtonType("No",ButtonData.CANCEL_CLOSE);
	
	private RssConfig rssConfig;
	private IController parentController;

	@FXML
	public void confirm() {

		if (rssConfig.isDirty()) {
			rssConfig.setName(nameTF.textProperty().getValue());
			rssConfig.setDirty(false);
		}
		this.parentController.closeStage();
	}

	@FXML
	public void cancel() {
		if (rssConfig.isDirty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("RssTickerConfig");
			alert.setHeaderText("Unsaved config changes");
			alert.setContentText("There are unsaved changes. Continue without saving?");
			alert.getButtonTypes().setAll(dialogNoBtn,dialogYesBtn);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				rssConfig.setDirty(false);
				this.parentController.closeStage();
			}
		} else {
			rssConfig.setDirty(false);
			this.parentController.closeStage();
		}
	}

	public void initData(RssConfig rssConfig, IController parentController) {
		this.rssConfig = rssConfig;
		this.parentController = parentController;
		nameTF.textProperty().setValue(this.rssConfig.getName());
	}

	public void initListeners() {

		rssUrlTF.textProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getUrlProperty(), oldValue, newValue));
		nameTF.textProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getNameProperty(), oldValue, newValue));
		subberTF.textProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getSubberProperty(), oldValue, newValue));
		frequencySL.valueProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getFrequencyProperty(), oldValue, newValue));
		mailReceiversTF.textProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getMailReceiversProperty(), oldValue, newValue));
		criteriasTF.textProperty().addListener(
				(obsValue, oldValue, newValue) -> validate(rssConfig.getCriteriasProperty(), oldValue, newValue));
	}

	private void validate(StringProperty property, String oldValue, String newValue) {
		if (oldValue != null && oldValue.equals(newValue))
			return;
		rssConfig.setDirty(true);
	}

	private void validate(LongProperty property, Number oldValue, Number newValue) {
		if (oldValue != null || oldValue == newValue)
			return;
		rssConfig.setDirty(true);
	}
}
