package model;

//登陆传回信息
public class MiUserInfo {
	private SqlRole role;
	private Long id;
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
	public long getId() {
		return id;
	}
	public void setId(long userid) {
		this.id = userid;
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
	public SqlRole getRole() {
		return role;
	}
	public void setRole(SqlRole role) {
		this.role = role;
	}
	public MiUserInfo() { }
	public MiUserInfo(SqlRole role, long userid, String name, String password, String icon, Integer pageNum) {
		this.role = role;
		this.id = userid;
		this.name = name;
		this.password = password;
		this.icon = icon;
		this.pageNum = pageNum;
	}
}
