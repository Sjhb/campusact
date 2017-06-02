package service;

import com.github.pagehelper.PageHelper;
import model.MiUser;
import model.SqlStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqlInters.SqlStudentOperation;

import java.util.List;

/**
 * Created by Manlin on 2017/4/7.
 */
@Service
public class StudentService {
    @Autowired
    SqlStudentOperation sqlStudentOperation;

    //插入新信息
    public int insertStu(SqlStudent stu){
//        2：成功 1：id重复 0：其他错误
        int re=0;
        List<SqlStudent> list=sqlStudentOperation.selectSqlStudent(stu.getId());
        if(list.size()>0){
            re=1;
            return re;
        }
        if(sqlStudentOperation.insertStu(stu)>0){
            re=2;
            return  re;
        }
        return  re;
    }
    //获取学生信息
    public SqlStudent getStuById(long sId){
        SqlStudent student=null;
        List<SqlStudent> list=sqlStudentOperation.selectSqlStudent(sId);
        if(list.size()==0){
            return student;
        }else {
            return list.get(0);
        }
    }
//    更新学生信息
    public  boolean updateStu(SqlStudent sqlStudent){
        int re=sqlStudentOperation.updateStu(sqlStudent);
        if(re>0){
            return true;
        }else return false;
    }
//    变更头像
    public  boolean  alterIcon(String icon,long id){
        int result=sqlStudentOperation.alterIcon(icon,id);
        if (result>0){
            return  true;
        }
        return  false;
    }
    //重置密码
    public  boolean resetPass(SqlStudent sqlStudent){
        if(sqlStudent.getResetpass()==3){
            int result=sqlStudentOperation.rejectResetPass(sqlStudent);
        }
        int result=sqlStudentOperation.resetPass(sqlStudent);
        if (result>0) return true;
        return  false;
    }
    public  List<SqlStudent> getRequestResetPass(SqlStudent student){
        PageHelper.startPage(student.getPageNum(), 9);
        List<SqlStudent> result=sqlStudentOperation.getRequestResetPass();
        return  result;
    }
}
