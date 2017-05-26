package sqlInters;

import model.MiUser;
import model.MiUserInfo;
import model.SqlOrganization;

import java.util.List;


public interface SqlOrganizationOperation {
	public List<SqlOrganization> selectSqlOrganization(MiUserInfo user) ;//通过id
	public int alterSqlOrganization(SqlOrganization organization);
	public int resetPass(MiUser user);//重置密码
	public int getOrgByName(SqlOrganization organization);//通过名称
	public int insertOrg(SqlOrganization organization);//插入
}
