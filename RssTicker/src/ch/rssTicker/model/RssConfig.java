package ch.rssTicker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import ch.rssTicker.persistence.RssConfigDTO;
import ch.rssTicker.persistence.RssTickerConfigRepository;
import javafx.beans.property.LongProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RssConfig {

	private int id;
	private StringProperty name;
	private StringProperty subber;
	private StringProperty url;
	private LongProperty frequency;
	private StringProperty criterias;
	private StringProperty mailReceivers;
	private boolean isStartable;
	private Map<Property<?>, Object> dirtyProperties;

	private RssConfig() {
		name = new SimpleStringProperty();
		subber = new SimpleStringProperty();
		url = new SimpleStringProperty();
		frequency = new SimpleLongProperty();
		mailReceivers = new SimpleStringProperty();
		criterias = new SimpleStringProperty();
		isStartable = false;
		dirtyProperties = new HashMap<>();
	}

	public void clearDirtyList() {
		dirtyProperties.clear();
	}

	public void addToDirtyList(Property<?> property, Object value) {
		dirtyProperties.put(property, value);
	}

	public boolean hasDirtyProperties() {
		return !dirtyProperties.isEmpty();
	}

	public void setDirtyPropertyValues() {
		for (Map.Entry<Property<?>, Object> entry : dirtyProperties.entrySet()) {

			//TODO
			if (entry.getKey().getName().equals(name.getName())) {
				setName(entry.getValue().toString());
			}
		}
	}

	/** Data-Methods **/
	public static ObservableList<RssConfig> get() {
		return fetchData();
	}

	public void save() {
		saveData();
	}

	private static ObservableList<RssConfig> fetchData() {
		ObservableList<RssConfig> rssConfigList = FXCollections.observableArrayList();
		List<RssConfigDTO> rssConfigDTOList = RssTickerConfigRepository.get();

		for (RssConfigDTO part : rssConfigDTOList) {
			RssConfig rssConfig = new RssConfig();
			rssConfig.setId(part.getId());
			rssConfig.setName(part.getName());
			rssConfig.setFrequency(part.getFrequency());
			rssConfig.setSubber(part.getSubber());
			rssConfig.setCriterias(part.getCriterias());
			rssConfig.setMailReceivers(part.getMailReceivers());
			rssConfig.setUrl(part.getUrl());
			rssConfigList.add(rssConfig);
		}

		if (rssConfigList.size() < 1)
			rssConfigList.add(new RssConfig());

		return rssConfigList;
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

	public boolean isStartable() {
		return url.get() != null && !url.get().isEmpty() && isStartable;
	}
}
