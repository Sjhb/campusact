package vo;

import model.SqlRole;

public class User {
	private Long id;
	private String name;
	private String password;
	private SqlRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SqlRole getRole() {
		return role;
	}

	public void setRole(SqlRole role) {
		this.role = role;
	}

	private Integer pageNum;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

}
