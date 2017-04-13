package service;

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
//    变更头像
    public  boolean  alterIcon(String icon,long id){
        int result=sqlStudentOperation.alterIcon(icon,id);
        if (result>0){
            return  true;
        }
        return  false;
    }
    //重置密码
    public  boolean resetPass(MiUser user){
        int result=sqlStudentOperation.resetPass(user);
        if (result>0) return true;
        return  false;
    }
}
