package controller;

import base.BaseController;
import base.BaseModel;
import com.github.pagehelper.PageInfo;
import constant.Constants;
import constant.Field;
import model.MiUser;
import model.SqlAdmin;
import model.SqlOrganization;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrganizationService;
import service.StudentService;
import sqlInters.SqlOrganizationOperation;
import sqlInters.SqlStudentOperation;

import java.util.List;

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

    //    重置学生用户密码
    @RequestMapping(value = "resetStuPass")
    @ResponseBody
    public BaseModel<String> resetStuPass(){
        BaseModel<String> model=new BaseModel();
        SqlStudent stu= (SqlStudent) this.getObject(new SqlStudent());
        if(this.isPermmit("admin")){
           boolean re=studentService.resetPass(stu);
           if(!re){
             model.setStatus(Constants.FAIL_BUSINESS_ERROR);
             model.setMessage("操作失败");
           }
        }else {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("无权访问");
        }
        return  model;

    }
    //    重置组织用户密码
    @RequestMapping(value = "resetOrgPass")
    @ResponseBody
    public BaseModel<String> resetOrgPass(){
        BaseModel<String> model=new BaseModel();
        SqlOrganization organization= (SqlOrganization) this.getObject(new SqlOrganization());
        if(this.isPermmit("admin")){
            boolean re=organizationService.resetPass(organization);
            if(!re){
                model.setStatus(Constants.FAIL_BUSINESS_ERROR);
                model.setMessage("操作失败");
            }
        }else {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("无权访问");
        }
        return  model;

    }
    //    重置用户密码
    @RequestMapping(value = "getResetRequestStu")
    @ResponseBody
    public BaseModel<List<SqlStudent>> getResetRequestStu(){
        BaseModel<List<SqlStudent>> model=new BaseModel();
        SqlStudent student= (SqlStudent) this.getObject(new SqlStudent());
        if(this.isPermmit("admin")){
            List<SqlStudent> stuList=studentService.getRequestResetPass(student);
            model.setData(stuList);
            model.setPage(new PageInfo<SqlStudent>(stuList));
        }else {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("无权访问");
        }
        return  model;

    }
    //    重置用户密码
    @RequestMapping(value = "getResetRequestOrg")
    @ResponseBody
    public BaseModel<List<SqlOrganization>> getResetRequestOrg(){
        BaseModel<List<SqlOrganization>> model=new BaseModel();
        SqlOrganization organization= (SqlOrganization) this.getObject(new SqlOrganization());
        if(this.isPermmit("admin")){
            List<SqlOrganization> orgList=organizationService.getRequestResetPass(organization);
            model.setData(orgList);
            model.setPage(new PageInfo<SqlOrganization>(orgList));
        }else {
            model.setStatus(Constants.FAIL_INVALID_AUTH);
            model.setMessage("无权访问");
        }
        return  model;

    }
}
