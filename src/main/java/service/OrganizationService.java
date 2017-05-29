package service;

import com.github.pagehelper.PageHelper;
import model.MiUser;
import model.SqlOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqlInters.SqlOrganizationOperation;
import vo.Activity;

import java.util.List;

/**
 * Created by Manlin on 2017/4/13.
 */
@Service
public class OrganizationService {
    @Autowired
    SqlOrganizationOperation sqlOrganizationOperation;

    public boolean resetPass(MiUser user){
        int result=sqlOrganizationOperation.resetPass(user);
        if (result>0) return true;
        return false;
    }
    public long insertOrg(SqlOrganization org){
//        2:名称被占用 1：成功 0：失败
        long re=0;
        int cloumn=sqlOrganizationOperation.getOrgByName(org);
        if(cloumn>0){
            return re=2;
        }
        re=sqlOrganizationOperation.insertOrg(org);
        if(re>0){
            return re;
        }else
            return re=0;
    }
//    头像
    public boolean alterIcon(String name,long id){
        int re=sqlOrganizationOperation.alterIcon(name,id);
            if(re==0){
                return false;
            }else {
                return true;
            }
          }
          //文件
    public boolean alterDocu(String name,long id){
        int re=sqlOrganizationOperation.alterDocu(name,id);
        if(re==0){
            return false;
        }else {
            return true;
        }
    }
    //获得所有组织者
    public List<SqlOrganization> getOrg(SqlOrganization org){
        PageHelper.startPage(org.getPageNum(), 9);
        List<SqlOrganization> result= sqlOrganizationOperation.getAllOrg();
        return result;
    }
//    审批组织者
   public  boolean checkOrg(SqlOrganization org){
        if(org.getState()==3){
            int dere=sqlOrganizationOperation.deleteOrg(org);
            if (dere>0){
                return true;
            }else return false;
        }else {
            int chre=sqlOrganizationOperation.checkOrg(org);
            if (chre>0) return true;
            else return false;
        }
   }
}
