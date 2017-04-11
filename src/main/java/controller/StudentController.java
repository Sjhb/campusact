package controller;

import base.BaseController;
import base.BaseModel;
import com.alibaba.fastjson.JSONObject;
import constant.Constants;
import model.SqlStudent;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Manlin on 2017/4/7.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController extends BaseController {
    @Autowired
    StudentService studentService;

//    注册接口
//    接受Sqlstudent类型对象
    @RequestMapping(value = "register", method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> register(HttpServletRequest request){
//        JSONObject Json=this.convertRequestBody();
        BaseModel<String> model=new BaseModel<>();
        Map<String,String[]> data=request.getParameterMap();
        String s=data.get("college")[0];
        SqlStudent stu = new SqlStudent(Long.parseLong(data.get("id")[0]),"default.jpg",data.get("name")[0],data.get("password")[0],data.get("sex")[0],data.get("phone")[0],data.get("major")[0],data.get("classes")[0], data.get("college")[0]);
        int re=studentService.insertStu(stu);
        switch (re){
            case 0:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("未知错误"); break;
            case 1:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("学号重复");break;
            case 2:break;
            default:break;
        }
        return model;
    }
    //头像上传储存
    @RequestMapping(value = "setIcon")
    @ResponseBody
    public BaseModel<String> setIcon(HttpServletRequest request){
        BaseModel<String> model=new BaseModel<>();

        return  model;
    }
}

