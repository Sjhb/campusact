package controller;

import java.io.IOException;
import java.util.List;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import model.MiUserInfo;
import model.SqlAdmin;
import model.SqlOrganization;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import service.UserService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    protected UserService userservice;

    @RequestMapping("test")
    @ResponseBody
    public void test() {
        MiUserInfo mi = new MiUserInfo();
        mi.setId(100000000000L);
        mi.setPassword("10000000000");
        Object test = userservice.userLogin(mi);
        System.out.print(test);
    }

    @RequestMapping("login")
    @ResponseBody
    /*
	 * 用户登陆
	 */
    public BaseModel<MiUserInfo> login() {
        BaseModel<MiUserInfo> model = new BaseModel<>();
        JSONObject jsonObject = convertRequestBody();
        MiUserInfo user = JSONObject.toJavaObject(jsonObject, MiUserInfo.class);
        MiUserInfo loginuser = (MiUserInfo) userservice.userLogin(user);
        if (loginuser == null) {
            model.setMessage("用户名或者密码不正确");
            model.setStatus(Constants.FAIL_INVALID_USER);
            return model;
        }
            setApplicationInfo("role", loginuser.getRole().getDetail());
            setApplicationInfo("user", loginuser);
            user.setRole(loginuser.getRole());
            user.setPassword("");
            user.setName(loginuser.getName());
            user.setIcon(loginuser.getIcon());
        model.setData(user);
        model.setMessage("登陆成功");
        return model;
    }

    //用户注销
    @RequestMapping("logout")
    @ResponseBody
    public BaseModel<MiUserInfo> logout() {
        JSONObject jsonObject = this.convertRequestBody();
        MiUserInfo logoutUser = JSON.toJavaObject(jsonObject, MiUserInfo.class);
        BaseModel<MiUserInfo> model = new BaseModel<>();

        String role = (String) getApplicationInfo("role");
		/*String id=(String)((Object)getApplicationInfo("user").getId());*/
        if (logoutUser.getRole().equals(role)) {
            switch (role) {
                case "student": {
                    SqlStudent student = (SqlStudent) getApplicationInfo("user");
                    if (logoutUser.getId() == student.getId()) {
                        setApplicationInfo("role", null);
                        setApplicationInfo("user", null);
                    }
                }
                ;
                case "admin": {
                    SqlAdmin admin = (SqlAdmin) getApplicationInfo("user");
                    if (logoutUser.getId() == admin.getId()) {
                        setApplicationInfo("role", null);
                        setApplicationInfo("user", null);
                    }
                }
                ;
                case "organization": {
                    SqlOrganization organization = (SqlOrganization) getApplicationInfo("user");
                    if (logoutUser.getId() == organization.getId()) {
                        setApplicationInfo("role", null);
                        setApplicationInfo("user", null);
                    }
                }
                ;
            }
        } else {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("用户信息不一致");
        }
        return model;

    }

    /*
     * 显示所有用户
     */
    @RequestMapping("search")
    @ResponseBody
    public BaseModel<List<SqlAdmin>> search() {
        JSONObject jsonObject = this.convertRequestBody();
        MiUserInfo searchuser = JSON.toJavaObject(jsonObject, MiUserInfo.class);
        BaseModel<List<SqlAdmin>> model = new BaseModel<>();
        List<SqlAdmin> allAdmin = userservice.findAllAdmin(searchuser);
        model.setData(allAdmin);
        model.setPage(new PageInfo<SqlAdmin>(allAdmin));
        return model;
    }

    //修改用户信息
    @RequestMapping("alter")
    @ResponseBody
    public BaseModel<MiUserInfo> alter() {
        BaseModel<MiUserInfo> model = new BaseModel<>();
        JSONObject Json = this.convertRequestBody();
        switch ((String) getApplicationInfo("role")) {
            case "student": {
                SqlStudent student = JSON.toJavaObject(Json, SqlStudent.class);
                if (!userservice.alterUser("stdent", student)) {
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("修改失败");
                }
                ;
            }
            ;
            case "admin": {
                SqlAdmin admin = JSON.toJavaObject(Json, SqlAdmin.class);
                if (!userservice.alterUser("admin", admin)) {
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("修改失败");
                }
                ;
            }
            ;
            case "organization": {
                SqlOrganization organization = JSON.toJavaObject(Json, SqlOrganization.class);
                if (!userservice.alterUser("stdent", organization)) {
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("修改失败");
                }
                ;
            }
            ;
        }
        ;
        return model;
    }

}
