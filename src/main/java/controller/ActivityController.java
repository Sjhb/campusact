package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import constant.Field;
import model.*;
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
import service.IOService;
import service.OrganizationService;
import vo.Activity;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("activity")
public class ActivityController extends BaseController {
    @Autowired
    protected IOService ioService;
    @Autowired
    protected ActivityService activityService;
    @Autowired
    protected OrganizationService organizationService;

    @Value("#{propertiesReader['ACTIVITY_IMG']}")
    private String ACTIVITY_IMG;


    //    查看是否具有创建活动的权限
    @RequestMapping("checkAuth")
    @ResponseBody
    public BaseModel<String> checkAuth() {
        BaseModel<String> model = new BaseModel<>();
        boolean re = this.isPermmit(Field.ORGANIZATION);
        if (!re) {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("权限错误");
        }
        long oId = this.getUserInfo();
        int auth = organizationService.checkAuth(oId);
        switch (auth) {
            case 0: {
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("未知错误");
                break;
            }
            case 1: {
                model.setStatus(Constants.SUCCESS);
                model.setMessage("success");
                break;
            }
            case 2: {
                model.setStatus(Constants.FAIL_INVALID_USER);
                model.setMessage("您的账户目前还处于未审核状态，不能发布活动信息");
                break;
            }
            case 3: {
                model.setStatus(Constants.FAIL_INVALID_USER);
                model.setMessage("您的账户未通过审核，不能发布活动信息");
                break;
            }
        }
        return model;
    }

    //    根据组织id查找活动
    @RequestMapping("getActByOid")
    @ResponseBody
    public BaseModel<List<Activity>> getActByOid() {
        BaseModel<List<Activity>> model = new BaseModel<>();
        Activity activity = (Activity) this.getObject(new Activity());
        long Oid = this.getUserInfo();
        if (Oid == 0) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("无效用户");
        } else {
            List<Activity> result = activityService.getActivityByOid(activity, Oid);
            model.setPage(new PageInfo<Activity>(result));
            model.setData(result);
        }
        return model;
    }

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
        BaseModel<SqlActivity> model = new BaseModel<>();
        if (!this.isPermmit(Field.ADMINISTOR)) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("权限错误");
            return model;
        }
        ;
        SqlActivity activityCheck = (SqlActivity) this.getObject(new SqlActivity());
        if (!activityService.checkActivity(activityCheck)) {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("更改失败");
        }
        ;
        model.setMessage("活动状态更改成功");
        return model;
    }

    //创建活动
    @RequestMapping("createact")
    @ResponseBody
    public BaseModel<String> createAct() {
        BaseModel<String> model = new BaseModel<>();
        SqlActivity activity = (SqlActivity) this.getObject(new SqlActivity());
        if (!this.isPermmit(Field.ORGANIZATION)) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("权限错误");
            return model;
        }
        Long Oid = this.getUserInfo();
        activity.setOrganizationId(Oid);
        long actId = activityService.createActivity(activity);
        if (actId == 0 || actId < 0) {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("活动信息提交失败");
            return model;
        }
        this.setApplicationInfo("actId", activity.getId());
        return model;
    }

    //修改活动图片
    @RequestMapping(value = "alterActPhoto")
    @ResponseBody
    public BaseModel<String> alterActPhoto() throws IOException {
        BaseModel<String> model = new BaseModel<>();
        if (!this.isPermmit(Field.ORGANIZATION)) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("权限错误");
            return model;
        }
        Map result = this.getMultiform();
        Map fileTable = (Map) (result.get("fileTable"));
        if (fileTable.size() == 0) {
            this.setApplicationInfo("actId", null);
            return model;
        }
        Map formfieldsTable = (Map) (result.get("formfieldsTable"));
        Map fileFormName = (Map) (result.get("fileFormName"));
        long actId = (long) this.getApplicationInfo("actId");
        String fileName = (String) fileFormName.get("file");
        String newFileName = Long.toString(actId) + Math.random() * 100 + ioService.getType(fileName);
        ioService.writeFile(ACTIVITY_IMG, newFileName, (byte[]) fileTable.get(fileName));
        activityService.addActPhoto("\"" + newFileName + "\"", actId);
        return model;
    }

    //修改活动
    @RequestMapping("test")
    @ResponseBody
    public void test() {
        activityService.addActPhoto("\"tes2\"", 100);
    }

    //修改活动
    @RequestMapping("updateact")
    @ResponseBody
    public BaseModel<List<SqlActivity>> updateAct() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity activity = JSON.toJavaObject(jsonObject, SqlActivity.class);
        MiUserInfo userinfo = (MiUserInfo) this.getApplicationInfo("user");
        BaseModel<List<SqlActivity>> model = new BaseModel<>();

        if (userinfo == null) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("用户名无效");
        } else if (!userinfo.getRole().getDetail().equals("organization")) {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("用户名无权修改活动");
        } else {
            if (activityService.alterActivity(activity)) {
                model.setStatus(Constants.SUCCESS);
            }
            ;
            model.setMessage("活动修改成功");
        }
        return model;
    }

    //参加活动//取得活动id，加入student的id
    @RequestMapping("engage")
    @ResponseBody
    public BaseModel<SqlActivity> engageinAct() {
        JSONObject jsonObject = this.convertRequestBody();
        SqlActivity getAct = JSON.toJavaObject(jsonObject, SqlActivity.class);
        BaseModel<SqlActivity> model = new BaseModel<>();
        long actId = getAct.getId();
        if (!this.isLogin()) {
            model.setMessage("未登录");
            model.setStatus(Constants.FAIL_INVALID_USER);
            return model;
        }
        ;
        if (!isPermmit(Field.STUDENT)) {
            model.setMessage("不是学生");
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            return model;
        }
        long stuId = this.getUserInfo();
        if(activityService.isEngage(actId,stuId)){
            model.setMessage("已经参加活动，请勿重复参加");
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }else
        if (!activityService.engageActivity("\"" + Long.toString(stuId) + "\"", actId)) {
            model.setMessage("操作失败");
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
        }
        return model;
    }

    //查看参加了的活动（stuid）
    @RequestMapping(value = "getActByStuid")
    @ResponseBody
    public BaseModel<List<Activity>> getActByStuid() {
        BaseModel<List<Activity>> model = new BaseModel<>();
        Activity activity = (Activity) this.getObject(new Activity());
        long stuId = this.getUserInfo();
        if (stuId == 0) {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("用户无效，可能未登录");
        } else {
            List<Activity> result = activityService.getActivityByStuid(activity, stuId);
            model.setData(result);
            model.setPage(new PageInfo<Activity>(result));
        }

        return model;
    }

    //    获取所有参加了活动的同学
    @RequestMapping(value = "getEngage")
    @ResponseBody
    public BaseModel<List> getEngage() {
        BaseModel model = new BaseModel();
        String test = activityService.getEngage(108);
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
        image = this.getPicture(ACTIVITY_IMG + File.separator + filename);
        response.getOutputStream().write(image);
    }
    //改变状态
    @RequestMapping(value = "organize", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> organize() throws IOException {
        BaseModel<String> model=new BaseModel<>();
        SqlActivity activity= (SqlActivity) this.getObject(new SqlActivity());
        if(isPermmit(Field.ORGANIZATION)){
            boolean re=activityService.changeState(activity);
            if(!re){
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("操作失败");
            }
        }else {
            model.setStatus(Constants.FAIL_INVALID_USER);
            model.setMessage("权限错误");
        }
        return  model;
    }
}
