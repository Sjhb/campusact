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
    @RequestMapping(value = "register", method= RequestMethod.POST)
    @ResponseBody
    public BaseModel<String> register(HttpServletRequest request){
//        JSONObject Json=this.convertRequestBody();
        BaseModel<String> model=new BaseModel<>();
        Map<String,String[]> data=request.getParameterMap();
        data.get("id");
//        SqlStudent newstu=JSONObject.toJavaObject(Json,SqlStudent.class);
//        int re=studentService.insertStu(newstu);
//        switch (re){
//            case 0:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("未知错误"); break;
//            case 1:model.setStatus(Constants.FAIL_BUSINESS_ERROR);model.setMessage("学号重复");break;
//            case 2:break;
//            default:break;
//        }
        return model;
    }
}
