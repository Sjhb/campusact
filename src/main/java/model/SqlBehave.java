package model;
//行为表
public class SqlBehave {

	private long id;//id
	private String behave;//行为
	private String description ;//行为描述

	public SqlBehave() {
	}
	public SqlBehave(long id, String behave, String description) {
		this.id = id;
		this.behave = behave;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBehave() {
		return behave;
	}

	public void setBehave(String behave) {
		this.behave = behave;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
