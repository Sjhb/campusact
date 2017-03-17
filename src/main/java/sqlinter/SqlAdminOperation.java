package sqlinter;

import model.MiUserInfo;
import model.SqlAdmin;

import java.util.List;


public interface SqlAdminOperation {
	public List<SqlAdmin> selectSqlAdmin(MiUserInfo user) ;//根据用户id以及密码
	public List<SqlAdmin> selectAllSqlAdmin();
	public int alterSqlAdmin(SqlAdmin admin);
	
}
