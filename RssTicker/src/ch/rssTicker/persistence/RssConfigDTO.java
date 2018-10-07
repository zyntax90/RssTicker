package ch.rssTicker.persistence;


public class RssConfigDTO {
	
	public int Id;
	public String Name;
	public String Subber;
	public String Url;
	public long Frequency;
	public String Criterias;
	public String MailReceivers;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSubber() {
		return Subber;
	}
	public void setSubber(String subber) {
		Subber = subber;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public long getFrequency() {
		return Frequency;
	}
	public void setFrequency(long frequency) {
		Frequency = frequency;
	}
	public String getCriterias() {
		return Criterias;
	}
	public void setCriterias(String criterias) {
		Criterias = criterias;
	}
	public String getMailReceivers() {
		return MailReceivers;
	}
	public void setMailReceivers(String mailReceivers) {
		MailReceivers = mailReceivers;
	}
}
