package controller;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import constant.Field;
import model.MiUser;
import model.SqlOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrganizationService;
import service.StudentService;
import sqlInters.SqlOrganizationOperation;
import sqlInters.SqlStudentOperation;

/**
 * Created by Manlin on 2017/4/13.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController extends BaseController {
    @Autowired
    StudentService studentService;
    @Autowired
    OrganizationService organizationService;

    //    重置用户密码
    @RequestMapping(value = "resetPass")
    @ResponseBody
    public BaseModel<String> resetPass(){
        BaseModel<String> model=new BaseModel();
        if(this.isPermmit("admin")){
            MiUser user= (MiUser) this.getObject(new MiUser());
            switch (user.getRole()){
                case Field.STUDENT: if(!studentService.resetPass(user)){
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("操作失败");
                } break;
                case Field.ORGANIZATION: if(!organizationService.resetPass(user)){
                    model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                    model.setMessage("操作失败");} break;
                default:break;
            }
        }else {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("无权访问");
        }
        return  model;

    }
}
