package vo;


import model.SqlOrganization;
import model.SqlState;

public class Activity {
	private String id;// 活动编号
	private String name;// 活动名称
	private String photo;// 活动照片
	private SqlOrganization organization;// 组织者
	private String stime;// 开始时间
	private String etime;// 结束时间
	private String detail;// 活动介绍
	private String sponsor;// 赞助商
	private String signtime;// 报名开始时间
	private String endsigntime;// 报名结束时间
	private String address;//地址
	private SqlState state;
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	private Integer pageNum;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public SqlOrganization getOrganization() {
		return organization;
	}


	public void setOrganization(SqlOrganization organization) {
		this.organization = organization;
	}


	public String getSigntime() {
		return signtime;
	}


	public void setSigntime(String signtime) {
		this.signtime = signtime;
	}


	public String getEndsigntime() {
		return endsigntime;
	}

	public void setEndsigntime(String endsigntime) {
		this.endsigntime = endsigntime;
	}

	public void setState(SqlState state) {
		this.state = state;
	}
}
