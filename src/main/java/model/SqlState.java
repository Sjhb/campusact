package model;

public class SqlState {

	private int state;//状态编号
	private String detail;//状态详情
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
