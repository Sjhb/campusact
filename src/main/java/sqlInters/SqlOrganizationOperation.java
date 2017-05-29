package sqlInters;

import model.MiUser;
import model.MiUserInfo;
import model.SqlOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlOrganizationOperation {
	public List<SqlOrganization> selectSqlOrganization(MiUserInfo user) ;//通过id
	public int alterSqlOrganization(SqlOrganization organization);
	public int resetPass(MiUser user);//重置密码
	public int getOrgByName(SqlOrganization organization);//通过名称
	public long insertOrg(SqlOrganization organization);//插入
	public int alterIcon(@Param("iconName")String iconName,@Param("orgId")long orgId);//设置头像
	public int alterDocu(@Param("docuName")String docuName,@Param("orgId")long orgId);//设置文件信息
}
