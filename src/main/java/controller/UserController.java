package controller;

import java.io.IOException;
import java.util.List;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import constant.Field;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import service.StudentService;
import service.UserService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    protected UserService userservice;
    @Autowired
    protected StudentService studentService;
    //设置文件读取路径
    @Value("#{propertiesReader['ORGANIZATION_ICON']}")
    private String ORGANIZATION_ICON;
    @Value("#{propertiesReader['STUDENT_ICON']}")
    private String STUDENT_ICON;
    @Value("#{propertiesReader['ADMINISTOR_ICON']}")
    private String ADMINISTOR_ICON;


    @RequestMapping("test")
    @ResponseBody
    public void test() {
        MiUser test = (MiUser) this.getObject(new MiUser());
        test.getId();
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
//        传回MiUserInfo对象
        model.setData(user);
        model.setMessage("登陆成功");
        return model;
    }

    //用户注销
    @RequestMapping("logout")
    @ResponseBody
    public BaseModel<MiUserInfo> logout() {
        BaseModel<MiUserInfo> model = new BaseModel<>();
        setApplicationInfo("role", null);
        setApplicationInfo("user", null);
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

    /*
     * 获取单个用户
     */
    @RequestMapping("getStu")
    @ResponseBody
    public BaseModel<SqlStudent> getStu() {
        BaseModel<SqlStudent> model = new BaseModel<>();
        if (!this.isLogin()) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("未登录");
            return model;
        }
        long sId=this.getUserInfo();
        SqlStudent restu=studentService.getStuById(sId);
        if(restu==null){
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("未知错误");
        }else {
            model.setData(restu);
        }
        return model;
    }
    /*
      * 获取单个用户
      */
    @RequestMapping("requestResetPass")
    @ResponseBody
    public BaseModel<SqlStudent> requestResetPass() {
        BaseModel<SqlStudent> model = new BaseModel<>();
        MiUserInfo miinfo= (MiUserInfo) this.getObject(new MiUserInfo());
        int re=userservice.requestResetPass(miinfo);
        if(re==0){model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("操作失败，可能是未找到用户。");
        }else {
            model.setMessage("操作成功，管理员会在几个工作日内完成重置。");
        }
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
            }
            case "admin": {
                SqlAdmin admin = JSON.toJavaObject(Json, SqlAdmin.class);
                if (!userservice.alterUser("admin", admin)) {
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("修改失败");
                }
            }
            case "organization": {
                SqlOrganization organization = JSON.toJavaObject(Json, SqlOrganization.class);
                if (!userservice.alterUser("stdent", organization)) {
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("修改失败");
                }
            }
        }
        return model;
    }

    //获取用户头像
    @RequestMapping(value = "getIcon", method = RequestMethod.GET)
    @ResponseBody
    public void getIcon(@RequestParam(value = "role", required = false) String role, @RequestParam(value = "icon", required = false) String filename, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "image/jpeg");//设置响应的媒体类型，这样浏览器会识别出响应的是图片
        byte[] image;
        switch (role) {
            case Field.STUDENT:
                image = this.getPicture(STUDENT_ICON + filename);
                break;
            case Field.ADMINISTOR:
                image = this.getPicture(ADMINISTOR_ICON + filename);
                break;
            case Field.ORGANIZATION:
                image = this.getPicture(ORGANIZATION_ICON + filename);
                break;
            default:
                image = null;
        }
        response.getOutputStream().write(image);
    }
}
