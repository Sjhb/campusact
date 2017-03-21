package model;

public class SqlActivity {
	private int id;// 活动编号
	private String name;// 活动名称
	private String photo;// 活动照片
	private long organizationId;// 组织者
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private String detail;// 活动介绍
	private String sponsor;// 赞助商
	private int stateId;// 审核状态
	private String signTime;// 报名开始时间
	private String endSignTime;// 报名结束时间
	private String address;//地址
	private Integer pageNum;

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(int id) {
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

	public String getStartTime() {

		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getEndSignTime() {
		return endSignTime;
	}

	public void setEndSignTime(String endSignTime) {
		this.endSignTime = endSignTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signtime) {
		this.signTime = signtime;
	}


}
