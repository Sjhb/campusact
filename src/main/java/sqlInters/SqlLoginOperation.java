package sqlInters;

import model.MiUserInfo;
import model.SqlLogin;

import java.util.List;

/**
 * Created by wanghuan on 2017/3/22.
 */
public interface SqlLoginOperation {
    public List<SqlLogin> selectLoginUser(MiUserInfo user);//根据用户名密码查找用户
}
