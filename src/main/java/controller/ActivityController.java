package controller;

import java.io.IOException;
import java.util.List;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import constant.field;
import model.LoginUser;
import model.SqlActivity;
import model.SqlOrganization;
import model.SqlStudent;
import org.omg.CORBA.ACTIVITY_COMPLETED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import service.ActivityService;
import vo.Activity;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("activity")
public class ActivityController extends BaseController {
    @Autowired
    protected ActivityService activityService;
    @Value("#{propertiesReader['ACTIVITY_IMG']}")
    private String ACTIVITY_IMG;

    //查看全部审核通过的活动
    @RequestMapping("getall")
    @ResponseBody
    public BaseModel<List<Activity>> getAct() {
        JSONObject jsonObject = this.convertRequestBody();
        Activity searchact = JSON.toJavaObject(jsonObject, Activity.class);

        BaseModel<List<Activity>> model = new BaseModel<>();
        List<Activity> allAactivity = null;
        allAactivity = activityService.getPassedActivity(searchact);
        model.setData(allAactivity);
        model.setPage(new PageInfo<Activity>(allAactivity));
        return model;
    }

    //查看待审核通过的活动
    @RequestMapping("checkall")
    @ResponseBody
    public BaseModel<List<Activity>> checkAll() {
        JSONObject jsonObject = this.convertRequestBody();
        Activity searchact = JSON.toJavaObject(jsonObject, Activity.class);

        BaseModel<List<Activity>> model = new BaseModel<>();
        List<Activity> allAactivity = null;
        allAactivity = activityService.getWaitingActivity(searchact);
        model.setData(allAactivity);
        model.setPage(new PageInfo<Activity>(allAactivity));
        return model;
    }

    //审批活动
    @RequestMapping("checkact")
    @ResponseBody
    public BaseModel<SqlActivity> checkAct() {
        JSONObject jsonObject = this.convertRequestBody();
        LoginUser userinfo = (LoginUser) this.getApplicationInfo("user");

        SqlActivity activityCheck = JSON.toJavaObject(jsonObject, SqlActivity.class);
        BaseModel<SqlActivity> model = new BaseModel<>();
        if (userinfo == null) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("用户名无效");
        } else if (!userinfo.getRole().equals("admin")) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("用户无权审批");
        } else {
            if (activityService.checkActivity(activityCheck)) {
                model.setStatus(Constants.SUCCESS);
            }
            ;
            model.setMessage("活动状态更改成功");
        }
        return model;
    }

    //创建活动
    @RequestMapping("createact")
    @ResponseBody
    public BaseModel<List<SqlActivity>> createAct() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity activity = JSON.toJavaObject(jsonObject, SqlActivity.class);
        SqlOrganization userinfo = (SqlOrganization) this.getApplicationInfo("user");
        BaseModel<List<SqlActivity>> model = new BaseModel<>();

        if (userinfo == null) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("用户名无效");
        }
        /*else if(!userinfo.getRole().equals("organization")){
			model.setStatus(Constants.FAIL_INVALID_AUTH);
			model.setMessage("用户名无权创建活动");
		}*/
        else {
            activity.setOrganizationId(userinfo.getId());
            if (activityService.createActivity(activity)) {
                model.setStatus(Constants.SUCCESS);
            }
            ;
            model.setMessage("活动创建成功");
        }
        return model;
    }

    //修改活动
    @RequestMapping("updateact")
    @ResponseBody
    public BaseModel<List<SqlActivity>> updateAct() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity activity = JSON.toJavaObject(jsonObject, SqlActivity.class);
        LoginUser userinfo = (LoginUser) this.getApplicationInfo("user");
        BaseModel<List<SqlActivity>> model = new BaseModel<>();

        if (userinfo == null) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("用户名无效");
        } else if (!userinfo.getRole().equals("organization")) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("用户名无权修改活动");
        } else {
            if (activityService.createActivity(activity)) {
                model.setStatus(Constants.SUCCESS);
            }
            ;
            model.setMessage("活动修改成功");
        }
        return model;
    }

    //参加活动//取得活动id，加入student的id
    @RequestMapping("engageinact")
    @ResponseBody
    public BaseModel<SqlActivity> engageinAct() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity getAct = JSON.toJavaObject(jsonObject, SqlActivity.class);
        SqlStudent getStu = (SqlStudent) this.getApplicationInfo("user");
        BaseModel<SqlActivity> model = new BaseModel<>();
        if (!activityService.engageActivity(getStu, getAct)) {
            model.setMessage("操作失败");
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        ;
        return model;
    }

    //取消参加活动
    @RequestMapping("cancelEngage")
    @ResponseBody
    public BaseModel<SqlActivity> cancelEngage() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity getAct = JSON.toJavaObject(jsonObject, SqlActivity.class);
        SqlStudent getStu = (SqlStudent) this.getApplicationInfo("user");
        BaseModel<SqlActivity> model = new BaseModel<>();
        if (!activityService.cancelEngage(getStu, getAct)) {
            model.setMessage("操作失败");
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        ;
        return model;
    }

    //删除活动
    @RequestMapping("deleteAct")
    @ResponseBody
    public BaseModel<SqlActivity> deleteAct() {
        JSONObject jsonobject = this.convertRequestBody();
        SqlActivity getAct = JSON.toJavaObject(jsonobject, SqlActivity.class);
        String role = (String) this.getApplicationInfo("role");
        BaseModel<SqlActivity> model = new BaseModel<>();
        if (role != null && role.equals("admin")) {
            if (!activityService.deleteActivity(getAct)) {
                model.setMessage("未删除指定的活动");
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            }
            ;
        } else {
            model.setMessage("无效用户");
            model.setStatus(Constants.FAIL_INVALID_USER);
        }
        return model;
    }

    //获取activity photo
    @RequestMapping(value = "getPhoto", method = RequestMethod.GET)
    @ResponseBody
    public void getIcon(@RequestParam(value = "photo", required = false) String filename, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "image/jpeg");//设置响应的媒体类型，这样浏览器会识别出响应的是图片
        byte[] image;
        image = this.getPicture(ACTIVITY_IMG + filename);
        response.getOutputStream().write(image);
    }
}
