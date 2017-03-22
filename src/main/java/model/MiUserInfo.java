package model;

//登陆传回信息
public class MiUserInfo {
	private String role;
	private long userid;
	private String name;
	private String password;
	private String icon;
	private Integer pageNum;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public MiUserInfo() {
	}

	public MiUserInfo(String role, long userid, String name, String password, String icon, Integer pageNum) {
		this.role = role;
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.icon = icon;
		this.pageNum = pageNum;
	}
}
