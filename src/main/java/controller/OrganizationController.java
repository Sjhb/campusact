package controller;

import base.BaseController;
import base.BaseModel;
import com.alibaba.fastjson.JSONObject;
import constant.Constants;
import constant.Field;
import model.MiUserInfo;
import model.SqlOrganization;
import model.SqlRole;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IOService;
import service.OrganizationService;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manlin on 2017/5/24.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController extends BaseController {

    @Autowired
    OrganizationService organizationService;
    @Autowired
    IOService ioService;
    @Value("#{propertiesReader['ORGANIZATION_ICON']}")
    private String ORGANIZATION_ICON;
    @Value("#{propertiesReader['DOCUMENT_PATH']}")
    private String DOCUMENT_PATH;
    //    注册接口
//    接受Sqlstudent类型对象
    @RequestMapping(value = "register", method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> register(){
        JSONObject Json=this.convertRequestBody();
        BaseModel<String> model=new BaseModel<>();
        SqlOrganization org=JSONObject.toJavaObject(Json,SqlOrganization.class);
        int re=OrganizationService.insertOrg(org);
        switch (re){
            case 0:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("未知错误"); break;
            case 1:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("学号重复");break;
            case 2:this.setApplicationInfo("role","student");this.setApplicationInfo("user",new MiUserInfo(new SqlRole(10, Field.STUDENT,""),stu.getId(),stu.getName(),"default.jpg")); break;
            default:break;
        }
        return model;
    }
    //头像上传储存
    @RequestMapping(value = "setIcon")
    @ResponseBody
    public BaseModel<String> setIcon() throws IOException {
        BaseModel<String> model=new BaseModel<>();
        HashMap<String,Map> requestForm=new HashMap<>();
        requestForm=this.getMultiform();
        long id=((MiUserInfo)this.getApplicationInfo("user")).getId();
        String filename= (String) requestForm.get("fileFormName").get("file");
        String iconname="default.jpg";
        iconname=Long.toString(id)+ioService.getType(filename);
        int result=ioService.writeFile(STUDENT_ICON,iconname, (byte[]) requestForm.get("fileTable").get(filename));
        if (result==300){
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("图片存储错误");
            return  model;
        }else {
            boolean iconresult=studentService.alterIcon(iconname,id);
            if (!iconresult){
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("存储成功，变更信息失败");
            }
        }
        return  model;
    }

}