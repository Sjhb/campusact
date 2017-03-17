package controller;

import java.util.List;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import model.LoginUser;
import model.SqlActivities;
import model.SqlOrganization;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import service.ActivitiesService;
import vo.Activities;

@Controller
@RequestMapping("activities")
public class ActivitiesController extends BaseController {
	@Autowired
	protected ActivitiesService activitiesService;
	
	//查看全部审核通过的活动
	@RequestMapping("getall")
	@ResponseBody
	public BaseModel<List<Activities>> getAct(){
		JSONObject jsonObject = this.convertRequestBody();
		Activities searchact= JSON.toJavaObject(jsonObject,Activities.class);
		
		BaseModel<List<Activities>> model = new BaseModel<>();
			List<Activities> allAactivities=null;
			allAactivities= activitiesService.getPassedActivities(searchact);
			model.setData(allAactivities);
			model.setPage(new PageInfo<Activities>(allAactivities));		
		return model;
	}
	//查看待审核通过的活动
		@RequestMapping("checkall")
		@ResponseBody
		public 	BaseModel<List<Activities>> checkAll(){
			JSONObject jsonObject = this.convertRequestBody();
			Activities searchact= JSON.toJavaObject(jsonObject,Activities.class);
			
			BaseModel<List<Activities>> model = new BaseModel<>();
				List<Activities> allAactivities=null;
				allAactivities= activitiesService.getWaitingActivities(searchact);
				model.setData(allAactivities);
				model.setPage(new PageInfo<Activities>(allAactivities));		
			return model;
		}
	//审批活动
	@RequestMapping("checkact")
	@ResponseBody
	public 	BaseModel<SqlActivities> checkAct(){
		JSONObject jsonObject = this.convertRequestBody();
		LoginUser userinfo=(LoginUser)this.getApplicationInfo("user");
	
		SqlActivities activitiesCheck=JSON.toJavaObject(jsonObject,SqlActivities.class);
		BaseModel<SqlActivities> model = new BaseModel<>();
		if(userinfo==null){
			model.setStatus(Constants.FAIL_INVALID_USER);
			model.setMessage("用户名无效");
		}else if(!userinfo.getRole().equals("admin")){
			model.setStatus(Constants.FAIL_INVALID_AUTH);
			model.setMessage("用户无权审批");
		}else{
			if(activitiesService.checkActivities(activitiesCheck)){model.setStatus(Constants.SUCCESS);};
			model.setMessage("活动状态更改成功");
		}
		return model;
	}
	//创建活动
	@RequestMapping("createact")
	@ResponseBody
	public 	BaseModel<List<SqlActivities>> createAct(){
		JSONObject jsonObject = this.convertRequestBody();
		SqlActivities activities= JSON.toJavaObject(jsonObject,SqlActivities.class);
		SqlOrganization userinfo=(SqlOrganization)this.getApplicationInfo("user");
		BaseModel<List<SqlActivities>> model = new BaseModel<>();
		
		if(userinfo==null){
			model.setStatus(Constants.FAIL_INVALID_USER);
			model.setMessage("用户名无效");
		}else if(!userinfo.getRole().equals("organization")){
			model.setStatus(Constants.FAIL_INVALID_AUTH);
			model.setMessage("用户名无权创建活动");
		}else{
			activities.setO_id(userinfo.getId());
			if(activitiesService.createActivities(activities)){	model.setStatus(Constants.SUCCESS);};
			model.setMessage("活动创建成功");
		}
		return model;
	}
	//修改活动
	@RequestMapping("updateact")
	@ResponseBody
	public BaseModel<List<SqlActivities>> updateAct(){
		JSONObject jsonObject = this.convertRequestBody();
		SqlActivities activities= JSON.toJavaObject(jsonObject,SqlActivities.class);
		LoginUser userinfo=(LoginUser)this.getApplicationInfo("user");
		BaseModel<List<SqlActivities>> model = new BaseModel<>();
		
		if(userinfo==null){
			model.setStatus(Constants.FAIL_INVALID_USER);
			model.setMessage("用户名无效");
		}else if(!userinfo.getRole().equals("organization")){
			model.setStatus(Constants.FAIL_INVALID_AUTH);
			model.setMessage("用户名无权修改活动");
		}else{
			if(activitiesService.createActivities(activities)){	model.setStatus(Constants.SUCCESS);};
			model.setMessage("活动修改成功");
		}
		return model;
	}
	//参加活动//取得活动id，加入student的id
	@RequestMapping("engageinact")
	@ResponseBody
	public 	BaseModel<SqlActivities> engageinAct(){
		JSONObject jsonObject = this.convertRequestBody();
		SqlActivities getAct=JSON.toJavaObject(jsonObject,SqlActivities.class);
		SqlStudent getStu=(SqlStudent)this.getApplicationInfo("user");
		BaseModel<SqlActivities> model = new BaseModel<>();
		if(!activitiesService.engageActivities(getStu, getAct))
			{
			model.setMessage("操作失败");
			model.setStatus(Constants.FAIL_BUSINESS_ERROR);
			};
		return model;
	}
	//取消参加活动
	@RequestMapping("cancelEngage")
	@ResponseBody
	public BaseModel<SqlActivities> cancelEngage(){
		JSONObject jsonObject=this.convertRequestBody();
		SqlActivities getAct=JSON.toJavaObject(jsonObject, SqlActivities.class);
		SqlStudent getStu=(SqlStudent)this.getApplicationInfo("user");
		BaseModel<SqlActivities> model=new BaseModel<>();
		if(!activitiesService.cancelEngage(getStu,getAct))
		{
			model.setMessage("操作失败");
			model.setStatus(Constants.FAIL_BUSINESS_ERROR);
			};
		return model;
	}
	//删除活动
	@RequestMapping("deleteAct")
	@ResponseBody
	public BaseModel<SqlActivities> deleteAct(){
		JSONObject jsonobject=this.convertRequestBody();
		SqlActivities getAct=JSON.toJavaObject(jsonobject, SqlActivities.class);
		String role=(String)this.getApplicationInfo("role");
		BaseModel<SqlActivities> model=new BaseModel<>();
		if(role!=null&&role.equals("admin")){
			if(!activitiesService.deleteActivities(getAct))
			{
				model.setMessage("未删除指定的活动");
				model.setStatus(Constants.FAIL_BUSINESS_ERROR);
				};
		}else{
				model.setMessage("无效用户");
				model.setStatus(Constants.FAIL_INVALID_USER);
		}
		return model;
	}
}
