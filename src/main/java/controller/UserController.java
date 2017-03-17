package controller;

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

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	@Autowired
	protected UserService userservice;

	@RequestMapping("login")
	@ResponseBody
	/*
	 * 用户登陆
	 */
	public BaseModel<MiUserInfo> login() {
		BaseModel<MiUserInfo> model = new BaseModel<>();
		JSONObject jsonObject = convertRequestBody();
 		MiUserInfo user = JSONObject.toJavaObject(jsonObject, MiUserInfo.class);
		if(user.getRole().equals("学生")){
			SqlStudent student= userservice.StudentLogin(user);
			if (student==null) {
				model.setMessage("用户名或者密码不正确");
				model.setStatus(Constants.FAIL_INVALID_USER);
				return model;
			}else {
				setApplicationInfo("role","student");
				setApplicationInfo("user", student);
				user.setPassword("");
				user.setName(student.getName());
				user.setIcon(student.getIcon());
			}
		}else if(user.getRole().equals("机构")){
			SqlOrganization organization= userservice.OrganizationLogin(user);
			if (organization==null) {
				model.setMessage("用户名或者密码不正确");
				model.setStatus(Constants.FAIL_INVALID_USER);
				return model;
			}else {
				setApplicationInfo("role","organization");
				setApplicationInfo("user", organization);
				user.setPassword("");
				user.setName(organization.getName());
				user.setIcon(organization.getIcon());
			}
		}else {
			SqlAdmin admin= userservice.AdminLogin(user);
			if (admin==null) {
				model.setMessage("用户名或者密码不正确");
				model.setStatus(Constants.FAIL_INVALID_USER);
				return model;
			}else {
				setApplicationInfo("role","admin");
				setApplicationInfo("user", admin);
				user.setPassword("");
				user.setName(admin.getName());
				user.setIcon(admin.getIcon());
			}
		}
		model.setData(user);
		model.setMessage("登陆成功");
		return model;
	}
	//用户注销
	@RequestMapping("logout")
	@ResponseBody
	public BaseModel<MiUserInfo> logout(){
		JSONObject jsonObject=this.convertRequestBody();
		MiUserInfo logoutUser=JSON.toJavaObject(jsonObject, MiUserInfo.class);
		BaseModel<MiUserInfo> model=new BaseModel<>();
		
		String role=(String)getApplicationInfo("role");
		/*String id=(String)((Object)getApplicationInfo("user").getId());*/
		if(logoutUser.getRole().equals(role)){
			switch(role){
			case "student":{
				SqlStudent student=(SqlStudent) getApplicationInfo("user");
				if(logoutUser.getUserid()==student.getId()){
					setApplicationInfo("role",null);
					setApplicationInfo("user",null);
				}
			};
			case "admin":{
				SqlAdmin admin=(SqlAdmin) getApplicationInfo("user");
				if(logoutUser.getUserid()==admin.getId()){
					setApplicationInfo("role",null);
					setApplicationInfo("user",null);
				}
			};
			case "organization":{
				SqlOrganization organization=(SqlOrganization) getApplicationInfo("user");
				if(logoutUser.getUserid()==organization.getId()){
					setApplicationInfo("role",null);
					setApplicationInfo("user",null);
				}
			};
		}
		}else{
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
		MiUserInfo searchuser = JSON.toJavaObject(jsonObject,MiUserInfo.class);
		BaseModel<List<SqlAdmin>> model = new BaseModel<>();
		List<SqlAdmin> allAdmin = userservice.findAllAdmin(searchuser);
		model.setData(allAdmin);
		model.setPage(new PageInfo<SqlAdmin>(allAdmin));
		return model;
	}
	//修改用户信息
	@RequestMapping("alter")
	@ResponseBody
	public BaseModel<MiUserInfo> alter(){
		BaseModel<MiUserInfo> model=new BaseModel<>();
		JSONObject Json=this.convertRequestBody();
		switch((String)getApplicationInfo("role")){
		case "student":{
			SqlStudent student =JSON.toJavaObject(Json, SqlStudent.class);
			if(!userservice.alterUser("stdent",student)){
				model.setStatus(Constants.FAIL_BUSINESS_ERROR);
				model.setMessage("修改失败");
			};
		};
		case "admin":{
			SqlAdmin admin =JSON.toJavaObject(Json, SqlAdmin.class);
			if(!userservice.alterUser("admin",admin)){
				model.setStatus(Constants.FAIL_BUSINESS_ERROR);
				model.setMessage("修改失败");
			};
		};
		case "organization":{
			SqlOrganization organization=JSON.toJavaObject(Json, SqlOrganization.class);
			if(!userservice.alterUser("stdent",organization)){
				model.setStatus(Constants.FAIL_BUSINESS_ERROR);
				model.setMessage("修改失败");
			};
		};
		};
		return model;
	}

}
