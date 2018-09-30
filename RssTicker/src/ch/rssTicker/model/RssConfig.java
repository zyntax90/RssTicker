package ch.rssTicker.model;

import java.util.List;

public class RssConfig {

	private String name;
	private String subber;
	private String url;
	private long frequency;
	private List<String> criterias;

	public RssConfig() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubber() {
		return subber;
	}

	public void setSubber(String subber) {
		this.subber = String.format("[%s]", subber);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getCriterias() {
		return criterias;
	}

	public void addCriteria(String criteria) {
		this.criterias.add(criteria);
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}
}
