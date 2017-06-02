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

    public long insertOrg(SqlOrganization org) {
//        2:名称被占用 1：成功 0：失败
        long re = 0;
        int del;
        List<SqlOrganization> cloumn = sqlOrganizationOperation.getOrgByName(org);
        if (cloumn.size() > 0) {
            if (cloumn.get(0).getState() == 1 || cloumn.get(0).getState() == 2) {
                return re = 2;
            } else {
                del = sqlOrganizationOperation.deleteOrg(cloumn.get(0));
                if (del > 0) {
                    re = sqlOrganizationOperation.insertOrg(org);
                    if (re > 0) {
                        return re;
                    } else {
                        return re = 0;
                    }
                } else {
                    return 0;
                }
            }
        } else {
            re = sqlOrganizationOperation.insertOrg(org);
            if (re > 0) {
                return re;
            } else {
                return re = 0;
            }
        }
    }

    //    头像
    public boolean alterIcon(String name, long id) {
        int re = sqlOrganizationOperation.alterIcon(name, id);
        if (re == 0) {
            return false;
        } else {
            return true;
        }
    }

    //文件
    public boolean alterDocu(String name, long id) {
        int re = sqlOrganizationOperation.alterDocu(name, id);
        if (re == 0) {
            return false;
        } else {
            return true;
        }
    }

    //获得所有组织者
    public List<SqlOrganization> getOrg(SqlOrganization org) {

        List<SqlOrganization> result = sqlOrganizationOperation.getAllOrg();
        return result;
    }

    //    审批组织者
    public boolean checkOrg(SqlOrganization org) {
        int dere = sqlOrganizationOperation.checkOrg(org);
        if (dere > 0) {
            return true;
        } else return false;
    }

    //删除用户
    public boolean deleteOrg(SqlOrganization org) {
        int dere = sqlOrganizationOperation.deleteOrg(org);
        if (dere > 0) {
            return true;
        } else return false;
    }

    public int checkAuth(long oId) {
        int dere = 0;
        dere = sqlOrganizationOperation.checkAuth(oId);
        return dere;
    }

    public List<SqlOrganization> getRequestResetPass(SqlOrganization organization) {
        PageHelper.startPage(organization.getPageNum(), 9);
        List<SqlOrganization> result = sqlOrganizationOperation.getRequestResetPass();
        return result;
    }

    //重置密码
    public boolean resetPass(SqlOrganization sqlOrganization) {
        if (sqlOrganization.getResetpass() == 3) {
            int result = sqlOrganizationOperation.rejectResetPass(sqlOrganization);
        }
        int result = sqlOrganizationOperation.resetPass(sqlOrganization);
        if (result > 0) return true;
        return false;
    }
}