package sqlInters;

import model.MiUser;
import model.MiUserInfo;
import model.SqlOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlOrganizationOperation {
	public List<SqlOrganization> selectSqlOrganization(MiUserInfo user) ;//通过id
	public int alterSqlOrganization(SqlOrganization organization);
	public int resetPass(SqlOrganization sqlOrganization);//重置密码
	public int rejectResetPass(SqlOrganization sqlOrganization);//重置密码
	public List<SqlOrganization> getOrgByName(SqlOrganization organization);//通过名称
	public List<SqlOrganization> checkOrgByName(SqlOrganization organization);//通过名称
	public long insertOrg(SqlOrganization organization);//插入
	public int alterIcon(@Param("iconName")String iconName,@Param("orgId")long orgId);//设置头像
	public int alterDocu(@Param("docuName")String docuName,@Param("orgId")long orgId);//设置文件信息
	public List<SqlOrganization> getAllOrg();//获取组织者
	public int checkOrg(SqlOrganization organization);//通过审核
	public int deleteOrg(SqlOrganization organization);//删除
	public int checkAuth(@Param("oId")long oId);//检查权限
	public int requestResetPass(MiUserInfo miinfo);//申请重置密码
	public List<SqlOrganization> getRequestResetPass();//申请重置密码
}
