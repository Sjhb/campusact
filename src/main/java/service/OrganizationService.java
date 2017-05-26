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
    public int insertOrg(SqlOrganization org){
//        1:名称被占用 2：成功 0：失败
        int cloumn=sqlOrganizationOperation.getOrgByName(org);
        if(cloumn>0){
            return 1;
        }
        int re=sqlOrganizationOperation.insertOrg(org);
        if(re>0){
            return 2;
        }else
            return 0;
    }
}
