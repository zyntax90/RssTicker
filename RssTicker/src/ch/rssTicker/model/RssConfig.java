package ch.rssTicker.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RssConfig {

	private StringProperty name;
	private StringProperty subber;
	private StringProperty url;
	private LongProperty frequency;
	private StringProperty criterias;
	private StringProperty mailReceivers;

	public RssConfig() {
		name = new SimpleStringProperty();
		subber = new SimpleStringProperty();
		url = new SimpleStringProperty();
		frequency = new SimpleLongProperty();
		mailReceivers = new SimpleStringProperty();
		criterias = new SimpleStringProperty();
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty getSubber() {
		return subber;
	}

	public void setSubber(String subber) {
		this.subber.set(String.format("[%s]", subber));
	}

	public StringProperty getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url.set(url);
	}

	public LongProperty getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency.set(frequency);
	}

	public void addMailReceiver(String receiver) {
		this.mailReceivers.set(String.format("%sASDASD%s", receiver,this.mailReceivers.get()));
	}

	public StringProperty getMailReceivers() {
		return this.mailReceivers;
	}

}
