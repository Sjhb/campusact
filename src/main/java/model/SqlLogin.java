package model;

/**
 * Created by wanghuan on 2017/3/22.
 */
public class SqlLogin {
    private long id;
    private String password;
    private int role;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
