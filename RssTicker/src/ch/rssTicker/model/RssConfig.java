package ch.rssTicker.model;

import ch.rssTicker.persistence.RssConfigDTO;
import ch.rssTicker.persistence.RssTickerConfigRepository;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RssConfig {

	private int id;
	private StringProperty name;
	private StringProperty subber;
	private StringProperty url;
	private LongProperty frequency;
	private StringProperty criterias;
	private StringProperty mailReceivers;

	private boolean isDirty;

	private RssConfig() {
		name = new SimpleStringProperty();
		subber = new SimpleStringProperty();
		url = new SimpleStringProperty();
		frequency = new SimpleLongProperty();
		mailReceivers = new SimpleStringProperty();
		criterias = new SimpleStringProperty();
		isDirty = false;
	}

	public static RssConfig get() {

		return fetchData();
	}

	public void save() {
		saveData();
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public boolean isDirty() {
		return isDirty;
	}

	private static RssConfig fetchData() {

		RssConfigDTO rssConfigDTO = RssTickerConfigRepository.get();
		if (rssConfigDTO == null)
			return new RssConfig();

		RssConfig rssConfig = new RssConfig();
		rssConfig.setId(rssConfigDTO.getId());
		rssConfig.setName(rssConfigDTO.getName());
		rssConfig.setFrequency(rssConfigDTO.getFrequency());
		rssConfig.setSubber(rssConfigDTO.getSubber());
		rssConfig.setCriterias(rssConfigDTO.getCriterias());
		rssConfig.setMailReceivers(rssConfigDTO.getMailReceivers());
		rssConfig.setUrl(rssConfigDTO.getUrl());

		return rssConfig;
	}

	private void saveData() {
		RssConfigDTO rssConfigDTO = new RssConfigDTO();
		rssConfigDTO.setId(getId());
		rssConfigDTO.setName(getName());
		rssConfigDTO.setSubber(getSubber());
		rssConfigDTO.setUrl(getUrl());
		rssConfigDTO.setCriterias(getCriterias());
		rssConfigDTO.setMailReceivers(getMailReceivers());
		rssConfigDTO.setFrequency(getFrequency());
	}

	/** BoilerPlate **/
	public StringProperty getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getName() {
		return this.name.get();
	}

	public StringProperty getSubberProperty() {
		return subber;
	}

	public void setSubber(String subber) {
		this.subber.set(String.format("[%s]", subber));
	}

	public String getSubber() {
		return this.subber.get();
	}

	public StringProperty getUrlProperty() {
		return url;
	}

	public void setUrl(String url) {
		this.url.set(url);
	}

	public String getUrl() {
		return this.url.get();
	}

	public LongProperty getFrequencyProperty() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency.set(frequency);
	}

	public long getFrequency() {
		return this.frequency.get();
	}

	public void setMailReceivers(String receiver) {
		this.mailReceivers.set(receiver);
	}

	public StringProperty getMailReceiversProperty() {
		return this.mailReceivers;
	}

	public String getMailReceivers() {
		return this.mailReceivers.get();

	}

	public void setCriterias(String criteria) {
		this.criterias.set(criteria);
	}

	public StringProperty getCriteriasProperty() {
		return this.criterias;
	}

	public String getCriterias() {
		return this.criterias.get();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
