package vo;


import model.SqlOrganization;
import model.SqlState;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//实现相关属性 get 返回 string
public class Activity {
    private Long id;// 活动编号
    private String name;// 活动名称
    private String photo;// 活动照片
    private SqlOrganization organization;// 组织者
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间
    private String detail;// 活动介绍
    private String sponsor;// 赞助商
    private Date signTime;// 报名开始时间
    private Date endSignTime;// 报名结束时间
    private String address;//地址
    private SqlState state;
    private String engage;//活动参与

    public String getEngage() {
        return engage;
    }

    public void setEngage(String engage) {
        this.engage = engage;
    }

    public String getAddress() {
        return address;
    }

    public String getStartTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSignTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(signTime);
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
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


    public String getEndSignTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endSignTime);
    }

    public void setEndSignTime(Date endSignTime) {
        this.endSignTime = endSignTime;
    }

    public SqlState getState() {
        return state;
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
    public List<Date> getTime(){
        List<Date> times=new ArrayList<Date>();
        times.add(signTime);
        times.add(endSignTime);
        times.add(startTime);
        times.add(endTime);
        return times;
    }
}
