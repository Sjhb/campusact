package model;

public class MiUser {
	private Long userId;// 用户ID

	private String email;// 用户邮箱

	private String phone;// 用户电话

	private String userName;// 昵称

	private String password;// 密码
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String userRealName;// 真实姓名

	private String roleAuth;// 用户角色

	private Long roleId;// 角色ID

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getRoleAuth() {
		return roleAuth;
	}

	public void setRoleAuth(String roleAuth) {
		this.roleAuth = roleAuth;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
