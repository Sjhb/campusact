package service;

import model.MiUser;
import model.SqlOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqlInters.SqlOrganizationOperation;

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
}
