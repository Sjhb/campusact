package vo;


import model.SqlOrganization;
import model.SqlState;

import java.util.Date;


public class Activity {
	private Long id;// 活动编号
	private String name;// 活动名称
	private String photo;// 活动照片
	private SqlOrganization organization;// 组织者
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String detail;// 活动介绍
	private String sponsor;// 赞助商
	private String signTime;// 报名开始时间
	private Date endSignTime;// 报名结束时间
	private String address;//地址
	private SqlState state;
	public String getAddress() {
		return address;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private Integer pageNum;

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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public Date getEndSignTime() {
		return endSignTime;
	}

	public void setEndSignTime(Date endSignTime) {
		this.endSignTime = endSignTime;
	}

	public SqlState getState() {
		return state;
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

	public void setState(SqlState state) {
		this.state = state;
	}
}
