package model;

public class SqlStudent {
			private long id;//| 学号   | 头像     | 姓名     | 密码         | 性别    | 电话      | 专业      | 班级      | 学院        | 角色     |
			private String icon; 
			private String name; 
			private String sex;
			private String phone;
			private String major;
			private String classes;
			private String college; 
			private int role;
			private Integer pageNum;
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public String getIcon() {
				return icon;
			}
			public void setIcon(String icon) {
				this.icon = icon;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getSex() {
				return sex;
			}
			public void setSex(String sex) {
				this.sex = sex;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
			public String getMajor() {
				return major;
			}
			public void setMajor(String major) {
				this.major = major;
			}
			public String getClasses() {
				return classes;
			}
			public void setClasses(String classes) {
				this.classes = classes;
			}
			public String getCollege() {
				return college;
			}
			public void setCollege(String college) {
				this.college = college;
			}
			public int getRole() {
				return role;
			}
			public void setRole(int role) {
				this.role = role;
			}
			public Integer getPageNum() {
				return pageNum;
			}
			public void setPageNum(Integer pageNum) {
				this.pageNum = pageNum;
			} 
	

}
