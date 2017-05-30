package controller;

import base.BaseController;
import base.BaseModel;
import constant.Constants;
import constant.Field;
import model.MiUserInfo;
import model.SqlRole;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.IOService;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manlin on 2017/4/7.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController extends BaseController {
    @Autowired
    StudentService studentService;
    @Autowired
    IOService ioService;
    @Value("#{propertiesReader['STUDENT_ICON']}")
    private String STUDENT_ICON;
//   修改个人心
    @RequestMapping(value = "alterInfo", method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> alterInfo(HttpServletRequest request){
        BaseModel<String> model=new BaseModel<>();
        Map<String,String[]> data=request.getParameterMap();
        SqlStudent stu = new SqlStudent(Long.parseLong(data.get("id")[0]),"default.jpg",data.get("name")[0],data.get("password")[0],data.get("sex")[0],data.get("phone")[0],data.get("major")[0],data.get("classes")[0], data.get("college")[0]);
        boolean re=studentService.updateStu(stu);
        if(!re){
            model.setStatus(Constants.FAIL_BUSINESS_ERROR);
            model.setMessage("操作失败");
        }
        return model;
    }
//    注册接口
//    接受Sqlstudent类型对象
    @RequestMapping(value = "register", method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> register(HttpServletRequest request){
//        JSONObject Json=this.convertRequestBody();
        BaseModel<String> model=new BaseModel<>();
        Map<String,String[]> data=request.getParameterMap();
        SqlStudent stu = new SqlStudent(Long.parseLong(data.get("id")[0]),"default.jpg",data.get("name")[0],data.get("password")[0],data.get("sex")[0],data.get("phone")[0],data.get("major")[0],data.get("classes")[0], data.get("college")[0]);
        int re=studentService.insertStu(stu);
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

