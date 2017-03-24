package sqlInters;

import model.MiUserInfo;

import java.util.List;

/**
 * Created by wanghuan on 2017/3/23.
 */
public interface UserOperation {
//        用户登陆
    public List<MiUserInfo> userLogin(MiUserInfo user);
}
