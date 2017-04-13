package service;

import model.MiUser;
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
}
