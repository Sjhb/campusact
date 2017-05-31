package sqlInters;

import model.MiUser;
import model.MiUserInfo;
import model.SqlStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SqlStudentOperation {
	public List<SqlStudent> selectSqlStudent(@Param("id") long id) ;//通过id
	public int alterStudent(SqlStudent student);//修改学生信息
	public int insertStu(SqlStudent sqlStudent);//插入学生
	public int alterIcon(@Param("icon") String icon,@Param("id") long id);
	public int resetPass(MiUser user); //重置密码
	public int updateStu(SqlStudent sqlStudent);//更新学生信息
	public int requestResetPass(MiUserInfo miinfo);//更新学生信息
}
