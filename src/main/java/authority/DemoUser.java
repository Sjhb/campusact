package authority;

import java.util.List;

/**
 * Created by wanghuan on 2017/3/14.
 */

//系统用户
public class DemoUser {
    private Long id;
    private String name;
    private String password;
    private List<String> role;


    //构造方法
    public DemoUser(){}

    public DemoUser(Long id,String name,String Password){
        this.id=id;
        this.name=name;
        this.password=Password;
    }

    public DemoUser(Long id,String name,String Password,List<String> role){
        this.id=id;
        this.name=name;
        this.password=Password;
        this.role=role;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
