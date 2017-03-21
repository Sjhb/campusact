package model;

/**
 * Created by wanghuan on 2017/3/21.
 */
//角色表 （权限集合）
public class SqlRole {
    private  long roleId;
    private String detail;
    private String behave;

    public SqlRole() {
    }

    public SqlRole(long roleId, String detail, String behave) {
        this.roleId = roleId;
        this.detail = detail;
        this.behave = behave;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBehave() {
        return behave;
    }

    public void setBehave(String behave) {
        this.behave = behave;
    }
}
