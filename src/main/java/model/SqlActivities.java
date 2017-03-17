package model;

public class SqlActivities {
	private int id;// 活动编号
	private String name;// 活动名称
	private String photo;// 活动照片
	private int o_id;// 组织者
	private String stime;// 开始时间
	private String etime;// 结束时间
	private String detail;// 活动介绍
	private String sponsor;// 赞助商
	private String state;// 审核状态
	private String signtime;// 报名开始时间
	private String endsigntime;// 报名结束时间
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


	public String getStime() {
		return stime;
	}
	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	
}
