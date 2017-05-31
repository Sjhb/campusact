package vo;

import model.MiUserInfo;
import model.SqlRole;

/**
 * Created by wanghuan on 2017/3/23.
 */
public class VoStudent extends MiUserInfo {
    //| 学号   | 头像     | 姓名     | 密码         | 性别    | 电话      | 专业      | 班级      | 学院        | 角色     |
    private long id;
    private String sex;
    private String phone;
    private String major;
    private String classes;
    private String college;
    private SqlRole role;
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
    public SqlRole getRole() {
        return role;
    }
    public void setRole(SqlRole role) {
        this.role = role;
    }
}
