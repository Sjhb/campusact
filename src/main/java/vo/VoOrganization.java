package vo;

import model.MiUserInfo;
import model.SqlRole;

/**
 * Created by wanghuan on 2017/3/23.
 */
public class VoOrganization extends MiUserInfo {
    //| 编号   | 组织头像   | 名称          | 电话       | 邮箱      | 详情     | 地址        | 角色     |
    private String detail;
    private String phone;
    private String mail;
    private String address;
    private SqlRole role;
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public SqlRole getRole() {
        return role;
    }
    public void setRole(SqlRole role) {
        this.role = role;
    }

}
