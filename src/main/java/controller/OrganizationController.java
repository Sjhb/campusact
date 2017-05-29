package controller;

import base.BaseController;
import base.BaseModel;
import com.alibaba.fastjson.JSONObject;
import constant.Constants;
import model.SqlOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IOService;
import service.OrganizationService;

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
//    接受SqlOrganization类型对象
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> register() {
        JSONObject Json = this.convertRequestBody();
        BaseModel<String> model = new BaseModel<>();
        SqlOrganization org = JSONObject.toJavaObject(Json, SqlOrganization.class);
        long re = organizationService.insertOrg(org);
        if(re==0){
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("未知错误");
        }else if(re==2){
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("组织名已经被注册");
        }else{
            model.setMessage("success");
            this.setApplicationInfo("orgId",org.getId());
        }
        return model;
    }

    //头像上传储存
    @RequestMapping(value = "alterIcon")
    @ResponseBody
    public BaseModel<String> setIcon() throws IOException {
        BaseModel<String> model = new BaseModel<>();
        HashMap<String, Map> requestForm = new HashMap<>();
        requestForm = this.getMultiform();
        long orgId= (long) this.getApplicationInfo("orgId");
        String filename = (String) requestForm.get("fileFormName").get("file");
        String iconname = "default.jpg";
        iconname = Long.toString(orgId) + ioService.getType(filename);
        int result = ioService.writeFile(ORGANIZATION_ICON, iconname, (byte[]) requestForm.get("fileTable").get(filename));
        if (result == 300) {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("图片存储错误");
            return model;
        } else {
            boolean iconresult = organizationService.alterIcon(iconname, orgId);
            if (!iconresult) {
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("图片上传成功，变更信息失败");
            }
        }
        return model;
    }
    //文件上传储存
    @RequestMapping(value = "alterDocu")
    @ResponseBody
    public BaseModel<String> setDocu() throws IOException {
        BaseModel<String> model = new BaseModel<>();
        HashMap<String, Map> requestForm = new HashMap<>();
        requestForm = this.getMultiform();
        long orgId= (long) this.getApplicationInfo("orgId");
        String filename = (String) requestForm.get("fileFormName").get("file");
        String docuname = "default.jpg";
        docuname = Long.toString(orgId) + ioService.getType(filename);
        int result = ioService.writeFile(DOCUMENT_PATH, docuname, (byte[]) requestForm.get("fileTable").get(filename));
        if (result == 300) {
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("图片存储错误");
            return model;
        } else {
            boolean docuresult = organizationService.alterDocu(docuname, orgId);
            if (!docuresult) {
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("图片上传成功，变更信息失败");
            }
        }
        return model;
    }


}
